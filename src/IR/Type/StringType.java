package IR.Type;

public class StringType extends StructType { //stringType
    public BasicType baseType;
    public int length;

    public StringType(BasicType _baseType, int _length) {
        super("string");
        this.baseType = _baseType;
        this.length = _length;
    }

    @Override
    public String toString() {
        return "[" + length + " x " + baseType.toString() + "]";
    }

    public boolean equals(BasicType other) {
       return false;
    }

    @Override
    public int size() {
        return baseType.size() * length;
    }
}
