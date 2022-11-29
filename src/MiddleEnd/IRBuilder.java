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
import IR.Type.*;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import static AST.node.concretNode.expNode.BinaryExpNode.BinaryOp.*;
//import static IR.Utils.TypeTransLater.allocBoolType;
import static IR.Utils.TypeTransLater.stringType;

public class IRBuilder implements ASTVisitor, IRDefine {
    public IRModule projectIRModule;
    public IRGlobalScope irGlobalScope;
    public IRScope irCurrentScope;
    public IRBasicBlock currentBlock;

    //varId -> varAddr (varId is A.1,A.2 etc.)
    public HashMap<IRScope.VarInfo, IRValue> varAddrMap = new HashMap<>();
    //for var only, the %add etc. will be stored in each Node

    private IRValue GetVarAddrInCurrentScope(String _rawName) {//for A.B it's A.B
        var varInfo = irCurrentScope.GetVarInfo(_rawName);
        if (varInfo != null) {
            if (varAddrMap.get(varInfo) == null) {
                throw new RuntimeException("varAddrMap.get(varInfo) == null");
            } else {
                return varAddrMap.get(varInfo);
            }
        } else throw new RuntimeException("varInfo == null");
    }


    private void AddVarDef(String _rawName, IRValue _addr) {
        String varId = rename(_rawName);
        Integer index = renameMap.get(_rawName);
        var varInfo = new IRScope.VarInfo(varId, index, false);
        varAddrMap.put(varInfo, _addr);
        irCurrentScope.VarInfoMap.put(_rawName, varInfo);
    }


    //renamer
    public static HashMap<String, Integer> renameMap = new HashMap<>();

    private static String rename(String _name) {
        String newName;
        if (renameMap.containsKey(_name)) {
            renameMap.put(_name, renameMap.get(_name) + 1);
            newName = _name + "." + renameMap.get(_name).toString();
        } else {
            renameMap.put(_name, 0);
            newName = _name;
        }
        return newName;
    }

    //about string
    private IRStrConstant GetStrInfo(String _str) {
        if (projectIRModule.IRStrConstantMap.containsKey(_str)) {
            return projectIRModule.IRStrConstantMap.get(_str);
        } else {
            IRStrConstant newStr = new IRStrConstant(rename(LLVM_STRING_IDENTIFIER), _str);
            projectIRModule.IRStrConstantMap.put(_str, newStr);
            return newStr;
        }
    }

    private void AddConstStrToModule(String _str) {
        if (!projectIRModule.IRStrConstantMap.containsKey(_str)) {
            IRStrConstant newStr = new IRStrConstant(rename(LLVM_STRING_IDENTIFIER), _str);
            projectIRModule.IRStrConstantMap.put(_str, newStr);
        }
    }

    //get value and add instruction
    private IRValue getExpNodeValue(ExpNode _node) {
        if (_node.irValue == null) {
            if (_node.irAddress == null) throw new RuntimeException("We know nothing about this node");
            _node.irValue = generateLoadInst(rename(_node.llvmName), Objects.requireNonNull(getNodePointer(_node)), currentBlock);
            currentBlock.addInst((IRInstruction) _node.irValue);
            if (_node.irValue.valueType instanceof memBoolType) _node.irValue = i8Toi1(_node.irValue);
            return _node.irValue;
        }
        if (_node.irValue instanceof IRInstruction) currentBlock.addInst((IRInstruction) _node.irValue);
        if (_node.irValue.valueType instanceof memBoolType) {
            throw new RuntimeException("getExpNodeValue: memBoolType");
        }
        return _node.irValue;
    }

    private IRValue getNodePointer(ExpNode _node) {
        if (_node.irAddress == null) return null;
        if (_node.irAddress instanceof IRInstruction)
            currentBlock.addInst((IRInstruction) _node.irAddress);
        return _node.irAddress;

    }

    //real content
    public IRBuilder(String _fileName, GlobalScope _globalScope) {
        projectIRModule = new IRModule(_fileName);
        var initFunc = GenerateInitFunc();
        this.currentBlock = null;
        projectIRModule.Init(_globalScope, initFunc);
        irGlobalScope = new IRGlobalScope(_globalScope);
        irCurrentScope = new IRScope();
        irCurrentScope.parentScope = irGlobalScope;
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
        var funcName = irCurrentScope.currentClassType == null ?
                node.funcName : irCurrentScope.currentClassType.classId + "." + node.funcName;
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

            if (irCurrentScope.currentIRFunction.funcName.equals("main")) {
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
            currentBlock.addInst((IRInstruction) allocPtr);
            AddVarDef(node.varName, allocPtr);
        }
        if (node.initValue != null) {
            node.initValue.accept(this);
            var initValue = getExpNodeValue(node.initValue);
            var storeInst = generateStoreInst(initValue, allocPtr, currentBlock);
            currentBlock.addInst(storeInst);
        } else if (node.varType.isClass || node.varType.dimSize > 0) {
            var storeInst = generateStoreInst(new IRNullptrConstant(), allocPtr, currentBlock);
            currentBlock.addInst(storeInst);
        }
    }

    @Override
    public void visit(IndexExpNode node) {

    }

    @Override
    public void visit(AssignExpNode node) {
        node.lhs.accept(this);
        node.rhs.accept(this);
        var lhsPtr = getNodePointer(node.lhs);
        var rhsValue = getExpNodeValue(node.rhs);
        var storeInst = generateStoreInst(rhsValue, lhsPtr, currentBlock);
        currentBlock.addInst(storeInst);
    }

    @Override
    public void visit(AtomExpNode node) {
        if (node instanceof BoolExpNode) {
            node.llvmName = "";
            node.irValue = new IRBoolConst(((BoolExpNode) node).value);
        } else if (node instanceof IdExpNode) {
            node.llvmName = ((IdExpNode) node).id;
            if (!((IdExpNode) node).isMember) node.irAddress = GetVarAddrInCurrentScope(node.llvmName);
            else throw new RuntimeException("[AtomExpNode] Error: member not supported");
        } else if (node instanceof IntExpNode) {
            node.llvmName = "";
            node.irValue = new IRIntConstant(((IntExpNode) node).value);
        } else if (node instanceof NullExpNode) {
            node.llvmName = "";
            node.irValue = new IRNullptrConstant();
        } else if (node instanceof StringExpNode) {
            String str = ((StringExpNode) node).value;
            AddConstStrToModule(str);
            var strConst = GetStrInfo(str);
            node.llvmName = strConst.valueName;
            node.irValue = generateUnnamedElementPtrInst(strConst, stringType, new IRIntConstant(0), new IRIntConstant(0));
        } else if (node instanceof ThisExpNode) {
            node.llvmName = "this";
            node.irValue = irCurrentScope.GetThis();
        }
    }


    private static class OpInfo {
        String opInfo;
        String instName;

        public OpInfo(String _opInfo, String _instName) {
            opInfo = _opInfo;
            instName = _instName;
        }
    }

    private OpInfo BinaryOpTransfer(BinaryExpNode.BinaryOp _op) {
        return switch (_op) {
            case PlusOp -> new OpInfo("add", "add");
            case MinusOp -> new OpInfo("sub", "sub");
            case MulOp -> new OpInfo("mul", "mul");
            case DivOp -> new OpInfo("sdiv", "sdiv");
            case ModOp -> new OpInfo("srem", "srem");


            //icmp + zext
            case GreaterOp -> new OpInfo("ugt", "icmp");
            case LessOp -> new OpInfo("ult", "icmp");
            case GreaterEqualOp -> new OpInfo("uge", "icmp");
            case LessEqualOp -> new OpInfo("ule", "icmp");
            case NotEqualOp -> new OpInfo("ne", "icmp");
            case EqualOp -> new OpInfo("eq", "icmp");

            //generate shortcut
            case LogicAndOp -> new OpInfo("and", "logic");
            case LogicOrOp -> new OpInfo("or", "logic");


            case BitAndOp -> new OpInfo("and", "and");
            case BitXorOp -> new OpInfo("xor", "xor");
            case BitOrOp -> new OpInfo("or", "or");
            case LeftShiftOp -> new OpInfo("shl", "shl");
            case RightShiftOp -> new OpInfo("ashr", "ashr");

            default -> throw new RuntimeException("[BinaryOpTransfer] Error: unknown binary op");
        };
    }

    @Override
    public void visit(BinaryExpNode node) {
        node.lhs.accept(this);
        node.rhs.accept(this);
        var lhsValue = getExpNodeValue(node.lhs);
        var rhsValue = getExpNodeValue(node.rhs);
        var opInfo = BinaryOpTransfer(node.operator);
        if (opInfo.instName.equals("icmp")) {
            var icmpInst = generateIcmpInst(opInfo.opInfo, lhsValue, rhsValue, currentBlock);
            currentBlock.addInst(icmpInst);
            node.irValue = icmpInst;
        }else if(opInfo.instName.equals("logic")){
            //todo
        }else{
            if(lhsValue.valueType instanceof IntegerType && rhsValue.valueType instanceof IntegerType) {
                var binaryInst = generateBinaryInst(opInfo.opInfo, INT32, lhsValue, rhsValue);
                currentBlock.addInst(binaryInst);
                node.irValue = binaryInst;
            }else{
                //for string
            }
        }


//        var binaryInst = generateBinaryInst(op, lhsValue, rhsValue, currentBlock);
//        currentBlock.addInst(binaryInst);
//        node.llvmName = binaryInst.llvmName;
//        node.irValue = binaryInst;
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
            var storeInst = new IRStoreInst(node.returnExp.irValue, irCurrentScope.currentIRFunction.retValPtr, currentBlock);
            currentBlock.addInst(storeInst);
        }
        var brInst = new IRBrInst(irCurrentScope.currentIRFunction.exitBlock, currentBlock);
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
        var funcName = irCurrentScope.currentClassType == null ?
                node.funcName : irCurrentScope.currentClassType.classId + "." + node.funcName;

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
            var loadInst = generateLoadInst(funcName + ".ret", allocaInst, CurFunc().exitBlock);
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
            var BrInst = generateUnConBrInst(CurFunc().exitBlock, currentBlock);
            currentBlock.AddTerminator(BrInst);
        }
        ExitFunc();
    }


    //scope control
    public void EnterClass(StructType _classTypeInfo) {
        irCurrentScope = new IRScope(true, _classTypeInfo, irCurrentScope.inFunc, irCurrentScope.currentIRFunction, false, null, null, irCurrentScope);
    }

    public void ExitClass() {
        irCurrentScope = irCurrentScope.parentScope;
    }

    public void EnterFunc(IRFunction _irFunctionInfo) {
        irCurrentScope = new IRScope(irCurrentScope.inClass, irCurrentScope.currentClassType, true, _irFunctionInfo, false, null, null, irCurrentScope);
    }

    public void ExitFunc() {
        irCurrentScope = irCurrentScope.parentScope;
    }

    public void EnterLoop(IRBasicBlock _loopExitBlock, IRBasicBlock _loopContinueBlock) {
        irCurrentScope = new IRScope(irCurrentScope.inClass, irCurrentScope.currentClassType, irCurrentScope.inFunc, irCurrentScope.currentIRFunction, true, _loopExitBlock, _loopContinueBlock, irCurrentScope);
    }

    public void ExitLoop() {
        irCurrentScope = irCurrentScope.parentScope;
    }

    //current info
    public StructType CurClass() {
        if (irCurrentScope.inClass) return irCurrentScope.currentClassType;
        else return null;
    }

    public IRFunction CurFunc() {
        if (irCurrentScope.inFunc) return irCurrentScope.currentIRFunction;
        else return null;
    }

    public IRBasicBlock CurLoopExitBlock() {
        if (irCurrentScope.inLoop) return irCurrentScope.loopExitBlock;
        else return null;
    }

    public IRBasicBlock CurLoopContinueBlock() {
        if (irCurrentScope.inLoop) return irCurrentScope.loopContinueBlock;
        else return null;
    }


    //globalVar init
    private IRFunction GenerateInitFunc() {
        FunctionType initFuncType = new FunctionType(new VoidType(), IRDefine.LLVM_INIT_FUNCTION);
        IRFunction initFunc = new IRFunction(IRDefine.LLVM_INIT_FUNCTION, initFuncType, false);
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

    //inst generate

    private IRInstruction i8Toi1(IRValue _i8Value) {
        if (_i8Value.valueType instanceof memBoolType) {
            var truncInst = generateTruncInst(_i8Value, new BoolType());
            currentBlock.addInst(truncInst);
            return truncInst;
        } else throw new RuntimeException("i8valueType should be memBoolType");
    }

    private IRInstruction i1Toi8(IRValue _i1Value) {
        if (_i1Value.valueType instanceof BoolType) {
            var zextInst = generateZextInst(_i1Value, new memBoolType());
            currentBlock.addInst(zextInst);
            return zextInst;
        } else throw new RuntimeException("i1valueType should be BoolType");
    }

    private IRInstruction generateAllocInst(String _allocName, BasicType _allocType) {
        var allocaType = _allocType;
        if (_allocType instanceof BoolType) allocaType = new memBoolType();
        return new IRAllocaInst(rename(_allocName + ".addr"), allocaType, CurFunc().entryBlock);
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
        return new IRGEPInst(rename(_elementName + ".addr"), _headPtr, currentBlock, _pointedType, indexes);
    }

    private IRInstruction generateUnnamedElementPtrInst(IRValue _headPtr, BasicType _pointedType, IRValue... indexes) {
        return new IRGEPInst(rename(LLVM_GEP_INST), _headPtr, currentBlock, _pointedType, indexes);
    }

    private IRInstruction generateIcmpInst(String _op, IRValue _lhs, IRValue _rhs, IRBasicBlock _parentBlock) {
        return new IRIcmpInst(rename(LLVM_ICMP_INST), _op, _lhs, _rhs, currentBlock);
    }

    private IRInstruction generateLoadInst(String _loadToAddr, IRValue _pointer, IRBasicBlock _parentBlock) {
        assert _pointer.valueType instanceof PointerType;
        assert !(((PointerType) _pointer.valueType).baseType instanceof BoolType);
        return new IRLoadInst(rename(_loadToAddr + ".load"), ((PointerType) _pointer.valueType).baseType, _pointer, _parentBlock);
    }

    private IRInstruction generateRetInst(IRValue _retValue, IRBasicBlock _parentBlock) {
        if (_retValue == null) return new IRRetInst(new VoidType(), null, _parentBlock);
        if (_retValue.valueType instanceof memBoolType) {
            var alteredRetValue = i8Toi1(_retValue);
            return new IRRetInst(alteredRetValue.valueType, alteredRetValue, _parentBlock);
        } else return new IRRetInst(_retValue.valueType, _retValue, _parentBlock);
    }

    private IRInstruction generateStoreInst(IRValue _storeValue, IRValue _destPtr, IRBasicBlock _parentBlock) {
        assert _destPtr.valueType instanceof PointerType;
        assert !(((PointerType) _destPtr.valueType).baseType instanceof BoolType);
        if (_storeValue.valueType instanceof BoolType)
            return new IRStoreInst(i1Toi8(_storeValue), _destPtr, _parentBlock);
        else return new IRStoreInst(_storeValue, _destPtr, _parentBlock);
    }

    private IRInstruction generateTruncInst(IRValue _fromValue, BasicType _targetType) {
        return new IRTruncInst(rename(LLVM_TRUNC_INST), _fromValue, _targetType, currentBlock);
    }

    private IRInstruction generateZextInst(IRValue _fromValue, BasicType _targetType) {
        return new IRZextInst(rename(LLVM_ZEXT_INST), _fromValue, _targetType, currentBlock);
    }
}
