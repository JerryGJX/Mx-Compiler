package ASM.Operand;

public class ASMGlobal extends Reg {
    public String globalName;
    public ASMGlobal(String globalName){
        this.globalName = globalName;
    }
}
