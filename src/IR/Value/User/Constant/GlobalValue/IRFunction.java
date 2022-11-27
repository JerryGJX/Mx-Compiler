package IR.Value.User.Constant.GlobalValue;

import IR.IRVisitor;
import IR.Type.BasicType;
import IR.Type.FunctionType;
import IR.Value.IRBasicBlock;
import IR.Value.IRValue;
import IR.Value.User.Constant.IRConstant;
import IR.Value.User.Instruction.IRInstruction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class IRFunction extends IRConstant {
    public BasicType returnType;

    public String funcName;
    public LinkedList<IRBasicBlock> blockList = new LinkedList<>();
    public ArrayList<IRValue> argVarList = new ArrayList<>();
    public IRBasicBlock entryBlock;//alloca指令从头插入
    public IRBasicBlock exitBlock;
    public IRValue retValPtr;

    public boolean isBuiltIn = false;


    public IRFunction(String _funcName, FunctionType _funcType,boolean _isBuiltIn) {
        super(_funcType);
        this.funcName = _funcName;
        this.retValPtr = null;
        returnType = _funcType.returnType;
        isBuiltIn = _isBuiltIn;
    }



    public void addArg(BasicType _argType, String _argName) {
        argVarList.add(new IRValue(_argName, _argType));
    }

    public FunctionType getFunctionType() {
        if (this.valueType instanceof FunctionType) {
            return (FunctionType) this.valueType;
        } else {
            throw new Error("IRFunction.getFunctionType() error");
        }
    }


//    public void AddAllocaInst(IRInstruction _allocaInst) {
//        entryBlock.instList.addFirst(_allocaInst);
//    }




    @Override
    public String getIdentifier() {
        return null;
    }

    public String toString() {
        StringBuilder ans = new StringBuilder("define " + this.returnType.toString() + " @" + this.funcName + "(");

//        for (int i = 0; i < argVarList.size(); i++) {
//            ans.append(argVarList.get(i).toString());
//            if (i != argVarList.size() - 1) {
//                ans.append(", ");
//            }
//        }
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
