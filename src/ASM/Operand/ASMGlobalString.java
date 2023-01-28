package ASM.Operand;


//.rodata
public class ASMGlobalString extends ASMGlobal {
    public String strConst;

    public ASMGlobalString(String name, String str) {
        super(name);
        this.strConst = str;
    }

    @Override
    public String toString() {
        return " .type " + globalName + ", @object\n" +
                globalName + ":\n" +
                "  .string \"" + strConst.replace("\\", "\\\\")
                .replace("\n", "\\n")
                .replace("\0", "")
                .replace("\t", "\\t")
                .replace("\"", "\\\"") +
                "\"\n" +
                " .size " + globalName + ", " + (strConst.length() + 1) + "\n\n";
    }
}


