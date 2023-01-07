package BackEnd;

import ASM.ASMBlock;
import ASM.ASMFunction;
import ASM.ASMModule;
import ASM.Operand.ASMGlobalString;
import ASM.Operand.ASMGlobalValue;
import ASM.Operand.ASMVirtualReg;
import IR.IRModule;
import IR.IRVisitor;
import IR.Value.IRBasicBlock;
import IR.Value.IRValue;
import IR.Value.User.Constant.GlobalValue.IRFunction;
import IR.Value.User.Instruction.*;

public class ASMBuilder implements IRVisitor {
    public ASMModule asmModule = new ASMModule();
    public ASMFunction curFunc = null;
    public ASMBlock curBlock = null;


    public ASMBuilder() {
    }


    @Override
    public void visit(IRModule irModule) {
        irModule.IRGlobalVariableMap.forEach((name, globalVariable) -> {
            ASMGlobalValue globalValue = new ASMGlobalValue(name, 0, 4);
            globalVariable.asmOperand = globalValue;
            asmModule.globalValueList.add(globalValue);
        });

        irModule.IRStrConstantMap.forEach((name, strConstant) -> {
            ASMGlobalString globalString = new ASMGlobalString(name, strConstant.strConstData);
            strConstant.asmOperand = globalString;
            asmModule.globalStringList.add(globalString);
        });

        irModule.IRFunctionMap.forEach((name, function) -> {
            ASMFunction asmFunction = new ASMFunction(name);
            function.asmOperand = asmFunction;

            for (int i = 0; i < function.operandSize(); i++) {
                IRValue arg = function.getOperand(i);
                ASMVirtualReg virtualReg = new ASMVirtualReg(arg.valueType.size());
                arg.asmOperand = virtualReg;
                asmFunction.paraList.add(virtualReg);


            }

            function.accept(this);
        });


    }

    @Override
    public void visit(IRBasicBlock IRBasicBlock) {

    }

    @Override
    public void visit(IRFunction function) {

    }

    @Override
    public void visit(IRAllocaInst irAllocaInst) {

    }

    @Override
    public void visit(IRBinaryInst irBinaryInst) {

    }

    @Override
    public void visit(IRBitCastInst irBitCastInst) {

    }

    @Override
    public void visit(IRBrInst irBrInst) {

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

    }

    @Override
    public void visit(IRZextInst irZextInst) {

    }
}
