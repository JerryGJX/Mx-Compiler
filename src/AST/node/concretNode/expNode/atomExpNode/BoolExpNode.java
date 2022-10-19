package AST.node.concretNode.expNode.atomExpNode;

import AST.ASTVisitor;
import Utils.Position;

public class BoolExpNode extends AtomExpNode {
    public boolean value;

    public BoolExpNode(boolean _value, Position _position) {
        super(_position);
        this.value = _value;
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

