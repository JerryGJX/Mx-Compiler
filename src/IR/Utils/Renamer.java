package IR.Utils;

import Utils.VarInfo;

public class Renamer {



    //renaming strategy
//    public String GetLoadFromAddr(String _name) {
//        Integer defTime = currentIRScope.GetIndexInCurrentScope(_name);
//        if (defTime == null) throw new RuntimeException("[GetLoadFromAddr] Error: " + _name + " is not defined");
//        var info = irGlobalScope.valNameToInfo.get(_name);
//        if (info.isGlobal && defTime == 0) return GLOBAL_PREFIX + _name; // @valName
//        else return LOCAL_PREFIX + _name + ".addr." + defTime.toString();// %valName.addr.defTime
//    }
//    public String GetLoadToAddr(String _name) {
//        var info = irGlobalScope.valNameToInfo.get(_name);
//        if (info == null) throw new RuntimeException("[GetLoadFromAddr] Error: " + _name + " is not defined");
//        //warning
//        info.loadTime++;
//        return LOCAL_PREFIX + _name + ".load." + info.loadTime.toString();// %valName.load.loadTime
//    }
//    public String GetStoreToAddr(String _name) {
//        return GetLoadFromAddr(_name);
//    }
//    public String GetVarAllocaAddr(String _name) {
//        var info = irGlobalScope.valNameToInfo.get(_name);
//        if (info == null) return LOCAL_PREFIX + _name + ".addr";// %valName.addr
//        else return LOCAL_PREFIX + _name + ".addr." + info.defTime.toString();// %valName.addr.defTime
//    }
//    public void DefVarInScope(String _name) {
//        if (currentIRScope.VarMap.containsKey(_name))
//            throw new RuntimeException("Error: " + _name + " is already defined in current scope");
//        if (!irGlobalScope.valNameToInfo.containsKey(_name))
//            irGlobalScope.valNameToInfo.put(_name, new VarInfo(_name, false));
//        else {
//            irGlobalScope.valNameToInfo.get(_name).defTime++;
//            currentIRScope.VarMap.put(_name, irGlobalScope.valNameToInfo.get(_name).defTime);
//        }
//    }
}
