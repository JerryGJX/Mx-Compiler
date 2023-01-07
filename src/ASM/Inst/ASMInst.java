package ASM.Inst;

import ASM.Operand.ASMImm;
import ASM.Operand.Reg;

public abstract class ASMInst {
    public Reg rd, rs1, rs2;
    public ASMImm ASMImm;

    public ASMInst (Reg rd, Reg rs1, Reg rs2, ASMImm ASMImm) {
        this.rd = rd;
        this.rs1 = rs1;
        this.rs2 = rs2;
        this.ASMImm = ASMImm;
    }

    public abstract String toString();
}
