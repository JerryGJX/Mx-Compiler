package AST.node.concretNode.expNode.atomExpNode;

import AST.ASTVisitor;
import Utils.Position;

public class IdExpNode extends AtomExpNode{
    public String id;
public IdExpNode(String _id, Position _nodePos){
        super(_nodePos);
        this.id = _id;
    }

    @Override
    public void accept(ASTVisitor _visitor) {
        _visitor.visit(this);
    }

    @Override
    public boolean isLeftValue() {
        return true;
    }
}
