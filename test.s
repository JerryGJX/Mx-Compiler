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
         lw t0, 0(sp)
         mv ra, t0
         addi sp, sp, 4
         ret

  .text
  .globl main
  .type main, @function
main:
main.entry:
         addi sp, sp, -188
         mv t0, ra
         sw t0, 4(sp)
         call _init_func
         sw zero, 0(sp)
         lui t0, %hi(i)
         sw t0, 8(sp)
         lw t0, 8(sp)
         sw zero, %lo(i)(t0)
         j for_cond
main.exit:
         lw t0, 0(sp)
         sw t0, 184(sp)
         lw t0, 184(sp)
         mv a0, t0
         lw t0, 4(sp)
         mv ra, t0
         addi sp, sp, 188
         ret
for_cond:
         lui t0, %hi(i)
         sw t0, 16(sp)
         lw t0, 16(sp)
         lw t0, %lo(i)(t0)
         sw t0, 12(sp)
         li t0, 5
         sw t0, 24(sp)
         lw t0, 12(sp)
         lw t1, 24(sp)
         slt t0, t0, t1
         sw t0, 20(sp)
         lw t0, 20(sp)
         beq t0, zero, for_exit
         j for_body
for_body:
         lui t0, %hi(j)
         sw t0, 28(sp)
         lw t0, 28(sp)
         sw zero, %lo(j)(t0)
         j for_cond.1
for_step:
         lui t0, %hi(i)
         sw t0, 36(sp)
         lw t0, 36(sp)
         lw t0, %lo(i)(t0)
         sw t0, 32(sp)
         lw t0, 32(sp)
         addi t0, t0, 1
         sw t0, 40(sp)
         li t0, 1
         sw t0, 44(sp)
         lw t0, 32(sp)
         lw t1, 44(sp)
         add t0, t0, t1
         sw t0, 40(sp)
         lui t0, %hi(i)
         sw t0, 48(sp)
         lw t0, 48(sp)
         lw t1, 40(sp)
         sw t1, %lo(i)(t0)
         j for_cond
for_exit:
         lui t0, %hi(r)
         sw t0, 56(sp)
         lw t0, 56(sp)
         lw t0, %lo(r)(t0)
         sw t0, 52(sp)
         li t0, 2
         sw t0, 64(sp)
         lw t0, 64(sp)
         lw t1, 52(sp)
         sub t0, t0, t1
         sw t0, 60(sp)
         lw t0, 60(sp)
         mv a0, t0
         call abs
         mv t0, a0
         sw t0, 68(sp)
         lui t0, %hi(c)
         sw t0, 76(sp)
         lw t0, 76(sp)
         lw t0, %lo(c)(t0)
         sw t0, 72(sp)
         li t0, 2
         sw t0, 84(sp)
         lw t0, 84(sp)
         lw t1, 72(sp)
         sub t0, t0, t1
         sw t0, 80(sp)
         lw t0, 80(sp)
         mv a0, t0
         call abs
         mv t0, a0
         sw t0, 88(sp)
         lw t0, 68(sp)
         lw t1, 88(sp)
         add t0, t0, t1
         sw t0, 92(sp)
         lw t0, 92(sp)
         mv a0, t0
         call printInt
         sw zero, 0(sp)
         j main.exit
for_cond.1:
         lui t0, %hi(j)
         sw t0, 100(sp)
         lw t0, 100(sp)
         lw t0, %lo(j)(t0)
         sw t0, 96(sp)
         li t0, 5
         sw t0, 108(sp)
         lw t0, 96(sp)
         lw t1, 108(sp)
         slt t0, t0, t1
         sw t0, 104(sp)
         lw t0, 104(sp)
         beq t0, zero, for_exit.1
         j for_body.1
for_body.1:
         call getInt
         mv t0, a0
         sw t0, 112(sp)
         lui t0, %hi(n)
         sw t0, 116(sp)
         lw t0, 116(sp)
         lw t1, 112(sp)
         sw t1, %lo(n)(t0)
         lui t0, %hi(n)
         sw t0, 124(sp)
         lw t0, 124(sp)
         lw t0, %lo(n)(t0)
         sw t0, 120(sp)
         li t0, 1
         sw t0, 132(sp)
         lw t0, 120(sp)
         lw t1, 132(sp)
         sub t0, t0, t1
         sw t0, 128(sp)
         lw t0, 128(sp)
         seqz t0, t0
         sw t0, 136(sp)
         lw t0, 136(sp)
         beq t0, zero, if_false.1
         j if_true.1
for_step.1:
         lui t0, %hi(j)
         sw t0, 144(sp)
         lw t0, 144(sp)
         lw t0, %lo(j)(t0)
         sw t0, 140(sp)
         lw t0, 140(sp)
         addi t0, t0, 1
         sw t0, 148(sp)
         li t0, 1
         sw t0, 152(sp)
         lw t0, 140(sp)
         lw t1, 152(sp)
         add t0, t0, t1
         sw t0, 148(sp)
         lui t0, %hi(j)
         sw t0, 156(sp)
         lw t0, 156(sp)
         lw t1, 148(sp)
         sw t1, %lo(j)(t0)
         j for_cond.1
for_exit.1:
         j for_step
if_true.1:
         lui t0, %hi(i)
         sw t0, 164(sp)
         lw t0, 164(sp)
         lw t0, %lo(i)(t0)
         sw t0, 160(sp)
         lui t0, %hi(r)
         sw t0, 168(sp)
         lw t0, 168(sp)
         lw t1, 160(sp)
         sw t1, %lo(r)(t0)
         lui t0, %hi(j)
         sw t0, 176(sp)
         lw t0, 176(sp)
         lw t0, %lo(j)(t0)
         sw t0, 172(sp)
         lui t0, %hi(c)
         sw t0, 180(sp)
         lw t0, 180(sp)
         lw t1, 172(sp)
         sw t1, %lo(c)(t0)
         j if_exit.1
if_false.1:
         j if_exit.1
if_exit.1:
         j for_step.1

  .text
  .globl abs
  .type abs, @function
abs:
abs.entry:
         addi sp, sp, -40
         mv t0, ra
         sw t0, 8(sp)
         lw t1, 12(sp)
         sw t1, 4(sp)
         lw t0, 4(sp)
         sw t0, 16(sp)
         lw t1, 16(sp)
         slt t0, zero, t1
         sw t0, 20(sp)
         lw t0, 20(sp)
         beq t0, zero, if_false
         j if_true
abs.exit:
         lw t0, 0(sp)
         sw t0, 36(sp)
         lw t0, 36(sp)
         mv a0, t0
         lw t0, 8(sp)
         mv ra, t0
         addi sp, sp, 40
         ret
if_true:
         lw t0, 4(sp)
         sw t0, 24(sp)
         lw t1, 24(sp)
         sw t1, 0(sp)
         j abs.exit
if_false:
         j if_exit
if_exit:
         lw t0, 4(sp)
         sw t0, 28(sp)
         lw t1, 28(sp)
         sub t0, zero, t1
         sw t0, 32(sp)
         lw t1, 32(sp)
         sw t1, 0(sp)
         j abs.exit

.section .bss
 .globl r
 .type r, @object
r:
  .word 0
 .size r, 4


 .globl c
 .type c, @object
c:
  .word 0
 .size c, 4


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


 .globl n
 .type n, @object
n:
  .word 0
 .size n, 4


.section .rodata

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

