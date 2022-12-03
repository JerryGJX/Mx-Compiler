IRGEPInst2: index: 0
IRGEPInst2: index: 0
IRGEPInst2: index: 0
IRGEPInst2: index: 1
IRGEPInst2: index: 0
IRGEPInst2: index: 0
IRGEPInst2: index: 0
IRGEPInst2: index: 1

; IRModule = src/test/IRTest/test.mx

; GlobalStrDef

; GlobalVar

; StructDef
%class.A = type {i8, i8}

; GlobalFunc
define void @_init_func() {
_init_func.entry:
    br label %_init_func.exit
_init_func.exit:
    ret void
}

define i1 @A.fn(%class.A* %this.1, i1 %b) {
A.fn.entry:
    %tmp = alloca i8, align 1
    %A.fn.ret = alloca i8, align 1
    %b.addr = alloca i8, align 1
    %zext = zext i1 %b to i8
    store i8 %zext, i8* %b.addr, align 1
    %trunc = trunc i8 %A.fn.ret.load to i1
    %x.addr.1 = getelementptr %class.A, %class.A* %this, i32 0, i32 0
    %x.load = load i8, i8* %x.addr.1, align 1
    store i8 %x.load, i8* %tmp, align 1
    %trunc.1 = trunc i8 %x.load to i1
    br i1 %trunc.1, label %noShortCutBlock, label %logicExitBlock
A.fn.exit:
    %A.fn.ret.load = load i8, i8* %A.fn.ret, align 1
    ret i1 %trunc
noShortCutBlock:
    %b.load = load i8, i8* %b.addr, align 1
    %and = and i1 %x.load, %b.load
    %zext.1 = zext i1 %and to i8
    store i8 %zext.1, i8* %tmp, align 1
    br label %logicExitBlock
logicExitBlock:
    %and.load = load i8, i8* %tmp, align 1
    store i8 %and.load, i8* %A.fn.ret, align 1
    br label %logicExitBlock
}

define void @A.A(%class.A* %this) {
A.A.entry:
    br label %A.A.entry
A.A.exit:
    ret void
}


; MainFunc
define i32 @main() {
main.entry:
    %main.ret = alloca i32, align 4
    call void @_init_func()
    store i32 0, i32* %main.ret, align 4
    store i32 0, i32* %main.ret, align 4
    br label %main.entry
main.exit:
    %main.ret.load = load i32, i32* %main.ret, align 4
    ret i32 %main.ret.load
}


