package ASM.Operand;

public class ASMReg extends ASMOperand {
    public ASMReg color;
    public ASMStackOffset stackOffset;

    public ASMReg(String identifier) {
        super(identifier);
    }

    public Boolean isReserved() {
        return false;
    }

    @Override
    public String toString() {
        if (color == null || color == this)
            return super.toString();
        else return color.toString();
    }
}
