package AST.node.concretNode.expNode.atomExpNode;

import AST.ASTVisitor;
import Utils.Position;

public class ThisExpNode extends AtomExpNode {
    public ThisExpNode(Position _position) {
        super(_position);
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
