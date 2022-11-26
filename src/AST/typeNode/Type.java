package AST.typeNode;


import IR.Type.*;


import java.util.HashMap;

import static IR.Type.PointerType.RecursiveBuildPointer;

public class Type {
    public String typeName;//不含维数
    public int dimSize;
    public boolean isClass;
    public boolean isFunction;



    public Type(Type _type) {
        this.typeName = _type.typeName;
        this.dimSize = _type.dimSize;
        this.isClass = _type.isClass;
        this.isFunction = _type.isFunction;
    }


    public Type(String _typeName, Integer _dimSize, Boolean _isFunction) {
        this.typeName = _typeName;
        this.dimSize = _dimSize;
        if (!typeName.equals("void")
                && !typeName.equals("int")
                && !typeName.equals("bool")
                && !typeName.equals("string")
                && !typeName.equals("null")
                && !typeName.equals("this"))
            isClass = true;

        this.isFunction = _isFunction;
    }


    public Boolean Match(Type _aimType) {
        if (_aimType == null) return false;
        return this.typeName.equals(_aimType.typeName) && this.dimSize == _aimType.dimSize;
    }

    public String PrintType() {
        String ret = "";
        ret += "[typeName]: " + typeName + "; [dimSize]: " + dimSize + "; [isClass]: " + isClass + "; [isFunction]: " + isFunction;
        return ret;
    }

    public Boolean NullAssignable() {
        return this.isClass || this.dimSize > 0;
    }

    public BasicType toIRType(HashMap<String, StructType> typeMap) {
        BasicType baseType;
        if (typeName.equals("int")) {
            baseType = new IntegerType(32);
        } else if (typeName.equals("bool")) {
            baseType = new BoolType();
        } else if (typeName.equals("void")) {
            return new VoidType();
//            throw new RuntimeException("void type cannot be member type of struct");
        } else if (!typeMap.containsKey(this.typeName)) {
            throw new RuntimeException("[IR] Type not found: " + typeName);
        } else {
            baseType = typeMap.get(typeName);
        }

        if (dimSize == 0) {
            return baseType;
        } else {
            return RecursiveBuildPointer(baseType, dimSize);
        }
    }


}
