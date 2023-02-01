
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
@strConst.1 = private unnamed_addr constant [2 x i8] c" \00"
@strConst = private unnamed_addr constant [2 x i8] c"A\00"
@strConst.2 = private unnamed_addr constant [2 x i8] c"B\00"

; GlobalVar

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
    %main.ret.addr = alloca i32
    call void @_init_func()
    store i32 0, i32* %main.ret.addr
    %getelementptr = getelementptr [2 x i8], [2 x i8]* @strConst, i32 0, i32 0
    %getelementptr.1 = getelementptr [2 x i8], [2 x i8]* @strConst.1, i32 0, i32 0
    %_str_concat.call = call i8* @_str_concat(i8* %getelementptr, i8* %getelementptr.1)
    %getelementptr.2 = getelementptr [2 x i8], [2 x i8]* @strConst.2, i32 0, i32 0
    %_str_concat.call.1 = call i8* @_str_concat(i8* %_str_concat.call, i8* %getelementptr.2)
    call void @println(i8* %_str_concat.call.1)
    br label %main.exit
main.exit:
    %main.ret.load = load i32, i32* %main.ret.addr
    ret i32 %main.ret.load
}


