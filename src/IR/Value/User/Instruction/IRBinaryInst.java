package IR.Value.User.Instruction;

import IR.Type.BasicType;
import IR.Value.IRBasicBlock;
import IR.Value.IRValue;

public class IRBinaryInst extends IRInstruction {
    public String op;

    public IRBinaryInst(String _op, BasicType _retType, IRValue _lhs, IRValue _rhs, IRBasicBlock _parentBlock) {
        super(_op, _retType, _parentBlock);
        this.op = _op;
        this.addOperand(_lhs);
        this.addOperand(_rhs);
    }

    public IRValue lhs() {
        return this.getOperand(0);
    }

    public IRValue rhs() {
        return this.getOperand(1);
    }

    @Override
    public String printInst() {
        return this.getIdentifier() + " = " + this.op + " " + this.valueType
                + " " + this.lhs().getIdentifier() + ", " + this.rhs().getIdentifier();
    }

}
