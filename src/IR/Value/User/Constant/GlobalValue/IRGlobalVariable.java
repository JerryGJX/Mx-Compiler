package IR.Value.User.Constant.GlobalValue;

import IR.IRVisitor;
import IR.Type.BasicType;
import IR.Type.PointerType;
import IR.Value.IRValue;
import IR.Value.User.Constant.IRConstant;

public class IRGlobalVariable extends IRConstant {
    public IRValue initIRValue;

    public IRGlobalVariable(String _constName, BasicType _basicType) {
        super(_constName, new PointerType(_basicType));
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
            if (_IR_constant instanceof IRGlobalVariable) {
                return this.valueName.equals(_IR_constant.valueName);
            } else return false;
        }
    }

    @Override
    public void accept(IRVisitor _visitor) {
        _visitor.visit(this);
    }
}
