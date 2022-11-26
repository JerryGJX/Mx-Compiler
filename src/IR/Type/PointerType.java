package IR.Type;

public class PointerType extends BasicType {
    public boolean isNullptr;
    public BasicType baseType;

    public int length;

    public PointerType(BasicType _baseType, int _length) {
        if (_baseType == null) {
            isNullptr = true;
        } else {
            this.baseType = _baseType;
            this.length = _length;
        }
    }

    public static PointerType RecursiveBuildPointer(BasicType _baseType, int _dimSize) {
        PointerType pointerType = new PointerType(_baseType, 0);
        for (int i = 0; i < _dimSize; i++) {
            pointerType = new PointerType(pointerType, 0);
        }
        return pointerType;
    }


    public BasicType Dereference() {
        if (isNullptr) throw new RuntimeException("Dereference nullptr");
        return baseType;
    }


    @Override
    public String toString() {
        return this.baseType.toString() + "*";
    }

    @Override
    public boolean equals(BasicType _basicType) {
        if (_basicType instanceof PointerType) {
            return this.baseType.equals(((PointerType) _basicType).baseType);
        } else return false;
    }

    @Override
    public int size() {
        return 4;
    }
}
