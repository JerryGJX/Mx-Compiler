package AST.node.concretNode.stmtNode;

import AST.ASTVisitor;
import AST.node.abstractNode.StmtNode;
import AST.node.concretNode.VarDefUnitNode;
import Utils.Position;

import java.util.ArrayList;

public class VarDefStmtNode extends StmtNode {
    public ArrayList<VarDefUnitNode> varDefUnitNodes;

    public VarDefStmtNode(Position _nodePos) {
        super(_nodePos);
        this.varDefUnitNodes = new ArrayList<>();
    }

    @Override
    public void accept(ASTVisitor _visitor) {
        _visitor.visit(this);
    }
}
