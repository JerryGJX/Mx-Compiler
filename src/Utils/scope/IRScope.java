package Utils.scope;

import AST.node.concretNode.ClassDefNode;
import AST.node.concretNode.FuncDefNode;
import IR.Type.PointerType;
import IR.Type.StructType;
import IR.Value.IRBasicBlock;
import IR.Value.IRDefine;
import IR.Value.User.Constant.GlobalValue.IRFunction;
import Utils.VarInfo;

import java.util.HashMap;

public class IRScope implements IRDefine {
    HashMap<String, Integer> VarMap = new HashMap<>();//记录当前scope下定义的var是第几次定义的（为了及编号）

    IRGlobalScope irGlobalScope;

    /*
    naming strategy:
    1. if the name hasn't been defined, you cannot use it
    2. if the name has been defined, you alloca the value to "valName.addr.defTime + 1".
    This secure the scope covering law
    3. if the name has been defined, you store the value to "valName.addr.defTime".
    4. if the name has been defined, you load the value from "valName.load.loadTime + 1"
     */

    public IRScope parentScope = null;


    public boolean inClass = false;
//    public ClassDefNode classNodeInfo = null;
    public StructType classTypeInfo = null;

    public boolean inFunc = false;
//    public FuncDefNode funcDefNodeInfo = null;
    public IRFunction irFunctionInfo = null;

    public boolean inLoop = false;

    public IRBasicBlock loopExitBlock = null;  //if breakStmt, jump to this block

    public IRBasicBlock loopContinueBlock = null;  //if continueStmt, jump to this block


//    public boolean inWhileLoop = false;
//    public IRBasicBlock whileLoopCondBlock = null;
//    public IRBasicBlock whileLoopBodyBlock = null;
//    public IRBasicBlock whileLoopExitBlock = null;


//    public boolean inForLoop = false;
//    public IRBasicBlock forLoopCondBlock = null;
//    public IRBasicBlock forLoopIncrBlock = null;
//    public IRBasicBlock forLoopBodyBlock = null;
//    public IRBasicBlock forLoopExitBlock = null;

//    public boolean inIfStmt = false;
//    public IRBasicBlock ifTrueBlock = null;
//    public IRBasicBlock ifFalseBlock = null;
//    public IRBasicBlock ifExitBlock = null;


    public IRScope(boolean _inClass, StructType _classTypeInfo, boolean _inFunc, IRFunction _irFunctionInfo, boolean _inLoop, IRBasicBlock _loopExitBlock, IRBasicBlock _loopContinueBlock, IRScope _parentScope) {
        inClass = _inClass;
        classTypeInfo = _classTypeInfo;
        inFunc = _inFunc;
        irFunctionInfo = _irFunctionInfo;
        inLoop = _inLoop;
        loopExitBlock = _loopExitBlock;
        loopContinueBlock = _loopContinueBlock;
        parentScope = _parentScope;
    }



    public void Init(IRGlobalScope _irGlobalScope) {
        this.irGlobalScope = _irGlobalScope;
        _irGlobalScope.valNameToInfo.forEach((name, info) -> {
            VarMap.put(name, info.defTime);
        });
    }


//    public void AddCurrentClass(StructType _classTypeInfo) {
//        classTypeInfo = _classTypeInfo;
//    }
//
//    public void AddCurrentFunc(FuncDefNode _funcDefNodeInfo, IRFunction _irFunctionInfo, IRBasicBlock _funcExitBlock, PointerType _retPtr) {
//        funcDefNodeInfo = _funcDefNodeInfo;
//        irFunctionInfo = _irFunctionInfo;
//        funcExitBlock = _funcExitBlock;
//        retPtr = _retPtr;
//    }


    public Integer GetIndexInCurrentScope(String _name) {
        if (VarMap.containsKey(_name)) {
            return VarMap.get(_name);
        } else {
            if (parentScope != null) {
                return parentScope.GetIndexInCurrentScope(_name);
            } else {
                return null;
            }
        }
    }


    public String GetLoadFromAddr(String _name) {
        Integer defTime = GetIndexInCurrentScope(_name);
        if (defTime == null) {
            System.err.println("[GetLoadFromAddr] Error: " + _name + " is not defined");
            System.exit(1);
        }
        var info = irGlobalScope.valNameToInfo.get(_name);
        if (info.isGlobal && defTime == 0) {
            return GLOBAL_PREFIX + _name; // @valName
        } else {
            return LOCAL_PREFIX + _name + ".addr." + defTime.toString();// %valName.addr.defTime
        }
    }

    public String GetLoadToAddr(String _name) {
        var info = irGlobalScope.valNameToInfo.get(_name);
        if(info== null){
            System.err.println("[GetLoadToAddr] Error: " + _name + " is not defined");
            System.exit(1);
        }
        info.loadTime++;
        return LOCAL_PREFIX + _name + ".load." + info.loadTime.toString();// %valName.load.loadTime
    }

    public String GetStoreToAddr(String _name) {
        return GetLoadFromAddr(_name);
    }

    public String GetVarAllocaAddr(String _name) {
        var info = irGlobalScope.valNameToInfo.get(_name);

        if(info == null){
            return LOCAL_PREFIX + _name + ".addr";// %valName.addr
        }else {
            return LOCAL_PREFIX + _name + ".addr." + info.defTime.toString();// %valName.addr.defTime
        }
    }


//    public void AddVar(String _name, VarInfo _info) {
//        if (accessibleVarMap.containsKey(_name)) {
//            System.err.println("Error: " + _name + " is already defined");
//            System.exit(1);
//        }
//        accessibleVarMap.put(_name, _info.defTime);
//        irGlobalScope.valNameToInfo.put(_name, _info);
//    }


    public void DefVarInScope(String _name) {
        if (VarMap.containsKey(_name)) {
            throw new RuntimeException("Error: " + _name + " is already defined in current scope");
        }
        if (!irGlobalScope.valNameToInfo.containsKey(_name)) {
            irGlobalScope.valNameToInfo.put(_name, new VarInfo(_name, false));
        }
        irGlobalScope.valNameToInfo.get(_name).defTime++;
        VarMap.put(_name, irGlobalScope.valNameToInfo.get(_name).defTime);
    }


}
