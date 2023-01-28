package ASM;

import ASM.Inst.ASMInst;
import ASM.Operand.ASMOperand;
import ASM.Operand.ASMReg;

import java.util.ArrayList;

public class ASMFunction extends ASMOperand {
    public ArrayList<ASMBlock> blockList = new ArrayList<>();
    public ArrayList<ASMReg> paraList = new ArrayList<>();

    public ASMInst decreaseStackPtrInst = null;
    public ASMInst increaseStackPtrInst = null;

    public int virtualRegCnt = 0;
    public int allocaCnt = 0;
    public int paramNum = 0;
    public int totalStack = 0;

    public ASMFunction(String funcName) {
        super(funcName);
    }

    public void addBlock(ASMBlock block) {
        blockList.add(block);
    }

    public String toString() {
        StringBuilder ret = new StringBuilder("  .text\n" + "  .globl " + this.identifier + "\n");
        ret.append(this.identifier).append(":\n");
        for (ASMBlock block : blockList)
            ret.append(block);
        return ret.toString();
    }
}
