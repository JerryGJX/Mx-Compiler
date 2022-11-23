package IR.Value.User.Instruction;

import IR.Type.BasicType;

public class IRAllocaInst extends IRInstruction {

    public IRAllocaInst(BasicType _basicType) {
        super(_basicType);
    }

    public IRAllocaInst(String _valueName, BasicType _basicType) {
        super(_valueName, _basicType);
    }

    public String toString(){
        return "";
    }
}
