set CLASSPATH=.;C:\javalib\antlr\antlr-4.11.1-complete.jar;%CLASSPATH%

cd D:\Work\studying materials\computer related\compiler_design\myCompiler\Mx-Compiler\src\grammar

echo Running ANTLR on the lexer: MxLexer.g4
java org.antlr.v4.Tool MxLexer.g4 -listener -visitor

echo Running ANTLR on the parser: MxParser.g4
java org.antlr.v4.Tool MxParser.g4 -listener -visitor

echo Compiling the Java code that ANTLR generator (the lexer and parser code)
javac *.java

