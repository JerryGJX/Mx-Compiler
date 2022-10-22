package AST.node.concretNode.expNode;

import AST.ASTVisitor;
import AST.node.abstractNode.ExpNode;
import Utils.Position;

public class BinaryBoolExpNode extends BinaryExpNode {
    public BinaryBoolExpNode(Position _nodePos){
        super(_nodePos);
    }

    @Override
    public void accept(ASTVisitor _visitor) {
        _visitor.visit(this);
    }
}
