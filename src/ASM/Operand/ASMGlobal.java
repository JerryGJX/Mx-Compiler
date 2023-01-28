package ASM.Operand;

public class ASMGlobal extends ASMOperand{
    public String globalName;
    public ASMGlobal(String globalName){
        super(globalName);
        this.globalName = globalName;
    }
}
