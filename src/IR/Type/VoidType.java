package IR.Type;

public class VoidType extends BasicType {

    @Override
    public String toString() {
        return null;
    }

    @Override
    public boolean equals(BasicType other) {
        return other instanceof VoidType;
    }

    @Override
    public int sizeof() {
        return 0;
    }
}
