package IR;

import AST.node.concretNode.BuiltInFuncDefNode.BuiltInFuncDefNode;
import AST.node.concretNode.ClassDefNode;
import AST.node.concretNode.ConstructorDefNode;
import AST.node.concretNode.FuncDefNode;
import AST.typeNode.Type;
import IR.Type.*;
import IR.Value.IRDefine;
import IR.Value.User.Constant.GlobalValue.GlobalVariable;
import IR.Value.User.Constant.GlobalValue.IRFunction;
import IR.Value.User.Constant.IRStrConstant;
import IR.Value.User.Constant.IRZeroInitConstant;
import IR.Utils.TypeTransLater;
import Utils.scope.GlobalScope;

import java.util.ArrayList;
import java.util.HashMap;

public class IRModule implements IRDefine {
    public String fileName;
    public IRFunction initFunc;

    public ArrayList<FunctionType> BuiltInFuncList = new ArrayList<>();
    public HashMap<String, IRFunction> IRFunctionMap = new HashMap<>();
    private final HashMap<String, StructType> structTypeMap = new HashMap<>();
    public HashMap<String, ClassDefNode> classDefNodeMap = new HashMap<>();
    public HashMap<String, GlobalVariable> IRGlobalVariableMap = new HashMap<>();
    public HashMap<String, IRStrConstant> IRStrConstantMap = new HashMap<>();
    private TypeTransLater typeTransLater;


    public IRModule(String _fileName) {
        this.fileName = _fileName;
    }

    public void Init(GlobalScope _globalScope, IRFunction _initFunc) {
        this.initFunc = _initFunc;
        //建立类
        _globalScope.classTable.forEach((name, classDefNode) -> {
            if (!name.equals("int") && !name.equals("bool")
//                    && !name.equals("string")
            ) {
                structTypeMap.put(name, new StructType(name));
                classDefNodeMap.put(name, classDefNode);
            }
        });
        //init translator
        typeTransLater = new TypeTransLater(structTypeMap);

        //填充类的成员变量
        _globalScope.classTable.forEach((className, classDefNode) -> {

            if (!className.equals("int") && !className.equals("bool") && !className.equals("string")) {
                for (int i = 0; i < classDefNode.memberVarNameList.size(); i++) {
                    String memberVarName = classDefNode.memberVarNameList.get(i);
                    var varDefUnitNode = classDefNode.memberVarMap.get(memberVarName);
                    var varType = typeTransLater.TranslateVarType(varDefUnitNode.varType);
                    //to be checked
                    if (varType instanceof BoolType) varType = new memBoolType();
                    structTypeMap.get(className).addMember(memberVarName, varType);
                }
                classDefNode.memberFuncMap.forEach((funcName, funcDefNode) -> {
                    var IRFunc = transFuncDef(funcDefNode, translateByString(className));
                    IRFunctionMap.put(className + "." + funcName, IRFunc);
                });
            }

        });
        //add function(including Constructor but no memberFunc)
        _globalScope.funcTable.forEach((name, funcDefNode) -> {
//            System.out.println("add function: " + name);
            boolean isBuiltIn = funcDefNode instanceof BuiltInFuncDefNode;
            if (funcDefNode == null)
                throw new RuntimeException("[addFunc] " + "funcName: " + name + " funcDefNode is null");
            if (!isBuiltIn) {
                var IRFunc = transFuncDef(funcDefNode, translateByString(funcDefNode.className));
                IRFunctionMap.put(IRFunc.funcName, IRFunc);
            }
        });
        //todo: add .size()

        //add global variable
        _globalScope.varTable.forEach((name, varDefUnitNode) -> {
            var varType=typeTransLater.TranslateAllocaType(varDefUnitNode.varType);
//            if(varType instanceof BoolType) varType=new memBoolType();
            IRGlobalVariableMap.put(name, new GlobalVariable(name, varType));
        });


        //add builtIn Func
        BuiltInFuncList.add(new FunctionType(INT8Star, "_malloc", INT32));
        BuiltInFuncList.add(new FunctionType(INT8Star, "_str_concat", INT8Star, INT8Star));
        BuiltInFuncList.add(new FunctionType(boolType, "_str_eq", INT8Star, INT8Star));
        BuiltInFuncList.add(new FunctionType(boolType, "_str_ne", INT8Star, INT8Star));
        BuiltInFuncList.add(new FunctionType(boolType, "_str_ult", INT8Star, INT8Star));
        BuiltInFuncList.add(new FunctionType(boolType, "_str_ugt", INT8Star, INT8Star));
        BuiltInFuncList.add(new FunctionType(boolType, "_str_ule", INT8Star, INT8Star));
        BuiltInFuncList.add(new FunctionType(boolType, "_str_uge", INT8Star, INT8Star));

        BuiltInFuncList.add(new FunctionType(voidType, "print", INT8Star));
        BuiltInFuncList.add(new FunctionType(voidType, "println", INT8Star));
        BuiltInFuncList.add(new FunctionType(voidType, "printInt", INT32));
        BuiltInFuncList.add(new FunctionType(voidType, "printlnInt", INT32));
        BuiltInFuncList.add(new FunctionType(INT8Star, "getString"));
        BuiltInFuncList.add(new FunctionType(INT32, "getInt"));
        BuiltInFuncList.add(new FunctionType(INT8Star, "toString", INT32));
        BuiltInFuncList.forEach(funcType -> {
            var irFunc = new IRFunction(funcType, funcType.funcName, null, true, funcType.parameterTypeList);
            IRFunctionMap.put(funcType.funcName, irFunc);
        });
        BuiltInFuncList.add(new FunctionType(INT8Star, "_str_substring", INT8Star, INT32, INT32));
        BuiltInFuncList.add(new FunctionType(INT32, "_str_length", INT8Star));
        BuiltInFuncList.add(new FunctionType(INT32, "_str_ord", INT8Star, INT32));
        BuiltInFuncList.add(new FunctionType(INT32, "_str_parseInt", INT8Star));
        BuiltInFuncList.forEach(funcType -> {
            IRFunctionMap.putIfAbsent(funcType.funcName, new IRFunction(funcType, funcType.funcName, stringType, true, funcType.parameterTypeList));
        });

    }

    public BasicType translateVarType(Type _type) {
        return typeTransLater.TranslateVarType(_type);
    }

    public BasicType translateAllocaType(Type _type) {
        return typeTransLater.TranslateAllocaType(_type);
    }

    public StructType translateByString(String _className) {
        if (_className == null) return null;
        if (structTypeMap.containsKey(_className)) {
            return structTypeMap.get(_className);
        } else throw new RuntimeException("[TranslateByString] " + "className: " + _className + " not found");
    }

    public IRFunction transFuncDef(FuncDefNode _funcDefNode, StructType _classType) {
        BasicType returnType;
        if (_funcDefNode.returnType == null || _funcDefNode instanceof ConstructorDefNode) returnType = new VoidType();//默认构造函数
        else returnType = typeTransLater.TranslateVarType(_funcDefNode.returnType);
        FunctionType functionType = new FunctionType(returnType, _funcDefNode.funcName);
        IRFunction irFunction = new IRFunction(functionType, _funcDefNode.funcName, _classType, false, null);
        if (_classType != null) {
            functionType.funcName = irFunction.funcName = _classType.classId + "." + _funcDefNode.funcName;
            var this_ptr_type = new PointerType(_classType);
//            System.out.println("[Warning] " + this_ptr_type.toString());
            functionType.addParameterType(this_ptr_type);
            irFunction.argNameList.add(THIS_POINTER);
        }
        _funcDefNode.argList.forEach(paramDefNode -> {
            var argType = translateVarType(paramDefNode.varType);
            functionType.addParameterType(argType);
            irFunction.argNameList.add(paramDefNode.varName);
        });
        irFunction.argTypeList = functionType.parameterTypeList;
        return irFunction;
    }
    //注：默认构造已经在semantic中加入了

    public IRFunction getIRFunction(String _funcName) {
        if (IRFunctionMap.containsKey(_funcName)) {
            return IRFunctionMap.get(_funcName);
        } else throw new RuntimeException("[getIRFunction] " + "funcName: " + _funcName + " not found");
    }

    public Integer getMemberVarRank(String _className, String _memberVarName) {
        var classDefNode = classDefNodeMap.get(_className);
        Integer rank = 0;
        for (var memberVarName : classDefNode.memberVarNameList) {
            if (memberVarName.equals(_memberVarName))
                return rank;
            rank++;
        }
        throw new RuntimeException("GetMemberVarRank error");
    }

    public BasicType getMemberVarType(String _className, String _memberVarName) {
        var classDefNode = classDefNodeMap.get(_className);
        for (var memberVarName : classDefNode.memberVarNameList) {
            if (memberVarName.equals(_memberVarName))
                return translateVarType(classDefNode.memberVarMap.get(memberVarName).varType);
        }
        throw new RuntimeException("GetMemberVarType error");
    }

    public String toString() {
        return "";
    }

    String generateComment(String _comment) {
        return "\n; " + _comment + "\n";
    }

    public void testPrint() {
        StringBuilder projectStr = new StringBuilder(generateComment("IRModule = " + fileName));

        projectStr.append(generateComment("BuiltinFunc"));
        BuiltInFuncList.forEach(funcType -> {
            projectStr.append("declare ").append(funcType.toString()).append("\n");
        });

        projectStr.append(generateComment("GlobalStrDef"));
        for (var strConstant : IRStrConstantMap.values()) {
            var str = strConstant.getIdentifier() + " = " + "private unnamed_addr constant" + " " + ((PointerType)(strConstant.valueType)).Dereference().toString() + " " + strConstant.toString() ;
            //+ ", align " + STRING_ALIGN;
            projectStr.append(str).append("\n");
        }

        projectStr.append(generateComment("GlobalVar"));
        for (var globalVar : IRGlobalVariableMap.values()) {
            var str = globalVar.getIdentifier() + " = global " + globalVar.pointedType().toString() + " " + new IRZeroInitConstant(globalVar.pointedType()).toString() ;
            //+ ", align " + POINTER_SIZE;
            projectStr.append(str).append("\n");
        }

        projectStr.append(generateComment("StructDef"));
        for (var structType : structTypeMap.values()) {
            if (structType.classId.equals("string")) continue;
            projectStr.append(structType.PrintStructType()).append("\n");
        }

        projectStr.append(generateComment("GlobalFunc"));
        projectStr.append(initFunc.toString()).append("\n");
        for (var func : IRFunctionMap.values()) {
            if (func.isBuiltIn || func.funcName.equals("main")) continue;
            //todo
            projectStr.append(func.toString()).append("\n");
        }

        projectStr.append(generateComment("MainFunc"));
        var mainFunc = IRFunctionMap.get("main");
        projectStr.append(mainFunc.toString()).append("\n");

        System.out.println(projectStr.toString());
    }
}
