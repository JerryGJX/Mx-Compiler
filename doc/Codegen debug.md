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





成员函数没有把 `this` 存下来导致 $a_0$ 被覆盖

```
auipc t1, 0
                # t1: 0 -> 0
jalr ra, 252(t1)
                # ra: 0 -> 8
addi sp, sp, -32
                # sp: 536870912 -> 536870880
addi t0, ra, 0
                # t0: 0 -> 8
sw t0, 8(sp)
                # stored value = 8
auipc t1, 4294967295
                # t1: 0 -> 4294963464
jalr ra, 3896(t1)
                # ra: 8 -> 272
addi sp, sp, -4
                # sp: 536870880 -> 536870876
addi t0, ra, 0
                # t0: 8 -> 272
sw t0, 0(sp)
                # stored value = 272
jal zero, 2  # _init_func.exit
                # zero: 0 -> 80
addi ra, t0, 0
                # ra: 272 -> 272
addi sp, sp, 4
                # sp: 536870876 -> 536870880
jalr zero, 0(ra)
                # zero: 0 -> 92
sw zero, 4(sp)
                # stored value = 0
addi t0, zero, 12
                # t0: 272 -> 12
sw t0, 12(sp)
                # stored value = 12
addi a0, t0, 0
                # a0: 0 -> 12
auipc t1, 0
                # t1: 4294963464 -> 288
jalr ra, 1008(t1)
                # ra: 272 -> 296
addi sp, sp, -16
                # sp: 536870880 -> 536870864
sw ra, 12(sp)
                # stored value = 296
sw s0, 8(sp)
                # stored value = 0
addi s0, sp, 16
                # s0: 0 -> 536870880
sw a0, -12(s0)
                # stored value = 12
lw a0, -12(s0)
                # a0: 12 -> 12
auipc t1, 4294967295
                # t1: 288 -> 4294964520
jalr ra, 2800(t1)
                # ra: 296 -> 1328
call libc-24
                # return value = 1888
lw ra, 12(sp)
                # ra: 5988 -> 296
lw s0, 8(sp)
                # s0: 536870880 -> 0
addi sp, sp, 16
                # sp: 536870864 -> 536870880
jalr zero, 0(ra)
                # zero: 0 -> 1344
addi t0, a0, 0
                # t0: 4672 -> 1888
sw t0, 16(sp)
                # stored value = 1888
addi t0, t0, 0
                # t0: 1888 -> 1888
sw t0, 20(sp)
                # stored value = 1888
addi a0, t0, 0
                # a0: 1888 -> 1888
auipc t1, 0
                # t1: 1884 -> 316
jalr ra, 72(t1)
                # ra: 296 -> 324
addi sp, sp, -36
                # sp: 536870880 -> 536870844
addi t0, ra, 0
                # t0: 1888 -> 324
sw t0, 0(sp)
                # stored value = 324
slli t0, zero, 2
                # t0: 324 -> 0
sw t0, 4(sp)
                # stored value = 0
lw t1, 4(sp)
                # t1: 316 -> 0
add t0, a0, t1
                # t0: 0 -> 1888
sw t0, 8(sp)
                # stored value = 1888
sw zero, 0(t0)
                # stored value = 0
addi t0, zero, 1
                # t0: 1888 -> 1
sw t0, 16(sp)
                # stored value = 1
slli t0, t0, 2
                # t0: 1 -> 4
sw t0, 12(sp)
                # stored value = 4
lw t1, 12(sp)
                # t1: 0 -> 4
add t0, a0, t1
                # t0: 4 -> 1892
sw t0, 20(sp)
                # stored value = 1892
sw zero, 0(t0)
                # stored value = 0
addi t0, zero, 2
                # t0: 1892 -> 2
sw t0, 28(sp)
                # stored value = 2
slli t0, t0, 2
                # t0: 2 -> 8
sw t0, 24(sp)
                # stored value = 8
lw t1, 24(sp)
                # t1: 4 -> 8
add t0, a0, t1
                # t0: 8 -> 1896
sw t0, 32(sp)
                # stored value = 1896
sw zero, 0(t0)
                # stored value = 0
jal zero, 2  # point.point.exit
                # zero: 0 -> 492
lw t0, 0(sp)
                # t0: 1896 -> 324
addi ra, t0, 0
                # ra: 324 -> 324
addi sp, sp, 36
                # sp: 536870844 -> 536870880
jalr zero, 0(ra)
                # zero: 0 -> 508
lw t1, 20(sp)
                # t1: 8 -> 1888
sw t1, 0(sp)
                # stored value = 1888
lw t0, 0(sp)
                # t0: 324 -> 1888
sw t0, 24(sp)
                # stored value = 1888
addi a0, t0, 0
                # a0: 1888 -> 1888
auipc t1, 4294967295
                # t1: 1888 -> 4294963544
jalr ra, 3844(t1)
                # ra: 324 -> 352
addi sp, sp, -40
                # sp: 536870880 -> 536870840
addi t0, ra, 0
                # t0: 1888 -> 352
sw t0, 0(sp)
                # stored value = 352
slli t0, zero, 2
                # t0: 352 -> 0
sw t0, 4(sp)
                # stored value = 0
lw t1, 4(sp)
                # t1: 4294963544 -> 0
add t0, a0, t1
                # t0: 0 -> 1888
sw t0, 8(sp)
                # stored value = 1888
lw t0, 0(t0)
                # t0: 1888 -> 0
sw t0, 12(sp)
                # stored value = 0
addi a0, t0, 0
                # a0: 1888 -> 0
auipc t1, 0
                # t1: 0 -> 136
jalr ra, 724(t1)
                # ra: 352 -> 144
addi sp, sp, -16
                # sp: 536870840 -> 536870824
sw ra, 12(sp)
                # stored value = 144
sw s0, 8(sp)
                # stored value = 0
addi s0, sp, 16
                # s0: 0 -> 536870840
sw a0, -12(s0)
                # stored value = 0
addi a0, zero, 256
                # a0: 0 -> 256
auipc t1, 4294967295
                # t1: 136 -> 4294964084
jalr ra, 3236(t1)
                # ra: 144 -> 892
call libc-24
                # return value = 1902
sw a0, -16(s0)
                # stored value = 1902
lw a0, -16(s0)
                # a0: 1902 -> 1902
lw a2, -12(s0)
                # a2: 9320 -> 0
lui a1, 0
                # a1: 9320 -> 0
addi a1, a1, 1871
                # a1: 0 -> 1871
auipc t1, 4294967295
                # t1: 1448 -> 4294964112
jalr ra, 3204(t1)
                # ra: 5552 -> 920
call libc-20
                # return value = 1
lw a0, -16(s0)
                # a0: 1 -> 1902
lw ra, 12(sp)
                # ra: 5580 -> 144
lw s0, 8(sp)
                # s0: 536870840 -> 0
addi sp, sp, 16
                # sp: 536870824 -> 536870840
jalr zero, 0(ra)
                # zero: 0 -> 940
addi t0, a0, 0
                # t0: 9320 -> 1902
sw t0, 16(sp)
                # stored value = 1902
addi a0, t0, 0
                # a0: 1902 -> 1902
auipc t1, 0
                # t1: 1476 -> 156
jalr ra, 408(t1)
                # ra: 144 -> 164
addi sp, sp, -16
                # sp: 536870840 -> 536870824
sw ra, 12(sp)
                # stored value = 164
sw s0, 8(sp)
                # stored value = 0
addi s0, sp, 16
                # s0: 0 -> 536870840
sw a0, -12(s0)
                # stored value = 1902
lw a1, -12(s0)
                # a1: 6531 -> 1902
lui a0, 0
                # a0: 1902 -> 0
addi a0, a0, 1867
                # a0: 0 -> 1867
auipc t1, 4294967295
                # t1: 156 -> 4294963796
jalr ra, 3518(t1)
                # ra: 164 -> 604
call libc-18
                # return value = 2
lw ra, 12(sp)
                # ra: 5264 -> 164
lw s0, 8(sp)
                # s0: 536870840 -> 0
addi sp, sp, 16
                # sp: 536870824 -> 536870840
jalr zero, 0(ra)
                # zero: 0 -> 620
addi t0, zero, 1
                # t0: 6562 -> 1
sw t0, 24(sp)
                # stored value = 1
slli t0, t0, 2
                # t0: 1 -> 4
sw t0, 20(sp)
                # stored value = 4
lw t1, 20(sp)
                # t1: 1160 -> 4
add t0, a0, t1
                # t0: 4 -> 6
sw t0, 28(sp)
                # stored value = 6
lw t0, 0(t0)
                # t0: 6 -> 131072
sw t0, 32(sp)
                # stored value = 131072
addi a0, t0, 0
                # a0: 2 -> 131072
auipc t1, 0
                # t1: 4 -> 204
jalr ra, 656(t1)
                # ra: 164 -> 212
addi sp, sp, -16
                # sp: 536870840 -> 536870824
sw ra, 12(sp)
                # stored value = 212
sw s0, 8(sp)
                # stored value = 0
addi s0, sp, 16
                # s0: 0 -> 536870840
sw a0, -12(s0)
                # stored value = 131072
addi a0, zero, 256
                # a0: 131072 -> 256
auipc t1, 4294967295
                # t1: 204 -> 4294964084
jalr ra, 3236(t1)
                # ra: 212 -> 892
call libc-24
                # return value = 2160
sw a0, -16(s0)
                # stored value = 2160
lw a0, -16(s0)
                # a0: 2160 -> 2160
lw a2, -12(s0)
                # a2: 13980 -> 131072
lui a1, 0
                # a1: 11222 -> 0
addi a1, a1, 1871
                # a1: 0 -> 1871
auipc t1, 4294967295
                # t1: 1448 -> 4294964112
jalr ra, 3204(t1)
                # ra: 5552 -> 920
call libc-20
                # return value = 6
lw a0, -16(s0)
                # a0: 6 -> 2160
lw ra, 12(sp)
                # ra: 5580 -> 212
lw s0, 8(sp)
                # s0: 536870840 -> 0
addi sp, sp, 16
                # sp: 536870824 -> 536870840
jalr zero, 0(ra)
                # zero: 0 -> 940
addi t0, a0, 0
                # t0: 140392 -> 2160
sw t0, 36(sp)
                # stored value = 2160
addi a0, t0, 0
                # a0: 2160 -> 2160
auipc t1, 0
                # t1: 1476 -> 224
jalr ra, 340(t1)
                # ra: 212 -> 232
addi sp, sp, -16
                # sp: 536870840 -> 536870824
sw ra, 12(sp)
                # stored value = 232
sw s0, 8(sp)
                # stored value = 0
addi s0, sp, 16
                # s0: 0 -> 536870840
sw a0, -12(s0)
                # stored value = 2160
lw a1, -12(s0)
                # a1: 6531 -> 2160
lui a0, 0
                # a0: 2160 -> 0
addi a0, a0, 1867
                # a0: 0 -> 1867
auipc t1, 4294967295
                # t1: 224 -> 4294963796
jalr ra, 3518(t1)
                # ra: 232 -> 604
call libc-18
                # return value = 7
lw ra, 12(sp)
                # ra: 5264 -> 232
lw s0, 8(sp)
                # s0: 536870840 -> 0
addi sp, sp, 16
                # sp: 536870824 -> 536870840
jalr zero, 0(ra)
                # zero: 0 -> 620
jal zero, 2  # point.printPoint.exit
                # zero: 0 -> 236
lw t0, 0(sp)
                # t0: 6820 -> 352
addi ra, t0, 0
                # ra: 232 -> 352
addi sp, sp, 40
                # sp: 536870840 -> 536870880
jalr zero, 0(ra)
                # zero: 0 -> 252
sw zero, 4(sp)
                # stored value = 0
jal zero, 2  # main.exit
                # zero: 0 -> 360
lw t0, 4(sp)
                # t0: 352 -> 0
sw t0, 28(sp)
                # stored value = 0
addi a0, t0, 0
                # a0: 7 -> 0
lw t0, 8(sp)
                # t0: 0 -> 8
addi ra, t0, 0
                # ra: 352 -> 8
addi sp, sp, 32
                # sp: 536870880 -> 536870912
jalr zero, 0(ra)
                # zero: 0 -> 388

```





```
auipc t1, 0
                # t1: 0 -> 0
jalr ra, 252(t1)
                # ra: 0 -> 8
addi sp, sp, -32
                # sp: 536870912 -> 536870880
addi t0, ra, 0
                # t0: 0 -> 8
sw t0, 8(sp)
                # stored value = 8
auipc t1, 4294967295
                # t1: 0 -> 4294963464
jalr ra, 3896(t1)
                # ra: 8 -> 272
addi sp, sp, -4
                # sp: 536870880 -> 536870876
addi t0, ra, 0
                # t0: 8 -> 272
sw t0, 0(sp)
                # stored value = 272
jal zero, 2  # _init_func.exit
                # zero: 0 -> 80
addi ra, t0, 0
                # ra: 272 -> 272
addi sp, sp, 4
                # sp: 536870876 -> 536870880
jalr zero, 0(ra)
                # zero: 0 -> 92
sw zero, 4(sp)
                # stored value = 0
addi t0, zero, 12
                # t0: 272 -> 12
sw t0, 12(sp)
                # stored value = 12
addi a0, t0, 0
                # a0: 0 -> 12
auipc t1, 0
                # t1: 4294963464 -> 288
jalr ra, 1008(t1)
                # ra: 272 -> 296
addi sp, sp, -16
                # sp: 536870880 -> 536870864      //开始 _malloc
sw ra, 12(sp)
                # stored value = 296
sw s0, 8(sp)
                # stored value = 0
addi s0, sp, 16
                # s0: 0 -> 536870880
sw a0, -12(s0)
                # stored value = 12
lw a0, -12(s0)
                # a0: 12 -> 12
auipc t1, 4294967295
                # t1: 288 -> 4294964520
jalr ra, 2800(t1)
                # ra: 296 -> 1328
call libc-24
                # return value = 1888
lw ra, 12(sp)
                # ra: 5988 -> 296
lw s0, 8(sp)
                # s0: 536870880 -> 0
addi sp, sp, 16
                # sp: 536870864 -> 536870880     //结束 _malloc
jalr zero, 0(ra)
                # zero: 0 -> 1344
addi t0, a0, 0
                # t0: 4672 -> 1888
sw t0, 16(sp)
                # stored value = 1888
addi t0, t0, 0
                # t0: 1888 -> 1888
sw t0, 20(sp)
                # stored value = 1888
addi a0, t0, 0
                # a0: 1888 -> 1888
auipc t1, 0
                # t1: 1884 -> 316
jalr ra, 72(t1)
                # ra: 296 -> 324
addi sp, sp, -36
                # sp: 536870880 -> 536870844    //进入 point.point
addi t0, ra, 0
                # t0: 1888 -> 324
sw t0, 0(sp)
                # stored value = 324
slli t0, zero, 2
                # t0: 324 -> 0
sw t0, 4(sp)
                # stored value = 0
lw t1, 4(sp)
                # t1: 316 -> 0
add t0, a0, t1
                # t0: 0 -> 1888
sw t0, 8(sp)
                # stored value = 1888
sw zero, 0(t0)
                # stored value = 0
addi t0, zero, 1
                # t0: 1888 -> 1
sw t0, 16(sp)
                # stored value = 1
slli t0, t0, 2
                # t0: 1 -> 4
sw t0, 12(sp)
                # stored value = 4
lw t1, 12(sp)
                # t1: 0 -> 4
add t0, a0, t1
                # t0: 4 -> 1892
sw t0, 20(sp)
                # stored value = 1892
sw zero, 0(t0)
                # stored value = 0
addi t0, zero, 2
                # t0: 1892 -> 2
sw t0, 28(sp)
                # stored value = 2
slli t0, t0, 2
                # t0: 2 -> 8
sw t0, 24(sp)
                # stored value = 8
lw t1, 24(sp)
                # t1: 4 -> 8
add t0, a0, t1
                # t0: 8 -> 1896
sw t0, 32(sp)
                # stored value = 1896
sw zero, 0(t0)
                # stored value = 0
jal zero, 2  # point.point.exit
                # zero: 0 -> 492
lw t0, 0(sp)
                # t0: 1896 -> 324
addi ra, t0, 0
                # ra: 324 -> 324
addi sp, sp, 36
                # sp: 536870844 -> 536870880   //退出 point.point
jalr zero, 0(ra)
                # zero: 0 -> 508
lw t1, 20(sp)
                # t1: 8 -> 1888
sw t1, 0(sp)
                # stored value = 1888
lw t0, 0(sp)
                # t0: 324 -> 1888
sw t0, 24(sp)
                # stored value = 1888
addi a0, t0, 0
                # a0: 1888 -> 1888
auipc t1, 4294967295
                # t1: 1888 -> 4294963544
jalr ra, 3844(t1)
                # ra: 324 -> 352
addi sp, sp, -40
                # sp: 536870880 -> 536870840  //进入 point.printPoint
addi t0, ra, 0
                # t0: 1888 -> 352
sw t0, 0(sp)
                # stored value = 352
slli t0, zero, 2
                # t0: 352 -> 0                //计算 x 的偏移量
sw t0, 4(sp)
                # stored value = 0
lw t1, 4(sp)
                # t1: 4294963544 -> 0
add t0, a0, t1
                # t0: 0 -> 1888              //计算 x 的地址
sw t0, 8(sp)
                # stored value = 1888        //存储 x 的地址
lw t0, 0(t0)
                # t0: 1888 -> 0              //取出 x 的值
sw t0, 12(sp)
                # stored value = 0           //存储 x 的值到 12(sp)
addi a0, t0, 0
                # a0: 1888 -> 0              //将 x 的值传入 a0
auipc t1, 0
                # t1: 0 -> 136
jalr ra, 724(t1)
                # ra: 352 -> 144
addi sp, sp, -16
                # sp: 536870840 -> 536870824  //进入 toString
sw ra, 12(sp)
                # stored value = 144
sw s0, 8(sp)
                # stored value = 0
addi s0, sp, 16
                # s0: 0 -> 536870840
sw a0, -12(s0)
                # stored value = 0
addi a0, zero, 256
                # a0: 0 -> 256
auipc t1, 4294967295
                # t1: 136 -> 4294964084
jalr ra, 3236(t1)
                # ra: 144 -> 892
call libc-24
                # return value = 1902
sw a0, -16(s0)
                # stored value = 1902
lw a0, -16(s0)
                # a0: 1902 -> 1902
lw a2, -12(s0)
                # a2: 9320 -> 0
lui a1, 0
                # a1: 9320 -> 0
addi a1, a1, 1871
                # a1: 0 -> 1871
auipc t1, 4294967295
                # t1: 1448 -> 4294964112
jalr ra, 3204(t1)
                # ra: 5552 -> 920
call libc-20
                # return value = 1
lw a0, -16(s0)
                # a0: 1 -> 1902
lw ra, 12(sp)
                # ra: 5580 -> 144
lw s0, 8(sp)
                # s0: 536870840 -> 0
addi sp, sp, 16
                # sp: 536870824 -> 536870840  //结束 toString
jalr zero, 0(ra)
                # zero: 0 -> 940
addi t0, a0, 0
                # t0: 9320 -> 1902
sw t0, 16(sp)
                # stored value = 1902         //存储 toString 的返回值
addi a0, t0, 0
                # a0: 1902 -> 1902
auipc t1, 0
                # t1: 1476 -> 156
jalr ra, 408(t1)
                # ra: 144 -> 164
addi sp, sp, -16
                # sp: 536870840 -> 536870824  //进入 println
sw ra, 12(sp)
                # stored value = 164
sw s0, 8(sp)
                # stored value = 0
addi s0, sp, 16
                # s0: 0 -> 536870840
sw a0, -12(s0)
                # stored value = 1902
lw a1, -12(s0)
                # a1: 6531 -> 1902
lui a0, 0
                # a0: 1902 -> 0
addi a0, a0, 1867
                # a0: 0 -> 1867
auipc t1, 4294967295
                # t1: 156 -> 4294963796
jalr ra, 3518(t1)
                # ra: 164 -> 604
call libc-18
                # return value = 2
lw ra, 12(sp)
                # ra: 5264 -> 164
lw s0, 8(sp)
                # s0: 536870840 -> 0
addi sp, sp, 16
                # sp: 536870824 -> 536870840  //结束 println
jalr zero, 0(ra)
                # zero: 0 -> 620
addi t0, zero, 1
                # t0: 6562 -> 1
sw t0, 24(sp)
                # stored value = 1
slli t0, t0, 2
                # t0: 1 -> 4                  //计算 y 的偏移量
sw t0, 20(sp)
                # stored value = 4
lw t1, 20(sp)
                # t1: 1160 -> 4
add t0, a0, t1
                # t0: 4 -> 6                  //a0 未复原, y的地址计算错误
sw t0, 28(sp)
                # stored value = 6
lw t0, 0(t0)
                # t0: 6 -> 131072
sw t0, 32(sp)
                # stored value = 131072
addi a0, t0, 0
                # a0: 2 -> 131072
auipc t1, 0
                # t1: 4 -> 204
jalr ra, 656(t1)
                # ra: 164 -> 212
addi sp, sp, -16
                # sp: 536870840 -> 536870824
sw ra, 12(sp)
                # stored value = 212
sw s0, 8(sp)
                # stored value = 0
addi s0, sp, 16
                # s0: 0 -> 536870840
sw a0, -12(s0)
                # stored value = 131072
addi a0, zero, 256
                # a0: 131072 -> 256
auipc t1, 4294967295
                # t1: 204 -> 4294964084
jalr ra, 3236(t1)
                # ra: 212 -> 892
call libc-24
                # return value = 2160
sw a0, -16(s0)
                # stored value = 2160
lw a0, -16(s0)
                # a0: 2160 -> 2160
lw a2, -12(s0)
                # a2: 13980 -> 131072
lui a1, 0
                # a1: 11222 -> 0
addi a1, a1, 1871
                # a1: 0 -> 1871
auipc t1, 4294967295
                # t1: 1448 -> 4294964112
jalr ra, 3204(t1)
                # ra: 5552 -> 920
call libc-20
                # return value = 6
lw a0, -16(s0)
                # a0: 6 -> 2160
lw ra, 12(sp)
                # ra: 5580 -> 212
lw s0, 8(sp)
                # s0: 536870840 -> 0
addi sp, sp, 16
                # sp: 536870824 -> 536870840
jalr zero, 0(ra)
                # zero: 0 -> 940
addi t0, a0, 0
                # t0: 140392 -> 2160
sw t0, 36(sp)
                # stored value = 2160
addi a0, t0, 0
                # a0: 2160 -> 2160
auipc t1, 0
                # t1: 1476 -> 224
jalr ra, 340(t1)
                # ra: 212 -> 232
addi sp, sp, -16
                # sp: 536870840 -> 536870824
sw ra, 12(sp)
                # stored value = 232
sw s0, 8(sp)
                # stored value = 0
addi s0, sp, 16
                # s0: 0 -> 536870840
sw a0, -12(s0)
                # stored value = 2160
lw a1, -12(s0)
                # a1: 6531 -> 2160
lui a0, 0
                # a0: 2160 -> 0
addi a0, a0, 1867
                # a0: 0 -> 1867
auipc t1, 4294967295
                # t1: 224 -> 4294963796
jalr ra, 3518(t1)
                # ra: 232 -> 604
call libc-18
                # return value = 7
lw ra, 12(sp)
                # ra: 5264 -> 232
lw s0, 8(sp)
                # s0: 536870840 -> 0
addi sp, sp, 16
                # sp: 536870824 -> 536870840
jalr zero, 0(ra)
                # zero: 0 -> 620
jal zero, 2  # point.printPoint.exit
                # zero: 0 -> 236
lw t0, 0(sp)
                # t0: 6820 -> 352
addi ra, t0, 0
                # ra: 232 -> 352
addi sp, sp, 40
                # sp: 536870840 -> 536870880
jalr zero, 0(ra)
                # zero: 0 -> 252
sw zero, 4(sp)
                # stored value = 0
jal zero, 2  # main.exit
                # zero: 0 -> 360
lw t0, 4(sp)
                # t0: 352 -> 0
sw t0, 28(sp)
                # stored value = 0
addi a0, t0, 0
                # a0: 7 -> 0
lw t0, 8(sp)
                # t0: 0 -> 8
addi ra, t0, 0
                # ra: 352 -> 8
addi sp, sp, 32
                # sp: 536870880 -> 536870912
jalr zero, 0(ra)
                # zero: 0 -> 388

```



![image-20230203185252290](C:\Users\JerryGuo\AppData\Roaming\Typora\typora-user-images\image-20230203185252290.png)

问题出在 if 的 true 分支计算出了 data 的地址，但是在执行另一个分支的时候这条计算指令没有执行，却用了执行后的寄存器，目前认为是 irScope 出了问题