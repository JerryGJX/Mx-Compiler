
; IRModule = test/debug/test.mx

; BuiltinFunc
declare i8* @_malloc(i32)
declare i8* @_str_concat(i8*, i8*)
declare i1 @_str_eq(i8*, i8*)
declare i1 @_str_ne(i8*, i8*)
declare i1 @_str_ult(i8*, i8*)
declare i1 @_str_ugt(i8*, i8*)
declare i1 @_str_ule(i8*, i8*)
declare i1 @_str_uge(i8*, i8*)
declare void @print(i8*)
declare void @println(i8*)
declare void @printInt(i32)
declare void @printlnInt(i32)
declare i8* @getString()
declare i32 @getInt()
declare i8* @toString(i32)
declare i8* @_str_substring(i8*, i32, i32)
declare i32 @_str_length(i8*)
declare i32 @_str_ord(i8*, i32)
declare i32 @_str_parseInt(i8*)

; GlobalStrDef

; GlobalVar
@A = global i8 zeroinitializer

; StructDef

; GlobalFunc
define void @_init_func() {
_init_func.entry:
    br label %_init_func.exit
_init_func.exit:
    ret void
}


; MainFunc
define i32 @main() {
main.entry:
    %tmp.addr = alloca i8
    %B.addr = alloca i8
    %main.ret.addr = alloca i32
    call void @_init_func()
    store i32 0, i32* %main.ret.addr
    %zext = zext i1 true to i8
    store i8 %zext, i8* @A
    %A.load = load i8, i8* @A
    %trunc = trunc i8 %A.load to i1
    %zext.1 = zext i1 %trunc to i8
    store i8 %zext.1, i8* %tmp.addr
    br i1 %trunc, label %noShortCutBlock, label %logicExitBlock
main.exit:
    %main.ret.load = load i32, i32* %main.ret.addr
    ret i32 %main.ret.load
noShortCutBlock:
    %and = and i1 %trunc, false
    %zext.2 = zext i1 %and to i8
    store i8 %zext.2, i8* %tmp.addr
    br label %logicExitBlock
logicExitBlock:
    %and.load = load i8, i8* %tmp.addr
    %trunc.1 = trunc i8 %and.load to i1
    %zext.3 = zext i1 %trunc.1 to i8
    store i8 %zext.3, i8* %B.addr
    br label %main.exit
}


