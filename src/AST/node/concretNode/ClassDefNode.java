package AST.node.concretNode;

import AST.ASTVisitor;
import AST.node.ASTNode;
import AST.node.concretNode.stmtNode.VarDefUnitNode;
import Utils.Position;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ClassDefNode extends ASTNode {
    public String className;
    public ConstructorDefNode constructorDefNode;
    public LinkedHashMap<String,VarDefUnitNode> memberVarMap;

    public ArrayList<String> memberVarNameList;
    public LinkedHashMap<String,FuncDefNode> memberFuncMap;

    public ClassDefNode(Position _nodePos) {
        super(_nodePos);
        this.className = null;
        this.constructorDefNode = null;
        this.memberVarMap = new LinkedHashMap<>();
        this.memberFuncMap = new LinkedHashMap<>();
    }

    public ClassDefNode(String _className, Position _nodePos) {
        super(_nodePos);
        this.className = _className;
        this.constructorDefNode = null;
        this.memberVarMap = new LinkedHashMap<>();
        this.memberFuncMap = new LinkedHashMap<>();
    }

    public String PrintClass() {
        //        for (VarDefUnitNode varDefUnitNode : memberVarMap) {
//            ret.append("memberVar: ").append(varDefUnitNode.PrintVarDefUnitNode()).append("\n");
//        }
//        for (FuncDefNode funcDefNode : memberFuncMap) {
//            ret.append("memberFunc: ").append(funcDefNode.PritnFunc()).append("\n");
//        }

        return "className: " + className + "\n";
//        for (VarDefUnitNode varDefUnitNode : memberVarMap) {
//            ret.append("memberVar: ").append(varDefUnitNode.PrintVarDefUnitNode()).append("\n");
//        }
//        for (FuncDefNode funcDefNode : memberFuncMap) {
//            ret.append("memberFunc: ").append(funcDefNode.PritnFunc()).append("\n");
//        }

    }

    @Override
    public void accept(ASTVisitor _visitor) {
        _visitor.visit(this);
    }
}
