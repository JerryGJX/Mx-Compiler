package ASM.Inst;

import ASM.ASMFunction;
import BackEnd.ASMVisitor;

public class ASMCallInst extends ASMInst {
    public ASMFunction calledFunc;

    public ASMCallInst(ASMFunction calledFunc) {
        super(null, null, null, null);
        this.calledFunc = calledFunc;
    }

    @Override
    public String toString() {
        return "call " + calledFunc.identifier;
    }

    @Override
    public void accept(ASMVisitor asmVisitor) {
        asmVisitor.visit(this);
    }
}
