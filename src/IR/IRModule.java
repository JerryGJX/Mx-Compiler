package IR;

import AST.node.concretNode.ClassDefNode;
import AST.node.concretNode.FuncDefNode;
import IR.Type.*;
import IR.Value.IRDefine;
import IR.Value.IRValue;
import IR.Value.User.Constant.GlobalValue.IRFunction;
import Utils.scope.GlobalScope;

import java.util.HashMap;

//import static jdk.internal.logger.DefaultLoggerFinder.SharedLoggers.system;

public class IRModule implements IRDefine {
    public String fileName;
    public HashMap<String, IRFunction> IRFunctionMap = new HashMap<>();
    public HashMap<String, StructType> structTypeMap = new HashMap<>();

//    public HashMap<String, Integer> classDefNodeMap = new HashMap<>();
    public HashMap<String, IRValue> IRGlobalVariableMap = new HashMap<>();


    public IRModule(String _fileName) {
        this.fileName = _fileName;
    }

    public void Init(GlobalScope _globalScope) {

        _globalScope.classTable.forEach((name, classDefNode) -> {
            if (!name.equals("int") && !name.equals("bool")){
                structTypeMap.put(name, new StructType(name));
            }

        });

        _globalScope.classTable.forEach((className, classDefNode) -> {
            if (!className.equals("int") && !className.equals("bool")){
                classDefNode.memberVarNameList.forEach(memberVarName -> {
                    var varDefUnitNode = classDefNode.memberVarMap.get(memberVarName);
                    var varType = new PointerType(varDefUnitNode.varType.toIRType(structTypeMap), 1);
                    structTypeMap.get(className).addMemberType(varType);
                });
//                classDefNode.memberVarMap.forEach((memberName, varDefUnitNode) -> {
//                    structTypeMap.get(className).addMemberType(new PointerType(varDefUnitNode.varType.toIRType(structTypeMap), 1));
//                });
            }
                //todo: may have recursive problem
            classDefNode.memberFuncMap.forEach((memberFuncName, funcDefNode) -> {
                IRFunctionMap.put(memberFuncName, transFuncDef(funcDefNode, true, className));
            });
        });

        _globalScope.funcTable.forEach((name, funcDefNode) -> {
            IRFunctionMap.put(name, transFuncDef(funcDefNode, false, null));
        });

        _globalScope.varTable.forEach((name, varDefUnitNode) -> {
            IRGlobalVariableMap.put(name, new IRValue(name, varDefUnitNode.varType.toIRType(structTypeMap)));
        });
    }

    public IRFunction transFuncDef(FuncDefNode _funcDefNode, boolean _isMemberFunc, String _className) {


//        System.out.println("[irModule] transFuncDef : " + _funcDefNode.funcName);

        FunctionType functionType = new FunctionType(_funcDefNode.returnType.toIRType(structTypeMap), _funcDefNode.funcName);
        IRFunction irFunction = new IRFunction(_funcDefNode.funcName,functionType);
        if (_isMemberFunc) {
            functionType.funcName = _className + "." + _funcDefNode.funcName;
            var this_ptr_type = new PointerType(structTypeMap.get(_className), 1);
            functionType.addParameterType(this_ptr_type);
            irFunction.addArg(this_ptr_type,"this");
        }
        _funcDefNode.argList.forEach(paramDefNode -> {
            var argType = paramDefNode.varType.toIRType(structTypeMap);
            functionType.addParameterType(argType);
            irFunction.addArg(argType,paramDefNode.varName);
        });
        return irFunction;
    }
    //注：默认构造已经在semantic中加入了


    public Integer GetMemberVarRank(String _className, String _memberVarName) {
        var classDefNode = classDefNodeMap.get(_className);
        Integer rank = 0;
        for (var memberVarName : classDefNode.memberVarMap.keySet()) {
            if (memberVarName.equals(_memberVarName))
                return rank;
            rank++;
        }
        return null;
    }


    public String toString() {
        return "";
    }
}
