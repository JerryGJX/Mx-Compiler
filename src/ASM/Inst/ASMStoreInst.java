package ASM.Inst;

import ASM.Operand.ASMImm;
import ASM.Operand.Reg;

public class ASMStoreInst extends ASMInst {
    int byteWidth;

    public ASMStoreInst(int byteWidth, Reg rs1, Reg rs2, ASMImm ASMImm) {
        super(null, rs1, rs2, ASMImm);

    }

    @Override
    public String toString() {
        return "s" + (byteWidth == 1 ? "b" : "w") + " " + rs2 + ", " + ASMImm + "(" + rs1 + ")";
    }
}

