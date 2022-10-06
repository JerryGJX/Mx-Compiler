set CLASSPATH=.;C:\javalib\antlr\antlr-4.11.1-complete.jar;%CLASSPATH%

cd D:\Work\studying materials\computer related\compiler_design\myCompiler\Mx-Compiler\src\grammar

echo Running ANTLR on the lexer: MxLexer.g4
java org.antlr.v4.Tool MxLexer.g4 -no-listener -no-visitor

echo Running ANTLR on the parser: MxParser.g4
java org.antlr.v4.Tool MxParser.g4 -no-listener -no-visitor

echo Compiling the Java code that ANTLR generator (the lexer and parser code)
javac *.java

echo Running the test rig on the generated parser, using as input the string in: input.txt
echo And generating a GUI output (i.e., a parse tree graphic)
grun Mx message -gui<..\test\antlr_data\input.txt