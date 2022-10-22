package Utils.scope;

import AST.node.concretNode.ClassDefNode;
import AST.node.concretNode.FuncDefNode;
import AST.typeNode.VarTypeNode;
import FrontEnd.BuiltInElements;

import java.util.HashMap;

public class GlobalScope extends Scope implements BuiltInElements {
    public HashMap<String, FuncDefNode> funcTable;
    public HashMap<String, ClassDefNode> classTable;

    public GlobalScope() {
        super(false,null,false,false,null);
        funcTable = new HashMap<>();
        classTable = new HashMap<>();

        funcTable.put("print", FuncPrint);
        funcTable.put("println", FuncPrintln);
        funcTable.put("printInt", FuncPrintInt);
        funcTable.put("printlnInt", FuncPrintlnInt);
        funcTable.put("getString", FuncGetString);
        funcTable.put("getInt", FuncGetInt);
        funcTable.put("toString", FuncToString);


        ClassDefNode intClass = new ClassDefNode("int", null);
        ClassDefNode boolClass = new ClassDefNode("bool", null);

        ClassDefNode stringClass = new ClassDefNode("string", null);

        stringClass.memberFuncMap.put("length",FuncStringLength);
        stringClass.memberFuncMap.put("substring",FuncStringSubString);
        stringClass.memberFuncMap.put("parseInt",FuncStringParseInt);
        stringClass.memberFuncMap.put("ord",FuncStringOrd);

        funcTable.put("size", FuncArrSize);

        classTable.put("int", intClass);
        classTable.put("bool", boolClass);
        classTable.put("string", stringClass);

    }

    public void addFunc(String name, FuncDefNode _funcDefNode) {
        funcTable.put(name, _funcDefNode);
    }

    public void addClass(String name, ClassDefNode _classDefNode) {
        classTable.put(name, _classDefNode);
    }



    public Boolean hasFunc(String name) {
        return funcTable.containsKey(name);
    }

    public Boolean hasClass(String name) {
        return classTable.containsKey(name);
    }



    public FuncDefNode getFuncDef(String _identifier) {
        return funcTable.getOrDefault(_identifier, null);
    }

    public ClassDefNode getClassDef(String _identifier) {
        return classTable.getOrDefault(_identifier, null);
    }


}

