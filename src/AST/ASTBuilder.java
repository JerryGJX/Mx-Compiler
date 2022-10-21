package AST;

import AST.node.*;
import AST.node.concretNode.*;
import AST.node.abstractNode.*;
import AST.node.concretNode.stmtNode.*;
import AST.node.concretNode.expNode.*;
import AST.node.concretNode.expNode.atomExpNode.*;

import AST.typeNode.*;
import Utils.*;
import grammar.MxParser;
import grammar.MxParserBaseVisitor;

import java.util.ArrayList;

public class ASTBuilder extends MxParserBaseVisitor<ASTNode> {
    @Override
    public ASTNode visitMxProgram(MxParser.MxProgramContext ctx) {
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
                    VarDefUnitNode varDefUnitNode = varDefStmtNode.varDefUnitNodes.get(i);
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

        ArrayList<VarDefUnitNode> memberVarList = new ArrayList<>();
        ArrayList<FuncDefNode> memberFuncList = new ArrayList<>();
        ConsDefNode constructorDefNode = null;

        if (ctx.varDefStmt() != null) {
            for (var i : ctx.varDefStmt()) {
                VarDefStmtNode varDefStmtNode = (VarDefStmtNode) visit(i);
                memberVarList.addAll(varDefStmtNode.varDefUnitNodes);
            }
        } else if (ctx.funcDef() != null) {
            for (var i : ctx.funcDef()) {
                FuncDefNode funcDefNode = (FuncDefNode) visit(i);
                memberFuncList.add(funcDefNode);
            }
        } else if (ctx.constructorDef() != null) {
            for (var i : ctx.funcDef()) {
                constructorDefNode = (ConsDefNode) visit(i);
            }
        }
        for (int i = 0; i < ctx.getChildCount(); i++) {
            if (ctx.getChild(i) instanceof MxParser.ConstructorDefContext) {
                constructorDefNode = (ConsDefNode) visit(ctx.getChild(i));
            } else if (ctx.getChild(i) instanceof MxParser.FuncDefContext) {
                FuncDefNode funcDefNode = (FuncDefNode) visit(ctx.getChild(i));
                memberFuncList.add(funcDefNode);
            } else if (ctx.getChild(i) instanceof MxParser.VarDefStmtContext) {
                VarDefStmtNode varDefStmtNode = (VarDefStmtNode) visit(ctx.getChild(i));
                for (int j = 0; j < varDefStmtNode.varDefUnitNodes.size(); j++) {
                    VarDefUnitNode varDefUnitNode = varDefStmtNode.varDefUnitNodes.get(i);
                    varDefUnitNode.isGlobal = false;
                    memberVarList.add(varDefUnitNode);
                }
            }
        }
        classDefNode.className = ctx.Identifier().getText();
        classDefNode.memberVarList = memberVarList;
        classDefNode.memberFuncList = memberFuncList;
        classDefNode.constructorDefNode = constructorDefNode;

        return classDefNode;
    }

    @Override
    public ASTNode visitFuncDef(MxParser.FuncDefContext ctx) {
        FuncDefNode funcDefNode = new FuncDefNode(new Position(ctx));

        var returnType = (ReturnTypeNode) visit(ctx.returnType());
        String funcName = ctx.Identifier().getText();
        var paramList = new ArrayList<VarDefUnitNode>();
        SuiteStmtNode funcBodyNode = null;
        boolean isBuiltin = false;

        if (ctx.funcDefArgList() != null) {
            for (int i = 0; i < ctx.funcDefArgList().varType().size(); i++) {
                VarDefUnitNode varDefUnitNode = new VarDefUnitNode(new Position(ctx));
                var varType = (VarTypeNode) visit(ctx.funcDefArgList().varType(i));
                var varName = ctx.funcDefArgList().Identifier(i).getText();
                varDefUnitNode.varType = varType;
                varDefUnitNode.varName = varName;
                paramList.add(varDefUnitNode);
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

        funcDefNode.returnType = returnType;
        funcDefNode.funcName = funcName;
        funcDefNode.paramList = paramList;
        funcDefNode.funcBodyNode = funcBodyNode;
        funcDefNode.isBuiltin = isBuiltin;

        return funcDefNode;
    }


    @Override
    public ASTNode visitConstructorDef(MxParser.ConstructorDefContext ctx) {
        var constructorDefNode = new ConsDefNode(new Position(ctx));

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
//        ReturnTypeNode returnTypeNode = new ReturnTypeNode(new Position(ctx));
        TypeEnum typeEnum = null;
        String typeName = null;
        int dimSize = 0;

        if (ctx.VoidType() != null) {
            typeEnum = TypeEnum.VOID;
        } else {
            VarTypeNode varTypeNode = (VarTypeNode) visit(ctx.varType());
            typeEnum = varTypeNode.typeInfo.typeEnum;
            typeName = varTypeNode.typeInfo.typeName;
            dimSize = varTypeNode.dimSize;
        }
        return new ReturnTypeNode(typeEnum, typeName, new Position(ctx), dimSize);
    }

    @Override
    public ASTNode visitVarType(MxParser.VarTypeContext ctx) {
        TypeEnum typeEnum = null;
        String typeName = null;
        int dimSize = 0;

        if (ctx.basicVarType() != null) {
            if (ctx.basicVarType().builtinType() != null) {
                if (ctx.basicVarType().builtinType().IntType() != null) {
                    typeEnum = TypeEnum.INT;
                    typeName = ctx.basicVarType().getText();
                } else if (ctx.basicVarType().builtinType().BoolType() != null) {
                    typeEnum = TypeEnum.BOOL;
                    typeName = ctx.basicVarType().getText();
                } else if (ctx.basicVarType().builtinType().StringType() != null) {
                    typeEnum = TypeEnum.STRING;
                    typeName = ctx.basicVarType().getText();
                }
            }
        } else {
            typeEnum = TypeEnum.CLASS;
            typeName = ctx.basicVarType().Identifier().getText();
        }
        if (ctx.LeftBracket() != null) {
            dimSize = ctx.LeftBracket().size();
        }
        return new VarTypeNode(typeEnum, typeName, new Position(ctx), dimSize);
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
            varDefUnitNode.varType = varType;
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

        StmtNode trueStmt = (StmtNode) visit(ctx.trueStmt);
        StmtNode elseStmt = (StmtNode) visit(ctx.elseStmt);
        var condition = (ExpNode) visit(ctx.expression());

        if (trueStmt instanceof SuiteStmtNode) {
            var stmtList = new ArrayList<StmtNode>();
            stmtList = ((SuiteStmtNode) trueStmt).stmtList;
            trueStmt = new SuiteStmtNode(new Position(ctx));
            ((SuiteStmtNode) trueStmt).stmtList = stmtList;
        }

        if (elseStmt instanceof SuiteStmtNode) {
            var stmtList = new ArrayList<StmtNode>();
            stmtList = ((SuiteStmtNode) elseStmt).stmtList;
            elseStmt = new SuiteStmtNode(new Position(ctx));
            ((SuiteStmtNode) elseStmt).stmtList = stmtList;
        }

        ifStmtNode.trueStmtNode = trueStmt;
        ifStmtNode.elseStmtNode = elseStmt;
        ifStmtNode.condExpNode = condition;

        return ifStmtNode;
    }

    @Override
    public ASTNode visitWhileStmt(MxParser.WhileStmtContext ctx) {
        WhileStmtNode whileStmtNode = new WhileStmtNode(new Position(ctx));

        var condition = (ExpNode) visit(ctx.expression());
        StmtNode body = (StmtNode) visit(ctx.statement());

        if (body instanceof SuiteStmtNode) {
            var stmtList = new ArrayList<StmtNode>();
            stmtList = ((SuiteStmtNode) body).stmtList;
            body = new SuiteStmtNode(new Position(ctx));
            ((SuiteStmtNode) body).stmtList = stmtList;
        }

        whileStmtNode.condExpNode = condition;
        whileStmtNode.bodyStmtNode = body;

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
        if (!(body instanceof SuiteStmtNode)) {
            var stmtList = new ArrayList<StmtNode>();
            stmtList.add(body);
            body = new SuiteStmtNode(new Position(ctx));
            ((SuiteStmtNode) body).stmtList = stmtList;
        }

        forStmtNode.bodyStmtNode = (SuiteStmtNode) body;
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
        return visit(ctx.expression());
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
        int dim = ctx.newArrSize().size() + ((ctx.LeftParen() != null) ? 1 : 0);
        TypeEnum builtInType = null;
        for (var i = 0; i < dim; i++) {
            if (ctx.newArrSize(i) != null) {
                newExpNode.SizeList.add((ExpNode) visit(ctx.newArrSize(i).expression()));
            } else {
                newExpNode.SizeList.add(null);
            }
        }
        if (ctx.basicVarType() != null) {
            if (ctx.basicVarType().Identifier() != null) {
                newExpNode.newType = new VarTypeNode(TypeEnum.CLASS, ctx.basicVarType().Identifier().getText(), new Position(ctx), dim);
            } else if (ctx.basicVarType().builtinType() != null) {
                if (ctx.basicVarType().builtinType().IntType() != null) {
                    builtInType = TypeEnum.INT;
                } else if (ctx.basicVarType().builtinType().BoolType() != null) {
                    builtInType = TypeEnum.BOOL;
                } else if (ctx.basicVarType().builtinType().StringType() != null) {
                    builtInType = TypeEnum.STRING;
                }
                newExpNode.newType = new VarTypeNode(builtInType, "", new Position(ctx), dim);
            }
        }
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
                varDefUnitNode.varType = varType;
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