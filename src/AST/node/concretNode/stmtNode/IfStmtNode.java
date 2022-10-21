package AST.node.concretNode.stmtNode;

import AST.ASTVisitor;
import AST.node.abstractNode.ExpNode;
import AST.node.abstractNode.StmtNode;
import Utils.Position;

public class IfStmtNode extends StmtNode {
    public ExpNode condExpNode;
    public StmtNode trueStmtNode,elseStmtNode;

    public IfStmtNode(Position _nodePos) {
        super(_nodePos);
        this.condExpNode = null;
        this.trueStmtNode = null;
        this.elseStmtNode = null;
    }

    public IfStmtNode(Position _nodePos, ExpNode _conditionExpNode, StmtNode _ifTrueStmtNode) {
        super(_nodePos);
        this.condExpNode = _conditionExpNode;
        this.trueStmtNode = _ifTrueStmtNode;
        this.elseStmtNode = null;
    }

    @Override
    public void accept(ASTVisitor _visitor) {
        _visitor.visit(this);
    }
}
