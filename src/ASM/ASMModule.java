package ASM;

import ASM.Operand.ASMGlobalString;
import ASM.Operand.ASMGlobalValue;
import BackEnd.ASMVisitor;

import java.util.ArrayList;

public class ASMModule {
    public ArrayList<ASMGlobalValue> globalValueList = new ArrayList<>();
    public ArrayList<ASMGlobalString> globalStringList = new ArrayList<>();
    public ArrayList<ASMFunction> funcList = new ArrayList<>();

    public String toString() {
        StringBuilder ret = new StringBuilder();
        for (ASMFunction func : funcList) {
            ret.append(func.toString()).append("\n");
        }
        ret.append(".section .bss\n");
        for (ASMGlobalValue globalValue : globalValueList) {
            ret.append(globalValue.toString()).append("\n");
        }
        ret.append(".section .rodata\n");
        for (ASMGlobalString globalString : globalStringList) {
            ret.append(globalString.toString()).append("\n");
        }
        return ret.toString();
    }

    public void accept(ASMVisitor asmVisitor){
        asmVisitor.visit(this);
    }
}
