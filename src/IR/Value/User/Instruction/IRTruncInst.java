package IR.Value.User.Instruction;

import IR.IRVisitor;
import IR.Type.BasicType;
import IR.Value.IRBasicBlock;
import IR.Value.IRValue;

public class IRTruncInst extends IRInstruction{
//    %Y = trunc i32 123 to i1                        ; yields i1:true
//    %Z = trunc i32 122 to i1                        ; yields i1:false
//    %W = trunc <2 x i16> <i16 8, i16 7> to <2 x i8> ; yields <i8 8, i8 7>

    public IRTruncInst(String _valueName,IRValue _fromValue, BasicType _targetType, IRBasicBlock _parentBlock) {
        super(_valueName, _targetType, _parentBlock);
        this.addOperand(_fromValue);
    }

    public IRValue fromValue() {
        return this.getOperand(0);
    }

    public  BasicType targetType() {
        return (BasicType) this.valueType;
    }

    @Override
    public String printInst() {
        return this.getIdentifier() + " = " + LLVM_TRUNC_INST + " " + this.fromValue().valueType
                + " " + this.fromValue().getIdentifier() + " to " + this.targetType();
    }

    @Override
    public void accept(IRVisitor _visitor) {
        _visitor.visit(this);
    }
}
