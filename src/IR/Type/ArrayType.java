package IR.Type;

import java.util.ArrayList;

public class ArrayType extends BasicType {
    public BasicType elementType;
    public ArrayList<Integer> dimList = new ArrayList<>();

    public ArrayType(BasicType elementType) {
        super(TypeEnum.ArrayType);
        this.elementType = elementType;
    }

    public void addDim(int dim) {
        dimList.add(dim);
    }

    @Override
    public String toString() {
        if (dimList.size() == 0) return elementType.toString();
        else {
            var subArrayType = new ArrayType(elementType);
            subArrayType.dimList.addAll(dimList.subList(1, dimList.size()));
            return "[" + dimList.get(0) + " x " + subArrayType.toString() + "]";
        }
    }

    public boolean equals(BasicType other) {
        if (other == null) return false;
        if (!(other instanceof ArrayType otherArrayType)) return false;
        if (dimList.size() != otherArrayType.dimList.size()) return false;
        for (int i = 0; i < dimList.size(); ++i) {
            if (!dimList.get(i).equals(otherArrayType.dimList.get(i))) return false;
        }
        return elementType.equals(otherArrayType.elementType);
    }


}
