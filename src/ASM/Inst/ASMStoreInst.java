package ASM.Inst;

import ASM.Operand.Imm;
import ASM.Operand.Reg;

public class ASMStoreInst extends ASMInst {
    int byteWidth;

    public ASMStoreInst(int byteWidth, Reg rs1, Reg rs2, Imm imm) {
        super(null, rs1, rs2, imm);

    }

    @Override
    public String toString() {
        return "s" + (byteWidth == 1 ? "b" : "w") + " " + rs2 + ", " + imm + "(" + rs1 + ")";
    }
}

