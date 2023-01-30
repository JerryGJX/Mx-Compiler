package ASM.Inst;

import ASM.ASMBlock;
import ASM.Operand.ASMReg;
import BackEnd.ASMVisitor;

public class ASMBrInst extends ASMInst{
    public enum ASMBrOp {
        beq, bne, blt, bge, bltu, bgeu
    }

    public ASMBrOp op;
    public ASMBlock targetBlock;

    public ASMBrInst(ASMBrOp op, ASMReg rs1, ASMReg rs2, ASMBlock targetBlock) {
        super(null, rs1, rs2, null);
        this.op = op;
        this.targetBlock = targetBlock;
    }

    @Override
    public String toString() {
        return op + " " + rs1 + ", " + rs2 + ", " + targetBlock.blockId();
    }

    @Override
    public void accept(ASMVisitor asmVisitor){
        asmVisitor.visit(this);
    }


}
