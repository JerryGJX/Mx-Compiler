package IR;

import IR.Value.User.Constant.GlobalValue.IRFunction;
import IR.Value.User.Constant.GlobalValue.IRGlobalVariable;
import IR.Value.User.Constant.IRStrConstant;

import java.util.ArrayList;

public class IRModule {
    public ArrayList<IRFunction> IRFunctionList = new ArrayList<>();
    public ArrayList<IRFunction> builtinIRFunctionList = new ArrayList<>();
    public ArrayList<IRStrConstant> strConstantList = new ArrayList<>();
    public ArrayList<IRGlobalVariable> IRGlobalVariableList = new ArrayList<>();

    public String toString() {
        return "";
    }
}
