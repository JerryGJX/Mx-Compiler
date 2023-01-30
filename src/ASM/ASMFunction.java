package ASM;

import ASM.Inst.ASMInst;
import ASM.Operand.ASMOperand;
import ASM.Operand.ASMReg;
import BackEnd.ASMVisitor;

import java.util.ArrayList;

public class ASMFunction extends ASMOperand {
    public ArrayList<ASMBlock> blockList = new ArrayList<>();
    public ArrayList<ASMReg> paraList = new ArrayList<>();

//    public ASMInst decreaseStackPtrInst = null;
//    public ASMInst increaseStackPtrInst = null;

    public Boolean ifBuiltin = false;

    public int allocaCnt = 0;
    public int spilledArgCnt = 0;
    public int spilledRegCnt = 0;
    public int totalStack = 0;

    public ASMFunction(String funcName) {
        super(funcName);
    }

    public void addBlock(ASMBlock block) {
        blockList.add(block);
    }

    public String toString() {
        if (ifBuiltin) return "";
        StringBuilder ret = new StringBuilder("  .text\n" + "  .globl " + this.identifier + "\n");
        ret.append("  .type ").append(this.identifier).append(", @function\n");
        ret.append(this.identifier).append(":\n");
        for (ASMBlock block : blockList)
            ret.append(block);
        return ret.toString();
    }

    public void accept(ASMVisitor asmVisitor) {
        asmVisitor.visit(this);
    }
}
