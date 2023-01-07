package ASM.Operand;

public class ASMVirtualReg extends Reg {
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
}
