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
//    public ArrayList<String> argNameList = new ArrayList<>();

    public ArrayList<IRValue> paraList = new ArrayList<>();


    public String funcName;
    public BasicType returnType;
//    public ArrayList<BasicType> argTypeList;

    public LinkedList<IRBasicBlock> blockList = new LinkedList<>();
    public IRBasicBlock entryBlock;//alloca指令从头插入
    public IRBasicBlock exitBlock;
    public IRValue retValPtr;
    public BasicType parentClassType = null;
    public boolean isBuiltIn = false;


    public IRFunction(FunctionType _funcType, String _funcName, BasicType _classType, boolean _isBuiltIn, ArrayList<IRValue> _paraList) {
        super(_funcType);
        this.funcName = _funcName;
        this.retValPtr = null;
        returnType = _funcType.returnType;
        this.parentClassType = _classType;
        isBuiltIn = _isBuiltIn;
        paraList = _paraList;
        if (paraList == null) paraList = new ArrayList<>();
    }


    public void addArg(IRValue _arg) {//for func call
        this.addOperand(_arg);
    }

//    public IRValue getArg(int _idx) {
//        return this.getOperand(_idx);
//    }

    public BasicType getParaType(int _idx) {
        return paraList.get(_idx).valueType;
    }

    public String getParaName(int _idx) {
        return paraList.get(_idx).valueName;
    }

    public void addPara(IRValue _para) {
        paraList.add(_para);
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

//        assert argTypeList.size() == argNameList.size();
        for (int i = 0; i < paraList.size(); i++) {
            ans.append(paraList.get(i).valueType.toString()).append(" ").append(LOCAL_PREFIX).append(paraList.get(i).valueName);
            if (i != paraList.size() - 1) {
                ans.append(", ");
            }
        }
        ans.append(") {\n");

        if (entryBlock != null) ans.append(entryBlock.toString());
        if (entryBlock != null) ans.append(exitBlock.toString());

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
