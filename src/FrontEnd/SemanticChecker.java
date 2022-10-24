package FrontEnd;

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
import Utils.error.semanticError;
import Utils.error.syntaxError;
import Utils.log.Log;
import Utils.scope.GlobalScope;
import Utils.scope.Scope;

import java.util.ArrayList;

public class SemanticChecker implements ASTVisitor, BuiltInElements {
    private GlobalScope globalScope;
    Log log;
    private Scope currentScope;

    Boolean idForFunc = false;


    public SemanticChecker(GlobalScope _globalScope, Log _log) {
        this.globalScope = _globalScope;
        this.log = _log;
    }


    @Override
    public void visit(RootNode node) {
        currentScope = new Scope(false, null, false, null, false, globalScope);
        //double def has been checked when doing AST building
        if (!globalScope.hasFunc("main")) {
            throw new syntaxError("No main function", node.nodePos);
        } else if (!intType.Match(globalScope.getFuncDef("main").returnType)) {
            throw new syntaxError("Main function should return int", node.nodePos);
        } else if (globalScope.getFuncDef("main").argList.size() != 0) {
            throw new syntaxError("Main function should have no parameter", node.nodePos);
        }
        for (int i = 0; i < node.childNodes.size(); i++) {
            node.childNodes.get(i).accept(this);
        }
    }

    @Override
    public void visit(ClassDefNode node) {
        currentScope = new Scope(true, node, false, null, false, currentScope);

        //check var def and add to scope
        node.memberVarMap.forEach((key, value) -> value.accept(this));

        //check func def and add to scope
        node.memberFuncMap.forEach((key, value) -> value.accept(this));

        if (node.constructorDefNode != null) {
            //secure the constructor name match the class name in ASTBuilder
            node.constructorDefNode.accept(this);
            globalScope.addFunc(node.constructorDefNode.funcName, node.constructorDefNode);
        } else {
            ConstructorDefNode consFunc = new ConstructorDefNode(node.nodePos);
            consFunc.returnType = new Type(node.className, 0, false);
            consFunc.funcBodyNode = null;
            consFunc.argList = new ArrayList<>();
            globalScope.addFunc(node.className, consFunc);
        }

        globalScope.addFunc(node.className, node.constructorDefNode);
        currentScope = currentScope.parentScope;
    }

    @Override
    public void visit(ConstructorDefNode node) {
        currentScope = new Scope(currentScope.inClass, currentScope.classDefNode, true, voidType, false, currentScope);

        if (!currentScope.inClass) {
            throw new syntaxError("Constructor should be in a class", node.nodePos);
        } else if (!currentScope.classDefNode.className.equals(node.funcName)) {
            throw new syntaxError("Constructor name should match the class name", node.nodePos);
        }
        node.funcBodyNode.accept(this);
        currentScope = currentScope.parentScope;
    }

    @Override
    public void visit(FuncDefNode node) {
        //funcName has been added to globalScope
        log.addLog("[funcDefNode]");
        currentScope = new Scope(currentScope.inClass, currentScope.classDefNode, true, node.returnType, false, currentScope);

        //check return type
        if (!globalScope.hasClass(node.returnType.typeName) && !voidType.Match(node.returnType)) {
            throw new syntaxError("Return type " + node.returnType.typeName + " is not defined", node.nodePos);
        }

        node.argList.forEach(value -> value.accept(this));

        node.funcBodyNode.accept(this);

        boolean hasReturn = false;


        if (node.funcBodyNode.hasReturn) {
            log.addLog("Function " + node.funcName + " has return");
            hasReturn = true;
//            if (node.funcBodyNode.retType == null || !node.returnType.Match(node.funcBodyNode.retType)) {
//                if (!node.funcName.equals("main"))
//                    throw new syntaxError("Function " + node.funcName + " should return " + node.returnType.typeName, node.nodePos);
//            }
        }
        if (!node.returnType.Match(voidType) && !hasReturn && !node.funcName.equals("main")) {
            throw new syntaxError("Function " + node.funcName + " should have return", node.nodePos);
        }
        currentScope = currentScope.parentScope;
    }

    //expression
    @Override//todo
    public void visit(IndexExpNode node) {
        log.addLog("[IndexExpNode]");

        node.array.accept(this);

        if (idType.Match(node.array.exprType)) {
            if (currentScope.VarUsable(((IdExpNode) node.array).id)) {
                node.array.exprType = currentScope.getVarDef(((IdExpNode) node.array).id).varType;
            } else {
                throw new semanticError("Variable " + ((IdExpNode) node.array).id + " is not defined", node.nodePos);
            }
        }

        log.addLog("array type: " + node.array.exprType.PrintType());

        node.index.accept(this);

        if (idType.Match(node.index.exprType)) {
            if (currentScope.VarUsable(((IdExpNode) node.index).id)) {
                node.index.exprType = currentScope.getVarDef(((IdExpNode) node.index).id).varType;
            } else {
                throw new semanticError("Variable " + ((IdExpNode) node.index).id + " is not defined", node.nodePos);
            }
        }

        log.addLog("index type: " + node.index.exprType.PrintType());

        if (node.array.exprType == null || node.index.exprType == null || !node.index.exprType.Match(intType)) {
            throw new semanticError("IndexExpNode is invalid", node.nodePos);
        }
//        node.exprType = node.array.exprType;

        node.exprType = new Type(node.array.exprType.typeName, node.array.exprType.dimSize - 1, false);

        if (node.exprType.dimSize < 0) {
            throw new semanticError("Can't visit a 0 dim array ", node.nodePos);
        }

    }

    @Override
    public void visit(AssignExpNode node) {

        log.addLog("Visit AssignExpNode");


        node.lhs.accept(this);
        if (idType.Match(node.lhs.exprType)) {
            if (currentScope.VarUsable(((IdExpNode) node.lhs).id)) {

                log.addLog("lhs type in scope: " + currentScope.getVarDef(((IdExpNode) node.lhs).id).varType.PrintType());

                node.lhs.exprType = currentScope.getVarDef(((IdExpNode) node.lhs).id).varType;
            } else {
                throw new semanticError("[Assign] Variable " + ((IdExpNode) node.lhs).id + " is not defined", node.nodePos);
            }
        }

        node.rhs.accept(this);
        if (idType.Match(node.rhs.exprType)) {
            if (currentScope.VarUsable(((IdExpNode) node.rhs).id)) {
                node.rhs.exprType = currentScope.getVarDef(((IdExpNode) node.rhs).id).varType;
            } else {
                throw new semanticError("[Assign] Variable " + ((IdExpNode) node.rhs).id + " is not defined", node.nodePos);
            }
        }

        if (node.lhs.exprType == null || node.rhs.exprType == null) {
            throw new semanticError("AssignExpNode is invalid", node.nodePos);
        }
        if (voidType.Match(node.lhs.exprType)) {
            throw new semanticError("Can't assign to void", node.nodePos);
        }

        if (voidType.Match(node.lhs.exprType)) {
            throw new semanticError("rhs can't be voidType", node.nodePos);
        }

        if (!node.lhs.exprType.Match(node.rhs.exprType)) {
            log.addLog("lhs type: " + node.lhs.exprType.PrintType());
            log.addLog("rhs type: " + node.rhs.exprType.PrintType());

            if (!(node.lhs.exprType.NullAssignable() && nullType.Match(node.rhs.exprType))) {
                throw new semanticError("AssignExpNode is mismatched", node.nodePos);
            }
        }

        if (!node.lhs.isLeftValue()) {
            throw new semanticError("Can't assign to a non-left-value", node.nodePos);
        }

        node.exprType = new Type(node.lhs.exprType);

    }

    @Override
    public void visit(AtomExpNode node) {
        if (node instanceof BoolExpNode) {
            node.exprType = boolType;
        } else if (node instanceof IdExpNode) {
            node.exprType = idType;
        } else if (node instanceof IntExpNode) {
            node.exprType = intType;
        } else if (node instanceof NullExpNode) {
            node.exprType = nullType;
        } else if (node instanceof StringExpNode) {
            node.exprType = stringType;
        } else if (node instanceof ThisExpNode) {
            if (!currentScope.inClass) {
                throw new semanticError("This can only be used in a class", node.nodePos);
            }
            node.exprType = new Type(currentScope.classDefNode.className, 0, false);
        }
    }

    @Override//todo
    public void visit(BinaryExpNode node) {
        node.lhs.accept(this);
        if (idType.Match(node.lhs.exprType)) {
            if (currentScope.VarUsable(((IdExpNode) node.lhs).id)) {
                node.lhs.exprType = currentScope.getVarDef(((IdExpNode) node.lhs).id).varType;
            } else {
                throw new semanticError("[BinaryExp] Variable " + ((IdExpNode) node.lhs).id + " is not defined", node.nodePos);
            }
        }

        node.rhs.accept(this);
        if (idType.Match(node.rhs.exprType)) {
            if (currentScope.VarUsable(((IdExpNode) node.rhs).id)) {
                node.rhs.exprType = currentScope.getVarDef(((IdExpNode) node.rhs).id).varType;
            } else {
                throw new semanticError("[BinaryExp] Variable " + ((IdExpNode) node.rhs).id + " is not defined", node.nodePos);
            }
        }


        Type resultType = null;
        if (node instanceof BinaryBoolExpNode) {
            resultType = boolType;
        }

        if (nullType.Match(node.lhs.exprType)) {
            if (!node.operator.equals(BinaryExpNode.BinaryOp.EqualOp) && !node.operator.equals(BinaryExpNode.BinaryOp.NotEqualOp)) {
                throw new semanticError("Can't use null in lhs", node.nodePos);
            }
            resultType = boolType;
        }
        if (intType.Match(node.lhs.exprType)) {
            if (!intType.Match(node.rhs.exprType)) {
                throw new semanticError("BinaryExpNode is mismatched in IntType", node.nodePos);
            }
            if (node.operator.equals(BinaryExpNode.BinaryOp.EqualOp) ||
                    node.operator.equals(BinaryExpNode.BinaryOp.NotEqualOp) ||
                    node.operator.equals(BinaryExpNode.BinaryOp.LessOp) ||
                    node.operator.equals(BinaryExpNode.BinaryOp.LessEqualOp) ||
                    node.operator.equals(BinaryExpNode.BinaryOp.GreaterOp) ||
                    node.operator.equals(BinaryExpNode.BinaryOp.GreaterEqualOp)
            ) {
                resultType = boolType;
            } else if (node.operator.equals(BinaryExpNode.BinaryOp.LogicAndOp) ||
                    node.operator.equals(BinaryExpNode.BinaryOp.LogicOrOp)) {
                throw new semanticError("Can't use logic operator on int", node.nodePos);
            } else {
                resultType = intType;
            }
        }

        if (boolType.Match(node.lhs.exprType)) {
            if (!boolType.Match(node.rhs.exprType)) {
                throw new semanticError("BinaryExpNode is mismatched in BoolType", node.nodePos);
            }
            if (!node.operator.equals(BinaryExpNode.BinaryOp.EqualOp) &&
                    !node.operator.equals(BinaryExpNode.BinaryOp.NotEqualOp) &&
                    !node.operator.equals(BinaryExpNode.BinaryOp.LogicAndOp) &&
                    !node.operator.equals(BinaryExpNode.BinaryOp.LogicOrOp)) {
                throw new semanticError("BinaryExpNode has undefined op in BoolType", node.nodePos);
            }
            resultType = boolType;
        }

        if (node.lhs.exprType.dimSize > 0) {
            if (!nullType.Match(node.rhs.exprType)) {
                throw new semanticError("Rhs should be null", node.nodePos);
            }
            if (!node.operator.equals(BinaryExpNode.BinaryOp.EqualOp) &&
                    !node.operator.equals(BinaryExpNode.BinaryOp.NotEqualOp)) {
                throw new semanticError("BinaryExpNode has undefined op in ArrayType", node.nodePos);
            }
            resultType = boolType;
        }

        if (node.lhs.exprType.isClass && node.lhs.exprType.dimSize == 0) {
            if (!node.lhs.exprType.Match(node.rhs.exprType)) {
                throw new semanticError("BinaryExpNode is mismatched in ClassType", node.nodePos);
            }
            if (!node.operator.equals(BinaryExpNode.BinaryOp.EqualOp) &&
                    !node.operator.equals(BinaryExpNode.BinaryOp.NotEqualOp)) {
                throw new semanticError("BinaryExpNode has undefined op in ClassType", node.nodePos);
            }
            resultType = boolType;
        }

        if (stringType.Match(node.lhs.exprType)) {
            if (!stringType.Match(node.rhs.exprType)) {
                throw new semanticError("BinaryExpNode is mismatched in StringType", node.nodePos);
            }
            if (!node.operator.equals(BinaryExpNode.BinaryOp.PlusOp) &&
                    !node.operator.equals(BinaryExpNode.BinaryOp.EqualOp) &&
                    !node.operator.equals(BinaryExpNode.BinaryOp.NotEqualOp) &&
                    !node.operator.equals(BinaryExpNode.BinaryOp.LessOp) &&
                    !node.operator.equals(BinaryExpNode.BinaryOp.LessEqualOp) &&
                    !node.operator.equals(BinaryExpNode.BinaryOp.GreaterOp) &&
                    !node.operator.equals(BinaryExpNode.BinaryOp.GreaterEqualOp)) {
                throw new semanticError("BinaryExpNode has undefined op in StringType", node.nodePos);
            }
            if (node.operator.equals(BinaryExpNode.BinaryOp.PlusOp)) resultType = stringType;
            else resultType = boolType;
        }

        node.exprType = resultType;
    }

    @Override
    public void visit(FuncCallExpNode node) {
        log.addLog("[FuncCallExpNode]");

        ClassDefNode classDefNode = null;
        FuncDefNode funcDefNode = null;
        node.function.accept(this);

        if (idType.Match(node.function.exprType)) {
            if (globalScope.hasFunc(((IdExpNode) node.function).id)) {
                funcDefNode = globalScope.getFuncDef(((IdExpNode) node.function).id);
            } else if (currentScope.inClass) {
                classDefNode = currentScope.classDefNode;
                if (classDefNode.memberFuncMap.containsKey(node.function.exprType.typeName)) {
                    funcDefNode = classDefNode.memberFuncMap.get(node.function.exprType.typeName);
                }
            } else {
                throw new semanticError("[1]Function " + ((IdExpNode) node.function).id + " is not defined", node.nodePos);
            }
        }

//        log.addLog("Function"+ );

        if (node.function instanceof MemberExpNode) {
            log.addLog("[FuncCallExpNode] MemberExpNode");
//            var classId = ((MemberExpNode) node.function).base.exprType.typeName;
//            var funcId = ((MemberExpNode) node.function).memberFunc.funcName;
//            if (!globalScope.hasClass(classId)) {
//                throw new semanticError("Class " + classId + " is not defined", node.nodePos);
//            } else {
//                classDefNode = globalScope.getClassDef(classId);
//                if (!classDefNode.memberFuncMap.containsKey(funcId)) {
//                    throw new semanticError("Function " + classId + "." + funcId + " is not defined", node.nodePos);
//                } else {
//                    funcDefNode = classDefNode.memberFuncMap.get(funcId);
//                }
//            }
            if (((MemberExpNode) node.function).isFunc) {
                funcDefNode = ((MemberExpNode) node.function).memberFunc;
            } else {
                throw new semanticError("[2]Function " + ((MemberExpNode) node.function).memberFunc.funcName + " is not defined", node.nodePos);
            }
        } else {
            if (node.function instanceof IdExpNode) {
                var funcId = ((IdExpNode) node.function).id;
                if (currentScope.inClass) {
                    classDefNode = currentScope.classDefNode;
                    if (classDefNode.memberFuncMap.containsKey(funcId)) {
                        funcDefNode = classDefNode.memberFuncMap.get(funcId);
                    } else {
                        if (globalScope.hasFunc(funcId)) {
                            funcDefNode = globalScope.getFuncDef(funcId);
                        } else throw new semanticError("Member Function " + funcId + " is not defined", node.nodePos);
                    }
                } else {
                    if (!globalScope.hasFunc(funcId)) {
                        throw new semanticError("Function " + funcId + " is not defined", node.nodePos);
                    } else {
                        funcDefNode = globalScope.getFuncDef(funcId);
                    }
                }
            } else {
                log.addLog(node.function.getClass().toString());

                throw new semanticError("Not a function", node.nodePos);
            }
        }
        if (classDefNode != null) {
            log.addLog("class name: " + classDefNode.className);
        }


        log.addLog("funcDefNode: " + funcDefNode.funcName);

        if (node.paraList.size() != funcDefNode.argList.size()) {
            log.addLog("paraList.size() = " + node.paraList.size());
            log.addLog("funcDefNode.argList.size() = " + funcDefNode.argList.size());
            throw new semanticError("[3]Function " + funcDefNode.funcName + " has " + funcDefNode.argList.size() + " arguments", node.nodePos);
        }

        //check idType
        node.paraList.forEach(para -> {
            para.accept(this);
            if (idType.Match(para.exprType)) {
                if (currentScope.VarUsable(((IdExpNode) para).id)) {
                    para.exprType = currentScope.getVarDef(((IdExpNode) para).id).varType;
                } else {
                    throw new semanticError("Variable " + ((IdExpNode) para).id + " is not defined", para.nodePos);
                }
            }
        });


        for (int i = 0; i < node.paraList.size(); i++) {
            Type paraType = node.paraList.get(i).exprType;
            Type defType = funcDefNode.argList.get(i).varType;
            if (!paraType.Match(defType)) {
                if (!(paraType.NullAssignable() && nullType.Match(paraType))) {
                    log.addLog("paraType: " + paraType.typeName);
                    log.addLog("defType: " + defType.typeName);
                    throw new semanticError("Function " + funcDefNode.funcName + " has wrong argument type", node.nodePos);
                }
            }
        }
        node.exprType = new Type(funcDefNode.returnType);
    }

    @Override//todo
    public void visit(LambdaExpNode node) {
        Scope tmpScope = currentScope;
        currentScope = new Scope(currentScope.inClass, currentScope.classDefNode, true, autoType, currentScope.inLoop, node.isCapture ? globalScope : null);

        node.paraList.forEach(para -> para.accept(this));

        if (node.paraList.size() != node.callArgList.size()) {
            throw new semanticError("LambdaExpNode is invalid", node.nodePos);
        }

        for (int i = 0; i < node.paraList.size(); i++) {
            node.paraList.get(i).accept(this);
            node.callArgList.get(i).accept(this);
            Type defType = node.paraList.get(i).varType;
            Type paraType = node.callArgList.get(i).exprType;
            if (!paraType.Match(defType) && !(paraType.NullAssignable() && nullType.Match(paraType))) {
                throw new semanticError("Function " + "LambdaExp" + " has wrong argument type", node.nodePos);
            }
        }

        node.body.accept(this);
        if (node.body.hasReturn) node.exprType = new Type(node.body.retType);
        else node.exprType = new Type(voidType);
        currentScope = tmpScope;

    }

    @Override//when call var, expType is var type
    public void visit(MemberExpNode node) {
        node.base.accept(this);

        if (idType.Match(node.base.exprType)) {
            if (currentScope.VarUsable(((IdExpNode) node.base).id)) {
                node.base.exprType = currentScope.getVarDef(((IdExpNode) node.base).id).varType;
            } else {
                throw new semanticError("Variable " + ((IdExpNode) node.base).id + " is not defined", node.nodePos);
            }
        }
        if ((node.base.exprType.isClass || stringType.Match(node.base.exprType)) && node.base.exprType.dimSize == 0) {
            var classId = node.base.exprType.typeName;
            var memberName = node.memberName;
            if (!globalScope.hasClass(classId)) {
                throw new semanticError("Class " + classId + " is not defined", node.nodePos);
            } else {
                ClassDefNode classDefNode = globalScope.getClassDef(classId);
                if (classDefNode.memberFuncMap.containsKey(memberName)) {
                    node.isFunc = true;
                    node.memberFunc = classDefNode.memberFuncMap.get(memberName);
                }
                if (classDefNode.memberVarMap.containsKey(memberName)) {
                    node.isVar = true;
                    node.memberVarType = classDefNode.memberVarMap.get(memberName).varType;
                }
                if (!node.isFunc && !node.isVar) {
                    throw new semanticError("[1]Member " + memberName + " is not defined", node.nodePos);
                }
            }
        } else {
            if (node.base.exprType.dimSize > 0 && node.memberName.equals("size")) {
                node.isFunc = true;
                node.memberFunc = globalScope.getFuncDef("size");
            } else {
                log.addLog("node.base.exprType.dimSize = " + node.base.exprType.dimSize);
                log.addLog("node.base.exprType = " + node.base.exprType.PrintType());
                log.addLog("node.base.exprType is stringType" + stringType.Match(node.base.exprType));
                log.addLog("node.memberName = " + node.memberName);
                throw new semanticError("[2]Member " + node.memberName + " is not defined", node.nodePos);
            }
        }
        node.exprType = node.isVar ? node.memberVarType : null;
    }

    @Override
    public void visit(NewExpNode node) {
        log.addLog("[NewExpNode]");

        for (var dim : node.SizeList) {
            dim.accept(this);
            if (idType.Match(dim.exprType)) {
                if (currentScope.VarUsable(((IdExpNode) dim).id)) {
                    dim.exprType = currentScope.getVarDef(((IdExpNode) dim).id).varType;
                } else {
                    throw new semanticError("Variable " + ((IdExpNode) dim).id + " is not defined", dim.nodePos);
                }
            }
            if (!intType.Match(dim.exprType)) {
                throw new semanticError("NewExpNode has wrong dim type", node.nodePos);//已在ASTBuild检查中间加“[]”的问题
            }
        }
        log.addLog("node.resultType = " + node.resultType.PrintType());
        if (!globalScope.hasClass(node.resultType.typeName)) {
            throw new syntaxError("Variable type " + node.resultType.typeName + " is not defined", node.nodePos);
        }
        node.exprType = new Type(node.resultType.typeName, node.resultType.dimSize, false);
        log.addLog("node.exprType = " + node.exprType.PrintType());
        log.addLog("new exp: " + node.exprType.toString());
    }

    @Override
    public void visit(PrefixExpNode node) {
        node.value.accept(this);
        if (idType.Match(node.value.exprType)) {
            if (currentScope.VarUsable(((IdExpNode) node.value).id)) {
                node.value.exprType = currentScope.getVarDef(((IdExpNode) node.value).id).varType;
            } else {
                throw new semanticError("Variable " + ((IdExpNode) node.value).id + " is not defined", node.nodePos);
            }
        }
        if (nullType.Match(node.value.exprType)) {
            throw new semanticError("PrefixExpNode has null type", node.nodePos);
        }
        if (!node.value.isLeftValue() || !intType.Match(node.value.exprType)) {
            throw new semanticError("PrefixExpNode has wrong type", node.nodePos);
        }
        node.exprType = new Type(intType);
    }

    @Override
    public void visit(SuffixExpNode node) {
        node.value.accept(this);

        if (idType.Match(node.value.exprType)) {
            if (currentScope.VarUsable(((IdExpNode) node.value).id)) {
                node.value.exprType = currentScope.getVarDef(((IdExpNode) node.value).id).varType;
            } else {
                throw new semanticError("Variable " + ((IdExpNode) node.value).id + " is not defined", node.nodePos);
            }
        }

        if (nullType.Match(node.value.exprType)) {
            throw new semanticError("SuffixExpNode has null type", node.nodePos);
        }
        if (!node.value.isLeftValue() || !intType.Match(node.value.exprType)) {
            throw new semanticError("SuffixExpNode has wrong type", node.nodePos);
        }
        node.exprType = new Type(intType);
    }

    @Override
    public void visit(UnaryExpNode node) {
        node.value.accept(this);

        if (idType.Match(node.value.exprType)) {
            if (currentScope.VarUsable(((IdExpNode) node.value).id)) {
                node.value.exprType = currentScope.getVarDef(((IdExpNode) node.value).id).varType;
            } else {
                throw new semanticError("Variable " + ((IdExpNode) node.value).id + " is not defined", node.nodePos);
            }
        }

        if (nullType.Match(node.value.exprType)) {
            throw new semanticError("UnaryExpNode has null type", node.nodePos);
        }
        if (node.op.equals("!")) {
            if (!boolType.Match(node.value.exprType)) {
                throw new semanticError("UnaryExpNode has wrong type", node.nodePos);
            }
            node.exprType = new Type(boolType);
        } else {
            if (!intType.Match(node.value.exprType)) {
                throw new semanticError("UnaryExpNode has wrong type", node.nodePos);
            }
            node.exprType = new Type(intType);
        }
    }

    //statement

    @Override
    public void visit(BreakStmtNode node) {
        if (!currentScope.inLoop) {
            throw new semanticError("BreakStmtNode is not in loop", node.nodePos);
        }
    }

    @Override
    public void visit(ContinueStmtNode node) {
        if (!currentScope.inLoop) {
            throw new semanticError("ContinueStmtNode is not in loop", node.nodePos);
        }
    }

    @Override
    public void visit(EmptyStmtNode node) {
    }

    @Override
    public void visit(ExpStmtNode node) {
        log.addLog("[ExpStmtNode]");
        node.expNode.accept(this);
    }

    @Override
    public void visit(ForStmtNode node) {
        currentScope = new Scope(currentScope.inClass, currentScope.classDefNode, currentScope.inFunc, currentScope.returnType, true, currentScope);
        if (node.initExpNode != null) {
            node.initExpNode.accept(this);
        }
        if (node.initVarDefUnitList != null) {
            for (var varDefUnit : node.initVarDefUnitList) {
                varDefUnit.accept(this);
                currentScope.addVar(varDefUnit.varName, varDefUnit);
            }
        }
        if (node.condExpNode != null) {
            node.condExpNode.accept(this);
            if (!boolType.Match(node.condExpNode.exprType)) {
                log.addLog("condExpNode: " + node.condExpNode.exprType.PrintType());
                throw new semanticError("ForStmtNode has wrong cond type", node.nodePos);
            }
        }
        if (node.stepExpNode != null) {
            node.stepExpNode.accept(this);
        }
        node.bodyStmtNode.accept(this);

        if (node.bodyStmtNode.hasReturn) {
            node.hasReturn = true;
        }

        currentScope = currentScope.parentScope;
    }

    @Override
    public void visit(IfStmtNode node) {
        if (node.condExpNode != null) {
            node.condExpNode.accept(this);

            if(idType.Match(node.condExpNode.exprType)){
                if(currentScope.VarUsable(((IdExpNode)node.condExpNode).id)){
                    node.condExpNode.exprType = currentScope.getVarDef(((IdExpNode)node.condExpNode).id).varType;
                }else{
                    throw new semanticError("Variable " + ((IdExpNode)node.condExpNode).id + " is not defined", node.nodePos);
                }
            }

            if (!boolType.Match(node.condExpNode.exprType)) {
                log.addLog("condExpNode: " + node.condExpNode.exprType.PrintType());
                throw new semanticError("IfStmtNode has wrong cond type", node.nodePos);
            }
        }
        currentScope = new Scope(currentScope.inClass, currentScope.classDefNode, currentScope.inFunc, currentScope.returnType, currentScope.inLoop, currentScope);
        node.trueStmtList.forEach(stmt -> {
            stmt.accept(this);
            if (stmt.hasReturn) {
                node.hasReturn = true;
            }
        });
        currentScope = currentScope.parentScope;
        if (node.elseStmtList != null) {
            currentScope = new Scope(currentScope.inClass, currentScope.classDefNode, currentScope.inFunc, currentScope.returnType, false, currentScope);
            node.elseStmtList.forEach(stmt -> {
                stmt.accept(this);
                if (stmt.hasReturn) {
                    node.hasReturn = true;
                }
            });
            currentScope = currentScope.parentScope;
        }
    }

    @Override
    public void visit(ReturnStmtNode node) {
        node.hasReturn = true;
        if (!currentScope.inFunc) {
            throw new semanticError("ReturnStmtNode is not in function", node.nodePos);
        }
        if (node.returnExp == null) {
            if (!voidType.Match(currentScope.returnType)) {
                throw new semanticError("[1]ReturnStmtNode has wrong type", node.nodePos);
            } else {
                node.returnType = new Type(voidType);
            }
        } else {
            node.returnExp.accept(this);
            if (voidType.Match(currentScope.returnType)) {
                if (node.returnExp != null) throw new semanticError("[2]ReturnStmtNode has wrong type", node.nodePos);
            }
            if (idType.Match(node.returnExp.exprType)) {
                if (currentScope.VarUsable(((IdExpNode) node.returnExp).id)) {
                    node.returnExp.exprType = currentScope.getVarDef(((IdExpNode) node.returnExp).id).varType;
                } else {
                    throw new semanticError("Variable " + ((IdExpNode) node.returnExp).id + " is not defined", node.nodePos);
                }
            }
            if (autoType.Match(currentScope.returnType)) {
                node.returnType = new Type(node.returnExp.exprType);
            } else if (currentScope.returnType.Match(node.returnExp.exprType)) {
                node.returnType = new Type(currentScope.returnType);
            } else {
                log.addLog("currentScope.returnType: " + currentScope.returnType.PrintType());
                log.addLog("node.returnExp.exprType: " + node.returnExp.exprType.PrintType());
                throw new semanticError("[3]ReturnStmtNode has wrong type", node.nodePos);
            }
        }
    }

    @Override
    public void visit(WhileStmtNode node) {
        node.condExpNode.accept(this);
        if (!boolType.Match(node.condExpNode.exprType)) {
            throw new semanticError("WhileStmtNode has wrong cond type", node.nodePos);
        }
        currentScope = new Scope(currentScope.inClass, currentScope.classDefNode, currentScope.inFunc, currentScope.returnType, true, currentScope);
        node.bodyStmtList.forEach(stmt -> {
            stmt.accept(this);
            if (stmt.hasReturn) {
                node.hasReturn = true;
            }
        });
        currentScope = currentScope.parentScope;
    }

    @Override
    public void visit(SuiteStmtNode node) {
        log.addLog("[SuiteStmtNode]");
        currentScope = new Scope(currentScope.inClass, currentScope.classDefNode, currentScope.inFunc, currentScope.returnType, currentScope.inLoop, currentScope);

        log.addLog("SuiteStmtNode: " + node.stmtList.size());
        node.stmtList.forEach(stmt -> {
            log.addLog("SuiteStmtNode: " + stmt.getClass().getName());
            stmt.accept(this);
            if (stmt.hasReturn) {
                node.hasReturn = true;
            }
        });

        currentScope = currentScope.parentScope;
    }

    @Override
    public void visit(VarDefStmtNode node) {
        node.varDefUnitNodes.forEach(varDefUnit -> varDefUnit.accept(this));
    }

    @Override
    public void visit(VarDefUnitNode node) {
        if (!globalScope.hasClass(node.varType.typeName)) {
            throw new syntaxError("Variable type " + node.varType.typeName + " is not defined", node.nodePos);
        }
        if (!currentScope.VarDefinable(node.varName)) {
            throw new syntaxError("Variable " + node.varName + " has been defined", node.nodePos);
        }

        if (node.initValue != null) {
            node.initValue.accept(this);
            if (!node.initValue.exprType.Match(node.varType)) {
                if (!node.varType.NullAssignable() && !nullType.Match(node.initValue.exprType)) {
                    throw new semanticError("Variable " + node.varName + " has wrong type", node.nodePos);
                }
            }
        }
        currentScope.addVar(node.varName, node);
    }

    //type

    @Override
    public void visit(VarTypeNode node) {
        if (!globalScope.hasClass(node.varType.typeName)) {
            throw new syntaxError("Variable type " + node.varType.typeName + " is not defined", node.nodePos);
        }
    }

    @Override
    public void visit(ReturnTypeNode node) {
        if (!globalScope.hasClass(node.returnType.typeName)) {
            throw new syntaxError("Variable type " + node.returnType.typeName + " is not defined", node.nodePos);
        }
    }

}
