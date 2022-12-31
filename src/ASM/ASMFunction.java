package ASM;

import ASM.Operand.Reg;

import java.util.ArrayList;

public class ASMFunction {
    public String funcName;
    public ArrayList<ASMBlock> blockList = new ArrayList<ASMBlock>();
    public ArrayList<Reg> paraList = new ArrayList<Reg>();

    public int virtualRegCnt = 0;
    public int allocaUsed = 4;
    public int paramUsed = 0;
    public int totalStackUsed = 0;

    public ASMFunction(String funcName) {
        this.funcName = funcName;
    }

    public void addBlock(ASMBlock block) {
        blockList.add(block);
    }

    public String toString() {
        StringBuilder ret = new StringBuilder("  .text\n" + "  .globl " + funcName + "\n");
        ret.append(funcName).append(":\n");
        for (ASMBlock block : blockList)
            ret.append(block);
        return ret.toString();
    }
}
