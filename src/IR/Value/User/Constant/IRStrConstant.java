package IR.Value.User.Constant;

import IR.IRVisitor;
import IR.Type.ArrayType;
import IR.Type.PointerType;

public class IRStrConstant extends IRConstant {
    public String strConstData;

    public String strIdentifier;

    public IRStrConstant(String _strIdentifier, String _strConstData) {
        super(new PointerType(new ArrayType(INT8, _strConstData.length()+1)));
        this.strConstData = _strConstData;
        this.strIdentifier = _strIdentifier;
    }


    //    @Override
    public String toString() {
       return  "c\"" + strConstData
                .replace("\n", "\\0A")
                .replace("\0", "\\00")
                .replace("\t", "\\09")
                .replace("\"", "\\22")
                + "\\00\"";

    }

    public String getIdentifier() {
        return strIdentifier;
    }

    @Override
    public boolean equals(IRConstant _IR_constant) {
        if (_IR_constant == null) {
            return false;
        } else {
            if (_IR_constant instanceof IRStrConstant) {
                return this.strConstData.equals(((IRStrConstant) _IR_constant).strConstData);
            } else return false;
        }
    }


    public void accept(IRVisitor _visitor) {
        _visitor.visit(this);
    }
}

