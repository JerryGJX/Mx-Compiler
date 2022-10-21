package AST.typeNode;

public class TypeInfo {
    public TypeEnum typeEnum;
    public String typeName;

    public TypeInfo(TypeEnum typeEnum) {
        this.typeEnum = typeEnum;
        this.typeName = "";
    }
    public TypeInfo(TypeEnum typeEnum, String typeName) {
        this.typeEnum = typeEnum;
        this.typeName = typeName;
    }

    public boolean match(TypeInfo typeInfo) {
        return this.typeEnum == typeInfo.typeEnum && this.typeName.equals(typeInfo.typeName);
    }
}
