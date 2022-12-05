package IR.Type;

public class BoolType extends BasicType {

    @Override
    public String toString() {
        return "i1";
    }

    @Override
    public boolean equals(BasicType other) {
        return other instanceof BoolType;
    }

    @Override
    public int size() {
        return 1;
    }
}

