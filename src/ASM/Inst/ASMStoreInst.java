package ASM.Inst;

import ASM.Operand.ASMImm;
import ASM.Operand.ASMReg;

public class ASMStoreInst extends ASMInst {
    int byteWidth;

    public ASMStoreInst(int byteWidth, ASMReg addr, ASMReg val, ASMImm ASMImm) {
        super(null, addr, val, ASMImm);

    }

    @Override
    public String toString() {
        return "s" + (byteWidth == 1 ? "b" : "w") + " " + rs2 + ", " + ASMImm + "(" + rs1 + ")";
    }
}

