package AST.node.concretNode.expNode;

import AST.ASTVisitor;
import AST.node.abstractNode.ExpNode;
import AST.node.concretNode.VarDefUnitNode;
import AST.node.concretNode.stmtNode.SuiteStmtNode;
import AST.typeNode.ReturnTypeNode;
import AST.typeNode.TypeEnum;
import Utils.Position;

import java.util.ArrayList;

public class LambdaExpNode extends ExpNode {
    //public ReturnTypeNode returnType;
    public ArrayList<VarDefUnitNode> paraList;
    public SuiteStmtNode body;
    public boolean isCapture;

    public LambdaExpNode(ArrayList<VarDefUnitNode> _paraList, SuiteStmtNode _body, Position _nodePos) {
        super(_nodePos);
        //this.returnType = TypeEnum.LAMBDA_RETURN;
        this.paraList = _paraList;
        this.body = _body;
        this.isCapture = false;
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
