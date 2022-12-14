package AST.node.concretNode.BuiltInFuncDefNode;

import AST.node.concretNode.FuncDefNode;
import AST.node.concretNode.stmtNode.VarDefUnitNode;
import AST.typeNode.Type;
import Utils.Position;

import java.util.ArrayList;

public class BuiltInFuncDefNode extends FuncDefNode {
//    BuiltInParaList paraList;

    public BuiltInFuncDefNode(Type _returnType, String _funcName, Type _paraType, Position _nodePos) {
        super(_nodePos);
        this.returnType = _returnType;
        this.funcName = _funcName;
        this.isBuiltin = true;
        ArrayList<Type> paraTypeList = new ArrayList<>();
        if (_paraType != null) paraTypeList.add(_paraType);

        for (int i = 0; i < paraTypeList.size(); i++) {
            this.argList.add(new VarDefUnitNode(paraTypeList.get(i), "Arg" + i, false, _nodePos));
        }
    }

    public BuiltInFuncDefNode(Type _returnType, String _funcName, Type _paraType1, Type _paraType2, Position _nodePos) {
        super(_nodePos);
        this.returnType = _returnType;
        this.funcName = _funcName;
        this.isBuiltin = true;
        ArrayList<Type> paraTypeList = new ArrayList<>();
        if (_paraType1 != null) paraTypeList.add(_paraType1);
        if (_paraType2 != null) paraTypeList.add(_paraType2);

        for (int i = 0; i < paraTypeList.size(); i++) {
            this.argList.add(new VarDefUnitNode(paraTypeList.get(i), "Arg" + i, false, _nodePos));
        }
    }

}
