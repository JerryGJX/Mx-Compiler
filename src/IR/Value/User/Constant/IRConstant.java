package IR.Value.User.Constant;

import IR.Type.BasicType;
import IR.Type.PointerType;
import IR.Value.User.IRUser;

public abstract class IRConstant extends IRUser {

    public IRConstant(BasicType _basicType) {
        super(_basicType);
    }
//    public IRConstant(String _constName, BasicType _basicType) {
//        super(_constName, _basicType);
//    }

    public abstract String getIdentifier();

    public abstract boolean equals(IRConstant _IR_constant);
}
