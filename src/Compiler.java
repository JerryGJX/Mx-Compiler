import ASM.ASMModule;
import AST.ASTBuilder;
import AST.node.concretNode.RootNode;
import BackEnd.ASMBuilder;
import BackEnd.ASMPrinter;
import BackEnd.ASMTranslator;
import BackEnd.RegAllocator;
import FrontEnd.SemanticChecker;
import FrontEnd.SymbolCollector;
import IR.IRModule;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class Compiler {
    public static void main(String[] args) throws Exception {
//        String fileName = "../test/debug/test.mx";

        String fileName = "output.s";
//        String fileName = "test/debug/test.mx";
//        InputStream inputStream = new FileInputStream(fileName);
//
////        File llvmir = new File("../test/debug/test.ll");
//        File llvmir = new File("test/debug/test.ll");
//        PrintStream irPs = new PrintStream(llvmir);
        File asm = new File("output.s");
        PrintStream asmPs = new PrintStream(asm);

        CharStream inputStream = CharStreams.fromStream(System.in);
//        PrintStream outputStream = System.out;
        System.setOut(asmPs);

        Log log = new Log();
        GlobalScope globalScope = new GlobalScope();
        try {
            RootNode ASTRoot;
            MxLexer lexer = new MxLexer(inputStream);
            lexer.removeErrorListeners();
            lexer.addErrorListener(new MxErrorListener());
            MxParser parser = new MxParser(new CommonTokenStream(lexer));
            parser.removeErrorListeners();
            parser.addErrorListener(new MxErrorListener());
            ParseTree parseTreeRoot = parser.mxProgram();
            ASTBuilder astBuilder = new ASTBuilder(log);
            ASTRoot = (RootNode) astBuilder.visit(parseTreeRoot);
            new SymbolCollector(globalScope, log).visit(ASTRoot);
            new SemanticChecker(globalScope, log).visit(ASTRoot);

            IRModule projectIRModule = new IRModule(fileName);
//            System.setOut(irPs);
            new IRBuilder(projectIRModule, globalScope).visit(ASTRoot);


            ASMModule projectASMModule = new ASMModule();
            new ASMBuilder(projectASMModule).visit(projectIRModule);
            new RegAllocator().visit(projectASMModule);
            new ASMTranslator().visit(projectASMModule);

            new ASMPrinter().printAsm(projectASMModule);

        } catch (error er) {
            System.err.println(er.toString());
        }
    }
}