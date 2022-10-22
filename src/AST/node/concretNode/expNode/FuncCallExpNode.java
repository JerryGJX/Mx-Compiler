package AST.node.concretNode.expNode;

import AST.ASTVisitor;
import AST.node.abstractNode.ExpNode;
import Utils.Position;

import java.util.ArrayList;

public class FuncCallExpNode extends ExpNode {

    public ExpNode function; //可能有成员函数
    public ArrayList<ExpNode> paraList;

    public FuncCallExpNode(ExpNode _func, ArrayList<ExpNode> _ArgList, Position _nodePos) {
        super(_nodePos);
        this.function = _func;
        this.paraList = _ArgList;
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
