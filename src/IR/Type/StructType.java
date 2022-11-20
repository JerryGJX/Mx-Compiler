package IR.Type;

import java.util.ArrayList;

public class StructType extends BasicType {
    public String classId;
    public ArrayList<BasicType> memberTypeList = new ArrayList<>();

    public StructType(String _classId) {
        super(TypeEnum.StructType);
        this.classId = _classId;
    }

    public void addMemberType(BasicType memberType) {
        memberTypeList.add(memberType);
    }

}
