  .text
  .globl _init_func
  .type _init_func, @function
_init_func:
_init_func.entry:
         addi sp, sp, -216
         mv t0, ra
         sw t0, 0(sp)
         li t0, 1001
         sw t0, 8(sp)
         li t0, 4
         sw t0, 12(sp)
         lw t0, 8(sp)
         lw t1, 12(sp)
         mul t0, t0, t1
         sw t0, 4(sp)
         lw t0, 4(sp)
         addi t0, t0, 4
         sw t0, 16(sp)
         li t0, 4
         sw t0, 20(sp)
         lw t0, 4(sp)
         lw t1, 20(sp)
         add t0, t0, t1
         sw t0, 16(sp)
         lw t0, 16(sp)
         mv a0, t0
         call _malloc
         mv t0, a0
         sw t0, 24(sp)
         lw t0, 24(sp)
         mv t0, t0
         sw t0, 28(sp)
         li t0, 1001
         sw t0, 32(sp)
         lw t0, 28(sp)
         lw t1, 32(sp)
         sw t1, 0(t0)
         li t0, 1
         sw t0, 40(sp)
         lw t0, 40(sp)
         slli t0, t0, 2
         sw t0, 36(sp)
         lw t0, 28(sp)
         lw t1, 36(sp)
         add t0, t0, t1
         sw t0, 44(sp)
         lw t0, 44(sp)
         mv t0, t0
         sw t0, 48(sp)
         lui t0, %hi(b)
         sw t0, 52(sp)
         lw t0, 52(sp)
         lw t1, 48(sp)
         sw t1, %lo(b)(t0)
         li t0, 170
         sw t0, 60(sp)
         li t0, 4
         sw t0, 64(sp)
         lw t0, 60(sp)
         lw t1, 64(sp)
         mul t0, t0, t1
         sw t0, 56(sp)
         lw t0, 56(sp)
         addi t0, t0, 4
         sw t0, 68(sp)
         li t0, 4
         sw t0, 72(sp)
         lw t0, 56(sp)
         lw t1, 72(sp)
         add t0, t0, t1
         sw t0, 68(sp)
         lw t0, 68(sp)
         mv a0, t0
         call _malloc
         mv t0, a0
         sw t0, 76(sp)
         lw t0, 76(sp)
         mv t0, t0
         sw t0, 80(sp)
         li t0, 170
         sw t0, 84(sp)
         lw t0, 80(sp)
         lw t1, 84(sp)
         sw t1, 0(t0)
         li t0, 1
         sw t0, 92(sp)
         lw t0, 92(sp)
         slli t0, t0, 2
         sw t0, 88(sp)
         lw t0, 80(sp)
         lw t1, 88(sp)
         add t0, t0, t1
         sw t0, 96(sp)
         lw t0, 96(sp)
         mv t0, t0
         sw t0, 100(sp)
         lui t0, %hi(prime)
         sw t0, 104(sp)
         lw t0, 104(sp)
         lw t1, 100(sp)
         sw t1, %lo(prime)(t0)
         li t0, 1001
         sw t0, 112(sp)
         li t0, 4
         sw t0, 116(sp)
         lw t0, 112(sp)
         lw t1, 116(sp)
         mul t0, t0, t1
         sw t0, 108(sp)
         lw t0, 108(sp)
         addi t0, t0, 4
         sw t0, 120(sp)
         li t0, 4
         sw t0, 124(sp)
         lw t0, 108(sp)
         lw t1, 124(sp)
         add t0, t0, t1
         sw t0, 120(sp)
         lw t0, 120(sp)
         mv a0, t0
         call _malloc
         mv t0, a0
         sw t0, 128(sp)
         lw t0, 128(sp)
         mv t0, t0
         sw t0, 132(sp)
         li t0, 1001
         sw t0, 136(sp)
         lw t0, 132(sp)
         lw t1, 136(sp)
         sw t1, 0(t0)
         li t0, 1
         sw t0, 144(sp)
         lw t0, 144(sp)
         slli t0, t0, 2
         sw t0, 140(sp)
         lw t0, 132(sp)
         lw t1, 140(sp)
         add t0, t0, t1
         sw t0, 148(sp)
         lw t0, 148(sp)
         mv t0, t0
         sw t0, 152(sp)
         lui t0, %hi(gps)
         sw t0, 156(sp)
         lw t0, 156(sp)
         lw t1, 152(sp)
         sw t1, %lo(gps)(t0)
         li t0, 1
         sw t0, 164(sp)
         li t0, 4
         sw t0, 168(sp)
         lw t0, 164(sp)
         lw t1, 168(sp)
         mul t0, t0, t1
         sw t0, 160(sp)
         lw t0, 160(sp)
         addi t0, t0, 4
         sw t0, 172(sp)
         li t0, 4
         sw t0, 176(sp)
         lw t0, 160(sp)
         lw t1, 176(sp)
         add t0, t0, t1
         sw t0, 172(sp)
         lw t0, 172(sp)
         mv a0, t0
         call _malloc
         mv t0, a0
         sw t0, 180(sp)
         lw t0, 180(sp)
         mv t0, t0
         sw t0, 184(sp)
         li t0, 1
         sw t0, 188(sp)
         lw t0, 184(sp)
         lw t1, 188(sp)
         sw t1, 0(t0)
         li t0, 1
         sw t0, 196(sp)
         lw t0, 196(sp)
         slli t0, t0, 2
         sw t0, 192(sp)
         lw t0, 184(sp)
         lw t1, 192(sp)
         add t0, t0, t1
         sw t0, 200(sp)
         lw t0, 200(sp)
         mv t0, t0
         sw t0, 204(sp)
         lui t0, %hi(tmp)
         sw t0, 208(sp)
         lw t0, 208(sp)
         lw t1, 204(sp)
         sw t1, %lo(tmp)(t0)
         lui t0, %hi(result)
         sw t0, 212(sp)
         lw t0, 212(sp)
         sw zero, %lo(result)(t0)
         j _init_func.exit
_init_func.exit:
         lw t0, 0(sp)
         mv ra, t0
         addi sp, sp, 216
         ret

  .text
  .globl origin
  .type origin, @function
origin:
origin.entry:
         addi sp, sp, -248
         mv t0, ra
         sw t0, 4(sp)
         sw a0, 0(sp)
         lw t0, 0(sp)
         sw t0, 8(sp)
         li t0, 4
         sw t0, 16(sp)
         lw t0, 8(sp)
         lw t1, 16(sp)
         mul t0, t0, t1
         sw t0, 12(sp)
         lw t0, 12(sp)
         addi t0, t0, 4
         sw t0, 20(sp)
         li t0, 4
         sw t0, 24(sp)
         lw t0, 12(sp)
         lw t1, 24(sp)
         add t0, t0, t1
         sw t0, 20(sp)
         lw t0, 20(sp)
         mv a0, t0
         call _malloc
         mv t0, a0
         sw t0, 28(sp)
         lw t0, 28(sp)
         mv t0, t0
         sw t0, 32(sp)
         lw t0, 32(sp)
         lw t1, 8(sp)
         sw t1, 0(t0)
         li t0, 1
         sw t0, 40(sp)
         lw t0, 40(sp)
         slli t0, t0, 2
         sw t0, 36(sp)
         lw t0, 32(sp)
         lw t1, 36(sp)
         add t0, t0, t1
         sw t0, 44(sp)
         lw t0, 44(sp)
         mv t0, t0
         sw t0, 48(sp)
         lui t0, %hi(result)
         sw t0, 52(sp)
         lw t0, 52(sp)
         lw t1, 48(sp)
         sw t1, %lo(result)(t0)
         lui t0, %hi(i)
         sw t0, 56(sp)
         lw t0, 56(sp)
         sw zero, %lo(i)(t0)
         j for_cond
origin.exit:
         lw t0, 4(sp)
         mv ra, t0
         addi sp, sp, 248
         ret
for_cond:
         lui t0, %hi(i)
         sw t0, 64(sp)
         lw t0, 64(sp)
         lw t0, %lo(i)(t0)
         sw t0, 60(sp)
         lw t0, 0(sp)
         sw t0, 68(sp)
         lw t0, 60(sp)
         lw t1, 68(sp)
         slt t0, t0, t1
         sw t0, 72(sp)
         lw t0, 72(sp)
         beq t0, zero, for_exit
         j for_body
for_body:
         lui t0, %hi(result)
         sw t0, 80(sp)
         lw t0, 80(sp)
         lw t0, %lo(result)(t0)
         sw t0, 76(sp)
         lui t0, %hi(i)
         sw t0, 88(sp)
         lw t0, 88(sp)
         lw t0, %lo(i)(t0)
         sw t0, 84(sp)
         lw t0, 84(sp)
         slli t0, t0, 2
         sw t0, 92(sp)
         lw t0, 76(sp)
         lw t1, 92(sp)
         add t0, t0, t1
         sw t0, 96(sp)
         lw t0, 0(sp)
         sw t0, 100(sp)
         li t0, 4
         sw t0, 108(sp)
         lw t0, 100(sp)
         lw t1, 108(sp)
         mul t0, t0, t1
         sw t0, 104(sp)
         lw t0, 104(sp)
         addi t0, t0, 4
         sw t0, 112(sp)
         li t0, 4
         sw t0, 116(sp)
         lw t0, 104(sp)
         lw t1, 116(sp)
         add t0, t0, t1
         sw t0, 112(sp)
         lw t0, 112(sp)
         mv a0, t0
         call _malloc
         mv t0, a0
         sw t0, 120(sp)
         lw t0, 120(sp)
         mv t0, t0
         sw t0, 124(sp)
         lw t0, 124(sp)
         lw t1, 100(sp)
         sw t1, 0(t0)
         li t0, 1
         sw t0, 132(sp)
         lw t0, 132(sp)
         slli t0, t0, 2
         sw t0, 128(sp)
         lw t0, 124(sp)
         lw t1, 128(sp)
         add t0, t0, t1
         sw t0, 136(sp)
         lw t0, 136(sp)
         mv t0, t0
         sw t0, 140(sp)
         lw t0, 96(sp)
         lw t1, 140(sp)
         sw t1, 0(t0)
         lui t0, %hi(j)
         sw t0, 144(sp)
         lw t0, 144(sp)
         sw zero, %lo(j)(t0)
         j for_cond.1
for_step:
         lui t0, %hi(i)
         sw t0, 152(sp)
         lw t0, 152(sp)
         lw t0, %lo(i)(t0)
         sw t0, 148(sp)
         lw t0, 148(sp)
         addi t0, t0, 1
         sw t0, 156(sp)
         li t0, 1
         sw t0, 160(sp)
         lw t0, 148(sp)
         lw t1, 160(sp)
         add t0, t0, t1
         sw t0, 156(sp)
         lui t0, %hi(i)
         sw t0, 164(sp)
         lw t0, 164(sp)
         lw t1, 156(sp)
         sw t1, %lo(i)(t0)
         j for_cond
for_exit:
         j origin.exit
for_cond.1:
         lui t0, %hi(j)
         sw t0, 172(sp)
         lw t0, 172(sp)
         lw t0, %lo(j)(t0)
         sw t0, 168(sp)
         lw t0, 0(sp)
         sw t0, 176(sp)
         lw t0, 168(sp)
         lw t1, 176(sp)
         slt t0, t0, t1
         sw t0, 180(sp)
         lw t0, 180(sp)
         beq t0, zero, for_exit.1
         j for_body.1
for_body.1:
         lui t0, %hi(result)
         sw t0, 188(sp)
         lw t0, 188(sp)
         lw t0, %lo(result)(t0)
         sw t0, 184(sp)
         lui t0, %hi(i)
         sw t0, 196(sp)
         lw t0, 196(sp)
         lw t0, %lo(i)(t0)
         sw t0, 192(sp)
         lw t0, 192(sp)
         slli t0, t0, 2
         sw t0, 200(sp)
         lw t0, 184(sp)
         lw t1, 200(sp)
         add t0, t0, t1
         sw t0, 204(sp)
         lw t0, 204(sp)
         lw t0, 0(t0)
         sw t0, 208(sp)
         lui t0, %hi(j)
         sw t0, 216(sp)
         lw t0, 216(sp)
         lw t0, %lo(j)(t0)
         sw t0, 212(sp)
         lw t0, 212(sp)
         slli t0, t0, 2
         sw t0, 220(sp)
         lw t0, 208(sp)
         lw t1, 220(sp)
         add t0, t0, t1
         sw t0, 224(sp)
         lw t0, 224(sp)
         sw zero, 0(t0)
         j for_step.1
for_step.1:
         lui t0, %hi(j)
         sw t0, 232(sp)
         lw t0, 232(sp)
         lw t0, %lo(j)(t0)
         sw t0, 228(sp)
         lw t0, 228(sp)
         addi t0, t0, 1
         sw t0, 236(sp)
         li t0, 1
         sw t0, 240(sp)
         lw t0, 228(sp)
         lw t1, 240(sp)
         add t0, t0, t1
         sw t0, 236(sp)
         lui t0, %hi(j)
         sw t0, 244(sp)
         lw t0, 244(sp)
         lw t1, 236(sp)
         sw t1, %lo(j)(t0)
         j for_cond.1
for_exit.1:
         j for_step

  .text
  .globl main
  .type main, @function
main:
main.entry:
         addi sp, sp, -860
         mv t0, ra
         sw t0, 4(sp)
         call _init_func
         sw zero, 0(sp)
         li t0, 170
         sw t0, 8(sp)
         lw t0, 8(sp)
         mv a0, t0
         call origin
         li t0, 1000
         sw t0, 12(sp)
         lui t0, %hi(N)
         sw t0, 16(sp)
         lw t0, 16(sp)
         lw t1, 12(sp)
         sw t1, %lo(N)(t0)
         call getInt
         mv t0, a0
         sw t0, 20(sp)
         lui t0, %hi(M)
         sw t0, 24(sp)
         lw t0, 24(sp)
         lw t1, 20(sp)
         sw t1, %lo(M)(t0)
         lui t0, %hi(primeCount)
         sw t0, 28(sp)
         lw t0, 28(sp)
         sw zero, %lo(primeCount)(t0)
         lui t0, %hi(resultCount)
         sw t0, 32(sp)
         lw t0, 32(sp)
         sw zero, %lo(resultCount)(t0)
         lui t0, %hi(tmp)
         sw t0, 40(sp)
         lw t0, 40(sp)
         lw t0, %lo(tmp)(t0)
         sw t0, 36(sp)
         slli t0, zero, 2
         sw t0, 44(sp)
         lw t0, 36(sp)
         lw t1, 44(sp)
         add t0, t0, t1
         sw t0, 48(sp)
         lw t0, 48(sp)
         sw zero, 0(t0)
         lui t0, %hi(i)
         sw t0, 52(sp)
         lw t0, 52(sp)
         sw zero, %lo(i)(t0)
         j for_cond.3
main.exit:
         lw t0, 0(sp)
         sw t0, 856(sp)
         lw t0, 856(sp)
         mv a0, t0
         lw t0, 4(sp)
         mv ra, t0
         addi sp, sp, 860
         ret
for_cond.3:
         lui t0, %hi(i)
         sw t0, 60(sp)
         lw t0, 60(sp)
         lw t0, %lo(i)(t0)
         sw t0, 56(sp)
         lui t0, %hi(N)
         sw t0, 68(sp)
         lw t0, 68(sp)
         lw t0, %lo(N)(t0)
         sw t0, 64(sp)
         lw t0, 64(sp)
         addi t0, t0, 1
         sw t0, 72(sp)
         li t0, 1
         sw t0, 76(sp)
         lw t0, 64(sp)
         lw t1, 76(sp)
         add t0, t0, t1
         sw t0, 72(sp)
         lw t0, 56(sp)
         lw t1, 72(sp)
         slt t0, t0, t1
         sw t0, 80(sp)
         lw t0, 80(sp)
         beq t0, zero, for_exit.3
         j for_body.3
for_body.3:
         lui t0, %hi(b)
         sw t0, 88(sp)
         lw t0, 88(sp)
         lw t0, %lo(b)(t0)
         sw t0, 84(sp)
         lui t0, %hi(i)
         sw t0, 96(sp)
         lw t0, 96(sp)
         lw t0, %lo(i)(t0)
         sw t0, 92(sp)
         lw t0, 92(sp)
         slli t0, t0, 2
         sw t0, 100(sp)
         lw t0, 84(sp)
         lw t1, 100(sp)
         add t0, t0, t1
         sw t0, 104(sp)
         li t0, 1
         sw t0, 108(sp)
         lw t0, 104(sp)
         lw t1, 108(sp)
         sw t1, 0(t0)
         lui t0, %hi(gps)
         sw t0, 116(sp)
         lw t0, 116(sp)
         lw t0, %lo(gps)(t0)
         sw t0, 112(sp)
         lui t0, %hi(i)
         sw t0, 124(sp)
         lw t0, 124(sp)
         lw t0, %lo(i)(t0)
         sw t0, 120(sp)
         lw t0, 120(sp)
         slli t0, t0, 2
         sw t0, 128(sp)
         lw t0, 112(sp)
         lw t1, 128(sp)
         add t0, t0, t1
         sw t0, 132(sp)
         lw t0, 132(sp)
         sw zero, 0(t0)
         j for_step.3
for_step.3:
         lui t0, %hi(i)
         sw t0, 140(sp)
         lw t0, 140(sp)
         lw t0, %lo(i)(t0)
         sw t0, 136(sp)
         lw t0, 136(sp)
         addi t0, t0, 1
         sw t0, 144(sp)
         li t0, 1
         sw t0, 148(sp)
         lw t0, 136(sp)
         lw t1, 148(sp)
         add t0, t0, t1
         sw t0, 144(sp)
         lui t0, %hi(i)
         sw t0, 152(sp)
         lw t0, 152(sp)
         lw t1, 144(sp)
         sw t1, %lo(i)(t0)
         j for_cond.3
for_exit.3:
         lui t0, %hi(i)
         sw t0, 156(sp)
         lw t0, 156(sp)
         sw zero, %lo(i)(t0)
         j for_cond.4
for_cond.4:
         lui t0, %hi(i)
         sw t0, 164(sp)
         lw t0, 164(sp)
         lw t0, %lo(i)(t0)
         sw t0, 160(sp)
         lui t0, %hi(M)
         sw t0, 172(sp)
         lw t0, 172(sp)
         lw t0, %lo(M)(t0)
         sw t0, 168(sp)
         lw t0, 168(sp)
         addi t0, t0, 1
         sw t0, 176(sp)
         li t0, 1
         sw t0, 180(sp)
         lw t0, 168(sp)
         lw t1, 180(sp)
         add t0, t0, t1
         sw t0, 176(sp)
         lw t0, 160(sp)
         lw t1, 176(sp)
         slt t0, t0, t1
         sw t0, 184(sp)
         lw t0, 184(sp)
         beq t0, zero, for_exit.4
         j for_body.4
for_body.4:
         lui t0, %hi(prime)
         sw t0, 192(sp)
         lw t0, 192(sp)
         lw t0, %lo(prime)(t0)
         sw t0, 188(sp)
         lui t0, %hi(i)
         sw t0, 200(sp)
         lw t0, 200(sp)
         lw t0, %lo(i)(t0)
         sw t0, 196(sp)
         lw t0, 196(sp)
         slli t0, t0, 2
         sw t0, 204(sp)
         lw t0, 188(sp)
         lw t1, 204(sp)
         add t0, t0, t1
         sw t0, 208(sp)
         lw t0, 208(sp)
         sw zero, 0(t0)
         j for_step.4
for_step.4:
         lui t0, %hi(i)
         sw t0, 216(sp)
         lw t0, 216(sp)
         lw t0, %lo(i)(t0)
         sw t0, 212(sp)
         lw t0, 212(sp)
         addi t0, t0, 1
         sw t0, 220(sp)
         li t0, 1
         sw t0, 224(sp)
         lw t0, 212(sp)
         lw t1, 224(sp)
         add t0, t0, t1
         sw t0, 220(sp)
         lui t0, %hi(i)
         sw t0, 228(sp)
         lw t0, 228(sp)
         lw t1, 220(sp)
         sw t1, %lo(i)(t0)
         j for_cond.4
for_exit.4:
         lui t0, %hi(i)
         sw t0, 232(sp)
         lw t0, 232(sp)
         sw zero, %lo(i)(t0)
         j for_cond.5
for_cond.5:
         lui t0, %hi(i)
         sw t0, 240(sp)
         lw t0, 240(sp)
         lw t0, %lo(i)(t0)
         sw t0, 236(sp)
         lui t0, %hi(M)
         sw t0, 248(sp)
         lw t0, 248(sp)
         lw t0, %lo(M)(t0)
         sw t0, 244(sp)
         lw t0, 244(sp)
         lw t1, 236(sp)
         slt t0, t0, t1
         sw t0, 252(sp)
         lw t0, 252(sp)
         xori t0, t0, 1
         sw t0, 256(sp)
         lw t0, 256(sp)
         beq t0, zero, for_exit.5
         j for_body.5
for_body.5:
         lui t0, %hi(j)
         sw t0, 260(sp)
         lw t0, 260(sp)
         sw zero, %lo(j)(t0)
         j for_cond.6
for_step.5:
         lui t0, %hi(i)
         sw t0, 268(sp)
         lw t0, 268(sp)
         lw t0, %lo(i)(t0)
         sw t0, 264(sp)
         lw t0, 264(sp)
         addi t0, t0, 1
         sw t0, 272(sp)
         li t0, 1
         sw t0, 276(sp)
         lw t0, 264(sp)
         lw t1, 276(sp)
         add t0, t0, t1
         sw t0, 272(sp)
         lui t0, %hi(i)
         sw t0, 280(sp)
         lw t0, 280(sp)
         lw t1, 272(sp)
         sw t1, %lo(i)(t0)
         j for_cond.5
for_exit.5:
         lui t0, %hi(N)
         sw t0, 288(sp)
         lw t0, 288(sp)
         lw t0, %lo(N)(t0)
         sw t0, 284(sp)
         lw t0, 284(sp)
         mv a0, t0
         call getPrime
         lui t0, %hi(tmp)
         sw t0, 296(sp)
         lw t0, 296(sp)
         lw t0, %lo(tmp)(t0)
         sw t0, 292(sp)
         slli t0, zero, 2
         sw t0, 300(sp)
         lw t0, 292(sp)
         lw t1, 300(sp)
         add t0, t0, t1
         sw t0, 304(sp)
         lw t0, 304(sp)
         lw t0, 0(t0)
         sw t0, 308(sp)
         lui t0, %hi(primeCount)
         sw t0, 312(sp)
         lw t0, 312(sp)
         lw t1, 308(sp)
         sw t1, %lo(primeCount)(t0)
         li t0, 1
         sw t0, 316(sp)
         lui t0, %hi(i)
         sw t0, 320(sp)
         lw t0, 320(sp)
         lw t1, 316(sp)
         sw t1, %lo(i)(t0)
         j for_cond.7
for_cond.6:
         lui t0, %hi(j)
         sw t0, 328(sp)
         lw t0, 328(sp)
         lw t0, %lo(j)(t0)
         sw t0, 324(sp)
         lui t0, %hi(M)
         sw t0, 336(sp)
         lw t0, 336(sp)
         lw t0, %lo(M)(t0)
         sw t0, 332(sp)
         lw t0, 332(sp)
         lw t1, 324(sp)
         slt t0, t0, t1
         sw t0, 340(sp)
         lw t0, 340(sp)
         xori t0, t0, 1
         sw t0, 344(sp)
         lw t0, 344(sp)
         beq t0, zero, for_exit.6
         j for_body.6
for_body.6:
         lui t0, %hi(result)
         sw t0, 352(sp)
         lw t0, 352(sp)
         lw t0, %lo(result)(t0)
         sw t0, 348(sp)
         lui t0, %hi(i)
         sw t0, 360(sp)
         lw t0, 360(sp)
         lw t0, %lo(i)(t0)
         sw t0, 356(sp)
         lw t0, 356(sp)
         slli t0, t0, 2
         sw t0, 364(sp)
         lw t0, 348(sp)
         lw t1, 364(sp)
         add t0, t0, t1
         sw t0, 368(sp)
         lw t0, 368(sp)
         lw t0, 0(t0)
         sw t0, 372(sp)
         lui t0, %hi(j)
         sw t0, 380(sp)
         lw t0, 380(sp)
         lw t0, %lo(j)(t0)
         sw t0, 376(sp)
         lw t0, 376(sp)
         slli t0, t0, 2
         sw t0, 384(sp)
         lw t0, 372(sp)
         lw t1, 384(sp)
         add t0, t0, t1
         sw t0, 388(sp)
         addi t0, zero, -1
         sw t0, 392(sp)
         li t0, 1
         sw t0, 396(sp)
         lw t1, 396(sp)
         sub t0, zero, t1
         sw t0, 392(sp)
         lw t0, 388(sp)
         lw t1, 392(sp)
         sw t1, 0(t0)
         j for_step.6
for_step.6:
         lui t0, %hi(j)
         sw t0, 404(sp)
         lw t0, 404(sp)
         lw t0, %lo(j)(t0)
         sw t0, 400(sp)
         lw t0, 400(sp)
         addi t0, t0, 1
         sw t0, 408(sp)
         li t0, 1
         sw t0, 412(sp)
         lw t0, 400(sp)
         lw t1, 412(sp)
         add t0, t0, t1
         sw t0, 408(sp)
         lui t0, %hi(j)
         sw t0, 416(sp)
         lw t0, 416(sp)
         lw t1, 408(sp)
         sw t1, %lo(j)(t0)
         j for_cond.6
for_exit.6:
         j for_step.5
for_cond.7:
         lui t0, %hi(i)
         sw t0, 424(sp)
         lw t0, 424(sp)
         lw t0, %lo(i)(t0)
         sw t0, 420(sp)
         lui t0, %hi(primeCount)
         sw t0, 432(sp)
         lw t0, 432(sp)
         lw t0, %lo(primeCount)(t0)
         sw t0, 428(sp)
         lw t0, 420(sp)
         lw t1, 428(sp)
         slt t0, t0, t1
         sw t0, 436(sp)
         lw t0, 436(sp)
         beq t0, zero, for_exit.7
         j for_body.7
for_body.7:
         lui t0, %hi(i)
         sw t0, 444(sp)
         lw t0, 444(sp)
         lw t0, %lo(i)(t0)
         sw t0, 440(sp)
         lw t0, 440(sp)
         addi t0, t0, 1
         sw t0, 448(sp)
         li t0, 1
         sw t0, 452(sp)
         lw t0, 440(sp)
         lw t1, 452(sp)
         add t0, t0, t1
         sw t0, 448(sp)
         lui t0, %hi(j)
         sw t0, 456(sp)
         lw t0, 456(sp)
         lw t1, 448(sp)
         sw t1, %lo(j)(t0)
         j for_cond.8
for_step.7:
         lui t0, %hi(i)
         sw t0, 464(sp)
         lw t0, 464(sp)
         lw t0, %lo(i)(t0)
         sw t0, 460(sp)
         lw t0, 460(sp)
         addi t0, t0, 1
         sw t0, 468(sp)
         li t0, 1
         sw t0, 472(sp)
         lw t0, 460(sp)
         lw t1, 472(sp)
         add t0, t0, t1
         sw t0, 468(sp)
         lui t0, %hi(i)
         sw t0, 476(sp)
         lw t0, 476(sp)
         lw t1, 468(sp)
         sw t1, %lo(i)(t0)
         j for_cond.7
for_exit.7:
         lui t0, %hi(strConst.2)
         sw t0, 480(sp)
         lw t0, 480(sp)
         addi t0, t0, %lo(strConst.2)
         sw t0, 480(sp)
         lw t0, 480(sp)
         mv a0, t0
         call print
         lui t0, %hi(resultCount)
         sw t0, 488(sp)
         lw t0, 488(sp)
         lw t0, %lo(resultCount)(t0)
         sw t0, 484(sp)
         lw t0, 484(sp)
         mv a0, t0
         call toString
         mv t0, a0
         sw t0, 492(sp)
         lw t0, 492(sp)
         mv a0, t0
         call println
         sw zero, 0(sp)
         j main.exit
for_cond.8:
         lui t0, %hi(j)
         sw t0, 500(sp)
         lw t0, 500(sp)
         lw t0, %lo(j)(t0)
         sw t0, 496(sp)
         lui t0, %hi(primeCount)
         sw t0, 508(sp)
         lw t0, 508(sp)
         lw t0, %lo(primeCount)(t0)
         sw t0, 504(sp)
         lw t0, 504(sp)
         lw t1, 496(sp)
         slt t0, t0, t1
         sw t0, 512(sp)
         lw t0, 512(sp)
         xori t0, t0, 1
         sw t0, 516(sp)
         lw t0, 516(sp)
         beq t0, zero, for_exit.8
         j for_body.8
for_body.8:
         lui t0, %hi(result)
         sw t0, 524(sp)
         lw t0, 524(sp)
         lw t0, %lo(result)(t0)
         sw t0, 520(sp)
         lui t0, %hi(i)
         sw t0, 532(sp)
         lw t0, 532(sp)
         lw t0, %lo(i)(t0)
         sw t0, 528(sp)
         lw t0, 528(sp)
         slli t0, t0, 2
         sw t0, 536(sp)
         lw t0, 520(sp)
         lw t1, 536(sp)
         add t0, t0, t1
         sw t0, 540(sp)
         lw t0, 540(sp)
         lw t0, 0(t0)
         sw t0, 544(sp)
         lui t0, %hi(j)
         sw t0, 552(sp)
         lw t0, 552(sp)
         lw t0, %lo(j)(t0)
         sw t0, 548(sp)
         lw t0, 548(sp)
         slli t0, t0, 2
         sw t0, 556(sp)
         lw t0, 544(sp)
         lw t1, 556(sp)
         add t0, t0, t1
         sw t0, 560(sp)
         lw t0, 560(sp)
         lw t0, 0(t0)
         sw t0, 564(sp)
         addi t0, zero, -1
         sw t0, 568(sp)
         li t0, 1
         sw t0, 572(sp)
         lw t1, 572(sp)
         sub t0, zero, t1
         sw t0, 568(sp)
         lw t0, 564(sp)
         lw t1, 568(sp)
         sub t0, t0, t1
         sw t0, 576(sp)
         lw t0, 576(sp)
         seqz t0, t0
         sw t0, 580(sp)
         lw t0, 580(sp)
         beq t0, zero, if_false.5
         j if_true.5
for_step.8:
         lui t0, %hi(j)
         sw t0, 588(sp)
         lw t0, 588(sp)
         lw t0, %lo(j)(t0)
         sw t0, 584(sp)
         lw t0, 584(sp)
         addi t0, t0, 1
         sw t0, 592(sp)
         li t0, 1
         sw t0, 596(sp)
         lw t0, 584(sp)
         lw t1, 596(sp)
         add t0, t0, t1
         sw t0, 592(sp)
         lui t0, %hi(j)
         sw t0, 600(sp)
         lw t0, 600(sp)
         lw t1, 592(sp)
         sw t1, %lo(j)(t0)
         j for_cond.8
for_exit.8:
         j for_step.7
if_true.5:
         lui t0, %hi(result)
         sw t0, 608(sp)
         lw t0, 608(sp)
         lw t0, %lo(result)(t0)
         sw t0, 604(sp)
         lui t0, %hi(i)
         sw t0, 616(sp)
         lw t0, 616(sp)
         lw t0, %lo(i)(t0)
         sw t0, 612(sp)
         lw t0, 612(sp)
         slli t0, t0, 2
         sw t0, 620(sp)
         lw t0, 604(sp)
         lw t1, 620(sp)
         add t0, t0, t1
         sw t0, 624(sp)
         lw t0, 624(sp)
         lw t0, 0(t0)
         sw t0, 628(sp)
         lui t0, %hi(j)
         sw t0, 636(sp)
         lw t0, 636(sp)
         lw t0, %lo(j)(t0)
         sw t0, 632(sp)
         lw t0, 632(sp)
         slli t0, t0, 2
         sw t0, 640(sp)
         lw t0, 628(sp)
         lw t1, 640(sp)
         add t0, t0, t1
         sw t0, 644(sp)
         lui t0, %hi(N)
         sw t0, 652(sp)
         lw t0, 652(sp)
         lw t0, %lo(N)(t0)
         sw t0, 648(sp)
         lui t0, %hi(i)
         sw t0, 660(sp)
         lw t0, 660(sp)
         lw t0, %lo(i)(t0)
         sw t0, 656(sp)
         lui t0, %hi(j)
         sw t0, 668(sp)
         lw t0, 668(sp)
         lw t0, %lo(j)(t0)
         sw t0, 664(sp)
         lw t0, 648(sp)
         mv a0, t0
         lw t0, 656(sp)
         mv a1, t0
         lw t0, 664(sp)
         mv a2, t0
         call getResult
         mv t0, a0
         sw t0, 672(sp)
         lw t0, 644(sp)
         lw t1, 672(sp)
         sw t1, 0(t0)
         lui t0, %hi(result)
         sw t0, 680(sp)
         lw t0, 680(sp)
         lw t0, %lo(result)(t0)
         sw t0, 676(sp)
         lui t0, %hi(i)
         sw t0, 688(sp)
         lw t0, 688(sp)
         lw t0, %lo(i)(t0)
         sw t0, 684(sp)
         lw t0, 684(sp)
         slli t0, t0, 2
         sw t0, 692(sp)
         lw t0, 676(sp)
         lw t1, 692(sp)
         add t0, t0, t1
         sw t0, 696(sp)
         lw t0, 696(sp)
         lw t0, 0(t0)
         sw t0, 700(sp)
         lui t0, %hi(j)
         sw t0, 708(sp)
         lw t0, 708(sp)
         lw t0, %lo(j)(t0)
         sw t0, 704(sp)
         lw t0, 704(sp)
         slli t0, t0, 2
         sw t0, 712(sp)
         lw t0, 700(sp)
         lw t1, 712(sp)
         add t0, t0, t1
         sw t0, 716(sp)
         lw t0, 716(sp)
         lw t0, 0(t0)
         sw t0, 720(sp)
         li t0, 1
         sw t0, 728(sp)
         lw t0, 728(sp)
         lw t1, 720(sp)
         slt t0, t0, t1
         sw t0, 724(sp)
         lw t0, 724(sp)
         beq t0, zero, if_false.6
         j if_true.6
if_false.5:
         j if_exit.5
if_exit.5:
         j for_step.8
if_true.6:
         lui t0, %hi(prime)
         sw t0, 736(sp)
         lw t0, 736(sp)
         lw t0, %lo(prime)(t0)
         sw t0, 732(sp)
         lui t0, %hi(i)
         sw t0, 744(sp)
         lw t0, 744(sp)
         lw t0, %lo(i)(t0)
         sw t0, 740(sp)
         lw t0, 740(sp)
         slli t0, t0, 2
         sw t0, 748(sp)
         lw t0, 732(sp)
         lw t1, 748(sp)
         add t0, t0, t1
         sw t0, 752(sp)
         lw t0, 752(sp)
         lw t0, 0(t0)
         sw t0, 756(sp)
         lui t0, %hi(prime)
         sw t0, 764(sp)
         lw t0, 764(sp)
         lw t0, %lo(prime)(t0)
         sw t0, 760(sp)
         lui t0, %hi(j)
         sw t0, 772(sp)
         lw t0, 772(sp)
         lw t0, %lo(j)(t0)
         sw t0, 768(sp)
         lw t0, 768(sp)
         slli t0, t0, 2
         sw t0, 776(sp)
         lw t0, 760(sp)
         lw t1, 776(sp)
         add t0, t0, t1
         sw t0, 780(sp)
         lw t0, 780(sp)
         lw t0, 0(t0)
         sw t0, 784(sp)
         lui t0, %hi(result)
         sw t0, 792(sp)
         lw t0, 792(sp)
         lw t0, %lo(result)(t0)
         sw t0, 788(sp)
         lui t0, %hi(i)
         sw t0, 800(sp)
         lw t0, 800(sp)
         lw t0, %lo(i)(t0)
         sw t0, 796(sp)
         lw t0, 796(sp)
         slli t0, t0, 2
         sw t0, 804(sp)
         lw t0, 788(sp)
         lw t1, 804(sp)
         add t0, t0, t1
         sw t0, 808(sp)
         lw t0, 808(sp)
         lw t0, 0(t0)
         sw t0, 812(sp)
         lui t0, %hi(j)
         sw t0, 820(sp)
         lw t0, 820(sp)
         lw t0, %lo(j)(t0)
         sw t0, 816(sp)
         lw t0, 816(sp)
         slli t0, t0, 2
         sw t0, 824(sp)
         lw t0, 812(sp)
         lw t1, 824(sp)
         add t0, t0, t1
         sw t0, 828(sp)
         lw t0, 828(sp)
         lw t0, 0(t0)
         sw t0, 832(sp)
         lw t0, 756(sp)
         mv a0, t0
         lw t0, 784(sp)
         mv a1, t0
         lw t0, 832(sp)
         mv a2, t0
         call printF
         lui t0, %hi(resultCount)
         sw t0, 840(sp)
         lw t0, 840(sp)
         lw t0, %lo(resultCount)(t0)
         sw t0, 836(sp)
         lw t0, 836(sp)
         addi t0, t0, 1
         sw t0, 844(sp)
         li t0, 1
         sw t0, 848(sp)
         lw t0, 836(sp)
         lw t1, 848(sp)
         add t0, t0, t1
         sw t0, 844(sp)
         lui t0, %hi(resultCount)
         sw t0, 852(sp)
         lw t0, 852(sp)
         lw t1, 844(sp)
         sw t1, %lo(resultCount)(t0)
         j if_exit.6
if_false.6:
         j if_exit.6
if_exit.6:
         j if_exit.5

  .text
  .globl printF
  .type printF, @function
printF:
printF.entry:
         addi sp, sp, -100
         mv t0, ra
         sw t0, 12(sp)
         sw a0, 8(sp)
         sw a1, 4(sp)
         sw a2, 0(sp)
         lw t0, 8(sp)
         sw t0, 16(sp)
         lw t0, 16(sp)
         mv a0, t0
         call toString
         mv t0, a0
         sw t0, 20(sp)
         lw t0, 20(sp)
         mv a0, t0
         call print
         j while_cond.1
printF.exit:
         lw t0, 12(sp)
         mv ra, t0
         addi sp, sp, 100
         ret
while_cond.1:
         lw t0, 0(sp)
         sw t0, 24(sp)
         lw t1, 24(sp)
         slt t0, zero, t1
         sw t0, 28(sp)
         lw t0, 28(sp)
         beq t0, zero, while_exit.1
         j while_body.1
while_body.1:
         lui t0, %hi(strConst)
         sw t0, 32(sp)
         lw t0, 32(sp)
         addi t0, t0, %lo(strConst)
         sw t0, 32(sp)
         lw t0, 32(sp)
         mv a0, t0
         call print
         lw t0, 4(sp)
         sw t0, 36(sp)
         lw t0, 36(sp)
         mv a0, t0
         call toString
         mv t0, a0
         sw t0, 40(sp)
         lw t0, 40(sp)
         mv a0, t0
         call print
         lw t0, 4(sp)
         sw t0, 44(sp)
         li t0, 2
         sw t0, 52(sp)
         lw t0, 44(sp)
         lw t1, 52(sp)
         mul t0, t0, t1
         sw t0, 48(sp)
         lw t0, 8(sp)
         sw t0, 56(sp)
         lw t0, 48(sp)
         lw t1, 56(sp)
         sub t0, t0, t1
         sw t0, 60(sp)
         lw t1, 60(sp)
         sw t1, 4(sp)
         lw t0, 8(sp)
         sw t0, 64(sp)
         lw t0, 4(sp)
         sw t0, 68(sp)
         lw t0, 64(sp)
         lw t1, 68(sp)
         add t0, t0, t1
         sw t0, 72(sp)
         li t0, 2
         sw t0, 80(sp)
         lw t0, 72(sp)
         lw t1, 80(sp)
         div t0, t0, t1
         sw t0, 76(sp)
         lw t1, 76(sp)
         sw t1, 8(sp)
         lw t0, 0(sp)
         sw t0, 84(sp)
         lw t0, 84(sp)
         addi t0, t0, -1
         sw t0, 88(sp)
         li t0, 1
         sw t0, 92(sp)
         lw t0, 84(sp)
         lw t1, 92(sp)
         sub t0, t0, t1
         sw t0, 88(sp)
         lw t1, 88(sp)
         sw t1, 0(sp)
         j while_cond.1
while_exit.1:
         lui t0, %hi(strConst.1)
         sw t0, 96(sp)
         lw t0, 96(sp)
         addi t0, t0, %lo(strConst.1)
         sw t0, 96(sp)
         lw t0, 96(sp)
         mv a0, t0
         call print
         j printF.exit

  .text
  .globl getPrime
  .type getPrime, @function
getPrime:
getPrime.entry:
         addi sp, sp, -280
         mv t0, ra
         sw t0, 12(sp)
         sw a0, 8(sp)
         li t0, 2
         sw t0, 16(sp)
         lw t1, 16(sp)
         sw t1, 4(sp)
         li t0, 2
         sw t0, 20(sp)
         lw t1, 20(sp)
         sw t1, 0(sp)
         j for_cond.2
getPrime.exit:
         lw t0, 12(sp)
         mv ra, t0
         addi sp, sp, 280
         ret
for_cond.2:
         lw t0, 0(sp)
         sw t0, 24(sp)
         lw t0, 8(sp)
         sw t0, 28(sp)
         lw t0, 28(sp)
         lw t1, 24(sp)
         slt t0, t0, t1
         sw t0, 32(sp)
         lw t0, 32(sp)
         xori t0, t0, 1
         sw t0, 36(sp)
         lw t0, 36(sp)
         beq t0, zero, for_exit.2
         j for_body.2
for_body.2:
         lui t0, %hi(b)
         sw t0, 44(sp)
         lw t0, 44(sp)
         lw t0, %lo(b)(t0)
         sw t0, 40(sp)
         lw t0, 0(sp)
         sw t0, 48(sp)
         lw t0, 48(sp)
         slli t0, t0, 2
         sw t0, 52(sp)
         lw t0, 40(sp)
         lw t1, 52(sp)
         add t0, t0, t1
         sw t0, 56(sp)
         lw t0, 56(sp)
         lw t0, 0(t0)
         sw t0, 60(sp)
         li t0, 1
         sw t0, 68(sp)
         lw t0, 60(sp)
         lw t1, 68(sp)
         sub t0, t0, t1
         sw t0, 64(sp)
         lw t0, 64(sp)
         seqz t0, t0
         sw t0, 72(sp)
         lw t0, 72(sp)
         beq t0, zero, if_false
         j if_true
for_step.2:
         lw t0, 0(sp)
         sw t0, 76(sp)
         lw t0, 76(sp)
         addi t0, t0, 1
         sw t0, 80(sp)
         li t0, 1
         sw t0, 84(sp)
         lw t0, 76(sp)
         lw t1, 84(sp)
         add t0, t0, t1
         sw t0, 80(sp)
         lw t1, 80(sp)
         sw t1, 0(sp)
         j for_cond.2
for_exit.2:
         j getPrime.exit
if_true:
         lui t0, %hi(tmp)
         sw t0, 92(sp)
         lw t0, 92(sp)
         lw t0, %lo(tmp)(t0)
         sw t0, 88(sp)
         slli t0, zero, 2
         sw t0, 96(sp)
         lw t0, 88(sp)
         lw t1, 96(sp)
         add t0, t0, t1
         sw t0, 100(sp)
         lui t0, %hi(tmp)
         sw t0, 108(sp)
         lw t0, 108(sp)
         lw t0, %lo(tmp)(t0)
         sw t0, 104(sp)
         slli t0, zero, 2
         sw t0, 112(sp)
         lw t0, 104(sp)
         lw t1, 112(sp)
         add t0, t0, t1
         sw t0, 116(sp)
         lw t0, 116(sp)
         lw t0, 0(t0)
         sw t0, 120(sp)
         lw t0, 120(sp)
         addi t0, t0, 1
         sw t0, 124(sp)
         li t0, 1
         sw t0, 128(sp)
         lw t0, 120(sp)
         lw t1, 128(sp)
         add t0, t0, t1
         sw t0, 124(sp)
         lw t0, 100(sp)
         lw t1, 124(sp)
         sw t1, 0(t0)
         lui t0, %hi(tmp)
         sw t0, 136(sp)
         lw t0, 136(sp)
         lw t0, %lo(tmp)(t0)
         sw t0, 132(sp)
         slli t0, zero, 2
         sw t0, 140(sp)
         lw t0, 132(sp)
         lw t1, 140(sp)
         add t0, t0, t1
         sw t0, 144(sp)
         lui t0, %hi(prime)
         sw t0, 152(sp)
         lw t0, 152(sp)
         lw t0, %lo(prime)(t0)
         sw t0, 148(sp)
         lw t0, 144(sp)
         lw t0, 0(t0)
         sw t0, 156(sp)
         lw t0, 156(sp)
         slli t0, t0, 2
         sw t0, 160(sp)
         lw t0, 148(sp)
         lw t1, 160(sp)
         add t0, t0, t1
         sw t0, 164(sp)
         lw t0, 0(sp)
         sw t0, 168(sp)
         lw t0, 164(sp)
         lw t1, 168(sp)
         sw t1, 0(t0)
         lui t0, %hi(gps)
         sw t0, 176(sp)
         lw t0, 176(sp)
         lw t0, %lo(gps)(t0)
         sw t0, 172(sp)
         lw t0, 0(sp)
         sw t0, 180(sp)
         lw t0, 180(sp)
         slli t0, t0, 2
         sw t0, 184(sp)
         lw t0, 172(sp)
         lw t1, 184(sp)
         add t0, t0, t1
         sw t0, 188(sp)
         lui t0, %hi(tmp)
         sw t0, 196(sp)
         lw t0, 196(sp)
         lw t0, %lo(tmp)(t0)
         sw t0, 192(sp)
         slli t0, zero, 2
         sw t0, 200(sp)
         lw t0, 192(sp)
         lw t1, 200(sp)
         add t0, t0, t1
         sw t0, 204(sp)
         lw t0, 204(sp)
         lw t0, 0(t0)
         sw t0, 208(sp)
         lw t0, 188(sp)
         lw t1, 208(sp)
         sw t1, 0(t0)
         j if_exit
if_false:
         j if_exit
if_exit:
         j while_cond
while_cond:
         lw t0, 0(sp)
         sw t0, 212(sp)
         lw t0, 4(sp)
         sw t0, 216(sp)
         lw t0, 212(sp)
         lw t1, 216(sp)
         mul t0, t0, t1
         sw t0, 220(sp)
         lw t0, 8(sp)
         sw t0, 224(sp)
         lw t0, 224(sp)
         lw t1, 220(sp)
         slt t0, t0, t1
         sw t0, 228(sp)
         lw t0, 228(sp)
         xori t0, t0, 1
         sw t0, 232(sp)
         lw t0, 232(sp)
         beq t0, zero, while_exit
         j while_body
while_body:
         lw t0, 0(sp)
         sw t0, 236(sp)
         lw t0, 4(sp)
         sw t0, 240(sp)
         lw t0, 236(sp)
         lw t1, 240(sp)
         mul t0, t0, t1
         sw t0, 244(sp)
         lui t0, %hi(b)
         sw t0, 252(sp)
         lw t0, 252(sp)
         lw t0, %lo(b)(t0)
         sw t0, 248(sp)
         lw t0, 244(sp)
         slli t0, t0, 2
         sw t0, 256(sp)
         lw t0, 248(sp)
         lw t1, 256(sp)
         add t0, t0, t1
         sw t0, 260(sp)
         lw t0, 260(sp)
         sw zero, 0(t0)
         lw t0, 4(sp)
         sw t0, 264(sp)
         lw t0, 264(sp)
         addi t0, t0, 1
         sw t0, 268(sp)
         li t0, 1
         sw t0, 272(sp)
         lw t0, 264(sp)
         lw t1, 272(sp)
         add t0, t0, t1
         sw t0, 268(sp)
         lw t1, 268(sp)
         sw t1, 4(sp)
         j while_cond
while_exit:
         li t0, 2
         sw t0, 276(sp)
         lw t1, 276(sp)
         sw t1, 4(sp)
         j for_step.2

  .text
  .globl getResult
  .type getResult, @function
getResult:
getResult.entry:
         addi sp, sp, -512
         mv t0, ra
         sw t0, 16(sp)
         sw a0, 12(sp)
         sw a1, 8(sp)
         sw a2, 4(sp)
         lui t0, %hi(result)
         sw t0, 24(sp)
         lw t0, 24(sp)
         lw t0, %lo(result)(t0)
         sw t0, 20(sp)
         lw t0, 8(sp)
         sw t0, 28(sp)
         lw t0, 28(sp)
         slli t0, t0, 2
         sw t0, 32(sp)
         lw t0, 20(sp)
         lw t1, 32(sp)
         add t0, t0, t1
         sw t0, 36(sp)
         lw t0, 36(sp)
         lw t0, 0(t0)
         sw t0, 40(sp)
         lw t0, 4(sp)
         sw t0, 44(sp)
         lw t0, 44(sp)
         slli t0, t0, 2
         sw t0, 48(sp)
         lw t0, 40(sp)
         lw t1, 48(sp)
         add t0, t0, t1
         sw t0, 52(sp)
         lw t0, 52(sp)
         lw t0, 0(t0)
         sw t0, 56(sp)
         addi t0, zero, -1
         sw t0, 60(sp)
         li t0, 1
         sw t0, 64(sp)
         lw t1, 64(sp)
         sub t0, zero, t1
         sw t0, 60(sp)
         lw t0, 56(sp)
         lw t1, 60(sp)
         sub t0, t0, t1
         sw t0, 68(sp)
         lw t0, 68(sp)
         seqz t0, t0
         sw t0, 72(sp)
         lw t0, 72(sp)
         beq t0, zero, if_false.1
         j if_true.1
getResult.exit:
         lw t0, 0(sp)
         sw t0, 508(sp)
         lw t0, 508(sp)
         mv a0, t0
         lw t0, 16(sp)
         mv ra, t0
         addi sp, sp, 512
         ret
if_true.1:
         lui t0, %hi(prime)
         sw t0, 80(sp)
         lw t0, 80(sp)
         lw t0, %lo(prime)(t0)
         sw t0, 76(sp)
         lw t0, 4(sp)
         sw t0, 84(sp)
         lw t0, 84(sp)
         slli t0, t0, 2
         sw t0, 88(sp)
         lw t0, 76(sp)
         lw t1, 88(sp)
         add t0, t0, t1
         sw t0, 92(sp)
         lw t0, 92(sp)
         lw t0, 0(t0)
         sw t0, 96(sp)
         li t0, 2
         sw t0, 104(sp)
         lw t0, 96(sp)
         lw t1, 104(sp)
         mul t0, t0, t1
         sw t0, 100(sp)
         lui t0, %hi(prime)
         sw t0, 112(sp)
         lw t0, 112(sp)
         lw t0, %lo(prime)(t0)
         sw t0, 108(sp)
         lw t0, 8(sp)
         sw t0, 116(sp)
         lw t0, 116(sp)
         slli t0, t0, 2
         sw t0, 120(sp)
         lw t0, 108(sp)
         lw t1, 120(sp)
         add t0, t0, t1
         sw t0, 124(sp)
         lw t0, 124(sp)
         lw t0, 0(t0)
         sw t0, 128(sp)
         lw t0, 100(sp)
         lw t1, 128(sp)
         sub t0, t0, t1
         sw t0, 132(sp)
         lw t0, 12(sp)
         sw t0, 136(sp)
         lw t0, 136(sp)
         lw t1, 132(sp)
         slt t0, t0, t1
         sw t0, 140(sp)
         lw t0, 140(sp)
         xori t0, t0, 1
         sw t0, 144(sp)
         lw t0, 144(sp)
         beq t0, zero, if_false.2
         j if_true.2
if_false.1:
         j if_exit.1
if_exit.1:
         lui t0, %hi(result)
         sw t0, 152(sp)
         lw t0, 152(sp)
         lw t0, %lo(result)(t0)
         sw t0, 148(sp)
         lw t0, 8(sp)
         sw t0, 156(sp)
         lw t0, 156(sp)
         slli t0, t0, 2
         sw t0, 160(sp)
         lw t0, 148(sp)
         lw t1, 160(sp)
         add t0, t0, t1
         sw t0, 164(sp)
         lw t0, 164(sp)
         lw t0, 0(t0)
         sw t0, 168(sp)
         lw t0, 4(sp)
         sw t0, 172(sp)
         lw t0, 172(sp)
         slli t0, t0, 2
         sw t0, 176(sp)
         lw t0, 168(sp)
         lw t1, 176(sp)
         add t0, t0, t1
         sw t0, 180(sp)
         lw t0, 180(sp)
         lw t0, 0(t0)
         sw t0, 184(sp)
         addi t0, zero, -1
         sw t0, 188(sp)
         li t0, 1
         sw t0, 192(sp)
         lw t1, 192(sp)
         sub t0, zero, t1
         sw t0, 188(sp)
         lw t0, 184(sp)
         lw t1, 188(sp)
         sub t0, t0, t1
         sw t0, 196(sp)
         lw t0, 196(sp)
         seqz t0, t0
         sw t0, 200(sp)
         lw t0, 200(sp)
         beq t0, zero, if_false.4
         j if_true.4
if_true.2:
         lui t0, %hi(prime)
         sw t0, 208(sp)
         lw t0, 208(sp)
         lw t0, %lo(prime)(t0)
         sw t0, 204(sp)
         lw t0, 4(sp)
         sw t0, 212(sp)
         lw t0, 212(sp)
         slli t0, t0, 2
         sw t0, 216(sp)
         lw t0, 204(sp)
         lw t1, 216(sp)
         add t0, t0, t1
         sw t0, 220(sp)
         lw t0, 220(sp)
         lw t0, 0(t0)
         sw t0, 224(sp)
         li t0, 2
         sw t0, 232(sp)
         lw t0, 224(sp)
         lw t1, 232(sp)
         mul t0, t0, t1
         sw t0, 228(sp)
         lui t0, %hi(prime)
         sw t0, 240(sp)
         lw t0, 240(sp)
         lw t0, %lo(prime)(t0)
         sw t0, 236(sp)
         lw t0, 8(sp)
         sw t0, 244(sp)
         lw t0, 244(sp)
         slli t0, t0, 2
         sw t0, 248(sp)
         lw t0, 236(sp)
         lw t1, 248(sp)
         add t0, t0, t1
         sw t0, 252(sp)
         lw t0, 252(sp)
         lw t0, 0(t0)
         sw t0, 256(sp)
         lw t0, 228(sp)
         lw t1, 256(sp)
         sub t0, t0, t1
         sw t0, 260(sp)
         lui t0, %hi(b)
         sw t0, 268(sp)
         lw t0, 268(sp)
         lw t0, %lo(b)(t0)
         sw t0, 264(sp)
         lw t0, 260(sp)
         slli t0, t0, 2
         sw t0, 272(sp)
         lw t0, 264(sp)
         lw t1, 272(sp)
         add t0, t0, t1
         sw t0, 276(sp)
         lw t0, 276(sp)
         lw t0, 0(t0)
         sw t0, 280(sp)
         lw t0, 280(sp)
         sub t0, t0, zero
         sw t0, 284(sp)
         lw t0, 284(sp)
         snez t0, t0
         sw t0, 288(sp)
         lw t0, 288(sp)
         beq t0, zero, if_false.3
         j if_true.3
if_false.2:
         j if_exit.2
if_exit.2:
         j if_exit.1
if_true.3:
         lui t0, %hi(result)
         sw t0, 296(sp)
         lw t0, 296(sp)
         lw t0, %lo(result)(t0)
         sw t0, 292(sp)
         lw t0, 8(sp)
         sw t0, 300(sp)
         lw t0, 300(sp)
         slli t0, t0, 2
         sw t0, 304(sp)
         lw t0, 292(sp)
         lw t1, 304(sp)
         add t0, t0, t1
         sw t0, 308(sp)
         lw t0, 308(sp)
         lw t0, 0(t0)
         sw t0, 312(sp)
         lw t0, 4(sp)
         sw t0, 316(sp)
         lw t0, 316(sp)
         slli t0, t0, 2
         sw t0, 320(sp)
         lw t0, 312(sp)
         lw t1, 320(sp)
         add t0, t0, t1
         sw t0, 324(sp)
         lw t0, 12(sp)
         sw t0, 328(sp)
         lw t0, 4(sp)
         sw t0, 332(sp)
         lui t0, %hi(prime)
         sw t0, 340(sp)
         lw t0, 340(sp)
         lw t0, %lo(prime)(t0)
         sw t0, 336(sp)
         lw t0, 4(sp)
         sw t0, 344(sp)
         lw t0, 344(sp)
         slli t0, t0, 2
         sw t0, 348(sp)
         lw t0, 336(sp)
         lw t1, 348(sp)
         add t0, t0, t1
         sw t0, 352(sp)
         lw t0, 352(sp)
         lw t0, 0(t0)
         sw t0, 356(sp)
         li t0, 2
         sw t0, 364(sp)
         lw t0, 356(sp)
         lw t1, 364(sp)
         mul t0, t0, t1
         sw t0, 360(sp)
         lui t0, %hi(prime)
         sw t0, 372(sp)
         lw t0, 372(sp)
         lw t0, %lo(prime)(t0)
         sw t0, 368(sp)
         lw t0, 8(sp)
         sw t0, 376(sp)
         lw t0, 376(sp)
         slli t0, t0, 2
         sw t0, 380(sp)
         lw t0, 368(sp)
         lw t1, 380(sp)
         add t0, t0, t1
         sw t0, 384(sp)
         lw t0, 384(sp)
         lw t0, 0(t0)
         sw t0, 388(sp)
         lw t0, 360(sp)
         lw t1, 388(sp)
         sub t0, t0, t1
         sw t0, 392(sp)
         lui t0, %hi(gps)
         sw t0, 400(sp)
         lw t0, 400(sp)
         lw t0, %lo(gps)(t0)
         sw t0, 396(sp)
         lw t0, 392(sp)
         slli t0, t0, 2
         sw t0, 404(sp)
         lw t0, 396(sp)
         lw t1, 404(sp)
         add t0, t0, t1
         sw t0, 408(sp)
         lw t0, 408(sp)
         lw t0, 0(t0)
         sw t0, 412(sp)
         lw t0, 328(sp)
         mv a0, t0
         lw t0, 332(sp)
         mv a1, t0
         lw t0, 412(sp)
         mv a2, t0
         call getResult
         mv t0, a0
         sw t0, 416(sp)
         lw t0, 416(sp)
         addi t0, t0, 1
         sw t0, 420(sp)
         li t0, 1
         sw t0, 424(sp)
         lw t0, 416(sp)
         lw t1, 424(sp)
         add t0, t0, t1
         sw t0, 420(sp)
         lw t0, 324(sp)
         lw t1, 420(sp)
         sw t1, 0(t0)
         j if_exit.3
if_false.3:
         j if_exit.3
if_exit.3:
         j if_exit.2
if_true.4:
         lui t0, %hi(result)
         sw t0, 432(sp)
         lw t0, 432(sp)
         lw t0, %lo(result)(t0)
         sw t0, 428(sp)
         lw t0, 8(sp)
         sw t0, 436(sp)
         lw t0, 436(sp)
         slli t0, t0, 2
         sw t0, 440(sp)
         lw t0, 428(sp)
         lw t1, 440(sp)
         add t0, t0, t1
         sw t0, 444(sp)
         lw t0, 444(sp)
         lw t0, 0(t0)
         sw t0, 448(sp)
         lw t0, 4(sp)
         sw t0, 452(sp)
         lw t0, 452(sp)
         slli t0, t0, 2
         sw t0, 456(sp)
         lw t0, 448(sp)
         lw t1, 456(sp)
         add t0, t0, t1
         sw t0, 460(sp)
         li t0, 1
         sw t0, 464(sp)
         lw t0, 460(sp)
         lw t1, 464(sp)
         sw t1, 0(t0)
         j if_exit.4
if_false.4:
         j if_exit.4
if_exit.4:
         lui t0, %hi(result)
         sw t0, 472(sp)
         lw t0, 472(sp)
         lw t0, %lo(result)(t0)
         sw t0, 468(sp)
         lw t0, 8(sp)
         sw t0, 476(sp)
         lw t0, 476(sp)
         slli t0, t0, 2
         sw t0, 480(sp)
         lw t0, 468(sp)
         lw t1, 480(sp)
         add t0, t0, t1
         sw t0, 484(sp)
         lw t0, 484(sp)
         lw t0, 0(t0)
         sw t0, 488(sp)
         lw t0, 4(sp)
         sw t0, 492(sp)
         lw t0, 492(sp)
         slli t0, t0, 2
         sw t0, 496(sp)
         lw t0, 488(sp)
         lw t1, 496(sp)
         add t0, t0, t1
         sw t0, 500(sp)
         lw t0, 500(sp)
         lw t0, 0(t0)
         sw t0, 504(sp)
         lw t1, 504(sp)
         sw t1, 0(sp)
         j getResult.exit

.section .bss
 .globl prime
 .type prime, @object
prime:
  .word 0
 .size prime, 4


 .globl result
 .type result, @object
result:
  .word 0
 .size result, 4


 .globl resultCount
 .type resultCount, @object
resultCount:
  .word 0
 .size resultCount, 4


 .globl b
 .type b, @object
b:
  .word 0
 .size b, 4


 .globl tmp
 .type tmp, @object
tmp:
  .word 0
 .size tmp, 4


 .globl i
 .type i, @object
i:
  .word 0
 .size i, 4


 .globl j
 .type j, @object
j:
  .word 0
 .size j, 4


 .globl primeCount
 .type primeCount, @object
primeCount:
  .word 0
 .size primeCount, 4


 .globl gps
 .type gps, @object
gps:
  .word 0
 .size gps, 4


 .globl M
 .type M, @object
M:
  .word 0
 .size M, 4


 .globl N
 .type N, @object
N:
  .word 0
 .size N, 4


.section .rodata
 .type strConst, @object
strConst:
         .string " "
         .size strConst, 2


 .type strConst.1, @object
strConst.1:
         .string "\n"
         .size strConst.1, 2


 .type strConst.2, @object
strConst.2:
         .string "Total: "
         .size strConst.2, 8



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
       	.asciz	"%s\\n"
       	.size	.L.str.1, 4

       	.type	.L.str.2,@object
       .L.str.2:
       	.asciz	"%d"
       	.size	.L.str.2, 3

       	.type	.L.str.3,@object
       .L.str.3:
       	.asciz	"%d\\n"
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

