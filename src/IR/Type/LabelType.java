package IR.Type;

public class LabelType extends BasicType {
    public LabelType() {

    }

    @Override
    public String toString() {
        return "label";
    }

    @Override
    public boolean equals(BasicType _basicType) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }
}
