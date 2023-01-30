package BackEnd;

import ASM.ASMModule;

public class ASMPrinter {
    public void printAsm(ASMModule asmModule) {
        System.out.println(asmModule.toString());

        String BuiltInAsm = """
                 STR_BUF_SIZE:
                         .word   256
                 INT_BUF_SIZE:
                         .word   20
                 .LC0:
                         .string "%s"
                 print:
                         addi    sp,sp,-32
                         sw      ra,28(sp)
                         sw      s0,24(sp)
                         addi    s0,sp,32
                         sw      a0,-20(s0)
                         lw      a1,-20(s0)
                         lui     a5,%hi(.LC0)
                         addi    a0,a5,%lo(.LC0)
                         call    printf
                         nop
                         lw      ra,28(sp)
                         lw      s0,24(sp)
                         addi    sp,sp,32
                         jr      ra
                 println:
                         addi    sp,sp,-32
                         sw      ra,28(sp)
                         sw      s0,24(sp)
                         addi    s0,sp,32
                         sw      a0,-20(s0)
                         lw      a0,-20(s0)
                         call    puts
                         nop
                         lw      ra,28(sp)
                         lw      s0,24(sp)
                         addi    sp,sp,32
                         jr      ra
                 .LC1:
                         .string "%d"
                 printInt:
                         addi    sp,sp,-32
                         sw      ra,28(sp)
                         sw      s0,24(sp)
                         addi    s0,sp,32
                         sw      a0,-20(s0)
                         lw      a1,-20(s0)
                         lui     a5,%hi(.LC1)
                         addi    a0,a5,%lo(.LC1)
                         call    printf
                         nop
                         lw      ra,28(sp)
                         lw      s0,24(sp)
                         addi    sp,sp,32
                         jr      ra
                 .LC2:
                         .string "%d\\\\n"
                 printlnInt:
                         addi    sp,sp,-32
                         sw      ra,28(sp)
                         sw      s0,24(sp)
                         addi    s0,sp,32
                         sw      a0,-20(s0)
                         lw      a1,-20(s0)
                         lui     a5,%hi(.LC2)
                         addi    a0,a5,%lo(.LC2)
                         call    printf
                         nop
                         lw      ra,28(sp)
                         lw      s0,24(sp)
                         addi    sp,sp,32
                         jr      ra
                 getString:
                         addi    sp,sp,-32
                         sw      ra,28(sp)
                         sw      s0,24(sp)
                         addi    s0,sp,32
                         li      a5,256
                         mv      a0,a5
                         call    malloc
                         mv      a5,a0
                         sw      a5,-20(s0)
                         lw      a1,-20(s0)
                         lui     a5,%hi(.LC0)
                         addi    a0,a5,%lo(.LC0)
                         call    scanf
                         lw      a5,-20(s0)
                         mv      a0,a5
                         lw      ra,28(sp)
                         lw      s0,24(sp)
                         addi    sp,sp,32
                         jr      ra
                 getInt:
                         addi    sp,sp,-32
                         sw      ra,28(sp)
                         sw      s0,24(sp)
                         addi    s0,sp,32
                         addi    a5,s0,-20
                         mv      a1,a5
                         lui     a5,%hi(.LC1)
                         addi    a0,a5,%lo(.LC1)
                         call    scanf
                         lw      a5,-20(s0)
                         mv      a0,a5
                         lw      ra,28(sp)
                         lw      s0,24(sp)
                         addi    sp,sp,32
                         jr      ra
                 toString:
                         addi    sp,sp,-48
                         sw      ra,44(sp)
                         sw      s0,40(sp)
                         addi    s0,sp,48
                         sw      a0,-36(s0)
                         li      a0,256
                         call    malloc
                         mv      a5,a0
                         sw      a5,-20(s0)
                         lw      a2,-36(s0)
                         lui     a5,%hi(.LC1)
                         addi    a1,a5,%lo(.LC1)
                         lw      a0,-20(s0)
                         call    sprintf
                         lw      a5,-20(s0)
                         mv      a0,a5
                         lw      ra,44(sp)
                         lw      s0,40(sp)
                         addi    sp,sp,48
                         jr      ra
                 _str_substring:
                         addi    sp,sp,-48
                         sw      ra,44(sp)
                         sw      s0,40(sp)
                         addi    s0,sp,48
                         sw      a0,-36(s0)
                         sw      a1,-40(s0)
                         sw      a2,-44(s0)
                         lw      a4,-44(s0)
                         lw      a5,-40(s0)
                         sub     a5,a4,a5
                         addi    a5,a5,1
                         mv      a0,a5
                         call    malloc
                         mv      a5,a0
                         sw      a5,-24(s0)
                         lw      a5,-40(s0)
                         sw      a5,-20(s0)
                         j       .L12
                 .L13:
                         lw      a5,-20(s0)
                         lw      a4,-36(s0)
                         add     a4,a4,a5
                         lw      a3,-20(s0)
                         lw      a5,-40(s0)
                         sub     a5,a3,a5
                         mv      a3,a5
                         lw      a5,-24(s0)
                         add     a5,a5,a3
                         lbu     a4,0(a4)
                         sb      a4,0(a5)
                         lw      a5,-20(s0)
                         addi    a5,a5,1
                         sw      a5,-20(s0)
                 .L12:
                         lw      a4,-20(s0)
                         lw      a5,-44(s0)
                         blt     a4,a5,.L13
                         lw      a4,-44(s0)
                         lw      a5,-40(s0)
                         sub     a5,a4,a5
                         mv      a4,a5
                         lw      a5,-24(s0)
                         add     a5,a5,a4
                         sb      zero,0(a5)
                         lw      a5,-24(s0)
                         mv      a0,a5
                         lw      ra,44(sp)
                         lw      s0,40(sp)
                         addi    sp,sp,48
                         jr      ra
                 _str_length:
                         addi    sp,sp,-32
                         sw      ra,28(sp)
                         sw      s0,24(sp)
                         addi    s0,sp,32
                         sw      a0,-20(s0)
                         lw      a0,-20(s0)
                         call    strlen
                         mv      a5,a0
                         mv      a0,a5
                         lw      ra,28(sp)
                         lw      s0,24(sp)
                         addi    sp,sp,32
                         jr      ra
                 _str_ord:
                         addi    sp,sp,-32
                         sw      s0,28(sp)
                         addi    s0,sp,32
                         sw      a0,-20(s0)
                         sw      a1,-24(s0)
                         lw      a5,-24(s0)
                         lw      a4,-20(s0)
                         add     a5,a4,a5
                         lbu     a5,0(a5)
                         mv      a0,a5
                         lw      s0,28(sp)
                         addi    sp,sp,32
                         jr      ra
                 _str_parseInt:
                         addi    sp,sp,-48
                         sw      ra,44(sp)
                         sw      s0,40(sp)
                         addi    s0,sp,48
                         sw      a0,-36(s0)
                         addi    a5,s0,-20
                         mv      a2,a5
                         lui     a5,%hi(.LC1)
                         addi    a1,a5,%lo(.LC1)
                         lw      a0,-36(s0)
                         call    sscanf
                         lw      a5,-20(s0)
                         mv      a0,a5
                         lw      ra,44(sp)
                         lw      s0,40(sp)
                         addi    sp,sp,48
                         jr      ra
                 _malloc:
                         addi    sp,sp,-32
                         sw      ra,28(sp)
                         sw      s0,24(sp)
                         addi    s0,sp,32
                         sw      a0,-20(s0)
                         lw      a5,-20(s0)
                         mv      a0,a5
                         call    malloc
                         mv      a5,a0
                         mv      a0,a5
                         lw      ra,28(sp)
                         lw      s0,24(sp)
                         addi    sp,sp,32
                         jr      ra
                 _str_concat:
                         addi    sp,sp,-48
                         sw      ra,44(sp)
                         sw      s0,40(sp)
                         sw      s1,36(sp)
                         addi    s0,sp,48
                         sw      a0,-36(s0)
                         sw      a1,-40(s0)
                         lw      a0,-36(s0)
                         call    strlen
                         mv      s1,a0
                         lw      a0,-40(s0)
                         call    strlen
                         mv      a5,a0
                         add     a5,s1,a5
                         addi    a5,a5,1
                         mv      a0,a5
                         call    malloc
                         mv      a5,a0
                         sw      a5,-20(s0)
                         lw      a1,-36(s0)
                         lw      a0,-20(s0)
                         call    strcpy
                         lw      a1,-40(s0)
                         lw      a0,-20(s0)
                         call    strcat
                         lw      a5,-20(s0)
                         mv      a0,a5
                         lw      ra,44(sp)
                         lw      s0,40(sp)
                         lw      s1,36(sp)
                         addi    sp,sp,48
                         jr      ra
                 _str_eq:
                         addi    sp,sp,-32
                         sw      ra,28(sp)
                         sw      s0,24(sp)
                         addi    s0,sp,32
                         sw      a0,-20(s0)
                         sw      a1,-24(s0)
                         lw      a1,-24(s0)
                         lw      a0,-20(s0)
                         call    strcmp
                         mv      a5,a0
                         seqz    a5,a5
                         andi    a5,a5,0xff
                         mv      a0,a5
                         lw      ra,28(sp)
                         lw      s0,24(sp)
                         addi    sp,sp,32
                         jr      ra
                 _str_ne:
                         addi    sp,sp,-32
                         sw      ra,28(sp)
                         sw      s0,24(sp)
                         addi    s0,sp,32
                         sw      a0,-20(s0)
                         sw      a1,-24(s0)
                         lw      a1,-24(s0)
                         lw      a0,-20(s0)
                         call    strcmp
                         mv      a5,a0
                         snez    a5,a5
                         andi    a5,a5,0xff
                         mv      a0,a5
                         lw      ra,28(sp)
                         lw      s0,24(sp)
                         addi    sp,sp,32
                         jr      ra
                 _str_ult:
                         addi    sp,sp,-32
                         sw      ra,28(sp)
                         sw      s0,24(sp)
                         addi    s0,sp,32
                         sw      a0,-20(s0)
                         sw      a1,-24(s0)
                         lw      a1,-24(s0)
                         lw      a0,-20(s0)
                         call    strcmp
                         mv      a5,a0
                         srli    a5,a5,31
                         andi    a5,a5,0xff
                         mv      a0,a5
                         lw      ra,28(sp)
                         lw      s0,24(sp)
                         addi    sp,sp,32
                         jr      ra
                 _str_ugt:
                         addi    sp,sp,-32
                         sw      ra,28(sp)
                         sw      s0,24(sp)
                         addi    s0,sp,32
                         sw      a0,-20(s0)
                         sw      a1,-24(s0)
                         lw      a1,-24(s0)
                         lw      a0,-20(s0)
                         call    strcmp
                         mv      a5,a0
                         sgt     a5,a5,zero
                         andi    a5,a5,0xff
                         mv      a0,a5
                         lw      ra,28(sp)
                         lw      s0,24(sp)
                         addi    sp,sp,32
                         jr      ra
                 _str_ule:
                         addi    sp,sp,-32
                         sw      ra,28(sp)
                         sw      s0,24(sp)
                         addi    s0,sp,32
                         sw      a0,-20(s0)
                         sw      a1,-24(s0)
                         lw      a1,-24(s0)
                         lw      a0,-20(s0)
                         call    strcmp
                         mv      a5,a0
                         slti    a5,a5,1
                         andi    a5,a5,0xff
                         mv      a0,a5
                         lw      ra,28(sp)
                         lw      s0,24(sp)
                         addi    sp,sp,32
                         jr      ra
                 _str_uge:
                         addi    sp,sp,-32
                         sw      ra,28(sp)
                         sw      s0,24(sp)
                         addi    s0,sp,32
                         sw      a0,-20(s0)
                         sw      a1,-24(s0)
                         lw      a1,-24(s0)
                         lw      a0,-20(s0)
                         call    strcmp
                         mv      a5,a0
                         not     a5,a5
                         srli    a5,a5,31
                         andi    a5,a5,0xff
                         mv      a0,a5
                         lw      ra,28(sp)
                         lw      s0,24(sp)
                         addi    sp,sp,32
                         jr      ra
                """;

        System.out.println(BuiltInAsm);
    }
}
