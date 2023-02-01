package AST.node.concretNode;

import AST.ASTVisitor;
import AST.node.ASTNode;
import AST.node.concretNode.stmtNode.SuiteStmtNode;
import AST.node.concretNode.stmtNode.VarDefUnitNode;
import AST.typeNode.Type;
import AST.typeNode.VarTypeNode;
import Utils.Position;

import java.util.ArrayList;
import java.util.LinkedList;

public class FuncDefNode extends ASTNode {
    public Type returnType; //constructor will set it to null
    public String funcName;
    public ArrayList<VarDefUnitNode> argList;
    public SuiteStmtNode funcBodyNode;
    public boolean isBuiltin;

    public boolean isMember;

    public String className;

//    public FuncDefNode(ReturnTypeNode _returnType, String _funcName, ArrayList<VarDefUnitNode> _paramList, SuiteStmtNode _funcBody, Position _nodePos, Boolean _isBuiltin) {
//        super(_nodePos);
//        this.returnType = _returnType;
//        this.funcName = _funcName;
//        this.argList = _paramList;
//        this.funcBodyNode = _funcBody;
//        this.isBuiltin = _isBuiltin;
//    }

    public FuncDefNode(Position _nodePos) {
        super(_nodePos);
        this.returnType = null;
        this.funcName = null;
        this.argList = new ArrayList<>();
        this.funcBodyNode = null;
        this.isBuiltin = false;
    }

    public FuncDefNode setBuiltin(boolean _isBuiltin) {
        this.isBuiltin = _isBuiltin;
        return this;
    }

    public String PrintFunc() {

        //        for (VarDefUnitNode varDefUnitNode : argList) {
//            ret.append(varDefUnitNode.varType.PrintType()).append(" ").append(varDefUnitNode.varName);
//            if(argList.indexOf(varDefUnitNode) != argList.size() - 1) {
//                ret.append(", ");
//            }
//        }
        return "[return type]: " + returnType.PrintType() + "; [funcName]: " + funcName + "; [paramList]: (" + ")\n";
    }

    @Override
    public void accept(ASTVisitor _visitor) {
        _visitor.visit(this);
    }
}
