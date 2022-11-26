package IR.Value.User.Instruction;

import IR.IRVisitor;
import IR.Type.BasicType;
import IR.Value.IRBasicBlock;
import IR.Value.User.IRUser;

public abstract class IRInstruction extends IRUser {
    public IRBasicBlock parentBlock;

    public IRInstruction(String _valueName,BasicType _valueType){
        super(_valueName,_valueType);
    };

//    public IRInstruction(String _insComment, BasicType _basicType) {
//        super(_basicType);
//        this.insComment = _insComment;
//    }

//    public String GetInstComment() {
//        return insComment;
//    }

    public abstract String toString();


    public void accept(IRVisitor _visitor) {
        _visitor.visit(this);
    }
}
