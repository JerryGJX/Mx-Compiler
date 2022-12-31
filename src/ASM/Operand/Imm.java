package ASM.Operand;

public class Imm extends Operand {
    public int val;

    public Imm(int val) {
        this.val = val;
    }

    public String toString() {
        return Integer.toString(val);
    }
}

