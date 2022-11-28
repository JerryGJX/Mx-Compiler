package AST.node.concretNode.expNode.atomExpNode;

import AST.ASTVisitor;
import Utils.Position;

public class IdExpNode extends AtomExpNode {
    public String id;

    //for LLVM
    public boolean isMember = false;

    public IdExpNode(String _id, Position _nodePos) {
        super(_nodePos);
        this.id = _id;
    }


    public void setAsFunction(){
        this.exprType.isFunction = true;
    }
    public boolean isFunction() {
        return this.exprType.isFunction;
    }

    @Override
    public void accept(ASTVisitor _visitor) {
        _visitor.visit(this);
    }

    @Override
    public boolean isLeftValue() {
        return true;
    }
}
