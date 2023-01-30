package ASM.Operand;


//.data
public class ASMGlobalValue extends ASMGlobal {
    public ASMGlobalValue(String globalName) {
        super(globalName);
    }

    public String toString() {
        return
                " .globl " + globalName + "\n" +
                " .type " + globalName + ", @object\n" +
                globalName + ":\n" +
                "  .word 0\n" +
                " .size " + globalName + ", 4\n\n";
    }
}
