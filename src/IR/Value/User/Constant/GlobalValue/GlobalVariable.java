package IR.Value.User.Constant.GlobalValue;

import IR.IRVisitor;
import IR.Type.BasicType;
import IR.Type.PointerType;
import IR.Value.IRValue;
import IR.Value.User.Constant.IRConstant;

public class GlobalVariable extends IRConstant {
    public IRValue initIRValue;
    public String varName;

//    public boolean isGlobal = false;

    public GlobalVariable(BasicType _basicType, String _varName, IRValue _initIRValue) {
        super(_basicType);
        this.varName = _varName;
//        this.isGlobal = _isGlobal;
        this.initIRValue = _initIRValue;
    }


    public String toString() {
        return GLOBAL_PREFIX + this.varName;
    }


    @Override
    public String getIdentifier() {
        return GLOBAL_PREFIX + this.varName;
    }


    @Override
    public boolean equals(IRConstant _IR_constant) {
        if (_IR_constant == null) {
            return false;
        } else {
            if (_IR_constant instanceof GlobalVariable) {
                return this.varName.equals(((GlobalVariable) _IR_constant).varName);
            } else return false;
        }
    }


    public void accept(IRVisitor _visitor) {
        _visitor.visit(this);
    }
}
