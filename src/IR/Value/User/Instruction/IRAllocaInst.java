package IR.Value.User.Instruction;

import IR.Type.BasicType;
import IR.Type.PointerType;
import IR.Value.IRBasicBlock;

public class IRAllocaInst extends IRInstruction {
    public String allocaName;
    public BasicType allocaType;

    public IRAllocaInst(String _allocaName, BasicType _allocaType, IRBasicBlock _parentBlock) {
        super(_allocaName,new PointerType(_allocaType),_parentBlock);
        this.allocaName = _allocaName;
        this.allocaType = _allocaType;
    }

    @Override
    public String printInst() {
        return this.getIdentifier() + " = " + LLVM_ALLOCA_INST + " " +
                this.allocaType ;
        //+ ", align " + this.allocaType.size();
    }
}
