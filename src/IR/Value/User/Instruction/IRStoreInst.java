package IR.Value.User.Instruction;

import IR.Type.BasicType;
import IR.Type.PointerType;
import IR.Type.VoidType;
import IR.Value.IRBasicBlock;
import IR.Value.IRValue;
import IR.Value.User.Constant.IRNullptrConstant;

public class IRStoreInst extends IRInstruction {
    IRValue storeValue;
    IRValue storePtr;

    public IRStoreInst(IRValue _value, IRValue _pointer, IRBasicBlock _parentBlock) {
        super(LLVM_STORE_INST, _value.valueType, _parentBlock);
        this.storeValue = _value;
        this.storePtr = _pointer;
    }

    @Override
    public String printInst() {
        if (this.storeValue instanceof IRNullptrConstant) {
            return LLVM_STORE_INST + " " + ((PointerType) this.storePtr.valueType).baseType + " " + this.storeValue.getIdentifier() + ", " + this.storePtr.valueType + " " + this.storePtr.getIdentifier() + ", align " + this.valueType.size();
        } else {
            return LLVM_STORE_INST + " " + this.storeValue.valueType + " " + this.storeValue.getIdentifier() + ", " + this.storePtr.valueType + " " + this.storePtr.getIdentifier() + ", align " + this.valueType.size();
        }
    }
}
