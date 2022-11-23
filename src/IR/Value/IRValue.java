package IR.Value;

import IR.IRVisitor;
import IR.Type.BasicType;

import java.util.ArrayList;

public abstract class IRValue implements IRDefine {
    public String valueName;
    public BasicType valueType;
    public ArrayList<IRUse> IRUseList = new ArrayList<>();

    public IRValue(BasicType _basicType){
        valueType = _basicType;
        valueName = "";
    }

    public IRValue(String _valueName, BasicType _basicType){
        valueName = _valueName;
        valueType = _basicType;
        IRUseList = new ArrayList<>();
    }

    public abstract void accept (IRVisitor _visitor);

}
