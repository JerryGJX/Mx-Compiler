package BackEnd.optimize;

import IR.IRModule;
import IR.IRVisitor;
import IR.Value.IRBasicBlock;
import IR.Value.User.Constant.GlobalValue.IRFunction;
import IR.Value.User.Instruction.*;

public class DeadInstEliminator implements IRVisitor {

    @Override
    public void visit(IRModule irModule) {
        irModule.IRFunctionMap.forEach((name, function) -> function.accept(this));
    }

    @Override
    public void visit(IRBasicBlock IRBasicBlock) {
        IRBasicBlock.instList.forEach(instruction ->{
            if(instruction.IRUseList.isEmpty()){
                IRBasicBlock.instList.remove(instruction);
            }
        });
    }

    @Override
    public void visit(IRFunction function) {
        function.blockList.forEach(basicBlock -> basicBlock.accept(this));
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
