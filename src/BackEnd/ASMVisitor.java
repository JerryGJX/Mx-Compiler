package BackEnd;

import ASM.ASMBlock;
import ASM.ASMFunction;
import ASM.ASMModule;
import ASM.Inst.*;

public interface ASMVisitor {
    void visit(ASMFunction asmFunction);

    void visit(ASMBlock asmBlock);

    void visit(ASMModule asmModule);

    void visit(ASMBinaryInst asmBinaryInst);

    void visit(ASMBrInst asmBrInst);

    void visit(ASMCallInst asmCallInst);

    void visit(ASMJumpInst asmJumpInst);

    void visit(ASMLiInst asmLiInst);

    void visit(ASMLoadInst asmLoadInst);

    void visit(ASMLuiInst asmLuiInst);

    void visit(ASMMvInst asmMvInst);

    void visit(ASMRetInst asmRetInst);

    void visit(ASMStoreInst asmStoreInst);

    void visit(ASMUnaryInst asmUnaryInst);
}
