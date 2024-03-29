package IR.Value.User.Instruction;

import IR.IRVisitor;
import IR.Type.BasicType;
import IR.Type.PointerType;
import IR.Type.VoidType;
import IR.Value.IRBasicBlock;
import IR.Value.IRValue;
import IR.Value.User.Constant.IRNullptrConstant;

public class IRStoreInst extends IRInstruction {
    public IRValue storeValue;
    public IRValue storePtr;

    public IRStoreInst(IRValue _value, IRValue _pointer, IRBasicBlock _parentBlock) {
        super(LLVM_STORE_INST, _value.valueType, _parentBlock);
        this.storeValue = _value;
        this.storePtr = _pointer;
        this.addOperand(_value);
        this.addOperand(_pointer);
    }

    public IRValue getStoreValue() {
        return storeValue;
    }

    public IRValue getStorePtr() {
        return storePtr;
    }

    @Override
    public String printInst() {
        if (this.storeValue instanceof IRNullptrConstant) {
            return LLVM_STORE_INST + " " + ((PointerType) this.storePtr.valueType).baseType + " "
                    + this.storeValue.getIdentifier() + ", " + this.storePtr.valueType + " "
                    + this.storePtr.getIdentifier();
            //+ ", align " + this.valueType.size();
        } else {
            return LLVM_STORE_INST + " " + this.storeValue.valueType + " "
                    + this.storeValue.getIdentifier() + ", " + this.storePtr.valueType
                    + " " + this.storePtr.getIdentifier() ;
            //+ ", align " + this.valueType.size();
        }
    }

    @Override
    public void accept(IRVisitor _visitor) {
        _visitor.visit(this);
    }
}
