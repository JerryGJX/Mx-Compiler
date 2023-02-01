package IR.Value.User.Instruction;

import IR.IRVisitor;
import IR.Type.BasicType;
import IR.Type.VoidType;
import IR.Value.IRBasicBlock;
import IR.Value.IRValue;

public class IRRetInst extends IRInstruction {
    BasicType retType;
    IRValue retAddr;

    public IRRetInst(BasicType _retType, IRValue _retAddr, IRBasicBlock _parentBlock) {
        super(LLVM_RET_INST, new VoidType(), _parentBlock);
        this.retType = _retType;
        this.retAddr = _retAddr;
        this.addOperand(_retAddr);
    }

    public boolean hasRetAddr() {
        return retAddr != null;
    }

    public IRValue getRetAddr() {
        return retAddr;
    }

    @Override
    public String printInst() {
        return LLVM_RET_INST + " " + retType + ((retAddr == null) ? "" : " " + retAddr.getIdentifier());
    }

    @Override
    public void accept(IRVisitor _visitor) {
        _visitor.visit(this);
    }
}
