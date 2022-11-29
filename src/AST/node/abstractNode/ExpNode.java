package AST.node.abstractNode;

import AST.node.ASTNode;
import AST.typeNode.Type;
import AST.typeNode.VarTypeNode;
import IR.Type.StructType;
import IR.Value.IRValue;
import Utils.Position;

public abstract class ExpNode extends ASTNode {
    public Type exprType;
//    public boolean isLeftValue;

    public IRValue irValue;    //int or register
    public IRValue irAddress;  //xxx.addr, InstAlloc, InstGetElementPtr
    //for LLVM renaming
    public String llvmName;
    //for A + B, A.llvmName = A, B.llvmName = B, A + B.llvmName = add, and they are to be renamed

    public ExpNode(Position _nodePos) {
        super(_nodePos);
        this.exprType = null;
    }

    public abstract boolean isLeftValue();
}
