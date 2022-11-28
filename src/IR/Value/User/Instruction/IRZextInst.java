package IR.Value.User.Instruction;

import IR.Type.BasicType;
import IR.Value.IRBasicBlock;
import IR.Value.IRValue;

public class IRZextInst extends IRInstruction{//类型转换 e.g. i32 -> i64
//    语法：
//    <result> = zext <ty> <value> to <ty2>             ; yields ty2
//    概述：
//    zext指令把操作数使用0扩展为ty2类型。
//    参数：
//    zext指令要转换的数值，必须是整型，目标类型也必须是整型。value的位的大小必须比目标类型ty2的位的大小小。

    public IRZextInst(String _valueName,IRValue _fromValue, BasicType _targetType, IRBasicBlock _parentBlock) {
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
        return this.getIdentifier() + " = " + LLVM_ZEXT_INST + " " + this.fromValue().valueType
                + " " + this.fromValue().getIdentifier() + " to " + this.targetType();
    }
}
