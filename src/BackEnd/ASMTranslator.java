package BackEnd;

import ASM.ASMBlock;
import ASM.ASMFunction;
import ASM.ASMModule;
import ASM.Inst.*;
import ASM.Operand.ASMImm;
import ASM.Operand.ASMStackOffset;

public class ASMTranslator implements ASMVisitor {
    //translate stackOffset
    /*
     *
     * args for FuncA                              <-- getArg pos
     * ------------                                <-- fp (frame pointer of FuncA)
     * spilled regs                                <-- spillReg Pos
     * ------------
     * alloc                                       <-- Alloc pos
     * ------------
     * args for FuncB (for called function FuncB)  <-- putArg pos
     * ------------                                <-- sp
     */

    ASMFunction curFunc;

    public static int StackByteNum = 4;

    ASMImm translateStackOffset(ASMImm imm) {
        if (imm == null) return null;
        else if (imm instanceof ASMStackOffset stackOffset) {
            int val = stackOffset.val;
            int index = switch (stackOffset.type) {
                case getArg -> curFunc.totalStack + val;
                case alloca -> curFunc.spilledArgCnt + val;
                case spillReg -> curFunc.spilledArgCnt + curFunc.allocaCnt + val;
                case putArg -> val;
                case lowerSp -> -curFunc.totalStack;
                case raiseSp -> curFunc.totalStack;
            };
            return new ASMImm(index * StackByteNum);
        } else return imm;
    }

    @Override
    public void visit(ASMModule asmModule) {
        asmModule.funcList.forEach(func -> func.accept(this));
    }

    @Override
    public void visit(ASMFunction asmFunction) {
        curFunc = asmFunction;
        asmFunction.totalStack = asmFunction.spilledArgCnt + asmFunction.allocaCnt + asmFunction.spilledRegCnt;
        asmFunction.blockList.forEach(block -> block.accept(this));
    }

    @Override
    public void visit(ASMBlock asmBlock) {
        asmBlock.instList.forEach(inst -> inst.accept(this));
    }

    @Override
    public void visit(ASMBinaryInst asmBinaryInst) {
        asmBinaryInst.imm = translateStackOffset(asmBinaryInst.imm);
    }

    @Override
    public void visit(ASMBrInst asmBrInst) {

    }

    @Override
    public void visit(ASMCallInst asmCallInst) {

    }

    @Override
    public void visit(ASMJumpInst asmJumpInst) {

    }

    @Override
    public void visit(ASMLiInst asmLiInst) {

    }

    @Override
    public void visit(ASMLoadInst asmLoadInst) {
        asmLoadInst.imm = translateStackOffset(asmLoadInst.imm);
        if(asmLoadInst.imm != null && (asmLoadInst.imm instanceof ASMImm) && asmLoadInst.imm.val == 68){
            int A = 0;
        }
    }

    @Override
    public void visit(ASMLuiInst asmLuiInst) {

    }

    @Override
    public void visit(ASMMvInst asmMvInst) {

    }

    @Override
    public void visit(ASMRetInst asmRetInst) {

    }

    @Override
    public void visit(ASMStoreInst asmStoreInst) {
        asmStoreInst.imm = translateStackOffset(asmStoreInst.imm);
    }

    @Override
    public void visit(ASMUnaryInst asmUnaryInst) {

    }
}
