package AST.typeNode;


import IR.Type.*;


import java.util.HashMap;

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
//for LLVM
    public boolean isVarType() {
        return !isFunction;
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
}
