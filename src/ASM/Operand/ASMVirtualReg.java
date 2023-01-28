package ASM.Operand;

public class ASMVirtualReg extends ASMReg {
    public static int virtualRegNum = 0;
    public int rank, byteSize;

    public ASMVirtualReg(){
        super("v" + virtualRegNum);
        this.rank = virtualRegNum;
        this.byteSize = 4;
        virtualRegNum++;
    }


    public ASMVirtualReg(int byteSize){
        super("v" + virtualRegNum);
        this.rank = virtualRegNum;
        this.byteSize = byteSize;
        virtualRegNum++;
    }

    public String toString() {
        throw new RuntimeException("ASMVirtualReg.toString() should not be called.");
    }
}

