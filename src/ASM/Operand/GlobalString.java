package ASM.Operand;


//.rodata
public class GlobalString extends Global {
    public String strConst;

    public GlobalString(String name, String str) {
        super(name);
        this.strConst = str;
    }

    @Override
    public String toString() {
        String ret = globalName + ":\n";
        ret += "  .string \"" + strConst.replace("\\", "\\\\")
                .replace("\n", "\\n")
                .replace("\0", "")
                .replace("\t", "\\t")
                .replace("\"", "\\\"") + "\"\n";
        return ret;
    }
}


