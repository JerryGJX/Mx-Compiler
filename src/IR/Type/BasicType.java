package IR.Type;

public class BasicType {
    public TypeEnum typeEnum;
    public int dimSize = 0;

    public BasicType(TypeEnum typeEnum) {
        this.typeEnum = typeEnum;
    }

    public BasicType(TypeEnum typeEnum, int dimSize) {
        this.typeEnum = typeEnum;
        this.dimSize = dimSize;
    }

    public BasicType (BasicType _basicType){
        this.typeEnum = _basicType.typeEnum;
        this.dimSize = _basicType.dimSize;
    }




    public String toString(){
        if(typeEnum.equals(TypeEnum.VoidType))return "void";
        else if(typeEnum.equals(TypeEnum.LabelType))return "label";
        else return null;
    }

    public boolean equals(BasicType other){
        if(other == null)return false;
        return this.typeEnum.equals(other.typeEnum) && this.dimSize == other.dimSize;
    }
}
