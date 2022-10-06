lexer grammar MxLexer;

//@header {package grammar;}


// 1 Operator

PlusOp: '+' ;
MinusOp: '-' ;
MulOp: '*' ;
DivOp: '/' ;
ModOp: '%' ;

GreaterOp: '>' ;
LessOp: '<' ;
GreaterEqualOp: '>=' ;
LessEqualOp: '<=' ;
NotEqualOp: '!=' ;
EqualOp: '==' ;

LogicAndOp: '&&' ;
LogicOrOp: '||' ;
LogicNotOp: '!' ;

LeftShiftOp: '<<' ;//e.g. 1<<2 = 4
RightShiftOp: '>>' ;
BitAndOp: '&' ;
BitOrOp: '|' ;
BitXorOp: '^' ;
BitNotOp: '~' ;

AssignOp: '=' ;

IncrementOp: '++' ;
DecrementOp: '--' ;

MemberOp: '.' ;

LeftBracket: '[';
RightBracket: ']';

LeftParen: '(';
RightParen: ')';

SemiColon: ';';
Comma: ',';
LeftBrace: '{';
RightBrace: '}';

QuoteOp: '"';

//LambdaStartSymbol: '[&]';
LambdaArrowSymbol: '->' ;


// 2.1 Basic Type Keyword
VoidType: 'void' ;
BoolType: 'bool' ;
IntType: 'int' ;
StringType: 'string' ;

NullConstant: 'null' ;
TrueConstant: 'true' ;
FalseConstant: 'false' ;

IfKw: 'if' ;
ElseKw: 'else' ;
ForKw: 'for' ;
WhileKw: 'while' ;
BreakKw: 'break' ;
ContinueKw: 'continue' ;
ReturnKw: 'return' ;

NewKw: 'new' ;
ClassKw: 'class' ;
ThisPointer: 'this' ;



// 4 Comment
CommentLine: '//' ~[\r\n]* -> skip ;
CommentPara: '/*' .*? '*/' -> skip ;

// 5 Identifier
Identifier: [a-zA-Z] [a-zA-Z_0-9]* ;

// 6 Constant

IntegerConstant
    :   '0'
    |   [1-9][0-9]*
    ;

// 6.2 String Constant



StringConstant
    :   QuoteOp (StrEnter | StrBackslash | StrQuote | StringContent)*? QuoteOp//贪婪模式
    ;

StrEnter: '\\n';
StrBackslash: '\\\\';
StrQuote: '\\"';

// 3 Blank
WhiteSpace: [ \t\r\n]+ -> skip;

StringContent: [ -~];

