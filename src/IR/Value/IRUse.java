package IR.Value;

import IR.Value.User.IRUser;

public class IRUse {
    public IRUser IRUser;
    public IRValue IRValue;

    public IRUse(IRUser IRUser, IRValue IRValue) {
        this.IRUser = IRUser;
        this.IRValue = IRValue;
    }


}
