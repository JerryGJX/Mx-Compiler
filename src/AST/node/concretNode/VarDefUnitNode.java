package AST.node.concretNode;

import AST.ASTVisitor;
import AST.node.ASTNode;
import AST.node.abstractNode.ExpNode;
import AST.typeNode.BasicVarTypeNode;
import Utils.Position;

public class VarDefUnitNode extends ASTNode {
    public String varName;
    public BasicVarTypeNode varType;
    public ExpNode initValue;

    public VarDefUnitNode(String _varName, BasicVarTypeNode _varType, ExpNode _initValue, Position _nodePos) {
        super(_nodePos);
        this.varName = _varName;
        this.varType = _varType;
        this.initValue = _initValue;
    }

    @Override
    public void accept(ASTVisitor _visitor) {
        _visitor.visit(this);
    }
}

