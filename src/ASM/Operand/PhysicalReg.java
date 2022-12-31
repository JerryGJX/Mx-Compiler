package ASM.Operand;

public class PhysicalReg extends Reg {
    public String name;
    public PhysicalReg(String name){
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
