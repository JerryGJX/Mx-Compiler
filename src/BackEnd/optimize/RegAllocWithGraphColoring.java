package BackEnd.optimize;

import ASM.ASMBlock;
import ASM.ASMFunction;
import ASM.ASMModule;
import ASM.Inst.*;
import ASM.Operand.ASMPhysicalASMReg;
import ASM.Operand.ASMReg;
import ASM.Operand.ASMStackOffset;
import ASM.Operand.ASMVirtualReg;

import java.util.*;

public class RegAllocWithGraphColoring {
    static final int INF_VALUE = Integer.MAX_VALUE;

    static final int K = 20; //not sure


    //机器寄存器集合
    HashSet<ASMReg> preColored = new HashSet<>();
    //临时寄存器集合,既没有预着色,也没有被处理
    HashSet<ASMReg> initial = new HashSet<>();
    //低度数且 mv 无关的节点
    HashSet<ASMReg> simplifyWorkList = new HashSet<>();
    //低度数且 mv 有关的节点
    HashSet<ASMReg> freezeWorkList = new HashSet<>();
    //高度数的节点
    HashSet<ASMReg> spillWorkList = new HashSet<>();
    //本轮要被溢出 (spill) 的节点
    HashSet<ASMReg> spilledNodes = new HashSet<>();
    //已合并的寄存器集合, 当合并 u <- v 时, 将 v 加入 coalescedNodes, u放回工作表
    HashSet<ASMReg> coalescedNodes = new HashSet<>();
    //已着色的节点集合
    HashSet<ASMReg> coloredNodes = new HashSet<>();
    //包含从图中删除的临时变量的栈
    Stack<ASMReg> selectStack = new Stack<>();

    //已经合并的 mv 指令集合
    HashSet<ASMMvInst> coalescedMoves = new HashSet<>();
    //源操作数和目标操作数冲突的 mv 指令集合
    HashSet<ASMMvInst> constrainedMoves = new HashSet<>();
    //不再考虑合并的 mv 指令集合
    HashSet<ASMMvInst> frozenMoves = new HashSet<>();
    //有可能合并的传送指令集合
    HashSet<ASMMvInst> workListMoves = new HashSet<>();
    //还未做好合并准备的传送指令集合
    HashSet<ASMMvInst> activeMoves = new HashSet<>();

    HashSet<ASMReg> tmpRegSet = new HashSet<>();


    static class RegPair {
        ASMReg first, second;

        RegPair(ASMReg u, ASMReg v) {
            this.first = u;
            this.second = v;
        }
    }

    //图中冲突边 (u,v) 的集合, 同时 (v,u) 也在其中
    HashSet<RegPair> adjSet = new HashSet<>();
    //图的邻接表表示, 对于每一个非预着色的临时变量 u, adjList[u] 是与 u 冲突的节点集合
    HashMap<ASMReg, HashSet<ASMReg>> adjList = new HashMap<>();
    //每个节点当前的度数
    HashMap<ASMReg, Integer> degree = new HashMap<>();
    //从一个节点到与该节点相关的 mv 指令表的映射
    HashMap<ASMReg, HashSet<ASMMvInst>> moveList = new HashMap<>();
    //当一条 mv 指令 (u,v) 已被合并, 并且 v 已放入到已合并节点集合 coalescedNodes 中, 那么 alias[v] = u
    HashMap<ASMReg, ASMReg> alias = new HashMap<>();
    //每个节点的颜色
    HashSet<ASMReg> color = new HashSet<>();

    HashSet<ASMReg> reservedColor = new HashSet<>();

    HashMap<ASMReg, Integer> frequencyMap = new HashMap<>();


    HashMap<ASMBlock, HashSet<ASMReg>> liveInMap, liveOutMap, defMap;

    private void Init() {
        preColored = new HashSet<>();
        initial = new HashSet<>();
        simplifyWorkList = new HashSet<>();
        freezeWorkList = new HashSet<>();
        spillWorkList = new HashSet<>();
        spilledNodes = new HashSet<>();
        coalescedNodes = new HashSet<>();
        coloredNodes = new HashSet<>();
        selectStack = new Stack<>();

        coalescedMoves = new HashSet<>();
        constrainedMoves = new HashSet<>();
        frozenMoves = new HashSet<>();
        workListMoves = new HashSet<>();
        activeMoves = new HashSet<>();
        frequencyMap = new HashMap<>();
    }

    private void LivenessAnalysis(ASMFunction _asmFunc) {
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


    public void runOnModule(ASMModule module) {
        module.funcList.forEach(this::runOnFunc);
    }


    public void runOnFunc(ASMFunction function) {
        tmpRegSet.clear();
        GraphColoring(function);
    }


    void GraphColoring(ASMFunction function) {
        Init();
        ASMPhysicalASMReg.regMap.forEach((s, reg) -> {
            preColored.add(reg);
            color.add(reg);
            reg.color = reg;
            if (reg.isReserved()) {
                reservedColor.add(reg);
            }
        });

        for (ASMBlock block : function.blockList) {
            for (ASMInst inst : block.instList) {
                initial.addAll(inst.def);
                initial.addAll(inst.use);
            }
        }

        for (ASMReg reg : initial) {
            adjList.put(reg, new HashSet<>());
            moveList.put(reg, new HashSet<>());
            degree.put(reg, 0);
            alias.put(reg, null);

            if (preColored.contains(reg)) {
                frequencyMap.put(reg, INF_VALUE);
                degree.replace(reg, INF_VALUE);
            } else {
                frequencyMap.put(reg, 0);
            }
        }

        initial.removeAll(preColored);

        LivenessAnalysis(function);
        Build(function);
        MakeWorkList();

        while (true) {
            if (!simplifyWorkList.isEmpty()) {
                Simplify();
            } else if (!workListMoves.isEmpty()) {
                Coalesce();
            } else if (!freezeWorkList.isEmpty()) {
                Freeze();
            } else if (!spillWorkList.isEmpty()) {
                SelectSpill();
            } else {
                break;
            }
        }

        AssignColors();

        if (!spilledNodes.isEmpty()) {
            RewriteProgram(function);
            GraphColoring(function);
        }


    }


    void Build(ASMFunction function) {
        for (ASMBlock block : function.blockList) {
            HashSet<ASMReg> live = liveOutMap.get(block);
            for (int i = block.instList.size() - 1; i >= 0; i--) {
                ASMInst inst = block.instList.get(i);

                inst.def.forEach(reg -> frequencyMap.replace(reg, frequencyMap.get(reg) + (preColored.contains(reg) ? 0 : 1)));
                inst.use.forEach(reg -> frequencyMap.replace(reg, frequencyMap.get(reg) + (preColored.contains(reg) ? 0 : 1)));

                if (inst instanceof ASMMvInst mvInst) {
                    mvInst.use.forEach(live::remove);
                    mvInst.def.forEach(d -> moveList.get(d).add(mvInst));
                    mvInst.use.forEach(u -> moveList.get(u).add(mvInst));
                    workListMoves.add(mvInst);
                }
                live.addAll(inst.def);
                inst.def.forEach(d -> live.forEach(l -> AddEdge(l, d)));

                inst.def.forEach(live::remove);
                live.addAll(inst.use);
            }
        }
    }

    void AddEdge(ASMReg u, ASMReg v) {
        RegPair regPair1 = new RegPair(u, v);
        RegPair regPair2 = new RegPair(v, u);
        if (!adjSet.contains(regPair1) && u != v) {

            if (u.isReserved() || v.isReserved()) return;//todo : check

            adjSet.add(regPair1);
            adjSet.add(regPair2);
            if (!preColored.contains(u)) {
                adjList.get(u).add(v);
                degree.replace(u, degree.get(u) + 1);
            }
            if (!preColored.contains(v)) {
                adjList.get(v).add(u);
                degree.replace(v, degree.get(v) + 1);
            }
        }
    }

    void MakeWorkList() {
        initial.forEach(n -> {
//            initial.remove(n);  //todo: check
            if (degree.get(n) >= K) {
                spillWorkList.add(n);
            } else if (MoveRelated(n)) {
                freezeWorkList.add(n);
            } else {
                simplifyWorkList.add(n);
            }
        });
    }

    HashSet<ASMReg> Adjacent(ASMReg n) {
        HashSet<ASMReg> res = new HashSet<>(adjList.get(n));
        selectStack.forEach(res::remove);
        coalescedNodes.forEach(res::remove);
        return res;
    }

    HashSet<ASMMvInst> NodeMoves(ASMReg n) {
        HashSet<ASMMvInst> res = new HashSet<>(activeMoves);
        res.addAll(workListMoves);
        res.retainAll(moveList.get(n)); //取交集
        return res;
    }

    boolean MoveRelated(ASMReg n) {
        return !NodeMoves(n).isEmpty();
    }

    void Simplify() {
        ASMReg n = simplifyWorkList.iterator().next();
        simplifyWorkList.remove(n);
        selectStack.add(n);
        Adjacent(n).forEach(this::DecrementDegree);
    }

    void DecrementDegree(ASMReg m) {
        int d = degree.get(m);
        degree.replace(m, d - 1);
        if (d == K) {
            HashSet<ASMReg> adjacent = Adjacent(m);
            adjacent.add(m);
            EnableMoves(adjacent);
            spillWorkList.remove(m);
            if (MoveRelated(m)) {
                freezeWorkList.add(m);
            } else {
                simplifyWorkList.add(m);
            }
        }
    }

    void EnableMoves(HashSet<ASMReg> nodes) {
        nodes.forEach(n -> NodeMoves(n).forEach(m -> {
            if (activeMoves.contains(m)) {
                activeMoves.remove(m);
                workListMoves.add(m);
            }
        }));
    }

    void Coalesce() {
        ASMMvInst m = workListMoves.iterator().next();
        ASMReg x = GetAlias(m.rd);
        ASMReg y = GetAlias(m.rs1);

        ASMReg u, v;
        if (preColored.contains(y)) {
            u = y;
            v = x;
        } else {
            u = x;
            v = y;
        }
        workListMoves.remove(m);
        if (u == v) {
            //直接合并
            coalescedMoves.add(m);
            AddWorkList(u);
        } else if (preColored.contains(v) || adjSet.contains(new RegPair(u, v))) {
            //有冲突, 不能合并, 被约束
            constrainedMoves.add(m);
            AddWorkList(u);
            AddWorkList(v);
        } else {
            boolean flag = true;
            if (preColored.contains(u)) {
                boolean ok = true;
                for (ASMReg t : Adjacent(v)) {
                    ok &= OK(t, u);
                }
                flag = ok;
            } else {
                HashSet<ASMReg> union = new HashSet<>(Adjacent(u));
                union.addAll(Adjacent(v));
                flag = Conservative(union);
            }
            if (flag) {
                coalescedMoves.add(m); //todo : check
                Combine(u, v);
                AddWorkList(u);
            } else {
                activeMoves.add(m);
            }
        }
    }

    // 可以简化则加入简化 workList
    void AddWorkList(ASMReg u) {
        if (!preColored.contains(u) && !MoveRelated(u) && degree.get(u) < K) {
            freezeWorkList.remove(u);
            simplifyWorkList.add(u);
        }
    }

    boolean OK(ASMReg t, ASMReg r) {
        return degree.get(t) < K || preColored.contains(t) || adjSet.contains(new RegPair(t, r));
    }

    boolean Conservative(HashSet<ASMReg> nodes) {
        int k = 0;
        for (ASMReg n : nodes) {
            if (degree.get(n) >= K) {
                k++;
            }
        }
        return k < K;
    }

    ASMReg GetAlias(ASMReg n) {
        if (coalescedNodes.contains(n)) {
            return GetAlias(alias.get(n));
        } else {
            return n;
        }
    }

    void Combine(ASMReg u, ASMReg v) {
        if (freezeWorkList.contains(v)) {
            freezeWorkList.remove(v);
        } else {
            spillWorkList.remove(v);
        }
        coalescedNodes.add(v);
        alias.replace(v, u);

        moveList.get(u).addAll(moveList.get(v));
        HashSet<ASMReg> vSet = new HashSet<>();
        vSet.add(v);
        EnableMoves(vSet);   //todo : check EnableMoves(v)
        HashSet<ASMReg> adjacent = Adjacent(v);
        for (ASMReg t : adjacent) {
            AddEdge(t, u);
            DecrementDegree(t);
        }
        if (degree.get(u) >= K && freezeWorkList.contains(u)) {
            freezeWorkList.remove(u);
            spillWorkList.add(u);
        }
    }

    void Freeze() {
        ASMReg u = freezeWorkList.iterator().next();
        freezeWorkList.remove(u);
        simplifyWorkList.add(u);
        FreezeMoves(u);
    }

    void FreezeMoves(ASMReg u) {
        NodeMoves(u).forEach(m -> {
            ASMReg x = m.rd, y = m.rs1;
            ASMReg v;
            if (GetAlias(y) == GetAlias(u)) {
                v = GetAlias(x);
            } else {
                v = GetAlias(y);
            }
            activeMoves.remove(m);
            frozenMoves.add(m);
            if (NodeMoves(v).isEmpty() && degree.get(v) < K) {
                freezeWorkList.remove(v);
                simplifyWorkList.add(v);
            }
        });
    }

    void SelectSpill() {
        double min = Double.POSITIVE_INFINITY;
        ASMReg spill = null;
        for (var reg : spilledNodes) {
            if (degree.get(reg) != 0) {
                double val = (double) frequencyMap.get(reg) / degree.get(reg);
                if (val < min) {
                    min = val;
                    spill = reg;
                }
            }
        }

        spillWorkList.remove(spill);
        simplifyWorkList.add(spill);
        FreezeMoves(spill);
    }

    void AssignColors() {
        while (!selectStack.isEmpty()) {
            ASMReg n = selectStack.pop();
            LinkedList<ASMReg> okColors = new LinkedList<>();
//            for (int i = 0; i < 32; i++) {
//                if (!reservedColor.contains(i)) okColors.add(i);
//            }
            color.forEach(tmp -> {
                if (!reservedColor.contains(tmp)) {
                    if (tmp instanceof ASMPhysicalASMReg) okColors.addFirst(tmp);
                    else okColors.addLast(tmp);
                }
            });

            for (ASMReg w : Adjacent(n)) {
                if (coloredNodes.contains(GetAlias(w)) || preColored.contains(GetAlias(w))) {
                    okColors.remove(GetAlias(w).color);
                }
            }

            if (okColors.isEmpty()) {
                spilledNodes.add(n);
            } else {
                coloredNodes.add(n);
                n.color = okColors.get(0);
                n.color = okColors.get(0);
            }

            coalescedNodes.forEach(reg -> {
                reg.color = GetAlias(reg).color;
            });

        }
    }


    void RewriteProgram(ASMFunction function) {
        for (var n : spilledNodes) {
            assert n instanceof ASMVirtualReg;
            ASMVirtualReg vReg = (ASMVirtualReg) n;
            color.add(vReg);
            vReg.color = vReg;
        }
        for (var reg : color) {
            reg.stackOffset = new ASMStackOffset(
                    function.spilledRegCnt, ASMStackOffset.StackOffsetType.spillReg);
            function.spilledRegCnt++;
        }

        ArrayList<ASMReg> newTemps = new ArrayList<>();

        for (var block : function.blockList) {
            var iter = block.instList.listIterator();
            while (iter.hasNext()) {
                var inst = iter.next();
                iter.previous();
                for (ASMReg reg : inst.use) {
                    ASMReg alias = GetAlias(reg);
                    if (!spilledNodes.contains(alias)) {
                        if (!alias.equals(reg)) {
                            if (reg.equals(inst.rs1)) inst.rs1 = alias;
                            if (reg.equals(inst.rs2)) inst.rs2 = alias;
                            continue;
                        }
                    }

                    ASMVirtualReg tmpReg = new ASMVirtualReg(((ASMVirtualReg) reg).byteSize, "tmp");
                    newTemps.add(tmpReg);
                    ASMLoadInst loadInst = new ASMLoadInst(tmpReg.byteSize, tmpReg, ASMPhysicalASMReg.regMap.get("sp"), alias.color.stackOffset);
                    iter.add(loadInst);
                    if (reg.equals(inst.rs1)) inst.rs1 = alias;
                    if (reg.equals(inst.rs2)) inst.rs2 = alias;
                }

                iter.next();

                for (ASMReg reg : inst.def) {
                    ASMReg alias = GetAlias(reg);
                    if (!spilledNodes.contains(alias)) {
                        if (!alias.equals(reg)) {
                            if (reg.equals(inst.rd)) inst.rd = alias;
                            continue;
                        }
                    }

                    ASMVirtualReg tmpReg = new ASMVirtualReg(((ASMVirtualReg) reg).byteSize, "tmp");
                    newTemps.add(tmpReg);
                    ASMStoreInst storeInst = new ASMStoreInst(tmpReg.byteSize, tmpReg, ASMPhysicalASMReg.regMap.get("sp"), alias.color.stackOffset);
                    iter.add(storeInst);
                    if (reg.equals(inst.rd)) inst.rd = alias;
                }

            }
        }

        spilledNodes.clear();
        HashSet<ASMReg> newTempSet = new HashSet<>(newTemps);
        newTempSet.addAll(coloredNodes);
        newTempSet.addAll(coalescedNodes);
        newTempSet.addAll(newTemps);
        coloredNodes.clear();
        coalescedNodes.clear();

    }


}
