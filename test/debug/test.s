  .text
  .globl _init_func
  .type _init_func, @function
_init_func:
_init_func.entry:
         addi sp, sp, 0
         mv vReg0, ra
         li vReg1, 8
         lui vReg2, %hi(N)
         sw vReg1, %lo(N)(vReg2)
         li vReg4, 8
         li vReg5, 4
         mul vReg3, vReg4, vReg5
         addi vReg6, vReg3, 4
         mv a0, vReg6
         call _malloc
         mv vReg7, a0
         mv vReg8, vReg7
         li vReg9, 8
         sw vReg9, 0(vReg8)
         li vReg11, 1
         slli vReg10, vReg11, 2
         add vReg12, vReg8, vReg10
         mv vReg13, vReg12
         lui vReg14, %hi(row)
         sw vReg13, %lo(row)(vReg14)
         li vReg16, 8
         li vReg17, 4
         mul vReg15, vReg16, vReg17
         addi vReg18, vReg15, 4
         mv a0, vReg18
         call _malloc
         mv vReg19, a0
         mv vReg20, vReg19
         li vReg21, 8
         sw vReg21, 0(vReg20)
         li vReg23, 1
         slli vReg22, vReg23, 2
         add vReg24, vReg20, vReg22
         mv vReg25, vReg24
         lui vReg26, %hi(col)
         sw vReg25, %lo(col)(vReg26)
         li vReg28, 2
         li vReg29, 4
         mul vReg27, vReg28, vReg29
         addi vReg30, vReg27, 4
         mv a0, vReg30
         call _malloc
         mv vReg31, a0
         mv vReg32, vReg31
         li vReg33, 2
         sw vReg33, 0(vReg32)
         li vReg35, 1
         slli vReg34, vReg35, 2
         add vReg36, vReg32, vReg34
         mv vReg37, vReg36
         lui vReg38, %hi(d)
         sw vReg37, %lo(d)(vReg38)
         j _init_func.exit
_init_func.exit:
         mv ra, vReg0
         addi sp, sp, 0
         ret

  .text
  .globl main
  .type main, @function
main:
main.entry:
         addi sp, sp, -12
         mv vReg0, ra
         call _init_func
         sw zero, 8(sp)
         sw zero, 4(sp)
         j for_cond.3
main.exit:
         lw vReg54, 8(sp)
         mv a0, vReg54
         mv ra, vReg0
         addi sp, sp, 12
         ret
for_cond.3:
         lw vReg1, 4(sp)
         li vReg3, 8
         slt vReg2, vReg1, vReg3
         beq vReg2, zero, for_exit.3
         j for_body.3
for_body.3:
         lui vReg5, %hi(row)
         lw vReg4, %lo(row)(vReg5)
         lw vReg6, 4(sp)
         slli vReg7, vReg6, 2
         add vReg8, vReg4, vReg7
         sw zero, 0(vReg8)
         lui vReg10, %hi(col)
         lw vReg9, %lo(col)(vReg10)
         lw vReg11, 4(sp)
         slli vReg12, vReg11, 2
         add vReg13, vReg9, vReg12
         sw zero, 0(vReg13)
         j for_step.3
for_step.3:
         lw vReg14, 4(sp)
         addi vReg15, vReg14, 1
         sw vReg15, 4(sp)
         j for_cond.3
for_exit.3:
         sw zero, 4(sp)
         j for_cond.4
for_cond.4:
         lw vReg16, 4(sp)
         li vReg18, 2
         slt vReg17, vReg16, vReg18
         beq vReg17, zero, for_exit.4
         j for_body.4
for_body.4:
         lui vReg20, %hi(d)
         lw vReg19, %lo(d)(vReg20)
         lw vReg21, 4(sp)
         slli vReg22, vReg21, 2
         add vReg23, vReg19, vReg22
         li vReg25, 8
         addi vReg24, vReg25, 8
         addi vReg26, vReg24, -1
         li vReg28, 4
         mul vReg27, vReg26, vReg28
         addi vReg29, vReg27, 4
         mv a0, vReg29
         call _malloc
         mv vReg30, a0
         mv vReg31, vReg30
         sw vReg26, 0(vReg31)
         li vReg33, 1
         slli vReg32, vReg33, 2
         add vReg34, vReg31, vReg32
         mv vReg35, vReg34
         sw vReg35, 0(vReg23)
         sw zero, 0(sp)
         j for_cond.5
for_step.4:
         lw vReg36, 4(sp)
         addi vReg37, vReg36, 1
         sw vReg37, 4(sp)
         j for_cond.4
for_exit.4:
         mv a0, zero
         call search
         sw zero, 8(sp)
         j main.exit
for_cond.5:
         lw vReg38, 0(sp)
         li vReg40, 8
         addi vReg39, vReg40, 8
         addi vReg41, vReg39, -1
         slt vReg42, vReg38, vReg41
         beq vReg42, zero, for_exit.5
         j for_body.5
for_body.5:
         lui vReg44, %hi(d)
         lw vReg43, %lo(d)(vReg44)
         lw vReg45, 4(sp)
         slli vReg46, vReg45, 2
         add vReg47, vReg43, vReg46
         lw vReg48, 0(vReg47)
         lw vReg49, 0(sp)
         slli vReg50, vReg49, 2
         add vReg51, vReg48, vReg50
         sw zero, 0(vReg51)
         j for_step.5
for_step.5:
         lw vReg52, 0(sp)
         addi vReg53, vReg52, 1
         sw vReg53, 0(sp)
         j for_cond.5
for_exit.5:
         j for_step.4

  .text
  .globl printBoard
  .type printBoard, @function
printBoard:
printBoard.entry:
         addi sp, sp, -8
         mv vReg0, ra
         sw zero, 4(sp)
         j for_cond
printBoard.exit:
         mv ra, vReg0
         addi sp, sp, 8
         ret
for_cond:
         lw vReg1, 4(sp)
         lui vReg3, %hi(N)
         lw vReg2, %lo(N)(vReg3)
         slt vReg4, vReg1, vReg2
         beq vReg4, zero, for_exit
         j for_body
for_body:
         sw zero, 0(sp)
         j for_cond.1
for_step:
         lw vReg5, 4(sp)
         addi vReg6, vReg5, 1
         sw vReg6, 4(sp)
         j for_cond
for_exit:
         lui vReg7, %hi(strConst.2)
         addi vReg8, vReg7, %lo(strConst.2)
         mv a0, vReg8
         call println
         j printBoard.exit
for_cond.1:
         lw vReg9, 0(sp)
         lui vReg11, %hi(N)
         lw vReg10, %lo(N)(vReg11)
         slt vReg12, vReg9, vReg10
         beq vReg12, zero, for_exit.1
         j for_body.1
for_body.1:
         lui vReg14, %hi(col)
         lw vReg13, %lo(col)(vReg14)
         lw vReg15, 4(sp)
         slli vReg16, vReg15, 2
         add vReg17, vReg13, vReg16
         lw vReg18, 0(vReg17)
         lw vReg19, 0(sp)
         sub vReg20, vReg18, vReg19
         seqz vReg21, vReg20
         beq vReg21, zero, if_false
         j if_true
for_step.1:
         lw vReg22, 0(sp)
         addi vReg23, vReg22, 1
         sw vReg23, 0(sp)
         j for_cond.1
for_exit.1:
         lui vReg24, %hi(strConst.2)
         addi vReg25, vReg24, %lo(strConst.2)
         mv a0, vReg25
         call println
         j for_step
if_true:
         lui vReg26, %hi(strConst)
         addi vReg27, vReg26, %lo(strConst)
         mv a0, vReg27
         call print
         j if_exit
if_false:
         lui vReg28, %hi(strConst.1)
         addi vReg29, vReg28, %lo(strConst.1)
         mv a0, vReg29
         call print
         j if_exit
if_exit:
         j for_step.1

  .text
  .globl search
  .type search, @function
search:
search.entry:
         addi sp, sp, -16
         mv vReg0, ra
         sw a0, 12(sp)
         lw vReg1, 12(sp)
         lui vReg3, %hi(N)
         lw vReg2, %lo(N)(vReg3)
         sub vReg4, vReg1, vReg2
         seqz vReg5, vReg4
         beq vReg5, zero, if_false.1
         j if_true.1
search.exit:
         mv ra, vReg0
         addi sp, sp, 16
         ret
if_true.1:
         call printBoard
         j if_exit.1
if_false.1:
         sw zero, 8(sp)
         j for_cond.2
if_exit.1:
         j search.exit
for_cond.2:
         lw vReg6, 8(sp)
         lui vReg8, %hi(N)
         lw vReg7, %lo(N)(vReg8)
         slt vReg9, vReg6, vReg7
         beq vReg9, zero, for_exit.2
         j for_body.2
for_body.2:
         lui vReg11, %hi(row)
         lw vReg10, %lo(row)(vReg11)
         lw vReg12, 8(sp)
         slli vReg13, vReg12, 2
         add vReg14, vReg10, vReg13
         lw vReg15, 0(vReg14)
         sub vReg16, vReg15, zero
         seqz vReg17, vReg16
         andi vReg18, vReg17, 1
         mv vReg19, vReg18
         sw vReg19, 4(sp)
         beq vReg17, zero, logicExitBlock
         j noShortCutBlock
for_step.2:
         lw vReg20, 8(sp)
         addi vReg21, vReg20, 1
         sw vReg21, 8(sp)
         j for_cond.2
for_exit.2:
         j if_exit.1
if_true.2:
         lui vReg23, %hi(d)
         lw vReg22, %lo(d)(vReg23)
         li vReg25, 1
         slli vReg24, vReg25, 2
         add vReg26, vReg22, vReg24
         lw vReg27, 8(sp)
         lui vReg29, %hi(N)
         lw vReg28, %lo(N)(vReg29)
         add vReg30, vReg27, vReg28
         addi vReg31, vReg30, -1
         lw vReg32, 12(sp)
         sub vReg33, vReg31, vReg32
         lw vReg34, 0(vReg26)
         slli vReg35, vReg33, 2
         add vReg36, vReg34, vReg35
         li vReg37, 1
         sw vReg37, 0(vReg36)
         lui vReg39, %hi(d)
         lw vReg38, %lo(d)(vReg39)
         slli vReg40, zero, 2
         add vReg41, vReg38, vReg40
         lw vReg42, 8(sp)
         lw vReg43, 12(sp)
         add vReg44, vReg42, vReg43
         lw vReg45, 0(vReg41)
         slli vReg46, vReg44, 2
         add vReg47, vReg45, vReg46
         li vReg48, 1
         sw vReg48, 0(vReg47)
         lui vReg50, %hi(row)
         lw vReg49, %lo(row)(vReg50)
         lw vReg51, 8(sp)
         slli vReg52, vReg51, 2
         add vReg53, vReg49, vReg52
         li vReg54, 1
         sw vReg54, 0(vReg53)
         lui vReg56, %hi(col)
         lw vReg55, %lo(col)(vReg56)
         lw vReg57, 12(sp)
         slli vReg58, vReg57, 2
         add vReg59, vReg55, vReg58
         lw vReg60, 8(sp)
         sw vReg60, 0(vReg59)
         lw vReg61, 12(sp)
         addi vReg62, vReg61, 1
         mv a0, vReg62
         call search
         lui vReg64, %hi(row)
         lw vReg63, %lo(row)(vReg64)
         lw vReg65, 8(sp)
         slli vReg66, vReg65, 2
         add vReg67, vReg63, vReg66
         sw zero, 0(vReg67)
         lui vReg69, %hi(d)
         lw vReg68, %lo(d)(vReg69)
         slli vReg70, zero, 2
         add vReg71, vReg68, vReg70
         lw vReg72, 8(sp)
         lw vReg73, 12(sp)
         add vReg74, vReg72, vReg73
         lw vReg75, 0(vReg71)
         slli vReg76, vReg74, 2
         add vReg77, vReg75, vReg76
         sw zero, 0(vReg77)
         lui vReg79, %hi(d)
         lw vReg78, %lo(d)(vReg79)
         li vReg81, 1
         slli vReg80, vReg81, 2
         add vReg82, vReg78, vReg80
         lw vReg83, 8(sp)
         lui vReg85, %hi(N)
         lw vReg84, %lo(N)(vReg85)
         add vReg86, vReg83, vReg84
         addi vReg87, vReg86, -1
         lw vReg88, 12(sp)
         sub vReg89, vReg87, vReg88
         lw vReg90, 0(vReg82)
         slli vReg91, vReg89, 2
         add vReg92, vReg90, vReg91
         sw zero, 0(vReg92)
         j if_exit.2
if_false.2:
         j if_exit.2
if_exit.2:
         j for_step.2
noShortCutBlock:
         lui vReg94, %hi(d)
         lw vReg93, %lo(d)(vReg94)
         slli vReg95, zero, 2
         add vReg96, vReg93, vReg95
         lw vReg97, 8(sp)
         lw vReg98, 12(sp)
         add vReg99, vReg97, vReg98
         lw vReg100, 0(vReg96)
         slli vReg101, vReg99, 2
         add vReg102, vReg100, vReg101
         lw vReg103, 0(vReg102)
         sub vReg104, vReg103, zero
         seqz vReg105, vReg104
         and vReg106, vReg17, vReg105
         andi vReg107, vReg106, 1
         mv vReg108, vReg107
         sw vReg108, 4(sp)
         j logicExitBlock
logicExitBlock:
         lw vReg109, 4(sp)
         andi vReg110, vReg109, 1
         mv vReg111, vReg110
         andi vReg112, vReg111, 1
         mv vReg113, vReg112
         sw vReg113, 0(sp)
         beq vReg111, zero, logicExitBlock.1
         j noShortCutBlock.1
noShortCutBlock.1:
         lui vReg115, %hi(d)
         lw vReg114, %lo(d)(vReg115)
         li vReg117, 1
         slli vReg116, vReg117, 2
         add vReg118, vReg114, vReg116
         lw vReg119, 8(sp)
         lui vReg121, %hi(N)
         lw vReg120, %lo(N)(vReg121)
         add vReg122, vReg119, vReg120
         addi vReg123, vReg122, -1
         lw vReg124, 12(sp)
         sub vReg125, vReg123, vReg124
         lw vReg126, 0(vReg118)
         slli vReg127, vReg125, 2
         add vReg128, vReg126, vReg127
         lw vReg129, 0(vReg128)
         sub vReg130, vReg129, zero
         seqz vReg131, vReg130
         and vReg132, vReg111, vReg131
         andi vReg133, vReg132, 1
         mv vReg134, vReg133
         sw vReg134, 0(sp)
         j logicExitBlock.1
logicExitBlock.1:
         lw vReg135, 0(sp)
         andi vReg136, vReg135, 1
         mv vReg137, vReg136
         beq vReg137, zero, if_false.2
         j if_true.2

.section .bss
 .globl col
 .type col, @object
col:
  .word 0
 .size col, 4


 .globl d
 .type d, @object
d:
  .word 0
 .size d, 4


 .globl row
 .type row, @object
row:
  .word 0
 .size row, 4


 .globl N
 .type N, @object
N:
  .word 0
 .size N, 4


.section .rodata
 .type strConst.2, @object
strConst.2:
         .string ""
         .size strConst.2, 1


 .type strConst.1, @object
strConst.1:
         .string " ."
         .size strConst.1, 3


 .type strConst, @object
strConst:
         .string " O"
         .size strConst, 3



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

