// Generated from java-escape by ANTLR 4.11.1
package grammar;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MxParser}.
 */
public interface MxParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MxParser#mxProgram}.
	 * @param ctx the parse tree
	 */
	void enterMxProgram(MxParser.MxProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#mxProgram}.
	 * @param ctx the parse tree
	 */
	void exitMxProgram(MxParser.MxProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#classDef}.
	 * @param ctx the parse tree
	 */
	void enterClassDef(MxParser.ClassDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#classDef}.
	 * @param ctx the parse tree
	 */
	void exitClassDef(MxParser.ClassDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#constructorDef}.
	 * @param ctx the parse tree
	 */
	void enterConstructorDef(MxParser.ConstructorDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#constructorDef}.
	 * @param ctx the parse tree
	 */
	void exitConstructorDef(MxParser.ConstructorDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#returnType}.
	 * @param ctx the parse tree
	 */
	void enterReturnType(MxParser.ReturnTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#returnType}.
	 * @param ctx the parse tree
	 */
	void exitReturnType(MxParser.ReturnTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#funcDef}.
	 * @param ctx the parse tree
	 */
	void enterFuncDef(MxParser.FuncDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#funcDef}.
	 * @param ctx the parse tree
	 */
	void exitFuncDef(MxParser.FuncDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#funcDefArgList}.
	 * @param ctx the parse tree
	 */
	void enterFuncDefArgList(MxParser.FuncDefArgListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#funcDefArgList}.
	 * @param ctx the parse tree
	 */
	void exitFuncDefArgList(MxParser.FuncDefArgListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#funcCallArgList}.
	 * @param ctx the parse tree
	 */
	void enterFuncCallArgList(MxParser.FuncCallArgListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#funcCallArgList}.
	 * @param ctx the parse tree
	 */
	void exitFuncCallArgList(MxParser.FuncCallArgListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#builtinType}.
	 * @param ctx the parse tree
	 */
	void enterBuiltinType(MxParser.BuiltinTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#builtinType}.
	 * @param ctx the parse tree
	 */
	void exitBuiltinType(MxParser.BuiltinTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#basicVarType}.
	 * @param ctx the parse tree
	 */
	void enterBasicVarType(MxParser.BasicVarTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#basicVarType}.
	 * @param ctx the parse tree
	 */
	void exitBasicVarType(MxParser.BasicVarTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#varType}.
	 * @param ctx the parse tree
	 */
	void enterVarType(MxParser.VarTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#varType}.
	 * @param ctx the parse tree
	 */
	void exitVarType(MxParser.VarTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#varDefUnit}.
	 * @param ctx the parse tree
	 */
	void enterVarDefUnit(MxParser.VarDefUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#varDefUnit}.
	 * @param ctx the parse tree
	 */
	void exitVarDefUnit(MxParser.VarDefUnitContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#varDefBody}.
	 * @param ctx the parse tree
	 */
	void enterVarDefBody(MxParser.VarDefBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#varDefBody}.
	 * @param ctx the parse tree
	 */
	void exitVarDefBody(MxParser.VarDefBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#suiteStmt}.
	 * @param ctx the parse tree
	 */
	void enterSuiteStmt(MxParser.SuiteStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#suiteStmt}.
	 * @param ctx the parse tree
	 */
	void exitSuiteStmt(MxParser.SuiteStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#varDefStmt}.
	 * @param ctx the parse tree
	 */
	void enterVarDefStmt(MxParser.VarDefStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#varDefStmt}.
	 * @param ctx the parse tree
	 */
	void exitVarDefStmt(MxParser.VarDefStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(MxParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(MxParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#ifStmt}.
	 * @param ctx the parse tree
	 */
	void enterIfStmt(MxParser.IfStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#ifStmt}.
	 * @param ctx the parse tree
	 */
	void exitIfStmt(MxParser.IfStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#whileStmt}.
	 * @param ctx the parse tree
	 */
	void enterWhileStmt(MxParser.WhileStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#whileStmt}.
	 * @param ctx the parse tree
	 */
	void exitWhileStmt(MxParser.WhileStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#forInit}.
	 * @param ctx the parse tree
	 */
	void enterForInit(MxParser.ForInitContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#forInit}.
	 * @param ctx the parse tree
	 */
	void exitForInit(MxParser.ForInitContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#forStmt}.
	 * @param ctx the parse tree
	 */
	void enterForStmt(MxParser.ForStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#forStmt}.
	 * @param ctx the parse tree
	 */
	void exitForStmt(MxParser.ForStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#breakStmt}.
	 * @param ctx the parse tree
	 */
	void enterBreakStmt(MxParser.BreakStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#breakStmt}.
	 * @param ctx the parse tree
	 */
	void exitBreakStmt(MxParser.BreakStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#continueStmt}.
	 * @param ctx the parse tree
	 */
	void enterContinueStmt(MxParser.ContinueStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#continueStmt}.
	 * @param ctx the parse tree
	 */
	void exitContinueStmt(MxParser.ContinueStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#returnStmt}.
	 * @param ctx the parse tree
	 */
	void enterReturnStmt(MxParser.ReturnStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#returnStmt}.
	 * @param ctx the parse tree
	 */
	void exitReturnStmt(MxParser.ReturnStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#expressionStmt}.
	 * @param ctx the parse tree
	 */
	void enterExpressionStmt(MxParser.ExpressionStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#expressionStmt}.
	 * @param ctx the parse tree
	 */
	void exitExpressionStmt(MxParser.ExpressionStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#emptyStmt}.
	 * @param ctx the parse tree
	 */
	void enterEmptyStmt(MxParser.EmptyStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#emptyStmt}.
	 * @param ctx the parse tree
	 */
	void exitEmptyStmt(MxParser.EmptyStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#atomExpression}.
	 * @param ctx the parse tree
	 */
	void enterAtomExpression(MxParser.AtomExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#atomExpression}.
	 * @param ctx the parse tree
	 */
	void exitAtomExpression(MxParser.AtomExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#suffixOps}.
	 * @param ctx the parse tree
	 */
	void enterSuffixOps(MxParser.SuffixOpsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#suffixOps}.
	 * @param ctx the parse tree
	 */
	void exitSuffixOps(MxParser.SuffixOpsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#prefixOps}.
	 * @param ctx the parse tree
	 */
	void enterPrefixOps(MxParser.PrefixOpsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#prefixOps}.
	 * @param ctx the parse tree
	 */
	void exitPrefixOps(MxParser.PrefixOpsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#unaryOps}.
	 * @param ctx the parse tree
	 */
	void enterUnaryOps(MxParser.UnaryOpsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#unaryOps}.
	 * @param ctx the parse tree
	 */
	void exitUnaryOps(MxParser.UnaryOpsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#mulLevelOps}.
	 * @param ctx the parse tree
	 */
	void enterMulLevelOps(MxParser.MulLevelOpsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#mulLevelOps}.
	 * @param ctx the parse tree
	 */
	void exitMulLevelOps(MxParser.MulLevelOpsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#addLevelOps}.
	 * @param ctx the parse tree
	 */
	void enterAddLevelOps(MxParser.AddLevelOpsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#addLevelOps}.
	 * @param ctx the parse tree
	 */
	void exitAddLevelOps(MxParser.AddLevelOpsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#shiftOps}.
	 * @param ctx the parse tree
	 */
	void enterShiftOps(MxParser.ShiftOpsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#shiftOps}.
	 * @param ctx the parse tree
	 */
	void exitShiftOps(MxParser.ShiftOpsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#relationalOps}.
	 * @param ctx the parse tree
	 */
	void enterRelationalOps(MxParser.RelationalOpsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#relationalOps}.
	 * @param ctx the parse tree
	 */
	void exitRelationalOps(MxParser.RelationalOpsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#equalityOps}.
	 * @param ctx the parse tree
	 */
	void enterEqualityOps(MxParser.EqualityOpsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#equalityOps}.
	 * @param ctx the parse tree
	 */
	void exitEqualityOps(MxParser.EqualityOpsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(MxParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(MxParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#lambdaExpression}.
	 * @param ctx the parse tree
	 */
	void enterLambdaExpression(MxParser.LambdaExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#lambdaExpression}.
	 * @param ctx the parse tree
	 */
	void exitLambdaExpression(MxParser.LambdaExpressionContext ctx);
}