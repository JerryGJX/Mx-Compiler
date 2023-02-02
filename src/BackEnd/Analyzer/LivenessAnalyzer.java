package BackEnd.Analyzer;

import ASM.ASMBlock;
import ASM.ASMFunction;
import ASM.Operand.ASMReg;

import java.util.HashMap;
import java.util.HashSet;

public class LivenessAnalyzer {
    static final int K = 16; //not sure

    HashMap<ASMBlock, HashSet<ASMReg>> liveInMap, liveOutMap, defMap;

    public void runOnFunc(ASMFunction _asmFunc) {
        liveInMap = new HashMap<>();
        liveOutMap = new HashMap<>();
        defMap = new HashMap<>();

        _asmFunc.blockList.forEach(block -> {
            HashSet<ASMReg> def = new HashSet<>();
            HashSet<ASMReg> use = new HashSet<>();
            block.instList.forEach(inst -> {
                inst.use.forEach(reg -> {
                    if (!def.contains(reg))
                        use.add(reg);
                    def.addAll(inst.def);
                });
            });
            defMap.put(block, def);
            liveInMap.put(block, use);
            liveOutMap.put(block, new HashSet<>());//迭代初值
        });

        while (true) {
            boolean stableFlag = false;
            for (int i = _asmFunc.blockList.size() - 1; i >= 0; i--) {
                ASMBlock block = _asmFunc.blockList.get(i);
                HashSet<ASMReg> liveInSet = liveInMap.get(block);
                HashSet<ASMReg> liveOutSet = liveOutMap.get(block);
                HashSet<ASMReg> defSet = defMap.get(block);
                int oldLiveInSize = liveInSet.size();
                int oldLiveOutSize = liveOutSet.size();
                liveOutSet.removeAll(defSet);
                liveInSet.addAll(liveOutSet);
                block.sucBlockList.forEach(sucBlock -> {
                    liveOutSet.addAll(liveInMap.get(sucBlock));  //out[B] = U in[S], S in suc[B]
                }); //todo: 此处照虎书翻译,但不完全理解原理
                stableFlag |= oldLiveInSize != liveInSet.size() && oldLiveOutSize != liveOutSet.size();
            }
            if (!stableFlag) break;
        }  //迭代得到稳定解
    }

}

