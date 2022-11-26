package IR.Value.User.Instruction;

import IR.Type.BasicType;
import IR.Type.VoidType;
import IR.Value.IRValue;

public class IRRetInst extends IRInstruction {
    BasicType retType;
    IRValue retAddr;
    public IRRetInst(BasicType _retType, IRValue _retAddr) {
        super(LLVM_RET_INST,new VoidType());
        this.retType = _retType;
        this.retAddr = _retAddr;
    }

    @Override
    public String toString() {
        return LLVM_RET_INST + " " + retType + " " +  retAddr.getIdentifier();
    }
}
