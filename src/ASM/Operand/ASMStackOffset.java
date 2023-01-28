package ASM.Operand;

public class ASMStackOffset extends ASMImm {
    public enum StackOffsetType {
        getArg, alloca, spill, putArg, lowerSp, raiseSp
    }

    public StackOffsetType type;

    public ASMStackOffset(int val, StackOffsetType type) {
        super(val);
        this.type = type;
    }
}
