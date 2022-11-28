package IR;

import AST.node.concretNode.BuiltInFuncDefNode.BuiltInFuncDefNode;
import AST.node.concretNode.ClassDefNode;
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

import java.util.HashMap;

public class IRModule implements IRDefine {
    public String fileName;

    public IRFunction initFunc;
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
            if (!name.equals("int") && !name.equals("bool") && !name.equals("string")) {
                structTypeMap.put(name, new StructType(name));
                classDefNodeMap.put(name, classDefNode);
            }
        });
        //填充类的成员变量
        _globalScope.classTable.forEach((className, classDefNode) -> {
            if (!className.equals("int") && !className.equals("bool") && !className.equals("string")) {
                for (int i = 0; i < classDefNode.memberVarNameList.size(); i++) {
                    String memberVarName = classDefNode.memberVarNameList.get(i);
                    var varDefUnitNode = classDefNode.memberVarMap.get(memberVarName);
                    var varType = typeTransLater.TranslateVarType(varDefUnitNode.varType);
                    structTypeMap.get(className).addMemberType(varType);
                }
            }
        });
        //init translator
        typeTransLater = new TypeTransLater(structTypeMap);
        //add function(including memberFunc and Constructor)
        _globalScope.funcTable.forEach((name, funcDefNode) -> {
            boolean isBuiltIn = funcDefNode instanceof BuiltInFuncDefNode;
            if (!isBuiltIn) System.out.println("funcName: " + name);
            if (funcDefNode == null)
                throw new RuntimeException("[addFunc] " + "funcName: " + name + " funcDefNode is null");
            var IRFunc = transFuncDef(funcDefNode, funcDefNode.isMember, funcDefNode.className, isBuiltIn);
            IRFunctionMap.put(IRFunc.funcName, IRFunc);
        });
        //add global variable
        _globalScope.varTable.forEach((name, varDefUnitNode) -> {
            IRGlobalVariableMap.put(name, new GlobalVariable(name, typeTransLater.TranslateVarType(varDefUnitNode.varType)));
        });
    }

    public BasicType TranslateVarType(Type _type) {
        return typeTransLater.TranslateVarType(_type);
    }

    public StructType TranslateByString(String _className) {
        if (structTypeMap.containsKey(_className)) {
            return structTypeMap.get(_className);
        } else throw new RuntimeException("[TranslateByString] " + "className: " + _className + " not found");
    }

    public IRFunction transFuncDef(FuncDefNode _funcDefNode, boolean _isMemberFunc, String _className, boolean _isBuiltIn) {
        BasicType returnType;
        if (_funcDefNode.returnType == null) returnType = new VoidType();//默认构造函数
        else returnType = typeTransLater.TranslateVarType(_funcDefNode.returnType);

        FunctionType functionType = new FunctionType(returnType, _funcDefNode.funcName);
        IRFunction irFunction = new IRFunction(_funcDefNode.funcName, functionType, _isBuiltIn);
        if (_isMemberFunc) {
            functionType.funcName = irFunction.funcName = _className + "." + _funcDefNode.funcName;
            var this_ptr_type = new PointerType(TranslateByString(_className));
            functionType.addParameterType(this_ptr_type);
            irFunction.addArg(this_ptr_type, "this");
        }
        _funcDefNode.argList.forEach(paramDefNode -> {
            var argType = TranslateVarType(paramDefNode.varType);
            functionType.addParameterType(argType);
            irFunction.addArg(argType, paramDefNode.varName);
        });
//        if (!_isBuiltIn) System.out.println("funcName: " + irFunction.funcName);
        return irFunction;
    }
    //注：默认构造已经在semantic中加入了

    public Integer GetMemberVarRank(String _className, String _memberVarName) {
        var classDefNode = classDefNodeMap.get(_className);
        Integer rank = 0;
        for (var memberVarName : classDefNode.memberVarNameList) {
            if (memberVarName.equals(_memberVarName))
                return rank;
            rank++;
        }
        throw new RuntimeException("GetMemberVarRank error");
    }

    public String toString() {
        return "";
    }

    String GenerateComment(String _comment) {
        return "\n; " + _comment + "\n";
    }

    public void TestPrint() {
        StringBuilder projectStr = new StringBuilder(GenerateComment("IRModule = " + fileName));

        projectStr.append(GenerateComment("BuiltinFunc"));
        projectStr.append(GenerateComment("GlobalStrDef"));
        for (var strConstant : IRStrConstantMap.values()) {
            var str = strConstant.strIdentifier + " = " + strConstant.valueType.toString() + " " + strConstant.toString() + ", align " + STRING_ALIGN;
            projectStr.append(str).append("\n");
        }

        projectStr.append(GenerateComment("GlobalVar"));
        for (var globalVar : IRGlobalVariableMap.values()) {
            var str = globalVar.getIdentifier() + " = global " + globalVar.valueType.toString() + " " + new IRZeroInitConstant(globalVar.valueType).toString() + ", align " + POINTER_SIZE;
            projectStr.append(str).append("\n");
        }

        projectStr.append(GenerateComment("StructDef"));
        for (var structType : structTypeMap.values()) {
            if (structType.classId.equals("class.string")) continue;
            projectStr.append(structType.PrintStructType()).append("\n");
        }

        projectStr.append(GenerateComment("GlobalFunc"));
        projectStr.append(initFunc.toString()).append("\n");
        for (var func : IRFunctionMap.values()) {
            if (func.isBuiltIn || func.funcName.equals("main")) continue;
            //todo
            projectStr.append(func.toString()).append("\n");
        }

        projectStr.append(GenerateComment("MainFunc"));
        var mainFunc = IRFunctionMap.get("main");
        projectStr.append(mainFunc.toString()).append("\n");

        System.out.println(projectStr.toString());
    }
}
