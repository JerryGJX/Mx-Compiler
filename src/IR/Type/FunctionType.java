package IR.Type;

import java.util.ArrayList;
import java.util.Arrays;

public class FunctionType extends BasicType {
    public BasicType returnType;
    public String funcName;
    public ArrayList<BasicType> parameterTypeList = new ArrayList<>();

    public FunctionType(BasicType _returnType, String _funcName) {
        this.returnType = _returnType;
        this.funcName = _funcName;
    }

    public FunctionType(BasicType _returnType, String _funcName, BasicType... _parameterTypeList) {
        this.returnType = _returnType;
        this.funcName = _funcName;
        this.parameterTypeList.addAll(Arrays.asList(_parameterTypeList));
    }

    public void addParameterType(BasicType parameterType) {
        parameterTypeList.add(parameterType);
    }


    @Override
    public String toString() {
        StringBuilder Ans = new StringBuilder(returnType.toString() + " " + GLOBAL_PREFIX + funcName + "(");
        for (int i = 0; i < parameterTypeList.size(); i++) {
            Ans.append(parameterTypeList.get(i).toString());
            if (i != parameterTypeList.size() - 1) {
                Ans.append(", ");
            }
        }
        Ans.append(")");
        return Ans.toString();
    }

    @Override
    public boolean equals(BasicType _basicType) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }
}
