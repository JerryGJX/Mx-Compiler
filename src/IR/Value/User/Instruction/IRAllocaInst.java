package IR.Value.User.Instruction;

import IR.Type.BasicType;
import IR.Type.PointerType;
import IR.Value.IRBasicBlock;

public class IRAllocaInst extends IRInstruction {
    public String allocaName;
    public BasicType allocaType;

    public IRAllocaInst(String _allocaName, BasicType _allocaType, IRBasicBlock _parentBlock) {
        super(_allocaName,new PointerType(_allocaType,1));
        this.allocaName = _allocaName;
        this.allocaType = _allocaType;
        this.parentBlock = _parentBlock;
    }

    @Override
    public String toString() {
        return allocaName + " = " + LLVM_ALLOCA_INST + " " +
                this.allocaType + ", align " + this.allocaType.size();
    }


}
