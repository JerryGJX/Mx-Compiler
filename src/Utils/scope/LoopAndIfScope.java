package Utils.scope;

import AST.typeNode.BasicVarTypeNode;
import AST.typeNode.VarTypeNode;

import java.util.HashMap;

public class LoopAndIfScope extends BaseScope{
    private HashMap<String, VarTypeNode> varTable;


    public LoopAndIfScope(BaseScope _parentScope) {
        super(_parentScope);
        varTable = new HashMap<>();
    }

    public Boolean ContainVar(String _identifier) {
        return varTable.containsKey(_identifier);
    }

    public BasicVarTypeNode getVarType(String _identifier) {
        if (varTable.containsKey(_identifier)) {
            return varTable.get(_identifier);
        } else if (parentScope() instanceof LoopAndIfScope) {
            return ((LoopAndIfScope) parentScope()).getVarType(_identifier);
        } else if (parentScope() instanceof FuncBodyScope) {
            return ((FuncBodyScope) parentScope()).getVarType(_identifier);
        }else if (parentScope() instanceof ClassScope) {
            return ((ClassScope) parentScope()).getVarType(_identifier);
        }else if (parentScope() instanceof GlobalScope) {
            return ((GlobalScope) parentScope()).getVarType(_identifier);
        }else return null;
    }

    public void defineVariable(String _identifier, VarTypeNode _varTypeNode) {
        varTable.put(_identifier,  _varTypeNode);
    }

    @Override
    public BaseScope parentScope() {
        return this.parentScope;
    }
}
