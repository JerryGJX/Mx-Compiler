package IR.Value.User;

import IR.Type.BasicType;
import IR.Value.IRUse;
import IR.Value.IRValue;

import java.util.ArrayList;

public abstract class IRUser extends IRValue {
    public ArrayList<IRUse> operandList = new ArrayList<>();

    public IRUser(BasicType _basicType) {
        super(_basicType);
    }

    public IRUser(String _valueName, BasicType _basicType) {
        super(_valueName, _basicType);
    }

    public void addUse(IRValue _val){
        IRUse _IR_use = new IRUse(this,_val);
        this.operandList.add(_IR_use);
        _val.IRUseList.add(_IR_use);
    }

}
