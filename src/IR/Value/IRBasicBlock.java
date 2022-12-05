package IR.Value;

import IR.IRVisitor;
import IR.Type.LabelType;
import IR.Value.User.Instruction.IRInstruction;

import java.util.LinkedList;

public class IRBasicBlock extends IRValue {

    public String blockName;
    public LinkedList<IRInstruction> instList = new LinkedList<>();
    public IRInstruction terminator;
//    public IRFunction parentFunc;

    public IRBasicBlock(String _blockName) {
        super(new LabelType());
        this.blockName = _blockName;
//        this.parentFunc = _parentFunc;
    }

    public void addInst(IRInstruction _inst) {
        instList.add(_inst);
    }

    public void addInstFirst(IRInstruction _inst) {
        instList.addFirst(_inst);
    }

    public void addTerminator(IRInstruction _terminator) {
        if (terminator == null) {
            terminator = _terminator;
        }
    }

    public String GetBlockId() {
        return LOCAL_PREFIX + blockName;
    }

    public String toString() {
        StringBuilder ans = new StringBuilder(this.blockName + ":\n");
        for (IRInstruction inst : instList) ans.append("    ").append(inst.printInst()).append("\n");
        if (terminator == null) throw new Error("[IRBasicBlock] " + this.blockName + ": with out terminator instruction");
        ans.append("    ").append(terminator.printInst()).append("\n");
        return ans.toString();
    }


    public void accept(IRVisitor _visitor) {
        _visitor.visit(this);
    }
}
