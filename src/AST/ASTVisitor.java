package AST;

import AST.node.concretNode.*;
import AST.node.concretNode.expNode.*;
import AST.node.concretNode.expNode.atomExpNode.AtomExpNode;
import AST.node.concretNode.stmtNode.*;
import AST.typeNode.*;

public interface ASTVisitor {
    void visit(ClassDefNode node);
    void visit(FuncDefNode node);
    void visit(VarDefUnitNode node);
    void visit(RootNode node);

    //Exp related
    void visit(IndexExpNode node);
    void visit(AssignExpNode node);
    void visit(AtomExpNode node);
    void visit(BinaryExpNode node);
    void visit(FuncCallExpNode node);
    void visit(LambdaExpNode node);
    void visit(MemberExpNode node);
    void visit(NewExpNode node);
    void visit(PrefixExpNode node);
    void visit(SuffixExpNode node);
    void visit(UnaryExpNode node);

    //stmt related
    void visit(BreakStmtNode node);
    void visit(ContinueStmtNode node);
    void visit(EmptyStmtNode node);
    void visit(ExpStmtNode node);
    void visit(ForStmtNode node);
    void visit(IfStmtNode node);
    void visit(ReturnStmtNode node);
    void visit(WhileStmtNode node);
    void visit(SuiteStmtNode node);
    void visit(VarDefStmtNode node);

    //type related
    void visit(VarTypeNode node);

    void visit(ReturnTypeNode node);

    void visit(ConstructorDefNode node);
}
