package Utils;

import AST.*;
import java.util.HashMap;
import java.util.Map;

public class Scope {
    private Scope parentScope;
    public Map<String, Integer> VariableTable;

    public Scope(Scope parent) {
        this.parentScope = parent;
        this.VariableTable = new HashMap<>();
    }
    public Integer getVarType(String varName) {
        if (VariableTable.containsKey(varName)) return VariableTable.get(varName);
        else if (parentScope != null) return parentScope.getVarType(varName);
        else return null;
    }

    public boolean isVarDefined(String varName) {
        return VariableTable.containsKey(varName);
    }

    public void defineVar(String varName, Integer varType) {
        VariableTable.put(varName, varType);
    }
}
