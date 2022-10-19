package AST.node.concretNode.expNode;

import AST.ASTVisitor;
import AST.node.abstractNode.ExpNode;
import AST.node.concretNode.FuncDefNode;
import AST.typeNode.TypeEnum;
import Utils.Position;

public class MemberExpNode extends ExpNode {
    public ExpNode base;
    public String memberName;
    public FuncDefNode memberFunc;

    public MemberExpNode(ExpNode _base, String _memberName, Position _pos) {
        super(_pos);
        this.base = _base;
        this.memberName = _memberName;
        memberFunc = null;
    }

    @Override
    public void accept(ASTVisitor _visitor) {
        _visitor.visit(this);
    }

    @Override
    public boolean isLeftValue() {
        return !this.exprType.matchType(TypeEnum.FUNCTION);//函数不是左值
    }
}
