package IR.Value.User.Instruction;

import IR.Type.BasicType;
import IR.Type.PointerType;
import IR.Value.IRBasicBlock;
import IR.Value.IRValue;

public class IRLoadInst extends IRInstruction {


    public BasicType loadType;
    public IRValue loadFromAddr;

    //    public String loadToAddr;
    public IRLoadInst(String _loadToAddr, BasicType _loadType, IRValue _loadFromAddr, IRBasicBlock _parentBlock) {
        super(_loadToAddr, new PointerType(_loadType), _parentBlock);
//        this.loadToAddr = _loadToAddr;
        this.loadType = _loadType;
        this.loadFromAddr = _loadFromAddr;
    }


    @Override
    public String printInst() {
        return this.getIdentifier() + " = " + LLVM_LOAD_INST + " " + loadType + ", " + loadFromAddr.valueType + " " + loadFromAddr.getIdentifier() + ", align " + loadType.size();
    }

}
