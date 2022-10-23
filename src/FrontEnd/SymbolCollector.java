package FrontEnd;

import AST.ASTVisitor;
import AST.node.concretNode.ClassDefNode;
import AST.node.concretNode.ConstructorDefNode;
import AST.node.concretNode.FuncDefNode;
import AST.node.concretNode.RootNode;
import AST.node.concretNode.expNode.*;
import AST.node.concretNode.expNode.atomExpNode.AtomExpNode;
import AST.node.concretNode.stmtNode.*;
import AST.typeNode.ReturnTypeNode;
import AST.typeNode.VarTypeNode;
import Utils.error.syntaxError;
import Utils.log.Log;
import Utils.scope.GlobalScope;

public class SymbolCollector implements ASTVisitor {
    public GlobalScope globalScope;
    public Log log;

    public SymbolCollector(GlobalScope _globalScope, Log _log) {
        this.globalScope = _globalScope;
        this.log = _log;
    }

    @Override
    public void visit(RootNode node) {
        for (int i = 0; i < node.childNodes.size(); i++) {
            node.childNodes.get(i).accept(this);
        }
    }


    @Override
    public void visit(ClassDefNode _node) {
        if (globalScope.hasClass(_node.className)) {
            throw new syntaxError("Class " + _node.className + " has been defined", _node.nodePos);
        } else if (globalScope.hasFunc(_node.className)) {
            throw new syntaxError("Class " + _node.className + " has been defined as a function", _node.nodePos);
        }
        globalScope.addClass(_node.className, _node);
    }

    @Override
    public void visit(FuncDefNode node) {
        log.addLog("SymbolCollector: visit FuncDefNode " + node.funcName);
        if (globalScope.hasFunc(node.funcName)) {
            throw new syntaxError("Function " + node.funcName + " has been defined", node.nodePos);
        } else if (globalScope.hasClass(node.funcName)) {
            throw new syntaxError("Function " + node.funcName + " has been defined as a class", node.nodePos);
        }
        globalScope.addFunc(node.funcName, node);
    }

    @Override
    public void visit(VarDefUnitNode node) {
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
