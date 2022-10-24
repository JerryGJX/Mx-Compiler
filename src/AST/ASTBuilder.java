package AST;

import AST.node.*;
import AST.node.concretNode.*;
import AST.node.abstractNode.*;
import AST.node.concretNode.stmtNode.*;
import AST.node.concretNode.expNode.*;
import AST.node.concretNode.expNode.atomExpNode.*;

import AST.typeNode.*;
import Utils.*;
import Utils.error.semanticError;
import Utils.log.Log;
import grammar.MxParser;
import grammar.MxParserBaseVisitor;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ASTBuilder extends MxParserBaseVisitor<ASTNode> {
    Log log;

    public ASTBuilder(Log _log) {
        log = _log;
        log.addLog("ASTBuilder init");
    }

    @Override
    public ASTNode visitMxProgram(MxParser.MxProgramContext ctx) {
        log.addLog("[visitMxProgram] start building AST\n");

        RootNode rootNode = new RootNode(new Position(ctx));

        ArrayList<ASTNode> childNodes = new ArrayList<>();
        for (int i = 0; i < ctx.getChildCount(); i++) {
            if (ctx.getChild(i) instanceof MxParser.ClassDefContext) {
                ClassDefNode classDefNode = (ClassDefNode) visit(ctx.getChild(i));
                childNodes.add(classDefNode);
            } else if (ctx.getChild(i) instanceof MxParser.FuncDefContext) {
                FuncDefNode funcDefNode = (FuncDefNode) visit(ctx.getChild(i));
                childNodes.add(funcDefNode);
            } else if (ctx.getChild(i) instanceof MxParser.VarDefStmtContext) {
                VarDefStmtNode varDefStmtNode = (VarDefStmtNode) visit(ctx.getChild(i));
                for (int j = 0; j < varDefStmtNode.varDefUnitNodes.size(); j++) {
                    VarDefUnitNode varDefUnitNode = varDefStmtNode.varDefUnitNodes.get(j);
                    varDefUnitNode.isGlobal = true;
                    childNodes.add(varDefUnitNode);
                }
            }
        }
        rootNode.childNodes = childNodes;

        return rootNode;
    }

    @Override
    public ASTNode visitClassDef(MxParser.ClassDefContext ctx) {
        ClassDefNode classDefNode = new ClassDefNode(new Position(ctx));

        LinkedHashMap<String, VarDefUnitNode> memberVarList = new LinkedHashMap<>();
        LinkedHashMap<String, FuncDefNode> memberFuncList = new LinkedHashMap<>();
        ConstructorDefNode constructorDefNode = null;

//        if (ctx.varDefStmt() != null) {
//            for (var i : ctx.varDefStmt()) {
//                VarDefStmtNode varDefStmtNode = (VarDefStmtNode) visit(i);
//                memberVarList.addAll(varDefStmtNode.varDefUnitNodes);
//            }
//        } else if (ctx.funcDef() != null) {
//            for (var i : ctx.funcDef()) {
//                FuncDefNode funcDefNode = (FuncDefNode) visit(i);
//                memberFuncList.add(funcDefNode);
//            }
//        } else if (ctx.constructorDef() != null) {
//            for (var i : ctx.funcDef()) {
//                constructorDefNode = (ConsDefNode) visit(i);
//            }
//        }
        for (int i = 0; i < ctx.getChildCount(); i++) {
            if (ctx.getChild(i) instanceof MxParser.ConstructorDefContext) {

                if (constructorDefNode != null) {
                    throw new semanticError("Class " + ctx.Identifier().getText() + " has more than one constructor", new Position(ctx));
                }
                if (!((MxParser.ConstructorDefContext) ctx.getChild(i)).Identifier().getText().equals(ctx.Identifier().getText())) {
                    throw new semanticError("Constructor name should be the same as class name", new Position(ctx));
                }

                constructorDefNode = (ConstructorDefNode) visit(ctx.getChild(i));
                constructorDefNode.returnType = new Type(ctx.Identifier().getText(), 0, false);
//                FuncDefNode funcDefNode = new FuncDefNode(new Position(ctx));
//                funcDefNode.funcName = ctx.Identifier().getText();
//                funcDefNode.funcBodyNode = constructorDefNode.funcBodyNode;
//                funcDefNode.argList = new ArrayList<>();
//                funcDefNode.returnType = new Type(ctx.Identifier().getText(),0,false);
//                memberFuncList.put(constructorDefNode.funcName, funcDefNode);

            } else if (ctx.getChild(i) instanceof MxParser.FuncDefContext) {
                FuncDefNode funcDefNode = (FuncDefNode) visit(ctx.getChild(i));

                if (memberFuncList.containsKey(funcDefNode.funcName)) {
                    throw new semanticError("Class " + ctx.Identifier().getText() + " has more than one function named " + funcDefNode.funcName, new Position(ctx));
                }
//                if(funcDefNode.funcName.equals(ctx.Identifier().getText())){
//                    throw new semanticError("Class " + ctx.Identifier().getText() + " has a function named " + funcDefNode.funcName + " which is the same as the class name", new Position(ctx));
//                }

                memberFuncList.put(funcDefNode.funcName, funcDefNode);
            } else if (ctx.getChild(i) instanceof MxParser.VarDefStmtContext) {
                VarDefStmtNode varDefStmtNode = (VarDefStmtNode) visit(ctx.getChild(i));
                for (int j = 0; j < varDefStmtNode.varDefUnitNodes.size(); j++) {
                    VarDefUnitNode varDefUnitNode = varDefStmtNode.varDefUnitNodes.get(j);
                    varDefUnitNode.isGlobal = false;

                    if (memberVarList.containsKey(varDefUnitNode.varName)) {
                        throw new semanticError("Class " + ctx.Identifier().getText() + " has more than one variable named " + varDefUnitNode.varName, new Position(ctx));
                    }

                    memberVarList.put(varDefUnitNode.varName, varDefUnitNode);
                }
            }
        }

//        if (constructorDefNode == null) {
//            FuncDefNode funcDefNode = new FuncDefNode(new Position(ctx));
//            funcDefNode.funcName = ctx.Identifier().getText();
//            funcDefNode.returnType = new Type(ctx.Identifier().getText(),0,false);
//            memberFuncList.put(ctx.Identifier().getText(), funcDefNode);
//        }


        classDefNode.className = ctx.Identifier().getText();
        classDefNode.memberVarMap = memberVarList;
        classDefNode.memberFuncMap = memberFuncList;
        classDefNode.constructorDefNode = constructorDefNode;

        //Log
        log.addLog("[visitClassDef] " + classDefNode.PrintClass());

        return classDefNode;
    }

    @Override
    public ASTNode visitFuncDef(MxParser.FuncDefContext ctx) {
        FuncDefNode funcDefNode = new FuncDefNode(new Position(ctx));

        var returnType = (ReturnTypeNode) visit(ctx.returnType());
        String funcName = ctx.Identifier().getText();
        var paramList = new ArrayList<VarDefUnitNode>();
        var paramNameList = new ArrayList<String>();
        SuiteStmtNode funcBodyNode = null;
        boolean isBuiltin = false;

        if (ctx.funcDefArgList() != null) {
            for (int i = 0; i < ctx.funcDefArgList().varType().size(); i++) {
                VarDefUnitNode varDefUnitNode = new VarDefUnitNode(new Position(ctx));
                var varType = (VarTypeNode) visit(ctx.funcDefArgList().varType(i));
                var varName = ctx.funcDefArgList().Identifier(i).getText();
                varDefUnitNode.varType = varType.varType;
                varDefUnitNode.varName = varName;

                if (paramNameList.contains(varName)) {
                    throw new semanticError("Function " + funcName + " has more than one parameter named " + varName, new Position(ctx));
                }

                paramList.add(varDefUnitNode);
                paramNameList.add(varName);
            }
        }
        if (ctx.suiteStmt() != null) {
            funcBodyNode = (SuiteStmtNode) visit(ctx.suiteStmt());
        }

        if (funcName.equals("print") ||
                funcName.equals("println") ||
                funcName.equals("printInt") ||
                funcName.equals("printlnInt") ||
                funcName.equals("getString") ||
                funcName.equals("getInt") ||
                funcName.equals("toString")) {
            isBuiltin = true;
        }

        funcDefNode.returnType = returnType.returnType;
        funcDefNode.funcName = funcName;
        funcDefNode.argList = paramList;
        funcDefNode.funcBodyNode = funcBodyNode;
        funcDefNode.isBuiltin = isBuiltin;

        log.addLog("[visitFuncDef] " + funcDefNode.PrintFunc());

        return funcDefNode;
    }


    @Override
    public ASTNode visitConstructorDef(MxParser.ConstructorDefContext ctx) {
        var constructorDefNode = new ConstructorDefNode(new Position(ctx));

        SuiteStmtNode funcBodyNode = null;
        if (ctx.suiteStmt() != null) {
            funcBodyNode = (SuiteStmtNode) visit(ctx.suiteStmt());
        }
        constructorDefNode.funcName = ctx.Identifier().getText();
        constructorDefNode.funcBodyNode = funcBodyNode;

        return constructorDefNode;
    }

    //type
    @Override
    public ASTNode visitReturnType(MxParser.ReturnTypeContext ctx) {
        String typeName = null;
        int dimSize = 0;

        if (ctx.VoidType() != null) {
            typeName = "void";
        } else {
            VarTypeNode varTypeNode = (VarTypeNode) visit(ctx.varType());
            typeName = varTypeNode.varType.typeName;
            dimSize = varTypeNode.varType.dimSize;
        }
        return new ReturnTypeNode(typeName, dimSize, new Position(ctx));
    }

    @Override
    public ASTNode visitVarType(MxParser.VarTypeContext ctx) {
        String typeName = null;
        int dimSize = 0;

        if (ctx.basicVarType() != null) {
            typeName = ctx.basicVarType().getText();
        }
        if (ctx.LeftBracket() != null) {
            dimSize = ctx.LeftBracket().size();
        }
        return new VarTypeNode(typeName, dimSize, new Position(ctx));
    }

    //statement

    @Override
    public ASTNode visitSuiteStmt(MxParser.SuiteStmtContext ctx) {
        SuiteStmtNode suiteStmtNode = new SuiteStmtNode(new Position(ctx));
        var stmtList = new ArrayList<StmtNode>();
        for (int i = 0; i < ctx.statement().size(); i++) {
            StmtNode cur = (StmtNode) visit(ctx.statement(i));
            if (cur instanceof VarDefStmtNode) {
                stmtList.addAll(((VarDefStmtNode) cur).varDefUnitNodes);
            } else {
                stmtList.add(cur);
            }
        }
        suiteStmtNode.stmtList = stmtList;
        return suiteStmtNode;
    }


    @Override
    public ASTNode visitVarDefStmt(MxParser.VarDefStmtContext ctx) {
        return visit(ctx.varDefBody());
    }

    @Override
    public ASTNode visitVarDefBody(MxParser.VarDefBodyContext ctx) {
        VarDefStmtNode varDefStmtNode = new VarDefStmtNode(new Position(ctx));

        var varType = (VarTypeNode) visit(ctx.varType());
        var varDefUnitNodes = new ArrayList<VarDefUnitNode>();

        for (int i = 0; i < ctx.varDefUnit().size(); i++) {
            VarDefUnitNode varDefUnitNode = new VarDefUnitNode(new Position(ctx.varDefUnit().get(i)));
            varDefUnitNode.varType = varType.varType;
            varDefUnitNode.varName = ctx.varDefUnit(i).Identifier().getText();
            if (ctx.varDefUnit(i).expression() != null) {
                varDefUnitNode.initValue = (ExpNode) visit(ctx.varDefUnit(i).expression());
            }
            varDefUnitNodes.add(varDefUnitNode);
        }
        varDefStmtNode.varDefUnitNodes = varDefUnitNodes;
        return varDefStmtNode;
    }


    @Override
    public ASTNode visitIfStmt(MxParser.IfStmtContext ctx) {
        IfStmtNode ifStmtNode = new IfStmtNode(new Position(ctx));

        ArrayList<StmtNode> trueStmtList = new ArrayList<>();
        ArrayList<StmtNode> elseStmtList = new ArrayList<>();

        StmtNode trueStmtNode = null;
        StmtNode elseStmtNode = null;

        if (ctx.trueStmt != null) {
            trueStmtNode = (StmtNode) visit(ctx.trueStmt);
        }

        if (ctx.elseStmt != null) {
            elseStmtNode = (StmtNode) visit(ctx.elseStmt);
        }

        var condition = (ExpNode) visit(ctx.expression());

        if (trueStmtNode instanceof SuiteStmtNode) {
            trueStmtList.addAll(((SuiteStmtNode) trueStmtNode).stmtList);
        }

        if (elseStmtNode instanceof SuiteStmtNode) {
            elseStmtList.addAll(((SuiteStmtNode) elseStmtNode).stmtList);
        }

        ifStmtNode.trueStmtList = trueStmtList;
        ifStmtNode.elseStmtList = elseStmtList;
        ifStmtNode.condExpNode = condition;

        return ifStmtNode;
    }

    @Override
    public ASTNode visitWhileStmt(MxParser.WhileStmtContext ctx) {
        WhileStmtNode whileStmtNode = new WhileStmtNode(new Position(ctx));

        whileStmtNode.condExpNode = (ExpNode) visit(ctx.expression());
        StmtNode body = (StmtNode) visit(ctx.statement());

        if (body instanceof SuiteStmtNode) {
            whileStmtNode.bodyStmtList.addAll(((SuiteStmtNode) body).stmtList);
        } else {
            whileStmtNode.bodyStmtList.add(body);
        }

        return whileStmtNode;
    }

    @Override
    public ASTNode visitForStmt(MxParser.ForStmtContext ctx) {
        ForStmtNode forStmtNode = new ForStmtNode(new Position(ctx));

        if (ctx.forInit() != null) {
            if (ctx.forInit().varDefBody() != null) {
                forStmtNode.initVarDefUnitList.addAll(((VarDefStmtNode) visit(ctx.forInit().varDefBody())).varDefUnitNodes);
            } else if (ctx.forInit().expression() != null) {
                forStmtNode.initExpNode = (ExpNode) visit(ctx.forInit().expression());
            }
        }
        if (ctx.forCondition != null) {
            forStmtNode.condExpNode = (ExpNode) visit(ctx.forCondition);
        }
        if (ctx.forStep != null) {
            forStmtNode.stepExpNode = (ExpNode) visit(ctx.forStep);
        }
        var body = (StmtNode) visit(ctx.statement());
        if (body instanceof SuiteStmtNode) {
            forStmtNode.bodyStmtNode = new SuiteStmtNode(new Position(ctx));
            forStmtNode.bodyStmtNode = (SuiteStmtNode) body;
        } else {
            if (body instanceof ReturnStmtNode) {
                forStmtNode.bodyStmtNode = new SuiteStmtNode(new Position(ctx));
                forStmtNode.bodyStmtNode.stmtList.add(body);
                forStmtNode.bodyStmtNode.hasReturn = true;
                forStmtNode.bodyStmtNode.retType = ((ReturnStmtNode) body).returnExp.exprType;
            } else {
                forStmtNode.bodyStmtNode = new SuiteStmtNode(new Position(ctx));
                forStmtNode.bodyStmtNode.stmtList.add(body);
            }
        }

        return forStmtNode;
    }

    @Override
    public ASTNode visitBreakStmt(MxParser.BreakStmtContext ctx) {
        return new BreakStmtNode(new Position(ctx));
    }

    @Override
    public ASTNode visitContinueStmt(MxParser.ContinueStmtContext ctx) {
        return new ContinueStmtNode(new Position(ctx));
    }

    @Override
    public ASTNode visitReturnStmt(MxParser.ReturnStmtContext ctx) {
        ReturnStmtNode returnStmtNode = new ReturnStmtNode(new Position(ctx));
        if (ctx.expression() != null) {
            returnStmtNode.returnExp = (ExpNode) visit(ctx.expression());
        }
        return returnStmtNode;
    }

    @Override
    public ASTNode visitExpressionStmt(MxParser.ExpressionStmtContext ctx) {
        ExpNode expNode = (ExpNode) visit(ctx.expression());
        ExpStmtNode expStmtNode = new ExpStmtNode(new Position(ctx));
        expStmtNode.expNode = expNode;
        return expStmtNode;
    }

    @Override
    public ASTNode visitEmptyStmt(MxParser.EmptyStmtContext ctx) {
        return new EmptyStmtNode(new Position(ctx));
    }

    //expression
    @Override
    public ASTNode visitAtomExp(MxParser.AtomExpContext ctx) {
        if (ctx.atomExpression().Identifier() != null) {
            return new IdExpNode(ctx.atomExpression().Identifier().getText(), new Position(ctx.atomExpression()));
        } else if (ctx.atomExpression().IntegerConstant() != null) {
            return new IntExpNode(Integer.parseInt(ctx.atomExpression().IntegerConstant().getText()), new Position(ctx.atomExpression()));
        } else if (ctx.atomExpression().StringConstant() != null) {
            return new StringExpNode(ctx.atomExpression().StringConstant().getText(), new Position(ctx.atomExpression()));
        } else if (ctx.atomExpression().TrueConstant() != null) {
            return new BoolExpNode(Boolean.parseBoolean(ctx.atomExpression().TrueConstant().getText()), new Position(ctx.atomExpression()));
        } else if (ctx.atomExpression().FalseConstant() != null) {
            return new BoolExpNode(Boolean.parseBoolean(ctx.atomExpression().FalseConstant().getText()), new Position(ctx.atomExpression()));
        } else if (ctx.atomExpression().NullConstant() != null) {
            return new NullExpNode(new Position(ctx.atomExpression()));
        } else if (ctx.atomExpression().ThisPointer() != null) {
            return new ThisExpNode(new Position(ctx.atomExpression()));
        }
        return null;
    }

    @Override
    public ASTNode visitParenExp(MxParser.ParenExpContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public ASTNode visitNewExp(MxParser.NewExpContext ctx) {
        NewExpNode newExpNode = new NewExpNode(new Position(ctx));
        String returnTypeName = null;
        int dim = ctx.newArrSize().size();
//        TypeEnum builtInType = null;
        if (ctx.newArrSize().size() == 0) {

        } else {
            boolean isAllBlank = true;
            for (var i = ctx.newArrSize().size(); i >= 0; i--) {
                if (ctx.newArrSize(i) != null) {
                    if (ctx.newArrSize(i).expression() != null) {
                        isAllBlank = false;
                        newExpNode.SizeList.add((ExpNode) visit(ctx.newArrSize(i).expression()));
                    } else {
                        if (!isAllBlank)
                            throw new semanticError("[ASTBuilder]new array size error", new Position(ctx.newArrSize(i)));
                    }
//                else {
//                    throw new semanticError("New Array size cannot be empty", new Position(ctx.newArrSize(i)));
//                }
                }
            }
        }


        if (ctx.basicVarType() != null) {
            returnTypeName = ctx.basicVarType().getText();
        }
        newExpNode.resultType = new Type(returnTypeName, dim, false);
        return newExpNode;
    }


    @Override
    public ASTNode visitSuffixExp(MxParser.SuffixExpContext ctx) {
        return new SuffixExpNode((ExpNode) visit(ctx.expression()), ctx.suffixOps().getText(), new Position(ctx));
    }

    @Override
    public ASTNode visitFuncCallExp(MxParser.FuncCallExpContext ctx) {
        var ArgList = new ArrayList<ExpNode>();
        var funcExp = (ExpNode) visit(ctx.expression());
        if (ctx.funcCallArgList() != null) {
            for (var i = 0; i < ctx.funcCallArgList().expression().size(); i++) {
                ArgList.add((ExpNode) visit(ctx.funcCallArgList().expression(i)));
            }
        }
        return new FuncCallExpNode(funcExp, ArgList, new Position(ctx));
    }

    @Override
    public ASTNode visitIndexExp(MxParser.IndexExpContext ctx) {
        return new IndexExpNode((ExpNode) visit(ctx.expression(0)), (ExpNode) visit(ctx.expression(1)), new Position(ctx));
    }

    @Override
    public ASTNode visitMemberExp(MxParser.MemberExpContext ctx) {
        return new MemberExpNode((ExpNode) visit(ctx.expression()), ctx.Identifier().getText(), new Position(ctx));
    }

    @Override
    public ASTNode visitLambdaExp(MxParser.LambdaExpContext ctx) {
        LambdaExpNode lambdaExpNode = new LambdaExpNode(new Position(ctx));

        var defArgList = new ArrayList<VarDefUnitNode>();
        var callArgList = new ArrayList<ExpNode>();
        var body = (SuiteStmtNode) visit(ctx.lambdaExpression().suiteStmt());
        boolean isCapture = false;
        if (ctx.lambdaExpression().funcCallArgList() != null) {
            for (var i = 0; i < ctx.lambdaExpression().funcCallArgList().expression().size(); i++) {
                callArgList.add((ExpNode) visit(ctx.lambdaExpression().funcCallArgList().expression(i)));
            }
        }

        if (ctx.lambdaExpression().funcDefArgList() != null) {
            for (int i = 0; i < ctx.lambdaExpression().funcDefArgList().varType().size(); i++) {
                VarDefUnitNode varDefUnitNode = new VarDefUnitNode(new Position(ctx.lambdaExpression().funcDefArgList().varType().get(i)));
                var varType = (VarTypeNode) visit(ctx.lambdaExpression().funcDefArgList().varType(i));
                var varName = ctx.lambdaExpression().funcDefArgList().Identifier(i).getText();
                varDefUnitNode.varType = varType.varType;
                varDefUnitNode.varName = varName;
                defArgList.add(varDefUnitNode);
            }
        }

        if (ctx.lambdaExpression().BitAndOp() != null) {
            isCapture = true;
        }

        lambdaExpNode.paraList = defArgList;
        lambdaExpNode.body = body;
        lambdaExpNode.callArgList = callArgList;
        lambdaExpNode.isCapture = isCapture;

        return lambdaExpNode;
    }


    @Override
    public ASTNode visitPrefixExp(MxParser.PrefixExpContext ctx) {
        return new PrefixExpNode((ExpNode) visit(ctx.expression()), ctx.prefixOps().getText(), new Position(ctx));
    }


    @Override
    public ASTNode visitUnaryExp(MxParser.UnaryExpContext ctx) {
        return new UnaryExpNode((ExpNode) visit(ctx.expression()), ctx.unaryOps().getText(), new Position(ctx));
    }

    @Override
    public ASTNode visitBinaryExp(MxParser.BinaryExpContext ctx) {
        BinaryExpNode binaryExpNode = new BinaryExpNode(new Position(ctx));
        if (ctx.mulLevelOps() != null) {
            switch (ctx.mulLevelOps().getText()) {
                case "*" -> binaryExpNode.operator = BinaryExpNode.BinaryOp.MulOp;
                case "/" -> binaryExpNode.operator = BinaryExpNode.BinaryOp.DivOp;
                case "%" -> binaryExpNode.operator = BinaryExpNode.BinaryOp.ModOp;
            }
        } else if (ctx.addLevelOps() != null) {
            switch (ctx.addLevelOps().getText()) {
                case "+" -> binaryExpNode.operator = BinaryExpNode.BinaryOp.PlusOp;
                case "-" -> binaryExpNode.operator = BinaryExpNode.BinaryOp.MinusOp;
            }
        } else if (ctx.shiftOps() != null) {
            switch (ctx.shiftOps().getText()) {
                case "<<" -> binaryExpNode.operator = BinaryExpNode.BinaryOp.LeftShiftOp;
                case ">>" -> binaryExpNode.operator = BinaryExpNode.BinaryOp.RightShiftOp;
            }
        } else if (ctx.BitAndOp() != null) {
            binaryExpNode.operator = BinaryExpNode.BinaryOp.BitAndOp;
        } else if (ctx.BitXorOp() != null) {
            binaryExpNode.operator = BinaryExpNode.BinaryOp.BitXorOp;
        } else if (ctx.BitOrOp() != null) {
            binaryExpNode.operator = BinaryExpNode.BinaryOp.BitOrOp;
        }

        binaryExpNode.lhs = (ExpNode) visit(ctx.expression(0));
        binaryExpNode.rhs = (ExpNode) visit(ctx.expression(1));

        return binaryExpNode;
    }

    @Override
    public ASTNode visitBinaryBoolExp(MxParser.BinaryBoolExpContext ctx) {
        BinaryBoolExpNode binaryBoolExpNode = new BinaryBoolExpNode(new Position(ctx));
        if (ctx.relationalOps() != null) {
            switch (ctx.relationalOps().getText()) {
                case "<" -> binaryBoolExpNode.operator = BinaryBoolExpNode.BinaryOp.LessOp;
                case ">" -> binaryBoolExpNode.operator = BinaryBoolExpNode.BinaryOp.GreaterOp;
                case "<=" -> binaryBoolExpNode.operator = BinaryBoolExpNode.BinaryOp.LessEqualOp;
                case ">=" -> binaryBoolExpNode.operator = BinaryBoolExpNode.BinaryOp.GreaterEqualOp;
            }
        } else if (ctx.equalityOps() != null) {
            switch (ctx.equalityOps().getText()) {
                case "==" -> binaryBoolExpNode.operator = BinaryBoolExpNode.BinaryOp.EqualOp;
                case "!=" -> binaryBoolExpNode.operator = BinaryBoolExpNode.BinaryOp.NotEqualOp;
            }
        } else if (ctx.LogicAndOp() != null) {
            binaryBoolExpNode.operator = BinaryBoolExpNode.BinaryOp.LogicAndOp;
        } else if (ctx.LogicOrOp() != null) {
            binaryBoolExpNode.operator = BinaryBoolExpNode.BinaryOp.LogicOrOp;
        }

        binaryBoolExpNode.lhs = (ExpNode) visit(ctx.expression(0));
        binaryBoolExpNode.rhs = (ExpNode) visit(ctx.expression(1));

        return binaryBoolExpNode;
    }

    @Override
    public ASTNode visitAssignExp(MxParser.AssignExpContext ctx) {
        AssignExpNode assignExpNode = new AssignExpNode(new Position(ctx));

        assignExpNode.lhs = (ExpNode) visit(ctx.expression(0));
        assignExpNode.rhs = (ExpNode) visit(ctx.expression(1));

        return assignExpNode;
    }
}