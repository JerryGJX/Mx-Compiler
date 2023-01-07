package IR.Value;

import ASM.Operand.ASMOperand;
import IR.IRVisitor;
import IR.Type.BasicType;
import IR.Value.User.IRUser;

import java.util.ArrayList;
import java.util.HashMap;

public class IRValue implements IRDefine {

    public String valueName;
    public BasicType valueType;

    public ASMOperand asmOperand = null;

//    public String id = "";
//    public String printId = "";
    public ArrayList<IRUse> IRUseList = new ArrayList<>();

    public IRValue(BasicType _basicType) {
        valueType = _basicType;
    }

    public IRValue(String _rawName, BasicType _basicType) {
        valueName = _rawName;
        valueType = _basicType;
    }



    public String getIdentifier(){
      return LOCAL_PREFIX + valueName;
    };

}
