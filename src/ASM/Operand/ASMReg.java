package ASM.Operand;

public abstract class ASMReg extends ASMOperand {
    public ASMReg(String identifier) {
        super(identifier);
    }


    public abstract boolean equals(ASMReg reg);
}
