package AST.node.concretNode.stmtNode;

import AST.ASTVisitor;
import AST.node.abstractNode.ExpNode;
import AST.node.abstractNode.StmtNode;
import AST.node.concretNode.VarDefUnitNode;
import Utils.Position;
import Utils.scope.FuncBodyScope;

import java.util.ArrayList;

public class ForStmtNode extends StmtNode {
    public ExpNode initExpNode, condExpNode, stepExpNode;
    public StmtNode bodyStmtNode;

    public FuncBodyScope funcAndLoopScope;

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

