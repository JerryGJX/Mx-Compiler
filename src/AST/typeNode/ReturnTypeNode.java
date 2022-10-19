package AST.typeNode;

import AST.ASTVisitor;
import Utils.Position;

public class ReturnTypeNode extends BasicVarTypeNode{
    public int dimSize;

    public  ReturnTypeNode(BasicVarTypeNode _node, Position _nodePos) {
        super(_node.typeInfo,_nodePos);
        if(_node instanceof ReturnTypeNode) {
            this.dimSize = ((ReturnTypeNode) _node).dimSize;
        }
        else {
            this.dimSize = 0;//表示没有“[]”
        }
    }
    public ReturnTypeNode(TypeEnum _typeEnum, String _typeName,Position _nodePos,int _dimSize){
        super(_typeEnum,_typeName,_nodePos);
        this.dimSize = _dimSize;
    }

    @Override
    public void accept(ASTVisitor _visitor) {
        _visitor.visit(this);
    }
}
