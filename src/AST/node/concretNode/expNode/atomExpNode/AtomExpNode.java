package AST.node.concretNode.expNode.atomExpNode;

import AST.node.abstractNode.ExpNode;
import AST.typeNode.VarTypeNode;
import Utils.Position;

public abstract class AtomExpNode extends ExpNode {
    public AtomExpNode(Position _nodePos) {
        super(_nodePos);
    }
}
