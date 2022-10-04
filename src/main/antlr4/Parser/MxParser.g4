grammar MxParser;

//parser
stmt : expr;

expr : expr NUL expr    # Mul
     | expr ADD expr    # Add
     | expr DIV expr    # Div
     | expr MIN expr    # Min
     | INT              # Int
     ;

NUL : '*';
ADD : '+';
DIV : '/';
MIN : '-';

INT : Digit+;
Digit : [0-9];

WS : [ \t\r\n]+ -> skip;

SHEBANG : '#' '!' ~('\n'|'\r')* -> channel(HIDDEN);