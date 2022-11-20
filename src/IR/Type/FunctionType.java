package IR.Type;

import java.util.ArrayList;

public class FunctionType extends BasicType{
    public BasicType returnType;
    public ArrayList<BasicType> parameterTypeList = new ArrayList<>();

    public FunctionType() {
       super(TypeEnum.FunctionType);
    }

    public FunctionType(BasicType returnType) {
        super(TypeEnum.FunctionType);
        this.returnType = returnType;
    }

    public void addParameterType(BasicType parameterType) {
        parameterTypeList.add(parameterType);
    }


}
