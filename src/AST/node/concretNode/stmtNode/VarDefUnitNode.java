package AST.node.concretNode.stmtNode;

import AST.ASTVisitor;
import AST.node.abstractNode.ExpNode;
import AST.node.abstractNode.StmtNode;
import AST.typeNode.BasicVarTypeNode;
import Utils.Position;

public class VarDefUnitNode extends StmtNode {
    public String varName;
    public BasicVarTypeNode varType;
    public ExpNode initValue;

    public Boolean isGlobal;

//    public VarDefUnitNode(String _varName, BasicVarTypeNode _varType, ExpNode _initValue, Position _nodePos) {
//        super(_nodePos);
//        this.varName = _varName;
//        this.varType = _varType;
//        this.initValue = _initValue;
//        isGlobal = false;
//    }

    public VarDefUnitNode (Position _nodePos) {
        super(_nodePos);
        this.varName = null;
        this.varType = null;
        this.initValue = null;
        isGlobal = false;
    }

    @Override
    public void accept(ASTVisitor _visitor) {
        _visitor.visit(this);
    }
}

