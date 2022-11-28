package IR.Value.User.Instruction;

import IR.Type.BasicType;
import IR.Type.PointerType;
import IR.Value.IRBasicBlock;
import IR.Value.IRValue;

public class IRGEPInst extends IRInstruction {
    public String elementName = null;

    public IRGEPInst(String _valueName, String _elementName, IRValue _headPointer, BasicType _pointedType, IRBasicBlock _parentBlock, IRValue... indexes) {
        super(_valueName, _pointedType, _parentBlock);
        assert _headPointer.valueType instanceof PointerType;
        this.addOperand(_headPointer);
        for (IRValue index : indexes) {
            this.addOperand(index);
        }
        this.elementName = _elementName;
    }

    public IRGEPInst(String _valueName,IRValue _headPointer, BasicType _pointedType, IRBasicBlock _parentBlock, IRValue... indexes) {
        super(_valueName, _pointedType, _parentBlock);
        assert _headPointer.valueType instanceof PointerType;
        this.addOperand(_headPointer);
        for (IRValue index : indexes) {
            this.addOperand(index);
        }
    }

    public int numOfIndexes() {
        return this.OperandList.size() - 1;
    }

    public IRValue headPointer() {
        return this.OperandList.get(0);
    }

    public IRValue getIndex(int index) {
        return this.OperandList.get(index + 1);
    }

    public IRValue ptrMoveIndex() {
        return getIndex(0);
    }

    public IRValue memberIndex() {
        return getIndex(1);
    }


    @Override
    public String printInst() {
        StringBuilder Ans = new StringBuilder(this.getIdentifier() + " = " + LLVM_GEP_INST + " " +
                ((PointerType) (this.headPointer().valueType)).baseType +
                ", " + this.headPointer().valueType + " " + this.headPointer().getIdentifier());
        for (int i = 0; i < this.numOfIndexes(); i++) {
            Ans.append(", ").append(this.getIndex(i).valueType).append(" ").append(this.getIndex(i).getIdentifier());
        }
        return Ans.toString();
    }
}
