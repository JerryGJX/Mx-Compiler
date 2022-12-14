package AST.node.abstractNode;

import AST.node.ASTNode;
import AST.typeNode.Type;
import Utils.Position;

public abstract class StmtNode extends ASTNode {
    public Boolean hasReturn = false;
    public Type returnType = null;

    public StmtNode(Position nodePos) {
        super(nodePos);
    }
}
