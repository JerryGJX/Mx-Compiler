// Generated from D:/Work/studying materials/computer related/compiler_design/myCompiler/try/src/grammar\MxLexer.g4 by ANTLR 4.10.1
package grammar;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MxLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.10.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		AddOp=1, SubOp=2, MulOp=3, DivOp=4, ModOp=5, GreaterOp=6, LessOp=7, GreaterEqualOp=8, 
		LessEqualOp=9, NotEqualOp=10, EqualOp=11, LogicAndOp=12, LogicOrOp=13, 
		LogicNotOp=14, ArithShiftLeftOp=15, ArithShiftRightOp=16, BitAndOp=17, 
		BitOrOp=18, BitXorOp=19, BitNotOp=20, AssignOp=21, IncrementOp=22, DecrementOp=23, 
		MemberOp=24, LeftBracket=25, RightBracket=26, LeftParen=27, RightParen=28, 
		SemiColon=29, Comma=30, LeftBrace=31, RightBrace=32, QuoteOp=33, VoidType=34, 
		BoolType=35, IntType=36, StringType=37, NullConstant=38, TrueConstant=39, 
		FalseConstant=40, IfKw=41, ElseKw=42, ForKw=43, WhileKw=44, BreakKw=45, 
		ContinueKw=46, ReturnKw=47, NewKw=48, ClassKw=49, ThisPointer=50, WhiteSpace=51, 
		CommentLine=52, Identifier=53, IntegerConstant=54, EscapeEnter=55, EscapeBackslash=56, 
		EscapeQuote=57, StringContent=58, StringConstant=59, LambdaStartSymbol=60, 
		LambdaArrowSymbol=61;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"AddOp", "SubOp", "MulOp", "DivOp", "ModOp", "GreaterOp", "LessOp", "GreaterEqualOp", 
			"LessEqualOp", "NotEqualOp", "EqualOp", "LogicAndOp", "LogicOrOp", "LogicNotOp", 
			"ArithShiftLeftOp", "ArithShiftRightOp", "BitAndOp", "BitOrOp", "BitXorOp", 
			"BitNotOp", "AssignOp", "IncrementOp", "DecrementOp", "MemberOp", "LeftBracket", 
			"RightBracket", "LeftParen", "RightParen", "SemiColon", "Comma", "LeftBrace", 
			"RightBrace", "QuoteOp", "VoidType", "BoolType", "IntType", "StringType", 
			"NullConstant", "TrueConstant", "FalseConstant", "IfKw", "ElseKw", "ForKw", 
			"WhileKw", "BreakKw", "ContinueKw", "ReturnKw", "NewKw", "ClassKw", "ThisPointer", 
			"WhiteSpace", "CommentLine", "Identifier", "IntegerConstant", "EscapeEnter", 
			"EscapeBackslash", "EscapeQuote", "StringContent", "StringConstant", 
			"LambdaStartSymbol", "LambdaArrowSymbol"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'+'", "'-'", "'*'", "'/'", "'%'", "'>'", "'<'", "'>='", "'<='", 
			"'!='", "'=='", "'&&'", "'||'", "'!'", "'<<'", "'>>'", "'&'", "'|'", 
			"'^'", "'~'", "'='", "'++'", "'--'", "'.'", "'['", "']'", "'('", "')'", 
			"';'", "','", "'{'", "'}'", "'\"'", "'void'", "'bool'", "'int'", "'string'", 
			"'null'", "'true'", "'false'", "'if'", "'else'", "'for'", "'while'", 
			"'break'", "'continue'", "'return'", "'new'", "'class'", "'this'", null, 
			null, null, null, "'\\n'", "'\\\\'", "'\\\"'", null, null, "'[&]'", "'->'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "AddOp", "SubOp", "MulOp", "DivOp", "ModOp", "GreaterOp", "LessOp", 
			"GreaterEqualOp", "LessEqualOp", "NotEqualOp", "EqualOp", "LogicAndOp", 
			"LogicOrOp", "LogicNotOp", "ArithShiftLeftOp", "ArithShiftRightOp", "BitAndOp", 
			"BitOrOp", "BitXorOp", "BitNotOp", "AssignOp", "IncrementOp", "DecrementOp", 
			"MemberOp", "LeftBracket", "RightBracket", "LeftParen", "RightParen", 
			"SemiColon", "Comma", "LeftBrace", "RightBrace", "QuoteOp", "VoidType", 
			"BoolType", "IntType", "StringType", "NullConstant", "TrueConstant", 
			"FalseConstant", "IfKw", "ElseKw", "ForKw", "WhileKw", "BreakKw", "ContinueKw", 
			"ReturnKw", "NewKw", "ClassKw", "ThisPointer", "WhiteSpace", "CommentLine", 
			"Identifier", "IntegerConstant", "EscapeEnter", "EscapeBackslash", "EscapeQuote", 
			"StringContent", "StringConstant", "LambdaStartSymbol", "LambdaArrowSymbol"
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


	public MxLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "MxLexer.g4"; }

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
		"\u0004\u0000=\u0164\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002"+
		"\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002"+
		"\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002"+
		"\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002"+
		"\u001b\u0007\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002"+
		"\u001e\u0007\u001e\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007"+
		"!\u0002\"\u0007\"\u0002#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007"+
		"&\u0002\'\u0007\'\u0002(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007"+
		"+\u0002,\u0007,\u0002-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u0007"+
		"0\u00021\u00071\u00022\u00072\u00023\u00073\u00024\u00074\u00025\u0007"+
		"5\u00026\u00076\u00027\u00077\u00028\u00078\u00029\u00079\u0002:\u0007"+
		":\u0002;\u0007;\u0002<\u0007<\u0001\u0000\u0001\u0000\u0001\u0001\u0001"+
		"\u0001\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0004\u0001"+
		"\u0004\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001"+
		"\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f"+
		"\u0001\f\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011"+
		"\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0016"+
		"\u0001\u0017\u0001\u0017\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019"+
		"\u0001\u001a\u0001\u001a\u0001\u001b\u0001\u001b\u0001\u001c\u0001\u001c"+
		"\u0001\u001d\u0001\u001d\u0001\u001e\u0001\u001e\u0001\u001f\u0001\u001f"+
		"\u0001 \u0001 \u0001!\u0001!\u0001!\u0001!\u0001!\u0001\"\u0001\"\u0001"+
		"\"\u0001\"\u0001\"\u0001#\u0001#\u0001#\u0001#\u0001$\u0001$\u0001$\u0001"+
		"$\u0001$\u0001$\u0001$\u0001%\u0001%\u0001%\u0001%\u0001%\u0001&\u0001"+
		"&\u0001&\u0001&\u0001&\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'"+
		"\u0001(\u0001(\u0001(\u0001)\u0001)\u0001)\u0001)\u0001)\u0001*\u0001"+
		"*\u0001*\u0001*\u0001+\u0001+\u0001+\u0001+\u0001+\u0001+\u0001,\u0001"+
		",\u0001,\u0001,\u0001,\u0001,\u0001-\u0001-\u0001-\u0001-\u0001-\u0001"+
		"-\u0001-\u0001-\u0001-\u0001.\u0001.\u0001.\u0001.\u0001.\u0001.\u0001"+
		".\u0001/\u0001/\u0001/\u0001/\u00010\u00010\u00010\u00010\u00010\u0001"+
		"0\u00011\u00011\u00011\u00011\u00011\u00012\u00042\u0125\b2\u000b2\f2"+
		"\u0126\u00012\u00012\u00013\u00013\u00013\u00013\u00053\u012f\b3\n3\f"+
		"3\u0132\t3\u00013\u00013\u00014\u00014\u00054\u0138\b4\n4\f4\u013b\t4"+
		"\u00015\u00015\u00015\u00055\u0140\b5\n5\f5\u0143\t5\u00035\u0145\b5\u0001"+
		"6\u00016\u00016\u00017\u00017\u00017\u00018\u00018\u00018\u00019\u0001"+
		"9\u0001:\u0001:\u0001:\u0001:\u0001:\u0005:\u0157\b:\n:\f:\u015a\t:\u0001"+
		":\u0001:\u0001;\u0001;\u0001;\u0001;\u0001<\u0001<\u0001<\u0001\u0158"+
		"\u0000=\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b"+
		"\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u001b"+
		"\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012%\u0013\'\u0014)\u0015+\u0016"+
		"-\u0017/\u00181\u00193\u001a5\u001b7\u001c9\u001d;\u001e=\u001f? A!C\""+
		"E#G$I%K&M\'O(Q)S*U+W,Y-[.]/_0a1c2e3g4i5k6m7o8q9s:u;w<y=\u0001\u0000\u0007"+
		"\u0002\u0000\t\n\r\r\u0002\u0000\n\n\r\r\u0002\u0000AZaz\u0004\u00000"+
		"9AZ__az\u0001\u000019\u0001\u000009\u0001\u0000 ~\u016c\u0000\u0001\u0001"+
		"\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001"+
		"\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000"+
		"\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000"+
		"\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000"+
		"\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000"+
		"\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000"+
		"\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000"+
		"\u0000\u0000\u001f\u0001\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000"+
		"\u0000#\u0001\u0000\u0000\u0000\u0000%\u0001\u0000\u0000\u0000\u0000\'"+
		"\u0001\u0000\u0000\u0000\u0000)\u0001\u0000\u0000\u0000\u0000+\u0001\u0000"+
		"\u0000\u0000\u0000-\u0001\u0000\u0000\u0000\u0000/\u0001\u0000\u0000\u0000"+
		"\u00001\u0001\u0000\u0000\u0000\u00003\u0001\u0000\u0000\u0000\u00005"+
		"\u0001\u0000\u0000\u0000\u00007\u0001\u0000\u0000\u0000\u00009\u0001\u0000"+
		"\u0000\u0000\u0000;\u0001\u0000\u0000\u0000\u0000=\u0001\u0000\u0000\u0000"+
		"\u0000?\u0001\u0000\u0000\u0000\u0000A\u0001\u0000\u0000\u0000\u0000C"+
		"\u0001\u0000\u0000\u0000\u0000E\u0001\u0000\u0000\u0000\u0000G\u0001\u0000"+
		"\u0000\u0000\u0000I\u0001\u0000\u0000\u0000\u0000K\u0001\u0000\u0000\u0000"+
		"\u0000M\u0001\u0000\u0000\u0000\u0000O\u0001\u0000\u0000\u0000\u0000Q"+
		"\u0001\u0000\u0000\u0000\u0000S\u0001\u0000\u0000\u0000\u0000U\u0001\u0000"+
		"\u0000\u0000\u0000W\u0001\u0000\u0000\u0000\u0000Y\u0001\u0000\u0000\u0000"+
		"\u0000[\u0001\u0000\u0000\u0000\u0000]\u0001\u0000\u0000\u0000\u0000_"+
		"\u0001\u0000\u0000\u0000\u0000a\u0001\u0000\u0000\u0000\u0000c\u0001\u0000"+
		"\u0000\u0000\u0000e\u0001\u0000\u0000\u0000\u0000g\u0001\u0000\u0000\u0000"+
		"\u0000i\u0001\u0000\u0000\u0000\u0000k\u0001\u0000\u0000\u0000\u0000m"+
		"\u0001\u0000\u0000\u0000\u0000o\u0001\u0000\u0000\u0000\u0000q\u0001\u0000"+
		"\u0000\u0000\u0000s\u0001\u0000\u0000\u0000\u0000u\u0001\u0000\u0000\u0000"+
		"\u0000w\u0001\u0000\u0000\u0000\u0000y\u0001\u0000\u0000\u0000\u0001{"+
		"\u0001\u0000\u0000\u0000\u0003}\u0001\u0000\u0000\u0000\u0005\u007f\u0001"+
		"\u0000\u0000\u0000\u0007\u0081\u0001\u0000\u0000\u0000\t\u0083\u0001\u0000"+
		"\u0000\u0000\u000b\u0085\u0001\u0000\u0000\u0000\r\u0087\u0001\u0000\u0000"+
		"\u0000\u000f\u0089\u0001\u0000\u0000\u0000\u0011\u008c\u0001\u0000\u0000"+
		"\u0000\u0013\u008f\u0001\u0000\u0000\u0000\u0015\u0092\u0001\u0000\u0000"+
		"\u0000\u0017\u0095\u0001\u0000\u0000\u0000\u0019\u0098\u0001\u0000\u0000"+
		"\u0000\u001b\u009b\u0001\u0000\u0000\u0000\u001d\u009d\u0001\u0000\u0000"+
		"\u0000\u001f\u00a0\u0001\u0000\u0000\u0000!\u00a3\u0001\u0000\u0000\u0000"+
		"#\u00a5\u0001\u0000\u0000\u0000%\u00a7\u0001\u0000\u0000\u0000\'\u00a9"+
		"\u0001\u0000\u0000\u0000)\u00ab\u0001\u0000\u0000\u0000+\u00ad\u0001\u0000"+
		"\u0000\u0000-\u00b0\u0001\u0000\u0000\u0000/\u00b3\u0001\u0000\u0000\u0000"+
		"1\u00b5\u0001\u0000\u0000\u00003\u00b7\u0001\u0000\u0000\u00005\u00b9"+
		"\u0001\u0000\u0000\u00007\u00bb\u0001\u0000\u0000\u00009\u00bd\u0001\u0000"+
		"\u0000\u0000;\u00bf\u0001\u0000\u0000\u0000=\u00c1\u0001\u0000\u0000\u0000"+
		"?\u00c3\u0001\u0000\u0000\u0000A\u00c5\u0001\u0000\u0000\u0000C\u00c7"+
		"\u0001\u0000\u0000\u0000E\u00cc\u0001\u0000\u0000\u0000G\u00d1\u0001\u0000"+
		"\u0000\u0000I\u00d5\u0001\u0000\u0000\u0000K\u00dc\u0001\u0000\u0000\u0000"+
		"M\u00e1\u0001\u0000\u0000\u0000O\u00e6\u0001\u0000\u0000\u0000Q\u00ec"+
		"\u0001\u0000\u0000\u0000S\u00ef\u0001\u0000\u0000\u0000U\u00f4\u0001\u0000"+
		"\u0000\u0000W\u00f8\u0001\u0000\u0000\u0000Y\u00fe\u0001\u0000\u0000\u0000"+
		"[\u0104\u0001\u0000\u0000\u0000]\u010d\u0001\u0000\u0000\u0000_\u0114"+
		"\u0001\u0000\u0000\u0000a\u0118\u0001\u0000\u0000\u0000c\u011e\u0001\u0000"+
		"\u0000\u0000e\u0124\u0001\u0000\u0000\u0000g\u012a\u0001\u0000\u0000\u0000"+
		"i\u0135\u0001\u0000\u0000\u0000k\u0144\u0001\u0000\u0000\u0000m\u0146"+
		"\u0001\u0000\u0000\u0000o\u0149\u0001\u0000\u0000\u0000q\u014c\u0001\u0000"+
		"\u0000\u0000s\u014f\u0001\u0000\u0000\u0000u\u0151\u0001\u0000\u0000\u0000"+
		"w\u015d\u0001\u0000\u0000\u0000y\u0161\u0001\u0000\u0000\u0000{|\u0005"+
		"+\u0000\u0000|\u0002\u0001\u0000\u0000\u0000}~\u0005-\u0000\u0000~\u0004"+
		"\u0001\u0000\u0000\u0000\u007f\u0080\u0005*\u0000\u0000\u0080\u0006\u0001"+
		"\u0000\u0000\u0000\u0081\u0082\u0005/\u0000\u0000\u0082\b\u0001\u0000"+
		"\u0000\u0000\u0083\u0084\u0005%\u0000\u0000\u0084\n\u0001\u0000\u0000"+
		"\u0000\u0085\u0086\u0005>\u0000\u0000\u0086\f\u0001\u0000\u0000\u0000"+
		"\u0087\u0088\u0005<\u0000\u0000\u0088\u000e\u0001\u0000\u0000\u0000\u0089"+
		"\u008a\u0005>\u0000\u0000\u008a\u008b\u0005=\u0000\u0000\u008b\u0010\u0001"+
		"\u0000\u0000\u0000\u008c\u008d\u0005<\u0000\u0000\u008d\u008e\u0005=\u0000"+
		"\u0000\u008e\u0012\u0001\u0000\u0000\u0000\u008f\u0090\u0005!\u0000\u0000"+
		"\u0090\u0091\u0005=\u0000\u0000\u0091\u0014\u0001\u0000\u0000\u0000\u0092"+
		"\u0093\u0005=\u0000\u0000\u0093\u0094\u0005=\u0000\u0000\u0094\u0016\u0001"+
		"\u0000\u0000\u0000\u0095\u0096\u0005&\u0000\u0000\u0096\u0097\u0005&\u0000"+
		"\u0000\u0097\u0018\u0001\u0000\u0000\u0000\u0098\u0099\u0005|\u0000\u0000"+
		"\u0099\u009a\u0005|\u0000\u0000\u009a\u001a\u0001\u0000\u0000\u0000\u009b"+
		"\u009c\u0005!\u0000\u0000\u009c\u001c\u0001\u0000\u0000\u0000\u009d\u009e"+
		"\u0005<\u0000\u0000\u009e\u009f\u0005<\u0000\u0000\u009f\u001e\u0001\u0000"+
		"\u0000\u0000\u00a0\u00a1\u0005>\u0000\u0000\u00a1\u00a2\u0005>\u0000\u0000"+
		"\u00a2 \u0001\u0000\u0000\u0000\u00a3\u00a4\u0005&\u0000\u0000\u00a4\""+
		"\u0001\u0000\u0000\u0000\u00a5\u00a6\u0005|\u0000\u0000\u00a6$\u0001\u0000"+
		"\u0000\u0000\u00a7\u00a8\u0005^\u0000\u0000\u00a8&\u0001\u0000\u0000\u0000"+
		"\u00a9\u00aa\u0005~\u0000\u0000\u00aa(\u0001\u0000\u0000\u0000\u00ab\u00ac"+
		"\u0005=\u0000\u0000\u00ac*\u0001\u0000\u0000\u0000\u00ad\u00ae\u0005+"+
		"\u0000\u0000\u00ae\u00af\u0005+\u0000\u0000\u00af,\u0001\u0000\u0000\u0000"+
		"\u00b0\u00b1\u0005-\u0000\u0000\u00b1\u00b2\u0005-\u0000\u0000\u00b2."+
		"\u0001\u0000\u0000\u0000\u00b3\u00b4\u0005.\u0000\u0000\u00b40\u0001\u0000"+
		"\u0000\u0000\u00b5\u00b6\u0005[\u0000\u0000\u00b62\u0001\u0000\u0000\u0000"+
		"\u00b7\u00b8\u0005]\u0000\u0000\u00b84\u0001\u0000\u0000\u0000\u00b9\u00ba"+
		"\u0005(\u0000\u0000\u00ba6\u0001\u0000\u0000\u0000\u00bb\u00bc\u0005)"+
		"\u0000\u0000\u00bc8\u0001\u0000\u0000\u0000\u00bd\u00be\u0005;\u0000\u0000"+
		"\u00be:\u0001\u0000\u0000\u0000\u00bf\u00c0\u0005,\u0000\u0000\u00c0<"+
		"\u0001\u0000\u0000\u0000\u00c1\u00c2\u0005{\u0000\u0000\u00c2>\u0001\u0000"+
		"\u0000\u0000\u00c3\u00c4\u0005}\u0000\u0000\u00c4@\u0001\u0000\u0000\u0000"+
		"\u00c5\u00c6\u0005\"\u0000\u0000\u00c6B\u0001\u0000\u0000\u0000\u00c7"+
		"\u00c8\u0005v\u0000\u0000\u00c8\u00c9\u0005o\u0000\u0000\u00c9\u00ca\u0005"+
		"i\u0000\u0000\u00ca\u00cb\u0005d\u0000\u0000\u00cbD\u0001\u0000\u0000"+
		"\u0000\u00cc\u00cd\u0005b\u0000\u0000\u00cd\u00ce\u0005o\u0000\u0000\u00ce"+
		"\u00cf\u0005o\u0000\u0000\u00cf\u00d0\u0005l\u0000\u0000\u00d0F\u0001"+
		"\u0000\u0000\u0000\u00d1\u00d2\u0005i\u0000\u0000\u00d2\u00d3\u0005n\u0000"+
		"\u0000\u00d3\u00d4\u0005t\u0000\u0000\u00d4H\u0001\u0000\u0000\u0000\u00d5"+
		"\u00d6\u0005s\u0000\u0000\u00d6\u00d7\u0005t\u0000\u0000\u00d7\u00d8\u0005"+
		"r\u0000\u0000\u00d8\u00d9\u0005i\u0000\u0000\u00d9\u00da\u0005n\u0000"+
		"\u0000\u00da\u00db\u0005g\u0000\u0000\u00dbJ\u0001\u0000\u0000\u0000\u00dc"+
		"\u00dd\u0005n\u0000\u0000\u00dd\u00de\u0005u\u0000\u0000\u00de\u00df\u0005"+
		"l\u0000\u0000\u00df\u00e0\u0005l\u0000\u0000\u00e0L\u0001\u0000\u0000"+
		"\u0000\u00e1\u00e2\u0005t\u0000\u0000\u00e2\u00e3\u0005r\u0000\u0000\u00e3"+
		"\u00e4\u0005u\u0000\u0000\u00e4\u00e5\u0005e\u0000\u0000\u00e5N\u0001"+
		"\u0000\u0000\u0000\u00e6\u00e7\u0005f\u0000\u0000\u00e7\u00e8\u0005a\u0000"+
		"\u0000\u00e8\u00e9\u0005l\u0000\u0000\u00e9\u00ea\u0005s\u0000\u0000\u00ea"+
		"\u00eb\u0005e\u0000\u0000\u00ebP\u0001\u0000\u0000\u0000\u00ec\u00ed\u0005"+
		"i\u0000\u0000\u00ed\u00ee\u0005f\u0000\u0000\u00eeR\u0001\u0000\u0000"+
		"\u0000\u00ef\u00f0\u0005e\u0000\u0000\u00f0\u00f1\u0005l\u0000\u0000\u00f1"+
		"\u00f2\u0005s\u0000\u0000\u00f2\u00f3\u0005e\u0000\u0000\u00f3T\u0001"+
		"\u0000\u0000\u0000\u00f4\u00f5\u0005f\u0000\u0000\u00f5\u00f6\u0005o\u0000"+
		"\u0000\u00f6\u00f7\u0005r\u0000\u0000\u00f7V\u0001\u0000\u0000\u0000\u00f8"+
		"\u00f9\u0005w\u0000\u0000\u00f9\u00fa\u0005h\u0000\u0000\u00fa\u00fb\u0005"+
		"i\u0000\u0000\u00fb\u00fc\u0005l\u0000\u0000\u00fc\u00fd\u0005e\u0000"+
		"\u0000\u00fdX\u0001\u0000\u0000\u0000\u00fe\u00ff\u0005b\u0000\u0000\u00ff"+
		"\u0100\u0005r\u0000\u0000\u0100\u0101\u0005e\u0000\u0000\u0101\u0102\u0005"+
		"a\u0000\u0000\u0102\u0103\u0005k\u0000\u0000\u0103Z\u0001\u0000\u0000"+
		"\u0000\u0104\u0105\u0005c\u0000\u0000\u0105\u0106\u0005o\u0000\u0000\u0106"+
		"\u0107\u0005n\u0000\u0000\u0107\u0108\u0005t\u0000\u0000\u0108\u0109\u0005"+
		"i\u0000\u0000\u0109\u010a\u0005n\u0000\u0000\u010a\u010b\u0005u\u0000"+
		"\u0000\u010b\u010c\u0005e\u0000\u0000\u010c\\\u0001\u0000\u0000\u0000"+
		"\u010d\u010e\u0005r\u0000\u0000\u010e\u010f\u0005e\u0000\u0000\u010f\u0110"+
		"\u0005t\u0000\u0000\u0110\u0111\u0005u\u0000\u0000\u0111\u0112\u0005r"+
		"\u0000\u0000\u0112\u0113\u0005n\u0000\u0000\u0113^\u0001\u0000\u0000\u0000"+
		"\u0114\u0115\u0005n\u0000\u0000\u0115\u0116\u0005e\u0000\u0000\u0116\u0117"+
		"\u0005w\u0000\u0000\u0117`\u0001\u0000\u0000\u0000\u0118\u0119\u0005c"+
		"\u0000\u0000\u0119\u011a\u0005l\u0000\u0000\u011a\u011b\u0005a\u0000\u0000"+
		"\u011b\u011c\u0005s\u0000\u0000\u011c\u011d\u0005s\u0000\u0000\u011db"+
		"\u0001\u0000\u0000\u0000\u011e\u011f\u0005t\u0000\u0000\u011f\u0120\u0005"+
		"h\u0000\u0000\u0120\u0121\u0005i\u0000\u0000\u0121\u0122\u0005s\u0000"+
		"\u0000\u0122d\u0001\u0000\u0000\u0000\u0123\u0125\u0007\u0000\u0000\u0000"+
		"\u0124\u0123\u0001\u0000\u0000\u0000\u0125\u0126\u0001\u0000\u0000\u0000"+
		"\u0126\u0124\u0001\u0000\u0000\u0000\u0126\u0127\u0001\u0000\u0000\u0000"+
		"\u0127\u0128\u0001\u0000\u0000\u0000\u0128\u0129\u00062\u0000\u0000\u0129"+
		"f\u0001\u0000\u0000\u0000\u012a\u012b\u0005/\u0000\u0000\u012b\u012c\u0005"+
		"/\u0000\u0000\u012c\u0130\u0001\u0000\u0000\u0000\u012d\u012f\b\u0001"+
		"\u0000\u0000\u012e\u012d\u0001\u0000\u0000\u0000\u012f\u0132\u0001\u0000"+
		"\u0000\u0000\u0130\u012e\u0001\u0000\u0000\u0000\u0130\u0131\u0001\u0000"+
		"\u0000\u0000\u0131\u0133\u0001\u0000\u0000\u0000\u0132\u0130\u0001\u0000"+
		"\u0000\u0000\u0133\u0134\u00063\u0000\u0000\u0134h\u0001\u0000\u0000\u0000"+
		"\u0135\u0139\u0007\u0002\u0000\u0000\u0136\u0138\u0007\u0003\u0000\u0000"+
		"\u0137\u0136\u0001\u0000\u0000\u0000\u0138\u013b\u0001\u0000\u0000\u0000"+
		"\u0139\u0137\u0001\u0000\u0000\u0000\u0139\u013a\u0001\u0000\u0000\u0000"+
		"\u013aj\u0001\u0000\u0000\u0000\u013b\u0139\u0001\u0000\u0000\u0000\u013c"+
		"\u0145\u00050\u0000\u0000\u013d\u0141\u0007\u0004\u0000\u0000\u013e\u0140"+
		"\u0007\u0005\u0000\u0000\u013f\u013e\u0001\u0000\u0000\u0000\u0140\u0143"+
		"\u0001\u0000\u0000\u0000\u0141\u013f\u0001\u0000\u0000\u0000\u0141\u0142"+
		"\u0001\u0000\u0000\u0000\u0142\u0145\u0001\u0000\u0000\u0000\u0143\u0141"+
		"\u0001\u0000\u0000\u0000\u0144\u013c\u0001\u0000\u0000\u0000\u0144\u013d"+
		"\u0001\u0000\u0000\u0000\u0145l\u0001\u0000\u0000\u0000\u0146\u0147\u0005"+
		"\\\u0000\u0000\u0147\u0148\u0005n\u0000\u0000\u0148n\u0001\u0000\u0000"+
		"\u0000\u0149\u014a\u0005\\\u0000\u0000\u014a\u014b\u0005\\\u0000\u0000"+
		"\u014bp\u0001\u0000\u0000\u0000\u014c\u014d\u0005\\\u0000\u0000\u014d"+
		"\u014e\u0005\"\u0000\u0000\u014er\u0001\u0000\u0000\u0000\u014f\u0150"+
		"\u0007\u0006\u0000\u0000\u0150t\u0001\u0000\u0000\u0000\u0151\u0158\u0003"+
		"A \u0000\u0152\u0157\u0003m6\u0000\u0153\u0157\u0003o7\u0000\u0154\u0157"+
		"\u0003q8\u0000\u0155\u0157\u0003s9\u0000\u0156\u0152\u0001\u0000\u0000"+
		"\u0000\u0156\u0153\u0001\u0000\u0000\u0000\u0156\u0154\u0001\u0000\u0000"+
		"\u0000\u0156\u0155\u0001\u0000\u0000\u0000\u0157\u015a\u0001\u0000\u0000"+
		"\u0000\u0158\u0159\u0001\u0000\u0000\u0000\u0158\u0156\u0001\u0000\u0000"+
		"\u0000\u0159\u015b\u0001\u0000\u0000\u0000\u015a\u0158\u0001\u0000\u0000"+
		"\u0000\u015b\u015c\u0003A \u0000\u015cv\u0001\u0000\u0000\u0000\u015d"+
		"\u015e\u0005[\u0000\u0000\u015e\u015f\u0005&\u0000\u0000\u015f\u0160\u0005"+
		"]\u0000\u0000\u0160x\u0001\u0000\u0000\u0000\u0161\u0162\u0005-\u0000"+
		"\u0000\u0162\u0163\u0005>\u0000\u0000\u0163z\u0001\u0000\u0000\u0000\b"+
		"\u0000\u0126\u0130\u0139\u0141\u0144\u0156\u0158\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}