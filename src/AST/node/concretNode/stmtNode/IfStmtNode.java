package AST.node.concretNode.stmtNode;

import AST.ASTVisitor;
import AST.node.abstractNode.ExpNode;
import AST.node.abstractNode.StmtNode;
import Utils.Position;

import java.util.ArrayList;

public class IfStmtNode extends StmtNode {
    public ExpNode condExpNode;
    public ArrayList<StmtNode> trueStmtList, elseStmtList;

    public IfStmtNode(Position _nodePos) {
        super(_nodePos);
        this.condExpNode = null;
        this.trueStmtList = null;
        this.elseStmtList = null;
    }

    public IfStmtNode(Position _nodePos, ExpNode _conditionExpNode, ArrayList<StmtNode> _ifTrueStmtList) {
        super(_nodePos);
        this.condExpNode = _conditionExpNode;
        this.trueStmtList = _ifTrueStmtList;
        this.elseStmtList = null;
    }

    @Override
    public void accept(ASTVisitor _visitor) {
        _visitor.visit(this);
    }
}
