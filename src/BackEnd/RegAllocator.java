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
import java.util.HashMap;
import java.util.LinkedList;

public class RegAllocator implements ASMVisitor {
    ASMFunction curFunc;
    ASMBlock curBlock;

    //debug
    ASMInst curInst;

    private final ASMPhysicalASMReg Reg_SP = ASMPhysicalASMReg.regMap.get("sp");
    //tmp reg
    private final ASMPhysicalASMReg Reg_T0 = ASMPhysicalASMReg.regMap.get("t0");
    private final ASMPhysicalASMReg Reg_T1 = ASMPhysicalASMReg.regMap.get("t1");


    private HashMap<ASMPhysicalASMReg, ASMVirtualReg> regMap = new HashMap<>(); //记录 t0,t1 当前存着的是哪个vReg


    ASMPhysicalASMReg AllocRegForLoad(ASMReg _fromReg, ASMPhysicalASMReg _phyReg) {
        if (_fromReg == null) return null;
        if (_fromReg instanceof ASMVirtualReg vReg) {
            if (!vReg.equals(regMap.get(_phyReg))) {
                curFunc.spilledRegCnt = Math.max(curFunc.spilledRegCnt, vReg.rank + 1);//vReg.rank is 0 based
                ASMLoadInst loadInst = new ASMLoadInst(vReg.byteSize, _phyReg, Reg_SP, new ASMStackOffset(vReg.rank, ASMStackOffset.StackOffsetType.spillReg));

                regMap.replace(_phyReg, vReg);
                //debug
                loadInst.parentInst = curInst;
                curBlock.addInst(loadInst);
            }
            return _phyReg;
        } else return (ASMPhysicalASMReg) _fromReg;
    }

    ASMPhysicalASMReg AllocRegForStore(ASMReg _toReg, ASMPhysicalASMReg _phyReg) {
        if (_toReg == null) return null;//for BinaryInst with imm
        if (_toReg instanceof ASMVirtualReg vReg) {
            if (!vReg.equals(regMap.get(_phyReg))) {
                curFunc.spilledRegCnt = Math.max(curFunc.spilledRegCnt, vReg.rank + 1);//vReg.rank is 0 based
                ASMStoreInst storeInst = new ASMStoreInst(vReg.byteSize, _phyReg, Reg_SP, new ASMStackOffset(vReg.rank, ASMStackOffset.StackOffsetType.spillReg));

                regMap.replace(_phyReg, vReg);
                //debug
                storeInst.parentInst = curInst;

                curBlock.addInst(storeInst);
            }
            return _phyReg;
        } else return (ASMPhysicalASMReg) _toReg;
    }

//    void checkIfVReg(ASMReg _reg) {
//        if (_reg == null) return;
//        if (_reg instanceof ASMVirtualReg) throw new RuntimeException("vReg not handled");
//    }


    @Override
    public void visit(ASMModule asmModule) {
        regMap.put(Reg_T0, null);
        regMap.put(Reg_T1, null);

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
        curInst = asmBinaryInst;
        ASMBinaryInst inst = new ASMBinaryInst(asmBinaryInst.op, null, null, null, asmBinaryInst.imm);
        inst.rs1 = AllocRegForLoad(asmBinaryInst.rs1, Reg_T0);
        inst.rs2 = AllocRegForLoad(asmBinaryInst.rs2, Reg_T1);

        //debug
        inst.parentInst = asmBinaryInst;

//        checkIfVReg(asmBinaryInst.rs1);
//        checkIfVReg(asmBinaryInst.rs2);
//        checkIfVReg(asmBinaryInst.rd);

        curBlock.addInst(inst);
        inst.rd = AllocRegForStore(asmBinaryInst.rd, Reg_T0);
    }

    @Override
    public void visit(ASMBrInst asmBrInst) {
        curInst = asmBrInst;
        ASMBrInst inst = new ASMBrInst(asmBrInst.op, null, null, asmBrInst.targetBlock);
        inst.rs1 = AllocRegForLoad(asmBrInst.rs1, Reg_T0);
        inst.rs2 = AllocRegForLoad(asmBrInst.rs2, Reg_T1);

        curBlock.addInst(inst);
    }

    @Override
    public void visit(ASMCallInst asmCallInst) {
        curInst = asmCallInst;
        ASMCallInst inst = new ASMCallInst(asmCallInst.calledFunc);
        inst.parentInst = asmCallInst;
        curBlock.addInst(inst);
    }

    @Override
    public void visit(ASMJumpInst asmJumpInst) {
        curInst = asmJumpInst;
        ASMJumpInst inst = new ASMJumpInst(asmJumpInst.targetBlock);
        inst.parentInst = asmJumpInst;
        curBlock.addInst(inst);
    }

    @Override
    public void visit(ASMLiInst asmLiInst) {
//        checkIfVReg(asmLiInst.rd);
        curInst = asmLiInst;
        ASMLiInst inst = new ASMLiInst(null, asmLiInst.imm);
        inst.parentInst = asmLiInst;
        curBlock.addInst(inst);
        inst.rd = AllocRegForStore(asmLiInst.rd, Reg_T0);
    }

    @Override
    public void visit(ASMLoadInst asmLoadInst) {
        curInst = asmLoadInst;
        ASMLoadInst inst = new ASMLoadInst(asmLoadInst.byteWidth, null, null, asmLoadInst.imm);
        inst.rs1 = AllocRegForLoad(asmLoadInst.rs1, Reg_T0);

//        checkIfVReg(asmLoadInst.rs1);
//        checkIfVReg(asmLoadInst.rd);
        inst.parentInst = asmLoadInst;

        curBlock.addInst(inst);
        inst.rd = AllocRegForStore(asmLoadInst.rd, Reg_T0);
    }

    @Override
    public void visit(ASMLuiInst asmLuiInst) {
        curInst = asmLuiInst;
        ASMLuiInst inst = new ASMLuiInst(null, asmLuiInst.imm);
        inst.parentInst = asmLuiInst;
        curBlock.addInst(inst);
        inst.rd = AllocRegForStore(asmLuiInst.rd, Reg_T0);


    }

    @Override
    public void visit(ASMMvInst asmMvInst) {
        curInst = asmMvInst;
        ASMMvInst inst = new ASMMvInst(null, null);
        inst.rs1 = AllocRegForLoad(asmMvInst.rs1, Reg_T0);
        inst.parentInst = asmMvInst;

        curBlock.addInst(inst);
        inst.rd = AllocRegForStore(asmMvInst.rd, Reg_T0);
    }

    @Override
    public void visit(ASMRetInst asmRetInst) {
        curInst = asmRetInst;
        ASMRetInst inst = new ASMRetInst();
        curBlock.addInst(inst);
    }

    @Override
    public void visit(ASMStoreInst asmStoreInst) {
        curInst = asmStoreInst;
        ASMStoreInst inst = new ASMStoreInst(asmStoreInst.byteWidth, null, null, asmStoreInst.imm);
        inst.rs1 = AllocRegForLoad(asmStoreInst.rs1, Reg_T0);
        inst.rs2 = AllocRegForLoad(asmStoreInst.rs2, Reg_T1);

        inst.parentInst = asmStoreInst;

        curBlock.addInst(inst);
    }

    @Override
    public void visit(ASMUnaryInst asmUnaryInst) {
        curInst = asmUnaryInst;
        ASMUnaryInst inst = new ASMUnaryInst(asmUnaryInst.op, null, null);
        inst.rs1 = AllocRegForLoad(asmUnaryInst.rs1, Reg_T0);
        inst.parentInst = asmUnaryInst;

        curBlock.addInst(inst);
        inst.rd = AllocRegForStore(asmUnaryInst.rd, Reg_T0);

    }
}
