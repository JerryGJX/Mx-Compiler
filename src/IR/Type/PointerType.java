package IR.Type;

public class PointerType extends BasicType {
    public boolean isNullptr;
    public BasicType baseType;
    public int dimSize;//pointer of high dimension

    public PointerType(BasicType _baseType) {
        if (_baseType == null) {
            isNullptr = true;
        } else {
            if (_baseType instanceof PointerType) {
                this.baseType = ((PointerType) _baseType).baseType;
                this.dimSize = ((PointerType) _baseType).dimSize + 1;
            } else {
                this.baseType = _baseType;
                this.dimSize = 1;
            }
        }
    }

    public PointerType(BasicType _baseType, int _dimSize) {
        if (_baseType == null) {
            isNullptr = true;
        } else {
            if (_baseType instanceof PointerType) {
                this.baseType = ((PointerType) _baseType).baseType;
                this.dimSize = ((PointerType) _baseType).dimSize + _dimSize;
            } else {
                this.baseType = _baseType;
                this.dimSize = _dimSize;
            }
        }
    }

    public BasicType Dereference() {
        if(isNullptr)throw new RuntimeException("Dereference nullptr");
        if (dimSize == 1) return baseType;
        else return new PointerType(baseType, dimSize - 1);
    }


    @Override
    public String toString() {
        return this.baseType.toString() + "*".repeat(dimSize);
    }

    @Override
    public boolean equals(BasicType _basicType) {
        if (_basicType instanceof PointerType) {
            return this.baseType.equals(((PointerType) _basicType).baseType) && this.dimSize == ((PointerType) _basicType).dimSize;
        } else return false;
    }

    @Override
    public int sizeof() {
        return 4;
    }
}
