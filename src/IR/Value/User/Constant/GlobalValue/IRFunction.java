package IR.Value.User.Constant.GlobalValue;

import IR.IRVisitor;
import IR.Type.BasicType;
import IR.Type.FunctionType;
import IR.Type.StructType;
import IR.Value.IRBasicBlock;
import IR.Value.IRValue;
import IR.Value.User.Constant.IRConstant;
import IR.Value.User.Instruction.IRInstruction;
import org.antlr.v4.runtime.misc.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class IRFunction extends IRConstant {
    //for func def display only
    public ArrayList<String> argNameList = new ArrayList<>();

    public String funcName;
    public BasicType returnType;
    public ArrayList<BasicType> argTypeList;

    public LinkedList<IRBasicBlock> blockList = new LinkedList<>();
    public IRBasicBlock entryBlock;//alloca指令从头插入
    public IRBasicBlock exitBlock;
    public IRValue retValPtr;
    public BasicType parentClassType = null;
    public boolean isBuiltIn = false;


    public IRFunction(FunctionType _funcType, String _funcName, BasicType _classType, boolean _isBuiltIn, ArrayList<BasicType> _argTypeList) {
        super(_funcType);
        this.funcName = _funcName;
        this.retValPtr = null;
        returnType = _funcType.returnType;
        this.parentClassType = _classType;
        isBuiltIn = _isBuiltIn;
        argTypeList = _argTypeList;
        if(argTypeList == null) argTypeList = new ArrayList<>();
    }


    public void addArg(IRValue _arg) {//for func call
        this.addOperand(_arg);
    }

    public IRValue getArg(int _idx) {
        return this.getOperand(_idx);
    }

    public BasicType getArgType(int _idx) {
        return argTypeList.get(_idx);
    }


    public FunctionType getFunctionType() {
        if (this.valueType instanceof FunctionType) {
            return (FunctionType) this.valueType;
        } else {
            throw new Error("IRFunction.getFunctionType() error");
        }
    }


    public void addBlock(IRBasicBlock _block) {
        blockList.add(_block);
    }


    @Override
    public String getIdentifier() {
        return GLOBAL_PREFIX + this.funcName;
    }

    public String toString() {
        StringBuilder ans = new StringBuilder("define " + this.returnType.toString() + " @" + this.funcName + "(");

        assert argTypeList.size() == argNameList.size();
        for (int i = 0; i < argNameList.size(); i++) {
            ans.append(argTypeList.get(i).toString()).append(" ").append(LOCAL_PREFIX).append(argNameList.get(i));
            if (i != argNameList.size() - 1) {
                ans.append(", ");
            }
        }
        ans.append(") {\n");

        ans.append(entryBlock.toString());
        ans.append(exitBlock.toString());

        for (IRBasicBlock block : blockList) {
            ans.append(block.toString());
        }
        ans.append("}\n");
        return ans.toString();
    }

    @Override
    public boolean equals(IRConstant _IR_constant) {
        if (_IR_constant == null) {
            return false;
        } else {
            if (_IR_constant instanceof IRFunction) {
                return this.funcName.equals(((IRFunction) _IR_constant).funcName);
            } else return false;
        }
    }


    public void accept(IRVisitor _visitor) {
        _visitor.visit(this);
    }
}
