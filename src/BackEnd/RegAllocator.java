package BackEnd;

import ASM.ASMBlock;
import ASM.ASMFunction;
import ASM.ASMModule;
import ASM.Inst.*;
import ASM.Operand.ASMPhysicalASMReg;
import ASM.Operand.ASMReg;
import ASM.Operand.ASMStackOffset;
import ASM.Operand.ASMVirtualReg;

import java.util.ArrayList;
import java.util.LinkedList;

public class RegAllocator implements ASMVisitor {
    ASMFunction curFunc;
    ASMBlock curBlock;

    private final ASMPhysicalASMReg Reg_SP = ASMPhysicalASMReg.regMap.get("sp");
    //tmp reg
    private final ASMPhysicalASMReg Reg_T0 = ASMPhysicalASMReg.regMap.get("t0");
    private final ASMPhysicalASMReg Reg_T1 = ASMPhysicalASMReg.regMap.get("t1");


    ASMPhysicalASMReg AllocRegForLoad(ASMReg _fromReg, ASMPhysicalASMReg _phyReg) {
        if (_fromReg == null) return null;
        if (_fromReg instanceof ASMVirtualReg vReg) {
            curFunc.spilledRegCnt = Math.max(curFunc.spilledRegCnt, vReg.rank + 1);//vReg.rank is 0 based
            ASMLoadInst loadInst = new ASMLoadInst(vReg.byteSize, _phyReg, Reg_SP, new ASMStackOffset(vReg.rank, ASMStackOffset.StackOffsetType.spillReg));
            curBlock.addInst(loadInst);
            return _phyReg;
        } else return (ASMPhysicalASMReg) _fromReg;
    }

    ASMPhysicalASMReg AllocRegForStore(ASMReg _toReg, ASMPhysicalASMReg _phyReg) {
        if (_toReg == null) return null;//for BinaryInst with imm
        if (_toReg instanceof ASMVirtualReg vReg) {
            curFunc.spilledRegCnt = Math.max(curFunc.spilledRegCnt, vReg.rank + 1);//vReg.rank is 0 based
            ASMStoreInst storeInst = new ASMStoreInst(vReg.byteSize, _phyReg, Reg_SP, new ASMStackOffset(vReg.rank, ASMStackOffset.StackOffsetType.spillReg));
            curBlock.addInst(storeInst);
            return _phyReg;
        } else return (ASMPhysicalASMReg) _toReg;
    }

    void checkIfVReg(ASMReg _reg) {
        if (_reg == null) return;
        if (_reg instanceof ASMVirtualReg) throw new RuntimeException("vReg not handled");
    }


    @Override
    public void visit(ASMModule asmModule) {
        asmModule.funcList.forEach(func -> func.accept(this));
    }

    @Override
    public void visit(ASMFunction asmFunction) {
        curFunc = asmFunction;

        asmFunction.blockList.forEach(block -> block.accept(this));
    }

    @Override
    public void visit(ASMBlock asmBlock) {
        curBlock = asmBlock;
        LinkedList<ASMInst> oldInstList = asmBlock.instList;
        asmBlock.instList = new LinkedList<>();
        oldInstList.forEach(inst -> inst.accept(this));
    }

    @Override
    public void visit(ASMBinaryInst asmBinaryInst) {
        asmBinaryInst.rs1 = AllocRegForLoad(asmBinaryInst.rs1, Reg_T0);
        asmBinaryInst.rs2 = AllocRegForLoad(asmBinaryInst.rs2, Reg_T1);

//        checkIfVReg(asmBinaryInst.rs1);
//        checkIfVReg(asmBinaryInst.rs2);
//        checkIfVReg(asmBinaryInst.rd);

        curBlock.addInst(asmBinaryInst);
        asmBinaryInst.rd = AllocRegForStore(asmBinaryInst.rd, Reg_T0);
    }

    @Override
    public void visit(ASMBrInst asmBrInst) {
        asmBrInst.rs1 = AllocRegForLoad(asmBrInst.rs1, Reg_T0);
        asmBrInst.rs2 = AllocRegForLoad(asmBrInst.rs2, Reg_T1);

        checkIfVReg(asmBrInst.rs1);
        checkIfVReg(asmBrInst.rs2);

        curBlock.addInst(asmBrInst);
    }

    @Override
    public void visit(ASMCallInst asmCallInst) {
        curBlock.addInst(asmCallInst);
    }

    @Override
    public void visit(ASMJumpInst asmJumpInst) {
        curBlock.addInst(asmJumpInst);
    }

    @Override
    public void visit(ASMLiInst asmLiInst) {
//        checkIfVReg(asmLiInst.rd);

        curBlock.addInst(asmLiInst);
        asmLiInst.rd = AllocRegForStore(asmLiInst.rd, Reg_T0);
    }

    @Override
    public void visit(ASMLoadInst asmLoadInst) {
        asmLoadInst.rs1 = AllocRegForLoad(asmLoadInst.rs1, Reg_T0);

//        checkIfVReg(asmLoadInst.rs1);
//        checkIfVReg(asmLoadInst.rd);

        curBlock.addInst(asmLoadInst);
        asmLoadInst.rd = AllocRegForStore(asmLoadInst.rd, Reg_T0);
    }

    @Override
    public void visit(ASMLuiInst asmLuiInst) {

//        checkIfVReg(asmLuiInst.rd);

        curBlock.addInst(asmLuiInst);
        asmLuiInst.rd = AllocRegForStore(asmLuiInst.rd, Reg_T0);
    }

    @Override
    public void visit(ASMMvInst asmMvInst) {
        asmMvInst.rs1 = AllocRegForLoad(asmMvInst.rs1, Reg_T0);

//        checkIfVReg(asmMvInst.rs1);
//        checkIfVReg(asmMvInst.rd);

        curBlock.addInst(asmMvInst);
        asmMvInst.rd = AllocRegForStore(asmMvInst.rd, Reg_T0);
    }

    @Override
    public void visit(ASMRetInst asmRetInst) {
        curBlock.addInst(asmRetInst);
    }

    @Override
    public void visit(ASMStoreInst asmStoreInst) {
        asmStoreInst.rs1 = AllocRegForLoad(asmStoreInst.rs1, Reg_T0);
        asmStoreInst.rs2 = AllocRegForLoad(asmStoreInst.rs2, Reg_T1);

        checkIfVReg(asmStoreInst.rs1);
        checkIfVReg(asmStoreInst.rs2);

        curBlock.addInst(asmStoreInst);
    }

    @Override
    public void visit(ASMUnaryInst asmUnaryInst) {
        asmUnaryInst.rs1 = AllocRegForLoad(asmUnaryInst.rs1, Reg_T0);

        checkIfVReg(asmUnaryInst.rs1);
//        checkIfVReg(asmUnaryInst.rd);

        curBlock.addInst(asmUnaryInst);
        asmUnaryInst.rd = AllocRegForStore(asmUnaryInst.rd, Reg_T0);

    }
}
