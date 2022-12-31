package BackEnd;

import ASM.ASMBlock;
import ASM.ASMFunction;
import ASM.ASMModule;
import IR.IRModule;
import IR.IRVisitor;
import IR.Value.IRBasicBlock;
import IR.Value.User.Constant.GlobalValue.IRFunction;
import IR.Value.User.Instruction.*;

public class ASMBuilder implements IRVisitor {
    public ASMModule asmModule;
    public ASMFunction curFunc;
    public ASMBlock curBlock;







    @Override
    public void visit(IRModule irModule) {

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
