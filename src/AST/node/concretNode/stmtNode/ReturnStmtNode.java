package AST.node.concretNode.stmtNode;

import AST.ASTVisitor;
import AST.node.abstractNode.ExpNode;
import AST.node.abstractNode.StmtNode;
import AST.typeNode.Type;
import Utils.Position;

public class ReturnStmtNode extends StmtNode {
    public ExpNode returnExp;
    public Type returnType;

    public ReturnStmtNode(Position nodePos) {
        super(nodePos);
        this.returnExp = null;
    }

    @Override
    public void accept(ASTVisitor _visitor) {
        _visitor.visit(this);
    }
}

