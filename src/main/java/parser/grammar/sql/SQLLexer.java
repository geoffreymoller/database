package parser.grammar.sql;// Generated from SQL.g4 by ANTLR 4.7.2

// Generated from SQL.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SQLLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, NUMERIC_LITERAL=19, IDENTIFIER=20, K_CREATE=21, K_ON=22, K_SELECT=23, 
		K_DISTINCT=24, K_FROM=25, K_WHERE=26, K_GROUP=27, K_BY=28, K_HAVING=29, 
		K_INDEX=30, K_ORDER=31, K_LIMIT=32, K_OFFSET=33, K_AS=34, K_ASC=35, K_DESC=36, 
		K_AND=37, K_OR=38, K_COLUMN=39, K_IN=40, K_TABLE=41, K_UNIQUE=42, K_WITH=43, 
		SPACES=44;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
			"T__17", "NUMERIC_LITERAL", "IDENTIFIER", "K_CREATE", "K_ON", "K_SELECT", 
			"K_DISTINCT", "K_FROM", "K_WHERE", "K_GROUP", "K_BY", "K_HAVING", "K_INDEX", 
			"K_ORDER", "K_LIMIT", "K_OFFSET", "K_AS", "K_ASC", "K_DESC", "K_AND", 
			"K_OR", "K_COLUMN", "K_IN", "K_TABLE", "K_UNIQUE", "K_WITH", "A", "B", 
			"C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", 
			"Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "SPACES"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'('", "')'", "','", "'*'", "'.'", "'-'", "'/'", "'%'", 
			"'+'", "'<'", "'<='", "'>'", "'>='", "'='", "'=='", "'!='", "'<>'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, "NUMERIC_LITERAL", "IDENTIFIER", 
			"K_CREATE", "K_ON", "K_SELECT", "K_DISTINCT", "K_FROM", "K_WHERE", "K_GROUP", 
			"K_BY", "K_HAVING", "K_INDEX", "K_ORDER", "K_LIMIT", "K_OFFSET", "K_AS", 
			"K_ASC", "K_DESC", "K_AND", "K_OR", "K_COLUMN", "K_IN", "K_TABLE", "K_UNIQUE", 
			"K_WITH", "SPACES"
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


	public SQLLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "SQL.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2.\u0186\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\3\2\3\2\3"+
		"\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3"+
		"\13\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\21\3\21\3"+
		"\21\3\22\3\22\3\22\3\23\3\23\3\23\3\24\6\24\u00ba\n\24\r\24\16\24\u00bb"+
		"\3\24\3\24\7\24\u00c0\n\24\f\24\16\24\u00c3\13\24\5\24\u00c5\n\24\3\25"+
		"\3\25\3\25\3\25\7\25\u00cb\n\25\f\25\16\25\u00ce\13\25\3\25\3\25\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32"+
		"\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34"+
		"\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37"+
		"\3\37\3\37\3 \3 \3 \3 \3 \3 \3!\3!\3!\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3\""+
		"\3\"\3#\3#\3#\3$\3$\3$\3$\3%\3%\3%\3%\3%\3&\3&\3&\3&\3\'\3\'\3\'\3(\3"+
		"(\3(\3(\3(\3(\3(\3)\3)\3)\3*\3*\3*\3*\3*\3*\3+\3+\3+\3+\3+\3+\3+\3,\3"+
		",\3,\3,\3,\3-\3-\3.\3.\3/\3/\3\60\3\60\3\61\3\61\3\62\3\62\3\63\3\63\3"+
		"\64\3\64\3\65\3\65\3\66\3\66\3\67\3\67\38\38\39\39\3:\3:\3;\3;\3<\3<\3"+
		"=\3=\3>\3>\3?\3?\3@\3@\3A\3A\3B\3B\3C\3C\3D\3D\3E\3E\3F\3F\3G\3G\3G\3"+
		"G\2\2H\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17"+
		"\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\35"+
		"9\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y\2[\2]\2_\2a\2c\2e\2g\2i\2k\2m"+
		"\2o\2q\2s\2u\2w\2y\2{\2}\2\177\2\u0081\2\u0083\2\u0085\2\u0087\2\u0089"+
		"\2\u008b\2\u008d.\3\2\37\3\2\62;\3\2$$\4\2CCcc\4\2DDdd\4\2EEee\4\2FFf"+
		"f\4\2GGgg\4\2HHhh\4\2IIii\4\2JJjj\4\2KKkk\4\2LLll\4\2MMmm\4\2NNnn\4\2"+
		"OOoo\4\2PPpp\4\2QQqq\4\2RRrr\4\2SSss\4\2TTtt\4\2UUuu\4\2VVvv\4\2WWww\4"+
		"\2XXxx\4\2YYyy\4\2ZZzz\4\2[[{{\4\2\\\\||\5\2\13\r\17\17\"\"\2\u0170\2"+
		"\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2"+
		"\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2"+
		"\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2"+
		"\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2"+
		"\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2"+
		"\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2"+
		"\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U"+
		"\3\2\2\2\2W\3\2\2\2\2\u008d\3\2\2\2\3\u008f\3\2\2\2\5\u0091\3\2\2\2\7"+
		"\u0093\3\2\2\2\t\u0095\3\2\2\2\13\u0097\3\2\2\2\r\u0099\3\2\2\2\17\u009b"+
		"\3\2\2\2\21\u009d\3\2\2\2\23\u009f\3\2\2\2\25\u00a1\3\2\2\2\27\u00a3\3"+
		"\2\2\2\31\u00a5\3\2\2\2\33\u00a8\3\2\2\2\35\u00aa\3\2\2\2\37\u00ad\3\2"+
		"\2\2!\u00af\3\2\2\2#\u00b2\3\2\2\2%\u00b5\3\2\2\2\'\u00b9\3\2\2\2)\u00c6"+
		"\3\2\2\2+\u00d1\3\2\2\2-\u00d8\3\2\2\2/\u00db\3\2\2\2\61\u00e2\3\2\2\2"+
		"\63\u00eb\3\2\2\2\65\u00f0\3\2\2\2\67\u00f6\3\2\2\29\u00fc\3\2\2\2;\u00ff"+
		"\3\2\2\2=\u0106\3\2\2\2?\u010c\3\2\2\2A\u0112\3\2\2\2C\u0118\3\2\2\2E"+
		"\u011f\3\2\2\2G\u0122\3\2\2\2I\u0126\3\2\2\2K\u012b\3\2\2\2M\u012f\3\2"+
		"\2\2O\u0132\3\2\2\2Q\u0139\3\2\2\2S\u013c\3\2\2\2U\u0142\3\2\2\2W\u0149"+
		"\3\2\2\2Y\u014e\3\2\2\2[\u0150\3\2\2\2]\u0152\3\2\2\2_\u0154\3\2\2\2a"+
		"\u0156\3\2\2\2c\u0158\3\2\2\2e\u015a\3\2\2\2g\u015c\3\2\2\2i\u015e\3\2"+
		"\2\2k\u0160\3\2\2\2m\u0162\3\2\2\2o\u0164\3\2\2\2q\u0166\3\2\2\2s\u0168"+
		"\3\2\2\2u\u016a\3\2\2\2w\u016c\3\2\2\2y\u016e\3\2\2\2{\u0170\3\2\2\2}"+
		"\u0172\3\2\2\2\177\u0174\3\2\2\2\u0081\u0176\3\2\2\2\u0083\u0178\3\2\2"+
		"\2\u0085\u017a\3\2\2\2\u0087\u017c\3\2\2\2\u0089\u017e\3\2\2\2\u008b\u0180"+
		"\3\2\2\2\u008d\u0182\3\2\2\2\u008f\u0090\7=\2\2\u0090\4\3\2\2\2\u0091"+
		"\u0092\7*\2\2\u0092\6\3\2\2\2\u0093\u0094\7+\2\2\u0094\b\3\2\2\2\u0095"+
		"\u0096\7.\2\2\u0096\n\3\2\2\2\u0097\u0098\7,\2\2\u0098\f\3\2\2\2\u0099"+
		"\u009a\7\60\2\2\u009a\16\3\2\2\2\u009b\u009c\7/\2\2\u009c\20\3\2\2\2\u009d"+
		"\u009e\7\61\2\2\u009e\22\3\2\2\2\u009f\u00a0\7\'\2\2\u00a0\24\3\2\2\2"+
		"\u00a1\u00a2\7-\2\2\u00a2\26\3\2\2\2\u00a3\u00a4\7>\2\2\u00a4\30\3\2\2"+
		"\2\u00a5\u00a6\7>\2\2\u00a6\u00a7\7?\2\2\u00a7\32\3\2\2\2\u00a8\u00a9"+
		"\7@\2\2\u00a9\34\3\2\2\2\u00aa\u00ab\7@\2\2\u00ab\u00ac\7?\2\2\u00ac\36"+
		"\3\2\2\2\u00ad\u00ae\7?\2\2\u00ae \3\2\2\2\u00af\u00b0\7?\2\2\u00b0\u00b1"+
		"\7?\2\2\u00b1\"\3\2\2\2\u00b2\u00b3\7#\2\2\u00b3\u00b4\7?\2\2\u00b4$\3"+
		"\2\2\2\u00b5\u00b6\7>\2\2\u00b6\u00b7\7@\2\2\u00b7&\3\2\2\2\u00b8\u00ba"+
		"\t\2\2\2\u00b9\u00b8\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb\u00b9\3\2\2\2\u00bb"+
		"\u00bc\3\2\2\2\u00bc\u00c4\3\2\2\2\u00bd\u00c1\7\60\2\2\u00be\u00c0\t"+
		"\2\2\2\u00bf\u00be\3\2\2\2\u00c0\u00c3\3\2\2\2\u00c1\u00bf\3\2\2\2\u00c1"+
		"\u00c2\3\2\2\2\u00c2\u00c5\3\2\2\2\u00c3\u00c1\3\2\2\2\u00c4\u00bd\3\2"+
		"\2\2\u00c4\u00c5\3\2\2\2\u00c5(\3\2\2\2\u00c6\u00cc\7$\2\2\u00c7\u00cb"+
		"\n\3\2\2\u00c8\u00c9\7$\2\2\u00c9\u00cb\7$\2\2\u00ca\u00c7\3\2\2\2\u00ca"+
		"\u00c8\3\2\2\2\u00cb\u00ce\3\2\2\2\u00cc\u00ca\3\2\2\2\u00cc\u00cd\3\2"+
		"\2\2\u00cd\u00cf\3\2\2\2\u00ce\u00cc\3\2\2\2\u00cf\u00d0\7$\2\2\u00d0"+
		"*\3\2\2\2\u00d1\u00d2\5]/\2\u00d2\u00d3\5{>\2\u00d3\u00d4\5a\61\2\u00d4"+
		"\u00d5\5Y-\2\u00d5\u00d6\5\177@\2\u00d6\u00d7\5a\61\2\u00d7,\3\2\2\2\u00d8"+
		"\u00d9\5u;\2\u00d9\u00da\5s:\2\u00da.\3\2\2\2\u00db\u00dc\5}?\2\u00dc"+
		"\u00dd\5a\61\2\u00dd\u00de\5o8\2\u00de\u00df\5a\61\2\u00df\u00e0\5]/\2"+
		"\u00e0\u00e1\5\177@\2\u00e1\60\3\2\2\2\u00e2\u00e3\5_\60\2\u00e3\u00e4"+
		"\5i\65\2\u00e4\u00e5\5}?\2\u00e5\u00e6\5\177@\2\u00e6\u00e7\5i\65\2\u00e7"+
		"\u00e8\5s:\2\u00e8\u00e9\5]/\2\u00e9\u00ea\5\177@\2\u00ea\62\3\2\2\2\u00eb"+
		"\u00ec\5c\62\2\u00ec\u00ed\5{>\2\u00ed\u00ee\5u;\2\u00ee\u00ef\5q9\2\u00ef"+
		"\64\3\2\2\2\u00f0\u00f1\5\u0085C\2\u00f1\u00f2\5g\64\2\u00f2\u00f3\5a"+
		"\61\2\u00f3\u00f4\5{>\2\u00f4\u00f5\5a\61\2\u00f5\66\3\2\2\2\u00f6\u00f7"+
		"\5e\63\2\u00f7\u00f8\5{>\2\u00f8\u00f9\5u;\2\u00f9\u00fa\5\u0081A\2\u00fa"+
		"\u00fb\5w<\2\u00fb8\3\2\2\2\u00fc\u00fd\5[.\2\u00fd\u00fe\5\u0089E\2\u00fe"+
		":\3\2\2\2\u00ff\u0100\5g\64\2\u0100\u0101\5Y-\2\u0101\u0102\5\u0083B\2"+
		"\u0102\u0103\5i\65\2\u0103\u0104\5s:\2\u0104\u0105\5e\63\2\u0105<\3\2"+
		"\2\2\u0106\u0107\5i\65\2\u0107\u0108\5s:\2\u0108\u0109\5_\60\2\u0109\u010a"+
		"\5a\61\2\u010a\u010b\5\u0087D\2\u010b>\3\2\2\2\u010c\u010d\5u;\2\u010d"+
		"\u010e\5{>\2\u010e\u010f\5_\60\2\u010f\u0110\5a\61\2\u0110\u0111\5{>\2"+
		"\u0111@\3\2\2\2\u0112\u0113\5o8\2\u0113\u0114\5i\65\2\u0114\u0115\5q9"+
		"\2\u0115\u0116\5i\65\2\u0116\u0117\5\177@\2\u0117B\3\2\2\2\u0118\u0119"+
		"\5u;\2\u0119\u011a\5c\62\2\u011a\u011b\5c\62\2\u011b\u011c\5}?\2\u011c"+
		"\u011d\5a\61\2\u011d\u011e\5\177@\2\u011eD\3\2\2\2\u011f\u0120\5Y-\2\u0120"+
		"\u0121\5}?\2\u0121F\3\2\2\2\u0122\u0123\5Y-\2\u0123\u0124\5}?\2\u0124"+
		"\u0125\5]/\2\u0125H\3\2\2\2\u0126\u0127\5_\60\2\u0127\u0128\5a\61\2\u0128"+
		"\u0129\5}?\2\u0129\u012a\5]/\2\u012aJ\3\2\2\2\u012b\u012c\5Y-\2\u012c"+
		"\u012d\5s:\2\u012d\u012e\5_\60\2\u012eL\3\2\2\2\u012f\u0130\5u;\2\u0130"+
		"\u0131\5{>\2\u0131N\3\2\2\2\u0132\u0133\5]/\2\u0133\u0134\5u;\2\u0134"+
		"\u0135\5o8\2\u0135\u0136\5\u0081A\2\u0136\u0137\5q9\2\u0137\u0138\5s:"+
		"\2\u0138P\3\2\2\2\u0139\u013a\5i\65\2\u013a\u013b\5s:\2\u013bR\3\2\2\2"+
		"\u013c\u013d\5\177@\2\u013d\u013e\5Y-\2\u013e\u013f\5[.\2\u013f\u0140"+
		"\5o8\2\u0140\u0141\5a\61\2\u0141T\3\2\2\2\u0142\u0143\5\u0081A\2\u0143"+
		"\u0144\5s:\2\u0144\u0145\5i\65\2\u0145\u0146\5y=\2\u0146\u0147\5\u0081"+
		"A\2\u0147\u0148\5a\61\2\u0148V\3\2\2\2\u0149\u014a\5\u0085C\2\u014a\u014b"+
		"\5i\65\2\u014b\u014c\5\177@\2\u014c\u014d\5g\64\2\u014dX\3\2\2\2\u014e"+
		"\u014f\t\4\2\2\u014fZ\3\2\2\2\u0150\u0151\t\5\2\2\u0151\\\3\2\2\2\u0152"+
		"\u0153\t\6\2\2\u0153^\3\2\2\2\u0154\u0155\t\7\2\2\u0155`\3\2\2\2\u0156"+
		"\u0157\t\b\2\2\u0157b\3\2\2\2\u0158\u0159\t\t\2\2\u0159d\3\2\2\2\u015a"+
		"\u015b\t\n\2\2\u015bf\3\2\2\2\u015c\u015d\t\13\2\2\u015dh\3\2\2\2\u015e"+
		"\u015f\t\f\2\2\u015fj\3\2\2\2\u0160\u0161\t\r\2\2\u0161l\3\2\2\2\u0162"+
		"\u0163\t\16\2\2\u0163n\3\2\2\2\u0164\u0165\t\17\2\2\u0165p\3\2\2\2\u0166"+
		"\u0167\t\20\2\2\u0167r\3\2\2\2\u0168\u0169\t\21\2\2\u0169t\3\2\2\2\u016a"+
		"\u016b\t\22\2\2\u016bv\3\2\2\2\u016c\u016d\t\23\2\2\u016dx\3\2\2\2\u016e"+
		"\u016f\t\24\2\2\u016fz\3\2\2\2\u0170\u0171\t\25\2\2\u0171|\3\2\2\2\u0172"+
		"\u0173\t\26\2\2\u0173~\3\2\2\2\u0174\u0175\t\27\2\2\u0175\u0080\3\2\2"+
		"\2\u0176\u0177\t\30\2\2\u0177\u0082\3\2\2\2\u0178\u0179\t\31\2\2\u0179"+
		"\u0084\3\2\2\2\u017a\u017b\t\32\2\2\u017b\u0086\3\2\2\2\u017c\u017d\t"+
		"\33\2\2\u017d\u0088\3\2\2\2\u017e\u017f\t\34\2\2\u017f\u008a\3\2\2\2\u0180"+
		"\u0181\t\35\2\2\u0181\u008c\3\2\2\2\u0182\u0183\t\36\2\2\u0183\u0184\3"+
		"\2\2\2\u0184\u0185\bG\2\2\u0185\u008e\3\2\2\2\b\2\u00bb\u00c1\u00c4\u00ca"+
		"\u00cc\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}