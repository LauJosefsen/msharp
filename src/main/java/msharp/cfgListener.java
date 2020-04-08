package msharp;// Generated from C:/Users/Alexi/Desktop/P4-Msharp/src/main/java\cfg.g4 by ANTLR 4.8
import msharp.cfgParser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link cfgParser}.
 */
public interface cfgListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link cfgParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(cfgParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link cfgParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(cfgParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link cfgParser#partDcl}.
	 * @param ctx the parse tree
	 */
	void enterPartDcl(cfgParser.PartDclContext ctx);
	/**
	 * Exit a parse tree produced by {@link cfgParser#partDcl}.
	 * @param ctx the parse tree
	 */
	void exitPartDcl(cfgParser.PartDclContext ctx);
	/**
	 * Enter a parse tree produced by {@link cfgParser#playDcl}.
	 * @param ctx the parse tree
	 */
	void enterPlayDcl(cfgParser.PlayDclContext ctx);
	/**
	 * Exit a parse tree produced by {@link cfgParser#playDcl}.
	 * @param ctx the parse tree
	 */
	void exitPlayDcl(cfgParser.PlayDclContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StmtPBody}
	 * labeled alternative in {@link cfgParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmtPBody(cfgParser.StmtPBodyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StmtPBody}
	 * labeled alternative in {@link cfgParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmtPBody(cfgParser.StmtPBodyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StmtOps}
	 * labeled alternative in {@link cfgParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmtOps(cfgParser.StmtOpsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StmtOps}
	 * labeled alternative in {@link cfgParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmtOps(cfgParser.StmtOpsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code OpsIntru}
	 * labeled alternative in {@link cfgParser#ops}.
	 * @param ctx the parse tree
	 */
	void enterOpsIntru(cfgParser.OpsIntruContext ctx);
	/**
	 * Exit a parse tree produced by the {@code OpsIntru}
	 * labeled alternative in {@link cfgParser#ops}.
	 * @param ctx the parse tree
	 */
	void exitOpsIntru(cfgParser.OpsIntruContext ctx);
	/**
	 * Enter a parse tree produced by the {@code OpsOctDown}
	 * labeled alternative in {@link cfgParser#ops}.
	 * @param ctx the parse tree
	 */
	void enterOpsOctDown(cfgParser.OpsOctDownContext ctx);
	/**
	 * Exit a parse tree produced by the {@code OpsOctDown}
	 * labeled alternative in {@link cfgParser#ops}.
	 * @param ctx the parse tree
	 */
	void exitOpsOctDown(cfgParser.OpsOctDownContext ctx);
	/**
	 * Enter a parse tree produced by the {@code OpsOctUp}
	 * labeled alternative in {@link cfgParser#ops}.
	 * @param ctx the parse tree
	 */
	void enterOpsOctUp(cfgParser.OpsOctUpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code OpsOctUp}
	 * labeled alternative in {@link cfgParser#ops}.
	 * @param ctx the parse tree
	 */
	void exitOpsOctUp(cfgParser.OpsOctUpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code OpsTempOp}
	 * labeled alternative in {@link cfgParser#ops}.
	 * @param ctx the parse tree
	 */
	void enterOpsTempOp(cfgParser.OpsTempOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code OpsTempOp}
	 * labeled alternative in {@link cfgParser#ops}.
	 * @param ctx the parse tree
	 */
	void exitOpsTempOp(cfgParser.OpsTempOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code OpsBpmDcl}
	 * labeled alternative in {@link cfgParser#ops}.
	 * @param ctx the parse tree
	 */
	void enterOpsBpmDcl(cfgParser.OpsBpmDclContext ctx);
	/**
	 * Exit a parse tree produced by the {@code OpsBpmDcl}
	 * labeled alternative in {@link cfgParser#ops}.
	 * @param ctx the parse tree
	 */
	void exitOpsBpmDcl(cfgParser.OpsBpmDclContext ctx);
	/**
	 * Enter a parse tree produced by {@link cfgParser#bpmDcl}.
	 * @param ctx the parse tree
	 */
	void enterBpmDcl(cfgParser.BpmDclContext ctx);
	/**
	 * Exit a parse tree produced by {@link cfgParser#bpmDcl}.
	 * @param ctx the parse tree
	 */
	void exitBpmDcl(cfgParser.BpmDclContext ctx);
	/**
	 * Enter a parse tree produced by {@link cfgParser#tempoOp}.
	 * @param ctx the parse tree
	 */
	void enterTempoOp(cfgParser.TempoOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link cfgParser#tempoOp}.
	 * @param ctx the parse tree
	 */
	void exitTempoOp(cfgParser.TempoOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MultStmtStmt}
	 * labeled alternative in {@link cfgParser#multStmt}.
	 * @param ctx the parse tree
	 */
	void enterMultStmtStmt(cfgParser.MultStmtStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MultStmtStmt}
	 * labeled alternative in {@link cfgParser#multStmt}.
	 * @param ctx the parse tree
	 */
	void exitMultStmtStmt(cfgParser.MultStmtStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MultStmtNL}
	 * labeled alternative in {@link cfgParser#multStmt}.
	 * @param ctx the parse tree
	 */
	void enterMultStmtNL(cfgParser.MultStmtNLContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MultStmtNL}
	 * labeled alternative in {@link cfgParser#multStmt}.
	 * @param ctx the parse tree
	 */
	void exitMultStmtNL(cfgParser.MultStmtNLContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MultStmtMultRepeat}
	 * labeled alternative in {@link cfgParser#multStmt}.
	 * @param ctx the parse tree
	 */
	void enterMultStmtMultRepeat(cfgParser.MultStmtMultRepeatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MultStmtMultRepeat}
	 * labeled alternative in {@link cfgParser#multStmt}.
	 * @param ctx the parse tree
	 */
	void exitMultStmtMultRepeat(cfgParser.MultStmtMultRepeatContext ctx);
	/**
	 * Enter a parse tree produced by {@link cfgParser#multilineRepeat}.
	 * @param ctx the parse tree
	 */
	void enterMultilineRepeat(cfgParser.MultilineRepeatContext ctx);
	/**
	 * Exit a parse tree produced by {@link cfgParser#multilineRepeat}.
	 * @param ctx the parse tree
	 */
	void exitMultilineRepeat(cfgParser.MultilineRepeatContext ctx);
	/**
	 * Enter a parse tree produced by {@link cfgParser#everyStmt}.
	 * @param ctx the parse tree
	 */
	void enterEveryStmt(cfgParser.EveryStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link cfgParser#everyStmt}.
	 * @param ctx the parse tree
	 */
	void exitEveryStmt(cfgParser.EveryStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link cfgParser#elseStmt}.
	 * @param ctx the parse tree
	 */
	void enterElseStmt(cfgParser.ElseStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link cfgParser#elseStmt}.
	 * @param ctx the parse tree
	 */
	void exitElseStmt(cfgParser.ElseStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PbodyTransUp}
	 * labeled alternative in {@link cfgParser#partBody}.
	 * @param ctx the parse tree
	 */
	void enterPbodyTransUp(cfgParser.PbodyTransUpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PbodyTransUp}
	 * labeled alternative in {@link cfgParser#partBody}.
	 * @param ctx the parse tree
	 */
	void exitPbodyTransUp(cfgParser.PbodyTransUpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PbodyPause}
	 * labeled alternative in {@link cfgParser#partBody}.
	 * @param ctx the parse tree
	 */
	void enterPbodyPause(cfgParser.PbodyPauseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PbodyPause}
	 * labeled alternative in {@link cfgParser#partBody}.
	 * @param ctx the parse tree
	 */
	void exitPbodyPause(cfgParser.PbodyPauseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PbodyTransDown}
	 * labeled alternative in {@link cfgParser#partBody}.
	 * @param ctx the parse tree
	 */
	void enterPbodyTransDown(cfgParser.PbodyTransDownContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PbodyTransDown}
	 * labeled alternative in {@link cfgParser#partBody}.
	 * @param ctx the parse tree
	 */
	void exitPbodyTransDown(cfgParser.PbodyTransDownContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PbodyId}
	 * labeled alternative in {@link cfgParser#partBody}.
	 * @param ctx the parse tree
	 */
	void enterPbodyId(cfgParser.PbodyIdContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PbodyId}
	 * labeled alternative in {@link cfgParser#partBody}.
	 * @param ctx the parse tree
	 */
	void exitPbodyId(cfgParser.PbodyIdContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PbodySingleAnd}
	 * labeled alternative in {@link cfgParser#partBody}.
	 * @param ctx the parse tree
	 */
	void enterPbodySingleAnd(cfgParser.PbodySingleAndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PbodySingleAnd}
	 * labeled alternative in {@link cfgParser#partBody}.
	 * @param ctx the parse tree
	 */
	void exitPbodySingleAnd(cfgParser.PbodySingleAndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PbodyTone}
	 * labeled alternative in {@link cfgParser#partBody}.
	 * @param ctx the parse tree
	 */
	void enterPbodyTone(cfgParser.PbodyToneContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PbodyTone}
	 * labeled alternative in {@link cfgParser#partBody}.
	 * @param ctx the parse tree
	 */
	void exitPbodyTone(cfgParser.PbodyToneContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PbodyDoubleAnd}
	 * labeled alternative in {@link cfgParser#partBody}.
	 * @param ctx the parse tree
	 */
	void enterPbodyDoubleAnd(cfgParser.PbodyDoubleAndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PbodyDoubleAnd}
	 * labeled alternative in {@link cfgParser#partBody}.
	 * @param ctx the parse tree
	 */
	void exitPbodyDoubleAnd(cfgParser.PbodyDoubleAndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PbodySingleLRepeat}
	 * labeled alternative in {@link cfgParser#partBody}.
	 * @param ctx the parse tree
	 */
	void enterPbodySingleLRepeat(cfgParser.PbodySingleLRepeatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PbodySingleLRepeat}
	 * labeled alternative in {@link cfgParser#partBody}.
	 * @param ctx the parse tree
	 */
	void exitPbodySingleLRepeat(cfgParser.PbodySingleLRepeatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PbodyParen}
	 * labeled alternative in {@link cfgParser#partBody}.
	 * @param ctx the parse tree
	 */
	void enterPbodyParen(cfgParser.PbodyParenContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PbodyParen}
	 * labeled alternative in {@link cfgParser#partBody}.
	 * @param ctx the parse tree
	 */
	void exitPbodyParen(cfgParser.PbodyParenContext ctx);
}