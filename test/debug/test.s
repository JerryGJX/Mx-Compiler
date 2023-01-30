  .text
  .globl _init_func
  .type _init_func, @function
_init_func:
_init_func.entry:
  addi sp, sp, -52
  sw t0, 0(sp)
  mv t0, ra
  sw t0, 4(sp)
  mv t0, s0
  sw t0, 8(sp)
  mv t0, s1
  sw t0, 12(sp)
  mv t0, s2
  sw t0, 16(sp)
  mv t0, s3
  sw t0, 20(sp)
  mv t0, s4
  sw t0, 24(sp)
  mv t0, s5
  sw t0, 28(sp)
  mv t0, s6
  sw t0, 32(sp)
  mv t0, s7
  sw t0, 36(sp)
  mv t0, s8
  sw t0, 40(sp)
  mv t0, s9
  sw t0, 44(sp)
  mv t0, s10
  sw t0, 48(sp)
  mv t0, s11
  j _init_func.exit
_init_func.exit:
  lw t0, 4(sp)
  mv s0, t0
  lw t0, 8(sp)
  mv s1, t0
  lw t0, 12(sp)
  mv s2, t0
  lw t0, 16(sp)
  mv s3, t0
  lw t0, 20(sp)
  mv s4, t0
  lw t0, 24(sp)
  mv s5, t0
  lw t0, 28(sp)
  mv s6, t0
  lw t0, 32(sp)
  mv s7, t0
  lw t0, 36(sp)
  mv s8, t0
  lw t0, 40(sp)
  mv s9, t0
  lw t0, 44(sp)
  mv s10, t0
  lw t0, 48(sp)
  mv s11, t0
  lw t0, 0(sp)
  mv ra, t0
  addi sp, sp, 52
  ret

  .text
  .globl main
  .type main, @function
main:
main.entry:
  addi sp, sp, -56
  sw t0, 4(sp)
  mv t0, ra
  sw t0, 8(sp)
  mv t0, s0
  sw t0, 12(sp)
  mv t0, s1
  sw t0, 16(sp)
  mv t0, s2
  sw t0, 20(sp)
  mv t0, s3
  sw t0, 24(sp)
  mv t0, s4
  sw t0, 28(sp)
  mv t0, s5
  sw t0, 32(sp)
  mv t0, s6
  sw t0, 36(sp)
  mv t0, s7
  sw t0, 40(sp)
  mv t0, s8
  sw t0, 44(sp)
  mv t0, s9
  sw t0, 48(sp)
  mv t0, s10
  sw t0, 52(sp)
  mv t0, s11
  call _init_func
  sw zero, 0(sp)
  sw zero, 0(sp)
  j main.exit
main.exit:
  lw t0, 8(sp)
  mv s0, t0
  lw t0, 12(sp)
  mv s1, t0
  lw t0, 16(sp)
  mv s2, t0
  lw t0, 20(sp)
  mv s3, t0
  lw t0, 24(sp)
  mv s4, t0
  lw t0, 28(sp)
  mv s5, t0
  lw t0, 32(sp)
  mv s6, t0
  lw t0, 36(sp)
  mv s7, t0
  lw t0, 40(sp)
  mv s8, t0
  lw t0, 44(sp)
  mv s9, t0
  lw t0, 48(sp)
  mv s10, t0
  lw t0, 52(sp)
  mv s11, t0
  lw t0, 4(sp)
  mv ra, t0
  addi sp, sp, 56
  ret

.section .bss
.section .rodata

