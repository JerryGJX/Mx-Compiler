package MiddleEnd;

import AST.ASTVisitor;
import AST.node.concretNode.ClassDefNode;
import AST.node.concretNode.ConstructorDefNode;
import AST.node.concretNode.FuncDefNode;
import AST.node.concretNode.RootNode;
import AST.node.concretNode.expNode.*;
import AST.node.concretNode.expNode.atomExpNode.*;
import AST.node.concretNode.stmtNode.*;
import AST.typeNode.ReturnTypeNode;
import AST.typeNode.Type;
import AST.typeNode.VarTypeNode;
import FrontEnd.BuiltInElements;
import IR.IRModule;
import IR.Type.BasicType;
import IR.Type.FunctionType;
import IR.Type.StructType;
import IR.Type.VoidType;
import IR.Value.IRBasicBlock;
import IR.Value.IRDefine;
import IR.Value.User.Constant.GlobalValue.IRFunction;
import IR.Value.User.Constant.IRBoolConst;
import IR.Value.User.Constant.IRIntConstant;
import IR.Value.User.Constant.IRNullptrConstant;
import IR.Value.User.Constant.IRStrConstant;
import IR.Value.User.Instruction.*;
import Utils.VarInfo;
import Utils.error.semanticError;
import Utils.scope.GlobalScope;
import Utils.scope.IRGlobalScope;
import Utils.scope.IRScope;
import Utils.scope.Scope;

import java.util.HashMap;

public class IRBuilder implements ASTVisitor, IRDefine {
    public IRModule projectIRModule;
    //    public GlobalScope globalScope;
    public IRGlobalScope irGlobalScope;
    public IRScope currentIRScope;

    public IRBasicBlock currentBlock;
    public IRFunction currentFunction;
    public StructType currentStructType;




    public IRBuilder(String _fileName, GlobalScope _globalScope) {
        projectIRModule = new IRModule(_fileName);

        this.currentBlock = null;
        this.currentFunction = null;
        this.currentStructType = null;
        projectIRModule.Init(_globalScope);
        irGlobalScope = new IRGlobalScope(_globalScope);
        currentIRScope = new IRScope(false, null, false, null, false, null, null, null);
        currentIRScope.Init(irGlobalScope);
    }

    @Override
    public void visit(RootNode node) {
        node.childNodes.forEach(classDefNode -> classDefNode.accept(this));
    }

    @Override
    public void visit(ClassDefNode node) {
        currentStructType = projectIRModule.structTypeMap.get(node.className);
        currentIRScope = new IRScope(true, currentStructType, false, null, false, null, null, currentIRScope);
        node.memberVarMap.forEach((name, varDefUnitNode) -> varDefUnitNode.accept(this));
        node.constructorDefNode.accept(this);
        node.memberFuncMap.forEach((name, funcDefNode) -> funcDefNode.accept(this));
        currentIRScope = currentIRScope.parentScope;
        currentStructType = null;
    }


    @Override
    public void visit(FuncDefNode node) {
        var funcName = currentStructType == null ? node.funcName : currentStructType + "." + node.funcName;
        var IRFunc = projectIRModule.IRFunctionMap.get(funcName);
        currentFunction = IRFunc;

        currentIRScope = new IRScope(currentIRScope.inClass, currentIRScope.classTypeInfo, true, currentFunction, false, null, null, currentIRScope);

        //construct func.entry block
        currentBlock = new IRBasicBlock(funcName + ".entry");
        IRFunc.entryBlock = currentBlock;
        var allocaInst = new IRAllocaInst(IRDefine.LOCAL_PREFIX + funcName + ".ret.addr", IRFunc.returnType, currentBlock);
        currentBlock.addInst(allocaInst);
        IRFunc.retValPtr = allocaInst;
        if (currentFunction.funcName.equals("main")) {
            var storeInst = new IRStoreInst(MAIN_DEFAULT_RETURN, allocaInst);
            currentBlock.addInst(storeInst);
        }

        //construct func.exit block
        IRFunc.exitBlock = new IRBasicBlock(funcName + ".exit");
        var loadInst = new IRLoadInst(IRDefine.LOCAL_PREFIX + funcName + ".ret.load", IRFunc.returnType, allocaInst);
        IRFunc.exitBlock.addInst(loadInst);
        var retInst = new IRRetInst(IRFunc.returnType, loadInst);
        IRFunc.exitBlock.addInst(retInst);

        node.funcBodyNode.accept(this);


        System.out.println(IRFunc.toString());
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
        if (node instanceof BoolExpNode) {
            node.irValue = new IRBoolConst(((BoolExpNode) node).value);
        } else if (node instanceof IdExpNode) {
            if(node.exprType.isFunction){
                String funcName = ((IdExpNode) node).id;
                if(currentIRScope.inClass) funcName = currentIRScope.classTypeInfo.classId + "." + funcName;
                node.irValue = projectIRModule.IRFunctionMap.get(funcName);
            }else{

            }
        } else if (node instanceof IntExpNode) {
            node.irValue = new IRIntConstant(((IntExpNode) node).value);
        } else if (node instanceof NullExpNode) {
            node.irValue = new IRNullptrConstant();
        } else if (node instanceof StringExpNode) {
            node.irValue = new IRStrConstant(((StringExpNode) node).value);
        } else if (node instanceof ThisExpNode) {
            if (!currentIRScope.inFunc || !currentIRScope.inClass) {
                throw new RuntimeException("this expression must be in a class function");
            }
            node.irValue = currentIRScope.irFunctionInfo.argVarList.get(0);
        }
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

        if (node.returnExp != null) {

            node.returnExp.accept(this);

            var storeInst = new IRStoreInst(node.returnExp.irValue, currentIRScope.irFunctionInfo.retValPtr);
            currentBlock.addInst(storeInst);
        }
        var brInst = new IRBrInst(currentIRScope.irFunctionInfo.exitBlock);
        currentBlock.addInst(brInst);

    }

    @Override
    public void visit(WhileStmtNode node) {

    }

    @Override
    public void visit(SuiteStmtNode node) {
        node.stmtList.forEach(stmtNode -> stmtNode.accept(this));
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
