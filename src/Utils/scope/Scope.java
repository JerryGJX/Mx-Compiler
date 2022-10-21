package Utils.scope;

import AST.typeNode.VarTypeNode;

import java.util.HashMap;
import java.util.Map;

public class Scope {
    public Map<String, VarTypeNode> varTable;
    public Scope parent;

    public Scope(Scope _parent){
        varTable = new HashMap<>();
        this.parent = _parent;
    }

    public VarTypeNode GetVarType(String identifier){
        if(varTable.containsKey(identifier)) return varTable.get(identifier);
        else if(this.parent != null) return parent.GetVarType(identifier);
        else return null;
    }

    public boolean contains_Variable(String identifier){
        return varTable.containsKey(identifier);
    }

    public void define_Variable(String identifier,VarTypeNode _type){
        varTable.put(identifier, _type);
    }

}
