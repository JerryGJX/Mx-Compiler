package AST.node.concretNode.stmtNode;

import AST.ASTVisitor;
import AST.node.abstractNode.ExpNode;
import AST.node.abstractNode.StmtNode;
import Utils.Position;
import Utils.scope.FuncBodyScope;

public class WhileStmtNode extends StmtNode {
    public ExpNode condExpNode;
    public StmtNode bodyStmtNode;

    public FuncBodyScope funcAndLoopScope;

    public WhileStmtNode(Position _nodePos) {
        super(_nodePos);
        this.condExpNode = null;
        this.bodyStmtNode = null;
    }

    @Override
    public void accept(ASTVisitor _visitor) {
        _visitor.visit(this);
    }
}

