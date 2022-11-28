package IR.Value.User;

import IR.Type.BasicType;
import IR.Value.IRUse;
import IR.Value.IRValue;

import java.util.ArrayList;

public abstract class IRUser extends IRValue {
    public ArrayList<IRValue> operandList = new ArrayList<>();

    public IRUser(BasicType _basicType) {
        super(_basicType);
    }

    public IRUser(String _valueName, BasicType _basicType) {
        super(_valueName, _basicType);
    }

    public void addOperand(IRValue _IRValue) {
        operandList.add(_IRValue);
        IRUse.addUse(this, _IRValue);
    }

    public IRValue getOperand(int _index) {
        return operandList.get(_index);
    }

    public void addUse(IRValue _val){
        IRUse.addUse(this, _val);
    }

}
