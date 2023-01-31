  .text
  .globl _init_func
  .type _init_func, @function
_init_func:
_init_func.entry:
         addi sp, sp, -56
         mv t0, ra
         sw t0, 0(sp)
         li t0, 4
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
         li t0, 4
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
         lui t0, %hi(a)
         sw t0, 52(sp)
         lw t0, 52(sp)
         lw t1, 48(sp)
         sw t1, %lo(a)(t0)
         j _init_func.exit
_init_func.exit:
         lw t0, 0(sp)
         mv ra, t0
         addi sp, sp, 56
         ret

  .text
  .globl main
  .type main, @function
main:
main.entry:
         addi sp, sp, -568
         mv t0, ra
         sw t0, 12(sp)
         call _init_func
         sw zero, 8(sp)
         li t0, 4
         sw t0, 20(sp)
         li t0, 4
         sw t0, 24(sp)
         lw t0, 20(sp)
         lw t1, 24(sp)
         mul t0, t0, t1
         sw t0, 16(sp)
         lw t0, 16(sp)
         addi t0, t0, 4
         sw t0, 28(sp)
         li t0, 4
         sw t0, 32(sp)
         lw t0, 16(sp)
         lw t1, 32(sp)
         add t0, t0, t1
         sw t0, 28(sp)
         lw t0, 28(sp)
         mv a0, t0
         call _malloc
         mv t0, a0
         sw t0, 36(sp)
         lw t0, 36(sp)
         mv t0, t0
         sw t0, 40(sp)
         li t0, 4
         sw t0, 44(sp)
         lw t0, 40(sp)
         lw t1, 44(sp)
         sw t1, 0(t0)
         li t0, 1
         sw t0, 52(sp)
         lw t0, 52(sp)
         slli t0, t0, 2
         sw t0, 48(sp)
         lw t0, 40(sp)
         lw t1, 48(sp)
         add t0, t0, t1
         sw t0, 56(sp)
         lw t0, 56(sp)
         mv t0, t0
         sw t0, 60(sp)
         lw t1, 60(sp)
         sw t1, 4(sp)
         lw t0, 4(sp)
         sw t0, 64(sp)
         slli t0, zero, 2
         sw t0, 68(sp)
         lw t0, 64(sp)
         lw t1, 68(sp)
         add t0, t0, t1
         sw t0, 72(sp)
         lui t0, %hi(a)
         sw t0, 80(sp)
         lw t0, 80(sp)
         lw t0, %lo(a)(t0)
         sw t0, 76(sp)
         lw t0, 72(sp)
         lw t1, 76(sp)
         sw t1, 0(t0)
         lw t0, 4(sp)
         sw t0, 84(sp)
         li t0, 1
         sw t0, 92(sp)
         lw t0, 92(sp)
         slli t0, t0, 2
         sw t0, 88(sp)
         lw t0, 84(sp)
         lw t1, 88(sp)
         add t0, t0, t1
         sw t0, 96(sp)
         lui t0, %hi(a)
         sw t0, 104(sp)
         lw t0, 104(sp)
         lw t0, %lo(a)(t0)
         sw t0, 100(sp)
         lw t0, 96(sp)
         lw t1, 100(sp)
         sw t1, 0(t0)
         lw t0, 4(sp)
         sw t0, 108(sp)
         li t0, 2
         sw t0, 116(sp)
         lw t0, 116(sp)
         slli t0, t0, 2
         sw t0, 112(sp)
         lw t0, 108(sp)
         lw t1, 112(sp)
         add t0, t0, t1
         sw t0, 120(sp)
         lui t0, %hi(a)
         sw t0, 128(sp)
         lw t0, 128(sp)
         lw t0, %lo(a)(t0)
         sw t0, 124(sp)
         lw t0, 120(sp)
         lw t1, 124(sp)
         sw t1, 0(t0)
         lw t0, 4(sp)
         sw t0, 132(sp)
         li t0, 3
         sw t0, 140(sp)
         lw t0, 140(sp)
         slli t0, t0, 2
         sw t0, 136(sp)
         lw t0, 132(sp)
         lw t1, 136(sp)
         add t0, t0, t1
         sw t0, 144(sp)
         lui t0, %hi(a)
         sw t0, 152(sp)
         lw t0, 152(sp)
         lw t0, %lo(a)(t0)
         sw t0, 148(sp)
         lw t0, 144(sp)
         lw t1, 148(sp)
         sw t1, 0(t0)
         lw t0, 4(sp)
         sw t0, 156(sp)
         lw t0, 156(sp)
         mv t0, t0
         sw t0, 160(sp)
         li t0, -1
         sw t0, 168(sp)
         lw t0, 168(sp)
         slli t0, t0, 2
         sw t0, 164(sp)
         lw t0, 160(sp)
         lw t1, 164(sp)
         add t0, t0, t1
         sw t0, 172(sp)
         lw t0, 172(sp)
         lw t0, 0(t0)
         sw t0, 176(sp)
         lw t0, 176(sp)
         mv a0, t0
         call toString
         mv t0, a0
         sw t0, 180(sp)
         lw t0, 180(sp)
         mv a0, t0
         call println
         sw zero, 0(sp)
         j for_cond
main.exit:
         lw t0, 12(sp)
         mv ra, t0
         addi sp, sp, 568
         ret
for_cond:
         lw t0, 0(sp)
         sw t0, 184(sp)
         lw t0, 4(sp)
         sw t0, 188(sp)
         slli t0, zero, 2
         sw t0, 192(sp)
         lw t0, 188(sp)
         lw t1, 192(sp)
         add t0, t0, t1
         sw t0, 196(sp)
         lw t0, 196(sp)
         lw t0, 0(t0)
         sw t0, 200(sp)
         lw t0, 200(sp)
         mv t0, t0
         sw t0, 204(sp)
         li t0, -1
         sw t0, 212(sp)
         lw t0, 212(sp)
         slli t0, t0, 2
         sw t0, 208(sp)
         lw t0, 204(sp)
         lw t1, 208(sp)
         add t0, t0, t1
         sw t0, 216(sp)
         lw t0, 216(sp)
         lw t0, 0(t0)
         sw t0, 220(sp)
         lw t0, 184(sp)
         lw t1, 220(sp)
         slt t0, t0, t1
         sw t0, 224(sp)
         lw t0, 224(sp)
         beq t0, zero, for_exit
         j for_body
for_body:
         lw t0, 4(sp)
         sw t0, 228(sp)
         slli t0, zero, 2
         sw t0, 232(sp)
         lw t0, 228(sp)
         lw t1, 232(sp)
         add t0, t0, t1
         sw t0, 236(sp)
         lw t0, 236(sp)
         lw t0, 0(t0)
         sw t0, 240(sp)
         lw t0, 0(sp)
         sw t0, 244(sp)
         lw t0, 244(sp)
         slli t0, t0, 2
         sw t0, 248(sp)
         lw t0, 240(sp)
         lw t1, 248(sp)
         add t0, t0, t1
         sw t0, 252(sp)
         call getInt
         mv t0, a0
         sw t0, 256(sp)
         lw t0, 252(sp)
         lw t1, 256(sp)
         sw t1, 0(t0)
         j for_step
for_step:
         lw t0, 0(sp)
         sw t0, 260(sp)
         lw t0, 260(sp)
         addi t0, t0, 1
         sw t0, 264(sp)
         li t0, 1
         sw t0, 268(sp)
         lw t0, 260(sp)
         lw t1, 268(sp)
         add t0, t0, t1
         sw t0, 264(sp)
         lw t1, 264(sp)
         sw t1, 0(sp)
         j for_cond
for_exit:
         sw zero, 0(sp)
         j for_cond.1
for_cond.1:
         lw t0, 0(sp)
         sw t0, 272(sp)
         lw t0, 4(sp)
         sw t0, 276(sp)
         li t0, 1
         sw t0, 284(sp)
         lw t0, 284(sp)
         slli t0, t0, 2
         sw t0, 280(sp)
         lw t0, 276(sp)
         lw t1, 280(sp)
         add t0, t0, t1
         sw t0, 288(sp)
         lw t0, 288(sp)
         lw t0, 0(t0)
         sw t0, 292(sp)
         lw t0, 292(sp)
         mv t0, t0
         sw t0, 296(sp)
         li t0, -1
         sw t0, 304(sp)
         lw t0, 304(sp)
         slli t0, t0, 2
         sw t0, 300(sp)
         lw t0, 296(sp)
         lw t1, 300(sp)
         add t0, t0, t1
         sw t0, 308(sp)
         lw t0, 308(sp)
         lw t0, 0(t0)
         sw t0, 312(sp)
         lw t0, 272(sp)
         lw t1, 312(sp)
         slt t0, t0, t1
         sw t0, 316(sp)
         lw t0, 316(sp)
         beq t0, zero, for_exit.1
         j for_body.1
for_body.1:
         lw t0, 4(sp)
         sw t0, 320(sp)
         li t0, 1
         sw t0, 328(sp)
         lw t0, 328(sp)
         slli t0, t0, 2
         sw t0, 324(sp)
         lw t0, 320(sp)
         lw t1, 324(sp)
         add t0, t0, t1
         sw t0, 332(sp)
         lw t0, 332(sp)
         lw t0, 0(t0)
         sw t0, 336(sp)
         lw t0, 0(sp)
         sw t0, 340(sp)
         lw t0, 340(sp)
         slli t0, t0, 2
         sw t0, 344(sp)
         lw t0, 336(sp)
         lw t1, 344(sp)
         add t0, t0, t1
         sw t0, 348(sp)
         lw t0, 348(sp)
         lw t0, 0(t0)
         sw t0, 352(sp)
         lw t0, 352(sp)
         mv a0, t0
         call toString
         mv t0, a0
         sw t0, 356(sp)
         lw t0, 356(sp)
         mv a0, t0
         call print
         j for_step.1
for_step.1:
         lw t0, 0(sp)
         sw t0, 360(sp)
         lw t0, 360(sp)
         addi t0, t0, 1
         sw t0, 364(sp)
         li t0, 1
         sw t0, 368(sp)
         lw t0, 360(sp)
         lw t1, 368(sp)
         add t0, t0, t1
         sw t0, 364(sp)
         lw t1, 364(sp)
         sw t1, 0(sp)
         j for_cond.1
for_exit.1:
         lui t0, %hi(strConst)
         sw t0, 372(sp)
         lw t0, 372(sp)
         addi t0, t0, %lo(strConst)
         sw t0, 372(sp)
         lw t0, 372(sp)
         mv a0, t0
         call println
         sw zero, 0(sp)
         j for_cond.2
for_cond.2:
         lw t0, 0(sp)
         sw t0, 376(sp)
         lw t0, 4(sp)
         sw t0, 380(sp)
         li t0, 2
         sw t0, 388(sp)
         lw t0, 388(sp)
         slli t0, t0, 2
         sw t0, 384(sp)
         lw t0, 380(sp)
         lw t1, 384(sp)
         add t0, t0, t1
         sw t0, 392(sp)
         lw t0, 392(sp)
         lw t0, 0(t0)
         sw t0, 396(sp)
         lw t0, 396(sp)
         mv t0, t0
         sw t0, 400(sp)
         li t0, -1
         sw t0, 408(sp)
         lw t0, 408(sp)
         slli t0, t0, 2
         sw t0, 404(sp)
         lw t0, 400(sp)
         lw t1, 404(sp)
         add t0, t0, t1
         sw t0, 412(sp)
         lw t0, 412(sp)
         lw t0, 0(t0)
         sw t0, 416(sp)
         lw t0, 376(sp)
         lw t1, 416(sp)
         slt t0, t0, t1
         sw t0, 420(sp)
         lw t0, 420(sp)
         beq t0, zero, for_exit.2
         j for_body.2
for_body.2:
         lw t0, 4(sp)
         sw t0, 424(sp)
         li t0, 2
         sw t0, 432(sp)
         lw t0, 432(sp)
         slli t0, t0, 2
         sw t0, 428(sp)
         lw t0, 424(sp)
         lw t1, 428(sp)
         add t0, t0, t1
         sw t0, 436(sp)
         lw t0, 436(sp)
         lw t0, 0(t0)
         sw t0, 440(sp)
         lw t0, 0(sp)
         sw t0, 444(sp)
         lw t0, 444(sp)
         slli t0, t0, 2
         sw t0, 448(sp)
         lw t0, 440(sp)
         lw t1, 448(sp)
         add t0, t0, t1
         sw t0, 452(sp)
         lw t0, 452(sp)
         sw zero, 0(t0)
         j for_step.2
for_step.2:
         lw t0, 0(sp)
         sw t0, 456(sp)
         lw t0, 456(sp)
         addi t0, t0, 1
         sw t0, 460(sp)
         li t0, 1
         sw t0, 464(sp)
         lw t0, 456(sp)
         lw t1, 464(sp)
         add t0, t0, t1
         sw t0, 460(sp)
         lw t1, 460(sp)
         sw t1, 0(sp)
         j for_cond.2
for_exit.2:
         sw zero, 0(sp)
         j for_cond.3
for_cond.3:
         lw t0, 0(sp)
         sw t0, 468(sp)
         lw t0, 4(sp)
         sw t0, 472(sp)
         li t0, 3
         sw t0, 480(sp)
         lw t0, 480(sp)
         slli t0, t0, 2
         sw t0, 476(sp)
         lw t0, 472(sp)
         lw t1, 476(sp)
         add t0, t0, t1
         sw t0, 484(sp)
         lw t0, 484(sp)
         lw t0, 0(t0)
         sw t0, 488(sp)
         lw t0, 488(sp)
         mv t0, t0
         sw t0, 492(sp)
         li t0, -1
         sw t0, 500(sp)
         lw t0, 500(sp)
         slli t0, t0, 2
         sw t0, 496(sp)
         lw t0, 492(sp)
         lw t1, 496(sp)
         add t0, t0, t1
         sw t0, 504(sp)
         lw t0, 504(sp)
         lw t0, 0(t0)
         sw t0, 508(sp)
         lw t0, 468(sp)
         lw t1, 508(sp)
         slt t0, t0, t1
         sw t0, 512(sp)
         lw t0, 512(sp)
         beq t0, zero, for_exit.3
         j for_body.3
for_body.3:
         lw t0, 4(sp)
         sw t0, 516(sp)
         li t0, 3
         sw t0, 524(sp)
         lw t0, 524(sp)
         slli t0, t0, 2
         sw t0, 520(sp)
         lw t0, 516(sp)
         lw t1, 520(sp)
         add t0, t0, t1
         sw t0, 528(sp)
         lw t0, 528(sp)
         lw t0, 0(t0)
         sw t0, 532(sp)
         lw t0, 0(sp)
         sw t0, 536(sp)
         lw t0, 536(sp)
         slli t0, t0, 2
         sw t0, 540(sp)
         lw t0, 532(sp)
         lw t1, 540(sp)
         add t0, t0, t1
         sw t0, 544(sp)
         lw t0, 544(sp)
         lw t0, 0(t0)
         sw t0, 548(sp)
         lw t0, 548(sp)
         mv a0, t0
         call toString
         mv t0, a0
         sw t0, 552(sp)
         lw t0, 552(sp)
         mv a0, t0
         call print
         j for_step.3
for_step.3:
         lw t0, 0(sp)
         sw t0, 556(sp)
         lw t0, 556(sp)
         addi t0, t0, 1
         sw t0, 560(sp)
         li t0, 1
         sw t0, 564(sp)
         lw t0, 556(sp)
         lw t1, 564(sp)
         add t0, t0, t1
         sw t0, 560(sp)
         lw t1, 560(sp)
         sw t1, 0(sp)
         j for_cond.3
for_exit.3:
         sw zero, 8(sp)
         j main.exit

.section .bss
 .globl a
 .type a, @object
a:
  .word 0
 .size a, 4


.section .rodata
 .type strConst, @object
strConst:
         .string ""
         .size strConst, 1



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

