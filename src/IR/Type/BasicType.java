package IR.Type;

public abstract class BasicType {
//    String typeName; //不含维数
//    public TypeEnum typeEnum;
//    public int dimSize = 0;

//    public BasicType(String _typeName, int dimSize) {
//        this.typeName = _typeName;
//        this.dimSize = dimSize;
//    }

//    public BasicType (BasicType _basicType){
//        this.typeEnum = _basicType.typeEnum;
//        this.dimSize = _basicType.dimSize;
//    }

    public abstract String toString();

    public abstract boolean equals(BasicType _basicType);

    public abstract int sizeof();
}
