package BackEnd;

import ASM.ASMBlock;
import ASM.ASMFunction;
import ASM.ASMModule;
import ASM.Inst.*;
import ASM.Operand.*;
import IR.IRModule;
import IR.IRVisitor;
import IR.Value.IRBasicBlock;
import IR.Value.IRValue;
import IR.Value.User.Constant.GlobalValue.IRFunction;
import IR.Value.User.Constant.IRBoolConst;
import IR.Value.User.Constant.IRIntConstant;
import IR.Value.User.Constant.IRNullptrConstant;
import IR.Value.User.Constant.IRZeroInitConstant;
import IR.Value.User.Instruction.*;

import java.util.ArrayList;

public class ASMBuilder implements IRVisitor {
    public ASMModule asmModule = new ASMModule();
    public ASMFunction curFunc = null;
    public ASMBlock curBlock = null;

    private ASMPhysicalASMReg getPhysicalReg(String name) {
        if (ASMPhysicalASMReg.regMap.containsKey(name)) return ASMPhysicalASMReg.regMap.get(name);
        else throw new RuntimeException("Physical register " + name + " not found.");
    }

    private Integer getConstValue(IRValue value) {
        if (value instanceof IRIntConstant) return ((IRIntConstant) value).constIntData;
        else if (value instanceof IRBoolConst) return ((IRBoolConst) value).constBoolData ? 1 : 0;
        else if (value instanceof IRNullptrConstant || value instanceof IRZeroInitConstant) return 0;
        else return null;
    }

    private ASMReg getReg(IRValue value) {
        if (value.asmOperand != null) {
            if (value.asmOperand instanceof ASMReg) return (ASMReg) value.asmOperand;
            else throw new RuntimeException("Unknown operand type");
        }
        Integer constValue = getConstValue(value);
        if (constValue != null) {
            if (constValue == 0) {
                value.asmOperand = getPhysicalReg("zero");
                return (ASMReg) value.asmOperand;
            } else {
                ASMVirtualReg reg = new ASMVirtualReg(value.valueType.size());
                ASMLiInst liInst = new ASMLiInst(reg, new ASMImm(constValue));
                curBlock.addInst(liInst);
                return reg;
            }
        } else {
            ASMVirtualReg reg = new ASMVirtualReg(value.valueType.size());
            value.asmOperand = reg;
            return reg;
        }
    }


    public ASMBuilder() {
    }


    @Override
    public void visit(IRModule irModule) {
        irModule.IRGlobalVariableMap.forEach((name, globalVariable) -> {
            ASMGlobalValue globalValue = new ASMGlobalValue(name);
            globalVariable.asmOperand = globalValue;
            asmModule.globalValueList.add(globalValue);
        });

        irModule.IRStrConstantMap.forEach((name, strConstant) -> {
            ASMGlobalString globalString = new ASMGlobalString(name, strConstant.strConstData);
            //strConstant.asmOperand = globalString;
            asmModule.globalStringList.add(globalString);
        });

        irModule.IRFunctionMap.forEach((name, function) -> {
            ASMFunction asmFunction = new ASMFunction(function.funcName);
            //function.asmOperand = asmFunction;
            asmModule.funcList.add(asmFunction);
            function.accept(this);
        });
    }

    @Override
    public void visit(IRBasicBlock irBasicBlock) {
        curBlock = new ASMBlock(irBasicBlock.blockName);
        irBasicBlock.instList.forEach(inst -> {
            inst.accept(this);
        });
        irBasicBlock.terminator.accept(this);
    }

    @Override
    public void visit(IRFunction function) {
        ASMFunction asmFunction = (ASMFunction) function.asmOperand;
        curFunc = asmFunction;

        ASMVirtualReg.virtualRegNum = 0;

        ASMBlock asmEntryBlock = new ASMBlock(function.entryBlock.blockName);
        function.entryBlock.asmOperand = asmEntryBlock;
        asmFunction.addBlock(asmEntryBlock);
        curBlock = asmEntryBlock;

        //分配栈空间
        ASMBinaryInst setSpaceInst = new ASMBinaryInst("addi", getPhysicalReg("sp"), getPhysicalReg("sp"), null, new ASMStackOffset(0, ASMStackOffset.StackOffsetType.lowerSp));
        asmFunction.decreaseStackPtrInst = setSpaceInst;
        curBlock.addInst(setSpaceInst);
        //save ra
        ASMVirtualReg raSaveReg = new ASMVirtualReg();
        ASMMvInst saveRaInst = new ASMMvInst(raSaveReg, getPhysicalReg("ra"));
        curBlock.addInst(saveRaInst);
        //save callee-save registers
        var calleeSaveRegList = new ArrayList<ASMReg>();
        ASMPhysicalASMReg.calleeSavedRegList.forEach(calleeSaveReg -> {
            ASMVirtualReg vCalleeSaveReg = new ASMVirtualReg();
            calleeSaveRegList.add(vCalleeSaveReg);
            ASMMvInst saveCalleeSaveInst = new ASMMvInst(vCalleeSaveReg, calleeSaveReg);
            curBlock.addInst(saveCalleeSaveInst);
        });
        //set arguments
        for (int i = 0; i < function.operandSize(); i++) {
            IRValue arg = function.getOperand(i);
            if (i < 8) {
                arg.asmOperand = getPhysicalReg("a" + i);
            } else {
                ASMVirtualReg virtualReg = new ASMVirtualReg(4);
                arg.asmOperand = virtualReg;
                asmFunction.paraList.add(virtualReg);
                ASMLoadInst loadInst = new ASMLoadInst(4, virtualReg, getPhysicalReg("sp"), new ASMStackOffset(4 * (i - 8), ASMStackOffset.StackOffsetType.getArg));
            }
        }

        function.entryBlock.accept(this);
        function.blockList.forEach(block -> {
            ASMBlock asmBlock = new ASMBlock(block.blockName);
            block.asmOperand = asmBlock;
            asmFunction.addBlock(asmBlock);
            block.accept(this);
        });

        ASMBlock asmExitBlock = new ASMBlock(function.exitBlock.blockName);
        function.exitBlock.asmOperand = asmExitBlock;
        asmFunction.addBlock(asmEntryBlock);
        curBlock = asmExitBlock;

        //restore callee-save registers
        for (int i = 0; i < ASMPhysicalASMReg.calleeSavedRegList.size(); i++) {
            ASMMvInst restoreCalleeSaveInst = new ASMMvInst(ASMPhysicalASMReg.calleeSavedRegList.get(i), calleeSaveRegList.get(i));
            curBlock.addInst(restoreCalleeSaveInst);
        }
        //restore ra
        ASMMvInst restoreRaInst = new ASMMvInst(getPhysicalReg("ra"), raSaveReg);
        curBlock.addInst(restoreRaInst);

        //restore stack
        ASMBinaryInst restoreStackInst = new ASMBinaryInst("addi", getPhysicalReg("sp"), getPhysicalReg("sp"), null,
                new ASMStackOffset(0, ASMStackOffset.StackOffsetType.raiseSp));
        asmFunction.increaseStackPtrInst = restoreStackInst;
        curBlock.addInst(restoreStackInst);

        //return
        ASMRetInst retInst = new ASMRetInst();
        curBlock.addInst(retInst);

    }

    @Override
    public void visit(IRAllocaInst irInst) {
        irInst.asmOperand = new ASMStackOffset(4 * curFunc.allocaCnt, ASMStackOffset.StackOffsetType.alloca);
        curFunc.allocaCnt++;
    }

    String opTrans(String irOp) {
        return switch (irOp) {
            case "add" -> "add";
            case "sub" -> "sub";
            case "mul" -> "mul";
            case "sdiv" -> "div";
            case "srem" -> "rem";
            case "shl" -> "sll";
            case "ashr" -> "sra";
            case "and" -> "and";
            case "or" -> "or";
            case "xor" -> "xor";
            default -> throw new RuntimeException("Unknown binary op " + irOp);
        };
    }

    Boolean ifCommutable(String asmOp) {
        return switch (asmOp) {
            case "add", "mul", "and", "or", "xor" -> true;
            default -> false;
        };
    }

    Boolean ifImm(IRValue value) {
        if (value == null) return false;
        else return (value instanceof IRIntConstant) || (value instanceof IRBoolConst);
    }

    Boolean ifHasImmVersion(String irOp) {
        return switch (irOp) {
            case "add", "sub", "shl", "ashr", "and", "or", "xor" -> true;
            default -> false;
        };
    }


    @Override
    public void visit(IRBinaryInst irBinaryInst) {
        String asmOp = opTrans(irBinaryInst.op);
        boolean commutable = ifCommutable(asmOp);
        boolean hasImmVersion = ifHasImmVersion(irBinaryInst.op);
        var lhs = irBinaryInst.lhs();
        var rhs = irBinaryInst.rhs();
        if (hasImmVersion) {
            if (commutable && ifImm(lhs)) {
                var tmp = lhs;
                lhs = rhs;
                rhs = tmp;
            }
            if (ifImm(rhs)) {
                int val = getConstValue(rhs);
                if (val < 1 << 11 && val >= -(1 << 11)) {
                    String iop = asmOp + "i";
                    if (asmOp.equals("sub")) {
                        iop = "addi";
                        val = -val;
                    }
                    ASMBinaryInst immBinaryInst = new ASMBinaryInst(iop, getReg(irBinaryInst), getReg(lhs), null, new ASMImm(val));
                    curBlock.addInst(immBinaryInst);
                }
            }
        }
        ASMBinaryInst binaryInst = new ASMBinaryInst(asmOp, getReg(irBinaryInst), getReg(lhs), getReg(rhs), null);
        curBlock.addInst(binaryInst);
    }

    @Override
    public void visit(IRBitCastInst irBitCastInst) {
        ASMMvInst mvInst = new ASMMvInst(getReg(irBitCastInst), getReg(irBitCastInst.fromValue()));
        curBlock.addInst(mvInst);
    }

    @Override
    public void visit(IRBrInst irBrInst) {
        irBrInst.asmOperand = null;
        if (irBrInst.ifCondition()) {
            ASMBrInst condBrInst = new ASMBrInst(ASMBrInst.ASMBrOp.beq, getReg(irBrInst.condition()), getPhysicalReg("zero"), (ASMBlock) irBrInst.ifFalseBlock().asmOperand);
            curBlock.addInst(condBrInst);
            ASMJumpInst jumpInst = new ASMJumpInst((ASMBlock) irBrInst.ifTrueBlock().asmOperand);
            curBlock.addInst(jumpInst);
        } else {
            ASMJumpInst jumpInst = new ASMJumpInst((ASMBlock) irBrInst.targetBlock().asmOperand);
            curBlock.addInst(jumpInst);
        }
    }

    @Override
    public void visit(IRCallInst irCallInst) {

    }

    @Override
    public void visit(IRGEPInst irgepInst) {

    }

    @Override
    public void visit(IRIcmpInst irIcmpInst) {

    }

    @Override
    public void visit(IRLoadInst irLoadInst) {

    }

    @Override
    public void visit(IRRetInst irRetInst) {

    }

    @Override
    public void visit(IRStoreInst irStoreInst) {

    }

    @Override
    public void visit(IRTruncInst irTruncInst) {
        ASMVirtualReg virtualReg = new ASMVirtualReg(4);
        ASMBinaryInst binaryInst = new ASMBinaryInst();
    }

    @Override
    public void visit(IRZextInst irZextInst) {

    }
}
