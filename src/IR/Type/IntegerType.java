package IR.Type;

public class IntegerType extends BasicType {
    public int bitWidth;//INT1, INT8, INT16, INT32, INT64

    public IntegerType(int _bitWidth) {
        this.bitWidth = _bitWidth;
    }


    @Override
    public String toString() {
        return "i" + this.bitWidth;
    }

    @Override
    public boolean equals(BasicType _basicType) {
        if (_basicType instanceof IntegerType) {
            return this.bitWidth == ((IntegerType) _basicType).bitWidth;
        }
        return false;
    }

    @Override
    public int size() {
        return this.bitWidth / 8;
    }
}
