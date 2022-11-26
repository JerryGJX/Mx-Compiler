package IR.Value.User.Constant;

import IR.IRVisitor;

public class IRIntConstant extends IRConstant {
    public int constIntData;

    public IRIntConstant(int _value) {
        super(INT32);
        this.constIntData = _value;
    }

    @Override
    public boolean equals(IRConstant _IR_constant) {
        if(_IR_constant ==null){
            return false;
        }else {
            if (_IR_constant instanceof IRIntConstant) {
                return this.constIntData == ((IRIntConstant) _IR_constant).constIntData;
            } else return false;
        }
    }

//    @Override
    public String getIdentifier() {
        return Integer.toString(constIntData);
    }


    public void accept(IRVisitor _visitor) {
        _visitor.visit(this);
    }
}

