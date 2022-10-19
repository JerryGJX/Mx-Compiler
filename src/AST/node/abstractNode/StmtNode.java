package AST.node.abstractNode;

import AST.node.ASTNode;
import Utils.Position;

public abstract class StmtNode extends ASTNode {
    public StmtNode(Position nodePos) {
        super(nodePos);
    }
}
