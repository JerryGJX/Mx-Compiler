package ASM.Inst;

import ASM.Operand.ASMImm;
import ASM.Operand.ASMReg;

public abstract class ASMInst {
    public ASMReg rd, rs1, rs2;
    public ASMImm ASMImm;

    public ASMInst (ASMReg rd, ASMReg rs1, ASMReg rs2, ASMImm ASMImm) {
        this.rd = rd;
        this.rs1 = rs1;
        this.rs2 = rs2;
        this.ASMImm = ASMImm;
    }

    public abstract String toString();
}
