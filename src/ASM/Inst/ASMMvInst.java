package ASM.Inst;

import ASM.Operand.ASMReg;
import BackEnd.ASMVisitor;

public class ASMMvInst extends ASMInst {
    public ASMMvInst(ASMReg rd, ASMReg rs1) {
        super(rd, rs1, null, null);
    }

    @Override
    public String toString() {
        return "mv " + rd + ", " + rs1;
    }

    @Override
    public void accept(ASMVisitor asmVisitor){
        asmVisitor.visit(this);
    }
}

