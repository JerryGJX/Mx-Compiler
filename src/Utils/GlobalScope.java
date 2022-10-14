package Utils;

import Utils.Error.semanticError;

import java.util.HashMap;

public class GlobalScope extends Scope {
    private HashMap<String, Integer> ClassTable;

    public GlobalScope(Scope parent) {
        super(parent);
        ClassTable = new HashMap<>();
    }

    public void addType(String name, Integer type, Position pos) {

    }

}

