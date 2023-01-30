package ASM.Inst;

import ASM.Operand.ASMImm;
import ASM.Operand.ASMReg;
import BackEnd.ASMVisitor;

public class ASMLiInst extends ASMInst{
    public ASMLiInst(ASMReg rd, ASMImm ASMImm) {
        super(rd, null, null, ASMImm);
    }

    @Override
    public String toString() {
        return "li " + rd + ", " + imm;
    }

    @Override
    public void accept(ASMVisitor asmVisitor){
        asmVisitor.visit(this);
    }
}
