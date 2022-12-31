package IR;

import IR.Value.IRBasicBlock;
import IR.Value.User.Constant.*;
import IR.Value.User.Constant.GlobalValue.GlobalVariable;
import IR.Value.User.Constant.GlobalValue.IRFunction;
import IR.Value.User.Instruction.*;

public interface IRVisitor {
//    void visit (IRIntConstant _intConstant);
//    void visit (IRNullptrConstant _nullptrConstant);
//    void visit (IRZeroInitConstant _zeroInitConstant);
//    void visit (IRStrConstant _strConstant);

//    void visit(IRInstruction IRInstruction);

    //    void visit(IRBoolConst IRBoolConst);
    void visit(IRModule irModule);

    void visit(IRBasicBlock IRBasicBlock);

    void visit(IRFunction function);

//    void visit(GlobalVariable globalVariable);

    void visit(IRAllocaInst irAllocaInst);

    void visit(IRBinaryInst irBinaryInst);

    void visit(IRBitCastInst irBitCastInst);

    void visit(IRBrInst irBrInst);

    void visit(IRCallInst irCallInst);

    void visit(IRGEPInst irgepInst);

    void visit(IRIcmpInst irIcmpInst);

    void visit(IRLoadInst irLoadInst);

    void visit(IRRetInst irRetInst);

    void visit(IRStoreInst irStoreInst);

    void visit(IRTruncInst irTruncInst);

    void visit(IRZextInst irZextInst);


}
