package IR.Type;

public class memBoolType extends BasicType {

    @Override
    public String toString() {
        return "i8";
    }

    @Override
    public boolean equals(BasicType _basicType) {
        return _basicType instanceof memBoolType;
    }

    @Override
    public int size() {
        return 1;
    }
}
