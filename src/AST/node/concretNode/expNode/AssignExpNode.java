package AST.node.concretNode.expNode;

import AST.ASTVisitor;
import AST.node.abstractNode.ExpNode;
import Utils.Position;

public class AssignExpNode extends ExpNode {
    public ExpNode rhs, lhs;

    public AssignExpNode(Position _nodePos) {
        super(_nodePos);
        this.rhs =null;
        this.lhs =null;
    }

    public AssignExpNode(ExpNode _left, ExpNode _right, Position _nodePos) {
        super(_nodePos);
        this.rhs = _left;
        this.lhs = _right;
    }

    @Override
    public void accept(ASTVisitor _visitor) {
        _visitor.visit(this);
    }

    @Override
    public boolean isLeftValue() { //后续可实现连续赋值
        return false;
    }
}
