//package IR.Utils;
//
//import IR.Type.FunctionType;
//import IR.Type.VoidType;
//import IR.Value.IRBasicBlock;
//import IR.Value.IRDefine;
//import IR.Value.User.Constant.GlobalValue.IRFunction;
//import IR.Value.User.Constant.IRStrConstant;
//import IR.Value.User.Instruction.IRBrInst;
//import IR.Value.User.Instruction.IRRetInst;
//import Utils.VarInfo;
//
//public class Renamer {
//
//
//
//    private String GetLoadFromAddr(String _name) {
//        Integer defTime = currentIRScope.GetIndexInCurrentScope(_name);
//        if (defTime == null) throw new RuntimeException("[GetLoadFromAddr] Error: " + _name + " is not defined");
//        var info = irGlobalScope.valNameToInfo.get(_name);
//        if (info.isGlobal && defTime == 0) return GLOBAL_PREFIX + _name; // @valName
//        else return LOCAL_PREFIX + _name + ".addr." + defTime.toString();// %valName.addr.defTime
//    }
//
//    private String GetLoadToAddr(String _name, boolean _isRet) {
//        if (_isRet) return LOCAL_PREFIX + _name + ".load";
//        var info = irGlobalScope.valNameToInfo.get(_name);
//        if (info == null) throw new RuntimeException("[GetLoadFromAddr] Error: " + _name + " is not defined");
//        //warning
//        info.loadTime++;
//        return LOCAL_PREFIX + _name + ".load." + info.loadTime.toString();// %valName.load.loadTime
//    }
//
//    private String GetStoreToAddr(String _name) {
//        return GetLoadFromAddr(_name);
//    }
//
//    private String GetVarAllocaAddr(String _name) {
//        var info = irGlobalScope.valNameToInfo.get(_name);
//        if (info == null) return LOCAL_PREFIX + _name + ".addr";// %valName.addr
//        else return LOCAL_PREFIX + _name + ".addr." + info.defTime.toString();// %valName.addr.defTime
//    }
//
//    private String GetStrIdentifier(String _str) {
//        if (projectIRModule.IRStrConstantMap.containsKey(_str))
//            return projectIRModule.IRStrConstantMap.get(_str).strIdentifier;
//        else {
//            String strId = GLOBAL_PREFIX + "strConst." + projectIRModule.IRStrConstantMap.size();
//            var strConst = new IRStrConstant(strId, _str);
//            projectIRModule.IRStrConstantMap.put(_str, strConst);
//            return strId;
//        }
//    }
//
//    private void DefVarInScope(String _name) {
//        if (currentIRScope.VarMap.containsKey(_name))
//            throw new RuntimeException("Error: " + _name + " is already defined in current scope");
//        if (!irGlobalScope.valNameToInfo.containsKey(_name))
//            irGlobalScope.valNameToInfo.put(_name, new VarInfo(_name, 1, 0, false));
//
//        irGlobalScope.valNameToInfo.get(_name).defTime++;
//        currentIRScope.VarMap.put(_name, irGlobalScope.valNameToInfo.get(_name).defTime);
//    }//you should call this func each time define a variable in current scope
//
//    //globalVar init
//    private IRFunction GenerateInitFunc() {
//        FunctionType initFuncType = new FunctionType(new VoidType(), IRDefine.LLVM_INIT_FUNCTION);
//        IRFunction initFunc = new IRFunction("init", initFuncType, false);
//        initFunc.entryBlock = new IRBasicBlock(IRDefine.LLVM_INIT_FUNCTION + ".entry");
//        //construct func.exit block
//        initFunc.exitBlock = new IRBasicBlock(IRDefine.LLVM_INIT_FUNCTION + ".exit");
//        //jump to exit block
//        var brInst = new IRBrInst(initFunc.exitBlock, initFunc.entryBlock);
//        initFunc.entryBlock.AddTerminator(brInst);
//        //return void
//        var retInst = new IRRetInst(initFunc.returnType, null, initFunc.exitBlock);
//        initFunc.exitBlock.AddTerminator(retInst);
//        return initFunc;
//    }
//
//
//}
