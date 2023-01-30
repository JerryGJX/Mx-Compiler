.Ltext0:
STR_BUF_SIZE:
        .word   256
INT_BUF_SIZE:
        .word   20
.LC0:
        .string "%s"
print:
.LFB0:
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
.LFE0:
println:
.LFB1:
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
.LFE1:
.LC1:
        .string "%d"
printInt:
.LFB2:
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
.LFE2:
.LC2:
        .string "%d\n"
printlnInt:
.LFB3:
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
.LFE3:
getString:
.LFB4:
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
.LFE4:
getInt:
.LFB5:
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
.LFE5:
toString:
.LFB6:
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
.LFE6:
_str_substring:
.LFB7:
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
.LFE7:
_str_length:
.LFB8:
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
.LFE8:
_str_ord:
.LFB9:
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
.LFE9:
_str_parseInt:
.LFB10:
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
.LFE10:
_malloc:
.LFB11:
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
.LFE11:
_str_concat:
.LFB12:
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
.LFE12:
_str_eq:
.LFB13:
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
.LFE13:
_str_ne:
.LFB14:
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
.LFE14:
_str_ult:
.LFB15:
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
.LFE15:
_str_ugt:
.LFB16:
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
.LFE16:
_str_ule:
.LFB17:
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
.LFE17:
_str_uge:
.LFB18:
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
.LFE18:
.Letext0:
.Ldebug_info0:
.Ldebug_abbrev0:
.Ldebug_line0:
.LASF37:
.LASF34:
.LASF3:
.LASF23:
.LASF8:
.LASF6:
.LASF33:
.LASF32:
.LASF25:
.LASF29:
.LASF36:
.LASF28:
.LASF16:
.LASF18:
.LASF30:
.LASF13:
.LASF24:
.LASF17:
.LASF15:
.LASF22:
.LASF26:
.LASF35:
.LASF9:
.LASF31:
.LASF12:
.LASF2:
.LASF27:
.LASF21:
.LASF5:
.LASF4:
.LASF38:
.LASF20:
.LASF14:
.LASF7:
.LASF0:
.LASF19:
.LASF10:
.LASF11:
.LASF1: