// Generated from C:/Users/Alexi/Desktop/P4-Msharp/src/main/java\cfg.g4 by ANTLR 4.8
package msharp;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class cfgParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, Nl=12, Bpm=13, Tone=14, Percent=15, Lparen=16, Rparen=17, 
		Comma=18, Digs=19, Assign=20, Instrument=21, OctaveUp=22, OctaveDown=23, 
		TransposeUp=24, TransposeDown=25, SingleAnd=26, DoubleAnd=27, Repeat=28, 
		Pause=29, Id=30, Peroids=31, S=32, MultilineComment=33, SingleLineComment=34;
	public static final int
		RULE_prog = 0, RULE_partDcl = 1, RULE_playDcl = 2, RULE_stmt = 3, RULE_ops = 4, 
		RULE_bpmDcl = 5, RULE_tempoOp = 6, RULE_multStmt = 7, RULE_multilineRepeat = 8, 
		RULE_everyStmt = 9, RULE_elseStmt = 10, RULE_partBody = 11;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "partDcl", "playDcl", "stmt", "ops", "bpmDcl", "tempoOp", "multStmt", 
			"multilineRepeat", "everyStmt", "elseStmt", "partBody"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'part'", "'end part'", "'play'", "'end play'", "'repeat'", "'times'", 
			"'end repeat'", "'every'", "'end every'", "'else'", "'end else'", null, 
			"'BPM'", null, "'%'", "'('", "')'", "','", null, "'='", null, "'/'", 
			"'\\'", "'^'", "'_'", "'&'", "'&&'", "'*'", "'-'", null, "'|'", "' '"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			"Nl", "Bpm", "Tone", "Percent", "Lparen", "Rparen", "Comma", "Digs", 
			"Assign", "Instrument", "OctaveUp", "OctaveDown", "TransposeUp", "TransposeDown", 
			"SingleAnd", "DoubleAnd", "Repeat", "Pause", "Id", "Peroids", "S", "MultilineComment", 
			"SingleLineComment"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "cfg.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public cfgParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgContext extends ParserRuleContext {
		public PlayDclContext playDcl() {
			return getRuleContext(PlayDclContext.class,0);
		}
		public List<PartDclContext> partDcl() {
			return getRuleContexts(PartDclContext.class);
		}
		public PartDclContext partDcl(int i) {
			return getRuleContext(PartDclContext.class,i);
		}
		public List<TerminalNode> Nl() { return getTokens(cfgParser.Nl); }
		public TerminalNode Nl(int i) {
			return getToken(cfgParser.Nl, i);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cfgListener ) ((cfgListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cfgListener ) ((cfgListener)listener).exitProg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cfgVisitor ) return ((cfgVisitor<? extends T>)visitor).visitProg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << Nl) | (1L << Id))) != 0)) {
				{
				setState(26);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__0:
				case Id:
					{
					setState(24);
					partDcl();
					}
					break;
				case Nl:
					{
					setState(25);
					match(Nl);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(30);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(31);
			playDcl();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PartDclContext extends ParserRuleContext {
		public TerminalNode Id() { return getToken(cfgParser.Id, 0); }
		public TerminalNode Assign() { return getToken(cfgParser.Assign, 0); }
		public List<TerminalNode> Nl() { return getTokens(cfgParser.Nl); }
		public TerminalNode Nl(int i) {
			return getToken(cfgParser.Nl, i);
		}
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public List<MultStmtContext> multStmt() {
			return getRuleContexts(MultStmtContext.class);
		}
		public MultStmtContext multStmt(int i) {
			return getRuleContext(MultStmtContext.class,i);
		}
		public PartDclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_partDcl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cfgListener ) ((cfgListener)listener).enterPartDcl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cfgListener ) ((cfgListener)listener).exitPartDcl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cfgVisitor ) return ((cfgVisitor<? extends T>)visitor).visitPartDcl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PartDclContext partDcl() throws RecognitionException {
		PartDclContext _localctx = new PartDclContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_partDcl);
		int _la;
		try {
			int _alt;
			setState(55);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Id:
				enterOuterAlt(_localctx, 1);
				{
				setState(33);
				match(Id);
				setState(34);
				match(Assign);
				setState(36); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(35);
					stmt();
					}
					}
					setState(38); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Bpm) | (1L << Tone) | (1L << Percent) | (1L << Lparen) | (1L << Digs) | (1L << Instrument) | (1L << OctaveUp) | (1L << OctaveDown) | (1L << Pause) | (1L << Id))) != 0) );
				setState(40);
				match(Nl);
				}
				break;
			case T__0:
				enterOuterAlt(_localctx, 2);
				{
				setState(42);
				match(T__0);
				setState(43);
				match(Id);
				setState(44);
				match(Nl);
				setState(46); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(45);
						multStmt();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(48); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(50);
				match(Nl);
				setState(51);
				match(T__1);
				setState(53);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
				case 1:
					{
					setState(52);
					match(Nl);
					}
					break;
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PlayDclContext extends ParserRuleContext {
		public List<TerminalNode> Nl() { return getTokens(cfgParser.Nl); }
		public TerminalNode Nl(int i) {
			return getToken(cfgParser.Nl, i);
		}
		public List<MultStmtContext> multStmt() {
			return getRuleContexts(MultStmtContext.class);
		}
		public MultStmtContext multStmt(int i) {
			return getRuleContext(MultStmtContext.class,i);
		}
		public PlayDclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_playDcl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cfgListener ) ((cfgListener)listener).enterPlayDcl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cfgListener ) ((cfgListener)listener).exitPlayDcl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cfgVisitor ) return ((cfgVisitor<? extends T>)visitor).visitPlayDcl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PlayDclContext playDcl() throws RecognitionException {
		PlayDclContext _localctx = new PlayDclContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_playDcl);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(57);
			match(T__2);
			setState(58);
			match(Nl);
			setState(60); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(59);
					multStmt();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(62); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(64);
			match(Nl);
			setState(65);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StmtContext extends ParserRuleContext {
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
	 
		public StmtContext() { }
		public void copyFrom(StmtContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class StmtPBodyContext extends StmtContext {
		public PartBodyContext partBody() {
			return getRuleContext(PartBodyContext.class,0);
		}
		public StmtPBodyContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cfgListener ) ((cfgListener)listener).enterStmtPBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cfgListener ) ((cfgListener)listener).exitStmtPBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cfgVisitor ) return ((cfgVisitor<? extends T>)visitor).visitStmtPBody(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StmtOpsContext extends StmtContext {
		public OpsContext ops() {
			return getRuleContext(OpsContext.class,0);
		}
		public StmtOpsContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cfgListener ) ((cfgListener)listener).enterStmtOps(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cfgListener ) ((cfgListener)listener).exitStmtOps(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cfgVisitor ) return ((cfgVisitor<? extends T>)visitor).visitStmtOps(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_stmt);
		try {
			setState(69);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Tone:
			case Lparen:
			case Pause:
			case Id:
				_localctx = new StmtPBodyContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(67);
				partBody(0);
				}
				break;
			case Bpm:
			case Percent:
			case Digs:
			case Instrument:
			case OctaveUp:
			case OctaveDown:
				_localctx = new StmtOpsContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(68);
				ops();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OpsContext extends ParserRuleContext {
		public OpsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ops; }
	 
		public OpsContext() { }
		public void copyFrom(OpsContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class OpsOctDownContext extends OpsContext {
		public TerminalNode OctaveDown() { return getToken(cfgParser.OctaveDown, 0); }
		public OpsOctDownContext(OpsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cfgListener ) ((cfgListener)listener).enterOpsOctDown(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cfgListener ) ((cfgListener)listener).exitOpsOctDown(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cfgVisitor ) return ((cfgVisitor<? extends T>)visitor).visitOpsOctDown(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OpsOctUpContext extends OpsContext {
		public TerminalNode OctaveUp() { return getToken(cfgParser.OctaveUp, 0); }
		public OpsOctUpContext(OpsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cfgListener ) ((cfgListener)listener).enterOpsOctUp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cfgListener ) ((cfgListener)listener).exitOpsOctUp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cfgVisitor ) return ((cfgVisitor<? extends T>)visitor).visitOpsOctUp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OpsBpmDclContext extends OpsContext {
		public BpmDclContext bpmDcl() {
			return getRuleContext(BpmDclContext.class,0);
		}
		public OpsBpmDclContext(OpsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cfgListener ) ((cfgListener)listener).enterOpsBpmDcl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cfgListener ) ((cfgListener)listener).exitOpsBpmDcl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cfgVisitor ) return ((cfgVisitor<? extends T>)visitor).visitOpsBpmDcl(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OpsIntruContext extends OpsContext {
		public TerminalNode Instrument() { return getToken(cfgParser.Instrument, 0); }
		public OpsIntruContext(OpsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cfgListener ) ((cfgListener)listener).enterOpsIntru(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cfgListener ) ((cfgListener)listener).exitOpsIntru(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cfgVisitor ) return ((cfgVisitor<? extends T>)visitor).visitOpsIntru(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OpsTempOpContext extends OpsContext {
		public TempoOpContext tempoOp() {
			return getRuleContext(TempoOpContext.class,0);
		}
		public OpsTempOpContext(OpsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cfgListener ) ((cfgListener)listener).enterOpsTempOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cfgListener ) ((cfgListener)listener).exitOpsTempOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cfgVisitor ) return ((cfgVisitor<? extends T>)visitor).visitOpsTempOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OpsContext ops() throws RecognitionException {
		OpsContext _localctx = new OpsContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_ops);
		try {
			setState(76);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Instrument:
				_localctx = new OpsIntruContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(71);
				match(Instrument);
				}
				break;
			case OctaveDown:
				_localctx = new OpsOctDownContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(72);
				match(OctaveDown);
				}
				break;
			case OctaveUp:
				_localctx = new OpsOctUpContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(73);
				match(OctaveUp);
				}
				break;
			case Percent:
			case Digs:
				_localctx = new OpsTempOpContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(74);
				tempoOp();
				}
				break;
			case Bpm:
				_localctx = new OpsBpmDclContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(75);
				bpmDcl();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BpmDclContext extends ParserRuleContext {
		public TerminalNode Bpm() { return getToken(cfgParser.Bpm, 0); }
		public TerminalNode Lparen() { return getToken(cfgParser.Lparen, 0); }
		public TerminalNode Digs() { return getToken(cfgParser.Digs, 0); }
		public TerminalNode Comma() { return getToken(cfgParser.Comma, 0); }
		public TempoOpContext tempoOp() {
			return getRuleContext(TempoOpContext.class,0);
		}
		public TerminalNode Rparen() { return getToken(cfgParser.Rparen, 0); }
		public TerminalNode Nl() { return getToken(cfgParser.Nl, 0); }
		public BpmDclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bpmDcl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cfgListener ) ((cfgListener)listener).enterBpmDcl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cfgListener ) ((cfgListener)listener).exitBpmDcl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cfgVisitor ) return ((cfgVisitor<? extends T>)visitor).visitBpmDcl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BpmDclContext bpmDcl() throws RecognitionException {
		BpmDclContext _localctx = new BpmDclContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_bpmDcl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78);
			match(Bpm);
			setState(79);
			match(Lparen);
			setState(80);
			match(Digs);
			setState(81);
			match(Comma);
			setState(82);
			tempoOp();
			setState(83);
			match(Rparen);
			setState(85);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(84);
				match(Nl);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TempoOpContext extends ParserRuleContext {
		public TerminalNode Percent() { return getToken(cfgParser.Percent, 0); }
		public List<TerminalNode> Digs() { return getTokens(cfgParser.Digs); }
		public TerminalNode Digs(int i) {
			return getToken(cfgParser.Digs, i);
		}
		public TempoOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tempoOp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cfgListener ) ((cfgListener)listener).enterTempoOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cfgListener ) ((cfgListener)listener).exitTempoOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cfgVisitor ) return ((cfgVisitor<? extends T>)visitor).visitTempoOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TempoOpContext tempoOp() throws RecognitionException {
		TempoOpContext _localctx = new TempoOpContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_tempoOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Digs) {
				{
				setState(87);
				match(Digs);
				}
			}

			setState(90);
			match(Percent);
			setState(92);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				setState(91);
				match(Digs);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MultStmtContext extends ParserRuleContext {
		public MultStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multStmt; }
	 
		public MultStmtContext() { }
		public void copyFrom(MultStmtContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class MultStmtStmtContext extends MultStmtContext {
		public StmtContext stmt() {
			return getRuleContext(StmtContext.class,0);
		}
		public MultStmtStmtContext(MultStmtContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cfgListener ) ((cfgListener)listener).enterMultStmtStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cfgListener ) ((cfgListener)listener).exitMultStmtStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cfgVisitor ) return ((cfgVisitor<? extends T>)visitor).visitMultStmtStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MultStmtNLContext extends MultStmtContext {
		public TerminalNode Nl() { return getToken(cfgParser.Nl, 0); }
		public MultStmtNLContext(MultStmtContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cfgListener ) ((cfgListener)listener).enterMultStmtNL(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cfgListener ) ((cfgListener)listener).exitMultStmtNL(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cfgVisitor ) return ((cfgVisitor<? extends T>)visitor).visitMultStmtNL(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MultStmtMultRepeatContext extends MultStmtContext {
		public MultilineRepeatContext multilineRepeat() {
			return getRuleContext(MultilineRepeatContext.class,0);
		}
		public MultStmtMultRepeatContext(MultStmtContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cfgListener ) ((cfgListener)listener).enterMultStmtMultRepeat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cfgListener ) ((cfgListener)listener).exitMultStmtMultRepeat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cfgVisitor ) return ((cfgVisitor<? extends T>)visitor).visitMultStmtMultRepeat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultStmtContext multStmt() throws RecognitionException {
		MultStmtContext _localctx = new MultStmtContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_multStmt);
		try {
			setState(97);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Bpm:
			case Tone:
			case Percent:
			case Lparen:
			case Digs:
			case Instrument:
			case OctaveUp:
			case OctaveDown:
			case Pause:
			case Id:
				_localctx = new MultStmtStmtContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(94);
				stmt();
				}
				break;
			case Nl:
				_localctx = new MultStmtNLContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(95);
				match(Nl);
				}
				break;
			case T__4:
				_localctx = new MultStmtMultRepeatContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(96);
				multilineRepeat();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MultilineRepeatContext extends ParserRuleContext {
		public TerminalNode Digs() { return getToken(cfgParser.Digs, 0); }
		public TerminalNode Nl() { return getToken(cfgParser.Nl, 0); }
		public List<MultStmtContext> multStmt() {
			return getRuleContexts(MultStmtContext.class);
		}
		public MultStmtContext multStmt(int i) {
			return getRuleContext(MultStmtContext.class,i);
		}
		public List<EveryStmtContext> everyStmt() {
			return getRuleContexts(EveryStmtContext.class);
		}
		public EveryStmtContext everyStmt(int i) {
			return getRuleContext(EveryStmtContext.class,i);
		}
		public MultilineRepeatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multilineRepeat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cfgListener ) ((cfgListener)listener).enterMultilineRepeat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cfgListener ) ((cfgListener)listener).exitMultilineRepeat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cfgVisitor ) return ((cfgVisitor<? extends T>)visitor).visitMultilineRepeat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultilineRepeatContext multilineRepeat() throws RecognitionException {
		MultilineRepeatContext _localctx = new MultilineRepeatContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_multilineRepeat);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			match(T__4);
			setState(100);
			match(Digs);
			setState(101);
			match(T__5);
			setState(102);
			match(Nl);
			setState(105); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(105);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__4:
				case Nl:
				case Bpm:
				case Tone:
				case Percent:
				case Lparen:
				case Digs:
				case Instrument:
				case OctaveUp:
				case OctaveDown:
				case Pause:
				case Id:
					{
					setState(103);
					multStmt();
					}
					break;
				case T__7:
					{
					setState(104);
					everyStmt();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(107); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__7) | (1L << Nl) | (1L << Bpm) | (1L << Tone) | (1L << Percent) | (1L << Lparen) | (1L << Digs) | (1L << Instrument) | (1L << OctaveUp) | (1L << OctaveDown) | (1L << Pause) | (1L << Id))) != 0) );
			setState(109);
			match(T__6);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EveryStmtContext extends ParserRuleContext {
		public TerminalNode Digs() { return getToken(cfgParser.Digs, 0); }
		public List<TerminalNode> Nl() { return getTokens(cfgParser.Nl); }
		public TerminalNode Nl(int i) {
			return getToken(cfgParser.Nl, i);
		}
		public List<MultStmtContext> multStmt() {
			return getRuleContexts(MultStmtContext.class);
		}
		public MultStmtContext multStmt(int i) {
			return getRuleContext(MultStmtContext.class,i);
		}
		public List<EveryStmtContext> everyStmt() {
			return getRuleContexts(EveryStmtContext.class);
		}
		public EveryStmtContext everyStmt(int i) {
			return getRuleContext(EveryStmtContext.class,i);
		}
		public ElseStmtContext elseStmt() {
			return getRuleContext(ElseStmtContext.class,0);
		}
		public EveryStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_everyStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cfgListener ) ((cfgListener)listener).enterEveryStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cfgListener ) ((cfgListener)listener).exitEveryStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cfgVisitor ) return ((cfgVisitor<? extends T>)visitor).visitEveryStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EveryStmtContext everyStmt() throws RecognitionException {
		EveryStmtContext _localctx = new EveryStmtContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_everyStmt);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(111);
			match(T__7);
			setState(112);
			match(Digs);
			setState(113);
			match(T__2);
			setState(114);
			match(Nl);
			setState(117); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(117);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__4:
				case Nl:
				case Bpm:
				case Tone:
				case Percent:
				case Lparen:
				case Digs:
				case Instrument:
				case OctaveUp:
				case OctaveDown:
				case Pause:
				case Id:
					{
					setState(115);
					multStmt();
					}
					break;
				case T__7:
					{
					setState(116);
					everyStmt();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(119); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__7) | (1L << Nl) | (1L << Bpm) | (1L << Tone) | (1L << Percent) | (1L << Lparen) | (1L << Digs) | (1L << Instrument) | (1L << OctaveUp) | (1L << OctaveDown) | (1L << Pause) | (1L << Id))) != 0) );
			setState(121);
			match(T__8);
			setState(123); 
			_errHandler.sync(this);
			_alt = 1+1;
			do {
				switch (_alt) {
				case 1+1:
					{
					{
					setState(122);
					match(Nl);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(125); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			} while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(128);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__9) {
				{
				setState(127);
				elseStmt();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ElseStmtContext extends ParserRuleContext {
		public TerminalNode Nl() { return getToken(cfgParser.Nl, 0); }
		public List<MultStmtContext> multStmt() {
			return getRuleContexts(MultStmtContext.class);
		}
		public MultStmtContext multStmt(int i) {
			return getRuleContext(MultStmtContext.class,i);
		}
		public List<EveryStmtContext> everyStmt() {
			return getRuleContexts(EveryStmtContext.class);
		}
		public EveryStmtContext everyStmt(int i) {
			return getRuleContext(EveryStmtContext.class,i);
		}
		public ElseStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elseStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cfgListener ) ((cfgListener)listener).enterElseStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cfgListener ) ((cfgListener)listener).exitElseStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cfgVisitor ) return ((cfgVisitor<? extends T>)visitor).visitElseStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElseStmtContext elseStmt() throws RecognitionException {
		ElseStmtContext _localctx = new ElseStmtContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_elseStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130);
			match(T__9);
			setState(131);
			match(Nl);
			setState(134); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(134);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__4:
				case Nl:
				case Bpm:
				case Tone:
				case Percent:
				case Lparen:
				case Digs:
				case Instrument:
				case OctaveUp:
				case OctaveDown:
				case Pause:
				case Id:
					{
					setState(132);
					multStmt();
					}
					break;
				case T__7:
					{
					setState(133);
					everyStmt();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(136); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__7) | (1L << Nl) | (1L << Bpm) | (1L << Tone) | (1L << Percent) | (1L << Lparen) | (1L << Digs) | (1L << Instrument) | (1L << OctaveUp) | (1L << OctaveDown) | (1L << Pause) | (1L << Id))) != 0) );
			setState(138);
			match(T__10);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PartBodyContext extends ParserRuleContext {
		public PartBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_partBody; }
	 
		public PartBodyContext() { }
		public void copyFrom(PartBodyContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class PbodyTransUpContext extends PartBodyContext {
		public PartBodyContext partBody() {
			return getRuleContext(PartBodyContext.class,0);
		}
		public TerminalNode TransposeUp() { return getToken(cfgParser.TransposeUp, 0); }
		public TerminalNode Digs() { return getToken(cfgParser.Digs, 0); }
		public PbodyTransUpContext(PartBodyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cfgListener ) ((cfgListener)listener).enterPbodyTransUp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cfgListener ) ((cfgListener)listener).exitPbodyTransUp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cfgVisitor ) return ((cfgVisitor<? extends T>)visitor).visitPbodyTransUp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PbodyPauseContext extends PartBodyContext {
		public TerminalNode Pause() { return getToken(cfgParser.Pause, 0); }
		public PbodyPauseContext(PartBodyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cfgListener ) ((cfgListener)listener).enterPbodyPause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cfgListener ) ((cfgListener)listener).exitPbodyPause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cfgVisitor ) return ((cfgVisitor<? extends T>)visitor).visitPbodyPause(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PbodyTransDownContext extends PartBodyContext {
		public PartBodyContext partBody() {
			return getRuleContext(PartBodyContext.class,0);
		}
		public TerminalNode TransposeDown() { return getToken(cfgParser.TransposeDown, 0); }
		public TerminalNode Digs() { return getToken(cfgParser.Digs, 0); }
		public PbodyTransDownContext(PartBodyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cfgListener ) ((cfgListener)listener).enterPbodyTransDown(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cfgListener ) ((cfgListener)listener).exitPbodyTransDown(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cfgVisitor ) return ((cfgVisitor<? extends T>)visitor).visitPbodyTransDown(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PbodyIdContext extends PartBodyContext {
		public TerminalNode Id() { return getToken(cfgParser.Id, 0); }
		public PbodyIdContext(PartBodyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cfgListener ) ((cfgListener)listener).enterPbodyId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cfgListener ) ((cfgListener)listener).exitPbodyId(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cfgVisitor ) return ((cfgVisitor<? extends T>)visitor).visitPbodyId(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PbodySingleAndContext extends PartBodyContext {
		public List<PartBodyContext> partBody() {
			return getRuleContexts(PartBodyContext.class);
		}
		public PartBodyContext partBody(int i) {
			return getRuleContext(PartBodyContext.class,i);
		}
		public TerminalNode SingleAnd() { return getToken(cfgParser.SingleAnd, 0); }
		public PbodySingleAndContext(PartBodyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cfgListener ) ((cfgListener)listener).enterPbodySingleAnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cfgListener ) ((cfgListener)listener).exitPbodySingleAnd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cfgVisitor ) return ((cfgVisitor<? extends T>)visitor).visitPbodySingleAnd(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PbodyToneContext extends PartBodyContext {
		public TerminalNode Tone() { return getToken(cfgParser.Tone, 0); }
		public TerminalNode Digs() { return getToken(cfgParser.Digs, 0); }
		public PbodyToneContext(PartBodyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cfgListener ) ((cfgListener)listener).enterPbodyTone(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cfgListener ) ((cfgListener)listener).exitPbodyTone(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cfgVisitor ) return ((cfgVisitor<? extends T>)visitor).visitPbodyTone(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PbodyDoubleAndContext extends PartBodyContext {
		public List<PartBodyContext> partBody() {
			return getRuleContexts(PartBodyContext.class);
		}
		public PartBodyContext partBody(int i) {
			return getRuleContext(PartBodyContext.class,i);
		}
		public TerminalNode DoubleAnd() { return getToken(cfgParser.DoubleAnd, 0); }
		public PbodyDoubleAndContext(PartBodyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cfgListener ) ((cfgListener)listener).enterPbodyDoubleAnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cfgListener ) ((cfgListener)listener).exitPbodyDoubleAnd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cfgVisitor ) return ((cfgVisitor<? extends T>)visitor).visitPbodyDoubleAnd(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PbodySingleLRepeatContext extends PartBodyContext {
		public PartBodyContext partBody() {
			return getRuleContext(PartBodyContext.class,0);
		}
		public TerminalNode Repeat() { return getToken(cfgParser.Repeat, 0); }
		public TerminalNode Digs() { return getToken(cfgParser.Digs, 0); }
		public PbodySingleLRepeatContext(PartBodyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cfgListener ) ((cfgListener)listener).enterPbodySingleLRepeat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cfgListener ) ((cfgListener)listener).exitPbodySingleLRepeat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cfgVisitor ) return ((cfgVisitor<? extends T>)visitor).visitPbodySingleLRepeat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PbodyParenContext extends PartBodyContext {
		public TerminalNode Lparen() { return getToken(cfgParser.Lparen, 0); }
		public TerminalNode Rparen() { return getToken(cfgParser.Rparen, 0); }
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public PbodyParenContext(PartBodyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cfgListener ) ((cfgListener)listener).enterPbodyParen(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cfgListener ) ((cfgListener)listener).exitPbodyParen(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cfgVisitor ) return ((cfgVisitor<? extends T>)visitor).visitPbodyParen(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PartBodyContext partBody() throws RecognitionException {
		return partBody(0);
	}

	private PartBodyContext partBody(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		PartBodyContext _localctx = new PartBodyContext(_ctx, _parentState);
		PartBodyContext _prevctx = _localctx;
		int _startState = 22;
		enterRecursionRule(_localctx, 22, RULE_partBody, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(155);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Tone:
				{
				_localctx = new PbodyToneContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(141);
				match(Tone);
				setState(143);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
				case 1:
					{
					setState(142);
					match(Digs);
					}
					break;
				}
				}
				break;
			case Id:
				{
				_localctx = new PbodyIdContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(145);
				match(Id);
				}
				break;
			case Pause:
				{
				_localctx = new PbodyPauseContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(146);
				match(Pause);
				}
				break;
			case Lparen:
				{
				_localctx = new PbodyParenContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(147);
				match(Lparen);
				setState(149); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(148);
					stmt();
					}
					}
					setState(151); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Bpm) | (1L << Tone) | (1L << Percent) | (1L << Lparen) | (1L << Digs) | (1L << Instrument) | (1L << OctaveUp) | (1L << OctaveDown) | (1L << Pause) | (1L << Id))) != 0) );
				setState(153);
				match(Rparen);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(178);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(176);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
					case 1:
						{
						_localctx = new PbodySingleAndContext(new PartBodyContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_partBody);
						setState(157);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(158);
						match(SingleAnd);
						setState(159);
						partBody(4);
						}
						break;
					case 2:
						{
						_localctx = new PbodyDoubleAndContext(new PartBodyContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_partBody);
						setState(160);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(161);
						match(DoubleAnd);
						setState(162);
						partBody(3);
						}
						break;
					case 3:
						{
						_localctx = new PbodyTransUpContext(new PartBodyContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_partBody);
						setState(163);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(164);
						match(TransposeUp);
						setState(166);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
						case 1:
							{
							setState(165);
							match(Digs);
							}
							break;
						}
						}
						break;
					case 4:
						{
						_localctx = new PbodyTransDownContext(new PartBodyContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_partBody);
						setState(168);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(169);
						match(TransposeDown);
						setState(171);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
						case 1:
							{
							setState(170);
							match(Digs);
							}
							break;
						}
						}
						break;
					case 5:
						{
						_localctx = new PbodySingleLRepeatContext(new PartBodyContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_partBody);
						setState(173);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(174);
						match(Repeat);
						setState(175);
						match(Digs);
						}
						break;
					}
					} 
				}
				setState(180);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 11:
			return partBody_sempred((PartBodyContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean partBody_sempred(PartBodyContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 3);
		case 1:
			return precpred(_ctx, 2);
		case 2:
			return precpred(_ctx, 5);
		case 3:
			return precpred(_ctx, 4);
		case 4:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3$\u00b8\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\3\2\3\2\7\2\35\n\2\f\2\16\2 \13\2\3\2\3\2\3\3\3\3"+
		"\3\3\6\3\'\n\3\r\3\16\3(\3\3\3\3\3\3\3\3\3\3\3\3\6\3\61\n\3\r\3\16\3\62"+
		"\3\3\3\3\3\3\5\38\n\3\5\3:\n\3\3\4\3\4\3\4\6\4?\n\4\r\4\16\4@\3\4\3\4"+
		"\3\4\3\5\3\5\5\5H\n\5\3\6\3\6\3\6\3\6\3\6\5\6O\n\6\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\5\7X\n\7\3\b\5\b[\n\b\3\b\3\b\5\b_\n\b\3\t\3\t\3\t\5\td\n\t\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\6\nl\n\n\r\n\16\nm\3\n\3\n\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\6\13x\n\13\r\13\16\13y\3\13\3\13\6\13~\n\13\r\13\16\13\177"+
		"\3\13\5\13\u0083\n\13\3\f\3\f\3\f\3\f\6\f\u0089\n\f\r\f\16\f\u008a\3\f"+
		"\3\f\3\r\3\r\3\r\5\r\u0092\n\r\3\r\3\r\3\r\3\r\6\r\u0098\n\r\r\r\16\r"+
		"\u0099\3\r\3\r\5\r\u009e\n\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u00a9"+
		"\n\r\3\r\3\r\3\r\5\r\u00ae\n\r\3\r\3\r\3\r\7\r\u00b3\n\r\f\r\16\r\u00b6"+
		"\13\r\3\r\3\177\3\30\16\2\4\6\b\n\f\16\20\22\24\26\30\2\2\2\u00d0\2\36"+
		"\3\2\2\2\49\3\2\2\2\6;\3\2\2\2\bG\3\2\2\2\nN\3\2\2\2\fP\3\2\2\2\16Z\3"+
		"\2\2\2\20c\3\2\2\2\22e\3\2\2\2\24q\3\2\2\2\26\u0084\3\2\2\2\30\u009d\3"+
		"\2\2\2\32\35\5\4\3\2\33\35\7\16\2\2\34\32\3\2\2\2\34\33\3\2\2\2\35 \3"+
		"\2\2\2\36\34\3\2\2\2\36\37\3\2\2\2\37!\3\2\2\2 \36\3\2\2\2!\"\5\6\4\2"+
		"\"\3\3\2\2\2#$\7 \2\2$&\7\26\2\2%\'\5\b\5\2&%\3\2\2\2\'(\3\2\2\2(&\3\2"+
		"\2\2()\3\2\2\2)*\3\2\2\2*+\7\16\2\2+:\3\2\2\2,-\7\3\2\2-.\7 \2\2.\60\7"+
		"\16\2\2/\61\5\20\t\2\60/\3\2\2\2\61\62\3\2\2\2\62\60\3\2\2\2\62\63\3\2"+
		"\2\2\63\64\3\2\2\2\64\65\7\16\2\2\65\67\7\4\2\2\668\7\16\2\2\67\66\3\2"+
		"\2\2\678\3\2\2\28:\3\2\2\29#\3\2\2\29,\3\2\2\2:\5\3\2\2\2;<\7\5\2\2<>"+
		"\7\16\2\2=?\5\20\t\2>=\3\2\2\2?@\3\2\2\2@>\3\2\2\2@A\3\2\2\2AB\3\2\2\2"+
		"BC\7\16\2\2CD\7\6\2\2D\7\3\2\2\2EH\5\30\r\2FH\5\n\6\2GE\3\2\2\2GF\3\2"+
		"\2\2H\t\3\2\2\2IO\7\27\2\2JO\7\31\2\2KO\7\30\2\2LO\5\16\b\2MO\5\f\7\2"+
		"NI\3\2\2\2NJ\3\2\2\2NK\3\2\2\2NL\3\2\2\2NM\3\2\2\2O\13\3\2\2\2PQ\7\17"+
		"\2\2QR\7\22\2\2RS\7\25\2\2ST\7\24\2\2TU\5\16\b\2UW\7\23\2\2VX\7\16\2\2"+
		"WV\3\2\2\2WX\3\2\2\2X\r\3\2\2\2Y[\7\25\2\2ZY\3\2\2\2Z[\3\2\2\2[\\\3\2"+
		"\2\2\\^\7\21\2\2]_\7\25\2\2^]\3\2\2\2^_\3\2\2\2_\17\3\2\2\2`d\5\b\5\2"+
		"ad\7\16\2\2bd\5\22\n\2c`\3\2\2\2ca\3\2\2\2cb\3\2\2\2d\21\3\2\2\2ef\7\7"+
		"\2\2fg\7\25\2\2gh\7\b\2\2hk\7\16\2\2il\5\20\t\2jl\5\24\13\2ki\3\2\2\2"+
		"kj\3\2\2\2lm\3\2\2\2mk\3\2\2\2mn\3\2\2\2no\3\2\2\2op\7\t\2\2p\23\3\2\2"+
		"\2qr\7\n\2\2rs\7\25\2\2st\7\5\2\2tw\7\16\2\2ux\5\20\t\2vx\5\24\13\2wu"+
		"\3\2\2\2wv\3\2\2\2xy\3\2\2\2yw\3\2\2\2yz\3\2\2\2z{\3\2\2\2{}\7\13\2\2"+
		"|~\7\16\2\2}|\3\2\2\2~\177\3\2\2\2\177\u0080\3\2\2\2\177}\3\2\2\2\u0080"+
		"\u0082\3\2\2\2\u0081\u0083\5\26\f\2\u0082\u0081\3\2\2\2\u0082\u0083\3"+
		"\2\2\2\u0083\25\3\2\2\2\u0084\u0085\7\f\2\2\u0085\u0088\7\16\2\2\u0086"+
		"\u0089\5\20\t\2\u0087\u0089\5\24\13\2\u0088\u0086\3\2\2\2\u0088\u0087"+
		"\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u0088\3\2\2\2\u008a\u008b\3\2\2\2\u008b"+
		"\u008c\3\2\2\2\u008c\u008d\7\r\2\2\u008d\27\3\2\2\2\u008e\u008f\b\r\1"+
		"\2\u008f\u0091\7\20\2\2\u0090\u0092\7\25\2\2\u0091\u0090\3\2\2\2\u0091"+
		"\u0092\3\2\2\2\u0092\u009e\3\2\2\2\u0093\u009e\7 \2\2\u0094\u009e\7\37"+
		"\2\2\u0095\u0097\7\22\2\2\u0096\u0098\5\b\5\2\u0097\u0096\3\2\2\2\u0098"+
		"\u0099\3\2\2\2\u0099\u0097\3\2\2\2\u0099\u009a\3\2\2\2\u009a\u009b\3\2"+
		"\2\2\u009b\u009c\7\23\2\2\u009c\u009e\3\2\2\2\u009d\u008e\3\2\2\2\u009d"+
		"\u0093\3\2\2\2\u009d\u0094\3\2\2\2\u009d\u0095\3\2\2\2\u009e\u00b4\3\2"+
		"\2\2\u009f\u00a0\f\5\2\2\u00a0\u00a1\7\34\2\2\u00a1\u00b3\5\30\r\6\u00a2"+
		"\u00a3\f\4\2\2\u00a3\u00a4\7\35\2\2\u00a4\u00b3\5\30\r\5\u00a5\u00a6\f"+
		"\7\2\2\u00a6\u00a8\7\32\2\2\u00a7\u00a9\7\25\2\2\u00a8\u00a7\3\2\2\2\u00a8"+
		"\u00a9\3\2\2\2\u00a9\u00b3\3\2\2\2\u00aa\u00ab\f\6\2\2\u00ab\u00ad\7\33"+
		"\2\2\u00ac\u00ae\7\25\2\2\u00ad\u00ac\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae"+
		"\u00b3\3\2\2\2\u00af\u00b0\f\3\2\2\u00b0\u00b1\7\36\2\2\u00b1\u00b3\7"+
		"\25\2\2\u00b2\u009f\3\2\2\2\u00b2\u00a2\3\2\2\2\u00b2\u00a5\3\2\2\2\u00b2"+
		"\u00aa\3\2\2\2\u00b2\u00af\3\2\2\2\u00b3\u00b6\3\2\2\2\u00b4\u00b2\3\2"+
		"\2\2\u00b4\u00b5\3\2\2\2\u00b5\31\3\2\2\2\u00b6\u00b4\3\2\2\2\36\34\36"+
		"(\62\679@GNWZ^ckmwy\177\u0082\u0088\u008a\u0091\u0099\u009d\u00a8\u00ad"+
		"\u00b2\u00b4";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}