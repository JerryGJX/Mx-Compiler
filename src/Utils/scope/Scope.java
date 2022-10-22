package Utils.scope;

import AST.node.concretNode.ClassDefNode;
import AST.node.concretNode.stmtNode.VarDefUnitNode;
import AST.typeNode.Type;
import AST.typeNode.VarTypeNode;

import java.util.HashMap;

public class Scope {
    public HashMap<String, VarDefUnitNode> varTable;
    public Scope parentScope;


    public Boolean inClass = false;
    public ClassDefNode classDefNode = null;

    public Boolean inFunc;
    public Type returnType = null;

    public Boolean inLoop;


    public Scope(Boolean _inClass,ClassDefNode _classDefNode,Boolean _inFunc,Boolean _inLoop,Scope _parent){
        varTable = new HashMap<>();
        this.parentScope = _parent;
        this.inClass = _inClass;
        this.classDefNode = _classDefNode;
        this.inFunc = _inFunc;
        this.inLoop = _inLoop;
    }

    public void addVar(String name, VarDefUnitNode _varDefUnitNode) {
        varTable.put(name, _varDefUnitNode);
    }

    public Boolean hasVar(String name) {
        if (varTable.containsKey(name)) {
            return true;
        }else if (parentScope != null) {
            return parentScope.hasVar(name);
        }else {
            return false;
        }
    }

    public VarDefUnitNode getVarDef(String _identifier) {
        if(varTable.containsKey(_identifier)){
            return varTable.get(_identifier);
        }
        else if(parentScope != null){
            return parentScope.getVarDef(_identifier);
        }
        else{
            return null;
        }
    }
}
