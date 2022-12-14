package IR.Type;

import javax.lang.model.type.NullType;

public class PointerType extends BasicType {
    public boolean isNullptr;
    public BasicType baseType;

    public int dimSize;

    public PointerType(BasicType _baseType) {
        if (_baseType == null) {
            isNullptr = true;
        } else {
            this.baseType = _baseType;
            if (_baseType instanceof PointerType) {
                this.dimSize = ((PointerType) _baseType).dimSize + 1;
            } else {
                this.dimSize = 1;
            }
        }
    }

//    public static PointerType RecursiveBuildPointer(BasicType _baseType, int _dimSize) {
//        PointerType pointerType = new PointerType(_baseType, 0);
//        for (int i = 0; i < _dimSize; i++) {
//            pointerType = new PointerType(pointerType);
//        }
//        return pointerType;
//    }


    public BasicType Dereference() {
        if (isNullptr) throw new RuntimeException("Dereference nullptr");
        return baseType;
    }


    @Override
    public String toString() {
        return this.baseType + "*";
    }

    @Override
    public boolean equals(BasicType other) {
        if (other == null) return true;
        if (other instanceof PointerType) {
            return this.baseType.equals(((PointerType) other).baseType) && this.dimSize == ((PointerType) other).dimSize;
        } else if (other instanceof ArrayType) {
            return this.equals(stringType);
        }
        return false;
    }

    @Override
    public int size() {
        return POINTER_SIZE;
    }
}
