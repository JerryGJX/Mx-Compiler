package ASM.Inst;

import BackEnd.ASMVisitor;

public class ASMRetInst extends ASMInst {
    public ASMRetInst() {
        super(null, null, null, null);
    }

    @Override
    public String toString() {
        return "ret";
    }

    @Override
    public void accept(ASMVisitor asmVisitor){
        asmVisitor.visit(this);
    }
}
