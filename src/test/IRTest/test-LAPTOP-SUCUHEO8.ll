

false

; IRModule = src/test/IRTest/test.mx

; GlobalStrDef

; GlobalVar

; StructDef

; GlobalFunc
define void @_init_func() {
_init_func.entry:
    br label %_init_func.exit
_init_func.exit:
    ret void
}

define void @test_logic_not() {
test_logic_not.entry:
    %y.addr = alloca i8, align 1
    %x.addr = alloca i8, align 1
    %zext = zext i1 true to i8
    store i8 %zext, i8* %x.addr, align 1
    %x.load = load i8, i8* %x.addr, align 1
    %trunc = trunc i8 %x.load to i1
    %xor = xor i1 %trunc, true
    %zext.1 = zext i1 %xor to i8
    store i8 %zext.1, i8* %y.addr, align 1
    br label %test_logic_not.exit
test_logic_not.exit:
    ret void
}


; MainFunc
define i32 @main() {
main.entry:
    %main.ret.addr = alloca i32, align 4
    call void @_init_func()
    store i32 0, i32* %main.ret.addr, align 4
    store i32 0, i32* %main.ret.addr, align 4
    br label %main.exit
main.exit:
    %main.ret.load = load i32, i32* %main.ret.addr, align 4
    ret i32 %main.ret.load
}


