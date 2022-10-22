package AST.typeNode;

import AST.ASTVisitor;
import AST.node.ASTNode;
import Utils.Position;

public class ReturnTypeNode extends ASTNode {
    public Type returnType;

    public ReturnTypeNode(String _typeName, Integer _dimSize, Position _nodePos) {
        super(_nodePos);
        returnType = new Type(_typeName,_dimSize,false);
    }

    public String PrintType() {
        String ret = "";
        ret += "[typeName: " + returnType.typeName + ", dimSize: " + returnType.dimSize + "]";
        return ret;
    }

    @Override
    public void accept(ASTVisitor _visitor) {
        _visitor.visit(this);
    }
}
