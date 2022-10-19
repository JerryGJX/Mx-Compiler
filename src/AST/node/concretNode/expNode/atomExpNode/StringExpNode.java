package AST.node.concretNode.expNode.atomExpNode;

import AST.ASTVisitor;
import Utils.Position;

public class StringExpNode extends AtomExpNode {
    public String value;

    public StringExpNode(String _value, Position _position) {
        super(_position);
        this.value = _value;
    }

    public String getValue() {
        return value;
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
