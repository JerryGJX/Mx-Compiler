package IR;

import IR.Value.IRBasicBlock;
import IR.Value.User.Constant.*;
import IR.Value.User.Constant.GlobalValue.IRFunction;
import IR.Value.User.Constant.GlobalValue.IRGlobalVariable;
import IR.Value.User.Instruction.IRInstruction;

public interface IRVisitor {
    void visit (IRIntConstant _intConstant);
    void visit (IRNullptrConstant _nullptrConstant);
    void visit (IRZeroInitConstant _zeroInitConstant);
    void visit (IRStrConstant _strConstant);

    void visit(IRInstruction IRInstruction);

    void visit(IRBoolConst IRBoolConst);

    void visit(IRGlobalVariable IRGlobalVariable);

    void visit(IRBasicBlock IRBasicBlock);

    void visit(IRFunction function);
}
