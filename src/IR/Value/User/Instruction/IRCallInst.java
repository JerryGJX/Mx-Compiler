package IR.Value.User.Instruction;

import IR.IRVisitor;
import IR.Type.VoidType;
import IR.Value.IRBasicBlock;
import IR.Value.IRValue;
import IR.Value.User.Constant.GlobalValue.IRFunction;

import java.util.ArrayList;

public class IRCallInst extends IRInstruction {
    //<result> = call  <fnptrval>(<function args>) [fn attrs] [ operand bundles ]
    public String funcName;

    public IRCallInst(String _valueName, IRFunction _calledFunc, IRBasicBlock _parentBlock, ArrayList<IRValue> argsList) {
        super(_valueName, _calledFunc.returnType, _parentBlock);
        this.addOperand(_calledFunc);
        if (argsList != null) {
            for (IRValue arg : argsList) {
                this.addOperand(arg);
            }
        }
    }

    public IRCallInst(String _valueName, IRFunction _calledFunc, IRBasicBlock _parentBlock, IRValue... argsList) {
        super(_valueName, _calledFunc.returnType, _parentBlock);
        this.addOperand(_calledFunc);
        if (argsList != null) {
            for (IRValue arg : argsList) {
                this.addOperand(arg);
            }
        }
    }

    public IRFunction calledFunc() {
        return (IRFunction) this.getOperand(0);
    }

    public IRValue args(Integer _rank) {
        if (_rank > this.operandSize()) throw new Error("IRCallInst.args() error");
        return this.getOperand(_rank + 1);
    }

    @Override
    public String printInst() {
        //for void: call void @print(i32 1)
        //%funcName.call = call i32 @funcName(i32 %a, i32 %b)
        StringBuilder Ans;
        if (this.valueType instanceof VoidType) {
            Ans = new StringBuilder(LLVM_CALL_INST + " " + this.valueType + " " + this.calledFunc().getIdentifier() + "(");
        } else {
            Ans = new StringBuilder(this.getIdentifier() + " = " + LLVM_CALL_INST + " " + this.valueType + " " + this.calledFunc().getIdentifier() + "(");
        }
        for (int i = 0; i < this.operandSize() - 1; ++i) {
            Ans.append(this.args(i).valueType).append(" ").append(this.args(i).getIdentifier());
            if (i != this.operandSize() - 2) Ans.append(", ");
        }
        Ans.append(")");
        return Ans.toString();
    }


    @Override
    public void accept(IRVisitor _visitor) {
        _visitor.visit(this);
    }
}
