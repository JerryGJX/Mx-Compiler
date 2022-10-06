parser grammar MxParser;

options {
    tokenVocab = MxLexer;
}

//@header {package grammar;}

//mxCode
//    :   (varDef|classDef|funcDef)* EOF
//    ;


//7.Variable
basicType : BoolType|IntType|VoidType|StringType;

varType
    :   basicType
    |   Identifier
    |   (basicType | Identifier)(LeftBracket RightBracket)+
    ;

//varDefUnit : Identifier (AssignOp expression)?;//e.g. A = 10 or A

//varDef : varType varDefUnit (Comma varDefUnit)*;



//8.ClassDef
//classConstructorDef : Identifier LeftParen RightParen