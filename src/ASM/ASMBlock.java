package ASM;

import ASM.Inst.ASMInst;

import java.util.LinkedList;

public class ASMBlock {
    String blockId;
    public LinkedList<ASMInst> instList = new LinkedList<>();

    public ASMBlock(String blockId) {
        this.blockId = blockId;
    }

    public void addInst(ASMInst inst) {
        instList.add(inst);
    }

    public String blockId() {
        return blockId;
    }

    public String toString() {
        StringBuilder ret = new StringBuilder();
        if (blockId != null) ret.append(blockId).append(":\n");
        for (ASMInst inst : instList)
            ret.append("  ").append(inst).append("\n");
        return ret.toString();
    }
}
