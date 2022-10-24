package AST.node.concretNode;

import AST.ASTVisitor;
import AST.node.ASTNode;
import AST.node.concretNode.stmtNode.SuiteStmtNode;
import Utils.Position;

public class ConstructorDefNode extends ASTNode {

    public String funcName;
    public SuiteStmtNode funcBodyNode;

    public ConstructorDefNode(Position _nodePos) {
        super(_nodePos);
        funcName = null;
        funcBodyNode = null;
    }

    @Override
    public void accept(ASTVisitor _visitor) {
        _visitor.visit(this);
    }
}