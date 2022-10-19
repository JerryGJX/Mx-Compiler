package AST.node.abstractNode;

import AST.ASTVisitor;
import AST.node.ASTNode;
import AST.typeNode.BasicVarTypeNode;
import Utils.Position;

public abstract class ExpNode extends ASTNode {
    public BasicVarTypeNode exprType;
//    public boolean isLeftValue;

    public ExpNode(Position _nodePos) {
        super(_nodePos);
        this.exprType = null;
        //this.isLeftValue = false;
    }

    public abstract boolean isLeftValue();
}
