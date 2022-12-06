
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
@a = global i32* zeroinitializer

; StructDef

; GlobalFunc
define void @_init_func() {
_init_func.entry:
    %mul = mul i32 4, 4
    %add = add i32 %mul, 4
    %_malloc.call = call i8* @_malloc(i32 %add)
    %bitcast = bitcast i8* %_malloc.call to i32*
    store i32 4, i32* %bitcast
    %getelementptr = getelementptr i32, i32* %bitcast, i32 1
    %bitcast.1 = bitcast i32* %getelementptr to i32*
    store i32* %bitcast.1, i32** @a
    br label %_init_func.exit
_init_func.exit:
    ret void
}


; MainFunc
define i32 @main() {
main.entry:
    %b.addr = alloca i32**
    %main.ret.addr = alloca i32
    call void @_init_func()
    store i32 0, i32* %main.ret.addr
    %mul.1 = mul i32 4, 4
    %add.1 = add i32 %mul.1, 4
    %_malloc.call.1 = call i8* @_malloc(i32 %add.1)
    %bitcast.2 = bitcast i8* %_malloc.call.1 to i32*
    store i32 4, i32* %bitcast.2
    %getelementptr.1 = getelementptr i32, i32* %bitcast.2, i32 1
    %bitcast.3 = bitcast i32* %getelementptr.1 to i32**
    store i32** %bitcast.3, i32*** %b.addr
    %b.load = load i32**, i32*** %b.addr
    %getelementptr.2 = getelementptr i32*, i32** %b.load, i32 3
    %a.load = load i32*, i32** @a
    store i32* %a.load, i32** %getelementptr.2
    %b.load.1 = load i32**, i32*** %b.addr
    %bitcast.4 = bitcast i32** %b.load.1 to i32*
    %getelementptr.3 = getelementptr i32, i32* %bitcast.4, i32 -1
    %array.load = load i32, i32* %getelementptr.3
    %toString.call = call i8* @toString(i32 %array.load)
    store i32 0, i32* %main.ret.addr
    br label %main.exit
main.exit:
    %main.ret.load = load i32, i32* %main.ret.addr
    ret i32 %main.ret.load
}


