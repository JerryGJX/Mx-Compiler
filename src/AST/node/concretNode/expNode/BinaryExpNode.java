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
    public ExpNode lhs, rhs;

    public BinaryExpNode(Position _nodePos){
        super(_nodePos);
        operator = null;
        lhs = null;
        rhs = null;
    }

    public BinaryExpNode(ExpNode _left, BinaryOp _operator, ExpNode _right, Position _nodePos) {
        super(_nodePos);
        this.lhs = _left;
        this.operator = _operator;
        this.rhs = _right;
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
