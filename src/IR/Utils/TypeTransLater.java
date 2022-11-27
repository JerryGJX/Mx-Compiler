package IR.Utils;

import AST.typeNode.Type;
import IR.Type.*;

import java.util.HashMap;

public class TypeTransLater {
    public static final BasicType stringType = new PointerType(new IntegerType(8));

    public static final BasicType i32Type = new IntegerType(32);
    public static final BasicType boolType = new BoolType();
    public static final BasicType allocBoolType = new IntegerType(8);
    public static final BasicType voidType = new VoidType();

    private final HashMap<String, StructType> typeMap;


    public TypeTransLater(HashMap<String, StructType> _typeMap) {
        this.typeMap = _typeMap;
    }
    public BasicType TransLateBuiltinType(Type _astType) {
        return switch (_astType.typeName) {
            case "int" -> i32Type;
            case "bool" -> boolType;
            case "string" -> stringType;
            case "void" -> voidType;
            default -> new PointerType(typeMap.get(_astType.typeName));
        };
    }
    private BasicType RecursiveBuildPointer(BasicType _basicType, int _dimSize) {
        if (_dimSize == 0) return _basicType;
        return new PointerType(RecursiveBuildPointer(_basicType, _dimSize - 1));
    }
    public BasicType TranslateVarType(Type _astType) {
        BasicType ans;
        if (_astType.isFunction) throw new RuntimeException("Function type cannot be translated by TranslateVarType");
        else ans = TransLateBuiltinType(_astType);
        ans = RecursiveBuildPointer(ans, _astType.dimSize);
        return ans;
    }
    public BasicType TranslateAllocaType(Type _astType) {
        BasicType ans;
        if (_astType.isFunction)
            throw new RuntimeException("Function type cannot be translated by TranslateAllocaType");
        if (_astType.typeName.equals("bool")) ans = allocBoolType;
        else ans = TransLateBuiltinType(_astType);

        ans = RecursiveBuildPointer(ans, _astType.dimSize);
        return ans;
    }

}
