package AST.node.concretNode;

import AST.ASTVisitor;
import AST.node.ASTNode;
import Utils.Position;

import java.util.ArrayList;

public class ClassDefNode extends ASTNode {
    public String className;
    public FuncDefNode constructorDefNode;
    public ArrayList<VarDefUnitNode> memberVarList;
    public ArrayList<FuncDefNode> memberFuncList;

    public ClassDefNode(Position _nodePos) {
        super(_nodePos);
        this.className = null;
        this.constructorDefNode = null;
        this.memberVarList = new ArrayList<>();
        this.memberFuncList = new ArrayList<>();
    }

    @Override
    public void accept(ASTVisitor _visitor) {
        _visitor.visit(this);
    }
}
