package AST.node;

import AST.ASTVisitor;
import Utils.Position;

public abstract class ASTNode {
    private Position nodePos;

    public ASTNode(Position _nodePos) {
        this.nodePos = _nodePos;
    }

    public Position getNodePos() {
        return nodePos;
    }

    abstract public void accept(ASTVisitor visitor);
}
