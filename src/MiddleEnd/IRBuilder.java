package MiddleEnd;

import AST.ASTVisitor;
import AST.node.abstractNode.ExpNode;
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
import IR.IRModule;
import IR.Type.*;
import IR.Utils.Renamer;
import IR.Value.IRBasicBlock;
import IR.Value.IRDefine;
import IR.Value.IRValue;
import IR.Value.User.Constant.GlobalValue.IRFunction;
import IR.Value.User.Constant.IRBoolConst;
import IR.Value.User.Constant.IRIntConstant;
import IR.Value.User.Constant.IRNullptrConstant;
import IR.Value.User.Constant.IRStrConstant;
import IR.Value.User.Instruction.*;
import Utils.Position;
import Utils.scope.GlobalScope;
import Utils.scope.IRGlobalScope;
import Utils.scope.IRScope;

import javax.lang.model.type.NullType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

//import static IR.Utils.TypeTransLater.allocBoolType;


public class IRBuilder implements ASTVisitor, IRDefine {
    public IRModule projectIRModule;
    public IRGlobalScope irGlobalScope;
    public IRScope irCurrentScope;
    public IRBasicBlock currentBlock;
    public Renamer renamer;

    //varId(not raw) -> varAddr (varId is A.1,A.2 etc.)
    public HashMap<String, IRValue> varAddrMap = new HashMap<>();
//    for var only, the %add etc. will be stored in each Node

    //for memberFunc only, you can get every memberVar's GEP inst by this map,
    // you should conduct the inst and add the var to the varAddrMap and delete the version in this map
    public HashMap<String, IRInstruction> memberVarAddrMap = new HashMap<>();

//    public HashMap<String,IRInstruction> globalVarAddrMap = new HashMap<>();

    private IRValue GetVarAddrInCurrentScope(String _rawName) {//for A.B it's A.B
        var varId = irCurrentScope.GetVarId(_rawName);
        if (varId != null) {
            if (memberVarAddrMap.containsKey(varId)) {
                currentBlock.addInst(memberVarAddrMap.get(varId));
                varAddrMap.put(varId, memberVarAddrMap.get(varId));
                memberVarAddrMap.remove(varId);
                return varAddrMap.get(varId);
            } else if (varAddrMap.containsKey(varId)) {
                return varAddrMap.get(varId);
            } else {
                throw new RuntimeException("varAddrMap.get(varInfo) == null");
            }
        } else throw new RuntimeException("rawName = " + _rawName + ":varInfo == null");
    }

    //for var def only
//    private void AddVarDef(String _rawName, IRValue _addr) {
//        int index = renamer.getCurrentRank(_rawName + ".addr");
//        var varId = _rawName + "." + index;
//        varAddrMap.put(varId, _addr);
//        irCurrentScope.VarInfoMap.put(_rawName, varId);
//    }

    //about string
    private IRStrConstant GetStrInfo(String _str) {
        if (projectIRModule.IRStrConstantMap.containsKey(_str)) {
            return projectIRModule.IRStrConstantMap.get(_str);
        } else {
            IRStrConstant newStr = new IRStrConstant(renamer.rename(LLVM_STRING_IDENTIFIER), _str);
            projectIRModule.IRStrConstantMap.put(_str, newStr);
            return newStr;
        }
    }

    private void AddConstStrToModule(String _str) {
        if (!projectIRModule.IRStrConstantMap.containsKey(_str)) {
            IRStrConstant newStr = new IRStrConstant(renamer.rename(LLVM_STRING_IDENTIFIER), _str);
            projectIRModule.IRStrConstantMap.put(_str, newStr);
        }
    }

    //get value and add instruction
    private IRValue getExpNodeValue(ExpNode _node) {
        if (_node.irValue == null) {
            if (_node.irAddress == null) throw new RuntimeException("We know nothing about this node");
            _node.irValue = generateLoadInst(_node.llvmName, Objects.requireNonNull(getNodePointer(_node)), currentBlock);
            currentBlock.addInst((IRInstruction) _node.irValue);
            if (_node.irValue.valueType instanceof memBoolType) _node.irValue = i8Toi1(_node.irValue);
            return _node.irValue;
        }
//        if (_node.irValue instanceof IRInstruction) currentBlock.addInst((IRInstruction) _node.irValue);
        if (_node.irValue.valueType instanceof memBoolType) {
            throw new RuntimeException("getExpNodeValue: memBoolType");
        }
        return _node.irValue;
    }

    private IRValue getNodePointer(ExpNode _node) {
        if (_node.irAddress == null) throw new RuntimeException("[getNodePointer] We know nothing about this node");
        return _node.irAddress;

    }

    //real content
    public IRBuilder(IRModule _projectIRModule, GlobalScope _globalScope) {
        renamer = new Renamer();
        projectIRModule = _projectIRModule;
        var initFunc = GenerateInitFunc();
        this.currentBlock = null;
        projectIRModule.Init(_globalScope, initFunc);
        irGlobalScope = new IRGlobalScope();
        projectIRModule.IRGlobalVariableMap.forEach((key, gVariable) -> {
            var varId = renamer.rename(key);
            irGlobalScope.rawToIdMap.put(key, varId);
            varAddrMap.put(varId, gVariable);
        });
        irCurrentScope = irGlobalScope;
    }

    @Override
    public void visit(RootNode node) {
        EnterFunc(projectIRModule.initFunc);
        currentBlock = projectIRModule.initFunc.entryBlock;
        node.childNodes.forEach(childNode -> {
            if (childNode instanceof VarDefUnitNode || childNode instanceof VarDefStmtNode) childNode.accept(this);
        });

        var lastBlock = (CurFunc().blockList.size() == 0) ? CurFunc().entryBlock : CurFunc().blockList.getLast();
        var brInst = generateUnConBrInst(CurFunc().exitBlock, lastBlock);
        lastBlock.addTerminator(brInst);

        ExitFunc();
        currentBlock = null;

        node.childNodes.forEach(childNode -> {
            if (!(childNode instanceof VarDefUnitNode || childNode instanceof VarDefStmtNode)) {
                if (childNode instanceof FuncDefNode && ((FuncDefNode) childNode).isBuiltin) {
                    //do nothing
                    if (((FuncDefNode) childNode).funcName.equals("size")) childNode.accept(this);
                } else {
                    childNode.accept(this);
                }
            }
        });

        projectIRModule.testPrint();
    }

    @Override
    public void visit(ClassDefNode node) {
        if (node.className.equals("string")) return;
        EnterClass(projectIRModule.translateByString(node.className));

//        CurClass().memberOffset.forEach((key, value) -> {
//            var varId = renamer.rename(key);
//            irCurrentScope.rawToIdMap.put(key, varId);
//            memberVarAddrMap.put(varId, null);//由各个成员函数补充
//        });

        node.constructorDefNode.accept(this);
        node.memberFuncMap.forEach((name, funcDefNode) -> funcDefNode.accept(this));
        ExitClass();
    }

    @Override
    public void visit(FuncDefNode node) {
        boolean isMember = irCurrentScope.currentClassType != null;

        if (node.funcName.equals("println")) {
            System.out.println("debug");
        }


        var funcName = isMember ? irCurrentScope.currentClassType.classId + "." + node.funcName : node.funcName;
        var IRFunc = getIRFunction(funcName);

        if (IRFunc == null) throw new RuntimeException("IRFunc == null");

        EnterFunc(IRFunc);

        //construct func.entry block
        CurFunc().entryBlock = new IRBasicBlock(renamer.rename(funcName + ".entry"));
        //construct func.exit block
        CurFunc().exitBlock = new IRBasicBlock(renamer.rename(funcName + ".exit"));

        assert IRFunc.argNameList.size() == IRFunc.argTypeList.size();

        currentBlock = CurFunc().entryBlock;

        for (int i = 0; i < IRFunc.argNameList.size(); i++) {//process the perimeter
            if (isMember && i == 0) {
                String argName = "this";
                var argType = IRFunc.argTypeList.get(0);
                var argId = renamer.rename(argName);
                var argAddr = new IRValue(argId, argType);
                irCurrentScope.rawToIdMap.put(argName, argId);
                varAddrMap.put(argId, argAddr);
                IRFunc.argNameList.set(0, argId);
                IRFunc.addArg(argAddr);
                continue;
            }
            String argName = IRFunc.argNameList.get(i);
            var argType = IRFunc.argTypeList.get(i);
            var allocaType = argType;
            if (argType instanceof BoolType) allocaType = new memBoolType();
            // todo:check boolType(done)
            String fakeVarId = renamer.rename(argName);
            String realVarId = renamer.rename(argName + ".addr");
            var argAllocInst = generateAllocInst(realVarId, allocaType, currentBlock);
            currentBlock.addInstFirst(argAllocInst);
            var storeInst = generateStoreInst(new IRValue(fakeVarId, argType), argAllocInst, currentBlock);
            currentBlock.addInst(storeInst);
            varAddrMap.put(realVarId, argAllocInst);
            irCurrentScope.rawToIdMap.put(argName, realVarId);
            IRFunc.argNameList.set(i, fakeVarId);
            IRFunc.addArg(argAllocInst);
        }


        if (isMember) {
            var thisPtr = irCurrentScope.GetThis();
            StructType parentType = (StructType) IRFunc.parentClassType;
            parentType.memberOffset.forEach((memberName, offset) -> {
                var memberType = parentType.memberTypeList.get(offset);
                var varId = renamer.rename(memberName + ".addr");
                var memberPtrInst = generateNamedElementPtrInst(varId, thisPtr, memberType, currentBlock, new IRIntConstant(0), new IRIntConstant(offset));
                if (!irCurrentScope.rawToIdMap.containsKey(memberName)) {
                    irCurrentScope.rawToIdMap.put(memberName, varId);
                    memberVarAddrMap.put(varId, memberPtrInst);
                }
            });
        }


        if (node.funcName.equals("main")) {
            var callInst = generateCallInst(projectIRModule.initFunc, currentBlock, new ArrayList<>());
            currentBlock.addInst(callInst);
        }


        if (!(CurFunc().returnType instanceof VoidType)) {
            var allocaInst = generateAllocInst(renamer.rename(funcName + ".ret.addr"), CurFunc().returnType, currentBlock);
            CurFunc().retValPtr = allocaInst;
            currentBlock.addInstFirst(allocaInst);
            if (irCurrentScope.currentIRFunction.funcName.equals("main")) {
                var storeInst = generateStoreInst(MAIN_DEFAULT_RETURN, allocaInst, currentBlock);
                currentBlock.addInst(storeInst);
            }
            currentBlock = CurFunc().exitBlock;
            var loadInst = generateLoadInst(funcName + ".ret", allocaInst, currentBlock);
            currentBlock.addInst(loadInst);
            var retInst = generateRetInst(CurFunc().returnType, loadInst, currentBlock);
            currentBlock.addTerminator(retInst);
        } else {
            currentBlock = CurFunc().exitBlock;
            var retInst = generateRetInst(new VoidType(), null, CurFunc().exitBlock);
            CurFunc().exitBlock.addTerminator(retInst);
        }

        currentBlock = CurFunc().entryBlock;

        if (node.funcBodyNode != null) node.funcBodyNode.accept(this);


        var brList = generateUnConBrInst(CurFunc().exitBlock, currentBlock);
        currentBlock.addTerminator(brList);

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
            assert irCurrentScope == irGlobalScope;
            String varId = irGlobalScope.GetVarId(node.varName);
            if (varId == null) {
                varId = renamer.rename(node.varName + ".addr");
                irCurrentScope.rawToIdMap.put(node.varName, varId);
                //todo : why not boolType?
                BasicType varType = projectIRModule.translateAllocaType(node.varType);
                allocPtr = generateAllocInst(varId, varType, currentBlock);
                varAddrMap.put(varId, allocPtr);
                CurFunc().entryBlock.addInstFirst((IRInstruction) allocPtr);
            } else allocPtr = varAddrMap.get(varId);
        } else {
            String varId = renamer.rename(node.varName + ".addr");
            irCurrentScope.rawToIdMap.put(node.varName, varId);
            //todo : why not boolType?
            BasicType varType = projectIRModule.translateAllocaType(node.varType);
            allocPtr = generateAllocInst(varId, varType, currentBlock);
            varAddrMap.put(varId, allocPtr);
            CurFunc().entryBlock.addInstFirst((IRInstruction) allocPtr);
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
        //TODO: indexExp
        node.array.accept(this);
        node.index.accept(this);
        var arrayPtr = getExpNodeValue(node.array);
        var indexValue = getExpNodeValue(node.index);
        var pointedType = ((PointerType) arrayPtr.valueType).Dereference();
        var gepInst = generateUnnamedElementPtrInst(arrayPtr, pointedType, currentBlock, indexValue);
        currentBlock.addInst(gepInst);
        node.irAddress = gepInst;
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

    public String getRawString(String str) {
        return str.replace("\\n", "\n")
                .replace("\\t", "\t")
                .replace("\\\\", "\\")
                .replace("\\\"", "\"");
    }

    @Override
    public void visit(AtomExpNode node) {
        if (node instanceof BoolExpNode) {
            node.llvmName = "";
            node.irValue = new IRBoolConst(((BoolExpNode) node).value);
        } else if (node instanceof IdExpNode) {
            node.llvmName = ((IdExpNode) node).id;
            if (!node.isMember) {
                if (node.isFunction) {
//                    if (((IdExpNode) node).id.equals("size")) {
//                        if()
//                        node.irValue = new IRValue(new VoidType());//this doesn't matter
//                    } else {
                        if (irCurrentScope.inClass) {
                            String className = CurClass().classId;
                            String memFuncName = className + "." + node.llvmName;
                            if (projectIRModule.IRFunctionMap.containsKey(memFuncName)) {
                                node.irValue = getIRFunction(memFuncName);
                            } else node.irValue = getIRFunction(node.llvmName);
                        } else node.irValue = getIRFunction(node.llvmName);
//                    }
                } else node.irAddress = GetVarAddrInCurrentScope(node.llvmName);
                //for memberFunc and memberVar, we process it in the member Exp
            } else throw new RuntimeException("[AtomExpNode] Error: member not supported");
        } else if (node instanceof IntExpNode) {
            node.llvmName = "";
            node.irValue = new IRIntConstant(((IntExpNode) node).value);
        } else if (node instanceof NullExpNode) {
            node.llvmName = "";
            node.irValue = new IRNullptrConstant();
        } else if (node instanceof StringExpNode) {
            String str = getRawString(((StringExpNode) node).value);
            AddConstStrToModule(str);
            var strConst = GetStrInfo(str);
            node.llvmName = strConst.valueName;
            var gepInst = generateUnnamedElementPtrInst(strConst, new IntegerType(8), currentBlock, new IRIntConstant(0), new IRIntConstant(0));
            node.irValue = gepInst;
            currentBlock.addInst(gepInst);
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
            case GreaterOp -> new OpInfo("sgt", "icmp");
            case LessOp -> new OpInfo("slt", "icmp");
            case GreaterEqualOp -> new OpInfo("sge", "icmp");
            case LessEqualOp -> new OpInfo("sle", "icmp");
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


    private void stringBinary(BinaryExpNode node) {
        //todo: stringBinary
        node.lhs.accept(this);
        node.rhs.accept(this);
        var lhsValue = getExpNodeValue(node.lhs);
        var rhsValue = getExpNodeValue(node.rhs);
        var opInfo = BinaryOpTransfer(node.operator);
        var opInfoStr = opInfo.opInfo;
        var instName = opInfo.instName;
        if (instName.equals("icmp")) {
            String funcName = "_str_" + opInfoStr;
            var IRFunc = getIRFunction(funcName);
            var callInst = generateCallInst(IRFunc, currentBlock, lhsValue, rhsValue);
            currentBlock.addInst(callInst);
            node.irValue = callInst;
        } else if (opInfoStr.equals("add")) {
            String funcName = "_str_concat";
            var IRFunc = getIRFunction(funcName);
            var callInst = generateCallInst(IRFunc, currentBlock, lhsValue, rhsValue);
            currentBlock.addInst(callInst);
            node.irValue = callInst;
        }
    }

    @Override
    public void visit(BinaryExpNode node) {
        node.lhs.accept(this);
        var lhsValue = getExpNodeValue(node.lhs);
        if (lhsValue.valueType.equals(stringType)) {
//            stringBinary(node);//using this func seems will cause great consumption

//            node.lhs.accept(this);
            node.rhs.accept(this);
//            var lhsValue = getExpNodeValue(node.lhs);
            var rhsValue = getExpNodeValue(node.rhs);
            var opInfo = BinaryOpTransfer(node.operator);
            var opInfoStr = opInfo.opInfo;
            var instName = opInfo.instName;
            if (instName.equals("icmp")) {
                String funcName = "_str_" + opInfoStr;
                var IRFunc = getIRFunction(funcName);
                var callInst = generateCallInst(IRFunc, currentBlock, lhsValue, rhsValue);
                currentBlock.addInst(callInst);
                node.irValue = callInst;
            } else if (opInfoStr.equals("add")) {
                String funcName = "_str_concat";
                var IRFunc = getIRFunction(funcName);
                var callInst = generateCallInst(IRFunc, currentBlock, lhsValue, rhsValue);
                currentBlock.addInst(callInst);
                node.irValue = callInst;
            }

            return;
        }
        var opInfo = BinaryOpTransfer(node.operator);
        if (opInfo.instName.equals("icmp")) {
            node.rhs.accept(this);
            var rhsValue = getExpNodeValue(node.rhs);
            var icmpInst = generateIcmpInst(opInfo.opInfo, lhsValue, rhsValue, currentBlock);
            currentBlock.addInst(icmpInst);
            node.irValue = icmpInst;
        } else if (opInfo.instName.equals("logic")) {
            //todo
            assert lhsValue.valueType instanceof BoolType;
            IRBasicBlock noShortCutBlock = new IRBasicBlock(renamer.rename("noShortCutBlock"));
            IRBasicBlock logicExitBlock = new IRBasicBlock(renamer.rename("logicExitBlock"));
            //to eliminate PHI
            var allocTmpInst = generateAllocInst(renamer.rename("tmp.addr"), boolType, CurFunc().entryBlock);
            CurFunc().entryBlock.addInstFirst(allocTmpInst);

            var storeInst = generateStoreInst(lhsValue, allocTmpInst, currentBlock);
            currentBlock.addInst(storeInst);
            CurFunc().addBlock(noShortCutBlock);//对于全局变量，init_func保证了其在函数内部
            CurFunc().addBlock(logicExitBlock);
            IRInstruction condBrInst;
            if (node.operator == BinaryExpNode.BinaryOp.LogicAndOp) {
                condBrInst = generateConBrInst(lhsValue, noShortCutBlock, logicExitBlock, currentBlock);
                currentBlock.addTerminator(condBrInst);
                currentBlock = noShortCutBlock;
                node.rhs.accept(this);
                var rhsValue = getExpNodeValue(node.rhs);
                var andInst = generateBinaryInst("and", boolType, lhsValue, rhsValue, currentBlock);
                currentBlock.addInst(andInst);
                var storeInst2 = generateStoreInst(andInst, allocTmpInst, currentBlock);
                currentBlock.addInst(storeInst2);
                var brInst = generateUnConBrInst(logicExitBlock, currentBlock);
                currentBlock.addTerminator(brInst);
            } else {
                condBrInst = generateConBrInst(lhsValue, logicExitBlock, noShortCutBlock, currentBlock);
                currentBlock.addTerminator(condBrInst);
                currentBlock = noShortCutBlock;
                node.rhs.accept(this);
                var rhsValue = getExpNodeValue(node.rhs);
                var orInst = generateBinaryInst("or", boolType, lhsValue, rhsValue, currentBlock);
                currentBlock.addInst(orInst);
                var storeInst2 = generateStoreInst(orInst, allocTmpInst, currentBlock);
                currentBlock.addInst(storeInst2);
                var brInst = generateUnConBrInst(logicExitBlock, currentBlock);
                currentBlock.addTerminator(brInst);
            }
            node.irAddress = allocTmpInst;
            currentBlock = logicExitBlock;
        } else {
            node.rhs.accept(this);
            var rhsValue = getExpNodeValue(node.rhs);
            if (!lhsValue.valueType.equals(rhsValue.valueType))
                throw new RuntimeException("lhsValue.valueType != rhsValue.valueType");
//            assert lhsValue.valueType instanceof IntegerType && rhsValue.valueType instanceof IntegerType;

            var binaryInst = generateBinaryInst(opInfo.opInfo, lhsValue.valueType, lhsValue, rhsValue, currentBlock);
            currentBlock.addInst(binaryInst);
            node.irValue = binaryInst;
        }
        node.llvmName = opInfo.opInfo;
    }

    @Override
    public void visit(FuncCallExpNode node) {
        node.function.isFunction = true;
        node.function.accept(this);
        if (!(getExpNodeValue(node.function) instanceof IRFunction func)) {
            //for .size()
            node.irValue = getExpNodeValue(node.function);
            return;
        }
        assert node.function.irValue instanceof IRFunction;
        ArrayList<IRValue> argsList = new ArrayList<>();

//        System.out.println(func.parentClassType);
//        System.out.println(func.funcName);

        if (func.parentClassType != null) {
            if (node.function instanceof MemberExpNode) {
                var thisPtr = getExpNodeValue(((MemberExpNode) node.function).base);//change from getPointer
                if (((MemberExpNode) node.function).base.irValue.valueType.equals(stringType)) {
                    thisPtr = getExpNodeValue(((MemberExpNode) node.function).base);
                }
                argsList.add(thisPtr);//c++ will allocate the class var
            } else argsList.add(irCurrentScope.GetThis());
        }
        for (var arg : node.paraList) {
            arg.accept(this);
            argsList.add(getExpNodeValue(arg));
        }
        var callInst = generateCallInst(func, currentBlock, argsList);
        currentBlock.addInst(callInst);
        node.irValue = callInst;
    }

    @Override
    public void visit(LambdaExpNode node) {
    }

    @Override
    public void visit(MemberExpNode node) {
        node.isMember = true;
        node.base.accept(this);
//        var basePtr = getNodePointer(node.base);
        var baseVar = getExpNodeValue(node.base);//a pointer
//        assert baseVar.valueType instanceof PointerType;

        var baseType = baseVar.valueType;

        if (node.isFunction) {
            if (baseType.equals(stringType)) {
                String funcName = "_str_" + node.memberName;
                node.irValue = getIRFunction(funcName);
            } else if (((PointerType) baseType).baseType instanceof StructType structType) {
                String funcName = structType.classId + "." + node.memberName;
                node.irValue = getIRFunction(funcName);
            } else {//for .size()
                var bitCastInst = generateBitCastInst(baseVar, INT32Star, currentBlock);
                currentBlock.addInst(bitCastInst);
                var getSizePtr = generateUnnamedElementPtrInst(bitCastInst, INT32, currentBlock, new IRIntConstant(-1));
                currentBlock.addInst(getSizePtr);
                var loadInst = generateLoadInst("array", getSizePtr, currentBlock);
                currentBlock.addInst(loadInst);
                node.irValue = loadInst;
            }
        } else {
            StructType structType = (StructType) ((PointerType) baseType).Dereference();
            Integer varRank = projectIRModule.getMemberVarRank(structType.classId, node.memberName);
            BasicType varType = projectIRModule.getMemberVarType(structType.classId, node.memberName);
            var namedGEPInst = generateNamedElementPtrInst(renamer.rename(node.memberName), baseVar, varType, currentBlock, new IRIntConstant(0), new IRIntConstant(varRank));
            currentBlock.addInst(namedGEPInst);
            node.irAddress = namedGEPInst;
        }
    }


    //_memberType: is for this level of array
    IRValue newSingleArray(BasicType _headType, Integer _dim, ArrayList<ExpNode> _dimList, Position _pos) {

        _dimList.get(0).accept(this);
        var dimValue = getExpNodeValue(_dimList.get(0));
        var realSize = generateBinaryInst("mul", INT32, dimValue, new IRIntConstant(POINTER_SIZE), currentBlock);
        currentBlock.addInst(realSize);
        var mallocSize = generateBinaryInst("add", INT32, realSize, new IRIntConstant(POINTER_SIZE), currentBlock);
        currentBlock.addInst(mallocSize);
        var mallocInst = generateCallInst(getIRFunction("_malloc"), currentBlock, mallocSize);
        currentBlock.addInst(mallocInst);
        //put size
        var bitCastInst = generateBitCastInst(mallocInst, INT32Star, currentBlock);
        currentBlock.addInst(bitCastInst);
        var storeInst = generateStoreInst(dimValue, bitCastInst, currentBlock);
        currentBlock.addInst(storeInst);
        //put head
        var headPtr = generateUnnamedElementPtrInst(bitCastInst, INT32, currentBlock, new IRIntConstant(1));
        currentBlock.addInst(headPtr);
        var realHeadPtr = generateBitCastInst(headPtr, _headType, currentBlock);
        currentBlock.addInst(realHeadPtr);


//        var storeHeadInst = generateStoreInst(realHeadPtr, _headPtr, currentBlock);
//        currentBlock.addInst(storeHeadInst);

        if (_dimList.size() == 1) {
            return realHeadPtr;
        }

        String tmpVarName = renamer.rename("_new_tmp");
        IdExpNode tmpVar = new IdExpNode(tmpVarName, _pos);
        VarDefUnitNode varDefUnitNode = new VarDefUnitNode(new Type("int", 0, false), tmpVarName, false, _pos);
        varDefUnitNode.initValue = new IntExpNode(0, _pos);

        BinaryExpNode condExpNode = new BinaryExpNode(_pos);
        condExpNode.operator = BinaryExpNode.BinaryOp.LessOp;
        condExpNode.lhs = tmpVar;
        condExpNode.rhs = _dimList.get(0);

        SuffixExpNode stepExpNode = new SuffixExpNode(tmpVar, "++", _pos);

        IRBasicBlock forCondBlock = new IRBasicBlock(renamer.rename(FOR_COND_LABEL));
        IRBasicBlock forBodyBlock = new IRBasicBlock(renamer.rename(FOR_BODY_LABEL));
        IRBasicBlock forStepBlock = new IRBasicBlock(renamer.rename(FOR_STEP_LABEL));
        IRBasicBlock forExitBlock = new IRBasicBlock(renamer.rename(FOR_EXIT_LABEL));

        varDefUnitNode.accept(this);


        CurFunc().addBlock(forCondBlock);
        CurFunc().addBlock(forBodyBlock);
        CurFunc().addBlock(forStepBlock);
        CurFunc().addBlock(forExitBlock);


        var brInst = generateUnConBrInst(forCondBlock, currentBlock);
        currentBlock.addTerminator(brInst);

        currentBlock = forCondBlock;

        condExpNode.accept(this);
        var condVal = getExpNodeValue(condExpNode);
        var condBrInst = generateConBrInst(condVal, forBodyBlock, forExitBlock, currentBlock);
        currentBlock.addTerminator(condBrInst);

        currentBlock = forStepBlock;
        stepExpNode.accept(this);
        var brInst4 = generateUnConBrInst(forCondBlock, currentBlock);
        currentBlock.addTerminator(brInst4);

        EnterLoop(forExitBlock, forStepBlock);

        currentBlock = forBodyBlock;
        var newDimList = new ArrayList<>(_dimList.subList(1, _dimList.size()));
        var index = getExpNodeValue(tmpVar);
        var subArrayHeadType = ((PointerType) _headType).Dereference();
        var headPtrForSubArray = generateUnnamedElementPtrInst(realHeadPtr, subArrayHeadType, currentBlock, index);
        currentBlock.addInst(headPtrForSubArray);

        var newSubArrayHead = newSingleArray(subArrayHeadType, _dim - 1, newDimList, _pos);


        var storeSubHead = generateStoreInst(newSubArrayHead, headPtrForSubArray, currentBlock);
        currentBlock.addInst(storeSubHead);
        var brInst3 = generateUnConBrInst(forStepBlock, currentBlock);
        currentBlock.addTerminator(brInst3);

        currentBlock = forExitBlock;
        ExitLoop();

        return realHeadPtr;
    }


    @Override
    public void visit(NewExpNode node) {
        //todo: NewExp
        String typeName = node.resultType.typeName;
        Type retType = new Type(typeName, node.resultType.dimSize, false);
        BasicType headType = projectIRModule.translateAllocaType(retType);//when translate type, structType will add pointer
        int dimSize = node.resultType.dimSize;
        if (node.SizeList.size() == 0) {
            var classType = projectIRModule.translateByString(typeName);
            var mallocInst = generateCallInst(getIRFunction("_malloc"), currentBlock, new IRIntConstant(classType.size()));
            currentBlock.addInst(mallocInst);
            var bitCastInst = generateBitCastInst(mallocInst, new PointerType(classType), currentBlock);
            currentBlock.addInst(bitCastInst);
            var callInst = generateCallInst(getIRFunction(typeName + "." + typeName), currentBlock, bitCastInst);
            currentBlock.addInst(callInst);
            node.irValue = bitCastInst;
        } else node.irValue = newSingleArray(headType, dimSize, node.SizeList, node.nodePos);

    }

    @Override
    public void visit(PrefixExpNode node) {
        node.isFunction = false;
        node.value.accept(this);
        var valuePtr = getNodePointer(node.value);
        var valueVal = getExpNodeValue(node.value);

        if (node.op.equals("++")) {
            var addInst = generateBinaryInst("add", INT32, valueVal, new IRIntConstant(1), currentBlock);
            currentBlock.addInst(addInst);
            var storeInst = generateStoreInst(addInst, valuePtr, currentBlock);
            currentBlock.addInst(storeInst);
            node.irValue = addInst;
            node.irAddress = valuePtr;
        } else {
            var subInst = generateBinaryInst("sub", INT32, valueVal, new IRIntConstant(1), currentBlock);
            currentBlock.addInst(subInst);
            var storeInst = generateStoreInst(subInst, valuePtr, currentBlock);
            currentBlock.addInst(storeInst);
            node.irValue = subInst;
            node.irAddress = valuePtr;
        }
    }

    @Override
    public void visit(SuffixExpNode node) {
        node.isFunction = false;
        node.value.accept(this);
        var valuePtr = getNodePointer(node.value);
        var valueVal = getExpNodeValue(node.value);
        if (node.op.equals("++")) {
            var addInst = generateBinaryInst("add", INT32, valueVal, new IRIntConstant(1), currentBlock);
            currentBlock.addInst(addInst);
            var storeInst = generateStoreInst(addInst, valuePtr, currentBlock);
            currentBlock.addInst(storeInst);
        } else {
            var subInst = generateBinaryInst("sub", INT32, valueVal, new IRIntConstant(1), currentBlock);
            currentBlock.addInst(subInst);
            var storeInst = generateStoreInst(subInst, valuePtr, currentBlock);
            currentBlock.addInst(storeInst);
        }
        node.irValue = valueVal;
        node.irAddress = null;
    }

    @Override
    public void visit(UnaryExpNode node) {
        node.isFunction = false;
        node.value.accept(this);
        var valueVal = getExpNodeValue(node.value);
        switch (node.op) {
            case "!" -> {
                var notInst = generateBinaryInst("xor", valueVal.valueType, valueVal, new IRBoolConst(true), currentBlock);
                currentBlock.addInst(notInst);
                node.irValue = notInst;
            }
            case "-" -> {
                var negInst = generateBinaryInst("sub", valueVal.valueType, new IRIntConstant(0), valueVal, currentBlock);
                currentBlock.addInst(negInst);
                node.irValue = negInst;
            }
            case "+" -> node.irValue = valueVal;
            default -> {
                var bitNotInst = generateBinaryInst("xor", valueVal.valueType, valueVal, new IRIntConstant(-1), currentBlock);
                currentBlock.addInst(bitNotInst);
                node.irValue = bitNotInst;
            }
        }
    }

    @Override
    public void visit(BreakStmtNode node) {
        var brInst = generateUnConBrInst(CurLoopExitBlock(), currentBlock);
        currentBlock.addTerminator(brInst);
    }

    @Override
    public void visit(ContinueStmtNode node) {
        var brInst = generateUnConBrInst(CurLoopContinueBlock(), currentBlock);
        currentBlock.addTerminator(brInst);
    }

    @Override
    public void visit(EmptyStmtNode node) {
    }

    @Override
    public void visit(ExpStmtNode node) {
        node.expNode.accept(this);
    }

    @Override
    public void visit(ForStmtNode node) {

        IRBasicBlock forCondBlock = new IRBasicBlock(renamer.rename(FOR_COND_LABEL));
        IRBasicBlock forBodyBlock = new IRBasicBlock(renamer.rename(FOR_BODY_LABEL));
        IRBasicBlock forStepBlock = new IRBasicBlock(renamer.rename(FOR_STEP_LABEL));
        IRBasicBlock forExitBlock = new IRBasicBlock(renamer.rename(FOR_EXIT_LABEL));



        CurFunc().addBlock(forCondBlock);
        CurFunc().addBlock(forBodyBlock);
        CurFunc().addBlock(forStepBlock);
        CurFunc().addBlock(forExitBlock);

        if (node.initExpNode != null) node.initExpNode.accept(this);
        if (node.initVarDefUnitList != null) node.initVarDefUnitList.forEach(varDefUnit -> varDefUnit.accept(this));


        var brInst = generateUnConBrInst(forCondBlock, currentBlock);
        currentBlock.addTerminator(brInst);

        currentBlock = forCondBlock;
        if (node.condExpNode != null) {
            node.condExpNode.accept(this);
            var condVal = getExpNodeValue(node.condExpNode);
            var condBrInst = generateConBrInst(condVal, forBodyBlock, forExitBlock, currentBlock);
            currentBlock.addTerminator(condBrInst);
        } else {
            var brInst2 = generateUnConBrInst(forBodyBlock, currentBlock);
            currentBlock.addTerminator(brInst2);
        }

        currentBlock = forStepBlock;
        if (node.stepExpNode != null) node.stepExpNode.accept(this);
        var brInst4 = generateUnConBrInst(forCondBlock, currentBlock);
        currentBlock.addTerminator(brInst4);

        EnterLoop(forExitBlock, forStepBlock);

        currentBlock = forBodyBlock;
        node.bodyStmtNode.accept(this);
        var brInst3 = generateUnConBrInst(forStepBlock, currentBlock);
        currentBlock.addTerminator(brInst3);



        currentBlock = forExitBlock;
        ExitLoop();
    }

    @Override
    public void visit(IfStmtNode node) {
        IRBasicBlock ifTrueBlock = new IRBasicBlock(renamer.rename(IF_TRUE_LABEL));
        IRBasicBlock ifFalseBlock = new IRBasicBlock(renamer.rename(IF_FALSE_LABEL));
        IRBasicBlock ifExitBlock = new IRBasicBlock(renamer.rename(IF_EXIT_LABEL));
        CurFunc().addBlock(ifTrueBlock);
        CurFunc().addBlock(ifFalseBlock);
        CurFunc().addBlock(ifExitBlock);

        node.condExpNode.accept(this);
        var condVal = getExpNodeValue(node.condExpNode);
        var condBrInst = generateConBrInst(condVal, ifTrueBlock, ifFalseBlock, currentBlock);
        currentBlock.addTerminator(condBrInst);

        currentBlock = ifTrueBlock;
        node.trueStmtList.forEach(stmt -> stmt.accept(this));
        var brInst = generateUnConBrInst(ifExitBlock, currentBlock);
        currentBlock.addTerminator(brInst);

        currentBlock = ifFalseBlock;
        if (node.elseStmtList != null) {
            node.elseStmtList.forEach(stmt -> stmt.accept(this));
        }
        var brInst2 = generateUnConBrInst(ifExitBlock, currentBlock);
        currentBlock.addTerminator(brInst2);

        currentBlock = ifExitBlock;
    }

    @Override
    public void visit(ReturnStmtNode node) {
        if (node.returnExp != null) {
            node.returnExp.accept(this);
            var retVal = getExpNodeValue(node.returnExp);
            var storeInst = generateStoreInst(retVal, CurFunc().retValPtr, currentBlock);
            currentBlock.addInst(storeInst);
        }
        var brInst = generateUnConBrInst(CurFunc().exitBlock, currentBlock);
        currentBlock.addTerminator(brInst);
    }

    @Override
    public void visit(WhileStmtNode node) {
        IRBasicBlock whileCondBlock = new IRBasicBlock(renamer.rename(WHILE_COND_LABEL));
        IRBasicBlock whileBodyBlock = new IRBasicBlock(renamer.rename(WHILE_BODY_LABEL));
        IRBasicBlock whileExitBlock = new IRBasicBlock(renamer.rename(WHILE_EXIT_LABEL));
        CurFunc().addBlock(whileCondBlock);
        CurFunc().addBlock(whileBodyBlock);
        CurFunc().addBlock(whileExitBlock);

        var brInst = generateUnConBrInst(whileCondBlock, currentBlock);
        currentBlock.addTerminator(brInst);

        currentBlock = whileCondBlock;
        node.condExpNode.accept(this);
        var condVal = getExpNodeValue(node.condExpNode);
        var condBrInst = generateConBrInst(condVal, whileBodyBlock, whileExitBlock, currentBlock);
        currentBlock.addTerminator(condBrInst);

        EnterLoop(whileExitBlock, whileCondBlock);
        currentBlock = whileBodyBlock;
        node.bodyStmtList.forEach(stmt -> stmt.accept(this));
        var brInst2 = generateUnConBrInst(whileCondBlock, currentBlock);
        currentBlock.addTerminator(brInst2);//此时 currentBlock 不一定为 whileBodyBlock

        ExitLoop();
        currentBlock = whileExitBlock;

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
        var funcName = irCurrentScope.currentClassType.classId + "." + node.funcName;
        var IRFunc = getIRFunction(funcName);

        if (IRFunc == null) throw new RuntimeException("IRFunc == null");

        IRFunc.returnType = new VoidType();

        EnterFunc(IRFunc);
        //construct func.entry block
        CurFunc().entryBlock = new IRBasicBlock(renamer.rename(funcName + ".entry"));
        //construct func.exit block
        CurFunc().exitBlock = new IRBasicBlock(renamer.rename(funcName + ".exit"));

        String argName = "this";
        var argType = IRFunc.argTypeList.get(0);
        var argId = renamer.rename(argName);
        var argAddr = new IRValue(argId, argType);
        irCurrentScope.rawToIdMap.put(argName, argId);
        varAddrMap.put(argId, argAddr);
        IRFunc.argNameList.set(0, argId);
        IRFunc.addArg(argAddr);


        currentBlock = CurFunc().entryBlock;
        var thisPtr = irCurrentScope.GetThis();
        StructType parentType = (StructType) IRFunc.parentClassType;
        parentType.memberOffset.forEach((memberName, offset) -> {
            var memberType = parentType.memberTypeList.get(offset);
            var varId = renamer.rename(memberName + ".addr");
            var memberPtrInst = generateNamedElementPtrInst(varId, thisPtr, memberType, currentBlock, new IRIntConstant(0), new IRIntConstant(offset));

            if (!irCurrentScope.rawToIdMap.containsKey(memberName)) {
                irCurrentScope.rawToIdMap.put(memberName, varId);
                memberVarAddrMap.put(varId, memberPtrInst);
            }
        });


        currentBlock = CurFunc().exitBlock;
        var retInst = generateRetInst(new VoidType(), null, CurFunc().exitBlock);
        CurFunc().exitBlock.addTerminator(retInst);


//        assert currentBlock.blockName.equals(CurFunc().entryBlock.blockName);
        currentBlock = CurFunc().entryBlock;

        if (node.funcBodyNode != null) node.funcBodyNode.accept(this);


        var lastBlock = (IRFunc.blockList.size() == 0) ? IRFunc.entryBlock : IRFunc.blockList.getLast();
        var brList = generateUnConBrInst(CurFunc().exitBlock, lastBlock);
        lastBlock.addTerminator(brList);

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


    public IRFunction getIRFunction(String funcName) {
        return projectIRModule.getIRFunction(funcName);
    }


    //globalVar init
    private IRFunction GenerateInitFunc() {
        FunctionType initFuncType = new FunctionType(new VoidType(), IRDefine.LLVM_INIT_FUNCTION);
        IRFunction initFunc = new IRFunction(initFuncType, IRDefine.LLVM_INIT_FUNCTION, null, false, null);
        initFunc.entryBlock = new IRBasicBlock(IRDefine.LLVM_INIT_FUNCTION + ".entry");
        //construct func.exit block
        initFunc.exitBlock = new IRBasicBlock(IRDefine.LLVM_INIT_FUNCTION + ".exit");
        //jump to exit block
//        var brInst = generateUnConBrInst(initFunc.exitBlock, initFunc.entryBlock);
//        initFunc.entryBlock.addTerminator(brInst);
        //return void
        var retInst = generateRetInst(initFunc.returnType, null, initFunc.exitBlock);
        initFunc.exitBlock.addTerminator(retInst);
        return initFunc;
    }

    //inst generate

    private IRInstruction i8Toi1(IRValue _i8Value) {
        if (_i8Value.valueType instanceof memBoolType) {
            var truncInst = generateTruncInst(_i8Value, new BoolType(), currentBlock);
            currentBlock.addInst(truncInst);
            return truncInst;
        } else throw new RuntimeException("i8valueType should be memBoolType");
    }

    private IRInstruction i1Toi8(IRValue _i1Value) {
        if (_i1Value.valueType instanceof BoolType) {
            var zextInst = generateZextInst(_i1Value, new memBoolType(), currentBlock);
            currentBlock.addInst(zextInst);
            return zextInst;
        } else throw new RuntimeException("i1valueType should be BoolType");
    }


    private IRInstruction generateAllocInst(String _allocName, BasicType _allocType, IRBasicBlock _parentBlock) {
        var allocaType = _allocType;

        if (_allocType instanceof BoolType) {
            allocaType = new memBoolType();
        }
        return new IRAllocaInst(_allocName, allocaType, _parentBlock);
    }

    private IRInstruction generateBinaryInst(String _op, BasicType _retType, IRValue _lhs, IRValue _rhs, IRBasicBlock _parentBlock) {
        return new IRBinaryInst(renamer.rename(_op), _op, _retType, _lhs, _rhs, _parentBlock);
    }

    private IRInstruction generateBitCastInst(IRValue _fromValue, BasicType _targetType, IRBasicBlock _parentBlock) {
        return new IRBitCastInst(renamer.rename(LLVM_BITCAST_INST), _fromValue, _targetType, _parentBlock);
    }

    private IRInstruction generateUnConBrInst(IRBasicBlock _destBlock, IRBasicBlock _parentBlock) {
        return new IRBrInst(_destBlock, _parentBlock);
    }

    private IRInstruction generateConBrInst(IRValue _cond, IRBasicBlock _trueBlock, IRBasicBlock _falseBlock, IRBasicBlock _parentBlock) {
        if (_cond.valueType instanceof memBoolType) {
            return new IRBrInst(i8Toi1(_cond), _trueBlock, _falseBlock, _parentBlock);
        } else return new IRBrInst(_cond, _trueBlock, _falseBlock, _parentBlock);
    }

    private IRInstruction generateCallInst(IRFunction _calledFunc, IRBasicBlock _parentBlock, ArrayList<IRValue> _args) {
        var retType = _calledFunc.returnType;
        if (retType instanceof VoidType) return new IRCallInst(null, _calledFunc, _parentBlock, _args);
        else
            return new IRCallInst(renamer.rename(_calledFunc.funcName + "." + LLVM_CALL_INST), _calledFunc, _parentBlock, _args);
    }

    private IRInstruction generateCallInst(IRFunction _calledFunc, IRBasicBlock _parentBlock, IRValue... _args) {
        var retType = _calledFunc.returnType;
        if (retType instanceof VoidType) return new IRCallInst(null, _calledFunc, _parentBlock, _args);
        else
            return new IRCallInst(renamer.rename(_calledFunc.funcName + "." + LLVM_CALL_INST), _calledFunc, _parentBlock, _args);
    }

    private IRInstruction generateNamedElementPtrInst(String _elementName, IRValue _headPtr, BasicType _pointedType, IRBasicBlock _parentBlock, IRValue... indexes) {
        if (_pointedType instanceof BoolType)
            return new IRGEPInst(_elementName, _headPtr, _parentBlock, new memBoolType(), indexes);
        else return new IRGEPInst(_elementName, _headPtr, _parentBlock, _pointedType, indexes);
    }

    private IRInstruction generateUnnamedElementPtrInst(IRValue _headPtr, BasicType _pointedType, IRBasicBlock _parentBlock, IRValue... indexes) {
        if (_pointedType instanceof BoolType)
            return new IRGEPInst(renamer.rename(LLVM_GEP_INST), _headPtr, _parentBlock, new memBoolType(), indexes);
        else return new IRGEPInst(renamer.rename(LLVM_GEP_INST), _headPtr, _parentBlock, _pointedType, indexes);
    }

    private IRInstruction generateIcmpInst(String _type, IRValue _lhs, IRValue _rhs, IRBasicBlock _parentBlock) {
        return new IRIcmpInst(renamer.rename(LLVM_ICMP_INST), _type, _lhs, _rhs, _parentBlock);
    }

    private IRInstruction generateLoadInst(String _loadToAddr, IRValue _pointer, IRBasicBlock _parentBlock) {
        assert _pointer.valueType instanceof PointerType;
        if (((PointerType) _pointer.valueType).baseType instanceof BoolType) {
            throw new RuntimeException("load bool type is not allowed");
        }
        return new IRLoadInst(renamer.rename(_loadToAddr + ".load"), ((PointerType) _pointer.valueType).baseType, _pointer, _parentBlock);
    }

    private IRInstruction generateRetInst(BasicType _retType, IRValue _retValue, IRBasicBlock _parentBlock) {
        if (_retValue == null) return new IRRetInst(_retType, null, _parentBlock);
        if (_retValue.valueType instanceof memBoolType) {
            var alteredRetValue = i8Toi1(_retValue);
            return new IRRetInst(_retType, alteredRetValue, _parentBlock);
        } else return new IRRetInst(_retType, _retValue, _parentBlock);
    }

    private IRInstruction generateStoreInst(IRValue _storeValue, IRValue _destPtr, IRBasicBlock _parentBlock) {
        assert _destPtr.valueType instanceof PointerType;
        assert !(((PointerType) _destPtr.valueType).baseType instanceof BoolType);
        if (_storeValue.valueType instanceof BoolType) {
            return new IRStoreInst(i1Toi8(_storeValue), _destPtr, _parentBlock);
        } else return new IRStoreInst(_storeValue, _destPtr, _parentBlock);
    }

    private IRInstruction generateTruncInst(IRValue _fromValue, BasicType _targetType, IRBasicBlock _parentBlock) {
        return new IRTruncInst(renamer.rename(LLVM_TRUNC_INST), _fromValue, _targetType, _parentBlock);
    }

    private IRInstruction generateZextInst(IRValue _fromValue, BasicType _targetType, IRBasicBlock _parentBlock) {
        return new IRZextInst(renamer.rename(LLVM_ZEXT_INST), _fromValue, _targetType, _parentBlock);
    }
}
