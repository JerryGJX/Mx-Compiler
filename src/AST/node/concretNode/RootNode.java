package AST.node.concretNode;

import AST.ASTVisitor;
import AST.node.ASTNode;
import Utils.Position;
import Utils.scope.GlobalScope;

import java.util.ArrayList;

public class RootNode extends ASTNode {
    public ArrayList<ASTNode> childNodes;

    public GlobalScope globalScope;

    public RootNode(ArrayList<ASTNode> _childNodes, Position _nodePos) {
        super(_nodePos);
        this.childNodes = _childNodes;
    }

    @Override
    public void accept(ASTVisitor _visitor) {
        _visitor.visit(this);
    }
}
