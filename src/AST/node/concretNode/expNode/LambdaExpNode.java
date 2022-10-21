package AST.node.concretNode.expNode;

import AST.ASTVisitor;
import AST.node.abstractNode.ExpNode;
import AST.node.concretNode.stmtNode.VarDefUnitNode;
import AST.node.concretNode.stmtNode.SuiteStmtNode;
import Utils.Position;

import java.util.ArrayList;

public class LambdaExpNode extends ExpNode {
    //public ReturnTypeNode returnType;
    public ArrayList<VarDefUnitNode> paraList;

    public ArrayList<ExpNode> callArgList;
    public SuiteStmtNode body;
    public boolean isCapture;

    public LambdaExpNode(Position _nodePos){
        super(_nodePos);
        paraList = new ArrayList<>();
        callArgList = new ArrayList<>();
        body = null;
        isCapture = false;
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
