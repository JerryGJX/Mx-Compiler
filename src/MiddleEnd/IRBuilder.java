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
import AST.typeNode.VarTypeNode;
import IR.IRModule;
import IR.Type.StructType;
import IR.Type.VoidType;
import IR.Utils.Renamer;
import IR.Value.IRBasicBlock;
import IR.Value.IRDefine;
import IR.Value.User.Constant.GlobalValue.IRFunction;
import IR.Value.User.Constant.IRBoolConst;
import IR.Value.User.Constant.IRIntConstant;
import IR.Value.User.Constant.IRNullptrConstant;
import IR.Value.User.Constant.IRStrConstant;
import IR.Value.User.Instruction.*;
import Utils.VarInfo;
import Utils.scope.GlobalScope;
import Utils.scope.IRGlobalScope;
import Utils.scope.IRScope;

public class IRBuilder implements ASTVisitor, IRDefine {


    public IRModule projectIRModule;
    public IRGlobalScope irGlobalScope;
    public IRScope currentIRScope;
    public IRBasicBlock currentBlock;
//    public IRFunction currentFunction;
//    public StructType currentStructType;

    //scope control
    public void EnterClass(StructType _classTypeInfo) {
        currentIRScope = new IRScope(true, _classTypeInfo, false, null, false, null, null, currentIRScope);
    }
    public void ExitClass() {
        currentIRScope = currentIRScope.parentScope;
    }
    public void EnterFunc(IRFunction _irFunctionInfo) {
        currentIRScope = new IRScope(currentIRScope.inClass, currentIRScope.currentClassType, true, _irFunctionInfo, false, null, null, currentIRScope);
    }
    public void ExitFunc() {
        currentIRScope = currentIRScope.parentScope;
    }
    public void EnterLoop(IRBasicBlock _loopExitBlock, IRBasicBlock _loopContinueBlock) {
        currentIRScope = new IRScope(currentIRScope.inClass, currentIRScope.currentClassType, currentIRScope.inFunc, currentIRScope.currentIRFunction, true, _loopExitBlock, _loopContinueBlock, currentIRScope);
    }
    public void ExitLoop() {
        currentIRScope = currentIRScope.parentScope;
    }

    //current info
    public StructType CurClass() {
        if (currentIRScope.inClass) return currentIRScope.currentClassType;
        else return null;
    }
    public IRFunction CurFunc() {
        if (currentIRScope.inFunc) return currentIRScope.currentIRFunction;
        else return null;
    }
    public IRBasicBlock CurLoopExitBlock() {
        if (currentIRScope.inLoop) return currentIRScope.loopExitBlock;
        else return null;
    }
    public IRBasicBlock CurLoopContinueBlock() {
        if (currentIRScope.inLoop) return currentIRScope.loopContinueBlock;
        else return null;
    }

    //renaming strategy
    public String GetLoadFromAddr(String _name) {
        Integer defTime = currentIRScope.GetIndexInCurrentScope(_name);
        if (defTime == null) throw new RuntimeException("[GetLoadFromAddr] Error: " + _name + " is not defined");
        var info = irGlobalScope.valNameToInfo.get(_name);
        if (info.isGlobal && defTime == 0) return GLOBAL_PREFIX + _name; // @valName
        else return LOCAL_PREFIX + _name + ".addr." + defTime.toString();// %valName.addr.defTime
    }
    public String GetLoadToAddr(String _name) {
        var info = irGlobalScope.valNameToInfo.get(_name);
        if (info == null) throw new RuntimeException("[GetLoadFromAddr] Error: " + _name + " is not defined");
        //warning
        info.loadTime++;
        return LOCAL_PREFIX + _name + ".load." + info.loadTime.toString();// %valName.load.loadTime
    }
    public String GetStoreToAddr(String _name) {
        return GetLoadFromAddr(_name);
    }
    public String GetVarAllocaAddr(String _name) {
        var info = irGlobalScope.valNameToInfo.get(_name);
        if (info == null) return LOCAL_PREFIX + _name + ".addr";// %valName.addr
        else return LOCAL_PREFIX + _name + ".addr." + info.defTime.toString();// %valName.addr.defTime
    }
    public String GetStrIdentifier(String _str) {
        if (projectIRModule.IRStrConstantMap.containsKey(_str))
            return projectIRModule.IRStrConstantMap.get(_str).strIdentifier;
        else {
            String strId = GLOBAL_PREFIX + "strConst." + projectIRModule.IRStrConstantMap.size();
            var strConst = new IRStrConstant(strId, _str);
            projectIRModule.IRStrConstantMap.put(_str, strConst);
            return strId;
        }
    }
    public void DefVarInScope(String _name) {
        if (currentIRScope.VarMap.containsKey(_name))
            throw new RuntimeException("Error: " + _name + " is already defined in current scope");
        if (!irGlobalScope.valNameToInfo.containsKey(_name))
            irGlobalScope.valNameToInfo.put(_name, new VarInfo(_name, false));
        else {
            irGlobalScope.valNameToInfo.get(_name).defTime++;
            currentIRScope.VarMap.put(_name, irGlobalScope.valNameToInfo.get(_name).defTime);
        }
    }




    public IRBuilder(String _fileName, GlobalScope _globalScope) {
        projectIRModule = new IRModule(_fileName);
        this.currentBlock = null;
        projectIRModule.Init(_globalScope);
        irGlobalScope = new IRGlobalScope(_globalScope);
        currentIRScope = new IRScope();
        currentIRScope.Init(irGlobalScope);
    }

    @Override
    public void visit(RootNode node) {
        node.childNodes.forEach(classDefNode -> classDefNode.accept(this));
        projectIRModule.TestPrint();
    }

    @Override
    public void visit(ClassDefNode node) {
        EnterClass(projectIRModule.TranslateByString(node.className));

        node.memberVarMap.forEach((name, varDefUnitNode) -> varDefUnitNode.accept(this));
        node.constructorDefNode.accept(this);
        node.memberFuncMap.forEach((name, funcDefNode) -> funcDefNode.accept(this));

        ExitClass();
    }


    @Override
    public void visit(FuncDefNode node) {
        var funcName = currentIRScope.currentClassType == null ?
                node.funcName : currentIRScope.currentClassType.classId + "." + node.funcName;
        var IRFunc = projectIRModule.IRFunctionMap.get(funcName);
        EnterFunc(IRFunc);
        //construct func.entry block
        CurFunc().entryBlock = new IRBasicBlock(funcName + ".entry");
        //construct func.exit block
        CurFunc().exitBlock = new IRBasicBlock(funcName + ".exit");
        if (!(CurFunc().returnType instanceof VoidType)) {
            var allocaInst = new IRAllocaInst(IRDefine.LOCAL_PREFIX + funcName + ".ret.addr", CurFunc().returnType, currentBlock);
            CurFunc().retValPtr = allocaInst;
            CurFunc().entryBlock.addInst(allocaInst);
            var loadInst = new IRLoadInst(IRDefine.LOCAL_PREFIX + funcName + ".ret.load", CurFunc().returnType, allocaInst);
            CurFunc().exitBlock.addInst(loadInst);
            var retInst = new IRRetInst(CurFunc().returnType, loadInst);
            CurFunc().exitBlock.AddTerminator(retInst);
            if (currentIRScope.currentIRFunction.funcName.equals("main")) {
                var storeInst = new IRStoreInst(MAIN_DEFAULT_RETURN, allocaInst);
                CurFunc().entryBlock.addInst(storeInst);
                var brInst = new IRBrInst(CurFunc().exitBlock);
                CurFunc().entryBlock.AddTerminator(brInst);
            }
        } else {
            var retInst = new IRRetInst(CurFunc().returnType, null);
            CurFunc().exitBlock.AddTerminator(retInst);
        }

        currentBlock = CurFunc().entryBlock;
        node.funcBodyNode.accept(this);

        ExitFunc();
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
            if (node.exprType.isFunction) {
                String funcName = ((IdExpNode) node).id;
                //注:此处要求memberExp要改变scope的currentClassType
                if (currentIRScope.inClass) funcName = currentIRScope.currentClassType.classId + "." + funcName;
                node.irValue = projectIRModule.IRFunctionMap.get(funcName);
            } else {

            }
        } else if (node instanceof IntExpNode) {
            node.irValue = new IRIntConstant(((IntExpNode) node).value);
        } else if (node instanceof NullExpNode) {
            node.irValue = new IRNullptrConstant();
        } else if (node instanceof StringExpNode) {
            String str = ((StringExpNode) node).value;
            node.irValue = new IRStrConstant(GetStrIdentifier(str), str);
        } else if (node instanceof ThisExpNode) {
            if (!currentIRScope.inFunc || !currentIRScope.inClass) {
                throw new RuntimeException("this expression must be in a class function");
            }
            node.irValue = currentIRScope.currentIRFunction.argVarList.get(0);
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

            var storeInst = new IRStoreInst(node.returnExp.irValue, currentIRScope.currentIRFunction.retValPtr);
            currentBlock.addInst(storeInst);
        }
        var brInst = new IRBrInst(currentIRScope.currentIRFunction.exitBlock);
        currentBlock.AddTerminator(brInst);

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
        var funcName = currentIRScope.currentClassType == null ?
                node.funcName : currentIRScope.currentClassType.classId + "." + node.funcName;

        System.out.println(funcName);

        var IRFunc = projectIRModule.IRFunctionMap.get(funcName);

        if(IRFunc == null){
            System.out.println("null");
        }

        EnterFunc(IRFunc);
        //construct func.entry block
        CurFunc().entryBlock = new IRBasicBlock(funcName + ".entry");
        //construct func.exit block
        CurFunc().exitBlock = new IRBasicBlock(funcName + ".exit");
        if (!(CurFunc().returnType instanceof VoidType)) {
            var allocaInst = new IRAllocaInst(IRDefine.LOCAL_PREFIX + funcName + ".ret.addr", CurFunc().returnType, currentBlock);
            CurFunc().retValPtr = allocaInst;
            CurFunc().entryBlock.addInst(allocaInst);
            var loadInst = new IRLoadInst(IRDefine.LOCAL_PREFIX + funcName + ".ret.load", CurFunc().returnType, allocaInst);
            CurFunc().exitBlock.addInst(loadInst);
            var retInst = new IRRetInst(CurFunc().returnType, loadInst);
            CurFunc().exitBlock.AddTerminator(retInst);
        } else {
            var retInst = new IRRetInst(CurFunc().returnType, null);
            CurFunc().exitBlock.AddTerminator(retInst);
        }

        currentBlock = CurFunc().entryBlock;

        if(node.funcBodyNode != null){
            node.funcBodyNode.accept(this); //默认构造函数
        }else{
            var BrInst = new IRBrInst(CurFunc().exitBlock);
            currentBlock.AddTerminator(BrInst);
        }


        ExitFunc();
    }
}
