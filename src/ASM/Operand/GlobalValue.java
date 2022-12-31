package ASM.Operand;


//.data
public class GlobalValue extends Global {
    public int value, byteWidth;

    public GlobalValue(String globalName, int value, int byteWidth) {
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
