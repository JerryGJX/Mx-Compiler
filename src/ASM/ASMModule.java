package ASM;

import ASM.Operand.ASMGlobalString;
import ASM.Operand.ASMGlobalValue;
import ASM.Operand.ASMPhysicalReg;

import java.util.ArrayList;
import java.util.HashMap;

public class ASMModule {
    public HashMap<String, ASMPhysicalReg> regMap = new HashMap<>();
    public ArrayList<ASMGlobalValue> globalValueList = new ArrayList<>();
    public ArrayList<ASMGlobalString> globalStringList = new ArrayList<>();
    public ArrayList<ASMFunction> functionList = new ArrayList<>();
}
