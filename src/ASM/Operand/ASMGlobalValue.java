package ASM.Operand;


//.data
public class ASMGlobalValue extends ASMGlobal {
    public int value, byteWidth;

    public ASMGlobalValue(String globalName, int value, int byteWidth) {
        super(globalName);
        this.value = value;
        this.byteWidth = byteWidth;
    }

    public String toString() {
        String ret = globalName + ":\n";
        ret += (byteWidth == 1 ? "  .byte " : "  .word ") + value + "\n";
        return ret;
    }
}
