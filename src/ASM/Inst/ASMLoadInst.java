package ASM.Inst;

import ASM.Operand.Imm;
import ASM.Operand.Reg;

public class ASMLoadInst extends ASMInst {
    int byteWidth;
    public ASMLoadInst(int byteWidth, Reg rd, Reg rs1, Imm offset) {
        super(rd, rs1, null, offset);
        this.byteWidth = byteWidth;
    }

    @Override
    public String toString() {
        return "l" + (byteWidth == 1 ? "b" : "w") + " " + rd + ", " + imm + "(" + rs1 + ")";
    }
}

