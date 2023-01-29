package IR.Value.User.Instruction;

import IR.IRVisitor;
import IR.Type.BasicType;
import IR.Type.PointerType;
import IR.Value.IRBasicBlock;
import IR.Value.IRValue;

public class IRGEPInst extends IRInstruction {
    public String elementName = null;

    public IRGEPInst(String _valueName, String _elementName, IRValue _headPointer, IRBasicBlock _parentBlock, BasicType _pointedType, IRValue index) {
        super(_valueName, new PointerType(_pointedType), _parentBlock);
        assert _headPointer.valueType instanceof PointerType;
        this.addOperand(_headPointer);

//        System.out.println("IRGEPInst1: list Size :" + this.operandSize());

//        for (IRValue index : indexes) {
        this.addOperand(index);
//        }
        this.elementName = _elementName;
    }

    public IRGEPInst(String _valueName, IRValue _headPointer, IRBasicBlock _parentBlock, BasicType _pointedType, IRValue index) {
        super(_valueName, new PointerType(_pointedType), _parentBlock);
        assert _headPointer.valueType instanceof PointerType;
        this.addOperand(_headPointer);

//        System.out.println("IRGEPInst2: list Size :" + this.operandSize());

//        for (IRValue index : indexes) {
//            System.out.println("IRGEPInst2: index: " + index);
        this.addOperand(index);
//        }
    }

    public int numOfIndexes() {
        return this.operandList.size() - 1;
    }

    public IRValue headPointer() {
        return this.operandList.get(0);
    }

    public IRValue getIndex(int index) {
        return this.operandList.get(index + 1);
    }

    public IRValue ptrMoveIndex() {
        return getIndex(0);
    }

    public IRValue memberIndex() {
        return getIndex(1);
    }


    @Override
    public String printInst() {
//        System.out.println(this.valueName + " IRGEPInst3: list Size :" + this.operandSize());

//        StringBuilder Ans = new StringBuilder(this.valueName + " = getelementptr ");

        StringBuilder Ans = new StringBuilder(this.getIdentifier() + " = " + LLVM_GEP_INST + " " +
                ((PointerType) (this.headPointer().valueType)).baseType +
                ", " + this.headPointer().valueType + " " + this.headPointer().getIdentifier());
        for (int i = 0; i < this.numOfIndexes(); i++) {
            Ans.append(", ").append(this.getIndex(i).valueType).append(" ").append(this.getIndex(i).getIdentifier());
        }
        return Ans.toString();
    }

    @Override
    public void accept(IRVisitor _visitor) {
        _visitor.visit(this);
    }
}
