package IR.Type;

public class PointerType extends BasicType {
    public BasicType baseType;
    public int dimSize;//pointer of high dimension

    public PointerType(BasicType _baseType) {
        super(TypeEnum.PointerType);
        this.baseType = _baseType;
        dimSize = 1;
    }

    public PointerType(BasicType _baseType, int _dimSize) {
        super(TypeEnum.PointerType);
        if(baseType.typeEnum == TypeEnum.PointerType){
            this.baseType = ((PointerType) baseType).baseType;
            this.dimSize = ((PointerType) baseType).dimSize + _dimSize;
        }
        else{
            this.baseType = _baseType;
            this.dimSize = _dimSize;
        }
    }

    public BasicType Dereference(){
        if(dimSize == 1)return baseType;
        else return new PointerType(baseType, dimSize - 1);
    }


}
