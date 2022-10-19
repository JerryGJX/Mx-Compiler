package AST.node.concretNode.expNode;

import AST.ASTVisitor;
import AST.node.abstractNode.ExpNode;
import Utils.Position;

public class BinaryBoolExpNode extends BinaryExpNode {
    public BinaryBoolExpNode(ExpNode _left, ExpNode _right, BinaryOp _operator, Position _position) {
        super(_left,  _operator,_right, _position);
    }

    @Override
    public void accept(ASTVisitor _visitor) {
        _visitor.visit(this);
    }


}
