package AST.node.concretNode.stmtNode;

import AST.ASTVisitor;
import AST.node.abstractNode.ExpNode;
import AST.node.abstractNode.StmtNode;
import AST.typeNode.Type;
import AST.typeNode.VarTypeNode;
import IR.Value.IRValue;
import Utils.Position;

public class VarDefUnitNode extends StmtNode {
    public Type varType;
    public String varName;
    public Boolean isGlobal;
    public ExpNode initValue;

    public IRValue irValue;    //int or register
    public IRValue irAddress;  //xxx.addr, InstAlloc, InstGetElementPtr


    public VarDefUnitNode(Type _varType, String _varName, Boolean _isGlobal, Position _nodePos) {
        super(_nodePos);
        this.varName = _varName;
        this.varType = _varType;
        isGlobal = _isGlobal;
    }

    public VarDefUnitNode(Position _nodePos) {
        super(_nodePos);
        this.varName = null;
        this.varType = null;
        this.initValue = null;
        isGlobal = false;
    }

    public String PrintVarDefUnitNode() {
        String ret = "";
        ret += "[varName]: " + varName + "; [varType]: " + varType.PrintType();
        return ret;
    }

    @Override
    public void accept(ASTVisitor _visitor) {
        _visitor.visit(this);
    }
}

