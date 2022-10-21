// Generated from java-escape by ANTLR 4.11.1
package grammar;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MxParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MxParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MxParser#mxProgram}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMxProgram(MxParser.MxProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#classDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDef(MxParser.ClassDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#constructorDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstructorDef(MxParser.ConstructorDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#returnType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnType(MxParser.ReturnTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#funcDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncDef(MxParser.FuncDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#builtinType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBuiltinType(MxParser.BuiltinTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#basicVarType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBasicVarType(MxParser.BasicVarTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#varType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarType(MxParser.VarTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#varDefUnit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDefUnit(MxParser.VarDefUnitContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#varDefBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDefBody(MxParser.VarDefBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#suiteStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSuiteStmt(MxParser.SuiteStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#varDefStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDefStmt(MxParser.VarDefStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(MxParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#ifStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStmt(MxParser.IfStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#whileStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStmt(MxParser.WhileStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#forInit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForInit(MxParser.ForInitContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#forStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStmt(MxParser.ForStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#breakStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakStmt(MxParser.BreakStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#continueStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinueStmt(MxParser.ContinueStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#returnStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStmt(MxParser.ReturnStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#expressionStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionStmt(MxParser.ExpressionStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#emptyStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmptyStmt(MxParser.EmptyStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#atomExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomExpression(MxParser.AtomExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#suffixOps}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSuffixOps(MxParser.SuffixOpsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#prefixOps}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrefixOps(MxParser.PrefixOpsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#unaryOps}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryOps(MxParser.UnaryOpsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#mulLevelOps}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulLevelOps(MxParser.MulLevelOpsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#addLevelOps}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddLevelOps(MxParser.AddLevelOpsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#shiftOps}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShiftOps(MxParser.ShiftOpsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#relationalOps}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationalOps(MxParser.RelationalOpsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#equalityOps}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualityOps(MxParser.EqualityOpsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binaryExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryExp(MxParser.BinaryExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignExp(MxParser.AssignExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code memberExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemberExp(MxParser.MemberExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code newExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewExp(MxParser.NewExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binaryBoolExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryBoolExp(MxParser.BinaryBoolExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code funcCallExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncCallExp(MxParser.FuncCallExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code atomExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomExp(MxParser.AtomExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code prefixExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrefixExp(MxParser.PrefixExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unaryExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExp(MxParser.UnaryExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lambdaExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLambdaExp(MxParser.LambdaExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenExp(MxParser.ParenExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code suffixExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSuffixExp(MxParser.SuffixExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code indexExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexExp(MxParser.IndexExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#newArrSize}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewArrSize(MxParser.NewArrSizeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#funcDefArgList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncDefArgList(MxParser.FuncDefArgListContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#funcCallArgList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncCallArgList(MxParser.FuncCallArgListContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#lambdaExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLambdaExpression(MxParser.LambdaExpressionContext ctx);
}