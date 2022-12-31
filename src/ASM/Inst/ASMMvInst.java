package ASM.Inst;

import ASM.Operand.Reg;

public class ASMMvInst extends ASMInst {
    public ASMMvInst(Reg rd, Reg rs1) {
        super(rd, rs1, null, null);
    }

    @Override
    public String toString() {
        return "mv " + rd + ", " + rs1;
    }
}

