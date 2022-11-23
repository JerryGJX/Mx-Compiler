package IR.Type;

import java.util.ArrayList;

public class ArrayType extends BasicType {
    public BasicType elementType;
    public int dimSize;

    public ArrayType(BasicType elementType,int _dimSize) {
        this.elementType = elementType;
        this.dimSize = _dimSize;
    }

    @Override
    public String toString() {
            return "[" + dimSize + " x " + this.elementType.toString() + "]";
    }

    public boolean equals(BasicType other) {
        if (other == null) return false;
        if(other instanceof ArrayType){
            return this.elementType.equals(((ArrayType) other).elementType) && this.dimSize == ((ArrayType) other).dimSize;
        }else return false;
    }

    @Override
    public int sizeof() {
        return elementType.sizeof() * dimSize;
    }
}
