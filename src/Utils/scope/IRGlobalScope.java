package Utils.scope;

import Utils.VarInfo;

import java.util.HashMap;

public class IRGlobalScope {
//    public Integer currentFuncCnt = 0;
//    public Integer currentWhileLoopCnt = 0;
//    public Integer currentForLoopCnt = 0;
//    public Integer currentIfStmtCnt = 0;

    public HashMap<String, Integer> valNameToInfo = new HashMap<>();

    public IRGlobalScope(GlobalScope _globalScope) {
        _globalScope.varTable.forEach((key, value) -> {
            valNameToInfo.put(key, 0);
        });
    }

    public void addVarDef(String varName) {
        if (valNameToInfo.containsKey(varName)) {
            valNameToInfo.put(varName, valNameToInfo.get(varName) + 1);
        } else {
            valNameToInfo.put(varName, 1);
        }
    }
}
