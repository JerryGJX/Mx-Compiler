package ASM.Operand;

public class ASMStackOffset extends ASMImm {
    public enum StackOffsetType {
        getArg, alloca, spillReg, putArg, lowerSp, raiseSp
    }

    public StackOffsetType type;

    public ASMStackOffset(int val, StackOffsetType type) {
        super(val);
        this.type = type;
    }
}
