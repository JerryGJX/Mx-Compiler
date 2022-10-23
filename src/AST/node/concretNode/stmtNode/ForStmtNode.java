package AST.node.concretNode.stmtNode;

import AST.ASTVisitor;
import AST.node.abstractNode.ExpNode;
import AST.node.abstractNode.StmtNode;
import Utils.Position;

import java.util.ArrayList;

public class ForStmtNode extends StmtNode {
    public ExpNode initExpNode, condExpNode, stepExpNode;
    public SuiteStmtNode bodyStmtNode;

    public ArrayList<VarDefUnitNode> initVarDefUnitList;

    public ForStmtNode(Position _nodePos) {
        super(_nodePos);
        this.initExpNode = null;
        this.condExpNode = null;
        this.stepExpNode = null;
        this.bodyStmtNode = null;
        this.initVarDefUnitList = new ArrayList<>();
    }


    @Override
    public void accept(ASTVisitor _visitor) {
        _visitor.visit(this);
    }
}

