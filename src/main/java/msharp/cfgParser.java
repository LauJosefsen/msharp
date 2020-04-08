// Generated from C:/Users/Alexi/floobits/share/fresser/msharp/src/main/java/msharp\cfg.g4 by ANTLR 4.8
package msharp;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

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
		TransposeUp=24, TransposeDown=25, And=26, Repeat=27, Pause=28, Id=29, 
		Peroids=30, S=31, MultilineComment=32, SingleLineComment=33;
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
			"'\\'", "'^'", "'_'", "'&'", "'*'", "'-'", null, "'|'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			"Nl", "Bpm", "Tone", "Percent", "Lparen", "Rparen", "Comma", "Digs", 
			"Assign", "Instrument", "OctaveUp", "OctaveDown", "TransposeUp", "TransposeDown", 
			"And", "Repeat", "Pause", "Id", "Peroids", "S", "MultilineComment", "SingleLineComment"
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
			setState(54);
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
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Bpm) | (1L << Tone) | (1L << Lparen) | (1L << Digs) | (1L << Instrument) | (1L << OctaveUp) | (1L << OctaveDown) | (1L << Pause) | (1L << Id))) != 0) );
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
				setState(52);
				match(Nl);
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
			setState(56);
			match(T__2);
			setState(57);
			match(Nl);
			setState(59); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(58);
					multStmt();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(61); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(63);
			match(Nl);
			setState(64);
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
			setState(68);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Tone:
			case Lparen:
			case Pause:
			case Id:
				_localctx = new StmtPBodyContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(66);
				partBody(0);
				}
				break;
			case Bpm:
			case Digs:
			case Instrument:
			case OctaveUp:
			case OctaveDown:
				_localctx = new StmtOpsContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(67);
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
			setState(75);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Instrument:
				_localctx = new OpsIntruContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(70);
				match(Instrument);
				}
				break;
			case OctaveDown:
				_localctx = new OpsOctDownContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(71);
				match(OctaveDown);
				}
				break;
			case OctaveUp:
				_localctx = new OpsOctUpContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(72);
				match(OctaveUp);
				}
				break;
			case Digs:
				_localctx = new OpsTempOpContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(73);
				tempoOp();
				}
				break;
			case Bpm:
				_localctx = new OpsBpmDclContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(74);
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
			setState(77);
			match(Bpm);
			setState(78);
			match(Lparen);
			setState(79);
			match(Digs);
			setState(80);
			match(Comma);
			setState(81);
			tempoOp();
			setState(82);
			match(Rparen);
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
		public List<TerminalNode> Digs() { return getTokens(cfgParser.Digs); }
		public TerminalNode Digs(int i) {
			return getToken(cfgParser.Digs, i);
		}
		public TerminalNode Percent() { return getToken(cfgParser.Percent, 0); }
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
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			match(Digs);
			setState(85);
			match(Percent);
			setState(86);
			match(Digs);
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
			setState(91);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Bpm:
			case Tone:
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
				setState(88);
				stmt();
				}
				break;
			case Nl:
				_localctx = new MultStmtNLContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(89);
				match(Nl);
				}
				break;
			case T__4:
				_localctx = new MultStmtMultRepeatContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(90);
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
			setState(93);
			match(T__4);
			setState(94);
			match(Digs);
			setState(95);
			match(T__5);
			setState(96);
			match(Nl);
			setState(99); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(99);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__4:
				case Nl:
				case Bpm:
				case Tone:
				case Lparen:
				case Digs:
				case Instrument:
				case OctaveUp:
				case OctaveDown:
				case Pause:
				case Id:
					{
					setState(97);
					multStmt();
					}
					break;
				case T__7:
					{
					setState(98);
					everyStmt();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(101); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__7) | (1L << Nl) | (1L << Bpm) | (1L << Tone) | (1L << Lparen) | (1L << Digs) | (1L << Instrument) | (1L << OctaveUp) | (1L << OctaveDown) | (1L << Pause) | (1L << Id))) != 0) );
			setState(103);
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
			setState(105);
			match(T__7);
			setState(106);
			match(Digs);
			setState(107);
			match(T__5);
			setState(108);
			match(Nl);
			setState(111); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(111);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__4:
				case Nl:
				case Bpm:
				case Tone:
				case Lparen:
				case Digs:
				case Instrument:
				case OctaveUp:
				case OctaveDown:
				case Pause:
				case Id:
					{
					setState(109);
					multStmt();
					}
					break;
				case T__7:
					{
					setState(110);
					everyStmt();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(113); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__7) | (1L << Nl) | (1L << Bpm) | (1L << Tone) | (1L << Lparen) | (1L << Digs) | (1L << Instrument) | (1L << OctaveUp) | (1L << OctaveDown) | (1L << Pause) | (1L << Id))) != 0) );
			setState(115);
			match(T__8);
			setState(117); 
			_errHandler.sync(this);
			_alt = 1+1;
			do {
				switch (_alt) {
				case 1+1:
					{
					{
					setState(116);
					match(Nl);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(119); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			} while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(122);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__9) {
				{
				setState(121);
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
			setState(124);
			match(T__9);
			setState(125);
			match(Nl);
			setState(128); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(128);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__4:
				case Nl:
				case Bpm:
				case Tone:
				case Lparen:
				case Digs:
				case Instrument:
				case OctaveUp:
				case OctaveDown:
				case Pause:
				case Id:
					{
					setState(126);
					multStmt();
					}
					break;
				case T__7:
					{
					setState(127);
					everyStmt();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(130); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__7) | (1L << Nl) | (1L << Bpm) | (1L << Tone) | (1L << Lparen) | (1L << Digs) | (1L << Instrument) | (1L << OctaveUp) | (1L << OctaveDown) | (1L << Pause) | (1L << Id))) != 0) );
			setState(132);
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
	public static class PbodyAndContext extends PartBodyContext {
		public List<PartBodyContext> partBody() {
			return getRuleContexts(PartBodyContext.class);
		}
		public PartBodyContext partBody(int i) {
			return getRuleContext(PartBodyContext.class,i);
		}
		public TerminalNode And() { return getToken(cfgParser.And, 0); }
		public PbodyAndContext(PartBodyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof cfgListener ) ((cfgListener)listener).enterPbodyAnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof cfgListener ) ((cfgListener)listener).exitPbodyAnd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof cfgVisitor ) return ((cfgVisitor<? extends T>)visitor).visitPbodyAnd(this);
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
			setState(149);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Tone:
				{
				_localctx = new PbodyToneContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(135);
				match(Tone);
				setState(137);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
				case 1:
					{
					setState(136);
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
				setState(139);
				match(Id);
				}
				break;
			case Pause:
				{
				_localctx = new PbodyPauseContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(140);
				match(Pause);
				}
				break;
			case Lparen:
				{
				_localctx = new PbodyParenContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(141);
				match(Lparen);
				setState(143); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(142);
					stmt();
					}
					}
					setState(145); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Bpm) | (1L << Tone) | (1L << Lparen) | (1L << Digs) | (1L << Instrument) | (1L << OctaveUp) | (1L << OctaveDown) | (1L << Pause) | (1L << Id))) != 0) );
				setState(147);
				match(Rparen);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(169);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(167);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
					case 1:
						{
						_localctx = new PbodyAndContext(new PartBodyContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_partBody);
						setState(151);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(152);
						match(And);
						setState(153);
						partBody(3);
						}
						break;
					case 2:
						{
						_localctx = new PbodyTransUpContext(new PartBodyContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_partBody);
						setState(154);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(155);
						match(TransposeUp);
						setState(157);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
						case 1:
							{
							setState(156);
							match(Digs);
							}
							break;
						}
						}
						break;
					case 3:
						{
						_localctx = new PbodyTransDownContext(new PartBodyContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_partBody);
						setState(159);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(160);
						match(TransposeDown);
						setState(162);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
						case 1:
							{
							setState(161);
							match(Digs);
							}
							break;
						}
						}
						break;
					case 4:
						{
						_localctx = new PbodySingleLRepeatContext(new PartBodyContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_partBody);
						setState(164);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(165);
						match(Repeat);
						setState(166);
						match(Digs);
						}
						break;
					}
					} 
				}
				setState(171);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
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
			return precpred(_ctx, 2);
		case 1:
			return precpred(_ctx, 4);
		case 2:
			return precpred(_ctx, 3);
		case 3:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3#\u00af\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\3\2\3\2\7\2\35\n\2\f\2\16\2 \13\2\3\2\3\2\3\3\3\3"+
		"\3\3\6\3\'\n\3\r\3\16\3(\3\3\3\3\3\3\3\3\3\3\3\3\6\3\61\n\3\r\3\16\3\62"+
		"\3\3\3\3\3\3\3\3\5\39\n\3\3\4\3\4\3\4\6\4>\n\4\r\4\16\4?\3\4\3\4\3\4\3"+
		"\5\3\5\5\5G\n\5\3\6\3\6\3\6\3\6\3\6\5\6N\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\5\t^\n\t\3\n\3\n\3\n\3\n\3\n\3\n\6\nf\n"+
		"\n\r\n\16\ng\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\6\13r\n\13\r\13\16"+
		"\13s\3\13\3\13\6\13x\n\13\r\13\16\13y\3\13\5\13}\n\13\3\f\3\f\3\f\3\f"+
		"\6\f\u0083\n\f\r\f\16\f\u0084\3\f\3\f\3\r\3\r\3\r\5\r\u008c\n\r\3\r\3"+
		"\r\3\r\3\r\6\r\u0092\n\r\r\r\16\r\u0093\3\r\3\r\5\r\u0098\n\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\5\r\u00a0\n\r\3\r\3\r\3\r\5\r\u00a5\n\r\3\r\3\r\3\r\7"+
		"\r\u00aa\n\r\f\r\16\r\u00ad\13\r\3\r\3y\3\30\16\2\4\6\b\n\f\16\20\22\24"+
		"\26\30\2\2\2\u00c2\2\36\3\2\2\2\48\3\2\2\2\6:\3\2\2\2\bF\3\2\2\2\nM\3"+
		"\2\2\2\fO\3\2\2\2\16V\3\2\2\2\20]\3\2\2\2\22_\3\2\2\2\24k\3\2\2\2\26~"+
		"\3\2\2\2\30\u0097\3\2\2\2\32\35\5\4\3\2\33\35\7\16\2\2\34\32\3\2\2\2\34"+
		"\33\3\2\2\2\35 \3\2\2\2\36\34\3\2\2\2\36\37\3\2\2\2\37!\3\2\2\2 \36\3"+
		"\2\2\2!\"\5\6\4\2\"\3\3\2\2\2#$\7\37\2\2$&\7\26\2\2%\'\5\b\5\2&%\3\2\2"+
		"\2\'(\3\2\2\2(&\3\2\2\2()\3\2\2\2)*\3\2\2\2*+\7\16\2\2+9\3\2\2\2,-\7\3"+
		"\2\2-.\7\37\2\2.\60\7\16\2\2/\61\5\20\t\2\60/\3\2\2\2\61\62\3\2\2\2\62"+
		"\60\3\2\2\2\62\63\3\2\2\2\63\64\3\2\2\2\64\65\7\16\2\2\65\66\7\4\2\2\66"+
		"\67\7\16\2\2\679\3\2\2\28#\3\2\2\28,\3\2\2\29\5\3\2\2\2:;\7\5\2\2;=\7"+
		"\16\2\2<>\5\20\t\2=<\3\2\2\2>?\3\2\2\2?=\3\2\2\2?@\3\2\2\2@A\3\2\2\2A"+
		"B\7\16\2\2BC\7\6\2\2C\7\3\2\2\2DG\5\30\r\2EG\5\n\6\2FD\3\2\2\2FE\3\2\2"+
		"\2G\t\3\2\2\2HN\7\27\2\2IN\7\31\2\2JN\7\30\2\2KN\5\16\b\2LN\5\f\7\2MH"+
		"\3\2\2\2MI\3\2\2\2MJ\3\2\2\2MK\3\2\2\2ML\3\2\2\2N\13\3\2\2\2OP\7\17\2"+
		"\2PQ\7\22\2\2QR\7\25\2\2RS\7\24\2\2ST\5\16\b\2TU\7\23\2\2U\r\3\2\2\2V"+
		"W\7\25\2\2WX\7\21\2\2XY\7\25\2\2Y\17\3\2\2\2Z^\5\b\5\2[^\7\16\2\2\\^\5"+
		"\22\n\2]Z\3\2\2\2][\3\2\2\2]\\\3\2\2\2^\21\3\2\2\2_`\7\7\2\2`a\7\25\2"+
		"\2ab\7\b\2\2be\7\16\2\2cf\5\20\t\2df\5\24\13\2ec\3\2\2\2ed\3\2\2\2fg\3"+
		"\2\2\2ge\3\2\2\2gh\3\2\2\2hi\3\2\2\2ij\7\t\2\2j\23\3\2\2\2kl\7\n\2\2l"+
		"m\7\25\2\2mn\7\b\2\2nq\7\16\2\2or\5\20\t\2pr\5\24\13\2qo\3\2\2\2qp\3\2"+
		"\2\2rs\3\2\2\2sq\3\2\2\2st\3\2\2\2tu\3\2\2\2uw\7\13\2\2vx\7\16\2\2wv\3"+
		"\2\2\2xy\3\2\2\2yz\3\2\2\2yw\3\2\2\2z|\3\2\2\2{}\5\26\f\2|{\3\2\2\2|}"+
		"\3\2\2\2}\25\3\2\2\2~\177\7\f\2\2\177\u0082\7\16\2\2\u0080\u0083\5\20"+
		"\t\2\u0081\u0083\5\24\13\2\u0082\u0080\3\2\2\2\u0082\u0081\3\2\2\2\u0083"+
		"\u0084\3\2\2\2\u0084\u0082\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0086\3\2"+
		"\2\2\u0086\u0087\7\r\2\2\u0087\27\3\2\2\2\u0088\u0089\b\r\1\2\u0089\u008b"+
		"\7\20\2\2\u008a\u008c\7\25\2\2\u008b\u008a\3\2\2\2\u008b\u008c\3\2\2\2"+
		"\u008c\u0098\3\2\2\2\u008d\u0098\7\37\2\2\u008e\u0098\7\36\2\2\u008f\u0091"+
		"\7\22\2\2\u0090\u0092\5\b\5\2\u0091\u0090\3\2\2\2\u0092\u0093\3\2\2\2"+
		"\u0093\u0091\3\2\2\2\u0093\u0094\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0096"+
		"\7\23\2\2\u0096\u0098\3\2\2\2\u0097\u0088\3\2\2\2\u0097\u008d\3\2\2\2"+
		"\u0097\u008e\3\2\2\2\u0097\u008f\3\2\2\2\u0098\u00ab\3\2\2\2\u0099\u009a"+
		"\f\4\2\2\u009a\u009b\7\34\2\2\u009b\u00aa\5\30\r\5\u009c\u009d\f\6\2\2"+
		"\u009d\u009f\7\32\2\2\u009e\u00a0\7\25\2\2\u009f\u009e\3\2\2\2\u009f\u00a0"+
		"\3\2\2\2\u00a0\u00aa\3\2\2\2\u00a1\u00a2\f\5\2\2\u00a2\u00a4\7\33\2\2"+
		"\u00a3\u00a5\7\25\2\2\u00a4\u00a3\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00aa"+
		"\3\2\2\2\u00a6\u00a7\f\3\2\2\u00a7\u00a8\7\35\2\2\u00a8\u00aa\7\25\2\2"+
		"\u00a9\u0099\3\2\2\2\u00a9\u009c\3\2\2\2\u00a9\u00a1\3\2\2\2\u00a9\u00a6"+
		"\3\2\2\2\u00aa\u00ad\3\2\2\2\u00ab\u00a9\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac"+
		"\31\3\2\2\2\u00ad\u00ab\3\2\2\2\32\34\36(\628?FM]egqsy|\u0082\u0084\u008b"+
		"\u0093\u0097\u009f\u00a4\u00a9\u00ab";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}