package ASM.Operand;

public class ASMGlobalAddr extends ASMImm {
    public enum HiLoType {hi, lo}

    public HiLoType addrType;
    public ASMGlobal regId;

    public ASMGlobalAddr(HiLoType _addrType, ASMGlobal _regId) {
        super(0);
        regId = _regId;
    }

    @Override
    public String toString() {
        return "%" + addrType + "(" + regId + ")";
    }
}
