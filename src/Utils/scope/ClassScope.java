package Utils.scope;

import AST.node.concretNode.FuncDefNode;
import AST.typeNode.VarTypeNode;
import Utils.Position;
import Utils.error.semanticError;

import java.util.HashMap;

public class ClassScope extends BaseScope {
    private HashMap<String, VarTypeNode> varTable;
    private HashMap<String, FuncDefNode> funcTable;

    public ClassScope(BaseScope _parentScope) {
        super(_parentScope);
        varTable = new HashMap<>();
        funcTable = new HashMap<>();
    }

    public Boolean ContainVar(String _identifier) {
        return varTable.containsKey(_identifier);
    }

    public Boolean ContainFunc(String _identifier) {
        return funcTable.containsKey(_identifier);
    }

    public VarTypeNode getVarType(String _identifier) {
       if (varTable.containsKey(_identifier)) {
                return varTable.get(_identifier);
            } else if (parentScope() instanceof FuncBodyScope) {
                throw new semanticError("class scope under func and loop scope" ,new Position());
            }else if (parentScope() instanceof ClassScope) {
           throw new semanticError("class scope under class scope" ,new Position());
            }else if (parentScope() instanceof GlobalScope) {
           return ((GlobalScope) parentScope()).getVarType(_identifier);
            }else return null;
    }

    public FuncDefNode getFuncDef(String _identifier) {
        if (funcTable.containsKey(_identifier)) {
            return funcTable.get(_identifier);
        } else if (parentScope() instanceof FuncBodyScope) {
            throw new semanticError("class scope under func and loop scope" ,new Position());
        }else if (parentScope() instanceof ClassScope) {
            throw new semanticError("class scope under class scope" ,new Position());
        }else if (parentScope() instanceof GlobalScope) {
            return ((GlobalScope) parentScope()).getFuncDef(_identifier);
        }else return null;
    }

    public void defineVariable(String _identifier, VarTypeNode _varTypeNode) {
        varTable.put(_identifier,  _varTypeNode);
    }

    public void defineFunc(String _identifier, FuncDefNode _funcDefNode) {
        funcTable.put(_identifier,  _funcDefNode);
    }

    @Override
    public BaseScope parentScope() {
        return this.parentScope;
    }
}
