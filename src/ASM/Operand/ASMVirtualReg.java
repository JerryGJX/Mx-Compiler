package ASM.Operand;

public class ASMVirtualReg extends ASMReg {
    public static int virtualRegNum = 0;
    public int rank, byteSize;
    public String funcId;

    public ASMVirtualReg(String _funcId) {
        super("v" + virtualRegNum);
        this.rank = virtualRegNum;
        this.byteSize = 4;
        this.funcId = _funcId;
        virtualRegNum++;
    }




    public ASMVirtualReg(int byteSize, String _funcId) {
        super("v" + virtualRegNum);
        this.rank = virtualRegNum;
        this.byteSize = byteSize;
        this.funcId = _funcId;
        virtualRegNum++;
    }


    public boolean equals(ASMReg reg) {
        if (reg == null) return false;
        else {
            if (reg instanceof ASMVirtualReg vReg) {
                return this.rank == vReg.rank && this.funcId.equals(vReg.funcId);
            } else return false;
        }
    }

    @Override
    public Boolean isReserved() {
        return false;
    }

    public String toString() {
        return "vReg" + rank;
//        throw new RuntimeException("ASMVirtualReg.toString() should not be called.");
    }
}

