package ASM.Inst;

import ASM.Operand.Imm;
import ASM.Operand.Reg;


// op,rd,rs1,rs2: add,sub,...
// op,rd,rs1,imm: addi,slti,...



public class ASMBinaryInst extends ASMInst{
    public String op;

    public ASMBinaryInst(String op, Reg rd, Reg rs1, Reg rs2) {
        super(rd, rs1, rs2, null);
        this.op = op;
    }

    public ASMBinaryInst(String op, Reg rd, Reg rs1, Imm imm) {
        super(rd, rs1, null, imm);
        this.op = op + "i";//todo
    }


    @Override
    public String toString() {
        if(this.imm == null)
            return op + " " + rd + ", " + rs1 + ", " + rs2;
        else
            return op + " " + rd + ", " + rs1 + ", " + imm;
    }
}
