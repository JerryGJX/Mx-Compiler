package FrontEnd;

import AST.node.concretNode.BuiltInFuncDefNode.BuiltInFuncDefNode;
import AST.node.concretNode.BuiltInFuncDefNode.BuiltInParaList;
import AST.node.concretNode.FuncDefNode;
import AST.node.concretNode.stmtNode.VarDefUnitNode;
import AST.typeNode.Type;

import java.util.ArrayList;

public interface BuiltInElements {
    Type intType = new Type("int", 0, false);
    Type boolType = new Type("bool", 0, false);
    Type stringType = new Type("string", 0, false);
    Type voidType = new Type("void", 0, false);
    Type nullType = new Type("null", 0, false);

    Type autoType = new Type("_auto", 0, false);
    Type idType = new Type("_id", 0, false);

    BuiltInFuncDefNode FuncPrint = new BuiltInFuncDefNode(voidType,"print",stringType,null);
    BuiltInFuncDefNode FuncPrintln = new BuiltInFuncDefNode(voidType,"println",stringType,null);
    BuiltInFuncDefNode FuncPrintInt = new BuiltInFuncDefNode(voidType,"printInt",intType,null);
    BuiltInFuncDefNode FuncPrintlnInt = new BuiltInFuncDefNode(voidType,"printlnInt",intType,null);
    BuiltInFuncDefNode FuncGetString = new BuiltInFuncDefNode(stringType,"getString",null,null);
    BuiltInFuncDefNode FuncGetInt = new BuiltInFuncDefNode(intType,"getInt",null,null);
    BuiltInFuncDefNode FuncToString = new BuiltInFuncDefNode(stringType,"toString",intType,null);

    BuiltInFuncDefNode FuncStringLength = new BuiltInFuncDefNode(intType,"length",null,null);
    BuiltInFuncDefNode FuncStringSubString = new BuiltInFuncDefNode(stringType,"substring",intType,intType,null);
    BuiltInFuncDefNode FuncStringParseInt = new BuiltInFuncDefNode(intType,"parseInt",null,null);
    BuiltInFuncDefNode FuncStringOrd = new BuiltInFuncDefNode(intType,"ord",intType,null);
    
    BuiltInFuncDefNode FuncArrSize = new BuiltInFuncDefNode(intType,"size",null,null);


}
