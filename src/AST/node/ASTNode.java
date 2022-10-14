package AST.node;

import AST.ASTVisitor;
import Utils.Position;

public abstract class ASTNode {
    private Position pos;

    public ASTNode(Position nodePos) {
        this.pos = nodePos;
    }

    public Position getPos() {
        return pos;
    }

    abstract public void accept(ASTVisitor visitor);
}
