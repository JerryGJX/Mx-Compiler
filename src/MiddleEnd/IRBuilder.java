package MiddleEnd;

import AST.ASTVisitor;
import AST.node.ASTNode;
import AST.node.abstractNode.ExpNode;
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
import IR.Type.BasicType;
import IR.Type.FunctionType;
import IR.Type.StructType;
import IR.Type.VoidType;
import IR.Value.IRBasicBlock;
import IR.Value.IRDefine;
import IR.Value.IRValue;
import IR.Value.User.Constant.GlobalValue.GlobalVariable;
import IR.Value.User.Constant.GlobalValue.IRFunction;
import IR.Value.User.Constant.IRBoolConst;
import IR.Value.User.Constant.IRIntConstant;
import IR.Value.User.Constant.IRNullptrConstant;
import IR.Value.User.Constant.IRStrConstant;
import IR.Value.User.Instruction.*;
import Utils.scope.GlobalScope;
import Utils.scope.IRGlobalScope;
import Utils.scope.IRScope;

import IR.Utils.TypeTransLater;

import java.util.ArrayList;
import java.util.HashMap;

import static IR.Utils.TypeTransLater.stringType;

public class IRBuilder implements ASTVisitor, IRDefine {


    public IRModule projectIRModule;
    public IRGlobalScope irGlobalScope;
    public IRScope currentIRScope;
    public IRBasicBlock currentBlock;


    public static HashMap<String, Integer> renameMap = new HashMap<>();

    public static String rename(String _name) {
        if (renameMap.containsKey(_name)) {
            renameMap.put(_name, renameMap.get(_name) + 1);
        } else {
            renameMap.put(_name, 0);
        }
        return _name + "." + renameMap.get(_name);
    }


    //inst generate
    private IRInstruction generateAllocInst(String _allocName, BasicType _allocType) {
        return new IRAllocaInst(rename(_allocName), _allocType, CurFunc().entryBlock);
    }

    private IRInstruction generateBinaryInst(String _op, BasicType _retType, IRValue _lhs, IRValue _rhs) {
        return new IRBinaryInst(rename(_op), _op, _retType, _lhs, _rhs, currentBlock);
    }

    private IRInstruction generateBitCastInst(IRValue _fromValue, BasicType _targetType) {
        return new IRBitCastInst(rename(LLVM_BITCAST_INST), _fromValue, _targetType, currentBlock);
    }

    private IRInstruction generateUnConBrInst(IRBasicBlock _destBlock, IRBasicBlock _parentBlock) {
        return new IRBrInst(_destBlock, _parentBlock);
    }

    private IRInstruction generateConBrInst(IRValue _cond, IRBasicBlock _trueBlock, IRBasicBlock _falseBlock, IRBasicBlock _parentBlock) {
        return new IRBrInst(_cond, _trueBlock, _falseBlock, _parentBlock);
    }

    private IRInstruction generateCallInst(IRFunction _calledFunc, BasicType _retType, ArrayList<IRValue> _args) {
        if (_retType == null) throw new RuntimeException("return type is null");
        if (_retType instanceof VoidType) return new IRCallInst(null, _calledFunc, _args, currentBlock);
        else
            return new IRCallInst(rename(_calledFunc.funcName + "." + LLVM_CALL_INST), _calledFunc, _args, currentBlock);
    }

    private IRInstruction generateNamedElementPtrInst(String _elementName, IRValue _headPtr, BasicType _pointedType, IRValue... indexes) {
        return new IRGEPInst(rename(_elementName + ".addr"), _headPtr, _pointedType, currentBlock, indexes);
    }

    private IRInstruction generateUnnamedElementPtrInst(IRValue _headPtr, BasicType _pointedType, IRValue... indexes) {
        return new IRGEPInst(rename(LLVM_GEP_INST), _headPtr, _pointedType, currentBlock, indexes);
    }

    private IRInstruction generateIcmpInst(String _op, IRValue _lhs, IRValue _rhs, IRBasicBlock _parentBlock) {
        return new IRIcmpInst(rename(LLVM_ICMP_INST), _op, _lhs, _rhs, currentBlock);
    }

    private IRInstruction generateLoadInst(String _loadToAddr, IRValue _pointer, IRBasicBlock _parentBlock) {
        return new IRLoadInst(rename(_loadToAddr), _pointer.valueType, _pointer, _parentBlock);
    }

    private IRInstruction generateRetInst(IRValue _retValue, IRBasicBlock _parentBlock) {
        if (_retValue == null) return new IRRetInst(new VoidType(), null, _parentBlock);
        return new IRRetInst(_retValue.valueType, _retValue, _parentBlock);
    }

    private IRInstruction generateStoreInst(IRValue _storeValue, IRValue _destPtr, IRBasicBlock _parentBlock) {
        return new IRStoreInst(_storeValue, _destPtr, _parentBlock);
    }

    private IRInstruction generateTruncInst(IRValue _fromValue, BasicType _targetType) {
        return new IRTruncInst(rename(LLVM_TRUNC_INST), _fromValue, _targetType, currentBlock);
    }

    private IRInstruction generateZextInst(IRValue _fromValue, BasicType _targetType) {
        return new IRZextInst(rename(LLVM_ZEXT_INST), _fromValue, _targetType, currentBlock);
    }

    private IRStrConstant GetStrInfo(String _str){
        if(projectIRModule.IRStrConstantMap.containsKey(_str)){
            return projectIRModule.IRStrConstantMap.get(_str);
        }
        else{
            IRStrConstant newStr = new IRStrConstant(rename(LLVM_STRING_IDENTIFIER),_str);
            projectIRModule.IRStrConstantMap.put(_str, newStr);
            return newStr;
        }
    }
    private IRValue getNodeValue(ExpNode _node, String _nodeId, IRBasicBlock _currentBlock) {
        if (_node.irValue != null) return ((ExpNode) _node).irValue;
        else {
            _node.irValue = generateLoadInst(_nodeId, getNodePointer(_node), _currentBlock);
            return _node.irValue;
        }
    }


    private IRValue getNodePointer(ASTNode _node) {
        if (!(_node instanceof VarDefUnitNode) && !(_node instanceof ExpNode))
            throw new RuntimeException("[getNodeValue] Error: " + _node.toString() + " is not a VarDefUnitNode or ExpNode");
        if (_node instanceof VarDefUnitNode) {
            if (((VarDefUnitNode) _node).irAddress != null) return ((VarDefUnitNode) _node).irAddress;
            else return null;
        }
        if (((ExpNode) _node).irAddress != null) return ((ExpNode) _node).irAddress;
        else return null;
    }


    public IRBuilder(String _fileName, GlobalScope _globalScope) {
        projectIRModule = new IRModule(_fileName);
        var initFunc = GenerateInitFunc();
        this.currentBlock = null;
        projectIRModule.Init(_globalScope, initFunc);
        irGlobalScope = new IRGlobalScope(_globalScope);
        currentIRScope = new IRScope();
        currentIRScope.Init(irGlobalScope);
    }

    @Override
    public void visit(RootNode node) {
        EnterFunc(projectIRModule.initFunc);
        currentBlock = projectIRModule.initFunc.entryBlock;
        node.childNodes.forEach(childNode -> {
            if (childNode instanceof VarDefUnitNode || childNode instanceof VarDefStmtNode) childNode.accept(this);
        });
        ExitFunc();
        currentBlock = null;

        node.childNodes.forEach(childNode -> {
            if (!(childNode instanceof VarDefUnitNode || childNode instanceof VarDefStmtNode)) childNode.accept(this);
        });

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
            var allocaInst = generateAllocInst(funcName + ".ret", CurFunc().returnType);
            CurFunc().retValPtr = allocaInst;
            CurFunc().entryBlock.addInst(allocaInst);

            var loadInst = generateLoadInst(funcName + ".ret", allocaInst, CurFunc().exitBlock);
            CurFunc().exitBlock.addInst(loadInst);

            var retInst = generateRetInst(loadInst, CurFunc().exitBlock);
            CurFunc().exitBlock.AddTerminator(retInst);

            if (currentIRScope.currentIRFunction.funcName.equals("main")) {
                var storeInst = generateStoreInst(MAIN_DEFAULT_RETURN, allocaInst, CurFunc().entryBlock);
                CurFunc().entryBlock.addInst(storeInst);
                var brInst = generateUnConBrInst(CurFunc().exitBlock, CurFunc().entryBlock);
                CurFunc().entryBlock.AddTerminator(brInst);
            }
        } else {
            var retInst = generateRetInst(null, CurFunc().exitBlock);
            CurFunc().exitBlock.AddTerminator(retInst);
        }

        currentBlock = CurFunc().entryBlock;
        node.funcBodyNode.accept(this);

        ExitFunc();
    }

    @Override
    public void visit(VarDefStmtNode node) {
        node.varDefUnitNodes.forEach(varDefUnitNode -> varDefUnitNode.accept(this));
    }

    @Override
    public void visit(VarDefUnitNode node) {
        IRValue allocPtr;
        //for globalVar
        if (CurFunc().equals(projectIRModule.initFunc)) {
            allocPtr = projectIRModule.IRGlobalVariableMap.get(node.varName);
        } else {
            allocPtr = generateAllocInst(node.varName, projectIRModule.TranslateVarType(node.varType));
        }


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
            var strConst = GetStrInfo(str);
            node.irAddress = generateUnnamedElementPtrInst(strConst, stringType, new IRIntConstant(0), new IRIntConstant(0));
        } else if (node instanceof ThisExpNode) {
            node.irValue = currentIRScope.GetThis();
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
            var storeInst = new IRStoreInst(node.returnExp.irValue, currentIRScope.currentIRFunction.retValPtr, currentBlock);
            currentBlock.addInst(storeInst);
        }
        var brInst = new IRBrInst(currentIRScope.currentIRFunction.exitBlock, currentBlock);
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
        if (IRFunc == null) {
            System.out.println("null");
        }
        EnterFunc(IRFunc);
        //construct func.entry block
        CurFunc().entryBlock = new IRBasicBlock(funcName + ".entry");
        //construct func.exit block
        CurFunc().exitBlock = new IRBasicBlock(funcName + ".exit");
        if (!(CurFunc().returnType instanceof VoidType)) {
            var allocaInst = generateAllocInst(funcName + ".ret", CurFunc().returnType);
            CurFunc().retValPtr = allocaInst;
            CurFunc().entryBlock.addInst(allocaInst);
            var loadInst = generateLoadInst(funcName + ".ret", true, allocaInst, CurFunc().exitBlock);
            CurFunc().exitBlock.addInst(loadInst);
            var retInst = generateRetInst(loadInst, CurFunc().exitBlock);
            CurFunc().exitBlock.AddTerminator(retInst);
        } else {
            var retInst = generateRetInst(null, CurFunc().exitBlock);
            CurFunc().exitBlock.AddTerminator(retInst);
        }
        currentBlock = CurFunc().entryBlock;
        if (node.funcBodyNode != null) {
            node.funcBodyNode.accept(this); //默认构造函数
        } else {
            var BrInst = generateBrInst(CurFunc().exitBlock, currentBlock);
            currentBlock.AddTerminator(BrInst);
        }
        ExitFunc();
    }


    //scope control
    public void EnterClass(StructType _classTypeInfo) {
        currentIRScope = new IRScope(true, _classTypeInfo, currentIRScope.inFunc, currentIRScope.currentIRFunction, false, null, null, currentIRScope);
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


    //globalVar init
    private IRFunction GenerateInitFunc() {
        FunctionType initFuncType = new FunctionType(new VoidType(), IRDefine.LLVM_INIT_FUNCTION);
        IRFunction initFunc = new IRFunction("init", initFuncType, false);
        initFunc.entryBlock = new IRBasicBlock(IRDefine.LLVM_INIT_FUNCTION + ".entry");
        //construct func.exit block
        initFunc.exitBlock = new IRBasicBlock(IRDefine.LLVM_INIT_FUNCTION + ".exit");
        //jump to exit block
        var brInst = new IRBrInst(initFunc.exitBlock, initFunc.entryBlock);
        initFunc.entryBlock.AddTerminator(brInst);
        //return void
        var retInst = new IRRetInst(initFunc.returnType, null, initFunc.exitBlock);
        initFunc.exitBlock.AddTerminator(retInst);
        return initFunc;
    }


}
