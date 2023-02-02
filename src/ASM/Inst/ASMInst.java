package ASM.Inst;

import ASM.Operand.ASMImm;
import ASM.Operand.ASMReg;
import BackEnd.ASMVisitor;

import java.util.ArrayList;

public abstract class ASMInst {
    public ASMReg rd, rs1, rs2;
    public ASMImm imm;

    public ArrayList<ASMReg> use = new ArrayList<>();
    public ArrayList<ASMReg> def = new ArrayList<>();

    //debug
    public ASMInst parentInst;

    public ASMInst(ASMReg rd, ASMReg rs1, ASMReg rs2, ASMImm imm) {
        this.rd = rd;
        if (rd != null) def.add(rd);
        this.rs1 = rs1;
        if (rs1 != null) use.add(rs1);
        this.rs2 = rs2;
        if (rs2 != null) use.add(rs2);
        this.imm = imm;
    }

    public abstract String toString();

    public abstract void accept(ASMVisitor asmVisitor);
}
