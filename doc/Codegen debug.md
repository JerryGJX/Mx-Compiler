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



```
class vector{
	int[] data;
	int init(){
		data = new int[10];
		return getDim();
	}

	int getDim(){
		int A = data.size();
		return A;
	}
};

int main(){
	vector x = new vector;
	int A = x.init();
	printInt(A);
	return A;
}
```





```
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
  .globl vector.init
  .type vector.init, @function
vector.init:
vector.init.entry:
         addi sp, sp, -72
         mv t0, ra
         sw t0, 4(sp)
         slli t0, zero, 2
         sw t0, 8(sp)
         lw t1, 8(sp)
         add t0, a0, t1
         sw t0, 12(sp)
         li t0, 10
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
         li t0, 10
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
         lw t0, 12(sp)
         lw t1, 60(sp)
         sw t1, 0(t0)
         mv a0, a0
         call vector.getDim
         mv t0, a0
         sw t0, 64(sp)
         lw t1, 64(sp)
         sw t1, 0(sp)
         j vector.init.exit
vector.init.exit:
         lw t0, 0(sp)
         sw t0, 68(sp)
         lw t0, 68(sp)
         mv a0, t0
         lw t0, 4(sp)
         mv ra, t0
         addi sp, sp, 72
         ret

  .text
  .globl main
  .type main, @function
main:
main.entry:
         addi sp, sp, -48
         mv t0, ra
         sw t0, 12(sp)              //由于 spillArg = 0, allocCnt = 3, 所以 spillReg 从 12 开始
         call _init_func
         sw zero, 8(sp)             //main 的返回值在 8(sp) 处
         li t0, 4                   //vector 大小为 4
         sw t0, 16(sp)              //vector 大小在 16(sp) 处
         lw t0, 16(sp)
         mv a0, t0                  //将 vector 大小传入 a0 作为 new vector 时调用的 _malloc 的参数
         call _malloc
         mv t0, a0                  //将 _malloc 的返回值传入 t0
         sw t0, 20(sp)              //将 t0 传入 20(sp) 处, 即指向 x 的指针在 20(sp) 处
         lw t0, 20(sp)
         mv t0, t0
         sw t0, 24(sp)              //蜜汁操作: 将 t0 传入 24(sp) 处, 即指向 x 的也在 24(sp) 处
         lw t0, 24(sp)
         mv a0, t0
         call vector.vector
         lw t1, 24(sp)              //将指向 x 的指针传入 t1
         sw t1, 4(sp)               //将 t1 传入 4(sp) 处, 即指向 x 的指针在 4(sp) 处
         lw t0, 4(sp)
         sw t0, 28(sp)              //将 t0 传入 28(sp) 处, 即指向 x 的指针在 28(sp) 处
         lw t0, 28(sp)
         mv a0, t0
         call vector.init           //调用 vector.init
         mv t0, a0                  //将 vector.init 的返回值传入 t0
         sw t0, 32(sp)              //将 x.init() 的返回值传入 32(sp) 处
         lw t1, 32(sp)
         sw t1, 0(sp)               //将 t1 传入 0(sp) 处, 即 x.init() 的返回值在 0(sp) 处, A[0(sp)]
         lw t0, 0(sp)               //取出 A
         sw t0, 36(sp)              //将 A 传入 36(sp) 处, 即 A[36(sp)]
         lw t0, 36(sp)              
         mv a0, t0
         call printInt
         lw t0, 0(sp)               //取出 A[0(sp)]
         sw t0, 40(sp)              //将 A[0(sp)] 传入 40(sp) 处, 即 A[40(sp)]
         lw t1, 40(sp)              //取出 A[40(sp)]
         sw t1, 8(sp)               //将 A[40(sp)] 传入 8(sp) 处, 即 A[8(sp)]
         j main.exit
main.exit:
         lw t0, 8(sp)               //取出 A[8(sp)]
         sw t0, 44(sp)              //将 A[8(sp)] 传入 44(sp) 处, 即 A[44(sp)]
         lw t0, 44(sp)              //取出 A[44(sp)]
         mv a0, t0                  //将 A[44(sp)] 传入 a0
         lw t0, 12(sp)              //取出 ra 到 t0
         mv ra, t0                  //将 ra 传入 ra
         addi sp, sp, 48
         ret

  .text
  .globl vector.vector
  .type vector.vector, @function
vector.vector:
vector.vector.entry:
         addi sp, sp, -4
         mv t0, ra
         sw t0, 0(sp)
         j vector.vector.exit
vector.vector.exit:
         lw t0, 0(sp)
         mv ra, t0
         addi sp, sp, 4
         ret

  .text
  .globl vector.getDim
  .type vector.getDim, @function
vector.getDim:
vector.getDim.entry:
         addi sp, sp, -52
         mv t0, ra
         sw t0, 8(sp)
         slli t0, zero, 2
         sw t0, 12(sp)
         lw t1, 12(sp)
         add t0, a0, t1
         sw t0, 16(sp)
         lw t0, 16(sp)
         lw t0, 0(t0)
         sw t0, 20(sp)
         lw t0, 20(sp)
         mv t0, t0
         sw t0, 24(sp)
         li t0, -1
         sw t0, 32(sp)
         lw t0, 32(sp)
         slli t0, t0, 2
         sw t0, 28(sp)
         lw t0, 24(sp)
         lw t1, 28(sp)
         add t0, t0, t1
         sw t0, 36(sp)
         lw t0, 36(sp)
         lw t0, 0(t0)
         sw t0, 40(sp)
         lw t1, 40(sp)
         sw t1, 0(sp)
         lw t0, 0(sp)
         sw t0, 44(sp)
         lw t1, 44(sp)
         sw t1, 4(sp)
         j vector.getDim.exit
vector.getDim.exit:
         lw t0, 4(sp)
         sw t0, 48(sp)
         lw t0, 48(sp)
         mv a0, t0
         lw t0, 8(sp)
         mv ra, t0
         addi sp, sp, 52
         ret
```

