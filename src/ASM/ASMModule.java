package ASM;

import ASM.Operand.GlobalString;
import ASM.Operand.GlobalValue;
import ASM.Operand.PhysicalReg;

import java.util.ArrayList;
import java.util.HashMap;

public class ASMModule {
    public HashMap<String, PhysicalReg> regMap = new HashMap<>();
    public ArrayList<GlobalValue> globalValueList = new ArrayList<>();
    public ArrayList<GlobalString> globalStringList = new ArrayList<>();
    public ArrayList<ASMFunction> functionList = new ArrayList<>();
}
