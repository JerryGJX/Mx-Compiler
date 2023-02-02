package ASM.Operand;

import java.util.ArrayList;
import java.util.HashMap;

public class ASMPhysicalASMReg extends ASMReg {
    public static HashMap<String, ASMPhysicalASMReg> regMap = new HashMap<>();
    public static ArrayList<ASMPhysicalASMReg> callerSavedRegList = new ArrayList<>();
    public static ArrayList<ASMPhysicalASMReg> calleeSavedRegList = new ArrayList<>();

    static {
        regMap.put("zero", new ASMPhysicalASMReg("zero"));
        regMap.put("ra", new ASMPhysicalASMReg("ra"));
        regMap.put("sp", new ASMPhysicalASMReg("sp"));
        regMap.put("gp", new ASMPhysicalASMReg("gp"));
        regMap.put("tp", new ASMPhysicalASMReg("tp"));
        for (int i = 0; i < 7; ++i) {
            var reg = new ASMPhysicalASMReg("t" + i);
            regMap.put("t" + i, reg);
            callerSavedRegList.add(reg);
        }
        for (int i = 0; i < 8; ++i) {
            var reg = new ASMPhysicalASMReg("a" + i);
            regMap.put("a" + i, reg);
            callerSavedRegList.add(reg);
        }
        for (int i = 0; i < 12; ++i) {
            var reg = new ASMPhysicalASMReg("s" + i);
            regMap.put("s" + i, reg);
            calleeSavedRegList.add(reg);
        }
    }

    @Override
    public Boolean isReserved() {
        return this.identifier.equals("zero") || this.identifier.equals("ra") || this.identifier.equals("sp") || this.identifier.equals("gp") || this.identifier.equals("tp");
    }



    public ASMPhysicalASMReg(String name) {
        super(name);
    }

    public boolean equals(ASMReg reg) {
        if(!(reg instanceof ASMPhysicalASMReg)) return false;
        return this.identifier.equals(reg.identifier);
    }

    public String toString() {
        return this.identifier;
    }
}
