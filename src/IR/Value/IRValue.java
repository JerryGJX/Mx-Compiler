package IR.Value;

import IR.IRVisitor;
import IR.Type.BasicType;

import java.util.ArrayList;

public class IRValue implements IRDefine {
    public String valueName;
    public BasicType valueType;

//    public String id = "";
//    public String printId = "";
    public ArrayList<IRUse> IRUseList = new ArrayList<>();

    public IRValue(BasicType _basicType) {
        valueType = _basicType;
    }

    public IRValue(String _valueName, BasicType _basicType) {
        valueName = _valueName;
        valueType = _basicType;
    }

    public String getIdentifier(){
      return valueName;
    };

}
