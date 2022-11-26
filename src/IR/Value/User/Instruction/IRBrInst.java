package IR.Value.User.Instruction;


import IR.Type.VoidType;
import IR.Value.IRBasicBlock;

public class IRBrInst extends IRInstruction {
    public IRBasicBlock targetBlock;

    public IRBrInst(IRBasicBlock _targetBlock) {
        super(LLVM_BR_INST,new VoidType());
        this.targetBlock = _targetBlock;
    }

    @Override
    public String toString() {
        return "br label %" + targetBlock.GetBlockName();
    }
}
