## Codegen debug

```
int []A;
int B;

int main(){
A[0] = 1;
B = 2;
B = A[0];
}

```



```
  .text
  .globl _init_func
  .type _init_func, @function
_init_func:
_init_func.entry:
         addi sp, sp, -8
         mv t0, ra
         sw t0, 0(sp)
         lui t0, %hi(A)
         sw t0, 4(sp)
         lw t0, 4(sp)
         sw zero, %lo(A)(t0)
         j _init_func.exit
_init_func.exit:
         lw t0, 0(sp)
         mv ra, t0
         addi sp, sp, 8
         ret

  .text
  .globl main
  .type main, @function
main:
main.entry:
         addi sp, sp, -60
         mv t0, ra
         sw t0, 4(sp)
         call _init_func
         sw zero, 0(sp)
         lui t0, %hi(A)
         sw t0, 12(sp)
         lw t0, 12(sp)
         lw t0, %lo(A)(t0)
         sw t0, 8(sp)      //此时 t0 为 A 的地址
         slli t0, zero, 2  //计算 A[0] 的地址偏移
         sw t0, 16(sp)
         lw t0, 8(sp)
         lw t1, 16(sp)
         add t0, t0, t1    //计算 A[0]
         sw t0, 20(sp)
         li t0, 1
         sw t0, 24(sp)     //将 1 存入 24(sp)
         lw t0, 20(sp)
         lw t1, 24(sp)
         sw t1, 0(t0)      //A[0] = 1
         li t0, 2
         sw t0, 28(sp)     //将 2 存入 28(sp)
         lui t0, %hi(B)
         sw t0, 32(sp)
         lw t0, 32(sp)
         lw t1, 28(sp)     //成功 load 2
         sw t1, %lo(B)(t0) //%lo(B)(t0) 为 B 的地址
         lui t0, %hi(A)
         sw t0, 40(sp)
         lw t0, 40(sp)
         lw t0, %lo(A)(t0)  //t0 为 A 的地址
         sw t0, 36(sp)      //将 A 的地址存入 36(sp)
         slli t0, zero, 2   //计算 A[0] 的地址偏移
         sw t0, 44(sp)      //将 A[0] 的地址偏移存入 44(sp)
         lw t0, 36(sp)      //t0 为 A 的地址
         lw t1, 44(sp)      //t1 为 A[0] 的地址偏移
         add t0, t0, t1     //t0 为 A[0] 的地址
         sw t0, 48(sp)      //将 A[0] 的地址存入 48(sp)
         lw t0, 0(sp)       //出错，此处应为 lw t0, 48(sp)
         sw t0, 52(sp)
         lui t0, %hi(B)
         sw t0, 56(sp)
         lw t0, 56(sp)
         lw t1, 52(sp)
         sw t1, %lo(B)(t0)
         j main.exit
main.exit:
         lw t0, 4(sp)
         mv ra, t0
         addi sp, sp, 60
         ret

.section .bss
 .globl A
 .type A, @object
A:
  .word 0
 .size A, 4
```

