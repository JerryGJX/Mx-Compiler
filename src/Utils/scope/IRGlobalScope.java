package Utils.scope;

import Utils.VarInfo;

import java.util.HashMap;

public class IRGlobalScope {
    public Integer currentFuncCnt = 0;
    public Integer currentWhileLoopCnt = 0;
    public Integer currentForLoopCnt = 0;
    public Integer currentIfStmtCnt = 0;

    public HashMap<String, VarInfo> valNameToInfo = new HashMap<>();

    public IRGlobalScope(GlobalScope _globalScope){
        _globalScope.varTable.forEach((key, value) -> {
            valNameToInfo.put(key, new VarInfo(key, value.isGlobal));
        });
    }





}
