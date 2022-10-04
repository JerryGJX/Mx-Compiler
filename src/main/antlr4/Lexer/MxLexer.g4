lexer grammar MxLexer;

@header {package grammar;}


// 1 Operator

// 1.1 Standard Op
AddOp: '+' ;
SubOp: '-' ;
MulOp: '*' ;
DivOp: '/' ;
ModOp: '%' ;

// 1.2 Relation Op
GreaterOp: '>' ;
LessOp: '<' ;
GreaterEqualOp: '>=' ;
LessEqualOp: '<=' ;
NotEqualOp: '!=' ;
EqualOp: '==' ;

// 1.3 Logic Op
LogicAndOp: '&&' ;
LogicOrOp: '||' ;
LogicNotOp: '!' ;

// 1.4 Bit Op
ArithShiftLeftOp: '<<' ;
ArithShiftRightOp: '>>' ;
BitAndOp: '&' ;
BitOrOp: '|' ;
BitXorOp: '^' ;
BitNotOp: '~' ;

// 1.5 Assign Op
AssignOp: '=' ;

// 1.6 Increment & Decrement Op
IncrementOp: '++' ;
DecrementOp: '--' ;

// 1.7 Member Op
MemberOp: '.' ;

// 1.8 Index Op
LeftBracket: '[';
RightBracket: ']';

// 1.9 Priority Op
LeftParen: '(';
RightParen: ')';

// 1.10 Seperator Op
SemiColon: ';';
Comma: ',';
LeftBrace: '{';
RightBrace: '}';

// 1.11 String Op
QuoteOp: '"';

// 1.12 Lambda

LambdaStartSymbol: '[&]';
LambdaArrowSymbol: '->' ;

// 2.Keyword

// 2.1 Basic Type Keyword
IntType: 'int' ;
BoolType: 'bool' ;
StringType: 'string' ;
VoidType: 'void' ;

// 2.2 Constant Keyword
NullConstant: 'null' ;
TrueConstant: 'true' ;
FalseConstant: 'false' ;

// 2.3 Control Keyword
IfKw: 'if' ;
ElseKw: 'else' ;
ForKw: 'for' ;
WhileKw: 'while' ;
BreakKw: 'break' ;
ContinueKw: 'continue' ;
ReturnKw: 'return' ;

// 2.4 Class Related Keyword
NewKw: 'new' ;
ClassKw: 'class' ;
ThisPointer: 'this' ;

// 3 Blank
WhitespaceEater: [ \t]+ -> skip ;
NewlineEater: ('\r' '\n'?| '\n') -> skip ;

// 4 Comment
LineCommentEater: '//' ~[\r\n]* -> skip ;
BlockCommentEater: '/*' .*? '*/' -> skip ;

// 5 Identifier
Identifier: [a-zA-Z] [a-zA-Z_0-9]* ;

// 6 Constant

// 6.1 Integer Constant
IntegerConstant
    :   '0'
    |   [1-9][0-9]*
    ;

// 6.2 String Constant

EscapeEnter: '\\n';
EscapeBackslash: '\\\\';
EscapeQuote: '\\"';
StringContent: [ -~];

StringConstant
    :   QuoteOp (EscapeEnter | EscapeBackslash | EscapeQuote | StringContent)*? QuoteOp
    ;

// 6.3 Bool Constant -> Keyword
// 6.4 Null Constant -> Keyword