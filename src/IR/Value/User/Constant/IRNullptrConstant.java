package IR.Value.User.Constant;

import IR.IRVisitor;
import IR.Type.PointerType;

public class IRNullptrConstant extends IRConstant {
//    public NullConstant(BasicType _basicType) {
//        super(_basicType);
//    }

    public IRNullptrConstant(){
        super(nullType);
    }

    @Override
    public boolean equals(IRConstant _IR_constant) {
        if(_IR_constant ==null) {
            return false;
        }else return _IR_constant instanceof IRNullptrConstant;
    }

    public String getIdentifier() {
        return NULLPTR_IDENTIFIER;
    }
    @Override
    public String toString() {
        return NULLPTR_IDENTIFIER;
    }


//    public void accept(IRVisitor _visitor) {
//        _visitor.visit(this);
//    }

}

