package AST.node.concretNode.stmtNode;

import AST.ASTVisitor;
import AST.node.abstractNode.StmtNode;
import Utils.Position;

public class BreakStmtNode extends StmtNode {
    public BreakStmtNode(Position _nodePos) {
        super(_nodePos);
    }

    @Override
    public void accept(ASTVisitor _visitor) {
        _visitor.visit(this);
    }


}


