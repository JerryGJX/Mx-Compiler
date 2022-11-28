package IR.Value.User.Constant.GlobalValue;

import IR.IRVisitor;
import IR.Type.BasicType;
import IR.Type.PointerType;
import IR.Value.IRValue;
import IR.Value.User.Constant.IRConstant;

public class GlobalVariable extends IRConstant {
    public IRValue initIRValue;

    public int dimSize = 0;
    public GlobalVariable(String _varName, BasicType _basicType) {
        super(_varName,_basicType);
    }
    public String toString() {
        return GLOBAL_PREFIX + this.valueName;
    }
    @Override
    public String getIdentifier() {
        return GLOBAL_PREFIX + this.valueName;
    }
    @Override
    public boolean equals(IRConstant _IR_constant) {
        if (_IR_constant == null) {
            return false;
        } else {
            if (_IR_constant instanceof GlobalVariable) {
                return this.valueName.equals(((GlobalVariable) _IR_constant).valueName);
            } else return false;
        }
    }

    public void accept(IRVisitor _visitor) {
        _visitor.visit(this);
    }
}
