package ASM.Operand;

public class ASMImm extends ASMOperand {
    public int val;

    public ASMImm(int val) {
        this.val = val;
    }

    public String toString() {
        return Integer.toString(val);
    }
}

