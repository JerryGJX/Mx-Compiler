package IR.Value.User.Constant.GlobalValue;

import IR.IRVisitor;
import IR.Type.BasicType;
import IR.Type.FunctionType;
import IR.Value.IRBasicBlock;
import IR.Value.IRValue;
import IR.Value.User.Constant.IRConstant;

import java.util.LinkedList;

public class IRFunction extends IRConstant {
    public LinkedList<IRBasicBlock> blockList = new LinkedList<>();
    public IRBasicBlock entryBlock;
    public IRBasicBlock exitBlock;
    public IRValue retValPtr;

    public boolean isUsed = false;
    public boolean isBuiltIn = false;

    public IRFunction(String _funcName, BasicType _retType) {
        super(_funcName, new FunctionType(_retType, _funcName));
        this.retValPtr = null;
    }

    public void addBlock(IRBasicBlock _block) {
        blockList.add(_block);
    }


    @Override
    public String getIdentifier() {
        return null;
    }

    @Override
    public boolean equals(IRConstant _IR_constant) {
        if(_IR_constant == null) {
            return false;
        }else {
            if(_IR_constant instanceof IRFunction) {
                return this.valueName.equals(_IR_constant.valueName);
            } else return false;
        }
    }

    @Override
    public void accept(IRVisitor _visitor) {
        _visitor.visit(this);
    }
}
