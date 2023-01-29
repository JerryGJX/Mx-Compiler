
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
@check = global i8* zeroinitializer
@M = global i32 zeroinitializer
@N = global i32 zeroinitializer

; StructDef

; GlobalFunc
define void @_init_func() {
_init_func.entry:
    store i32 0, i32* @M
    store i8* null, i8** @check
    br label %_init_func.exit
_init_func.exit:
    ret void
}


; MainFunc
define i32 @main() {
main.entry:
    %tmp.addr.1 = alloca i32
    %tmp.addr = alloca i8
    %i.addr.1 = alloca i32
    %k.addr = alloca i32
    %P.addr = alloca i32*
    %phi.addr = alloca i32*
    %i.addr = alloca i32
    %main.ret.addr = alloca i32
    call void @_init_func()
    store i32 0, i32* %main.ret.addr
    %getInt.call = call i32 @getInt()
    store i32 %getInt.call, i32* @N
    %N.load = load i32, i32* @N
    %add = add i32 %N.load, 5
    %mul = mul i32 %add, 4
    %add.1 = add i32 %mul, 4
    %_malloc.call = call i8* @_malloc(i32 %add.1)
    %bitcast = bitcast i8* %_malloc.call to i32*
    store i32 %add, i32* %bitcast
    %getelementptr = getelementptr i32, i32* %bitcast, i32 1
    %bitcast.1 = bitcast i32* %getelementptr to i8*
    store i8* %bitcast.1, i8** @check
    store i32 0, i32* %i.addr
    br label %while_cond
main.exit:
    %main.ret.load = load i32, i32* %main.ret.addr
    ret i32 %main.ret.load
while_cond:
    %i.load = load i32, i32* %i.addr
    %N.load.1 = load i32, i32* @N
    %icmp = icmp sle i32 %i.load, %N.load.1
    br i1 %icmp, label %while_body, label %while_exit
while_body:
    %i.load.1 = load i32, i32* %i.addr
    %add.2 = add i32 %i.load.1, 1
    store i32 %add.2, i32* %i.addr
    %check.load = load i8*, i8** @check
    %getelementptr.1 = getelementptr i8, i8* %check.load, i32 %i.load.1
    %zext = zext i1 true to i8
    store i8 %zext, i8* %getelementptr.1
    br label %while_cond
while_exit:
    %N.load.2 = load i32, i32* @N
    %add.3 = add i32 %N.load.2, 5
    %mul.1 = mul i32 %add.3, 4
    %add.4 = add i32 %mul.1, 4
    %_malloc.call.1 = call i8* @_malloc(i32 %add.4)
    %bitcast.2 = bitcast i8* %_malloc.call.1 to i32*
    store i32 %add.3, i32* %bitcast.2
    %getelementptr.2 = getelementptr i32, i32* %bitcast.2, i32 1
    %bitcast.3 = bitcast i32* %getelementptr.2 to i32*
    store i32* %bitcast.3, i32** %phi.addr
    %N.load.3 = load i32, i32* @N
    %add.5 = add i32 %N.load.3, 5
    %mul.2 = mul i32 %add.5, 4
    %add.6 = add i32 %mul.2, 4
    %_malloc.call.2 = call i8* @_malloc(i32 %add.6)
    %bitcast.4 = bitcast i8* %_malloc.call.2 to i32*
    store i32 %add.5, i32* %bitcast.4
    %getelementptr.3 = getelementptr i32, i32* %bitcast.4, i32 1
    %bitcast.5 = bitcast i32* %getelementptr.3 to i32*
    store i32* %bitcast.5, i32** %P.addr
    %phi.load = load i32*, i32** %phi.addr
    %getelementptr.4 = getelementptr i32, i32* %phi.load, i32 1
    store i32 1, i32* %getelementptr.4
    store i32 2, i32* %i.addr
    br label %for_cond
for_cond:
    br label %for_body
for_body:
    %i.load.3 = load i32, i32* %i.addr
    %N.load.4 = load i32, i32* @N
    %icmp.1 = icmp sgt i32 %i.load.3, %N.load.4
    br i1 %icmp.1, label %if_true, label %if_false
for_step:
    %i.load.2 = load i32, i32* %i.addr
    %add.7 = add i32 %i.load.2, 1
    store i32 %add.7, i32* %i.addr
    br label %for_cond
for_exit:
    store i32 0, i32* %main.ret.addr
    br label %main.exit
if_true:
    br label %for_exit
if_false:
    br label %if_exit
if_exit:
    %check.load.1 = load i8*, i8** @check
    %i.load.4 = load i32, i32* %i.addr
    %getelementptr.5 = getelementptr i8, i8* %check.load.1, i32 %i.load.4
    %null.load = load i8, i8* %getelementptr.5
    %trunc = trunc i8 %null.load to i1
    br i1 %trunc, label %if_true.1, label %if_false.1
if_true.1:
    %M.load = load i32, i32* @M
    %add.8 = add i32 %M.load, 1
    store i32 %add.8, i32* @M
    %P.load = load i32*, i32** %P.addr
    %getelementptr.6 = getelementptr i32, i32* %P.load, i32 %add.8
    %i.load.5 = load i32, i32* %i.addr
    store i32 %i.load.5, i32* %getelementptr.6
    %phi.load.1 = load i32*, i32** %phi.addr
    %i.load.6 = load i32, i32* %i.addr
    %getelementptr.7 = getelementptr i32, i32* %phi.load.1, i32 %i.load.6
    %i.load.7 = load i32, i32* %i.addr
    %sub = sub i32 %i.load.7, 1
    store i32 %sub, i32* %getelementptr.7
    br label %if_exit.1
if_false.1:
    br label %if_exit.1
if_exit.1:
    %i.load.8 = load i32, i32* %i.addr
    store i32 %i.load.8, i32* %k.addr
    store i32 1, i32* %i.addr.1
    br label %for_cond.1
for_cond.1:
    %i.load.9 = load i32, i32* %i.addr.1
    %M.load.1 = load i32, i32* @M
    %icmp.2 = icmp sle i32 %i.load.9, %M.load.1
    %zext.1 = zext i1 %icmp.2 to i8
    store i8 %zext.1, i8* %tmp.addr
    br i1 %icmp.2, label %noShortCutBlock, label %logicExitBlock
for_body.1:
    %k.load.1 = load i32, i32* %k.addr
    %P.load.2 = load i32*, i32** %P.addr
    %i.load.12 = load i32, i32* %i.addr.1
    %getelementptr.9 = getelementptr i32, i32* %P.load.2, i32 %i.load.12
    %null.load.2 = load i32, i32* %getelementptr.9
    %mul.4 = mul i32 %k.load.1, %null.load.2
    store i32 %mul.4, i32* %tmp.addr.1
    %tmp.load = load i32, i32* %tmp.addr.1
    %N.load.6 = load i32, i32* @N
    %icmp.4 = icmp sgt i32 %tmp.load, %N.load.6
    br i1 %icmp.4, label %if_true.2, label %if_false.2
for_step.1:
    %i.load.11 = load i32, i32* %i.addr.1
    %add.9 = add i32 %i.load.11, 1
    store i32 %add.9, i32* %i.addr.1
    br label %for_cond.1
for_exit.1:
    %phi.load.6 = load i32*, i32** %phi.addr
    %k.load.6 = load i32, i32* %k.addr
    %getelementptr.19 = getelementptr i32, i32* %phi.load.6, i32 %k.load.6
    %null.load.9 = load i32, i32* %getelementptr.19
    %toString.call = call i8* @toString(i32 %null.load.9)
    call void @println(i8* %toString.call)
    br label %for_step
noShortCutBlock:
    %k.load = load i32, i32* %k.addr
    %P.load.1 = load i32*, i32** %P.addr
    %i.load.10 = load i32, i32* %i.addr.1
    %getelementptr.8 = getelementptr i32, i32* %P.load.1, i32 %i.load.10
    %null.load.1 = load i32, i32* %getelementptr.8
    %mul.3 = mul i32 %k.load, %null.load.1
    %N.load.5 = load i32, i32* @N
    %icmp.3 = icmp sle i32 %mul.3, %N.load.5
    %and = and i1 %icmp.2, %icmp.3
    %zext.2 = zext i1 %and to i8
    store i8 %zext.2, i8* %tmp.addr
    br label %logicExitBlock
logicExitBlock:
    %and.load = load i8, i8* %tmp.addr
    %trunc.1 = trunc i8 %and.load to i1
    br i1 %trunc.1, label %for_body.1, label %for_exit.1
if_true.2:
    br label %for_step.1
if_false.2:
    br label %if_exit.2
if_exit.2:
    %check.load.2 = load i8*, i8** @check
    %tmp.load.1 = load i32, i32* %tmp.addr.1
    %getelementptr.10 = getelementptr i8, i8* %check.load.2, i32 %tmp.load.1
    %zext.3 = zext i1 false to i8
    store i8 %zext.3, i8* %getelementptr.10
    %k.load.2 = load i32, i32* %k.addr
    %P.load.3 = load i32*, i32** %P.addr
    %i.load.13 = load i32, i32* %i.addr.1
    %getelementptr.11 = getelementptr i32, i32* %P.load.3, i32 %i.load.13
    %null.load.3 = load i32, i32* %getelementptr.11
    %srem = srem i32 %k.load.2, %null.load.3
    %icmp.5 = icmp eq i32 %srem, 0
    br i1 %icmp.5, label %if_true.3, label %if_false.3
if_true.3:
    %phi.load.2 = load i32*, i32** %phi.addr
    %tmp.load.2 = load i32, i32* %tmp.addr.1
    %getelementptr.12 = getelementptr i32, i32* %phi.load.2, i32 %tmp.load.2
    %phi.load.3 = load i32*, i32** %phi.addr
    %k.load.3 = load i32, i32* %k.addr
    %getelementptr.13 = getelementptr i32, i32* %phi.load.3, i32 %k.load.3
    %null.load.4 = load i32, i32* %getelementptr.13
    %P.load.4 = load i32*, i32** %P.addr
    %i.load.14 = load i32, i32* %i.addr.1
    %getelementptr.14 = getelementptr i32, i32* %P.load.4, i32 %i.load.14
    %null.load.5 = load i32, i32* %getelementptr.14
    %mul.5 = mul i32 %null.load.4, %null.load.5
    store i32 %mul.5, i32* %getelementptr.12
    br label %for_exit.1
if_false.3:
    %k.load.4 = load i32, i32* %k.addr
    %P.load.5 = load i32*, i32** %P.addr
    %i.load.15 = load i32, i32* %i.addr.1
    %getelementptr.15 = getelementptr i32, i32* %P.load.5, i32 %i.load.15
    %null.load.6 = load i32, i32* %getelementptr.15
    %mul.6 = mul i32 %k.load.4, %null.load.6
    %phi.load.4 = load i32*, i32** %phi.addr
    %getelementptr.16 = getelementptr i32, i32* %phi.load.4, i32 %mul.6
    %phi.load.5 = load i32*, i32** %phi.addr
    %k.load.5 = load i32, i32* %k.addr
    %getelementptr.17 = getelementptr i32, i32* %phi.load.5, i32 %k.load.5
    %null.load.7 = load i32, i32* %getelementptr.17
    %P.load.6 = load i32*, i32** %P.addr
    %i.load.16 = load i32, i32* %i.addr.1
    %getelementptr.18 = getelementptr i32, i32* %P.load.6, i32 %i.load.16
    %null.load.8 = load i32, i32* %getelementptr.18
    %sub.1 = sub i32 %null.load.8, 1
    %mul.7 = mul i32 %null.load.7, %sub.1
    store i32 %mul.7, i32* %getelementptr.16
    br label %if_exit.3
if_exit.3:
    br label %for_step.1
}


