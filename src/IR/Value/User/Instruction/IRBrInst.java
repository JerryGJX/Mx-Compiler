package IR.Value.User.Instruction;


import IR.IRVisitor;
import IR.Type.VoidType;
import IR.Value.IRBasicBlock;
import IR.Value.IRValue;

public class IRBrInst extends IRInstruction {

//    br i1 <cond>, label <iftrue>, label <iffalse>		;条件跳转
//    br label <dest>          ;无条件跳转

    public IRBrInst(IRBasicBlock _targetBlock, IRBasicBlock _parentBlock) {
        super(LLVM_BR_INST,new VoidType(),_parentBlock);
        this.addOperand(_targetBlock);
    }

    public IRBrInst(IRValue _condition, IRBasicBlock _ifTrueBlock, IRBasicBlock _ifFalseBlock, IRBasicBlock _parentBlock) {
        super(LLVM_BR_INST,new VoidType(),_parentBlock);
        this.addOperand(_condition);
        this.addOperand(_ifTrueBlock);
        this.addOperand(_ifFalseBlock);
    }

    public boolean ifCondition() {
        return this.operandSize() == 3;
    }

    public IRBasicBlock targetBlock() {
        if(this.ifCondition())throw new RuntimeException("targetBlock() called on a conditional branch");
        return (IRBasicBlock) this.getOperand(0);
    }

    public IRValue condition() {
        if(!this.ifCondition())throw new RuntimeException("condition() called on a unconditional branch");
        return this.getOperand(0);
    }

    public IRBasicBlock ifTrueBlock() {
        if(!this.ifCondition())throw new RuntimeException("ifTrueBlock() called on a unconditional branch");
        return (IRBasicBlock) this.getOperand(1);
    }

    public IRBasicBlock ifFalseBlock() {
        if(!this.ifCondition())throw new RuntimeException("ifFalseBlock() called on a unconditional branch");
        return (IRBasicBlock) this.getOperand(2);
    }

    @Override
    public String printInst() {
        String Ans = LLVM_BR_INST;
        if(this.ifCondition()){
            Ans += " " + this.condition().valueType + " " + this.condition().getIdentifier()
                    + ", " +LLVM_LABEL+ " " + this.ifTrueBlock().GetBlockId()
                    + ", " + LLVM_LABEL+ " " + this.ifFalseBlock().GetBlockId();
        }else{
            Ans += " " + LLVM_LABEL + " " + this.targetBlock().GetBlockId();
        }
        return Ans;
    }

    @Override
    public void accept(IRVisitor _visitor) {
        _visitor.visit(this);
    }
}
