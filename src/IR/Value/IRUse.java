package IR.Value;

import IR.Value.User.IRUser;

public class IRUse {
    public IRUser IRUser;
    public IRValue IRValue;

    public IRUse(IRUser IRUser, IRValue IRValue) {
        this.IRUser = IRUser;
        this.IRValue = IRValue;
    }

    public static void addUse(IRUser _IRUser, IRValue _IRValue) {
//        IRUse IRUse = new IRUse(_IRUser, _IRValue);
//        _IRValue.IRUseList.add(IRUse);
//        _IRUser.IRUseList.add(IRUse);
    }


}
