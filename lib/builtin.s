	.text
	.file	"builtin.c"
	.globl	print                           # -- Begin function print
	.p2align	4, 0x90
	.type	print,@function
print:                                  # @print
	.cfi_startproc
# %bb.0:
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset %rbp, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register %rbp
	subq	$16, %rsp
	movq	%rdi, -8(%rbp)
	movq	-8(%rbp), %rsi
	movabsq	$.L.str, %rdi
	movb	$0, %al
	callq	printf@PLT
	addq	$16, %rsp
	popq	%rbp
	.cfi_def_cfa %rsp, 8
	retq
.Lfunc_end0:
	.size	print, .Lfunc_end0-print
	.cfi_endproc
                                        # -- End function
	.globl	println                         # -- Begin function println
	.p2align	4, 0x90
	.type	println,@function
println:                                # @println
	.cfi_startproc
# %bb.0:
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset %rbp, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register %rbp
	subq	$16, %rsp
	movq	%rdi, -8(%rbp)
	movq	-8(%rbp), %rsi
	movabsq	$.L.str.1, %rdi
	movb	$0, %al
	callq	printf@PLT
	addq	$16, %rsp
	popq	%rbp
	.cfi_def_cfa %rsp, 8
	retq
.Lfunc_end1:
	.size	println, .Lfunc_end1-println
	.cfi_endproc
                                        # -- End function
	.globl	printInt                        # -- Begin function printInt
	.p2align	4, 0x90
	.type	printInt,@function
printInt:                               # @printInt
	.cfi_startproc
# %bb.0:
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset %rbp, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register %rbp
	subq	$16, %rsp
	movl	%edi, -4(%rbp)
	movl	-4(%rbp), %esi
	movabsq	$.L.str.2, %rdi
	movb	$0, %al
	callq	printf@PLT
	addq	$16, %rsp
	popq	%rbp
	.cfi_def_cfa %rsp, 8
	retq
.Lfunc_end2:
	.size	printInt, .Lfunc_end2-printInt
	.cfi_endproc
                                        # -- End function
	.globl	printlnInt                      # -- Begin function printlnInt
	.p2align	4, 0x90
	.type	printlnInt,@function
printlnInt:                             # @printlnInt
	.cfi_startproc
# %bb.0:
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset %rbp, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register %rbp
	subq	$16, %rsp
	movl	%edi, -4(%rbp)
	movl	-4(%rbp), %esi
	movabsq	$.L.str.3, %rdi
	movb	$0, %al
	callq	printf@PLT
	addq	$16, %rsp
	popq	%rbp
	.cfi_def_cfa %rsp, 8
	retq
.Lfunc_end3:
	.size	printlnInt, .Lfunc_end3-printlnInt
	.cfi_endproc
                                        # -- End function
	.globl	getString                       # -- Begin function getString
	.p2align	4, 0x90
	.type	getString,@function
getString:                              # @getString
	.cfi_startproc
# %bb.0:
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset %rbp, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register %rbp
	subq	$16, %rsp
	movl	$256, %edi                      # imm = 0x100
	callq	malloc@PLT
	movq	%rax, -8(%rbp)
	movq	-8(%rbp), %rsi
	movabsq	$.L.str, %rdi
	movb	$0, %al
	callq	scanf@PLT
	movq	-8(%rbp), %rax
	addq	$16, %rsp
	popq	%rbp
	.cfi_def_cfa %rsp, 8
	retq
.Lfunc_end4:
	.size	getString, .Lfunc_end4-getString
	.cfi_endproc
                                        # -- End function
	.globl	getInt                          # -- Begin function getInt
	.p2align	4, 0x90
	.type	getInt,@function
getInt:                                 # @getInt
	.cfi_startproc
# %bb.0:
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset %rbp, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register %rbp
	subq	$16, %rsp
	movabsq	$.L.str.2, %rdi
	leaq	-4(%rbp), %rsi
	movb	$0, %al
	callq	scanf@PLT
	movl	-4(%rbp), %eax
	addq	$16, %rsp
	popq	%rbp
	.cfi_def_cfa %rsp, 8
	retq
.Lfunc_end5:
	.size	getInt, .Lfunc_end5-getInt
	.cfi_endproc
                                        # -- End function
	.globl	toString                        # -- Begin function toString
	.p2align	4, 0x90
	.type	toString,@function
toString:                               # @toString
	.cfi_startproc
# %bb.0:
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset %rbp, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register %rbp
	subq	$16, %rsp
	movl	%edi, -4(%rbp)
	movl	$10, %edi
	callq	malloc@PLT
	movq	%rax, -16(%rbp)
	movq	-16(%rbp), %rdi
	movl	-4(%rbp), %edx
	movabsq	$.L.str.2, %rsi
	movb	$0, %al
	callq	sprintf@PLT
	movq	-16(%rbp), %rax
	addq	$16, %rsp
	popq	%rbp
	.cfi_def_cfa %rsp, 8
	retq
.Lfunc_end6:
	.size	toString, .Lfunc_end6-toString
	.cfi_endproc
                                        # -- End function
	.globl	_str_substring                  # -- Begin function _str_substring
	.p2align	4, 0x90
	.type	_str_substring,@function
_str_substring:                         # @_str_substring
	.cfi_startproc
# %bb.0:
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset %rbp, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register %rbp
	subq	$32, %rsp
	movq	%rdi, -32(%rbp)
	movl	%esi, -8(%rbp)
	movl	%edx, -12(%rbp)
	movl	-12(%rbp), %eax
	subl	-8(%rbp), %eax
	addl	$1, %eax
	movslq	%eax, %rdi
	callq	malloc@PLT
	movq	%rax, -24(%rbp)
	movl	-8(%rbp), %eax
	movl	%eax, -4(%rbp)
.LBB7_1:                                # =>This Inner Loop Header: Depth=1
	movl	-4(%rbp), %eax
	cmpl	-12(%rbp), %eax
	jge	.LBB7_4
# %bb.2:                                #   in Loop: Header=BB7_1 Depth=1
	movq	-32(%rbp), %rax
	movslq	-4(%rbp), %rcx
	movb	(%rax,%rcx), %al
	movq	-24(%rbp), %rcx
	movl	-4(%rbp), %edx
	subl	-8(%rbp), %edx
	movslq	%edx, %rdx
	movb	%al, (%rcx,%rdx)
# %bb.3:                                #   in Loop: Header=BB7_1 Depth=1
	movl	-4(%rbp), %eax
	addl	$1, %eax
	movl	%eax, -4(%rbp)
	jmp	.LBB7_1
.LBB7_4:
	movq	-24(%rbp), %rax
	movl	-12(%rbp), %ecx
	subl	-8(%rbp), %ecx
	movslq	%ecx, %rcx
	movb	$0, (%rax,%rcx)
	movq	-24(%rbp), %rax
	addq	$32, %rsp
	popq	%rbp
	.cfi_def_cfa %rsp, 8
	retq
.Lfunc_end7:
	.size	_str_substring, .Lfunc_end7-_str_substring
	.cfi_endproc
                                        # -- End function
	.globl	_str_length                     # -- Begin function _str_length
	.p2align	4, 0x90
	.type	_str_length,@function
_str_length:                            # @_str_length
	.cfi_startproc
# %bb.0:
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset %rbp, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register %rbp
	subq	$16, %rsp
	movq	%rdi, -8(%rbp)
	movq	-8(%rbp), %rdi
	callq	strlen@PLT
                                        # kill: def $eax killed $eax killed $rax
	addq	$16, %rsp
	popq	%rbp
	.cfi_def_cfa %rsp, 8
	retq
.Lfunc_end8:
	.size	_str_length, .Lfunc_end8-_str_length
	.cfi_endproc
                                        # -- End function
	.globl	_str_ord                        # -- Begin function _str_ord
	.p2align	4, 0x90
	.type	_str_ord,@function
_str_ord:                               # @_str_ord
	.cfi_startproc
# %bb.0:
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset %rbp, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register %rbp
	movq	%rdi, -16(%rbp)
	movl	%esi, -4(%rbp)
	movq	-16(%rbp), %rax
	movslq	-4(%rbp), %rcx
	movsbl	(%rax,%rcx), %eax
	popq	%rbp
	.cfi_def_cfa %rsp, 8
	retq
.Lfunc_end9:
	.size	_str_ord, .Lfunc_end9-_str_ord
	.cfi_endproc
                                        # -- End function
	.globl	_str_parseInt                   # -- Begin function _str_parseInt
	.p2align	4, 0x90
	.type	_str_parseInt,@function
_str_parseInt:                          # @_str_parseInt
	.cfi_startproc
# %bb.0:
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset %rbp, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register %rbp
	subq	$16, %rsp
	movq	%rdi, -16(%rbp)
	movq	-16(%rbp), %rdi
	movabsq	$.L.str.2, %rsi
	leaq	-4(%rbp), %rdx
	movb	$0, %al
	callq	sscanf@PLT
	movl	-4(%rbp), %eax
	addq	$16, %rsp
	popq	%rbp
	.cfi_def_cfa %rsp, 8
	retq
.Lfunc_end10:
	.size	_str_parseInt, .Lfunc_end10-_str_parseInt
	.cfi_endproc
                                        # -- End function
	.globl	_malloc                         # -- Begin function _malloc
	.p2align	4, 0x90
	.type	_malloc,@function
_malloc:                                # @_malloc
	.cfi_startproc
# %bb.0:
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset %rbp, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register %rbp
	subq	$16, %rsp
	movl	%edi, -4(%rbp)
	movslq	-4(%rbp), %rdi
	callq	malloc@PLT
	addq	$16, %rsp
	popq	%rbp
	.cfi_def_cfa %rsp, 8
	retq
.Lfunc_end11:
	.size	_malloc, .Lfunc_end11-_malloc
	.cfi_endproc
                                        # -- End function
	.globl	_str_concat                     # -- Begin function _str_concat
	.p2align	4, 0x90
	.type	_str_concat,@function
_str_concat:                            # @_str_concat
	.cfi_startproc
# %bb.0:
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset %rbp, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register %rbp
	pushq	%rbx
	subq	$24, %rsp
	.cfi_offset %rbx, -24
	movq	%rdi, -32(%rbp)
	movq	%rsi, -24(%rbp)
	movq	-32(%rbp), %rdi
	callq	strlen@PLT
	movq	%rax, %rbx
	movq	-24(%rbp), %rdi
	callq	strlen@PLT
	addq	%rax, %rbx
	addq	$5, %rbx
	movq	%rbx, %rdi
	callq	malloc@PLT
	movq	%rax, -16(%rbp)
	movq	-16(%rbp), %rdi
	movq	-32(%rbp), %rsi
	callq	strcpy@PLT
	movq	-16(%rbp), %rdi
	movq	-24(%rbp), %rsi
	callq	strcat@PLT
	movq	-16(%rbp), %rax
	addq	$24, %rsp
	popq	%rbx
	popq	%rbp
	.cfi_def_cfa %rsp, 8
	retq
.Lfunc_end12:
	.size	_str_concat, .Lfunc_end12-_str_concat
	.cfi_endproc
                                        # -- End function
	.globl	_str_eq                         # -- Begin function _str_eq
	.p2align	4, 0x90
	.type	_str_eq,@function
_str_eq:                                # @_str_eq
	.cfi_startproc
# %bb.0:
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset %rbp, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register %rbp
	subq	$16, %rsp
	movq	%rdi, -16(%rbp)
	movq	%rsi, -8(%rbp)
	movq	-16(%rbp), %rdi
	movq	-8(%rbp), %rsi
	callq	strcmp@PLT
	cmpl	$0, %eax
	sete	%al
	andb	$1, %al
	movzbl	%al, %eax
	movsbl	%al, %eax
	addq	$16, %rsp
	popq	%rbp
	.cfi_def_cfa %rsp, 8
	retq
.Lfunc_end13:
	.size	_str_eq, .Lfunc_end13-_str_eq
	.cfi_endproc
                                        # -- End function
	.globl	_str_ne                         # -- Begin function _str_ne
	.p2align	4, 0x90
	.type	_str_ne,@function
_str_ne:                                # @_str_ne
	.cfi_startproc
# %bb.0:
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset %rbp, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register %rbp
	subq	$16, %rsp
	movq	%rdi, -16(%rbp)
	movq	%rsi, -8(%rbp)
	movq	-16(%rbp), %rdi
	movq	-8(%rbp), %rsi
	callq	strcmp@PLT
	cmpl	$0, %eax
	setne	%al
	andb	$1, %al
	movzbl	%al, %eax
	movsbl	%al, %eax
	addq	$16, %rsp
	popq	%rbp
	.cfi_def_cfa %rsp, 8
	retq
.Lfunc_end14:
	.size	_str_ne, .Lfunc_end14-_str_ne
	.cfi_endproc
                                        # -- End function
	.globl	_str_lt                         # -- Begin function _str_lt
	.p2align	4, 0x90
	.type	_str_lt,@function
_str_lt:                                # @_str_lt
	.cfi_startproc
# %bb.0:
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset %rbp, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register %rbp
	subq	$16, %rsp
	movq	%rdi, -16(%rbp)
	movq	%rsi, -8(%rbp)
	movq	-16(%rbp), %rdi
	movq	-8(%rbp), %rsi
	callq	strcmp@PLT
	cmpl	$0, %eax
	setl	%al
	andb	$1, %al
	movzbl	%al, %eax
	movsbl	%al, %eax
	addq	$16, %rsp
	popq	%rbp
	.cfi_def_cfa %rsp, 8
	retq
.Lfunc_end15:
	.size	_str_lt, .Lfunc_end15-_str_lt
	.cfi_endproc
                                        # -- End function
	.globl	_str_gt                         # -- Begin function _str_gt
	.p2align	4, 0x90
	.type	_str_gt,@function
_str_gt:                                # @_str_gt
	.cfi_startproc
# %bb.0:
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset %rbp, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register %rbp
	subq	$16, %rsp
	movq	%rdi, -16(%rbp)
	movq	%rsi, -8(%rbp)
	movq	-16(%rbp), %rdi
	movq	-8(%rbp), %rsi
	callq	strcmp@PLT
	cmpl	$0, %eax
	setg	%al
	andb	$1, %al
	movzbl	%al, %eax
	movsbl	%al, %eax
	addq	$16, %rsp
	popq	%rbp
	.cfi_def_cfa %rsp, 8
	retq
.Lfunc_end16:
	.size	_str_gt, .Lfunc_end16-_str_gt
	.cfi_endproc
                                        # -- End function
	.globl	_str_le                         # -- Begin function _str_le
	.p2align	4, 0x90
	.type	_str_le,@function
_str_le:                                # @_str_le
	.cfi_startproc
# %bb.0:
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset %rbp, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register %rbp
	subq	$16, %rsp
	movq	%rdi, -16(%rbp)
	movq	%rsi, -8(%rbp)
	movq	-16(%rbp), %rdi
	movq	-8(%rbp), %rsi
	callq	strcmp@PLT
	cmpl	$0, %eax
	setle	%al
	andb	$1, %al
	movzbl	%al, %eax
	movsbl	%al, %eax
	addq	$16, %rsp
	popq	%rbp
	.cfi_def_cfa %rsp, 8
	retq
.Lfunc_end17:
	.size	_str_le, .Lfunc_end17-_str_le
	.cfi_endproc
                                        # -- End function
	.globl	_str_ge                         # -- Begin function _str_ge
	.p2align	4, 0x90
	.type	_str_ge,@function
_str_ge:                                # @_str_ge
	.cfi_startproc
# %bb.0:
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset %rbp, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register %rbp
	subq	$16, %rsp
	movq	%rdi, -16(%rbp)
	movq	%rsi, -8(%rbp)
	movq	-16(%rbp), %rdi
	movq	-8(%rbp), %rsi
	callq	strcmp@PLT
	cmpl	$0, %eax
	setge	%al
	andb	$1, %al
	movzbl	%al, %eax
	movsbl	%al, %eax
	addq	$16, %rsp
	popq	%rbp
	.cfi_def_cfa %rsp, 8
	retq
.Lfunc_end18:
	.size	_str_ge, .Lfunc_end18-_str_ge
	.cfi_endproc
                                        # -- End function
	.type	STR_BUF_SIZE,@object            # @STR_BUF_SIZE
	.section	.rodata,"a",@progbits
	.globl	STR_BUF_SIZE
	.p2align	2
STR_BUF_SIZE:
	.long	256                             # 0x100
	.size	STR_BUF_SIZE, 4

	.type	INT_BUF_SIZE,@object            # @INT_BUF_SIZE
	.globl	INT_BUF_SIZE
	.p2align	2
INT_BUF_SIZE:
	.long	10                              # 0xa
	.size	INT_BUF_SIZE, 4

	.type	.L.str,@object                  # @.str
	.section	.rodata.str1.1,"aMS",@progbits,1
.L.str:
	.asciz	"%s"
	.size	.L.str, 3

	.type	.L.str.1,@object                # @.str.1
.L.str.1:
	.asciz	"%s\n"
	.size	.L.str.1, 4

	.type	.L.str.2,@object                # @.str.2
.L.str.2:
	.asciz	"%d"
	.size	.L.str.2, 3

	.type	.L.str.3,@object                # @.str.3
.L.str.3:
	.asciz	"%d\n"
	.size	.L.str.3, 4

	.ident	"Ubuntu clang version 14.0.0-1ubuntu1"
	.section	".note.GNU-stack","",@progbits
