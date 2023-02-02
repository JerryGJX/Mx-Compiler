package ASM;

import ASM.Inst.ASMInst;
import ASM.Inst.ASMLoadInst;
import ASM.Operand.ASMOperand;
import ASM.Operand.ASMPhysicalASMReg;
import BackEnd.ASMVisitor;

import java.util.LinkedList;
import java.util.Objects;

public class ASMBlock extends ASMOperand {
    //    String blockId;
    public LinkedList<ASMInst> instList = new LinkedList<>();

    public LinkedList<ASMBlock> sucBlockList = new LinkedList<>();

    public ASMBlock(String blockId) {
        super(blockId);
    }

    public void addInst(ASMInst inst) {
        instList.add(inst);
    }

    public String blockId() {
        return this.identifier;
    }

    public String toString() {
        StringBuilder ret = new StringBuilder();
        if (this.identifier != null) ret.append(this.identifier).append(":\n");
        for (ASMInst inst : instList) {
            //debug
            if (inst instanceof ASMLoadInst && Objects.equals(((ASMLoadInst) inst).rs1.identifier, "sp") && ((ASMLoadInst) inst).imm.val == 0) {
                int A = 0;
            }

            ret.append("         ").append(inst).append("\n");
        }

        return ret.toString();
    }

    public void accept(ASMVisitor asmVisitor) {
        asmVisitor.visit(this);
    }
}
