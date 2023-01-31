package ASM.Inst;

import ASM.Operand.ASMImm;
import ASM.Operand.ASMReg;
import BackEnd.ASMVisitor;

public class ASMStoreInst extends ASMInst {
    public int byteWidth;

    public ASMStoreInst(int byteWidth, ASMReg val, ASMReg addr, ASMImm ASMImm) {
        super(null, addr, val, ASMImm);

    }

    @Override
    public String toString() {
        return "s" + (byteWidth == 1 ? "b" : "w") + " " + rs2 + ", " + imm + "(" + rs1 + ")";
    }

    @Override
    public void accept(ASMVisitor asmVisitor){
        asmVisitor.visit(this);
    }
}

