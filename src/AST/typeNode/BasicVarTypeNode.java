package AST.typeNode;

import AST.node.ASTNode;
import Utils.Position;
import org.antlr.v4.runtime.misc.Pair;

public abstract class BasicVarTypeNode extends ASTNode {
    public TypeInfo typeInfo;

    public BasicVarTypeNode(TypeInfo _typeInfo, Position nodePos) {
        super(nodePos);
        typeInfo = _typeInfo;
    }
    public BasicVarTypeNode(TypeEnum _typeEnum, String _Id, Position nodePos) {
        super(nodePos);
        this.typeInfo = new TypeInfo(_typeEnum, _Id);
    }

    public boolean matchName(BasicVarTypeNode node) {
        if(this instanceof VarTypeNode && node instanceof VarTypeNode) return this.typeInfo.equals(node.typeInfo) && ((VarTypeNode) this).dimSize == ((VarTypeNode) node).dimSize;
        return (this instanceof ReturnTypeNode) && (node instanceof ReturnTypeNode);
    }

    public boolean matchName(TypeEnum typeEnum, String typeName) {
        return this.typeInfo.typeEnum == typeEnum && this.typeInfo.typeName.equals(typeName);
    }

    public boolean matchType(TypeEnum typeEnum) {
        return this.typeInfo.typeEnum == typeEnum;
    }
}
