package IR.Value.User.Instruction;


import IR.Type.VoidType;
import IR.Value.IRBasicBlock;

public class IRBrInst extends IRInstruction {
    public IRBasicBlock targetBlock;

    public IRBrInst(IRBasicBlock _targetBlock, IRBasicBlock _parentBlock) {
        super(LLVM_BR_INST,new VoidType(),_parentBlock);
        this.targetBlock = _targetBlock;
    }

    @Override
    public String printInst() {
        return "br label %" + targetBlock.GetBlockName();
    }
}
