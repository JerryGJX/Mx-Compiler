package ASM.Inst;

public class ASMRetInst extends ASMInst {
    public ASMRetInst() {
        super(null, null, null, null);
    }

    @Override
    public String toString() {
        return "ret";
    }
}
