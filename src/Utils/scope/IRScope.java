package Utils.scope;

import IR.Type.StructType;
import IR.Value.IRBasicBlock;
import IR.Value.IRDefine;
import IR.Value.IRValue;
import IR.Value.User.Constant.GlobalValue.IRFunction;
import IR.Value.User.Instruction.IRInstruction;

import java.util.HashMap;

public class IRScope implements IRDefine {
    //    public static class VarInfo{
//        String rawName;
//        Integer index;
//        boolean isGlobal;
//        public VarInfo(String _rawName,Integer _index,boolean _isGlobal){
//            rawName = _rawName;
//            index = _index;
//            isGlobal = _isGlobal;
//        }
//        public String VarId(){
//            return rawName + index.toString();
//        }
//    }
    public HashMap<String, String> rawToIdMap = new HashMap<>();//记录当前scope下定义的var的id (e.g. A-> A.1)

//    IRGlobalScope irGlobalScope;

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

    public StructType currentClassType = null;

    public boolean inFunc = false;

    public IRFunction currentIRFunction = null;

    public boolean inLoop = false;

    public IRBasicBlock loopExitBlock = null;  //if breakStmt, jump to this block

    public IRBasicBlock loopContinueBlock = null;  //if continueStmt, jump to this block

    public IRInstruction thisPtr = null;


    public IRScope() {
        inClass = false;
        currentClassType = null;
        inFunc = false;
        currentIRFunction = null;
        inLoop = false;
        loopExitBlock = null;
        loopContinueBlock = null;
        parentScope = null;
    }

    public IRScope(boolean _inClass, StructType _classTypeInfo, boolean _inFunc, IRFunction _irFunctionInfo, boolean _inLoop, IRBasicBlock _loopExitBlock, IRBasicBlock _loopContinueBlock, IRScope _parentScope) {
        inClass = _inClass;
        currentClassType = _classTypeInfo;
        inFunc = _inFunc;
        currentIRFunction = _irFunctionInfo;
        inLoop = _inLoop;
        loopExitBlock = _loopExitBlock;
        loopContinueBlock = _loopContinueBlock;
        parentScope = _parentScope;
    }

    public String GetVarId(String _rawName) {
        if (rawToIdMap.containsKey(_rawName)) {
            return rawToIdMap.get(_rawName);
        } else {
            if (parentScope != null) {
                return parentScope.GetVarId(_rawName);
            } else {
                return null;
            }
        }
    }


    public IRValue GetThisPtr(){
        if(this.thisPtr == null){
            return parentScope.GetThisPtr();
        }else {
            return thisPtr;
        }
    }
}