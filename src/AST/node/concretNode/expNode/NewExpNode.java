package AST.node.concretNode.expNode;

import AST.ASTVisitor;
import AST.node.abstractNode.ExpNode;
import AST.typeNode.TypeEnum;
import AST.typeNode.VarTypeNode;
import Utils.Position;

import java.util.ArrayList;

public class NewExpNode extends ExpNode {
    public VarTypeNode newType;

    public ArrayList<ExpNode> SizeList;

    public NewExpNode(VarTypeNode _newType, ArrayList<ExpNode> _SizeList, Position _nodePos) {
        super(_nodePos);
        this.newType = _newType;
        this.SizeList = _SizeList;
    }

    public NewExpNode(TypeEnum _typeEnum, String _typeName, Position _nodePos, int _dimSize, ArrayList<ExpNode> _SizeList) {
        super(_nodePos);
        this.newType = new VarTypeNode(_typeEnum, _typeName, _nodePos, _dimSize);
        this.SizeList = _SizeList;
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
