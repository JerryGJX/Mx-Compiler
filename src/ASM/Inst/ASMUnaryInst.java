package ASM.Inst;

import ASM.Operand.ASMReg;
import BackEnd.ASMVisitor;

public class ASMUnaryInst extends ASMInst{
    public String op;

    public ASMUnaryInst(String op, ASMReg rd, ASMReg rs1) {
        super(rd, rs1, null, null);
        this.op = op;
    }

    @Override
    public String toString() {
        return op + " " + rd + ", " + rs1;
    }

    @Override
    public void accept(ASMVisitor asmVisitor){
        asmVisitor.visit(this);
    }
}
