package IR.Value;

import IR.IRVisitor;
import IR.Type.BasicType;
import IR.Type.LabelType;
import IR.Value.User.Constant.GlobalValue.IRFunction;
import IR.Value.User.Instruction.IRInstruction;

import java.util.LinkedList;

public class IRBasicBlock extends IRValue {
    public String blockName;
    public LinkedList<IRInstruction> instList = new LinkedList<>();
    public IRInstruction terminator;
    public IRFunction parentFunc;

    public IRBasicBlock(String _blockName) {
        super(new LabelType());
        this.blockName = _blockName;
    }

    public void addInst(IRInstruction _inst) {
        instList.add(_inst);
    }

    public String GetBlockName() {
        return blockName;
    }

    public String toString() {
        StringBuilder ans = new StringBuilder(this.blockName + ":\n");
        for (IRInstruction inst : instList) {
            ans.append("    ").append(inst.toString()).append("\n");
        }
        return ans.toString();
    }


    public void accept(IRVisitor _visitor) {
        _visitor.visit(this);
    }
}
