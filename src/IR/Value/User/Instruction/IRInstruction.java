package IR.Value.User.Instruction;

import IR.IRVisitor;
import IR.Type.BasicType;
import IR.Value.IRBasicBlock;
import IR.Value.IRValue;
import IR.Value.User.IRUser;

import java.util.ArrayList;

public abstract class IRInstruction extends IRUser {
    public IRBasicBlock parentBlock;

    public IRInstruction(String _valueName, BasicType _valueType, IRBasicBlock _parentBlock) {
        super(_valueName,_valueType);
        this.parentBlock = _parentBlock;
    }

    public abstract String printInst();
    public void accept(IRVisitor _visitor) {
        _visitor.visit(this);
    }
}
