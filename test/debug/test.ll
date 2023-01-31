
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
@A = global i32* zeroinitializer

; StructDef

; GlobalFunc
define void @_init_func() {
_init_func.entry:
    store i32* null, i32** @A
    br label %_init_func.exit
_init_func.exit:
    ret void
}


; MainFunc
define i32 @main() {
main.entry:
    %main.ret.addr = alloca i32
    call void @_init_func()
    store i32 0, i32* %main.ret.addr
    %A.load = load i32*, i32** @A
    %getelementptr = getelementptr i32, i32* %A.load, i32 0
    store i32 1, i32* %getelementptr
    %A.load.1 = load i32*, i32** @A
    %getelementptr.1 = getelementptr i32, i32* %A.load.1, i32 0
    %null.load = load i32, i32* %getelementptr.1
    %toString.call = call i8* @toString(i32 %null.load)
    call void @println(i8* %toString.call)
    br label %main.exit
main.exit:
    %main.ret.load = load i32, i32* %main.ret.addr
    ret i32 %main.ret.load
}


