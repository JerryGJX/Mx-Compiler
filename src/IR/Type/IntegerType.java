package IR.Type;

public class IntegerType extends BasicType {
    public int bitWidth;//INT1, INT8, INT16, INT32, INT64

    public IntegerType(int _bitWidth) {
        super(TypeEnum.IntegerType);
        this.bitWidth = _bitWidth;
    }

    public String toString(){
        return "i" + this.bitWidth;
    }

    public boolean equals(BasicType other){
        if(other == null)return false;
        return other instanceof IntegerType && this.bitWidth == ((IntegerType) other).bitWidth;
    }
}
