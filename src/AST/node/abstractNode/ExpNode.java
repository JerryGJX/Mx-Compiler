package AST.node.abstractNode;

import AST.node.ASTNode;
import AST.typeNode.Type;
import AST.typeNode.VarTypeNode;
import Utils.Position;

public abstract class ExpNode extends ASTNode {
    public Type exprType;
//    public boolean isLeftValue;

    public ExpNode(Position _nodePos) {
        super(_nodePos);
        this.exprType = null;
        //this.isLeftValue = false;
    }

    public abstract boolean isLeftValue();
}