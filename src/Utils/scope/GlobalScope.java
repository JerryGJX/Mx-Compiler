package Utils.scope;

import AST.node.concretNode.ClassDefNode;
import AST.node.concretNode.FuncDefNode;
import AST.typeNode.TypeInfo;
import AST.typeNode.VarTypeNode;

import java.util.HashMap;

public class GlobalScope extends BaseScope {
    private HashMap<String, VarTypeNode> gloVarTable;
    private HashMap<String, FuncDefNode> gloFuncTable;
    private HashMap<String, ClassDefNode> gloClassTable;

    public GlobalScope() {
        super(null);
        gloVarTable = new HashMap<>();
        gloFuncTable = new HashMap<>();
        gloClassTable = new HashMap<>();
    }

    public Boolean ContainVar(String _identifier) {
        return gloVarTable.containsKey(_identifier);
    }

    public Boolean ContainFunc(String _identifier) {
        return gloFuncTable.containsKey(_identifier);
    }

    public Boolean ContainClass(String _identifier) {
        return gloClassTable.containsKey(_identifier);
    }

    public VarTypeNode getVarType(String _identifier) {
        return gloVarTable.getOrDefault(_identifier, null);
    }

    public FuncDefNode getFuncDef(String _identifier) {
        return gloFuncTable.getOrDefault(_identifier, null);
    }

    public ClassDefNode getClassDef(String _identifier) {
        return gloClassTable.getOrDefault(_identifier, null);
    }

    @Override
    public BaseScope parentScope() {
        return this.parentScope;
    }

}

