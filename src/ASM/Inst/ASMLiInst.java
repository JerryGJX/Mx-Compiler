package ASM.Inst;

import ASM.Operand.ASMImm;
import ASM.Operand.Reg;

public class ASMLiInst extends ASMInst{
    public ASMLiInst(Reg rd, ASMImm ASMImm) {
        super(rd, null, null, ASMImm);
    }

    @Override
    public String toString() {
        return "li " + rd + ", " + ASMImm;
    }

}
