package ASM.Inst;

import ASM.ASMBlock;
import BackEnd.ASMVisitor;

public class ASMJumpInst extends ASMInst {
    public ASMBlock targetBlock;

    public ASMJumpInst(ASMBlock targetBlock) {
        super(null, null, null, null);
        this.targetBlock = targetBlock;
    }

    @Override
    public String toString() {
        return "j " + targetBlock.blockId();
    }

    @Override
    public void accept(ASMVisitor asmVisitor){
        asmVisitor.visit(this);
    }
}
