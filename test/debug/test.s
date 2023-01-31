  .text
  .globl _init_func
  .type _init_func, @function
_init_func:
_init_func.entry:
         addi sp, sp, -36
         mv t0, ra
         sw t0, 0(sp)
         li t0, 1000000
         sw t0, 4(sp)
         lui t0, %hi(MAX)
         sw t0, 8(sp)
         lw t0, 8(sp)
         lw t1, 4(sp)
         sw t1, %lo(MAX)(t0)
         lui t0, %hi(tot)
         sw t0, 12(sp)
         lw t0, 12(sp)
         sw zero, %lo(tot)(t0)
         lui t0, %hi(ans)
         sw t0, 16(sp)
         lw t0, 16(sp)
         sw zero, %lo(ans)(t0)
         lui t0, %hi(pick)
         sw t0, 20(sp)
         lw t0, 20(sp)
         sw zero, %lo(pick)(t0)
         lui t0, %hi(fst)
         sw t0, 24(sp)
         lw t0, 24(sp)
         sw zero, %lo(fst)(t0)
         lui t0, %hi(dis)
         sw t0, 28(sp)
         lw t0, 28(sp)
         sw zero, %lo(dis)(t0)
         lui t0, %hi(ed)
         sw t0, 32(sp)
         lw t0, 32(sp)
         sw zero, %lo(ed)(t0)
         j _init_func.exit
_init_func.exit:
         lw t0, 0(sp)
         mv ra, t0
         addi sp, sp, 36
         ret

  .text
  .globl Edge.Edge
  .type Edge.Edge, @function
Edge.Edge:
Edge.Edge.entry:
         addi sp, sp, -4
         mv t0, ra
         sw t0, 0(sp)
         j Edge.Edge.exit
Edge.Edge.exit:
         lw t0, 0(sp)
         mv ra, t0
         addi sp, sp, 4
         ret

  .text
  .globl main
  .type main, @function
main:
main.entry:
         addi sp, sp, -1080
         mv t0, ra
         sw t0, 36(sp)
         call _init_func
         sw zero, 32(sp)
         call getInt
         mv t0, a0
         sw t0, 40(sp)
         lui t0, %hi(n)
         sw t0, 44(sp)
         lw t0, 44(sp)
         lw t1, 40(sp)
         sw t1, %lo(n)(t0)
         call getInt
         mv t0, a0
         sw t0, 48(sp)
         lui t0, %hi(m)
         sw t0, 52(sp)
         lw t0, 52(sp)
         lw t1, 48(sp)
         sw t1, %lo(m)(t0)
         lui t0, %hi(n)
         sw t0, 60(sp)
         lw t0, 60(sp)
         lw t0, %lo(n)(t0)
         sw t0, 56(sp)
         lw t0, 56(sp)
         addi t0, t0, 1
         sw t0, 64(sp)
         li t0, 1
         sw t0, 68(sp)
         lw t0, 56(sp)
         lw t1, 68(sp)
         add t0, t0, t1
         sw t0, 64(sp)
         li t0, 4
         sw t0, 76(sp)
         lw t0, 64(sp)
         lw t1, 76(sp)
         mul t0, t0, t1
         sw t0, 72(sp)
         lw t0, 72(sp)
         addi t0, t0, 4
         sw t0, 80(sp)
         li t0, 4
         sw t0, 84(sp)
         lw t0, 72(sp)
         lw t1, 84(sp)
         add t0, t0, t1
         sw t0, 80(sp)
         lw t0, 80(sp)
         mv a0, t0
         call _malloc
         mv t0, a0
         sw t0, 88(sp)
         lw t0, 88(sp)
         mv t0, t0
         sw t0, 92(sp)
         lw t0, 92(sp)
         lw t1, 64(sp)
         sw t1, 0(t0)
         li t0, 1
         sw t0, 100(sp)
         lw t0, 100(sp)
         slli t0, t0, 2
         sw t0, 96(sp)
         lw t0, 92(sp)
         lw t1, 96(sp)
         add t0, t0, t1
         sw t0, 104(sp)
         lw t0, 104(sp)
         mv t0, t0
         sw t0, 108(sp)
         lui t0, %hi(pick)
         sw t0, 112(sp)
         lw t0, 112(sp)
         lw t1, 108(sp)
         sw t1, %lo(pick)(t0)
         lui t0, %hi(n)
         sw t0, 120(sp)
         lw t0, 120(sp)
         lw t0, %lo(n)(t0)
         sw t0, 116(sp)
         lw t0, 116(sp)
         addi t0, t0, 1
         sw t0, 124(sp)
         li t0, 1
         sw t0, 128(sp)
         lw t0, 116(sp)
         lw t1, 128(sp)
         add t0, t0, t1
         sw t0, 124(sp)
         li t0, 4
         sw t0, 136(sp)
         lw t0, 124(sp)
         lw t1, 136(sp)
         mul t0, t0, t1
         sw t0, 132(sp)
         lw t0, 132(sp)
         addi t0, t0, 4
         sw t0, 140(sp)
         li t0, 4
         sw t0, 144(sp)
         lw t0, 132(sp)
         lw t1, 144(sp)
         add t0, t0, t1
         sw t0, 140(sp)
         lw t0, 140(sp)
         mv a0, t0
         call _malloc
         mv t0, a0
         sw t0, 148(sp)
         lw t0, 148(sp)
         mv t0, t0
         sw t0, 152(sp)
         lw t0, 152(sp)
         lw t1, 124(sp)
         sw t1, 0(t0)
         li t0, 1
         sw t0, 160(sp)
         lw t0, 160(sp)
         slli t0, t0, 2
         sw t0, 156(sp)
         lw t0, 152(sp)
         lw t1, 156(sp)
         add t0, t0, t1
         sw t0, 164(sp)
         lw t0, 164(sp)
         mv t0, t0
         sw t0, 168(sp)
         lui t0, %hi(fst)
         sw t0, 172(sp)
         lw t0, 172(sp)
         lw t1, 168(sp)
         sw t1, %lo(fst)(t0)
         lui t0, %hi(n)
         sw t0, 180(sp)
         lw t0, 180(sp)
         lw t0, %lo(n)(t0)
         sw t0, 176(sp)
         lw t0, 176(sp)
         addi t0, t0, 1
         sw t0, 184(sp)
         li t0, 1
         sw t0, 188(sp)
         lw t0, 176(sp)
         lw t1, 188(sp)
         add t0, t0, t1
         sw t0, 184(sp)
         li t0, 4
         sw t0, 196(sp)
         lw t0, 184(sp)
         lw t1, 196(sp)
         mul t0, t0, t1
         sw t0, 192(sp)
         lw t0, 192(sp)
         addi t0, t0, 4
         sw t0, 200(sp)
         li t0, 4
         sw t0, 204(sp)
         lw t0, 192(sp)
         lw t1, 204(sp)
         add t0, t0, t1
         sw t0, 200(sp)
         lw t0, 200(sp)
         mv a0, t0
         call _malloc
         mv t0, a0
         sw t0, 208(sp)
         lw t0, 208(sp)
         mv t0, t0
         sw t0, 212(sp)
         lw t0, 212(sp)
         lw t1, 184(sp)
         sw t1, 0(t0)
         li t0, 1
         sw t0, 220(sp)
         lw t0, 220(sp)
         slli t0, t0, 2
         sw t0, 216(sp)
         lw t0, 212(sp)
         lw t1, 216(sp)
         add t0, t0, t1
         sw t0, 224(sp)
         lw t0, 224(sp)
         mv t0, t0
         sw t0, 228(sp)
         lui t0, %hi(dis)
         sw t0, 232(sp)
         lw t0, 232(sp)
         lw t1, 228(sp)
         sw t1, %lo(dis)(t0)
         lui t0, %hi(m)
         sw t0, 240(sp)
         lw t0, 240(sp)
         lw t0, %lo(m)(t0)
         sw t0, 236(sp)
         li t0, 2
         sw t0, 248(sp)
         lw t0, 248(sp)
         lw t1, 236(sp)
         mul t0, t0, t1
         sw t0, 244(sp)
         lw t0, 244(sp)
         addi t0, t0, 1
         sw t0, 252(sp)
         li t0, 1
         sw t0, 256(sp)
         lw t0, 244(sp)
         lw t1, 256(sp)
         add t0, t0, t1
         sw t0, 252(sp)
         li t0, 4
         sw t0, 264(sp)
         lw t0, 252(sp)
         lw t1, 264(sp)
         mul t0, t0, t1
         sw t0, 260(sp)
         lw t0, 260(sp)
         addi t0, t0, 4
         sw t0, 268(sp)
         li t0, 4
         sw t0, 272(sp)
         lw t0, 260(sp)
         lw t1, 272(sp)
         add t0, t0, t1
         sw t0, 268(sp)
         lw t0, 268(sp)
         mv a0, t0
         call _malloc
         mv t0, a0
         sw t0, 276(sp)
         lw t0, 276(sp)
         mv t0, t0
         sw t0, 280(sp)
         lw t0, 280(sp)
         lw t1, 252(sp)
         sw t1, 0(t0)
         li t0, 1
         sw t0, 288(sp)
         lw t0, 288(sp)
         slli t0, t0, 2
         sw t0, 284(sp)
         lw t0, 280(sp)
         lw t1, 284(sp)
         add t0, t0, t1
         sw t0, 292(sp)
         lw t0, 292(sp)
         mv t0, t0
         sw t0, 296(sp)
         lui t0, %hi(ed)
         sw t0, 300(sp)
         lw t0, 300(sp)
         lw t1, 296(sp)
         sw t1, %lo(ed)(t0)
         sw zero, 28(sp)
         j for_cond
main.exit:
         lw t0, 36(sp)
         mv ra, t0
         addi sp, sp, 1080
         ret
for_cond:
         lw t0, 28(sp)
         sw t0, 304(sp)
         lui t0, %hi(n)
         sw t0, 312(sp)
         lw t0, 312(sp)
         lw t0, %lo(n)(t0)
         sw t0, 308(sp)
         lw t0, 308(sp)
         lw t1, 304(sp)
         slt t0, t0, t1
         sw t0, 316(sp)
         lw t0, 316(sp)
         xori t0, t0, 1
         sw t0, 320(sp)
         lw t0, 320(sp)
         beq t0, zero, for_exit
         j for_body
for_body:
         lui t0, %hi(pick)
         sw t0, 328(sp)
         lw t0, 328(sp)
         lw t0, %lo(pick)(t0)
         sw t0, 324(sp)
         lw t0, 28(sp)
         sw t0, 332(sp)
         lw t0, 332(sp)
         slli t0, t0, 2
         sw t0, 336(sp)
         lw t0, 324(sp)
         lw t1, 336(sp)
         add t0, t0, t1
         sw t0, 340(sp)
         lw t0, 340(sp)
         sw zero, 0(t0)
         lui t0, %hi(fst)
         sw t0, 348(sp)
         lw t0, 348(sp)
         lw t0, %lo(fst)(t0)
         sw t0, 344(sp)
         lw t0, 28(sp)
         sw t0, 352(sp)
         lw t0, 352(sp)
         slli t0, t0, 2
         sw t0, 356(sp)
         lw t0, 344(sp)
         lw t1, 356(sp)
         add t0, t0, t1
         sw t0, 360(sp)
         lw t0, 360(sp)
         sw zero, 0(t0)
         lui t0, %hi(dis)
         sw t0, 368(sp)
         lw t0, 368(sp)
         lw t0, %lo(dis)(t0)
         sw t0, 364(sp)
         lw t0, 28(sp)
         sw t0, 372(sp)
         lw t0, 372(sp)
         slli t0, t0, 2
         sw t0, 376(sp)
         lw t0, 364(sp)
         lw t1, 376(sp)
         add t0, t0, t1
         sw t0, 380(sp)
         lui t0, %hi(MAX)
         sw t0, 388(sp)
         lw t0, 388(sp)
         lw t0, %lo(MAX)(t0)
         sw t0, 384(sp)
         lw t0, 380(sp)
         lw t1, 384(sp)
         sw t1, 0(t0)
         j for_step
for_step:
         lw t0, 28(sp)
         sw t0, 392(sp)
         lw t0, 392(sp)
         addi t0, t0, 1
         sw t0, 396(sp)
         li t0, 1
         sw t0, 400(sp)
         lw t0, 392(sp)
         lw t1, 400(sp)
         add t0, t0, t1
         sw t0, 396(sp)
         lw t1, 396(sp)
         sw t1, 28(sp)
         j for_cond
for_exit:
         li t0, 1
         sw t0, 404(sp)
         lw t1, 404(sp)
         sw t1, 28(sp)
         j for_cond.1
for_cond.1:
         lw t0, 28(sp)
         sw t0, 408(sp)
         lui t0, %hi(m)
         sw t0, 416(sp)
         lw t0, 416(sp)
         lw t0, %lo(m)(t0)
         sw t0, 412(sp)
         lw t0, 412(sp)
         lw t1, 408(sp)
         slt t0, t0, t1
         sw t0, 420(sp)
         lw t0, 420(sp)
         xori t0, t0, 1
         sw t0, 424(sp)
         lw t0, 424(sp)
         beq t0, zero, for_exit.1
         j for_body.1
for_body.1:
         call getInt
         mv t0, a0
         sw t0, 428(sp)
         lw t1, 428(sp)
         sw t1, 24(sp)
         call getInt
         mv t0, a0
         sw t0, 432(sp)
         lw t1, 432(sp)
         sw t1, 20(sp)
         call getInt
         mv t0, a0
         sw t0, 436(sp)
         lw t1, 436(sp)
         sw t1, 16(sp)
         lw t0, 24(sp)
         sw t0, 440(sp)
         lw t0, 20(sp)
         sw t0, 444(sp)
         lw t0, 16(sp)
         sw t0, 448(sp)
         lw t0, 440(sp)
         mv a0, t0
         lw t0, 444(sp)
         mv a1, t0
         lw t0, 448(sp)
         mv a2, t0
         call addedge
         lw t0, 20(sp)
         sw t0, 452(sp)
         lw t0, 24(sp)
         sw t0, 456(sp)
         lw t0, 16(sp)
         sw t0, 460(sp)
         lw t0, 452(sp)
         mv a0, t0
         lw t0, 456(sp)
         mv a1, t0
         lw t0, 460(sp)
         mv a2, t0
         call addedge
         j for_step.1
for_step.1:
         lw t0, 28(sp)
         sw t0, 464(sp)
         lw t0, 464(sp)
         addi t0, t0, 1
         sw t0, 468(sp)
         li t0, 1
         sw t0, 472(sp)
         lw t0, 464(sp)
         lw t1, 472(sp)
         add t0, t0, t1
         sw t0, 468(sp)
         lw t1, 468(sp)
         sw t1, 28(sp)
         j for_cond.1
for_exit.1:
         li t0, 1
         sw t0, 476(sp)
         lw t1, 476(sp)
         sw t1, 24(sp)
         li t0, 1
         sw t0, 480(sp)
         lw t1, 480(sp)
         sw t1, 28(sp)
         j for_cond.2
for_cond.2:
         lw t0, 28(sp)
         sw t0, 484(sp)
         lui t0, %hi(n)
         sw t0, 492(sp)
         lw t0, 492(sp)
         lw t0, %lo(n)(t0)
         sw t0, 488(sp)
         lw t0, 488(sp)
         addi t0, t0, -1
         sw t0, 496(sp)
         li t0, 1
         sw t0, 500(sp)
         lw t0, 488(sp)
         lw t1, 500(sp)
         sub t0, t0, t1
         sw t0, 496(sp)
         lw t0, 496(sp)
         lw t1, 484(sp)
         slt t0, t0, t1
         sw t0, 504(sp)
         lw t0, 504(sp)
         xori t0, t0, 1
         sw t0, 508(sp)
         lw t0, 508(sp)
         beq t0, zero, for_exit.2
         j for_body.2
for_body.2:
         lui t0, %hi(pick)
         sw t0, 516(sp)
         lw t0, 516(sp)
         lw t0, %lo(pick)(t0)
         sw t0, 512(sp)
         lw t0, 24(sp)
         sw t0, 520(sp)
         lw t0, 520(sp)
         slli t0, t0, 2
         sw t0, 524(sp)
         lw t0, 512(sp)
         lw t1, 524(sp)
         add t0, t0, t1
         sw t0, 528(sp)
         li t0, 1
         sw t0, 532(sp)
         lw t0, 528(sp)
         lw t1, 532(sp)
         sw t1, 0(t0)
         lui t0, %hi(fst)
         sw t0, 540(sp)
         lw t0, 540(sp)
         lw t0, %lo(fst)(t0)
         sw t0, 536(sp)
         lw t0, 24(sp)
         sw t0, 544(sp)
         lw t0, 544(sp)
         slli t0, t0, 2
         sw t0, 548(sp)
         lw t0, 536(sp)
         lw t1, 548(sp)
         add t0, t0, t1
         sw t0, 552(sp)
         lw t0, 552(sp)
         lw t0, 0(t0)
         sw t0, 556(sp)
         lw t1, 556(sp)
         sw t1, 12(sp)
         j for_cond.3
for_step.2:
         lw t0, 28(sp)
         sw t0, 560(sp)
         lw t0, 560(sp)
         addi t0, t0, 1
         sw t0, 564(sp)
         li t0, 1
         sw t0, 568(sp)
         lw t0, 560(sp)
         lw t1, 568(sp)
         add t0, t0, t1
         sw t0, 564(sp)
         lw t1, 564(sp)
         sw t1, 28(sp)
         j for_cond.2
for_exit.2:
         lui t0, %hi(ans)
         sw t0, 576(sp)
         lw t0, 576(sp)
         lw t0, %lo(ans)(t0)
         sw t0, 572(sp)
         lw t0, 572(sp)
         mv a0, t0
         call printInt
         sw zero, 32(sp)
         j main.exit
for_cond.3:
         lw t0, 12(sp)
         sw t0, 580(sp)
         lw t0, 580(sp)
         sub t0, t0, zero
         sw t0, 584(sp)
         lw t0, 584(sp)
         snez t0, t0
         sw t0, 588(sp)
         lw t0, 588(sp)
         beq t0, zero, for_exit.3
         j for_body.3
for_body.3:
         lui t0, %hi(ed)
         sw t0, 596(sp)
         lw t0, 596(sp)
         lw t0, %lo(ed)(t0)
         sw t0, 592(sp)
         lw t0, 12(sp)
         sw t0, 600(sp)
         lw t0, 600(sp)
         slli t0, t0, 2
         sw t0, 604(sp)
         lw t0, 592(sp)
         lw t1, 604(sp)
         add t0, t0, t1
         sw t0, 608(sp)
         lw t0, 608(sp)
         lw t0, 0(t0)
         sw t0, 612(sp)
         li t0, 1
         sw t0, 620(sp)
         lw t0, 620(sp)
         slli t0, t0, 2
         sw t0, 616(sp)
         lw t0, 612(sp)
         lw t1, 616(sp)
         add t0, t0, t1
         sw t0, 624(sp)
         lw t0, 624(sp)
         lw t0, 0(t0)
         sw t0, 628(sp)
         lw t1, 628(sp)
         sw t1, 8(sp)
         lui t0, %hi(pick)
         sw t0, 636(sp)
         lw t0, 636(sp)
         lw t0, %lo(pick)(t0)
         sw t0, 632(sp)
         lw t0, 8(sp)
         sw t0, 640(sp)
         lw t0, 640(sp)
         slli t0, t0, 2
         sw t0, 644(sp)
         lw t0, 632(sp)
         lw t1, 644(sp)
         add t0, t0, t1
         sw t0, 648(sp)
         lw t0, 648(sp)
         lw t0, 0(t0)
         sw t0, 652(sp)
         lw t0, 652(sp)
         sub t0, t0, zero
         sw t0, 656(sp)
         lw t0, 656(sp)
         seqz t0, t0
         sw t0, 660(sp)
         lw t0, 660(sp)
         andi t0, t0, 1
         sw t0, 664(sp)
         lw t0, 664(sp)
         mv t0, t0
         sw t0, 668(sp)
         lw t1, 668(sp)
         sw t1, 4(sp)
         lw t0, 660(sp)
         beq t0, zero, logicExitBlock
         j noShortCutBlock
for_step.3:
         lui t0, %hi(ed)
         sw t0, 676(sp)
         lw t0, 676(sp)
         lw t0, %lo(ed)(t0)
         sw t0, 672(sp)
         lw t0, 12(sp)
         sw t0, 680(sp)
         lw t0, 680(sp)
         slli t0, t0, 2
         sw t0, 684(sp)
         lw t0, 672(sp)
         lw t1, 684(sp)
         add t0, t0, t1
         sw t0, 688(sp)
         lw t0, 688(sp)
         lw t0, 0(t0)
         sw t0, 692(sp)
         li t0, 2
         sw t0, 700(sp)
         lw t0, 700(sp)
         slli t0, t0, 2
         sw t0, 696(sp)
         lw t0, 692(sp)
         lw t1, 696(sp)
         add t0, t0, t1
         sw t0, 704(sp)
         lw t0, 704(sp)
         lw t0, 0(t0)
         sw t0, 708(sp)
         lw t1, 708(sp)
         sw t1, 12(sp)
         j for_cond.3
for_exit.3:
         sw zero, 24(sp)
         li t0, 1
         sw t0, 712(sp)
         lw t1, 712(sp)
         sw t1, 12(sp)
         j for_cond.4
if_true:
         lui t0, %hi(dis)
         sw t0, 720(sp)
         lw t0, 720(sp)
         lw t0, %lo(dis)(t0)
         sw t0, 716(sp)
         lw t0, 8(sp)
         sw t0, 724(sp)
         lw t0, 724(sp)
         slli t0, t0, 2
         sw t0, 728(sp)
         lw t0, 716(sp)
         lw t1, 728(sp)
         add t0, t0, t1
         sw t0, 732(sp)
         lui t0, %hi(ed)
         sw t0, 740(sp)
         lw t0, 740(sp)
         lw t0, %lo(ed)(t0)
         sw t0, 736(sp)
         lw t0, 12(sp)
         sw t0, 744(sp)
         lw t0, 744(sp)
         slli t0, t0, 2
         sw t0, 748(sp)
         lw t0, 736(sp)
         lw t1, 748(sp)
         add t0, t0, t1
         sw t0, 752(sp)
         lw t0, 752(sp)
         lw t0, 0(t0)
         sw t0, 756(sp)
         li t0, 3
         sw t0, 764(sp)
         lw t0, 764(sp)
         slli t0, t0, 2
         sw t0, 760(sp)
         lw t0, 756(sp)
         lw t1, 760(sp)
         add t0, t0, t1
         sw t0, 768(sp)
         lw t0, 768(sp)
         lw t0, 0(t0)
         sw t0, 772(sp)
         lw t0, 732(sp)
         lw t1, 772(sp)
         sw t1, 0(t0)
         j if_exit
if_false:
         j if_exit
if_exit:
         j for_step.3
noShortCutBlock:
         lui t0, %hi(ed)
         sw t0, 780(sp)
         lw t0, 780(sp)
         lw t0, %lo(ed)(t0)
         sw t0, 776(sp)
         lw t0, 12(sp)
         sw t0, 784(sp)
         lw t0, 784(sp)
         slli t0, t0, 2
         sw t0, 788(sp)
         lw t0, 776(sp)
         lw t1, 788(sp)
         add t0, t0, t1
         sw t0, 792(sp)
         lw t0, 792(sp)
         lw t0, 0(t0)
         sw t0, 796(sp)
         li t0, 3
         sw t0, 804(sp)
         lw t0, 804(sp)
         slli t0, t0, 2
         sw t0, 800(sp)
         lw t0, 796(sp)
         lw t1, 800(sp)
         add t0, t0, t1
         sw t0, 808(sp)
         lw t0, 808(sp)
         lw t0, 0(t0)
         sw t0, 812(sp)
         lui t0, %hi(dis)
         sw t0, 820(sp)
         lw t0, 820(sp)
         lw t0, %lo(dis)(t0)
         sw t0, 816(sp)
         lw t0, 8(sp)
         sw t0, 824(sp)
         lw t0, 824(sp)
         slli t0, t0, 2
         sw t0, 828(sp)
         lw t0, 816(sp)
         lw t1, 828(sp)
         add t0, t0, t1
         sw t0, 832(sp)
         lw t0, 832(sp)
         lw t0, 0(t0)
         sw t0, 836(sp)
         lw t0, 812(sp)
         lw t1, 836(sp)
         slt t0, t0, t1
         sw t0, 840(sp)
         lw t0, 660(sp)
         lw t1, 840(sp)
         and t0, t0, t1
         sw t0, 844(sp)
         lw t0, 844(sp)
         andi t0, t0, 1
         sw t0, 848(sp)
         lw t0, 848(sp)
         mv t0, t0
         sw t0, 852(sp)
         lw t1, 852(sp)
         sw t1, 4(sp)
         j logicExitBlock
logicExitBlock:
         lw t0, 4(sp)
         sw t0, 856(sp)
         lw t0, 856(sp)
         andi t0, t0, 1
         sw t0, 860(sp)
         lw t0, 860(sp)
         mv t0, t0
         sw t0, 864(sp)
         lw t0, 864(sp)
         beq t0, zero, if_false
         j if_true
for_cond.4:
         lw t0, 12(sp)
         sw t0, 868(sp)
         lui t0, %hi(n)
         sw t0, 876(sp)
         lw t0, 876(sp)
         lw t0, %lo(n)(t0)
         sw t0, 872(sp)
         lw t0, 872(sp)
         lw t1, 868(sp)
         slt t0, t0, t1
         sw t0, 880(sp)
         lw t0, 880(sp)
         xori t0, t0, 1
         sw t0, 884(sp)
         lw t0, 884(sp)
         beq t0, zero, for_exit.4
         j for_body.4
for_body.4:
         lui t0, %hi(pick)
         sw t0, 892(sp)
         lw t0, 892(sp)
         lw t0, %lo(pick)(t0)
         sw t0, 888(sp)
         lw t0, 12(sp)
         sw t0, 896(sp)
         lw t0, 896(sp)
         slli t0, t0, 2
         sw t0, 900(sp)
         lw t0, 888(sp)
         lw t1, 900(sp)
         add t0, t0, t1
         sw t0, 904(sp)
         lw t0, 904(sp)
         lw t0, 0(t0)
         sw t0, 908(sp)
         lw t0, 908(sp)
         sub t0, t0, zero
         sw t0, 912(sp)
         lw t0, 912(sp)
         seqz t0, t0
         sw t0, 916(sp)
         lw t0, 916(sp)
         andi t0, t0, 1
         sw t0, 920(sp)
         lw t0, 920(sp)
         mv t0, t0
         sw t0, 924(sp)
         lw t1, 924(sp)
         sw t1, 0(sp)
         lw t0, 916(sp)
         beq t0, zero, logicExitBlock.1
         j noShortCutBlock.1
for_step.4:
         lw t0, 12(sp)
         sw t0, 928(sp)
         lw t0, 928(sp)
         addi t0, t0, 1
         sw t0, 932(sp)
         li t0, 1
         sw t0, 936(sp)
         lw t0, 928(sp)
         lw t1, 936(sp)
         add t0, t0, t1
         sw t0, 932(sp)
         lw t1, 932(sp)
         sw t1, 12(sp)
         j for_cond.4
for_exit.4:
         lw t0, 24(sp)
         sw t0, 940(sp)
         lw t0, 940(sp)
         sub t0, t0, zero
         sw t0, 944(sp)
         lw t0, 944(sp)
         seqz t0, t0
         sw t0, 948(sp)
         lw t0, 948(sp)
         beq t0, zero, if_false.2
         j if_true.2
if_true.1:
         lw t0, 12(sp)
         sw t0, 952(sp)
         lw t1, 952(sp)
         sw t1, 24(sp)
         j if_exit.1
if_false.1:
         j if_exit.1
if_exit.1:
         j for_step.4
noShortCutBlock.1:
         lui t0, %hi(dis)
         sw t0, 960(sp)
         lw t0, 960(sp)
         lw t0, %lo(dis)(t0)
         sw t0, 956(sp)
         lw t0, 12(sp)
         sw t0, 964(sp)
         lw t0, 964(sp)
         slli t0, t0, 2
         sw t0, 968(sp)
         lw t0, 956(sp)
         lw t1, 968(sp)
         add t0, t0, t1
         sw t0, 972(sp)
         lw t0, 972(sp)
         lw t0, 0(t0)
         sw t0, 976(sp)
         lui t0, %hi(dis)
         sw t0, 984(sp)
         lw t0, 984(sp)
         lw t0, %lo(dis)(t0)
         sw t0, 980(sp)
         lw t0, 24(sp)
         sw t0, 988(sp)
         lw t0, 988(sp)
         slli t0, t0, 2
         sw t0, 992(sp)
         lw t0, 980(sp)
         lw t1, 992(sp)
         add t0, t0, t1
         sw t0, 996(sp)
         lw t0, 996(sp)
         lw t0, 0(t0)
         sw t0, 1000(sp)
         lw t0, 976(sp)
         lw t1, 1000(sp)
         slt t0, t0, t1
         sw t0, 1004(sp)
         lw t0, 916(sp)
         lw t1, 1004(sp)
         and t0, t0, t1
         sw t0, 1008(sp)
         lw t0, 1008(sp)
         andi t0, t0, 1
         sw t0, 1012(sp)
         lw t0, 1012(sp)
         mv t0, t0
         sw t0, 1016(sp)
         lw t1, 1016(sp)
         sw t1, 0(sp)
         j logicExitBlock.1
logicExitBlock.1:
         lw t0, 0(sp)
         sw t0, 1020(sp)
         lw t0, 1020(sp)
         andi t0, t0, 1
         sw t0, 1024(sp)
         lw t0, 1024(sp)
         mv t0, t0
         sw t0, 1028(sp)
         lw t0, 1028(sp)
         beq t0, zero, if_false.1
         j if_true.1
if_true.2:
         addi t0, zero, -1
         sw t0, 1032(sp)
         li t0, 1
         sw t0, 1036(sp)
         lw t1, 1036(sp)
         sub t0, zero, t1
         sw t0, 1032(sp)
         lw t0, 1032(sp)
         mv a0, t0
         call printInt
         sw zero, 32(sp)
         j main.exit
if_false.2:
         j if_exit.2
if_exit.2:
         lui t0, %hi(ans)
         sw t0, 1044(sp)
         lw t0, 1044(sp)
         lw t0, %lo(ans)(t0)
         sw t0, 1040(sp)
         lui t0, %hi(dis)
         sw t0, 1052(sp)
         lw t0, 1052(sp)
         lw t0, %lo(dis)(t0)
         sw t0, 1048(sp)
         lw t0, 24(sp)
         sw t0, 1056(sp)
         lw t0, 1056(sp)
         slli t0, t0, 2
         sw t0, 1060(sp)
         lw t0, 1048(sp)
         lw t1, 1060(sp)
         add t0, t0, t1
         sw t0, 1064(sp)
         lw t0, 1064(sp)
         lw t0, 0(t0)
         sw t0, 1068(sp)
         lw t0, 1040(sp)
         lw t1, 1068(sp)
         add t0, t0, t1
         sw t0, 1072(sp)
         lui t0, %hi(ans)
         sw t0, 1076(sp)
         lw t0, 1076(sp)
         lw t1, 1072(sp)
         sw t1, %lo(ans)(t0)
         j for_step.2

  .text
  .globl addedge
  .type addedge, @function
addedge:
addedge.entry:
         addi sp, sp, -304
         mv t0, ra
         sw t0, 12(sp)
         lw t1, 16(sp)
         sw t1, 8(sp)
         lw t1, 20(sp)
         sw t1, 4(sp)
         lw t1, 24(sp)
         sw t1, 0(sp)
         lui t0, %hi(tot)
         sw t0, 32(sp)
         lw t0, 32(sp)
         lw t0, %lo(tot)(t0)
         sw t0, 28(sp)
         lw t0, 28(sp)
         addi t0, t0, 1
         sw t0, 36(sp)
         li t0, 1
         sw t0, 40(sp)
         lw t0, 28(sp)
         lw t1, 40(sp)
         add t0, t0, t1
         sw t0, 36(sp)
         lui t0, %hi(tot)
         sw t0, 44(sp)
         lw t0, 44(sp)
         lw t1, 36(sp)
         sw t1, %lo(tot)(t0)
         lui t0, %hi(ed)
         sw t0, 52(sp)
         lw t0, 52(sp)
         lw t0, %lo(ed)(t0)
         sw t0, 48(sp)
         lui t0, %hi(tot)
         sw t0, 60(sp)
         lw t0, 60(sp)
         lw t0, %lo(tot)(t0)
         sw t0, 56(sp)
         lw t0, 56(sp)
         slli t0, t0, 2
         sw t0, 64(sp)
         lw t0, 48(sp)
         lw t1, 64(sp)
         add t0, t0, t1
         sw t0, 68(sp)
         li t0, 16
         sw t0, 72(sp)
         lw t0, 72(sp)
         mv a0, t0
         call _malloc
         mv t0, a0
         sw t0, 76(sp)
         lw t0, 76(sp)
         mv t0, t0
         sw t0, 80(sp)
         lw t0, 80(sp)
         mv a0, t0
         call Edge.Edge
         lw t0, 68(sp)
         lw t1, 80(sp)
         sw t1, 0(t0)
         lui t0, %hi(ed)
         sw t0, 88(sp)
         lw t0, 88(sp)
         lw t0, %lo(ed)(t0)
         sw t0, 84(sp)
         lui t0, %hi(tot)
         sw t0, 96(sp)
         lw t0, 96(sp)
         lw t0, %lo(tot)(t0)
         sw t0, 92(sp)
         lw t0, 92(sp)
         slli t0, t0, 2
         sw t0, 100(sp)
         lw t0, 84(sp)
         lw t1, 100(sp)
         add t0, t0, t1
         sw t0, 104(sp)
         lw t0, 104(sp)
         lw t0, 0(t0)
         sw t0, 108(sp)
         slli t0, zero, 2
         sw t0, 112(sp)
         lw t0, 108(sp)
         lw t1, 112(sp)
         add t0, t0, t1
         sw t0, 116(sp)
         lw t0, 8(sp)
         sw t0, 120(sp)
         lw t0, 116(sp)
         lw t1, 120(sp)
         sw t1, 0(t0)
         lui t0, %hi(ed)
         sw t0, 128(sp)
         lw t0, 128(sp)
         lw t0, %lo(ed)(t0)
         sw t0, 124(sp)
         lui t0, %hi(tot)
         sw t0, 136(sp)
         lw t0, 136(sp)
         lw t0, %lo(tot)(t0)
         sw t0, 132(sp)
         lw t0, 132(sp)
         slli t0, t0, 2
         sw t0, 140(sp)
         lw t0, 124(sp)
         lw t1, 140(sp)
         add t0, t0, t1
         sw t0, 144(sp)
         lw t0, 144(sp)
         lw t0, 0(t0)
         sw t0, 148(sp)
         li t0, 1
         sw t0, 156(sp)
         lw t0, 156(sp)
         slli t0, t0, 2
         sw t0, 152(sp)
         lw t0, 148(sp)
         lw t1, 152(sp)
         add t0, t0, t1
         sw t0, 160(sp)
         lw t0, 4(sp)
         sw t0, 164(sp)
         lw t0, 160(sp)
         lw t1, 164(sp)
         sw t1, 0(t0)
         lui t0, %hi(ed)
         sw t0, 172(sp)
         lw t0, 172(sp)
         lw t0, %lo(ed)(t0)
         sw t0, 168(sp)
         lui t0, %hi(tot)
         sw t0, 180(sp)
         lw t0, 180(sp)
         lw t0, %lo(tot)(t0)
         sw t0, 176(sp)
         lw t0, 176(sp)
         slli t0, t0, 2
         sw t0, 184(sp)
         lw t0, 168(sp)
         lw t1, 184(sp)
         add t0, t0, t1
         sw t0, 188(sp)
         lw t0, 188(sp)
         lw t0, 0(t0)
         sw t0, 192(sp)
         li t0, 3
         sw t0, 200(sp)
         lw t0, 200(sp)
         slli t0, t0, 2
         sw t0, 196(sp)
         lw t0, 192(sp)
         lw t1, 196(sp)
         add t0, t0, t1
         sw t0, 204(sp)
         lw t0, 0(sp)
         sw t0, 208(sp)
         lw t0, 204(sp)
         lw t1, 208(sp)
         sw t1, 0(t0)
         lui t0, %hi(ed)
         sw t0, 216(sp)
         lw t0, 216(sp)
         lw t0, %lo(ed)(t0)
         sw t0, 212(sp)
         lui t0, %hi(tot)
         sw t0, 224(sp)
         lw t0, 224(sp)
         lw t0, %lo(tot)(t0)
         sw t0, 220(sp)
         lw t0, 220(sp)
         slli t0, t0, 2
         sw t0, 228(sp)
         lw t0, 212(sp)
         lw t1, 228(sp)
         add t0, t0, t1
         sw t0, 232(sp)
         lw t0, 232(sp)
         lw t0, 0(t0)
         sw t0, 236(sp)
         li t0, 2
         sw t0, 244(sp)
         lw t0, 244(sp)
         slli t0, t0, 2
         sw t0, 240(sp)
         lw t0, 236(sp)
         lw t1, 240(sp)
         add t0, t0, t1
         sw t0, 248(sp)
         lui t0, %hi(fst)
         sw t0, 256(sp)
         lw t0, 256(sp)
         lw t0, %lo(fst)(t0)
         sw t0, 252(sp)
         lw t0, 8(sp)
         sw t0, 260(sp)
         lw t0, 260(sp)
         slli t0, t0, 2
         sw t0, 264(sp)
         lw t0, 252(sp)
         lw t1, 264(sp)
         add t0, t0, t1
         sw t0, 268(sp)
         lw t0, 268(sp)
         lw t0, 0(t0)
         sw t0, 272(sp)
         lw t0, 248(sp)
         lw t1, 272(sp)
         sw t1, 0(t0)
         lui t0, %hi(fst)
         sw t0, 280(sp)
         lw t0, 280(sp)
         lw t0, %lo(fst)(t0)
         sw t0, 276(sp)
         lw t0, 8(sp)
         sw t0, 284(sp)
         lw t0, 284(sp)
         slli t0, t0, 2
         sw t0, 288(sp)
         lw t0, 276(sp)
         lw t1, 288(sp)
         add t0, t0, t1
         sw t0, 292(sp)
         lui t0, %hi(tot)
         sw t0, 300(sp)
         lw t0, 300(sp)
         lw t0, %lo(tot)(t0)
         sw t0, 296(sp)
         lw t0, 292(sp)
         lw t1, 296(sp)
         sw t1, 0(t0)
         j addedge.exit
addedge.exit:
         lw t0, 12(sp)
         mv ra, t0
         addi sp, sp, 304
         ret

.section .bss
 .globl MAX
 .type MAX, @object
MAX:
  .word 0
 .size MAX, 4


 .globl pick
 .type pick, @object
pick:
  .word 0
 .size pick, 4


 .globl fst
 .type fst, @object
fst:
  .word 0
 .size fst, 4


 .globl ans
 .type ans, @object
ans:
  .word 0
 .size ans, 4


 .globl tot
 .type tot, @object
tot:
  .word 0
 .size tot, 4


 .globl m
 .type m, @object
m:
  .word 0
 .size m, 4


 .globl n
 .type n, @object
n:
  .word 0
 .size n, 4


 .globl dis
 .type dis, @object
dis:
  .word 0
 .size dis, 4


 .globl ed
 .type ed, @object
ed:
  .word 0
 .size ed, 4


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

