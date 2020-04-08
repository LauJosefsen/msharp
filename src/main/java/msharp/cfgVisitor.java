// Generated from C:/Users/Alexi/Desktop/P4-Msharp/src/main/java\cfg.g4 by ANTLR 4.8
package msharp;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link cfgParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface cfgVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link cfgParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(cfgParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by {@link cfgParser#partDcl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartDcl(cfgParser.PartDclContext ctx);
	/**
	 * Visit a parse tree produced by {@link cfgParser#playDcl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlayDcl(cfgParser.PlayDclContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StmtPBody}
	 * labeled alternative in {@link cfgParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtPBody(cfgParser.StmtPBodyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StmtOps}
	 * labeled alternative in {@link cfgParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtOps(cfgParser.StmtOpsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code OpsIntru}
	 * labeled alternative in {@link cfgParser#ops}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOpsIntru(cfgParser.OpsIntruContext ctx);
	/**
	 * Visit a parse tree produced by the {@code OpsOctDown}
	 * labeled alternative in {@link cfgParser#ops}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOpsOctDown(cfgParser.OpsOctDownContext ctx);
	/**
	 * Visit a parse tree produced by the {@code OpsOctUp}
	 * labeled alternative in {@link cfgParser#ops}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOpsOctUp(cfgParser.OpsOctUpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code OpsTempOp}
	 * labeled alternative in {@link cfgParser#ops}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOpsTempOp(cfgParser.OpsTempOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code OpsBpmDcl}
	 * labeled alternative in {@link cfgParser#ops}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOpsBpmDcl(cfgParser.OpsBpmDclContext ctx);
	/**
	 * Visit a parse tree produced by {@link cfgParser#bpmDcl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBpmDcl(cfgParser.BpmDclContext ctx);
	/**
	 * Visit a parse tree produced by {@link cfgParser#tempoOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTempoOp(cfgParser.TempoOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MultStmtStmt}
	 * labeled alternative in {@link cfgParser#multStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultStmtStmt(cfgParser.MultStmtStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MultStmtNL}
	 * labeled alternative in {@link cfgParser#multStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultStmtNL(cfgParser.MultStmtNLContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MultStmtMultRepeat}
	 * labeled alternative in {@link cfgParser#multStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultStmtMultRepeat(cfgParser.MultStmtMultRepeatContext ctx);
	/**
	 * Visit a parse tree produced by {@link cfgParser#multilineRepeat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultilineRepeat(cfgParser.MultilineRepeatContext ctx);
	/**
	 * Visit a parse tree produced by {@link cfgParser#everyStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEveryStmt(cfgParser.EveryStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link cfgParser#elseStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElseStmt(cfgParser.ElseStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PbodyTransUp}
	 * labeled alternative in {@link cfgParser#partBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPbodyTransUp(cfgParser.PbodyTransUpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PbodyPause}
	 * labeled alternative in {@link cfgParser#partBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPbodyPause(cfgParser.PbodyPauseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PbodyTransDown}
	 * labeled alternative in {@link cfgParser#partBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPbodyTransDown(cfgParser.PbodyTransDownContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PbodyId}
	 * labeled alternative in {@link cfgParser#partBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPbodyId(cfgParser.PbodyIdContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PbodySingleAnd}
	 * labeled alternative in {@link cfgParser#partBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPbodySingleAnd(cfgParser.PbodySingleAndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PbodyTone}
	 * labeled alternative in {@link cfgParser#partBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPbodyTone(cfgParser.PbodyToneContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PbodyDoubleAnd}
	 * labeled alternative in {@link cfgParser#partBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPbodyDoubleAnd(cfgParser.PbodyDoubleAndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PbodySingleLRepeat}
	 * labeled alternative in {@link cfgParser#partBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPbodySingleLRepeat(cfgParser.PbodySingleLRepeatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PbodyParen}
	 * labeled alternative in {@link cfgParser#partBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPbodyParen(cfgParser.PbodyParenContext ctx);
}