package IR.Value.User.Constant.GlobalValue;

import ASM.Operand.ASMGlobalValue;
import IR.IRVisitor;
import IR.Type.BasicType;
import IR.Type.PointerType;
import IR.Value.IRValue;
import IR.Value.User.Constant.IRConstant;
import IR.Value.User.Constant.IRZeroInitConstant;

public class GlobalVariable extends IRConstant {
    public IRValue initIRValue = null;

    public int dimSize = 0;
    public GlobalVariable(String _varName, BasicType _basicType) {
        super(_varName,new PointerType(_basicType));
        initIRValue = new IRZeroInitConstant(_basicType);
    }
    public String toString() {
        return GLOBAL_PREFIX + this.valueName;
    }

    public BasicType pointedType(){
        return ((PointerType) this.valueType).baseType;
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

//    public void accept(IRVisitor _visitor) {
//        _visitor.visit(this);
//    }
}
