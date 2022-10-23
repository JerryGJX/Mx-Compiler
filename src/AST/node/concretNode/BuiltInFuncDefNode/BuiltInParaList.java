package AST.node.concretNode.BuiltInFuncDefNode;

import AST.ASTVisitor;
import AST.node.ASTNode;
import AST.node.concretNode.stmtNode.VarDefUnitNode;
import AST.typeNode.Type;
import Utils.Position;

import java.util.ArrayList;

public class BuiltInParaList {
    public ArrayList<VarDefUnitNode> paraList = new ArrayList<>();

    public BuiltInParaList(ArrayList<Type> _paraTypeList, Position _nodePos) {
        for(int i = 0;i<_paraTypeList.size();i++) {
            paraList.add(new VarDefUnitNode(_paraTypeList.get(i),"Arg" + i,false,_nodePos));
        }
    }

}
