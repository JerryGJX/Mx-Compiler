package AST.node.concretNode.expNode.atomExpNode;

import AST.ASTVisitor;
import Utils.Position;

public class NullExpNode extends AtomExpNode {
    public NullExpNode(Position _position) {
        super(_position);
    }

    @Override
    public boolean isLeftValue() {
        return false;
    }

    @Override
    public void accept(ASTVisitor _visitor) {
        _visitor.visit(this);
    }

}
