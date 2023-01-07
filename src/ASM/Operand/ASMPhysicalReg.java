package ASM.Operand;

public class ASMPhysicalReg extends Reg {
    public String name;
    public ASMPhysicalReg(String name){
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
