package AST.node.concretNode.stmtNode;

import AST.ASTVisitor;
import AST.node.abstractNode.StmtNode;
import Utils.Position;

import java.util.ArrayList;

public class SuiteStmtNode extends StmtNode {
    public ArrayList<StmtNode> stmts;

    public SuiteStmtNode(Position _nodePos) {
        super(_nodePos);
        this.stmts = new ArrayList<>();
    }



    @Override
    public void accept(ASTVisitor _visitor) {
        _visitor.visit(this);
    }
}
