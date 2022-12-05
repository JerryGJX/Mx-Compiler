
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


; MainFunc
define i32 @main() {
main.entry:
    %_new_tmp.addr = alloca i32, align 4
    %A.addr = alloca i32***, align 4
    %main.ret.addr = alloca i32, align 4
    call void @_init_func()
    store i32 0, i32* %main.ret.addr, align 4
    %mul = mul i32 2, 4
    %add = add i32 %mul, 4
    %_malloc.call = call i8* @_malloc(i32 %add)
    %bitcast = bitcast i8* %_malloc.call to i32*
    store i32 2, i32* %bitcast, align 4
    %getelementptr = getelementptr i32, i32* %bitcast, i32 1
    %bitcast.1 = bitcast i32* %getelementptr to i32***
    store i32 0, i32* %_new_tmp.addr, align 4
    br label %for_cond
main.exit:
    %main.ret.load = load i32, i32* %main.ret.addr, align 4
    ret i32 %main.ret.load
for_cond:
    %_new_tmp.load = load i32, i32* %_new_tmp.addr, align 4
    %icmp = icmp ult i32 %_new_tmp.load, 2
    br i1 %icmp, label %for_body, label %for_exit
for_body:
    %getelementptr.1 = getelementptr i32**, i32*** %bitcast.1, i32 %_new_tmp.load
    %mul.1 = mul i32 3, 4
    %add.2 = add i32 %mul.1, 4
    %_malloc.call.1 = call i8* @_malloc(i32 %add.2)
    %bitcast.2 = bitcast i8* %_malloc.call.1 to i32*
    store i32 3, i32* %bitcast.2, align 4
    %getelementptr.2 = getelementptr i32, i32* %bitcast.2, i32 1
    %bitcast.3 = bitcast i32* %getelementptr.2 to i32**
    store i32** %bitcast.3, i32*** %getelementptr.1, align 4
    br label %for_step
for_step:
    %add.1 = add i32 %_new_tmp.load, 1
    store i32 %add.1, i32* %_new_tmp.addr, align 4
    br label %for_cond
for_exit:
    store i32*** %bitcast.1, i32**** %A.addr, align 4
    store i32 0, i32* %main.ret.addr, align 4
    br label %main.exit
}


