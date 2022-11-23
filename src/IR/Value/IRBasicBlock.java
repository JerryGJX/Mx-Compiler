package IR.Value;

import IR.IRVisitor;
import IR.Type.BasicType;
import IR.Value.User.Constant.GlobalValue.IRFunction;
import IR.Value.User.Instruction.IRInstruction;

import java.util.LinkedList;

public class IRBasicBlock extends IRValue {
    public LinkedList<IRInstruction> instList = new LinkedList<>();
    public IRInstruction terminator;
    public IRFunction parentFunc;

    public IRBasicBlock(BasicType _basicType) {
        super(_basicType);
    }

    @Override
    public void accept(IRVisitor _visitor) {
        _visitor.visit(this);
    }
}
