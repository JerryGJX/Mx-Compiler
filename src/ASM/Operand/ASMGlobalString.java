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
        String ret = globalName + ":\n";
        ret += "  .string \"" + strConst.replace("\\", "\\\\")
                .replace("\n", "\\n")
                .replace("\0", "")
                .replace("\t", "\\t")
                .replace("\"", "\\\"") + "\"\n";
        return ret;
    }
}


