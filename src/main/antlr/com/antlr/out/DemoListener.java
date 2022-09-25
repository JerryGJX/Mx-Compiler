// Generated from D:/Work/studying materials/computer related/±‡“Î∆˜øŒ≥Ã…Ëº∆/myCompiler/Mx-Compiler/src/main/antlr\Demo.g4 by ANTLR 4.10.1
package com.antlr.out;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link DemoParser}.
 */
public interface DemoListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link DemoParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmt(DemoParser.StmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link DemoParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmt(DemoParser.StmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Add}
	 * labeled alternative in {@link DemoParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAdd(DemoParser.AddContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Add}
	 * labeled alternative in {@link DemoParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAdd(DemoParser.AddContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Div}
	 * labeled alternative in {@link DemoParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterDiv(DemoParser.DivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Div}
	 * labeled alternative in {@link DemoParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitDiv(DemoParser.DivContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Min}
	 * labeled alternative in {@link DemoParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMin(DemoParser.MinContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Min}
	 * labeled alternative in {@link DemoParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMin(DemoParser.MinContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Mul}
	 * labeled alternative in {@link DemoParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMul(DemoParser.MulContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Mul}
	 * labeled alternative in {@link DemoParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMul(DemoParser.MulContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Int}
	 * labeled alternative in {@link DemoParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterInt(DemoParser.IntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Int}
	 * labeled alternative in {@link DemoParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitInt(DemoParser.IntContext ctx);
}