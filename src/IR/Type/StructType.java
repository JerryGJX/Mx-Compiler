package IR.Type;

import java.util.ArrayList;

public class StructType extends BasicType {
    public String classId;
    public ArrayList<BasicType> memberTypeList = new ArrayList<>();



    public StructType(String _classId) {
        this.classId = _classId;
    }

    public void addMemberType(BasicType memberType) {
        memberTypeList.add(memberType);
    }

    @Override
    public String toString() {
        return "%class." + classId;
    }

    public String PrintStructType() {
        StringBuilder Ans = new StringBuilder("%class." + classId + " = type {");
        for (BasicType memberType : memberTypeList) {
            Ans.append(memberType.toString());
            if(memberType != memberTypeList.get(memberTypeList.size() - 1)) {
                Ans.append(", ");
            }
        }
        Ans.append("}");
        return Ans.toString();
    }

    @Override
    public boolean equals(BasicType _basicType) {
        return _basicType instanceof StructType && this.classId.equals(((StructType) _basicType).classId);
    }

    @Override
    public int size() {
        int size = 0;
        for(BasicType memberType : memberTypeList){
            size += memberType.size();
        }
        return size;
    }
}
