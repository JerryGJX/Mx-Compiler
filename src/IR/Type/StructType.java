package IR.Type;

import java.util.ArrayList;
import java.util.HashMap;

public class StructType extends BasicType {
    public String classId;

    public HashMap<String, Integer> memberOffset = new HashMap<>();
    public ArrayList<BasicType> memberTypeList = new ArrayList<>();


    public StructType(String _classId) {
        this.classId = _classId;
    }

    public void addMember(String varName, BasicType memberType) {
        memberTypeList.add(memberType);
        memberOffset.put(varName, memberTypeList.size() - 1);
    }

    @Override
    public String toString() {
        return "%class." + classId;
    }

    public String PrintStructType() {
        StringBuilder Ans = new StringBuilder("%class." + classId + " = type {");
//        for (BasicType memberType : memberTypeList) {
//            Ans.append(memberType.toString());
//            if(memberType != memberTypeList.get(memberTypeList.size() - 1)) {
//                Ans.append(", ");
//            }
//        }
        for (int i = 0; i < memberTypeList.size(); i++) {
            Ans.append(memberTypeList.get(i).toString());
            if (i != memberTypeList.size() - 1) {
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
        for (BasicType memberType : memberTypeList) {
            size += memberType.size();
        }
        return size;
    }
}
