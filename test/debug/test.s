  .text
  .globl _init_func
  .type _init_func, @function
_init_func:
_init_func.entry:
         addi sp, sp, -4
         mv t0, ra
         sw t0, 0(sp)
         j _init_func.exit
_init_func.exit:
         mv ra, t0
         addi sp, sp, 4
         ret

  .text
  .globl vector.scalarInPlaceMultiply
  .type vector.scalarInPlaceMultiply, @function
vector.scalarInPlaceMultiply:
vector.scalarInPlaceMultiply.entry:
         addi sp, sp, -120
         mv t0, ra
         sw t0, 16(sp)
         sw a0, 12(sp)
         lw t0, 12(sp)
         sw t0, 20(sp)
         sw a1, 8(sp)
         slli t0, zero, 2
         sw t0, 24(sp)
         lw t0, 20(sp)
         lw t1, 24(sp)
         add t0, t0, t1
         sw t0, 28(sp)
         lw t0, 0(t0)
         sw t0, 32(sp)
         sub t0, t0, zero
         sw t0, 36(sp)
         seqz t0, t0
         sw t0, 40(sp)
         beq t0, zero, if_false.2
         j if_true.2
vector.scalarInPlaceMultiply.exit:
         lw t0, 4(sp)
         sw t0, 116(sp)
         mv a0, t0
         lw t0, 16(sp)
         mv ra, t0
         addi sp, sp, 120
         ret
if_true.2:
         sw zero, 4(sp)
         j vector.scalarInPlaceMultiply.exit
if_false.2:
         j if_exit.2
if_exit.2:
         sw zero, 0(sp)
         j for_cond.1
for_cond.1:
         lw t0, 0(sp)
         sw t0, 44(sp)
         lw t0, 20(sp)
         mv a0, t0
         call vector.getDim
         mv t0, a0
         sw t0, 48(sp)
         lw t0, 44(sp)
         lw t1, 48(sp)
         slt t0, t0, t1
         sw t0, 52(sp)
         beq t0, zero, for_exit.1
         j for_body.1
for_body.1:
         slli t0, zero, 2
         sw t0, 56(sp)
         lw t0, 20(sp)
         lw t1, 56(sp)
         add t0, t0, t1
         sw t0, 28(sp)
         lw t0, 0(t0)
         sw t0, 60(sp)
         lw t0, 0(sp)
         sw t0, 64(sp)
         slli t0, t0, 2
         sw t0, 68(sp)
         lw t0, 60(sp)
         lw t1, 68(sp)
         add t0, t0, t1
         sw t0, 72(sp)
         lw t0, 8(sp)
         sw t0, 76(sp)
         slli t0, zero, 2
         sw t0, 80(sp)
         lw t0, 20(sp)
         lw t1, 80(sp)
         add t0, t0, t1
         sw t0, 28(sp)
         lw t0, 0(t0)
         sw t0, 84(sp)
         lw t0, 0(sp)
         sw t0, 88(sp)
         slli t0, t0, 2
         sw t0, 92(sp)
         lw t0, 84(sp)
         lw t1, 92(sp)
         add t0, t0, t1
         sw t0, 96(sp)
         lw t0, 0(t0)
         sw t0, 100(sp)
         lw t0, 76(sp)
         lw t1, 100(sp)
         mul t0, t0, t1
         sw t0, 104(sp)
         lw t0, 72(sp)
         lw t1, 104(sp)
         sw t1, 0(t0)
         j for_step.1
for_step.1:
         lw t0, 0(sp)
         sw t0, 108(sp)
         addi t0, t0, 1
         sw t0, 112(sp)
         lw t1, 112(sp)
         sw t1, 0(sp)
         j for_cond.1
for_exit.1:
         lw t1, 20(sp)
         sw t1, 4(sp)
         j vector.scalarInPlaceMultiply.exit

  .text
  .globl main
  .type main, @function
main:
main.entry:
         addi sp, sp, -296
         mv t0, ra
         sw t0, 20(sp)
         call _init_func
         sw zero, 16(sp)
         li t0, 4
         sw t0, 24(sp)
         mv a0, t0
         call _malloc
         mv t0, a0
         sw t0, 28(sp)
         mv t0, t0
         sw t0, 32(sp)
         mv a0, t0
         call vector.vector
         lw t1, 32(sp)
         sw t1, 12(sp)
         li t0, 10
         sw t0, 40(sp)
         li t0, 4
         sw t0, 44(sp)
         lw t0, 40(sp)
         lw t1, 44(sp)
         mul t0, t0, t1
         sw t0, 36(sp)
         addi t0, t0, 4
         sw t0, 48(sp)
         mv a0, t0
         call _malloc
         mv t0, a0
         sw t0, 52(sp)
         mv t0, t0
         sw t0, 56(sp)
         li t0, 10
         sw t0, 60(sp)
         lw t0, 56(sp)
         lw t1, 60(sp)
         sw t1, 0(t0)
         li t0, 1
         sw t0, 68(sp)
         slli t0, t0, 2
         sw t0, 64(sp)
         lw t0, 56(sp)
         lw t1, 64(sp)
         add t0, t0, t1
         sw t0, 72(sp)
         mv t0, t0
         sw t0, 76(sp)
         lw t1, 76(sp)
         sw t1, 8(sp)
         sw zero, 4(sp)
         j for_cond.5
main.exit:
         lw t0, 16(sp)
         sw t0, 292(sp)
         mv a0, t0
         lw t0, 20(sp)
         mv ra, t0
         addi sp, sp, 296
         ret
for_cond.5:
         lw t0, 4(sp)
         sw t0, 80(sp)
         li t0, 10
         sw t0, 88(sp)
         lw t0, 80(sp)
         lw t1, 88(sp)
         slt t0, t0, t1
         sw t0, 84(sp)
         beq t0, zero, for_exit.5
         j for_body.5
for_body.5:
         lw t0, 8(sp)
         sw t0, 92(sp)
         lw t0, 4(sp)
         sw t0, 96(sp)
         slli t0, t0, 2
         sw t0, 100(sp)
         lw t0, 92(sp)
         lw t1, 100(sp)
         add t0, t0, t1
         sw t0, 104(sp)
         lw t0, 4(sp)
         sw t0, 108(sp)
         li t0, 9
         sw t0, 116(sp)
         lw t1, 108(sp)
         sub t0, t0, t1
         sw t0, 112(sp)
         lw t0, 104(sp)
         lw t1, 112(sp)
         sw t1, 0(t0)
         j for_step.5
for_step.5:
         lw t0, 4(sp)
         sw t0, 120(sp)
         addi t0, t0, 1
         sw t0, 124(sp)
         lw t1, 124(sp)
         sw t1, 4(sp)
         j for_cond.5
for_exit.5:
         lw t0, 12(sp)
         sw t0, 128(sp)
         lw t0, 8(sp)
         sw t0, 132(sp)
         lw t0, 128(sp)
         mv a0, t0
         lw t0, 132(sp)
         mv a1, t0
         call vector.init
         lui t0, %hi(strConst.3)
         sw t0, 136(sp)
         addi t0, t0, %lo(strConst.3)
         sw t0, 140(sp)
         mv a0, t0
         call print
         lw t0, 12(sp)
         sw t0, 144(sp)
         mv a0, t0
         call vector.tostring
         mv t0, a0
         sw t0, 148(sp)
         mv a0, t0
         call println
         li t0, 4
         sw t0, 152(sp)
         mv a0, t0
         call _malloc
         mv t0, a0
         sw t0, 156(sp)
         mv t0, t0
         sw t0, 160(sp)
         mv a0, t0
         call vector.vector
         lw t1, 160(sp)
         sw t1, 0(sp)
         lw t0, 0(sp)
         sw t0, 164(sp)
         lw t0, 12(sp)
         sw t0, 168(sp)
         lw t0, 164(sp)
         mv a0, t0
         lw t0, 168(sp)
         mv a1, t0
         call vector.copy
         mv t0, a0
         sw t0, 172(sp)
         lw t0, 0(sp)
         sw t0, 176(sp)
         mv a0, t0
         li t0, 3
         sw t0, 180(sp)
         mv a1, t0
         li t0, 817
         sw t0, 184(sp)
         mv a2, t0
         call vector.set
         mv t0, a0
         sw t0, 188(sp)
         beq t0, zero, if_false.8
         j if_true.8
if_true.8:
         lui t0, %hi(strConst.4)
         sw t0, 192(sp)
         addi t0, t0, %lo(strConst.4)
         sw t0, 196(sp)
         mv a0, t0
         call println
         j if_exit.8
if_false.8:
         j if_exit.8
if_exit.8:
         lui t0, %hi(strConst.5)
         sw t0, 200(sp)
         addi t0, t0, %lo(strConst.5)
         sw t0, 204(sp)
         mv a0, t0
         call print
         lw t0, 0(sp)
         sw t0, 208(sp)
         mv a0, t0
         call vector.tostring
         mv t0, a0
         sw t0, 212(sp)
         mv a0, t0
         call println
         lui t0, %hi(strConst.6)
         sw t0, 216(sp)
         addi t0, t0, %lo(strConst.6)
         sw t0, 220(sp)
         mv a0, t0
         call print
         lw t0, 12(sp)
         sw t0, 224(sp)
         lw t0, 0(sp)
         sw t0, 228(sp)
         lw t0, 224(sp)
         mv a0, t0
         lw t0, 228(sp)
         mv a1, t0
         call vector.add
         mv t0, a0
         sw t0, 232(sp)
         mv a0, t0
         call vector.tostring
         mv t0, a0
         sw t0, 236(sp)
         mv a0, t0
         call println
         lui t0, %hi(strConst.7)
         sw t0, 240(sp)
         addi t0, t0, %lo(strConst.7)
         sw t0, 244(sp)
         mv a0, t0
         call print
         lw t0, 12(sp)
         sw t0, 248(sp)
         lw t0, 0(sp)
         sw t0, 252(sp)
         lw t0, 248(sp)
         mv a0, t0
         lw t0, 252(sp)
         mv a1, t0
         call vector.dot
         mv t0, a0
         sw t0, 256(sp)
         mv a0, t0
         call toString
         mv t0, a0
         sw t0, 260(sp)
         mv a0, t0
         call println
         lui t0, %hi(strConst.8)
         sw t0, 264(sp)
         addi t0, t0, %lo(strConst.8)
         sw t0, 268(sp)
         mv a0, t0
         call print
         lw t0, 0(sp)
         sw t0, 272(sp)
         li t0, 1
         sw t0, 280(sp)
         slli t0, t0, 3
         sw t0, 276(sp)
         lw t0, 272(sp)
         mv a0, t0
         lw t0, 276(sp)
         mv a1, t0
         call vector.scalarInPlaceMultiply
         mv t0, a0
         sw t0, 284(sp)
         mv a0, t0
         call vector.tostring
         mv t0, a0
         sw t0, 288(sp)
         mv a0, t0
         call println
         sw zero, 16(sp)
         j main.exit

  .text
  .globl vector.vector
  .type vector.vector, @function
vector.vector:
vector.vector.entry:
         addi sp, sp, -12
         mv t0, ra
         sw t0, 4(sp)
         sw a0, 0(sp)
         lw t0, 0(sp)
         sw t0, 8(sp)
         j vector.vector.exit
vector.vector.exit:
         lw t0, 4(sp)
         mv ra, t0
         addi sp, sp, 12
         ret

  .text
  .globl vector.getDim
  .type vector.getDim, @function
vector.getDim:
vector.getDim.entry:
         addi sp, sp, -68
         mv t0, ra
         sw t0, 8(sp)
         sw a0, 4(sp)
         lw t0, 4(sp)
         sw t0, 12(sp)
         slli t0, zero, 2
         sw t0, 16(sp)
         lw t0, 12(sp)
         lw t1, 16(sp)
         add t0, t0, t1
         sw t0, 20(sp)
         lw t0, 0(t0)
         sw t0, 24(sp)
         sub t0, t0, zero
         sw t0, 28(sp)
         seqz t0, t0
         sw t0, 32(sp)
         beq t0, zero, if_false.1
         j if_true.1
vector.getDim.exit:
         lw t0, 0(sp)
         sw t0, 64(sp)
         mv a0, t0
         lw t0, 8(sp)
         mv ra, t0
         addi sp, sp, 68
         ret
if_true.1:
         sw zero, 0(sp)
         j vector.getDim.exit
if_false.1:
         j if_exit.1
if_exit.1:
         slli t0, zero, 2
         sw t0, 36(sp)
         lw t0, 12(sp)
         lw t1, 36(sp)
         add t0, t0, t1
         sw t0, 20(sp)
         lw t0, 0(t0)
         sw t0, 40(sp)
         mv t0, t0
         sw t0, 44(sp)
         li t0, -1
         sw t0, 52(sp)
         slli t0, t0, 2
         sw t0, 48(sp)
         lw t0, 44(sp)
         lw t1, 48(sp)
         add t0, t0, t1
         sw t0, 56(sp)
         lw t0, 0(t0)
         sw t0, 60(sp)
         lw t1, 60(sp)
         sw t1, 0(sp)
         j vector.getDim.exit

  .text
  .globl vector.dot
  .type vector.dot, @function
vector.dot:
vector.dot.entry:
         addi sp, sp, -120
         mv t0, ra
         sw t0, 20(sp)
         sw a0, 16(sp)
         lw t0, 16(sp)
         sw t0, 24(sp)
         sw a1, 12(sp)
         sw zero, 4(sp)
         sw zero, 0(sp)
         j while_cond
vector.dot.exit:
         lw t0, 8(sp)
         sw t0, 116(sp)
         mv a0, t0
         lw t0, 20(sp)
         mv ra, t0
         addi sp, sp, 120
         ret
while_cond:
         lw t0, 4(sp)
         sw t0, 28(sp)
         lw t0, 24(sp)
         mv a0, t0
         call vector.getDim
         mv t0, a0
         sw t0, 32(sp)
         lw t0, 28(sp)
         lw t1, 32(sp)
         slt t0, t0, t1
         sw t0, 36(sp)
         beq t0, zero, while_exit
         j while_body
while_body:
         slli t0, zero, 2
         sw t0, 40(sp)
         lw t0, 24(sp)
         lw t1, 40(sp)
         add t0, t0, t1
         sw t0, 44(sp)
         lw t0, 0(t0)
         sw t0, 48(sp)
         lw t0, 4(sp)
         sw t0, 52(sp)
         slli t0, t0, 2
         sw t0, 56(sp)
         lw t0, 48(sp)
         lw t1, 56(sp)
         add t0, t0, t1
         sw t0, 60(sp)
         lw t0, 0(t0)
         sw t0, 64(sp)
         lw t0, 12(sp)
         sw t0, 68(sp)
         slli t0, zero, 2
         sw t0, 72(sp)
         lw t0, 68(sp)
         lw t1, 72(sp)
         add t0, t0, t1
         sw t0, 76(sp)
         lw t0, 0(t0)
         sw t0, 80(sp)
         lw t0, 4(sp)
         sw t0, 84(sp)
         slli t0, t0, 2
         sw t0, 88(sp)
         lw t0, 80(sp)
         lw t1, 88(sp)
         add t0, t0, t1
         sw t0, 92(sp)
         lw t0, 0(t0)
         sw t0, 96(sp)
         lw t0, 64(sp)
         lw t1, 96(sp)
         mul t0, t0, t1
         sw t0, 100(sp)
         lw t1, 100(sp)
         sw t1, 0(sp)
         lw t0, 4(sp)
         sw t0, 104(sp)
         addi t0, t0, 1
         sw t0, 108(sp)
         lw t1, 108(sp)
         sw t1, 4(sp)
         j while_cond
while_exit:
         lw t0, 0(sp)
         sw t0, 112(sp)
         lw t1, 112(sp)
         sw t1, 8(sp)
         j vector.dot.exit

  .text
  .globl vector.init
  .type vector.init, @function
vector.init:
vector.init.entry:
         addi sp, sp, -180
         mv t0, ra
         sw t0, 12(sp)
         sw a0, 8(sp)
         lw t0, 8(sp)
         sw t0, 16(sp)
         sw a1, 4(sp)
         lw t0, 4(sp)
         sw t0, 20(sp)
         sub t0, t0, zero
         sw t0, 24(sp)
         seqz t0, t0
         sw t0, 28(sp)
         beq t0, zero, if_false
         j if_true
vector.init.exit:
         lw t0, 12(sp)
         mv ra, t0
         addi sp, sp, 180
         ret
if_true:
         j vector.init.exit
if_false:
         j if_exit
if_exit:
         slli t0, zero, 2
         sw t0, 32(sp)
         lw t0, 16(sp)
         lw t1, 32(sp)
         add t0, t0, t1
         sw t0, 36(sp)
         lw t0, 4(sp)
         sw t0, 40(sp)
         mv t0, t0
         sw t0, 44(sp)
         li t0, -1
         sw t0, 52(sp)
         slli t0, t0, 2
         sw t0, 48(sp)
         lw t0, 44(sp)
         lw t1, 48(sp)
         add t0, t0, t1
         sw t0, 56(sp)
         lw t0, 0(t0)
         sw t0, 60(sp)
         li t0, 4
         sw t0, 68(sp)
         lw t0, 60(sp)
         lw t1, 68(sp)
         mul t0, t0, t1
         sw t0, 64(sp)
         addi t0, t0, 4
         sw t0, 72(sp)
         mv a0, t0
         call _malloc
         mv t0, a0
         sw t0, 76(sp)
         mv t0, t0
         sw t0, 80(sp)
         lw t1, 60(sp)
         sw t1, 0(t0)
         li t0, 1
         sw t0, 88(sp)
         slli t0, t0, 2
         sw t0, 84(sp)
         lw t0, 80(sp)
         lw t1, 84(sp)
         add t0, t0, t1
         sw t0, 92(sp)
         mv t0, t0
         sw t0, 96(sp)
         lw t0, 36(sp)
         lw t1, 96(sp)
         sw t1, 0(t0)
         sw zero, 0(sp)
         j for_cond
for_cond:
         lw t0, 0(sp)
         sw t0, 100(sp)
         lw t0, 4(sp)
         sw t0, 104(sp)
         mv t0, t0
         sw t0, 108(sp)
         li t0, -1
         sw t0, 116(sp)
         slli t0, t0, 2
         sw t0, 112(sp)
         lw t0, 108(sp)
         lw t1, 112(sp)
         add t0, t0, t1
         sw t0, 120(sp)
         lw t0, 0(t0)
         sw t0, 124(sp)
         lw t0, 100(sp)
         lw t1, 124(sp)
         slt t0, t0, t1
         sw t0, 128(sp)
         beq t0, zero, for_exit
         j for_body
for_body:
         slli t0, zero, 2
         sw t0, 132(sp)
         lw t0, 16(sp)
         lw t1, 132(sp)
         add t0, t0, t1
         sw t0, 36(sp)
         lw t0, 0(t0)
         sw t0, 136(sp)
         lw t0, 0(sp)
         sw t0, 140(sp)
         slli t0, t0, 2
         sw t0, 144(sp)
         lw t0, 136(sp)
         lw t1, 144(sp)
         add t0, t0, t1
         sw t0, 148(sp)
         lw t0, 4(sp)
         sw t0, 152(sp)
         lw t0, 0(sp)
         sw t0, 156(sp)
         slli t0, t0, 2
         sw t0, 160(sp)
         lw t0, 152(sp)
         lw t1, 160(sp)
         add t0, t0, t1
         sw t0, 164(sp)
         lw t0, 0(t0)
         sw t0, 168(sp)
         lw t0, 148(sp)
         lw t1, 168(sp)
         sw t1, 0(t0)
         j for_step
for_step:
         lw t0, 0(sp)
         sw t0, 172(sp)
         addi t0, t0, 1
         sw t0, 176(sp)
         lw t1, 176(sp)
         sw t1, 0(sp)
         j for_cond
for_exit:
         j vector.init.exit

  .text
  .globl vector.add
  .type vector.add, @function
vector.add:
vector.add.entry:
         addi sp, sp, -280
         mv t0, ra
         sw t0, 24(sp)
         sw a0, 20(sp)
         lw t0, 20(sp)
         sw t0, 28(sp)
         sw a1, 16(sp)
         mv a0, t0
         call vector.getDim
         mv t0, a0
         sw t0, 32(sp)
         lw t0, 16(sp)
         sw t0, 36(sp)
         mv a0, t0
         call vector.getDim
         mv t0, a0
         sw t0, 40(sp)
         lw t0, 32(sp)
         lw t1, 40(sp)
         sub t0, t0, t1
         sw t0, 44(sp)
         snez t0, t0
         sw t0, 48(sp)
         andi t0, t0, 1
         sw t0, 52(sp)
         mv t0, t0
         sw t0, 56(sp)
         lw t1, 56(sp)
         sw t1, 8(sp)
         lw t0, 48(sp)
         beq t0, zero, noShortCutBlock
         j logicExitBlock
vector.add.exit:
         lw t0, 12(sp)
         sw t0, 276(sp)
         mv a0, t0
         lw t0, 24(sp)
         mv ra, t0
         addi sp, sp, 280
         ret
if_true.3:
         sw zero, 12(sp)
         j vector.add.exit
if_false.3:
         j if_exit.3
if_exit.3:
         li t0, 4
         sw t0, 60(sp)
         mv a0, t0
         call _malloc
         mv t0, a0
         sw t0, 64(sp)
         mv t0, t0
         sw t0, 68(sp)
         mv a0, t0
         call vector.vector
         lw t1, 68(sp)
         sw t1, 4(sp)
         lw t0, 4(sp)
         sw t0, 72(sp)
         slli t0, zero, 2
         sw t0, 76(sp)
         lw t0, 72(sp)
         lw t1, 76(sp)
         add t0, t0, t1
         sw t0, 80(sp)
         lw t0, 28(sp)
         mv a0, t0
         call vector.getDim
         mv t0, a0
         sw t0, 84(sp)
         li t0, 4
         sw t0, 92(sp)
         lw t0, 84(sp)
         lw t1, 92(sp)
         mul t0, t0, t1
         sw t0, 88(sp)
         addi t0, t0, 4
         sw t0, 96(sp)
         mv a0, t0
         call _malloc
         mv t0, a0
         sw t0, 100(sp)
         mv t0, t0
         sw t0, 104(sp)
         lw t1, 84(sp)
         sw t1, 0(t0)
         li t0, 1
         sw t0, 112(sp)
         slli t0, t0, 2
         sw t0, 108(sp)
         lw t0, 104(sp)
         lw t1, 108(sp)
         add t0, t0, t1
         sw t0, 116(sp)
         mv t0, t0
         sw t0, 120(sp)
         lw t0, 80(sp)
         lw t1, 120(sp)
         sw t1, 0(t0)
         sw zero, 0(sp)
         j for_cond.2
noShortCutBlock:
         lw t0, 28(sp)
         mv a0, t0
         call vector.getDim
         mv t0, a0
         sw t0, 124(sp)
         sub t0, t0, zero
         sw t0, 128(sp)
         seqz t0, t0
         sw t0, 132(sp)
         lw t0, 48(sp)
         lw t1, 132(sp)
         or t0, t0, t1
         sw t0, 136(sp)
         andi t0, t0, 1
         sw t0, 140(sp)
         mv t0, t0
         sw t0, 144(sp)
         lw t1, 144(sp)
         sw t1, 8(sp)
         j logicExitBlock
logicExitBlock:
         lw t0, 8(sp)
         sw t0, 148(sp)
         andi t0, t0, 1
         sw t0, 152(sp)
         mv t0, t0
         sw t0, 156(sp)
         beq t0, zero, if_false.3
         j if_true.3
for_cond.2:
         lw t0, 0(sp)
         sw t0, 160(sp)
         lw t0, 28(sp)
         mv a0, t0
         call vector.getDim
         mv t0, a0
         sw t0, 164(sp)
         lw t0, 160(sp)
         lw t1, 164(sp)
         slt t0, t0, t1
         sw t0, 168(sp)
         beq t0, zero, for_exit.2
         j for_body.2
for_body.2:
         lw t0, 4(sp)
         sw t0, 172(sp)
         slli t0, zero, 2
         sw t0, 176(sp)
         lw t0, 172(sp)
         lw t1, 176(sp)
         add t0, t0, t1
         sw t0, 180(sp)
         lw t0, 0(t0)
         sw t0, 184(sp)
         lw t0, 0(sp)
         sw t0, 188(sp)
         slli t0, t0, 2
         sw t0, 192(sp)
         lw t0, 184(sp)
         lw t1, 192(sp)
         add t0, t0, t1
         sw t0, 196(sp)
         slli t0, zero, 2
         sw t0, 200(sp)
         lw t0, 28(sp)
         lw t1, 200(sp)
         add t0, t0, t1
         sw t0, 204(sp)
         lw t0, 0(t0)
         sw t0, 208(sp)
         lw t0, 0(sp)
         sw t0, 212(sp)
         slli t0, t0, 2
         sw t0, 216(sp)
         lw t0, 208(sp)
         lw t1, 216(sp)
         add t0, t0, t1
         sw t0, 220(sp)
         lw t0, 0(t0)
         sw t0, 224(sp)
         lw t0, 16(sp)
         sw t0, 228(sp)
         slli t0, zero, 2
         sw t0, 232(sp)
         lw t0, 228(sp)
         lw t1, 232(sp)
         add t0, t0, t1
         sw t0, 236(sp)
         lw t0, 0(t0)
         sw t0, 240(sp)
         lw t0, 0(sp)
         sw t0, 244(sp)
         slli t0, t0, 2
         sw t0, 248(sp)
         lw t0, 240(sp)
         lw t1, 248(sp)
         add t0, t0, t1
         sw t0, 252(sp)
         lw t0, 0(t0)
         sw t0, 256(sp)
         lw t0, 224(sp)
         lw t1, 256(sp)
         add t0, t0, t1
         sw t0, 260(sp)
         lw t0, 196(sp)
         lw t1, 260(sp)
         sw t1, 0(t0)
         j for_step.2
for_step.2:
         lw t0, 0(sp)
         sw t0, 264(sp)
         addi t0, t0, 1
         sw t0, 268(sp)
         lw t1, 268(sp)
         sw t1, 0(sp)
         j for_cond.2
for_exit.2:
         lw t0, 4(sp)
         sw t0, 272(sp)
         lw t1, 272(sp)
         sw t1, 12(sp)
         j vector.add.exit

  .text
  .globl vector.copy
  .type vector.copy, @function
vector.copy:
vector.copy.entry:
         addi sp, sp, -212
         mv t0, ra
         sw t0, 16(sp)
         sw a0, 12(sp)
         lw t0, 12(sp)
         sw t0, 20(sp)
         sw a1, 8(sp)
         lw t0, 8(sp)
         sw t0, 24(sp)
         sub t0, t0, zero
         sw t0, 28(sp)
         seqz t0, t0
         sw t0, 32(sp)
         beq t0, zero, if_false.6
         j if_true.6
vector.copy.exit:
         lw t0, 4(sp)
         sw t0, 200(sp)
         andi t0, t0, 1
         sw t0, 204(sp)
         mv t0, t0
         sw t0, 208(sp)
         mv a0, t0
         lw t0, 16(sp)
         mv ra, t0
         addi sp, sp, 212
         ret
if_true.6:
         andi t0, zero, 1
         sw t0, 36(sp)
         mv t0, t0
         sw t0, 40(sp)
         lw t1, 40(sp)
         sw t1, 4(sp)
         j vector.copy.exit
if_false.6:
         j if_exit.6
if_exit.6:
         lw t0, 8(sp)
         sw t0, 44(sp)
         mv a0, t0
         call vector.getDim
         mv t0, a0
         sw t0, 48(sp)
         sub t0, t0, zero
         sw t0, 52(sp)
         seqz t0, t0
         sw t0, 56(sp)
         beq t0, zero, if_false.7
         j if_true.7
if_true.7:
         slli t0, zero, 2
         sw t0, 60(sp)
         lw t0, 20(sp)
         lw t1, 60(sp)
         add t0, t0, t1
         sw t0, 64(sp)
         sw zero, 0(t0)
         j if_exit.7
if_false.7:
         slli t0, zero, 2
         sw t0, 68(sp)
         lw t0, 20(sp)
         lw t1, 68(sp)
         add t0, t0, t1
         sw t0, 64(sp)
         lw t0, 8(sp)
         sw t0, 72(sp)
         mv a0, t0
         call vector.getDim
         mv t0, a0
         sw t0, 76(sp)
         li t0, 4
         sw t0, 84(sp)
         lw t0, 76(sp)
         lw t1, 84(sp)
         mul t0, t0, t1
         sw t0, 80(sp)
         addi t0, t0, 4
         sw t0, 88(sp)
         mv a0, t0
         call _malloc
         mv t0, a0
         sw t0, 92(sp)
         mv t0, t0
         sw t0, 96(sp)
         lw t1, 76(sp)
         sw t1, 0(t0)
         li t0, 1
         sw t0, 104(sp)
         slli t0, t0, 2
         sw t0, 100(sp)
         lw t0, 96(sp)
         lw t1, 100(sp)
         add t0, t0, t1
         sw t0, 108(sp)
         mv t0, t0
         sw t0, 112(sp)
         lw t0, 64(sp)
         lw t1, 112(sp)
         sw t1, 0(t0)
         sw zero, 0(sp)
         j for_cond.4
if_exit.7:
         li t0, 1
         sw t0, 120(sp)
         andi t0, t0, 1
         sw t0, 116(sp)
         mv t0, t0
         sw t0, 124(sp)
         lw t1, 124(sp)
         sw t1, 4(sp)
         j vector.copy.exit
for_cond.4:
         lw t0, 0(sp)
         sw t0, 128(sp)
         lw t0, 20(sp)
         mv a0, t0
         call vector.getDim
         mv t0, a0
         sw t0, 132(sp)
         lw t0, 128(sp)
         lw t1, 132(sp)
         slt t0, t0, t1
         sw t0, 136(sp)
         beq t0, zero, for_exit.4
         j for_body.4
for_body.4:
         slli t0, zero, 2
         sw t0, 140(sp)
         lw t0, 20(sp)
         lw t1, 140(sp)
         add t0, t0, t1
         sw t0, 64(sp)
         lw t0, 0(t0)
         sw t0, 144(sp)
         lw t0, 0(sp)
         sw t0, 148(sp)
         slli t0, t0, 2
         sw t0, 152(sp)
         lw t0, 144(sp)
         lw t1, 152(sp)
         add t0, t0, t1
         sw t0, 156(sp)
         lw t0, 8(sp)
         sw t0, 160(sp)
         slli t0, zero, 2
         sw t0, 164(sp)
         lw t0, 160(sp)
         lw t1, 164(sp)
         add t0, t0, t1
         sw t0, 168(sp)
         lw t0, 0(t0)
         sw t0, 172(sp)
         lw t0, 0(sp)
         sw t0, 176(sp)
         slli t0, t0, 2
         sw t0, 180(sp)
         lw t0, 172(sp)
         lw t1, 180(sp)
         add t0, t0, t1
         sw t0, 184(sp)
         lw t0, 0(t0)
         sw t0, 188(sp)
         lw t0, 156(sp)
         lw t1, 188(sp)
         sw t1, 0(t0)
         j for_step.4
for_step.4:
         lw t0, 0(sp)
         sw t0, 192(sp)
         addi t0, t0, 1
         sw t0, 196(sp)
         lw t1, 196(sp)
         sw t1, 0(sp)
         j for_cond.4
for_exit.4:
         j if_exit.7

  .text
  .globl vector.tostring
  .type vector.tostring, @function
vector.tostring:
vector.tostring.entry:
         addi sp, sp, -172
         mv t0, ra
         sw t0, 16(sp)
         sw a0, 12(sp)
         lw t0, 12(sp)
         sw t0, 20(sp)
         lui t0, %hi(strConst)
         sw t0, 24(sp)
         addi t0, t0, %lo(strConst)
         sw t0, 28(sp)
         lw t1, 28(sp)
         sw t1, 4(sp)
         lw t0, 20(sp)
         mv a0, t0
         call vector.getDim
         mv t0, a0
         sw t0, 32(sp)
         lw t1, 32(sp)
         slt t0, zero, t1
         sw t0, 36(sp)
         beq t0, zero, if_false.5
         j if_true.5
vector.tostring.exit:
         lw t0, 8(sp)
         sw t0, 168(sp)
         mv a0, t0
         lw t0, 16(sp)
         mv ra, t0
         addi sp, sp, 172
         ret
if_true.5:
         lw t0, 4(sp)
         sw t0, 40(sp)
         slli t0, zero, 2
         sw t0, 44(sp)
         lw t0, 20(sp)
         lw t1, 44(sp)
         add t0, t0, t1
         sw t0, 48(sp)
         lw t0, 0(t0)
         sw t0, 52(sp)
         slli t0, zero, 2
         sw t0, 56(sp)
         lw t0, 52(sp)
         lw t1, 56(sp)
         add t0, t0, t1
         sw t0, 60(sp)
         lw t0, 0(t0)
         sw t0, 64(sp)
         mv a0, t0
         call toString
         mv t0, a0
         sw t0, 68(sp)
         lw t0, 40(sp)
         mv a0, t0
         lw t0, 68(sp)
         mv a1, t0
         call _str_concat
         mv t0, a0
         sw t0, 72(sp)
         lw t1, 72(sp)
         sw t1, 4(sp)
         j if_exit.5
if_false.5:
         j if_exit.5
if_exit.5:
         li t0, 1
         sw t0, 76(sp)
         lw t1, 76(sp)
         sw t1, 0(sp)
         j for_cond.3
for_cond.3:
         lw t0, 0(sp)
         sw t0, 80(sp)
         lw t0, 20(sp)
         mv a0, t0
         call vector.getDim
         mv t0, a0
         sw t0, 84(sp)
         lw t0, 80(sp)
         lw t1, 84(sp)
         slt t0, t0, t1
         sw t0, 88(sp)
         beq t0, zero, for_exit.3
         j for_body.3
for_body.3:
         lw t0, 4(sp)
         sw t0, 92(sp)
         lui t0, %hi(strConst.1)
         sw t0, 96(sp)
         addi t0, t0, %lo(strConst.1)
         sw t0, 100(sp)
         lw t0, 92(sp)
         mv a0, t0
         lw t0, 100(sp)
         mv a1, t0
         call _str_concat
         mv t0, a0
         sw t0, 104(sp)
         slli t0, zero, 2
         sw t0, 108(sp)
         lw t0, 20(sp)
         lw t1, 108(sp)
         add t0, t0, t1
         sw t0, 48(sp)
         lw t0, 0(t0)
         sw t0, 112(sp)
         lw t0, 0(sp)
         sw t0, 116(sp)
         slli t0, t0, 2
         sw t0, 120(sp)
         lw t0, 112(sp)
         lw t1, 120(sp)
         add t0, t0, t1
         sw t0, 124(sp)
         lw t0, 0(t0)
         sw t0, 128(sp)
         mv a0, t0
         call toString
         mv t0, a0
         sw t0, 132(sp)
         lw t0, 104(sp)
         mv a0, t0
         lw t0, 132(sp)
         mv a1, t0
         call _str_concat
         mv t0, a0
         sw t0, 136(sp)
         lw t1, 136(sp)
         sw t1, 4(sp)
         j for_step.3
for_step.3:
         lw t0, 0(sp)
         sw t0, 140(sp)
         addi t0, t0, 1
         sw t0, 144(sp)
         lw t1, 144(sp)
         sw t1, 0(sp)
         j for_cond.3
for_exit.3:
         lw t0, 4(sp)
         sw t0, 148(sp)
         lui t0, %hi(strConst.2)
         sw t0, 152(sp)
         addi t0, t0, %lo(strConst.2)
         sw t0, 156(sp)
         lw t0, 148(sp)
         mv a0, t0
         lw t0, 156(sp)
         mv a1, t0
         call _str_concat
         mv t0, a0
         sw t0, 160(sp)
         lw t1, 160(sp)
         sw t1, 4(sp)
         lw t0, 4(sp)
         sw t0, 164(sp)
         lw t1, 164(sp)
         sw t1, 8(sp)
         j vector.tostring.exit

  .text
  .globl vector.set
  .type vector.set, @function
vector.set:
vector.set.entry:
         addi sp, sp, -96
         mv t0, ra
         sw t0, 16(sp)
         sw a0, 12(sp)
         lw t0, 12(sp)
         sw t0, 20(sp)
         sw a1, 8(sp)
         sw a2, 4(sp)
         mv a0, t0
         call vector.getDim
         mv t0, a0
         sw t0, 24(sp)
         lw t0, 8(sp)
         sw t0, 28(sp)
         lw t0, 24(sp)
         lw t1, 28(sp)
         slt t0, t0, t1
         sw t0, 32(sp)
         beq t0, zero, if_false.4
         j if_true.4
vector.set.exit:
         lw t0, 0(sp)
         sw t0, 84(sp)
         andi t0, t0, 1
         sw t0, 88(sp)
         mv t0, t0
         sw t0, 92(sp)
         mv a0, t0
         lw t0, 16(sp)
         mv ra, t0
         addi sp, sp, 96
         ret
if_true.4:
         andi t0, zero, 1
         sw t0, 36(sp)
         mv t0, t0
         sw t0, 40(sp)
         lw t1, 40(sp)
         sw t1, 0(sp)
         j vector.set.exit
if_false.4:
         j if_exit.4
if_exit.4:
         slli t0, zero, 2
         sw t0, 44(sp)
         lw t0, 20(sp)
         lw t1, 44(sp)
         add t0, t0, t1
         sw t0, 48(sp)
         lw t0, 0(t0)
         sw t0, 52(sp)
         lw t0, 8(sp)
         sw t0, 56(sp)
         slli t0, t0, 2
         sw t0, 60(sp)
         lw t0, 52(sp)
         lw t1, 60(sp)
         add t0, t0, t1
         sw t0, 64(sp)
         lw t0, 4(sp)
         sw t0, 68(sp)
         lw t0, 64(sp)
         lw t1, 68(sp)
         sw t1, 0(t0)
         li t0, 1
         sw t0, 76(sp)
         andi t0, t0, 1
         sw t0, 72(sp)
         mv t0, t0
         sw t0, 80(sp)
         lw t1, 80(sp)
         sw t1, 0(sp)
         j vector.set.exit

.section .bss
.section .rodata
 .type strConst.3, @object
strConst.3:
         .string "vector x: "
         .size strConst.3, 11


 .type strConst.5, @object
strConst.5:
         .string "vector y: "
         .size strConst.5, 11


 .type strConst.1, @object
strConst.1:
         .string ", "
         .size strConst.1, 3


 .type strConst.7, @object
strConst.7:
         .string "x * y: "
         .size strConst.7, 8


 .type strConst.4, @object
strConst.4:
         .string "excited!"
         .size strConst.4, 9


 .type strConst.6, @object
strConst.6:
         .string "x + y: "
         .size strConst.6, 8


 .type strConst, @object
strConst:
         .string "( "
         .size strConst, 3


 .type strConst.2, @object
strConst.2:
         .string " )"
         .size strConst.2, 3


 .type strConst.8, @object
strConst.8:
         .string "(1 << 3) * y: "
         .size strConst.8, 15



 		.text
       	.attribute	4, 16
       	.attribute	5, "rv32i2p0_m2p0"
       	.file	"builtin.c"
       	.globl	print
       	.p2align	2
       	.type	print,@function
       print:
       	addi	sp, sp, -16
       	sw	ra, 12(sp)
       	sw	s0, 8(sp)
       	addi	s0, sp, 16
       	sw	a0, -12(s0)
       	lw	a1, -12(s0)
       	lui	a0, %hi(.L.str)
       	addi	a0, a0, %lo(.L.str)
       	call	printf
       	lw	ra, 12(sp)
       	lw	s0, 8(sp)
       	addi	sp, sp, 16
       	ret
       .Lfunc_end0:
       	.size	print, .Lfunc_end0-print

       	.globl	println
       	.p2align	2
       	.type	println,@function
       println:
       	addi	sp, sp, -16
       	sw	ra, 12(sp)
       	sw	s0, 8(sp)
       	addi	s0, sp, 16
       	sw	a0, -12(s0)
       	lw	a1, -12(s0)
       	lui	a0, %hi(.L.str.1)
       	addi	a0, a0, %lo(.L.str.1)
       	call	printf
       	lw	ra, 12(sp)
       	lw	s0, 8(sp)
       	addi	sp, sp, 16
       	ret
       .Lfunc_end1:
       	.size	println, .Lfunc_end1-println

       	.globl	printInt
       	.p2align	2
       	.type	printInt,@function
       printInt:
       	addi	sp, sp, -16
       	sw	ra, 12(sp)
       	sw	s0, 8(sp)
       	addi	s0, sp, 16
       	sw	a0, -12(s0)
       	lw	a1, -12(s0)
       	lui	a0, %hi(.L.str.2)
       	addi	a0, a0, %lo(.L.str.2)
       	call	printf
       	lw	ra, 12(sp)
       	lw	s0, 8(sp)
       	addi	sp, sp, 16
       	ret
       .Lfunc_end2:
       	.size	printInt, .Lfunc_end2-printInt

       	.globl	printlnInt
       	.p2align	2
       	.type	printlnInt,@function
       printlnInt:
       	addi	sp, sp, -16
       	sw	ra, 12(sp)
       	sw	s0, 8(sp)
       	addi	s0, sp, 16
       	sw	a0, -12(s0)
       	lw	a1, -12(s0)
       	lui	a0, %hi(.L.str.3)
       	addi	a0, a0, %lo(.L.str.3)
       	call	printf
       	lw	ra, 12(sp)
       	lw	s0, 8(sp)
       	addi	sp, sp, 16
       	ret
       .Lfunc_end3:
       	.size	printlnInt, .Lfunc_end3-printlnInt

       	.globl	getString
       	.p2align	2
       	.type	getString,@function
       getString:
       	addi	sp, sp, -16
       	sw	ra, 12(sp)
       	sw	s0, 8(sp)
       	addi	s0, sp, 16
       	li	a0, 256
       	call	malloc
       	sw	a0, -12(s0)
       	lw	a1, -12(s0)
       	lui	a0, %hi(.L.str)
       	addi	a0, a0, %lo(.L.str)
       	call	scanf
       	lw	a0, -12(s0)
       	lw	ra, 12(sp)
       	lw	s0, 8(sp)
       	addi	sp, sp, 16
       	ret
       .Lfunc_end4:
       	.size	getString, .Lfunc_end4-getString

       	.globl	getInt
       	.p2align	2
       	.type	getInt,@function
       getInt:
       	addi	sp, sp, -16
       	sw	ra, 12(sp)
       	sw	s0, 8(sp)
       	addi	s0, sp, 16
       	lui	a0, %hi(.L.str.2)
       	addi	a0, a0, %lo(.L.str.2)
       	addi	a1, s0, -12
       	call	scanf
       	lw	a0, -12(s0)
       	lw	ra, 12(sp)
       	lw	s0, 8(sp)
       	addi	sp, sp, 16
       	ret
       .Lfunc_end5:
       	.size	getInt, .Lfunc_end5-getInt

       	.globl	toString
       	.p2align	2
       	.type	toString,@function
       toString:
       	addi	sp, sp, -16
       	sw	ra, 12(sp)
       	sw	s0, 8(sp)
       	addi	s0, sp, 16
       	sw	a0, -12(s0)
       	li	a0, 256
       	call	malloc
       	sw	a0, -16(s0)
       	lw	a0, -16(s0)
       	lw	a2, -12(s0)
       	lui	a1, %hi(.L.str.2)
       	addi	a1, a1, %lo(.L.str.2)
       	call	sprintf
       	lw	a0, -16(s0)
       	lw	ra, 12(sp)
       	lw	s0, 8(sp)
       	addi	sp, sp, 16
       	ret
       .Lfunc_end6:
       	.size	toString, .Lfunc_end6-toString

       	.globl	_str_substring
       	.p2align	2
       	.type	_str_substring,@function
       _str_substring:
       	addi	sp, sp, -32
       	sw	ra, 28(sp)
       	sw	s0, 24(sp)
       	addi	s0, sp, 32
       	sw	a0, -12(s0)
       	sw	a1, -16(s0)
       	sw	a2, -20(s0)
       	lw	a0, -20(s0)
       	lw	a1, -16(s0)
       	sub	a0, a0, a1
       	addi	a0, a0, 1
       	call	malloc
       	sw	a0, -24(s0)
       	lw	a0, -16(s0)
       	sw	a0, -28(s0)
       	j	.LBB7_1
       .LBB7_1:
       	lw	a0, -28(s0)
       	lw	a1, -20(s0)
       	bge	a0, a1, .LBB7_4
       	j	.LBB7_2
       .LBB7_2:
       	lw	a0, -12(s0)
       	lw	a2, -28(s0)
       	add	a0, a0, a2
       	lb	a0, 0(a0)
       	lw	a1, -24(s0)
       	lw	a3, -16(s0)
       	sub	a2, a2, a3
       	add	a1, a1, a2
       	sb	a0, 0(a1)
       	j	.LBB7_3
       .LBB7_3:
       	lw	a0, -28(s0)
       	addi	a0, a0, 1
       	sw	a0, -28(s0)
       	j	.LBB7_1
       .LBB7_4:
       	lw	a0, -24(s0)
       	lw	a1, -20(s0)
       	lw	a2, -16(s0)
       	sub	a1, a1, a2
       	add	a1, a0, a1
       	li	a0, 0
       	sb	a0, 0(a1)
       	lw	a0, -24(s0)
       	lw	ra, 28(sp)
       	lw	s0, 24(sp)
       	addi	sp, sp, 32
       	ret
       .Lfunc_end7:
       	.size	_str_substring, .Lfunc_end7-_str_substring

       	.globl	_str_length
       	.p2align	2
       	.type	_str_length,@function
       _str_length:
       	addi	sp, sp, -16
       	sw	ra, 12(sp)
       	sw	s0, 8(sp)
       	addi	s0, sp, 16
       	sw	a0, -12(s0)
       	lw	a0, -12(s0)
       	call	strlen
       	lw	ra, 12(sp)
       	lw	s0, 8(sp)
       	addi	sp, sp, 16
       	ret
       .Lfunc_end8:
       	.size	_str_length, .Lfunc_end8-_str_length

       	.globl	_str_ord
       	.p2align	2
       	.type	_str_ord,@function
       _str_ord:
       	addi	sp, sp, -16
       	sw	ra, 12(sp)
       	sw	s0, 8(sp)
       	addi	s0, sp, 16
       	sw	a0, -12(s0)
       	sw	a1, -16(s0)
       	lw	a0, -12(s0)
       	lw	a1, -16(s0)
       	add	a0, a0, a1
       	lbu	a0, 0(a0)
       	lw	ra, 12(sp)
       	lw	s0, 8(sp)
       	addi	sp, sp, 16
       	ret
       .Lfunc_end9:
       	.size	_str_ord, .Lfunc_end9-_str_ord

       	.globl	_str_parseInt
       	.p2align	2
       	.type	_str_parseInt,@function
       _str_parseInt:
       	addi	sp, sp, -16
       	sw	ra, 12(sp)
       	sw	s0, 8(sp)
       	addi	s0, sp, 16
       	sw	a0, -12(s0)
       	lw	a0, -12(s0)
       	lui	a1, %hi(.L.str.2)
       	addi	a1, a1, %lo(.L.str.2)
       	addi	a2, s0, -16
       	call	sscanf
       	lw	a0, -16(s0)
       	lw	ra, 12(sp)
       	lw	s0, 8(sp)
       	addi	sp, sp, 16
       	ret
       .Lfunc_end10:
       	.size	_str_parseInt, .Lfunc_end10-_str_parseInt

       	.globl	_malloc
       	.p2align	2
       	.type	_malloc,@function
       _malloc:
       	addi	sp, sp, -16
       	sw	ra, 12(sp)
       	sw	s0, 8(sp)
       	addi	s0, sp, 16
       	sw	a0, -12(s0)
       	lw	a0, -12(s0)
       	call	malloc
       	lw	ra, 12(sp)
       	lw	s0, 8(sp)
       	addi	sp, sp, 16
       	ret
       .Lfunc_end11:
       	.size	_malloc, .Lfunc_end11-_malloc

       	.globl	_str_concat
       	.p2align	2
       	.type	_str_concat,@function
       _str_concat:
       	addi	sp, sp, -32
       	sw	ra, 28(sp)
       	sw	s0, 24(sp)
       	addi	s0, sp, 32
       	sw	a0, -12(s0)
       	sw	a1, -16(s0)
       	lw	a0, -12(s0)
       	call	strlen
       	sw	a0, -24(s0)
       	lw	a0, -16(s0)
       	call	strlen
       	mv	a1, a0
       	lw	a0, -24(s0)
       	add	a0, a0, a1
       	addi	a0, a0, 1
       	call	malloc
       	sw	a0, -20(s0)
       	lw	a0, -20(s0)
       	lw	a1, -12(s0)
       	call	strcpy
       	lw	a0, -20(s0)
       	lw	a1, -16(s0)
       	call	strcat
       	lw	a0, -20(s0)
       	lw	ra, 28(sp)
       	lw	s0, 24(sp)
       	addi	sp, sp, 32
       	ret
       .Lfunc_end12:
       	.size	_str_concat, .Lfunc_end12-_str_concat

       	.globl	_str_eq
       	.p2align	2
       	.type	_str_eq,@function
       _str_eq:
       	addi	sp, sp, -16
       	sw	ra, 12(sp)
       	sw	s0, 8(sp)
       	addi	s0, sp, 16
       	sw	a0, -12(s0)
       	sw	a1, -16(s0)
       	lw	a0, -12(s0)
       	lw	a1, -16(s0)
       	call	strcmp
       	seqz	a0, a0
       	lw	ra, 12(sp)
       	lw	s0, 8(sp)
       	addi	sp, sp, 16
       	ret
       .Lfunc_end13:
       	.size	_str_eq, .Lfunc_end13-_str_eq

       	.globl	_str_ne
       	.p2align	2
       	.type	_str_ne,@function
       _str_ne:
       	addi	sp, sp, -16
       	sw	ra, 12(sp)
       	sw	s0, 8(sp)
       	addi	s0, sp, 16
       	sw	a0, -12(s0)
       	sw	a1, -16(s0)
       	lw	a0, -12(s0)
       	lw	a1, -16(s0)
       	call	strcmp
       	snez	a0, a0
       	lw	ra, 12(sp)
       	lw	s0, 8(sp)
       	addi	sp, sp, 16
       	ret
       .Lfunc_end14:
       	.size	_str_ne, .Lfunc_end14-_str_ne

       	.globl	_str_ult
       	.p2align	2
       	.type	_str_ult,@function
       _str_ult:
       	addi	sp, sp, -16
       	sw	ra, 12(sp)
       	sw	s0, 8(sp)
       	addi	s0, sp, 16
       	sw	a0, -12(s0)
       	sw	a1, -16(s0)
       	lw	a0, -12(s0)
       	lw	a1, -16(s0)
       	call	strcmp
       	srli	a0, a0, 31
       	lw	ra, 12(sp)
       	lw	s0, 8(sp)
       	addi	sp, sp, 16
       	ret
       .Lfunc_end15:
       	.size	_str_ult, .Lfunc_end15-_str_ult

       	.globl	_str_ugt
       	.p2align	2
       	.type	_str_ugt,@function
       _str_ugt:
       	addi	sp, sp, -16
       	sw	ra, 12(sp)
       	sw	s0, 8(sp)
       	addi	s0, sp, 16
       	sw	a0, -12(s0)
       	sw	a1, -16(s0)
       	lw	a0, -12(s0)
       	lw	a1, -16(s0)
       	call	strcmp
       	mv	a1, a0
       	li	a0, 0
       	slt	a0, a0, a1
       	lw	ra, 12(sp)
       	lw	s0, 8(sp)
       	addi	sp, sp, 16
       	ret
       .Lfunc_end16:
       	.size	_str_ugt, .Lfunc_end16-_str_ugt

       	.globl	_str_ule
       	.p2align	2
       	.type	_str_ule,@function
       _str_ule:
       	addi	sp, sp, -16
       	sw	ra, 12(sp)
       	sw	s0, 8(sp)
       	addi	s0, sp, 16
       	sw	a0, -12(s0)
       	sw	a1, -16(s0)
       	lw	a0, -12(s0)
       	lw	a1, -16(s0)
       	call	strcmp
       	slti	a0, a0, 1
       	lw	ra, 12(sp)
       	lw	s0, 8(sp)
       	addi	sp, sp, 16
       	ret
       .Lfunc_end17:
       	.size	_str_ule, .Lfunc_end17-_str_ule

       	.globl	_str_uge
       	.p2align	2
       	.type	_str_uge,@function
       _str_uge:
       	addi	sp, sp, -16
       	sw	ra, 12(sp)
       	sw	s0, 8(sp)
       	addi	s0, sp, 16
       	sw	a0, -12(s0)
       	sw	a1, -16(s0)
       	lw	a0, -12(s0)
       	lw	a1, -16(s0)
       	call	strcmp
       	not	a0, a0
       	srli	a0, a0, 31
       	lw	ra, 12(sp)
       	lw	s0, 8(sp)
       	addi	sp, sp, 16
       	ret
       .Lfunc_end18:
       	.size	_str_uge, .Lfunc_end18-_str_uge

       	.type	STR_BUF_SIZE,@object
       	.section	.rodata,"a",@progbits
       	.globl	STR_BUF_SIZE
       	.p2align	2
       STR_BUF_SIZE:
       	.word	256
       	.size	STR_BUF_SIZE, 4

       	.type	INT_BUF_SIZE,@object
       	.globl	INT_BUF_SIZE
       	.p2align	2
       INT_BUF_SIZE:
       	.word	20
       	.size	INT_BUF_SIZE, 4

       	.type	.L.str,@object
       	.section	.rodata.str1.1,"aMS",@progbits,1
       .L.str:
       	.asciz	"%s"
       	.size	.L.str, 3

       	.type	.L.str.1,@object
       .L.str.1:
       	.asciz	"%s\n"
       	.size	.L.str.1, 4

       	.type	.L.str.2,@object
       .L.str.2:
       	.asciz	"%d"
       	.size	.L.str.2, 3

       	.type	.L.str.3,@object
       .L.str.3:
       	.asciz	"%d\n"
       	.size	.L.str.3, 4

       	.ident	"Ubuntu clang version 14.0.0-1ubuntu1"
       	.section	".note.GNU-stack","",@progbits
       	.addrsig
       	.addrsig_sym printf
       	.addrsig_sym malloc
       	.addrsig_sym scanf
       	.addrsig_sym sprintf
       	.addrsig_sym strlen
       	.addrsig_sym sscanf
       	.addrsig_sym strcpy
       	.addrsig_sym strcat
       	.addrsig_sym strcmp

