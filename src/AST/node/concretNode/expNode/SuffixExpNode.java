package AST.node.concretNode.expNode;

import AST.ASTVisitor;
import AST.node.abstractNode.ExpNode;
import Utils.Position;

public class SuffixExpNode extends ExpNode {
    public ExpNode value;
    public String op;

    public SuffixExpNode(ExpNode _value, String _op, Position _nodePos) {
        super(_nodePos);
        this.value = _value;
        this.op = _op;
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
