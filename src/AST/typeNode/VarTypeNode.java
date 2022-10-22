package AST.typeNode;

import AST.ASTVisitor;
import AST.node.ASTNode;
import Utils.Position;


public class VarTypeNode extends ASTNode {
    public Type varType;

    public VarTypeNode(String _typeName,Integer _dimSize, Position _nodePos) {
        super(_nodePos);
        varType = new Type(_typeName,_dimSize,false);
    }

    public VarTypeNode(Type _type, Position _nodePos) {
        super(_nodePos);
        varType = _type;
    }

    public String PrintType() {
        String ret = "";
        ret += "[typeName: " + this.varType.typeName + ", dimSize: " + varType.dimSize +"]";
        return ret;
    }

    public Boolean VarTypeMatch(Type _aimType){
        return this.varType.typeName.equals(_aimType.typeName);
    }

    @Override
    public void accept(ASTVisitor _visitor) {
        _visitor.visit(this);
    }
}
