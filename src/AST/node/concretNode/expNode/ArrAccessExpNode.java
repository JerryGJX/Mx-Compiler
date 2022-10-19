package AST.node.concretNode.expNode;

import AST.ASTVisitor;
import AST.node.abstractNode.ExpNode;
import Utils.Position;

public class ArrAccessExpNode extends ExpNode {
    public ExpNode array;
    public ExpNode index;

    public ArrAccessExpNode(ExpNode _array, ExpNode _index, Position _nodePos) {
        super(_nodePos);
        this.array = _array;
        this.index = _index;
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
