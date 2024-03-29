package IR.Value.User.Instruction;

import IR.IRVisitor;
import IR.Type.BasicType;
import IR.Type.BoolType;
import IR.Value.IRBasicBlock;
import IR.Value.IRValue;

public class IRIcmpInst extends IRInstruction {
    //<result> = icmp <cond> <ty> <op1>, <op2>
    public String type;

    public IRIcmpInst(String _valueName, String _type, IRValue _lhs, IRValue _rhs, IRBasicBlock _parentBlock) {
        super(_valueName, new BoolType(), _parentBlock);
        this.type = _type;
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
        return this.getIdentifier() + " = " + LLVM_ICMP_INST + " " + this.type + " " + this.lhs().valueType
                + " " + this.lhs().getIdentifier() + ", " + this.rhs().getIdentifier();
    }

    @Override
    public void accept(IRVisitor _visitor) {
        _visitor.visit(this);
    }
}
