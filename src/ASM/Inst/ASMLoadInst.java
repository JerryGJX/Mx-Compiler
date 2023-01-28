package ASM.Inst;

import ASM.Operand.ASMImm;
import ASM.Operand.ASMReg;

public class ASMLoadInst extends ASMInst {
    int byteWidth;
    public ASMLoadInst(int byteWidth, ASMReg rd, ASMReg rs1, ASMImm offset) {
        super(rd, rs1, null, offset);
        this.byteWidth = byteWidth;
    }

    @Override
    public String toString() {
        return "l" + (byteWidth == 1 ? "b" : "w") + " " + rd + ", " + ASMImm + "(" + rs1 + ")";
    }
}

