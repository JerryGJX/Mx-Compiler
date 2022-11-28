package IR.Value.User.Instruction;


import IR.Type.BasicType;
import IR.Value.IRBasicBlock;
import IR.Value.IRValue;

public class IRBitCastInst extends IRInstruction {
    public IRBitCastInst(String _valueName, IRValue _fromValue, BasicType _retType, IRBasicBlock _parentBlock) {
        super(_valueName, _retType, _parentBlock);
        this.addOperand(_fromValue);
    }

    public IRValue fromValue() {
        return this.getOperand(0);
    }

    @Override
    public String printInst() {
        return this.getIdentifier() + " = " + LLVM_BITCAST_INST + " " + this.fromValue().valueType
                + " " + this.fromValue().getIdentifier() + " to " + this.valueType;
    }
}

