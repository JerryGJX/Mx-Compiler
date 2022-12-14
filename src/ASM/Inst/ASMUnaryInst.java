package ASM.Inst;

import ASM.Operand.Reg;

public class ASMUnaryInst extends ASMInst{
    public String op;

    ASMUnaryInst(String op, Reg rd, Reg rs1) {
        super(rd, rs1, null, null);
        this.op = op;
    }

    @Override
    public String toString() {
        return op + " " + rd + ", " + rs1;
    }
}
