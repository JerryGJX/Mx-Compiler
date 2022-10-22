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
import Utils.scope.GlobalScope;
import Utils.scope.Scope;

public class SemanticChecker implements ASTVisitor, BuiltInElements {
    private GlobalScope globalScope;
    private Scope currentScope;


    public SemanticChecker(GlobalScope _globalScope) {
        this.globalScope = _globalScope;
    }


    @Override
    public void visit(RootNode node) {
        currentScope = globalScope;
        //double def has been checked when doing AST building
        if (!globalScope.hasFunc("main")) {
            throw new syntaxError("No main function", node.nodePos);
        } else if (globalScope.getFuncDef("main").returnType != intType) {
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
        currentScope = new Scope(true, node, false, false, currentScope);

        //check var def and add to scope
        node.memberVarMap.forEach((key, value) -> value.accept(this));

        //check func def and add to scope
        node.memberFuncMap.forEach((key, value) -> value.accept(this));

        if (node.constructorDefNode != null) {
            //secure the constructor name match the class name in ASTBuilder
            node.constructorDefNode.accept(this);
        }

        currentScope = currentScope.parentScope;
    }

    @Override
    public void visit(ConstructorDefNode node) {
        currentScope = new Scope(currentScope.inClass, currentScope.classDefNode, true, false, currentScope);

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
        currentScope = new Scope(currentScope.inClass, currentScope.classDefNode, true, false, currentScope);

        //check return type
        if (!globalScope.hasClass(node.returnType.typeName)) {
            throw new syntaxError("Return type " + node.returnType.typeName + " is not defined", node.nodePos);
        }

        node.argList.forEach(value -> value.accept(this));

        node.funcBodyNode.accept(this);

        boolean hasReturn = false;
        if (node.funcBodyNode.retType != null) {
            hasReturn = true;
            if (!node.returnType.Match(node.funcBodyNode.retType)) {
                throw new syntaxError("Function " + node.funcName + " should return " + node.returnType.typeName, node.nodePos);
            }
        }
        if (!node.returnType.Match(voidType) && !hasReturn && !node.funcName.equals("main")) {
            throw new syntaxError("Function " + node.funcName + " should have return", node.nodePos);
        }
        currentScope = currentScope.parentScope;
    }

    //expression
    @Override
    public void visit(IndexExpNode node) {
        node.array.accept(this);
        node.index.accept(this);
        if (node.array.exprType == null || node.index.exprType == null || !node.index.exprType.Match(intType)) {
            throw new semanticError("IndexExpNode is invalid", node.nodePos);
        }
        node.exprType = node.array.exprType;
        node.exprType.dimSize--;

        if (node.exprType.dimSize < 0) {
            throw new semanticError("Can't visit a 0 dim array", node.nodePos);
        }

    }

    @Override
    public void visit(AssignExpNode node) {
        node.lhs.accept(this);
        node.rhs.accept(this);
        if (node.lhs.exprType == null || node.rhs.exprType == null) {
            throw new semanticError("AssignExpNode is invalid", node.nodePos);
        }
        if (voidType.Match(node.lhs.exprType)) {
            throw new semanticError("Can't assign to void", node.nodePos);
        }

        if (!voidType.Match(node.lhs.exprType)) {
            throw new semanticError("rhs can't be voidType", node.nodePos);
        }

        if (!node.lhs.exprType.Match(node.rhs.exprType) && !(node.exprType.NullAssignable() && nullType.Match(node.rhs.exprType))) {
            throw new semanticError("AssignExpNode is mismatched", node.nodePos);
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
            String Id = ((IdExpNode) node).id;
            if (!currentScope.hasVar(Id)) {
                throw new semanticError("Id " + Id + " is not defined", node.nodePos);
            }
            node.exprType = new Type(currentScope.getVarDef(Id).varType);
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
        node.rhs.accept(this);

        Type resultType = null;
        if (node instanceof BinaryBoolExpNode) {
            resultType = boolType;
        }


        if (nullType.Match(node.lhs.exprType)) {
            throw new semanticError("Can't use null in lhs", node.nodePos);
        }
        if (intType.Match(node.lhs.exprType)) {
            if (!intType.Match(node.rhs.exprType)) {
                throw new semanticError("BinaryExpNode is mismatched in IntType", node.nodePos);
            }
            if (!node.operator.equals(BinaryExpNode.BinaryOp.PlusOp) &&
                    !node.operator.equals(BinaryExpNode.BinaryOp.EqualOp) &&
                    !node.operator.equals(BinaryExpNode.BinaryOp.NotEqualOp) &&
                    !node.operator.equals(BinaryExpNode.BinaryOp.LessOp) &&
                    !node.operator.equals(BinaryExpNode.BinaryOp.LessEqualOp) &&
                    !node.operator.equals(BinaryExpNode.BinaryOp.GreaterOp) &&
                    !node.operator.equals(BinaryExpNode.BinaryOp.GreaterEqualOp)) {
                throw new semanticError("BinaryExpNode has undefined op in IntType", node.nodePos);
            }
            resultType = intType;
        }

        if (boolType.Match(node.lhs.exprType)) {
            if (!boolType.Match(node.rhs.exprType)) {
                throw new semanticError("BinaryExpNode is mismatched in BoolType", node.nodePos);
            }
            if (!node.operator.equals(BinaryExpNode.BinaryOp.EqualOp) &&
                    !node.operator.equals(BinaryExpNode.BinaryOp.NotEqualOp)) {
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
                    !node.operator.equals(BinaryExpNode.BinaryOp.NotEqualOp)) {
                throw new semanticError("BinaryExpNode has undefined op in StringType", node.nodePos);
            }
            if (node.operator.equals(BinaryExpNode.BinaryOp.PlusOp)) resultType = stringType;
            else resultType = boolType;
        }

        node.exprType = resultType;
    }

    @Override
    public void visit(FuncCallExpNode node) {
        ClassDefNode classDefNode = null;
        FuncDefNode funcDefNode = null;
        node.function.accept(this);

        if (node.function instanceof MemberExpNode) {
            var classId = ((MemberExpNode) node.function).base.exprType.typeName;
            var funcId = ((MemberExpNode) node.function).memberFunc.funcName;
            if (!globalScope.hasClass(classId)) {
                throw new semanticError("Class " + classId + " is not defined", node.nodePos);
            } else {
                classDefNode = globalScope.getClassDef(classId);
                if (!classDefNode.memberFuncMap.containsKey(funcId)) {
                    throw new semanticError("Function " + classId + "." + funcId + " is not defined", node.nodePos);
                } else {
                    funcDefNode = classDefNode.memberFuncMap.get(funcId);
                }
            }
        } else {
            if (node.function instanceof IdExpNode) {
                var funcId = ((IdExpNode) node.function).id;
                if (currentScope.inClass) {
                    classDefNode = currentScope.classDefNode;
                    if (classDefNode.memberFuncMap.containsKey(funcId)) {
                        funcDefNode = classDefNode.memberFuncMap.get(funcId);
                    } else {
                        throw new semanticError("Member Function " + funcId + " is not defined", node.nodePos);
                    }
                } else {
                    if (!globalScope.hasFunc(funcId)) {
                        throw new semanticError("Function " + funcId + " is not defined", node.nodePos);
                    } else {
                        funcDefNode = globalScope.getFuncDef(funcId);
                    }
                }
            } else {
                throw new semanticError("Not a function", node.nodePos);
            }
        }

        if (node.paraList.size() != funcDefNode.argList.size()) {
            throw new semanticError("Function " + funcDefNode.funcName + " has " + funcDefNode.argList.size() + " arguments", node.nodePos);
        }

        for (int i = 0; i < node.paraList.size(); i++) {
            node.paraList.get(i).accept(this);
            Type paraType = node.paraList.get(i).exprType;
            Type defType = funcDefNode.argList.get(i).varType;
            if (!paraType.Match(defType) && !(paraType.NullAssignable() && nullType.Match(paraType))) {
                throw new semanticError("Function " + funcDefNode.funcName + " has wrong argument type", node.nodePos);
            }
        }
        node.exprType = new Type(funcDefNode.returnType);
    }

    @Override//todo
    public void visit(LambdaExpNode node) {
        Scope tmpScope = currentScope;
        currentScope = new Scope(currentScope.inClass, currentScope.classDefNode, true, currentScope.inLoop, node.isCapture ? globalScope : null);

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
        node.exprType = new Type(node.body.retType);

    }

    @Override//when call var, expType is var type
    public void visit(MemberExpNode node) {
        node.base.accept(this);

        if (node.base.exprType.isClass && node.base.exprType.dimSize == 0) {
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
                    throw new semanticError("Member " + memberName + " is not defined", node.nodePos);
                }
            }
        } else {
            throw new semanticError("MemberExpNode is not a class", node.nodePos);
        }
        node.exprType = node.isVar ? node.memberVarType : null;
    }

    @Override
    public void visit(NewExpNode node) {
        for (var dim : node.SizeList) {
            dim.accept(this);
            if (!intType.Match(dim.exprType)) {
                throw new semanticError("NewExpNode has wrong dim type", node.nodePos);//已在ASTBuild检查中间加“[]”的问题
            }
        }
        if (!globalScope.hasClass(node.resultType.typeName)) {
            throw new syntaxError("Variable type " + node.resultType.typeName + " is not defined", node.nodePos);
        }
        node.exprType = new Type(node.resultType);
    }

    @Override
    public void visit(PrefixExpNode node) {
        node.value.accept(this);
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
        node.expNode.accept(this);
    }

    @Override
    public void visit(ForStmtNode node) {
        currentScope = new Scope(currentScope.inClass, currentScope.classDefNode, currentScope.inFunc, true, currentScope);
        if (node.initExpNode != null) {
            node.initExpNode.accept(this);
        }
        if (node.initVarDefUnitList != null) {
            for (var varDefUnit : node.initVarDefUnitList) {
                varDefUnit.accept(this);
            }
        }
        if (node.condExpNode != null) {
            node.condExpNode.accept(this);
            if (!boolType.Match(node.condExpNode.exprType)) {
                throw new semanticError("ForStmtNode has wrong cond type", node.nodePos);
            }
        }
        if (node.stepExpNode != null) {
            node.stepExpNode.accept(this);
        }
        node.bodyStmtNode.accept(this);
        currentScope = currentScope.parentScope;
    }

    @Override
    public void visit(IfStmtNode node) {
        if (node.condExpNode != null) {
            node.condExpNode.accept(this);
            if (!boolType.Match(node.condExpNode.exprType)) {
                throw new semanticError("IfStmtNode has wrong cond type", node.nodePos);
            }
        }
        currentScope = new Scope(currentScope.inClass, currentScope.classDefNode, currentScope.inFunc, false, currentScope);
        node.trueStmtList.forEach(stmt -> stmt.accept(this));
        currentScope = currentScope.parentScope;
        if(node.elseStmtList != null) {
            currentScope = new Scope(currentScope.inClass, currentScope.classDefNode, currentScope.inFunc, false, currentScope);
            node.elseStmtList.forEach(stmt -> stmt.accept(this));
            currentScope = currentScope.parentScope;
        }

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
    public void visit(VarDefUnitNode node) {
        if (!globalScope.hasClass(node.varType.typeName)) {
            throw new syntaxError("Variable type " + node.varType.typeName + " is not defined", node.nodePos);
        }
        if (currentScope.hasVar(node.varName)) {
            throw new syntaxError("Variable " + node.varName + " has been defined", node.nodePos);
        } else {
            currentScope.addVar(node.varName, node);
        }
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
