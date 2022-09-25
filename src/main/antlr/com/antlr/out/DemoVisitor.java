// Generated from D:/Work/studying materials/computer related/±‡“Î∆˜øŒ≥Ã…Ëº∆/myCompiler/Mx-Compiler/src/main/antlr\Demo.g4 by ANTLR 4.10.1
package com.antlr.out;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link DemoParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface DemoVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link DemoParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt(DemoParser.StmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Add}
	 * labeled alternative in {@link DemoParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdd(DemoParser.AddContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Div}
	 * labeled alternative in {@link DemoParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDiv(DemoParser.DivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Min}
	 * labeled alternative in {@link DemoParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMin(DemoParser.MinContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Mul}
	 * labeled alternative in {@link DemoParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMul(DemoParser.MulContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Int}
	 * labeled alternative in {@link DemoParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInt(DemoParser.IntContext ctx);
}