package IR.Value.User.Constant;

import IR.IRVisitor;
import IR.Type.PointerType;

public class IRNullptrConstant extends IRConstant {
//    public NullConstant(BasicType _basicType) {
//        super(_basicType);
//    }

    public IRNullptrConstant(){
        super(new PointerType(null,0));
    }

    @Override
    public boolean equals(IRConstant _IR_constant) {
        if(_IR_constant ==null) {
            return false;
        }else return _IR_constant instanceof IRNullptrConstant;
    }

//    @Override
    public String getIdentifier() {
        return NULLPTR_IDENTIFIER;
    }


    public void accept(IRVisitor _visitor) {
        _visitor.visit(this);
    }

}

