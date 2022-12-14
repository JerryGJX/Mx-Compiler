package ASM.Inst;

import ASM.Operand.ASMImm;
import ASM.Operand.Reg;

public class ASMLuiInst extends ASMInst {
    public ASMLuiInst(Reg rd, ASMImm ASMImm) {
        super(rd, null, null, ASMImm);
    }

    @Override
    public String toString() {
        return "lui " + rd + ", " + ASMImm;
    }
}

