package MiddleEnd;

import AST.ASTVisitor;
import AST.node.concretNode.ClassDefNode;
import AST.node.concretNode.ConstructorDefNode;
import AST.node.concretNode.FuncDefNode;
import AST.node.concretNode.RootNode;
import AST.node.concretNode.expNode.*;
import AST.node.concretNode.expNode.atomExpNode.AtomExpNode;
import AST.node.concretNode.stmtNode.*;
import AST.typeNode.ReturnTypeNode;
import AST.typeNode.Type;
import AST.typeNode.VarTypeNode;
import IR.IRModule;
import IR.Type.BasicType;
import IR.Type.StructType;
import IR.Value.IRBasicBlock;
import IR.Value.User.Constant.GlobalValue.IRFunction;
import Utils.scope.GlobalScope;
import Utils.scope.Scope;

import java.util.HashMap;

public class IRBuilder implements ASTVisitor {
    public IRModule currentIRModule;
    public GlobalScope globalScope;
    public Scope currentScope;

    public HashMap<String, BasicType> IRTypeMap;

    public HashMap<String, IRFunction> IRFunctionMap;//member function is special function
    public IRBasicBlock currentBlock;
    public IRFunction currentFunction;
    public StructType currentStructType;


    public IRBuilder(IRModule _currentIRModule, GlobalScope _globalScope) {
        this.currentIRModule = _currentIRModule;
        this.globalScope = _globalScope;
        this.currentBlock = null;
        this.currentFunction = null;
        this.currentStructType = null;

        this.globalScope.varTable.forEach();
    }

    @Override
    public void visit(ClassDefNode node) {

    }

    @Override
    public void visit(FuncDefNode node) {

    }

    @Override
    public void visit(VarDefUnitNode node) {

    }

    @Override
    public void visit(RootNode node) {

    }

    @Override
    public void visit(IndexExpNode node) {

    }

    @Override
    public void visit(AssignExpNode node) {

    }

    @Override
    public void visit(AtomExpNode node) {

    }

    @Override
    public void visit(BinaryExpNode node) {

    }

    @Override
    public void visit(FuncCallExpNode node) {

    }

    @Override
    public void visit(LambdaExpNode node) {

    }

    @Override
    public void visit(MemberExpNode node) {

    }

    @Override
    public void visit(NewExpNode node) {

    }

    @Override
    public void visit(PrefixExpNode node) {

    }

    @Override
    public void visit(SuffixExpNode node) {

    }

    @Override
    public void visit(UnaryExpNode node) {

    }

    @Override
    public void visit(BreakStmtNode node) {

    }

    @Override
    public void visit(ContinueStmtNode node) {

    }

    @Override
    public void visit(EmptyStmtNode node) {

    }

    @Override
    public void visit(ExpStmtNode node) {

    }

    @Override
    public void visit(ForStmtNode node) {

    }

    @Override
    public void visit(IfStmtNode node) {

    }

    @Override
    public void visit(ReturnStmtNode node) {

    }

    @Override
    public void visit(WhileStmtNode node) {

    }

    @Override
    public void visit(SuiteStmtNode node) {

    }

    @Override
    public void visit(VarDefStmtNode node) {

    }

    @Override
    public void visit(VarTypeNode node) {

    }

    @Override
    public void visit(ReturnTypeNode node) {

    }

    @Override
    public void visit(ConstructorDefNode node) {

    }
}
