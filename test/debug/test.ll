
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

define i1 @func(i1 %C) {
func.entry:
    %func.ret.addr = alloca i8
    %C.addr = alloca i8
    %zext = zext i1 %C to i8
    store i8 %zext, i8* %C.addr
    %C.load = load i8, i8* %C.addr
    %trunc.1 = trunc i8 %C.load to i1
    %zext.1 = zext i1 %trunc.1 to i8
    store i8 %zext.1, i8* %func.ret.addr
    br label %func.exit
func.exit:
    %func.ret.load = load i8, i8* %func.ret.addr
    %trunc = trunc i8 %func.ret.load to i1
    ret i1 %trunc
}


; MainFunc
define i32 @main() {
main.entry:
    %tmp.addr = alloca i8
    %B.addr = alloca i8
    %main.ret.addr = alloca i32
    call void @_init_func()
    store i32 0, i32* %main.ret.addr
    %func.call = call i1 @func(i1 true)
    %zext.2 = zext i1 %func.call to i8
    store i8 %zext.2, i8* @A
    %A.load = load i8, i8* @A
    %trunc.2 = trunc i8 %A.load to i1
    %icmp = icmp ne i1 %trunc.2, true
    br i1 %icmp, label %if_true, label %if_false
main.exit:
    %main.ret.load = load i32, i32* %main.ret.addr
    ret i32 %main.ret.load
if_true:
    %A.load.1 = load i8, i8* @A
    %trunc.3 = trunc i8 %A.load.1 to i1
    %zext.3 = zext i1 %trunc.3 to i8
    store i8 %zext.3, i8* %tmp.addr
    br i1 %trunc.3, label %noShortCutBlock, label %logicExitBlock
if_false:
    br label %if_exit
if_exit:
    br label %main.exit
noShortCutBlock:
    %and = and i1 %trunc.3, false
    %zext.4 = zext i1 %and to i8
    store i8 %zext.4, i8* %tmp.addr
    br label %logicExitBlock
logicExitBlock:
    %and.load = load i8, i8* %tmp.addr
    %trunc.4 = trunc i8 %and.load to i1
    %zext.5 = zext i1 %trunc.4 to i8
    store i8 %zext.5, i8* %B.addr
    br label %if_exit
}


