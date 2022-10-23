package AST.node.concretNode.stmtNode;

import AST.ASTVisitor;
import AST.node.abstractNode.ExpNode;
import AST.node.abstractNode.StmtNode;
import Utils.Position;

import java.util.ArrayList;

public class WhileStmtNode extends StmtNode {
    public ExpNode condExpNode;
    public ArrayList<StmtNode> bodyStmtList;

    public WhileStmtNode(Position _nodePos) {
        super(_nodePos);
        this.condExpNode = null;
        this.bodyStmtList = new ArrayList<>();
    }

    @Override
    public void accept(ASTVisitor _visitor) {
        _visitor.visit(this);
    }
}

