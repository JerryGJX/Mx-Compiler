
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

; StructDef

; GlobalFunc
define void @_init_func() {
_init_func.entry:
    br label %_init_func.exit
_init_func.exit:
    ret void
}

define i32 @abs(i32 %c) {
abs.entry:
    %abs.ret.addr = alloca i32
    %c.addr = alloca i32
    store i32 %c, i32* %c.addr
    %c.load = load i32, i32* %c.addr
    %sub = sub i32 0, %c.load
    store i32 %sub, i32* %abs.ret.addr
    br label %abs.exit
abs.exit:
    %abs.ret.load = load i32, i32* %abs.ret.addr
    ret i32 %abs.ret.load
}


; MainFunc
define i32 @main() {
main.entry:
    %main.ret.addr = alloca i32
    call void @_init_func()
    store i32 0, i32* %main.ret.addr
    %abs.call = call i32 @abs(i32 1)
    call void @printInt(i32 %abs.call)
    br label %main.exit
main.exit:
    %main.ret.load = load i32, i32* %main.ret.addr
    ret i32 %main.ret.load
}


