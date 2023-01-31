
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
@strConst = private unnamed_addr constant [1 x i8] c"\00"

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
    %i.addr = alloca i32
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
    %getelementptr.2 = getelementptr i32*, i32** %b.load, i32 0
    %a.load = load i32*, i32** @a
    store i32* %a.load, i32** %getelementptr.2
    %b.load.1 = load i32**, i32*** %b.addr
    %getelementptr.3 = getelementptr i32*, i32** %b.load.1, i32 1
    %a.load.1 = load i32*, i32** @a
    store i32* %a.load.1, i32** %getelementptr.3
    %b.load.2 = load i32**, i32*** %b.addr
    %getelementptr.4 = getelementptr i32*, i32** %b.load.2, i32 2
    %a.load.2 = load i32*, i32** @a
    store i32* %a.load.2, i32** %getelementptr.4
    %b.load.3 = load i32**, i32*** %b.addr
    %getelementptr.5 = getelementptr i32*, i32** %b.load.3, i32 3
    %a.load.3 = load i32*, i32** @a
    store i32* %a.load.3, i32** %getelementptr.5
    %b.load.4 = load i32**, i32*** %b.addr
    %bitcast.4 = bitcast i32** %b.load.4 to i32*
    %getelementptr.6 = getelementptr i32, i32* %bitcast.4, i32 -1
    %array.load = load i32, i32* %getelementptr.6
    %toString.call = call i8* @toString(i32 %array.load)
    call void @println(i8* %toString.call)
    store i32 0, i32* %i.addr
    br label %for_cond
main.exit:
    %main.ret.load = load i32, i32* %main.ret.addr
    ret i32 %main.ret.load
for_cond:
    %i.load = load i32, i32* %i.addr
    %b.load.5 = load i32**, i32*** %b.addr
    %getelementptr.7 = getelementptr i32*, i32** %b.load.5, i32 0
    %null.load = load i32*, i32** %getelementptr.7
    %bitcast.5 = bitcast i32* %null.load to i32*
    %getelementptr.8 = getelementptr i32, i32* %bitcast.5, i32 -1
    %array.load.1 = load i32, i32* %getelementptr.8
    %icmp = icmp slt i32 %i.load, %array.load.1
    br i1 %icmp, label %for_body, label %for_exit
for_body:
    %b.load.6 = load i32**, i32*** %b.addr
    %getelementptr.9 = getelementptr i32*, i32** %b.load.6, i32 0
    %null.load.1 = load i32*, i32** %getelementptr.9
    %i.load.2 = load i32, i32* %i.addr
    %getelementptr.10 = getelementptr i32, i32* %null.load.1, i32 %i.load.2
    %getInt.call = call i32 @getInt()
    store i32 %getInt.call, i32* %getelementptr.10
    br label %for_step
for_step:
    %i.load.1 = load i32, i32* %i.addr
    %add.2 = add i32 %i.load.1, 1
    store i32 %add.2, i32* %i.addr
    br label %for_cond
for_exit:
    store i32 0, i32* %i.addr
    br label %for_cond.1
for_cond.1:
    %i.load.3 = load i32, i32* %i.addr
    %b.load.7 = load i32**, i32*** %b.addr
    %getelementptr.11 = getelementptr i32*, i32** %b.load.7, i32 1
    %null.load.2 = load i32*, i32** %getelementptr.11
    %bitcast.6 = bitcast i32* %null.load.2 to i32*
    %getelementptr.12 = getelementptr i32, i32* %bitcast.6, i32 -1
    %array.load.2 = load i32, i32* %getelementptr.12
    %icmp.1 = icmp slt i32 %i.load.3, %array.load.2
    br i1 %icmp.1, label %for_body.1, label %for_exit.1
for_body.1:
    %b.load.8 = load i32**, i32*** %b.addr
    %getelementptr.13 = getelementptr i32*, i32** %b.load.8, i32 1
    %null.load.3 = load i32*, i32** %getelementptr.13
    %i.load.5 = load i32, i32* %i.addr
    %getelementptr.14 = getelementptr i32, i32* %null.load.3, i32 %i.load.5
    %null.load.4 = load i32, i32* %getelementptr.14
    %toString.call.1 = call i8* @toString(i32 %null.load.4)
    call void @print(i8* %toString.call.1)
    br label %for_step.1
for_step.1:
    %i.load.4 = load i32, i32* %i.addr
    %add.3 = add i32 %i.load.4, 1
    store i32 %add.3, i32* %i.addr
    br label %for_cond.1
for_exit.1:
    %getelementptr.15 = getelementptr [1 x i8], [1 x i8]* @strConst, i32 0
    call void @println(i8* %getelementptr.15)
    store i32 0, i32* %i.addr
    br label %for_cond.2
for_cond.2:
    %i.load.6 = load i32, i32* %i.addr
    %b.load.9 = load i32**, i32*** %b.addr
    %getelementptr.16 = getelementptr i32*, i32** %b.load.9, i32 2
    %null.load.5 = load i32*, i32** %getelementptr.16
    %bitcast.7 = bitcast i32* %null.load.5 to i32*
    %getelementptr.17 = getelementptr i32, i32* %bitcast.7, i32 -1
    %array.load.3 = load i32, i32* %getelementptr.17
    %icmp.2 = icmp slt i32 %i.load.6, %array.load.3
    br i1 %icmp.2, label %for_body.2, label %for_exit.2
for_body.2:
    %b.load.10 = load i32**, i32*** %b.addr
    %getelementptr.18 = getelementptr i32*, i32** %b.load.10, i32 2
    %null.load.6 = load i32*, i32** %getelementptr.18
    %i.load.8 = load i32, i32* %i.addr
    %getelementptr.19 = getelementptr i32, i32* %null.load.6, i32 %i.load.8
    store i32 0, i32* %getelementptr.19
    br label %for_step.2
for_step.2:
    %i.load.7 = load i32, i32* %i.addr
    %add.4 = add i32 %i.load.7, 1
    store i32 %add.4, i32* %i.addr
    br label %for_cond.2
for_exit.2:
    store i32 0, i32* %i.addr
    br label %for_cond.3
for_cond.3:
    %i.load.9 = load i32, i32* %i.addr
    %b.load.11 = load i32**, i32*** %b.addr
    %getelementptr.20 = getelementptr i32*, i32** %b.load.11, i32 3
    %null.load.7 = load i32*, i32** %getelementptr.20
    %bitcast.8 = bitcast i32* %null.load.7 to i32*
    %getelementptr.21 = getelementptr i32, i32* %bitcast.8, i32 -1
    %array.load.4 = load i32, i32* %getelementptr.21
    %icmp.3 = icmp slt i32 %i.load.9, %array.load.4
    br i1 %icmp.3, label %for_body.3, label %for_exit.3
for_body.3:
    %b.load.12 = load i32**, i32*** %b.addr
    %getelementptr.22 = getelementptr i32*, i32** %b.load.12, i32 3
    %null.load.8 = load i32*, i32** %getelementptr.22
    %i.load.11 = load i32, i32* %i.addr
    %getelementptr.23 = getelementptr i32, i32* %null.load.8, i32 %i.load.11
    %null.load.9 = load i32, i32* %getelementptr.23
    %toString.call.2 = call i8* @toString(i32 %null.load.9)
    call void @print(i8* %toString.call.2)
    br label %for_step.3
for_step.3:
    %i.load.10 = load i32, i32* %i.addr
    %add.5 = add i32 %i.load.10, 1
    store i32 %add.5, i32* %i.addr
    br label %for_cond.3
for_exit.3:
    store i32 0, i32* %main.ret.addr
    br label %main.exit
}


