package BackEnd;

import ASM.ASMBlock;
import ASM.ASMFunction;
import ASM.ASMModule;
import ASM.Inst.*;
import ASM.Operand.*;
import IR.IRModule;
import IR.IRVisitor;
import IR.Type.*;
import IR.Value.IRBasicBlock;
import IR.Value.IRValue;
import IR.Value.User.Constant.*;
import IR.Value.User.Constant.GlobalValue.IRFunction;
import IR.Value.User.Instruction.*;

import java.util.ArrayList;

public class ASMBuilder implements IRVisitor {
    public IRModule irModule;
    public ASMModule asmModule;
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


    public ASMBuilder(ASMModule asmModule) {
        this.asmModule = asmModule;
    }


    @Override
    public void visit(IRModule _irModule) {
        irModule = _irModule;
        irModule.IRGlobalVariableMap.forEach((name, globalVariable) -> {
            ASMGlobalValue globalValue = new ASMGlobalValue(name);
            globalVariable.asmOperand = globalValue;
            asmModule.globalValueList.add(globalValue);
        });

        irModule.IRStrConstantMap.forEach((name, strConstant) -> {
            ASMGlobalString globalString = new ASMGlobalString(strConstant.valueName, strConstant.strConstData);
            strConstant.asmOperand = globalString;
            asmModule.globalStringList.add(globalString);
        });

        ASMFunction asmInitFunc = new ASMFunction(irModule.initFunc.funcName);
        irModule.initFunc.asmOperand = asmInitFunc;
        asmModule.funcList.add(asmInitFunc);


        irModule.IRFunctionMap.forEach((name, function) -> {
            ASMFunction asmFunction = new ASMFunction(function.funcName);
            asmModule.funcList.add(asmFunction);
            function.asmOperand = asmFunction;
        });

        irModule.initFunc.accept(this);

        irModule.IRFunctionMap.forEach((name, function) -> {
            if (!function.isBuiltIn) function.accept(this);
            else ((ASMFunction) function.asmOperand).ifBuiltin = true;
        });
    }

    @Override
    public void visit(IRBasicBlock irBasicBlock) {
        irBasicBlock.instList.forEach(inst -> inst.accept(this));
        irBasicBlock.terminator.accept(this);
    }

    @Override
    public void visit(IRFunction function) {
        ASMFunction asmFunction = (ASMFunction) function.asmOperand;
        curFunc = asmFunction;

        ASMVirtualReg.virtualRegNum = 0;

        //create all ASMBlock or things will go wrong when meet br
        ASMBlock asmEntryBlock = new ASMBlock(function.entryBlock.blockName);
        function.entryBlock.asmOperand = asmEntryBlock;
        asmFunction.addBlock(asmEntryBlock);

        ASMBlock asmExitBlock = new ASMBlock(function.exitBlock.blockName);
        function.exitBlock.asmOperand = asmExitBlock;
        asmFunction.addBlock(asmExitBlock);

        function.blockList.forEach(block -> {
            ASMBlock asmBlock = new ASMBlock(block.blockName);
            block.asmOperand = asmBlock;
            asmFunction.addBlock(asmBlock);
        });


        curBlock = asmEntryBlock;

        //分配栈空间
        ASMBinaryInst setSpaceInst = new ASMBinaryInst("addi", getPhysicalReg("sp"), getPhysicalReg("sp"), null, new ASMStackOffset(0, ASMStackOffset.StackOffsetType.lowerSp));
//        asmFunction.decreaseStackPtrInst = setSpaceInst;
        curBlock.addInst(setSpaceInst);
        //save ra
        ASMVirtualReg raSaveReg = new ASMVirtualReg();
        ASMMvInst saveRaInst = new ASMMvInst(raSaveReg, getPhysicalReg("ra"));
        curBlock.addInst(saveRaInst);
        //save callee-save registers
//        var calleeSaveRegList = new ArrayList<ASMReg>();
//        ASMPhysicalASMReg.calleeSavedRegList.forEach(calleeSaveReg -> {
//            ASMVirtualReg vCalleeSaveReg = new ASMVirtualReg();
//            calleeSaveRegList.add(vCalleeSaveReg);
//            ASMMvInst saveCalleeSaveInst = new ASMMvInst(vCalleeSaveReg, calleeSaveReg);
//            curBlock.addInst(saveCalleeSaveInst);
//        });
        //set arguments
        for (int i = 0; i < function.paraList.size(); i++) {
            IRValue arg = function.paraList.get(i);
            if (i < 8) {
                arg.asmOperand = getPhysicalReg("a" + i);
            } else {
                ASMVirtualReg virtualReg = new ASMVirtualReg(4);
                arg.asmOperand = virtualReg;
                asmFunction.paraList.add(virtualReg);
                ASMLoadInst loadInst = new ASMLoadInst(4, virtualReg, getPhysicalReg("sp"), new ASMStackOffset(i - 8, ASMStackOffset.StackOffsetType.getArg));
                curBlock.addInst(loadInst);
            }
        }

        function.entryBlock.accept(this);
        function.blockList.forEach(block -> {
            curBlock = (ASMBlock) block.asmOperand;
            block.accept(this);
        });

        curBlock = asmExitBlock;
        function.exitBlock.accept(this);
        //restore callee-save registers
//        for (int i = 0; i < ASMPhysicalASMReg.calleeSavedRegList.size(); i++) {
//            ASMMvInst restoreCalleeSaveInst = new ASMMvInst(ASMPhysicalASMReg.calleeSavedRegList.get(i), calleeSaveRegList.get(i));
//            curBlock.addInst(restoreCalleeSaveInst);
//        }
        //restore ra
        ASMMvInst restoreRaInst = new ASMMvInst(getPhysicalReg("ra"), raSaveReg);
        curBlock.addInst(restoreRaInst);

        //restore stack
        ASMBinaryInst restoreStackInst = new ASMBinaryInst("addi", getPhysicalReg("sp"), getPhysicalReg("sp"), null,
                new ASMStackOffset(0, ASMStackOffset.StackOffsetType.raiseSp));
        //by using StackOffset, this part is not necessary
        //        asmFunction.increaseStackPtrInst = restoreStackInst;

        curBlock.addInst(restoreStackInst);

        ASMRetInst retInst = new ASMRetInst();
        curBlock.addInst(retInst);
    }

    @Override
    public void visit(IRAllocaInst irInst) {
        irInst.asmOperand = new ASMStackOffset(curFunc.allocaCnt, ASMStackOffset.StackOffsetType.alloca);
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
        //debug
        if (irCallInst.calledFunc() == null || irCallInst.calledFunc().asmOperand == null) {
            int A = 0;
        }

        for (int i = 0; i < irCallInst.operandSize() - 1; ++i) {
            IRValue arg = irCallInst.args(i);
            if (i < 8) {
                ASMMvInst mvInst = new ASMMvInst(getPhysicalReg("a" + i), getReg(arg));
                curBlock.addInst(mvInst);
            } else {
                curFunc.spilledArgCnt = Math.max(curFunc.spilledArgCnt, i - 8);
                ASMStoreInst storeInst = new ASMStoreInst(4, getReg(arg), getPhysicalReg("sp"), new ASMStackOffset(i - 8, ASMStackOffset.StackOffsetType.putArg));
                curBlock.addInst(storeInst);
            }
        }

        ASMCallInst callInst = new ASMCallInst((ASMFunction) (irCallInst.calledFunc().asmOperand));
        curBlock.addInst(callInst);

        if (!(irCallInst.calledFunc().returnType instanceof VoidType)) {
            ASMMvInst mvInst = new ASMMvInst(getReg(irCallInst), getPhysicalReg("a0"));
            curBlock.addInst(mvInst);
        }
    }

    @Override
    public void visit(IRGEPInst irgepInst) {
        IRValue headPointer = irgepInst.headPointer();
        BasicType pointedType = ((PointerType) irgepInst.valueType).baseType;
        if (pointedType instanceof IntegerType && ((IntegerType) pointedType).bitWidth == 8) {//string
            ASMReg tmpReg = new ASMVirtualReg();
            ASMGlobalString str = (ASMGlobalString) headPointer.asmOperand;
            ASMLuiInst asmLuiInst = new ASMLuiInst(tmpReg, new ASMGlobalAddr(ASMGlobalAddr.HiLoType.hi, str));
            curBlock.addInst(asmLuiInst);
            ASMBinaryInst asmBinaryInst = new ASMBinaryInst("addi", tmpReg, tmpReg, null, new ASMGlobalAddr(ASMGlobalAddr.HiLoType.lo, str));
            curBlock.addInst(asmBinaryInst);
            irgepInst.asmOperand = tmpReg;
        } else {//assert bool, int, pointer all take 4 bytes
            int byteSize = pointedType.size();
            if (byteSize != 4) {
                throw new RuntimeException("IRGEPInst: wrong byteSize!! Should be 4");
            }
            ASMReg tmpReg = new ASMVirtualReg();
            ASMBinaryInst asmBinaryInst1 = new ASMBinaryInst("slli", tmpReg, getReg(irgepInst.getIndex()), null, new ASMImm(2));
            curBlock.addInst(asmBinaryInst1);
            ASMBinaryInst asmBinaryInst2 = new ASMBinaryInst("add", getReg(irgepInst), getReg(headPointer), tmpReg, null);
            curBlock.addInst(asmBinaryInst2);
        }
    }

    @Override
    public void visit(IRIcmpInst irIcmpInst) {
        switch (irIcmpInst.type) {
            case "sgt" -> {
                ASMBinaryInst binaryInst = new ASMBinaryInst("slt", getReg(irIcmpInst), getReg(irIcmpInst.rhs()), getReg(irIcmpInst.lhs()), null);
                curBlock.addInst(binaryInst);
            }
            case "slt" -> {
                ASMBinaryInst binaryInst = new ASMBinaryInst("slt", getReg(irIcmpInst), getReg(irIcmpInst.lhs()), getReg(irIcmpInst.rhs()), null);
                curBlock.addInst(binaryInst);
            }
            case "sge" -> { //a >= b <--> !(a < b)
                ASMVirtualReg tmpReg = new ASMVirtualReg();
                ASMBinaryInst binaryInst1 = new ASMBinaryInst("slt", tmpReg, getReg(irIcmpInst.lhs()), getReg(irIcmpInst.rhs()), null);
                curBlock.addInst(binaryInst1);
                ASMBinaryInst binaryInst2 = new ASMBinaryInst("xori", getReg(irIcmpInst), tmpReg, null, new ASMImm(1));
                curBlock.addInst(binaryInst2);
            }
            case "sle" -> { //a <= b <--> !(a > b)
                ASMVirtualReg tmpReg = new ASMVirtualReg();
                ASMBinaryInst binaryInst1 = new ASMBinaryInst("slt", tmpReg, getReg(irIcmpInst.rhs()), getReg(irIcmpInst.lhs()), null);
                curBlock.addInst(binaryInst1);
                ASMBinaryInst binaryInst2 = new ASMBinaryInst("xori", getReg(irIcmpInst), tmpReg, null, new ASMImm(1));
                curBlock.addInst(binaryInst2);
            }
            case "eq" -> {
                ASMVirtualReg tmpReg = new ASMVirtualReg();
                ASMBinaryInst binaryInst = new ASMBinaryInst("sub", tmpReg, getReg(irIcmpInst.lhs()), getReg(irIcmpInst.rhs()), null);
                curBlock.addInst(binaryInst);
                //can be merge into binaryInst
                ASMUnaryInst unaryInst = new ASMUnaryInst("seqz", getReg(irIcmpInst), tmpReg);
                curBlock.addInst(unaryInst);
            }
            case "ne" -> {
                ASMVirtualReg tmpReg = new ASMVirtualReg();
                ASMBinaryInst binaryInst = new ASMBinaryInst("sub", tmpReg, getReg(irIcmpInst.lhs()), getReg(irIcmpInst.rhs()), null);
                curBlock.addInst(binaryInst);
                ASMUnaryInst unaryInst = new ASMUnaryInst("snez", getReg(irIcmpInst), tmpReg);
                curBlock.addInst(unaryInst);
            }

        }
    }

    @Override
    public void visit(IRLoadInst irLoadInst) {
        int loadSize = irLoadInst.loadType.size();
        ASMReg destReg = getReg(irLoadInst);
        IRValue fromAddr = irLoadInst.loadFromAddr;
        if (fromAddr instanceof IRConstant) {
            //deal with globalVar and String
            ASMVirtualReg tmpReg = new ASMVirtualReg();
            ASMLuiInst asmLuiInst = new ASMLuiInst(tmpReg, new ASMGlobalAddr(ASMGlobalAddr.HiLoType.hi, (ASMGlobal) fromAddr.asmOperand));
            curBlock.addInst(asmLuiInst);
            ASMLoadInst asmLoadInst = new ASMLoadInst(loadSize, destReg, tmpReg, new ASMGlobalAddr(ASMGlobalAddr.HiLoType.lo, (ASMGlobal) fromAddr.asmOperand));
            //load 的 imm 只有 12 位
            curBlock.addInst(asmLoadInst);
        } else {
            if (fromAddr.asmOperand instanceof ASMStackOffset offset) {
                ASMLoadInst asmLoadInst = new ASMLoadInst(loadSize, destReg, getPhysicalReg("sp"), offset);
                curBlock.addInst(asmLoadInst);
            } else {
                if (fromAddr.asmOperand instanceof ASMReg) {
                    ASMLoadInst asmLoadInst = new ASMLoadInst(loadSize, destReg, (ASMReg) (fromAddr.asmOperand), new ASMImm(0));
                    curBlock.addInst(asmLoadInst);
                } else {
                    throw new RuntimeException("LoadInst: wrong fromAddr.ASMOperand type");
                }

            }
        }
    }

    @Override
    public void visit(IRRetInst irRetInst) {
        if (irRetInst.hasRetAddr()) {
            ASMMvInst mvInst = new ASMMvInst(getPhysicalReg("a0"), getReg(irRetInst.getRetAddr()));
            curBlock.addInst(mvInst);
        }
    }

    @Override
    public void visit(IRStoreInst irStoreInst) {
        int storeSize = irStoreInst.valueType.size();
        ASMReg fromReg = getReg(irStoreInst.storeValue);
        IRValue destAddr = irStoreInst.storePtr;
        if (destAddr instanceof IRConstant) {
            //deal with globalVar and String
            ASMVirtualReg tmpReg = new ASMVirtualReg();
            ASMLuiInst asmLuiInst = new ASMLuiInst(tmpReg, new ASMGlobalAddr(ASMGlobalAddr.HiLoType.hi, (ASMGlobal) destAddr.asmOperand));
            curBlock.addInst(asmLuiInst);
            ASMStoreInst asmStoreInst = new ASMStoreInst(storeSize, fromReg, tmpReg, new ASMGlobalAddr(ASMGlobalAddr.HiLoType.lo, (ASMGlobal) destAddr.asmOperand));
            //load 的 imm 只有 12 位
            curBlock.addInst(asmStoreInst);
        } else {
            if (destAddr.asmOperand instanceof ASMStackOffset offset) {
                ASMStoreInst asmStoreInst = new ASMStoreInst(storeSize, fromReg, getPhysicalReg("sp"), offset);
                curBlock.addInst(asmStoreInst);
            } else {
                if (destAddr.asmOperand instanceof ASMReg) {
                    ASMStoreInst asmStoreInst = new ASMStoreInst(storeSize, fromReg, (ASMReg) destAddr.asmOperand, new ASMImm(0));
                    curBlock.addInst(asmStoreInst);
                } else {
                    throw new RuntimeException("StoreInst: wrong destAddr.ASMOperand type");
                }
            }
        }
    }

    @Override
    public void visit(IRTruncInst irTruncInst) {//only for bool
        ASMVirtualReg virtualReg = new ASMVirtualReg(irTruncInst.targetType().size());
        ASMBinaryInst binaryInst = new ASMBinaryInst("andi", virtualReg, getReg(irTruncInst.fromValue()), null, new ASMImm(1));
        curBlock.addInst(binaryInst);
        ASMMvInst mvInst = new ASMMvInst(getReg(irTruncInst), virtualReg);
        curBlock.addInst(mvInst);
    }

    @Override
    public void visit(IRZextInst irZextInst) {//only for bool
        ASMVirtualReg virtualReg = new ASMVirtualReg(irZextInst.targetType().size());
        ASMBinaryInst binaryInst = new ASMBinaryInst("andi", virtualReg, getReg(irZextInst.fromValue()), null, new ASMImm(1));
        curBlock.addInst(binaryInst);
        ASMMvInst mvInst = new ASMMvInst(getReg(irZextInst), virtualReg);
        curBlock.addInst(mvInst);
    }
}
