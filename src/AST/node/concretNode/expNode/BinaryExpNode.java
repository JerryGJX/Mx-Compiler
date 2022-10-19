package AST.node.concretNode.expNode;

import AST.ASTVisitor;
import AST.node.abstractNode.ExpNode;
import Utils.Position;

public class BinaryExpNode extends ExpNode {


    public enum BinaryOp {
        PlusOp, MinusOp, MulOp, DivOp, ModOp,
        GreaterOp, LessOp, GreaterEqualOp, LessEqualOp, NotEqualOp, EqualOp,
        LogicAndOp,LogicOrOp,
        BitAndOp,BitXorOp, BitOrOp,
        LeftShiftOp,RightShiftOp,
        AssignOp
        }

    public BinaryOp operator;
    public ExpNode left, right;

    public BinaryExpNode(ExpNode _left, BinaryOp _operator, ExpNode _right, Position _nodePos) {
        super(_nodePos);
        this.left = _left;
        this.operator = _operator;
        this.right = _right;
    }

    @Override
    public void accept(ASTVisitor _visitor) {
        _visitor.visit(this);
    }

    @Override
    public boolean isLeftValue() {
        return false;
    }

}
