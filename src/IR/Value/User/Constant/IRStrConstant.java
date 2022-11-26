package IR.Value.User.Constant;

import IR.IRVisitor;
import IR.Type.StringType;
import IR.Type.PointerType;

public class IRStrConstant extends IRConstant {
    public String strConstData;

    public IRStrConstant(String _strConstData) {
        super(new PointerType(new StringType(INT8, _strConstData.length()),1));
        this.strConstData = _strConstData;
    }


//    @Override
    public String getIdentifier() {
        return "c\"" + strConstData
                .replace("\n", "\\0A")
                .replace("\0", "\\00")
                .replace("\t", "\\09")
                .replace("\"", "\\22")
                + "\\00\"";
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

