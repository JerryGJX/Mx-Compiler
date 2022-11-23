package IR.Type;

import java.util.ArrayList;

public class FunctionType extends BasicType {
    public BasicType returnType;
    String funcName;
    public ArrayList<BasicType> parameterTypeList = new ArrayList<>();

    public FunctionType(BasicType _returnType, String _funcName) {
        this.returnType = _returnType;
        this.funcName = _funcName;
    }

    @Override
    public String toString() {
        throw new Error("FunctionType.toString() not implemented");
    }

    @Override
    public boolean equals(BasicType _basicType) {
        if (_basicType instanceof FunctionType) {
            if (!this.returnType.equals(((FunctionType) _basicType).returnType)) return false;
            if (this.parameterTypeList.size() != ((FunctionType) _basicType).parameterTypeList.size()) return false;
            for (int i = 0; i < this.parameterTypeList.size(); i++) {
                if (!this.parameterTypeList.get(i).equals(((FunctionType) _basicType).parameterTypeList.get(i)))
                    return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public int sizeof() {
        return 0;
    }
}
