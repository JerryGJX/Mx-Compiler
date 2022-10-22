import AST.ASTBuilder;
import AST.node.concretNode.RootNode;
import Utils.MxErrorListener;
import Utils.log.Log;
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
        String fileName = "D:\\Work\\studying materials\\computer related\\compiler_design\\myCompiler\\Mx-Compiler\\src\\test\\testCase\\sema\\basic-package\\basic-69.mx";
        InputStream inputStream = new FileInputStream(fileName);
        PrintStream outputStream = System.out;

        try {
            Log log = new Log();
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

            log.printLog();
        } catch (Error er) {
            System.err.println(er);
            throw new RuntimeException();
        }
    }
}