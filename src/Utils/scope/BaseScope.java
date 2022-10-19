package Utils.scope;

import AST.typeNode.BasicVarTypeNode;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseScope {
    enum QueryType {
        VAR, FUNC, CLASS
    }
    public BaseScope parentScope;

    public BaseScope(BaseScope _parentScope) {
        this.parentScope = _parentScope;
    }

    public abstract BaseScope parentScope();
}
