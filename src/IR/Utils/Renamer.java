package IR.Utils;

import java.util.HashMap;



public class Renamer {
    public static HashMap<String, Integer> renameMap = new HashMap<>();

    public String rename(String _name) {
        String newName;

        if (renameMap.containsKey(_name)) {
            renameMap.put(_name, renameMap.get(_name) + 1);
            newName = _name + "." + renameMap.get(_name).toString();
        } else {
            renameMap.put(_name, 0);
            newName = _name;
        }
        return newName;
    }

    public int getCurrentRank(String _name) {
        if (renameMap.containsKey(_name)) {
            return renameMap.get(_name);
        } else {
            throw new RuntimeException("no such name in renameMap");
        }
    }

}