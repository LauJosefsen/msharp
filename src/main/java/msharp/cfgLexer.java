// Generated from C:/Users/Alexi/Desktop/P4-Msharp/src/main/java\cfg.g4 by ANTLR 4.8
package msharp;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class cfgLexer extends Lexer {
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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "Nl", "Bpm", "Tone", "Percent", "Lparen", "Rparen", 
			"Comma", "Digs", "Assign", "Instrument", "OctaveUp", "OctaveDown", "TransposeUp", 
			"TransposeDown", "SingleAnd", "DoubleAnd", "Repeat", "Pause", "Id", "Peroids", 
			"S", "MultilineComment", "SingleLineComment"
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


	public cfgLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "cfg.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2$\u00f7\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\r\6\r\u009b\n\r\r\r\16\r\u009c\3\16\3\16\3\16\3\16\3\17\3\17\3\20"+
		"\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\6\24\u00ae\n\24\r\24\16\24\u00af"+
		"\3\25\3\25\3\26\6\26\u00b5\n\26\r\26\16\26\u00b6\3\26\3\26\3\27\3\27\3"+
		"\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\34\3\35\3\35\3\36\3"+
		"\36\3\37\3\37\7\37\u00ce\n\37\f\37\16\37\u00d1\13\37\3 \3 \3 \3 \3!\3"+
		"!\3!\3!\3\"\3\"\3\"\3\"\3\"\7\"\u00e0\n\"\f\"\16\"\u00e3\13\"\3\"\3\""+
		"\3\"\3\"\3\"\3\"\3\"\3\"\3#\3#\7#\u00ef\n#\f#\16#\u00f2\13#\3#\3#\3#\3"+
		"#\3\u00e1\2$\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16"+
		"\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34"+
		"\67\359\36;\37= ?!A\"C#E$\3\2\7\4\2\f\f\17\17\3\2ci\3\2\62;\3\2C\\\5\2"+
		"\62;C\\c|\2\u00fc\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13"+
		"\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2"+
		"\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2"+
		"!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3"+
		"\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2"+
		"\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E"+
		"\3\2\2\2\3G\3\2\2\2\5L\3\2\2\2\7U\3\2\2\2\tZ\3\2\2\2\13c\3\2\2\2\rj\3"+
		"\2\2\2\17p\3\2\2\2\21{\3\2\2\2\23\u0081\3\2\2\2\25\u008b\3\2\2\2\27\u0090"+
		"\3\2\2\2\31\u009a\3\2\2\2\33\u009e\3\2\2\2\35\u00a2\3\2\2\2\37\u00a4\3"+
		"\2\2\2!\u00a6\3\2\2\2#\u00a8\3\2\2\2%\u00aa\3\2\2\2\'\u00ad\3\2\2\2)\u00b1"+
		"\3\2\2\2+\u00b4\3\2\2\2-\u00ba\3\2\2\2/\u00bc\3\2\2\2\61\u00be\3\2\2\2"+
		"\63\u00c0\3\2\2\2\65\u00c2\3\2\2\2\67\u00c4\3\2\2\29\u00c7\3\2\2\2;\u00c9"+
		"\3\2\2\2=\u00cb\3\2\2\2?\u00d2\3\2\2\2A\u00d6\3\2\2\2C\u00da\3\2\2\2E"+
		"\u00ec\3\2\2\2GH\7r\2\2HI\7c\2\2IJ\7t\2\2JK\7v\2\2K\4\3\2\2\2LM\7g\2\2"+
		"MN\7p\2\2NO\7f\2\2OP\7\"\2\2PQ\7r\2\2QR\7c\2\2RS\7t\2\2ST\7v\2\2T\6\3"+
		"\2\2\2UV\7r\2\2VW\7n\2\2WX\7c\2\2XY\7{\2\2Y\b\3\2\2\2Z[\7g\2\2[\\\7p\2"+
		"\2\\]\7f\2\2]^\7\"\2\2^_\7r\2\2_`\7n\2\2`a\7c\2\2ab\7{\2\2b\n\3\2\2\2"+
		"cd\7t\2\2de\7g\2\2ef\7r\2\2fg\7g\2\2gh\7c\2\2hi\7v\2\2i\f\3\2\2\2jk\7"+
		"v\2\2kl\7k\2\2lm\7o\2\2mn\7g\2\2no\7u\2\2o\16\3\2\2\2pq\7g\2\2qr\7p\2"+
		"\2rs\7f\2\2st\7\"\2\2tu\7t\2\2uv\7g\2\2vw\7r\2\2wx\7g\2\2xy\7c\2\2yz\7"+
		"v\2\2z\20\3\2\2\2{|\7g\2\2|}\7x\2\2}~\7g\2\2~\177\7t\2\2\177\u0080\7{"+
		"\2\2\u0080\22\3\2\2\2\u0081\u0082\7g\2\2\u0082\u0083\7p\2\2\u0083\u0084"+
		"\7f\2\2\u0084\u0085\7\"\2\2\u0085\u0086\7g\2\2\u0086\u0087\7x\2\2\u0087"+
		"\u0088\7g\2\2\u0088\u0089\7t\2\2\u0089\u008a\7{\2\2\u008a\24\3\2\2\2\u008b"+
		"\u008c\7g\2\2\u008c\u008d\7n\2\2\u008d\u008e\7u\2\2\u008e\u008f\7g\2\2"+
		"\u008f\26\3\2\2\2\u0090\u0091\7g\2\2\u0091\u0092\7p\2\2\u0092\u0093\7"+
		"f\2\2\u0093\u0094\7\"\2\2\u0094\u0095\7g\2\2\u0095\u0096\7n\2\2\u0096"+
		"\u0097\7u\2\2\u0097\u0098\7g\2\2\u0098\30\3\2\2\2\u0099\u009b\t\2\2\2"+
		"\u009a\u0099\3\2\2\2\u009b\u009c\3\2\2\2\u009c\u009a\3\2\2\2\u009c\u009d"+
		"\3\2\2\2\u009d\32\3\2\2\2\u009e\u009f\7D\2\2\u009f\u00a0\7R\2\2\u00a0"+
		"\u00a1\7O\2\2\u00a1\34\3\2\2\2\u00a2\u00a3\t\3\2\2\u00a3\36\3\2\2\2\u00a4"+
		"\u00a5\7\'\2\2\u00a5 \3\2\2\2\u00a6\u00a7\7*\2\2\u00a7\"\3\2\2\2\u00a8"+
		"\u00a9\7+\2\2\u00a9$\3\2\2\2\u00aa\u00ab\7.\2\2\u00ab&\3\2\2\2\u00ac\u00ae"+
		"\t\4\2\2\u00ad\u00ac\3\2\2\2\u00ae\u00af\3\2\2\2\u00af\u00ad\3\2\2\2\u00af"+
		"\u00b0\3\2\2\2\u00b0(\3\2\2\2\u00b1\u00b2\7?\2\2\u00b2*\3\2\2\2\u00b3"+
		"\u00b5\t\5\2\2\u00b4\u00b3\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b6\u00b4\3\2"+
		"\2\2\u00b6\u00b7\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8\u00b9\7<\2\2\u00b9"+
		",\3\2\2\2\u00ba\u00bb\7\61\2\2\u00bb.\3\2\2\2\u00bc\u00bd\7^\2\2\u00bd"+
		"\60\3\2\2\2\u00be\u00bf\7`\2\2\u00bf\62\3\2\2\2\u00c0\u00c1\7a\2\2\u00c1"+
		"\64\3\2\2\2\u00c2\u00c3\7(\2\2\u00c3\66\3\2\2\2\u00c4\u00c5\7(\2\2\u00c5"+
		"\u00c6\7(\2\2\u00c68\3\2\2\2\u00c7\u00c8\7,\2\2\u00c8:\3\2\2\2\u00c9\u00ca"+
		"\7/\2\2\u00ca<\3\2\2\2\u00cb\u00cf\t\5\2\2\u00cc\u00ce\t\6\2\2\u00cd\u00cc"+
		"\3\2\2\2\u00ce\u00d1\3\2\2\2\u00cf\u00cd\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0"+
		">\3\2\2\2\u00d1\u00cf\3\2\2\2\u00d2\u00d3\7~\2\2\u00d3\u00d4\3\2\2\2\u00d4"+
		"\u00d5\b \2\2\u00d5@\3\2\2\2\u00d6\u00d7\7\"\2\2\u00d7\u00d8\3\2\2\2\u00d8"+
		"\u00d9\b!\2\2\u00d9B\3\2\2\2\u00da\u00db\7%\2\2\u00db\u00dc\7,\2\2\u00dc"+
		"\u00dd\7,\2\2\u00dd\u00e1\3\2\2\2\u00de\u00e0\13\2\2\2\u00df\u00de\3\2"+
		"\2\2\u00e0\u00e3\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e1\u00df\3\2\2\2\u00e2"+
		"\u00e4\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e4\u00e5\7,\2\2\u00e5\u00e6\7,\2"+
		"\2\u00e6\u00e7\7%\2\2\u00e7\u00e8\3\2\2\2\u00e8\u00e9\5\31\r\2\u00e9\u00ea"+
		"\3\2\2\2\u00ea\u00eb\b\"\2\2\u00ebD\3\2\2\2\u00ec\u00f0\7%\2\2\u00ed\u00ef"+
		"\n\2\2\2\u00ee\u00ed\3\2\2\2\u00ef\u00f2\3\2\2\2\u00f0\u00ee\3\2\2\2\u00f0"+
		"\u00f1\3\2\2\2\u00f1\u00f3\3\2\2\2\u00f2\u00f0\3\2\2\2\u00f3\u00f4\5\31"+
		"\r\2\u00f4\u00f5\3\2\2\2\u00f5\u00f6\b#\2\2\u00f6F\3\2\2\2\t\2\u009c\u00af"+
		"\u00b6\u00cf\u00e1\u00f0\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}