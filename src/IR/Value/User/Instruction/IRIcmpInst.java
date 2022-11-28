package IR.Value.User.Instruction;

import IR.Type.BasicType;
import IR.Type.BoolType;
import IR.Value.IRBasicBlock;
import IR.Value.IRValue;

public class IRIcmpInst extends IRInstruction {
    //<result> = icmp <cond> <ty> <op1>, <op2>   ; yields i1 or <N x i1>:result
    public String op;

    public IRIcmpInst(String _valueName, String _op, IRValue _lhs, IRValue _rhs, IRBasicBlock _parentBlock) {
        super(_valueName, new BoolType(), _parentBlock);
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
        return this.getIdentifier() + " = " + LLVM_ICMP_INST + " " + this.op + " " + this.lhs().valueType
                + " " + this.lhs().getIdentifier() + ", " + this.rhs().getIdentifier();
    }
}
