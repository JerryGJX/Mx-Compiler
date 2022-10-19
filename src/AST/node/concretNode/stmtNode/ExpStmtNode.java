package AST.node.concretNode.stmtNode;

import AST.ASTVisitor;
import AST.node.abstractNode.ExpNode;
import AST.node.abstractNode.StmtNode;
import Utils.Position;

public class ExpStmtNode extends StmtNode {
    public ExpNode expNode;
    public ExpStmtNode(Position _nodePos) {
        super(_nodePos);
        this.expNode = null;
    }

    @Override
    public void accept(ASTVisitor _visitor) {
        _visitor.visit(this);
    }
}

