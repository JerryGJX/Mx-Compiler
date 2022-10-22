package AST.node.concretNode.expNode;

import AST.ASTVisitor;
import AST.node.abstractNode.ExpNode;
import AST.typeNode.Type;
import Utils.Position;

import java.util.ArrayList;

public class NewExpNode extends ExpNode {
    public Type resultType;
    public ArrayList<ExpNode> SizeList;

    public NewExpNode(Position _nodePos){
        super(_nodePos);
        SizeList = new ArrayList<>();
        resultType = null;
    }

    @Override
    public void accept(ASTVisitor _visitor) {
        _visitor.visit(this);
    }

    @Override
    public boolean isLeftValue() {
        return false;
    }
}
