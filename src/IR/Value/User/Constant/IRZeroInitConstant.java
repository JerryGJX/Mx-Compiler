package IR.Value.User.Constant;

import IR.IRVisitor;
import IR.Type.BasicType;

public class IRZeroInitConstant extends IRConstant {
    public IRZeroInitConstant(BasicType _basicType) {
        super(_basicType);
    }

//    @Override
    public String getIdentifier() {
        return null;
    }

    @Override
    public boolean equals(IRConstant _IR_constant) {
        return false;
    }


    public void accept(IRVisitor _visitor) {
        _visitor.visit(this);
    }
}

