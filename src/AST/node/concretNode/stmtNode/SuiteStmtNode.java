package AST.node.concretNode.stmtNode;

import AST.ASTVisitor;
import AST.node.abstractNode.StmtNode;
import Utils.Position;

import java.util.ArrayList;

public class SuiteStmtNode extends StmtNode {
    public ArrayList<StmtNode> stmtList;

    public SuiteStmtNode(Position _nodePos) {
        super(_nodePos);
        this.stmtList = new ArrayList<>();
    }



    @Override
    public void accept(ASTVisitor _visitor) {
        _visitor.visit(this);
    }
}
