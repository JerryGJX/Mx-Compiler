grammar Demo;

//parser
stat : expr;

expr : expr MUL expr    #Mul
     | expr ADD expr    #Add
     | expr DIV expr    #Div
     | expr MIN expr    #Min
     | INT              #Int
     ;


MUL : '*';
ADD : '+';
DIV : '/';
MIN : '-';

INT : Digit+;
Digit : [0-9];
