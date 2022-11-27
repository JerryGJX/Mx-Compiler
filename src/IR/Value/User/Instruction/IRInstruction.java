package IR.Value.User.Instruction;

import IR.IRVisitor;
import IR.Type.BasicType;
import IR.Value.IRBasicBlock;
import IR.Value.IRValue;
import IR.Value.User.IRUser;

import java.util.ArrayList;

public abstract class IRInstruction extends IRUser {
    public IRBasicBlock parentBlock;
    public ArrayList<IRValue> OperandList = new ArrayList<>();

    public IRInstruction(String _valueName, BasicType _valueType){
        super(_valueName,_valueType);
    }
    public abstract String toString();
    public void accept(IRVisitor _visitor) {
        _visitor.visit(this);
    }
}
