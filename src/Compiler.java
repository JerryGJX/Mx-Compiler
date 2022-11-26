import AST.ASTBuilder;
import AST.node.concretNode.RootNode;
import FrontEnd.SemanticChecker;
import FrontEnd.SymbolCollector;
import MiddleEnd.IRBuilder;
import Utils.MxErrorListener;
import Utils.error.error;
import Utils.log.Log;
import Utils.scope.GlobalScope;
import grammar.MxLexer;
import grammar.MxParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class Compiler {
    public static void main(String[] args) throws Exception {
        String fileName = "D:\\Users\\JXGuo\\OneDrive\\studying materials\\computer related\\compiler_design\\myCompiler\\Mx-Compiler\\src\\test\\IRTest\\test.mx";
        InputStream inputStream = new FileInputStream(fileName);
//        CharStream inputStream = CharStreams.fromStream(inputStream);
        PrintStream outputStream = System.out;

        Log log = new Log();
        GlobalScope globalScope = new GlobalScope();
        try {
            RootNode ASTRoot;
            MxLexer lexer = new MxLexer(CharStreams.fromStream(inputStream));
            lexer.removeErrorListeners();
            lexer.addErrorListener(new MxErrorListener());
            MxParser parser = new MxParser(new CommonTokenStream(lexer));
            parser.removeErrorListeners();
            parser.addErrorListener(new MxErrorListener());
            ParseTree parseTreeRoot = parser.mxProgram();
            ASTBuilder astBuilder = new ASTBuilder(log);
            ASTRoot = (RootNode) astBuilder.visit(parseTreeRoot);
            new SymbolCollector(globalScope,log).visit(ASTRoot);
            new SemanticChecker(globalScope,log).visit(ASTRoot);
//            log.printLog();
            new IRBuilder(fileName,globalScope).visit(ASTRoot);
        } catch (error er) {
//            log.printLog();
//            System.err.println(er.toString());
            throw new RuntimeException();
        }
    }
}