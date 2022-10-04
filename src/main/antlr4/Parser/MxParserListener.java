// Generated from java-escape by ANTLR 4.11.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MxParserParser}.
 */
public interface MxParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MxParserParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmt(MxParserParser.StmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParserParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmt(MxParserParser.StmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Add}
	 * labeled alternative in {@link MxParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAdd(MxParserParser.AddContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Add}
	 * labeled alternative in {@link MxParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAdd(MxParserParser.AddContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Div}
	 * labeled alternative in {@link MxParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterDiv(MxParserParser.DivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Div}
	 * labeled alternative in {@link MxParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitDiv(MxParserParser.DivContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Min}
	 * labeled alternative in {@link MxParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMin(MxParserParser.MinContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Min}
	 * labeled alternative in {@link MxParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMin(MxParserParser.MinContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Mul}
	 * labeled alternative in {@link MxParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMul(MxParserParser.MulContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Mul}
	 * labeled alternative in {@link MxParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMul(MxParserParser.MulContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Int}
	 * labeled alternative in {@link MxParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterInt(MxParserParser.IntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Int}
	 * labeled alternative in {@link MxParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitInt(MxParserParser.IntContext ctx);
}