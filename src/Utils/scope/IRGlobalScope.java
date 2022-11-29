package Utils.scope;

//import Utils.VarInfo;

import java.util.HashMap;

public class IRGlobalScope extends IRScope{
    public IRGlobalScope(GlobalScope _globalScope) {
        super();
        _globalScope.varTable.forEach((name, var) -> {
            VarInfoMap.put(name, new VarInfo(name, 0, true));
        });
    }
}
