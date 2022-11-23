package IR.Value.User.Constant;

import IR.IRVisitor;
import IR.Type.BasicType;

public class IRZeroInitConstant extends IRConstant {
    public IRZeroInitConstant(BasicType _basicType) {
        super(_basicType);
    }

    @Override
    public void accept(IRVisitor _visitor) {
        _visitor.visit(this);
    }
}

