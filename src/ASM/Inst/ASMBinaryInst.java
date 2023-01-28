package ASM.Inst;

import ASM.Operand.ASMImm;
import ASM.Operand.ASMReg;


// op,rd,rs1,rs2: add,sub,...
// op,rd,rs1,imm: addi,slti,...



public class ASMBinaryInst extends ASMInst{
    public String op;

    public ASMBinaryInst(String op, ASMReg rd, ASMReg rs1, ASMReg rs2, ASMImm ASMImm) {
        super(rd, rs1, rs2, ASMImm);
        this.op = op;
    }

    @Override
    public String toString() {
        if(this.ASMImm == null)
            return op + " " + rd + ", " + rs1 + ", " + rs2;
        else
            return op + " " + rd + ", " + rs1 + ", " + ASMImm;
    }
}
