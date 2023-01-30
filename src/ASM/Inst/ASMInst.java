package ASM.Inst;

import ASM.Operand.ASMImm;
import ASM.Operand.ASMReg;
import BackEnd.ASMVisitor;

public abstract class ASMInst {
    public ASMReg rd, rs1, rs2;
    public ASMImm imm;

    public ASMInst (ASMReg rd, ASMReg rs1, ASMReg rs2, ASMImm imm) {
        this.rd = rd;
        this.rs1 = rs1;
        this.rs2 = rs2;
        this.imm = imm;
    }

    public abstract String toString();

    public abstract void accept(ASMVisitor asmVisitor);
}
