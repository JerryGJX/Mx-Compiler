package AST.node.concretNode;

import AST.ASTVisitor;
import AST.node.ASTNode;
import AST.node.concretNode.stmtNode.SuiteStmtNode;
import Utils.Position;
import java.util.ArrayList;
import AST.typeNode.BasicVarTypeNode;
import Utils.scope.FuncBodyScope;

public class FuncDefNode extends ASTNode {
    public BasicVarTypeNode returnType; //constructor will set it to null
    public String funcName;
    public ArrayList<VarDefUnitNode> paramList;
    public SuiteStmtNode funcBodyNode;
    public boolean isBuiltin;

    public FuncBodyScope funcAndLoopScope;

    public FuncDefNode(BasicVarTypeNode _returnType, String _funcName, ArrayList<VarDefUnitNode> _paramList, SuiteStmtNode _funcBody, Position _nodePos) {
        super(_nodePos);
        this.returnType = _returnType;
        this.funcName = _funcName;
        this.paramList = _paramList;
        this.funcBodyNode = _funcBody;
        this.isBuiltin = false;
    }

    public FuncDefNode setBuiltin(boolean _isBuiltin) {
        this.isBuiltin = _isBuiltin;
        return this;
    }

    @Override
    public void accept(ASTVisitor _visitor) {
        _visitor.visit(this);
    }
}
