package ASM.Inst;

import ASM.ASMBlock;
import ASM.Operand.Reg;

public class ASMBrInst extends ASMInst{
    public String op;
    public ASMBlock targetBlock;

    public ASMBrInst(String op, Reg rs1,Reg rs2, ASMBlock targetBlock) {
        super(null, rs1, rs2, null);
        this.op = op;
        this.targetBlock = targetBlock;
    }

    @Override
    public String toString() {
        return op + " " + rs1 + ", " + rs2 + ", " + targetBlock.blockId();
    }

}
