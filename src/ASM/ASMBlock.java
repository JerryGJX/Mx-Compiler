package ASM;

import ASM.Inst.ASMInst;
import ASM.Operand.ASMOperand;

import java.util.LinkedList;

public class ASMBlock extends ASMOperand {
    //    String blockId;
    public LinkedList<ASMInst> instList = new LinkedList<>();

    public ASMBlock(String blockId) {
        super(blockId);
    }

    public void addInst(ASMInst inst) {
        instList.add(inst);
    }

    public String blockId() {
        return this.identifier;
    }

    public String toString() {
        StringBuilder ret = new StringBuilder();
        if (this.identifier != null) ret.append(this.identifier).append(":\n");
        for (ASMInst inst : instList)
            ret.append("  ").append(inst).append("\n");
        return ret.toString();
    }
}
