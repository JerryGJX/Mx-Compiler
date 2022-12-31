package ASM.Inst;

import ASM.Operand.Imm;
import ASM.Operand.Reg;

public class ASMLiInst extends ASMInst{
    public ASMLiInst(Reg rd, Imm imm) {
        super(rd, null, null, imm);
    }

    @Override
    public String toString() {
        return "li " + rd + ", " + imm;
    }

}
