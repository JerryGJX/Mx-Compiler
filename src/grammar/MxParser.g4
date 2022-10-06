parser grammar MxParser;

options {
    tokenVocab = MxLexer;
}

//@header {package grammar;}

mxProgram : (classDef|funcDef|varDefStmt)* EOF;

/*
the sequence of classDef, funcDef, varDefStmt is set on account of
the fact that class contains function and variable definition, and
function definition contains variable definition.

But I'm still not sure whether the sequence is correct.(2022/10/6)
*/

/*
class <classIdentifier> {
   // 类成员变量  -> vardefstmt
   // 类构造函数  -> constructorDef
   // 类方法     -> 函数定义
};
*/
classDef : ClassKw Identifier LeftBrace
           (varDefStmt|constructorDef|funcDef)*
           RightBrace SemiColon
         ;
//构造函数
constructorDef : Identifier LeftParen RightParen suiteStmt;
//返回值类型
returnType : varType | VoidType;
//函数定义
funcDef : returnType Identifier LeftParen funcDefArgList? RightParen suiteStmt;
//函数参数列表
funcDefArgList : varType Identifier (Comma varType Identifier)*;
funcCallArgList : expression (Comma expression)*;

//7.Variable
//变量基本类型
builtinType : BoolType|IntType|StringType;//variable can't be void type

//变量类型
basicVarType
    :   builtinType
    |   Identifier
    ;

varType : basicVarType (LeftBracket RightBracket)*;



//变量赋值&声明
varDefUnit : Identifier (AssignOp expression)?;//e.g. A = 10 or A
//变量定义
varDefBody : varType varDefUnit (Comma varDefUnit)?;//e.g. int A = 10 (, B = 20)


//statement

suiteStmt : LeftBrace statement* RightBrace;
//变量定义语句
varDefStmt : varDefBody SemiColon;

statement
    :   suiteStmt
    |   varDefStmt
    |   ifStmt
    |   whileStmt
    |   forStmt
    |   breakStmt
    |   continueStmt
    |   returnStmt
    |   expressionStmt
    |   emptyStmt
    ;

//if语句
ifStmt : IfKw LeftParen expression RightParen statement (ElseKw statement)?;
//while语句
whileStmt : WhileKw LeftParen expression RightParen statement;
//for语句
forInit : (varDefBody | expression);
forStmt : ForKw LeftParen forInit? SemiColon expression? SemiColon expression? RightParen statement;
//break语句
breakStmt : BreakKw SemiColon;
//continue语句
continueStmt : ContinueKw SemiColon;
//return语句
returnStmt : ReturnKw expression? SemiColon;
//表达式语句
expressionStmt : expression SemiColon;
//空表达式
emptyStmt : SemiColon;


atomExpression
        :   Identifier
        |   IntegerConstant
        |   StringConstant
        |   FalseConstant
        |   TrueConstant
        |   NullConstant
        |   ThisPointer
        ;

//operator precedence
suffixOps : (IncrementOp|DecrementOp);//e.g. a++ a--
prefixOps : (IncrementOp|DecrementOp);//e.g. ++a --a
unaryOps : (PlusOp|MinusOp|LogicNotOp|BitNotOp);//e.g. +a -a !a ~a
mulLevelOps : (MulOp|DivOp|ModOp);//e.g. a*b a/b a%b
addLevelOps : (PlusOp|MinusOp);//e.g. a+b a-b
shiftOps : (LeftShiftOp|RightShiftOp);//e.g. a<<b a>>b
relationalOps : (LessOp|GreaterOp|LessEqualOp|GreaterEqualOp);//e.g. a<b a>b a<=b a>=b
equalityOps : (EqualOp|NotEqualOp);//e.g. a==b a!=b

//expression
expression
        :   atomExpression
        |   LeftParen expression RightParen
        |   NewKw basicVarType (LeftBracket expression? RightBracket)*//e.g. new int[10]
        //left to right
        |   expression suffixOps
        |   Identifier LeftParen funcCallArgList? RightParen//e.g. a(1,2,3)
        |   expression LeftBracket expression RightBracket//e.g. a[1]
        |   expression MemberOp Identifier//e.g. a.b
        |   lambdaExpression
        //right to left
        |   <assoc = right> prefixOps expression
        |   <assoc = right> unaryOps expression
        //left to right
        |   expression mulLevelOps expression
        |   expression addLevelOps expression
        |   expression shiftOps expression
        |   expression relationalOps expression
        |   expression equalityOps expression
        |   expression BitAndOp expression
        |   expression BitXorOp expression
        |   expression BitOrOp expression
        |   expression LogicAndOp expression
        |   expression LogicOrOp expression
        //right to left
        |   <assoc = right> expression AssignOp expression
        ;

lambdaExpression : LeftBracket BitAndOp RightBracket LeftParen funcDefArgList? RightParen LambdaArrowSymbol suiteStmt LeftParen funcCallArgList? RightParen;