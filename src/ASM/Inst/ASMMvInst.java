package ASM.Inst;

import ASM.Operand.ASMReg;

public class ASMMvInst extends ASMInst {
    public ASMMvInst(ASMReg rd, ASMReg rs1) {
        super(rd, rs1, null, null);
    }

    @Override
    public String toString() {
        return "mv " + rd + ", " + rs1;
    }
}

