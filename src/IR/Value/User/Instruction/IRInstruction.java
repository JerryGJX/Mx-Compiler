package IR.Value.User.Instruction;

import IR.IRVisitor;
import IR.Type.BasicType;
import IR.Value.IRBasicBlock;
import IR.Value.User.IRUser;

public abstract class IRInstruction extends IRUser {
    IRBasicBlock parentBlock;

    public IRInstruction(BasicType _basicType) {
        super(_basicType);
    }

    public IRInstruction(String _valueName, BasicType _basicType) {
        super(_valueName, _basicType);
    }

    @Override
    public void accept(IRVisitor _visitor) {
        _visitor.visit(this);
    }
}
