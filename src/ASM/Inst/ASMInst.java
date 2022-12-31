package ASM.Inst;

import ASM.Operand.Imm;
import ASM.Operand.Reg;

public abstract class ASMInst {
    public Reg rd, rs1, rs2;
    public Imm imm;

    public ASMInst (Reg rd, Reg rs1, Reg rs2, Imm imm) {
        this.rd = rd;
        this.rs1 = rs1;
        this.rs2 = rs2;
        this.imm = imm;
    }

    public abstract String toString();
}
