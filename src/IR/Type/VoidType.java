package IR.Type;

public class VoidType extends BasicType {

    @Override
    public String toString() {
        return "void";
    }

    @Override
    public boolean equals(BasicType other) {
        return other instanceof VoidType;
    }

    @Override
    public int size() {
        return 0;
    }
}
