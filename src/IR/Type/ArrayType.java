package IR.Type;

public class ArrayType extends BasicType { //stringType
    public BasicType baseType;
    public int length;

    public ArrayType(BasicType _baseType, int _length) {
        this.baseType = _baseType;
        this.length = _length;
    }

    @Override
    public String toString() {
        return "[" + length + " x " + baseType + "]";
    }

    public boolean equals(BasicType other) {
       return false;
    }

    @Override //align size
    public int size() {
        return baseType.size();
    }
}
