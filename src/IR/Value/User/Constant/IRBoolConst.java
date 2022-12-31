package IR.Value.User.Constant;

import IR.IRVisitor;
import IR.Type.BoolType;

public class IRBoolConst extends IRConstant {
    public boolean constBoolData;

    public IRBoolConst(boolean _constBoolData) {
        super(new BoolType());
        constBoolData = _constBoolData;
    }

//    @Override
    public String getIdentifier() {
        return constBoolData ? "true" : "false";
    }

    public String toString() {
        return constBoolData ? "true" : "false";
    }

    @Override
    public boolean equals(IRConstant _IR_constant) {
        if (_IR_constant == null) {
            return false;
        } else {
            if (_IR_constant instanceof IRBoolConst) {
                return this.constBoolData == ((IRBoolConst) _IR_constant).constBoolData;
            } else return false;
        }
    }


//    public void accept(IRVisitor _visitor) {
//        _visitor.visit(this);
//    }
}
