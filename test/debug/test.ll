
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
@strConst = private unnamed_addr constant [2 x i8] c" \00"
@strConst.1 = private unnamed_addr constant [2 x i8] c"\0A\00"
@strConst.2 = private unnamed_addr constant [8 x i8] c"Total: \00"

; GlobalVar
@prime = global i32* zeroinitializer
@result = global i32** zeroinitializer
@resultCount = global i32 zeroinitializer
@b = global i32* zeroinitializer
@tmp = global i32* zeroinitializer
@i = global i32 zeroinitializer
@j = global i32 zeroinitializer
@primeCount = global i32 zeroinitializer
@gps = global i32* zeroinitializer
@M = global i32 zeroinitializer
@N = global i32 zeroinitializer

; StructDef

; GlobalFunc
define void @_init_func() {
_init_func.entry:
    %mul = mul i32 1001, 4
    %add = add i32 %mul, 4
    %_malloc.call = call i8* @_malloc(i32 %add)
    %bitcast = bitcast i8* %_malloc.call to i32*
    store i32 1001, i32* %bitcast
    %getelementptr = getelementptr i32, i32* %bitcast, i32 1
    %bitcast.1 = bitcast i32* %getelementptr to i32*
    store i32* %bitcast.1, i32** @b
    %mul.1 = mul i32 170, 4
    %add.1 = add i32 %mul.1, 4
    %_malloc.call.1 = call i8* @_malloc(i32 %add.1)
    %bitcast.2 = bitcast i8* %_malloc.call.1 to i32*
    store i32 170, i32* %bitcast.2
    %getelementptr.1 = getelementptr i32, i32* %bitcast.2, i32 1
    %bitcast.3 = bitcast i32* %getelementptr.1 to i32*
    store i32* %bitcast.3, i32** @prime
    %mul.2 = mul i32 1001, 4
    %add.2 = add i32 %mul.2, 4
    %_malloc.call.2 = call i8* @_malloc(i32 %add.2)
    %bitcast.4 = bitcast i8* %_malloc.call.2 to i32*
    store i32 1001, i32* %bitcast.4
    %getelementptr.2 = getelementptr i32, i32* %bitcast.4, i32 1
    %bitcast.5 = bitcast i32* %getelementptr.2 to i32*
    store i32* %bitcast.5, i32** @gps
    %mul.3 = mul i32 1, 4
    %add.3 = add i32 %mul.3, 4
    %_malloc.call.3 = call i8* @_malloc(i32 %add.3)
    %bitcast.6 = bitcast i8* %_malloc.call.3 to i32*
    store i32 1, i32* %bitcast.6
    %getelementptr.3 = getelementptr i32, i32* %bitcast.6, i32 1
    %bitcast.7 = bitcast i32* %getelementptr.3 to i32*
    store i32* %bitcast.7, i32** @tmp
    store i32** null, i32*** @result
    br label %_init_func.exit
_init_func.exit:
    ret void
}

define void @origin(i32 %N.1) {
origin.entry:
    %N.addr = alloca i32
    store i32 %N.1, i32* %N.addr
    %N.load = load i32, i32* %N.addr
    %mul.4 = mul i32 %N.load, 4
    %add.4 = add i32 %mul.4, 4
    %_malloc.call.4 = call i8* @_malloc(i32 %add.4)
    %bitcast.8 = bitcast i8* %_malloc.call.4 to i32*
    store i32 %N.load, i32* %bitcast.8
    %getelementptr.4 = getelementptr i32, i32* %bitcast.8, i32 1
    %bitcast.9 = bitcast i32* %getelementptr.4 to i32**
    store i32** %bitcast.9, i32*** @result
    store i32 0, i32* @i
    br label %for_cond
origin.exit:
    ret void
for_cond:
    %i.load = load i32, i32* @i
    %N.load.1 = load i32, i32* %N.addr
    %icmp = icmp slt i32 %i.load, %N.load.1
    br i1 %icmp, label %for_body, label %for_exit
for_body:
    %result.load = load i32**, i32*** @result
    %i.load.2 = load i32, i32* @i
    %getelementptr.5 = getelementptr i32*, i32** %result.load, i32 %i.load.2
    %N.load.2 = load i32, i32* %N.addr
    %mul.5 = mul i32 %N.load.2, 4
    %add.6 = add i32 %mul.5, 4
    %_malloc.call.5 = call i8* @_malloc(i32 %add.6)
    %bitcast.10 = bitcast i8* %_malloc.call.5 to i32*
    store i32 %N.load.2, i32* %bitcast.10
    %getelementptr.6 = getelementptr i32, i32* %bitcast.10, i32 1
    %bitcast.11 = bitcast i32* %getelementptr.6 to i32*
    store i32* %bitcast.11, i32** %getelementptr.5
    store i32 0, i32* @j
    br label %for_cond.1
for_step:
    %i.load.1 = load i32, i32* @i
    %add.5 = add i32 %i.load.1, 1
    store i32 %add.5, i32* @i
    br label %for_cond
for_exit:
    br label %origin.exit
for_cond.1:
    %j.load = load i32, i32* @j
    %N.load.3 = load i32, i32* %N.addr
    %icmp.1 = icmp slt i32 %j.load, %N.load.3
    br i1 %icmp.1, label %for_body.1, label %for_exit.1
for_body.1:
    %result.load.1 = load i32**, i32*** @result
    %i.load.3 = load i32, i32* @i
    %getelementptr.7 = getelementptr i32*, i32** %result.load.1, i32 %i.load.3
    %null.load = load i32*, i32** %getelementptr.7
    %j.load.2 = load i32, i32* @j
    %getelementptr.8 = getelementptr i32, i32* %null.load, i32 %j.load.2
    store i32 0, i32* %getelementptr.8
    br label %for_step.1
for_step.1:
    %j.load.1 = load i32, i32* @j
    %add.7 = add i32 %j.load.1, 1
    store i32 %add.7, i32* @j
    br label %for_cond.1
for_exit.1:
    br label %for_step
}

define void @printF(i32 %k1.1, i32 %k2.1, i32 %k3) {
printF.entry:
    %k3.addr = alloca i32
    %k2.addr.1 = alloca i32
    %k1.addr.1 = alloca i32
    store i32 %k1.1, i32* %k1.addr.1
    store i32 %k2.1, i32* %k2.addr.1
    store i32 %k3, i32* %k3.addr
    %k1.load.8 = load i32, i32* %k1.addr.1
    %toString.call = call i8* @toString(i32 %k1.load.8)
    call void @print(i8* %toString.call)
    br label %while_cond.1
printF.exit:
    ret void
while_cond.1:
    %k3.load = load i32, i32* %k3.addr
    %icmp.9 = icmp sgt i32 %k3.load, 0
    br i1 %icmp.9, label %while_body.1, label %while_exit.1
while_body.1:
    %getelementptr.35 = getelementptr [2 x i8], [2 x i8]* @strConst, i32 0
    call void @print(i8* %getelementptr.35)
    %k2.load.9 = load i32, i32* %k2.addr.1
    %toString.call.1 = call i8* @toString(i32 %k2.load.9)
    call void @print(i8* %toString.call.1)
    %k2.load.10 = load i32, i32* %k2.addr.1
    %mul.11 = mul i32 %k2.load.10, 2
    %k1.load.9 = load i32, i32* %k1.addr.1
    %sub.5 = sub i32 %mul.11, %k1.load.9
    store i32 %sub.5, i32* %k2.addr.1
    %k1.load.10 = load i32, i32* %k1.addr.1
    %k2.load.11 = load i32, i32* %k2.addr.1
    %add.12 = add i32 %k1.load.10, %k2.load.11
    %sdiv = sdiv i32 %add.12, 2
    store i32 %sdiv, i32* %k1.addr.1
    %k3.load.1 = load i32, i32* %k3.addr
    %sub.6 = sub i32 %k3.load.1, 1
    store i32 %sub.6, i32* %k3.addr
    br label %while_cond.1
while_exit.1:
    %getelementptr.36 = getelementptr [2 x i8], [2 x i8]* @strConst.1, i32 0
    call void @print(i8* %getelementptr.36)
    br label %printF.exit
}

define void @getPrime(i32 %N.2) {
getPrime.entry:
    %i.addr = alloca i32
    %count.addr = alloca i32
    %N.addr.1 = alloca i32
    store i32 %N.2, i32* %N.addr.1
    store i32 2, i32* %count.addr
    store i32 2, i32* %i.addr
    br label %for_cond.2
getPrime.exit:
    ret void
for_cond.2:
    %i.load.4 = load i32, i32* %i.addr
    %N.load.4 = load i32, i32* %N.addr.1
    %icmp.2 = icmp sle i32 %i.load.4, %N.load.4
    br i1 %icmp.2, label %for_body.2, label %for_exit.2
for_body.2:
    %b.load = load i32*, i32** @b
    %i.load.6 = load i32, i32* %i.addr
    %getelementptr.9 = getelementptr i32, i32* %b.load, i32 %i.load.6
    %null.load.1 = load i32, i32* %getelementptr.9
    %icmp.3 = icmp eq i32 %null.load.1, 1
    br i1 %icmp.3, label %if_true, label %if_false
for_step.2:
    %i.load.5 = load i32, i32* %i.addr
    %add.8 = add i32 %i.load.5, 1
    store i32 %add.8, i32* %i.addr
    br label %for_cond.2
for_exit.2:
    br label %getPrime.exit
if_true:
    %tmp.load = load i32*, i32** @tmp
    %getelementptr.10 = getelementptr i32, i32* %tmp.load, i32 0
    %tmp.load.1 = load i32*, i32** @tmp
    %getelementptr.11 = getelementptr i32, i32* %tmp.load.1, i32 0
    %null.load.2 = load i32, i32* %getelementptr.11
    %add.9 = add i32 %null.load.2, 1
    store i32 %add.9, i32* %getelementptr.10
    %tmp.load.2 = load i32*, i32** @tmp
    %getelementptr.12 = getelementptr i32, i32* %tmp.load.2, i32 0
    %prime.load = load i32*, i32** @prime
    %null.load.3 = load i32, i32* %getelementptr.12
    %getelementptr.13 = getelementptr i32, i32* %prime.load, i32 %null.load.3
    %i.load.7 = load i32, i32* %i.addr
    store i32 %i.load.7, i32* %getelementptr.13
    %gps.load = load i32*, i32** @gps
    %i.load.8 = load i32, i32* %i.addr
    %getelementptr.14 = getelementptr i32, i32* %gps.load, i32 %i.load.8
    %tmp.load.3 = load i32*, i32** @tmp
    %getelementptr.15 = getelementptr i32, i32* %tmp.load.3, i32 0
    %null.load.4 = load i32, i32* %getelementptr.15
    store i32 %null.load.4, i32* %getelementptr.14
    br label %if_exit
if_false:
    br label %if_exit
if_exit:
    br label %while_cond
while_cond:
    %i.load.9 = load i32, i32* %i.addr
    %count.load = load i32, i32* %count.addr
    %mul.6 = mul i32 %i.load.9, %count.load
    %N.load.5 = load i32, i32* %N.addr.1
    %icmp.4 = icmp sle i32 %mul.6, %N.load.5
    br i1 %icmp.4, label %while_body, label %while_exit
while_body:
    %i.load.10 = load i32, i32* %i.addr
    %count.load.1 = load i32, i32* %count.addr
    %mul.7 = mul i32 %i.load.10, %count.load.1
    %b.load.1 = load i32*, i32** @b
    %getelementptr.16 = getelementptr i32, i32* %b.load.1, i32 %mul.7
    store i32 0, i32* %getelementptr.16
    %count.load.2 = load i32, i32* %count.addr
    %add.10 = add i32 %count.load.2, 1
    store i32 %add.10, i32* %count.addr
    br label %while_cond
while_exit:
    store i32 2, i32* %count.addr
    br label %for_step.2
}

define i32 @getResult(i32 %N.3, i32 %k1, i32 %k2) {
getResult.entry:
    %getResult.ret.addr = alloca i32
    %k2.addr = alloca i32
    %k1.addr = alloca i32
    %N.addr.2 = alloca i32
    store i32 %N.3, i32* %N.addr.2
    store i32 %k1, i32* %k1.addr
    store i32 %k2, i32* %k2.addr
    %result.load.2 = load i32**, i32*** @result
    %k1.load = load i32, i32* %k1.addr
    %getelementptr.17 = getelementptr i32*, i32** %result.load.2, i32 %k1.load
    %null.load.5 = load i32*, i32** %getelementptr.17
    %k2.load = load i32, i32* %k2.addr
    %getelementptr.18 = getelementptr i32, i32* %null.load.5, i32 %k2.load
    %null.load.6 = load i32, i32* %getelementptr.18
    %sub = sub i32 0, 1
    %icmp.5 = icmp eq i32 %null.load.6, %sub
    br i1 %icmp.5, label %if_true.1, label %if_false.1
getResult.exit:
    %getResult.ret.load = load i32, i32* %getResult.ret.addr
    ret i32 %getResult.ret.load
if_true.1:
    %prime.load.1 = load i32*, i32** @prime
    %k2.load.1 = load i32, i32* %k2.addr
    %getelementptr.19 = getelementptr i32, i32* %prime.load.1, i32 %k2.load.1
    %null.load.7 = load i32, i32* %getelementptr.19
    %mul.8 = mul i32 %null.load.7, 2
    %prime.load.2 = load i32*, i32** @prime
    %k1.load.1 = load i32, i32* %k1.addr
    %getelementptr.20 = getelementptr i32, i32* %prime.load.2, i32 %k1.load.1
    %null.load.8 = load i32, i32* %getelementptr.20
    %sub.1 = sub i32 %mul.8, %null.load.8
    %N.load.6 = load i32, i32* %N.addr.2
    %icmp.6 = icmp sle i32 %sub.1, %N.load.6
    br i1 %icmp.6, label %if_true.2, label %if_false.2
if_false.1:
    br label %if_exit.1
if_exit.1:
    %result.load.4 = load i32**, i32*** @result
    %k1.load.5 = load i32, i32* %k1.addr
    %getelementptr.29 = getelementptr i32*, i32** %result.load.4, i32 %k1.load.5
    %null.load.16 = load i32*, i32** %getelementptr.29
    %k2.load.6 = load i32, i32* %k2.addr
    %getelementptr.30 = getelementptr i32, i32* %null.load.16, i32 %k2.load.6
    %null.load.17 = load i32, i32* %getelementptr.30
    %sub.4 = sub i32 0, 1
    %icmp.8 = icmp eq i32 %null.load.17, %sub.4
    br i1 %icmp.8, label %if_true.4, label %if_false.4
if_true.2:
    %prime.load.3 = load i32*, i32** @prime
    %k2.load.2 = load i32, i32* %k2.addr
    %getelementptr.21 = getelementptr i32, i32* %prime.load.3, i32 %k2.load.2
    %null.load.9 = load i32, i32* %getelementptr.21
    %mul.9 = mul i32 %null.load.9, 2
    %prime.load.4 = load i32*, i32** @prime
    %k1.load.2 = load i32, i32* %k1.addr
    %getelementptr.22 = getelementptr i32, i32* %prime.load.4, i32 %k1.load.2
    %null.load.10 = load i32, i32* %getelementptr.22
    %sub.2 = sub i32 %mul.9, %null.load.10
    %b.load.2 = load i32*, i32** @b
    %getelementptr.23 = getelementptr i32, i32* %b.load.2, i32 %sub.2
    %null.load.11 = load i32, i32* %getelementptr.23
    %icmp.7 = icmp ne i32 %null.load.11, 0
    br i1 %icmp.7, label %if_true.3, label %if_false.3
if_false.2:
    br label %if_exit.2
if_exit.2:
    br label %if_exit.1
if_true.3:
    %result.load.3 = load i32**, i32*** @result
    %k1.load.3 = load i32, i32* %k1.addr
    %getelementptr.24 = getelementptr i32*, i32** %result.load.3, i32 %k1.load.3
    %null.load.12 = load i32*, i32** %getelementptr.24
    %k2.load.3 = load i32, i32* %k2.addr
    %getelementptr.25 = getelementptr i32, i32* %null.load.12, i32 %k2.load.3
    %N.load.7 = load i32, i32* %N.addr.2
    %k2.load.4 = load i32, i32* %k2.addr
    %prime.load.5 = load i32*, i32** @prime
    %k2.load.5 = load i32, i32* %k2.addr
    %getelementptr.26 = getelementptr i32, i32* %prime.load.5, i32 %k2.load.5
    %null.load.13 = load i32, i32* %getelementptr.26
    %mul.10 = mul i32 %null.load.13, 2
    %prime.load.6 = load i32*, i32** @prime
    %k1.load.4 = load i32, i32* %k1.addr
    %getelementptr.27 = getelementptr i32, i32* %prime.load.6, i32 %k1.load.4
    %null.load.14 = load i32, i32* %getelementptr.27
    %sub.3 = sub i32 %mul.10, %null.load.14
    %gps.load.1 = load i32*, i32** @gps
    %getelementptr.28 = getelementptr i32, i32* %gps.load.1, i32 %sub.3
    %null.load.15 = load i32, i32* %getelementptr.28
    %getResult.call = call i32 @getResult(i32 %N.load.7, i32 %k2.load.4, i32 %null.load.15)
    %add.11 = add i32 %getResult.call, 1
    store i32 %add.11, i32* %getelementptr.25
    br label %if_exit.3
if_false.3:
    br label %if_exit.3
if_exit.3:
    br label %if_exit.2
if_true.4:
    %result.load.5 = load i32**, i32*** @result
    %k1.load.6 = load i32, i32* %k1.addr
    %getelementptr.31 = getelementptr i32*, i32** %result.load.5, i32 %k1.load.6
    %null.load.18 = load i32*, i32** %getelementptr.31
    %k2.load.7 = load i32, i32* %k2.addr
    %getelementptr.32 = getelementptr i32, i32* %null.load.18, i32 %k2.load.7
    store i32 1, i32* %getelementptr.32
    br label %if_exit.4
if_false.4:
    br label %if_exit.4
if_exit.4:
    %result.load.6 = load i32**, i32*** @result
    %k1.load.7 = load i32, i32* %k1.addr
    %getelementptr.33 = getelementptr i32*, i32** %result.load.6, i32 %k1.load.7
    %null.load.19 = load i32*, i32** %getelementptr.33
    %k2.load.8 = load i32, i32* %k2.addr
    %getelementptr.34 = getelementptr i32, i32* %null.load.19, i32 %k2.load.8
    %null.load.20 = load i32, i32* %getelementptr.34
    store i32 %null.load.20, i32* %getResult.ret.addr
    br label %getResult.exit
}


; MainFunc
define i32 @main() {
main.entry:
    %main.ret.addr = alloca i32
    call void @_init_func()
    store i32 0, i32* %main.ret.addr
    call void @origin(i32 170)
    store i32 1000, i32* @N
    %getInt.call = call i32 @getInt()
    store i32 %getInt.call, i32* @M
    store i32 0, i32* @primeCount
    store i32 0, i32* @resultCount
    %tmp.load.4 = load i32*, i32** @tmp
    %getelementptr.37 = getelementptr i32, i32* %tmp.load.4, i32 0
    store i32 0, i32* %getelementptr.37
    store i32 0, i32* @i
    br label %for_cond.3
main.exit:
    %main.ret.load = load i32, i32* %main.ret.addr
    ret i32 %main.ret.load
for_cond.3:
    %i.load.11 = load i32, i32* @i
    %N.load.8 = load i32, i32* @N
    %add.13 = add i32 %N.load.8, 1
    %icmp.10 = icmp slt i32 %i.load.11, %add.13
    br i1 %icmp.10, label %for_body.3, label %for_exit.3
for_body.3:
    %b.load.3 = load i32*, i32** @b
    %i.load.13 = load i32, i32* @i
    %getelementptr.38 = getelementptr i32, i32* %b.load.3, i32 %i.load.13
    store i32 1, i32* %getelementptr.38
    %gps.load.2 = load i32*, i32** @gps
    %i.load.14 = load i32, i32* @i
    %getelementptr.39 = getelementptr i32, i32* %gps.load.2, i32 %i.load.14
    store i32 0, i32* %getelementptr.39
    br label %for_step.3
for_step.3:
    %i.load.12 = load i32, i32* @i
    %add.14 = add i32 %i.load.12, 1
    store i32 %add.14, i32* @i
    br label %for_cond.3
for_exit.3:
    store i32 0, i32* @i
    br label %for_cond.4
for_cond.4:
    %i.load.15 = load i32, i32* @i
    %M.load = load i32, i32* @M
    %add.15 = add i32 %M.load, 1
    %icmp.11 = icmp slt i32 %i.load.15, %add.15
    br i1 %icmp.11, label %for_body.4, label %for_exit.4
for_body.4:
    %prime.load.7 = load i32*, i32** @prime
    %i.load.17 = load i32, i32* @i
    %getelementptr.40 = getelementptr i32, i32* %prime.load.7, i32 %i.load.17
    store i32 0, i32* %getelementptr.40
    br label %for_step.4
for_step.4:
    %i.load.16 = load i32, i32* @i
    %add.16 = add i32 %i.load.16, 1
    store i32 %add.16, i32* @i
    br label %for_cond.4
for_exit.4:
    store i32 0, i32* @i
    br label %for_cond.5
for_cond.5:
    %i.load.18 = load i32, i32* @i
    %M.load.1 = load i32, i32* @M
    %icmp.12 = icmp sle i32 %i.load.18, %M.load.1
    br i1 %icmp.12, label %for_body.5, label %for_exit.5
for_body.5:
    store i32 0, i32* @j
    br label %for_cond.6
for_step.5:
    %i.load.19 = load i32, i32* @i
    %add.17 = add i32 %i.load.19, 1
    store i32 %add.17, i32* @i
    br label %for_cond.5
for_exit.5:
    %N.load.9 = load i32, i32* @N
    call void @getPrime(i32 %N.load.9)
    %tmp.load.5 = load i32*, i32** @tmp
    %getelementptr.43 = getelementptr i32, i32* %tmp.load.5, i32 0
    %null.load.22 = load i32, i32* %getelementptr.43
    store i32 %null.load.22, i32* @primeCount
    store i32 1, i32* @i
    br label %for_cond.7
for_cond.6:
    %j.load.3 = load i32, i32* @j
    %M.load.2 = load i32, i32* @M
    %icmp.13 = icmp sle i32 %j.load.3, %M.load.2
    br i1 %icmp.13, label %for_body.6, label %for_exit.6
for_body.6:
    %result.load.7 = load i32**, i32*** @result
    %i.load.20 = load i32, i32* @i
    %getelementptr.41 = getelementptr i32*, i32** %result.load.7, i32 %i.load.20
    %null.load.21 = load i32*, i32** %getelementptr.41
    %j.load.5 = load i32, i32* @j
    %getelementptr.42 = getelementptr i32, i32* %null.load.21, i32 %j.load.5
    %sub.7 = sub i32 0, 1
    store i32 %sub.7, i32* %getelementptr.42
    br label %for_step.6
for_step.6:
    %j.load.4 = load i32, i32* @j
    %add.18 = add i32 %j.load.4, 1
    store i32 %add.18, i32* @j
    br label %for_cond.6
for_exit.6:
    br label %for_step.5
for_cond.7:
    %i.load.21 = load i32, i32* @i
    %primeCount.load = load i32, i32* @primeCount
    %icmp.14 = icmp slt i32 %i.load.21, %primeCount.load
    br i1 %icmp.14, label %for_body.7, label %for_exit.7
for_body.7:
    %i.load.23 = load i32, i32* @i
    %add.20 = add i32 %i.load.23, 1
    store i32 %add.20, i32* @j
    br label %for_cond.8
for_step.7:
    %i.load.22 = load i32, i32* @i
    %add.19 = add i32 %i.load.22, 1
    store i32 %add.19, i32* @i
    br label %for_cond.7
for_exit.7:
    %getelementptr.54 = getelementptr [8 x i8], [8 x i8]* @strConst.2, i32 0
    call void @print(i8* %getelementptr.54)
    %resultCount.load.1 = load i32, i32* @resultCount
    %toString.call.2 = call i8* @toString(i32 %resultCount.load.1)
    call void @println(i8* %toString.call.2)
    store i32 0, i32* %main.ret.addr
    br label %main.exit
for_cond.8:
    %j.load.6 = load i32, i32* @j
    %primeCount.load.1 = load i32, i32* @primeCount
    %icmp.15 = icmp sle i32 %j.load.6, %primeCount.load.1
    br i1 %icmp.15, label %for_body.8, label %for_exit.8
for_body.8:
    %result.load.8 = load i32**, i32*** @result
    %i.load.24 = load i32, i32* @i
    %getelementptr.44 = getelementptr i32*, i32** %result.load.8, i32 %i.load.24
    %null.load.23 = load i32*, i32** %getelementptr.44
    %j.load.8 = load i32, i32* @j
    %getelementptr.45 = getelementptr i32, i32* %null.load.23, i32 %j.load.8
    %null.load.24 = load i32, i32* %getelementptr.45
    %sub.8 = sub i32 0, 1
    %icmp.16 = icmp eq i32 %null.load.24, %sub.8
    br i1 %icmp.16, label %if_true.5, label %if_false.5
for_step.8:
    %j.load.7 = load i32, i32* @j
    %add.21 = add i32 %j.load.7, 1
    store i32 %add.21, i32* @j
    br label %for_cond.8
for_exit.8:
    br label %for_step.7
if_true.5:
    %result.load.9 = load i32**, i32*** @result
    %i.load.25 = load i32, i32* @i
    %getelementptr.46 = getelementptr i32*, i32** %result.load.9, i32 %i.load.25
    %null.load.25 = load i32*, i32** %getelementptr.46
    %j.load.9 = load i32, i32* @j
    %getelementptr.47 = getelementptr i32, i32* %null.load.25, i32 %j.load.9
    %N.load.10 = load i32, i32* @N
    %i.load.26 = load i32, i32* @i
    %j.load.10 = load i32, i32* @j
    %getResult.call.1 = call i32 @getResult(i32 %N.load.10, i32 %i.load.26, i32 %j.load.10)
    store i32 %getResult.call.1, i32* %getelementptr.47
    %result.load.10 = load i32**, i32*** @result
    %i.load.27 = load i32, i32* @i
    %getelementptr.48 = getelementptr i32*, i32** %result.load.10, i32 %i.load.27
    %null.load.26 = load i32*, i32** %getelementptr.48
    %j.load.11 = load i32, i32* @j
    %getelementptr.49 = getelementptr i32, i32* %null.load.26, i32 %j.load.11
    %null.load.27 = load i32, i32* %getelementptr.49
    %icmp.17 = icmp sgt i32 %null.load.27, 1
    br i1 %icmp.17, label %if_true.6, label %if_false.6
if_false.5:
    br label %if_exit.5
if_exit.5:
    br label %for_step.8
if_true.6:
    %prime.load.8 = load i32*, i32** @prime
    %i.load.28 = load i32, i32* @i
    %getelementptr.50 = getelementptr i32, i32* %prime.load.8, i32 %i.load.28
    %null.load.28 = load i32, i32* %getelementptr.50
    %prime.load.9 = load i32*, i32** @prime
    %j.load.12 = load i32, i32* @j
    %getelementptr.51 = getelementptr i32, i32* %prime.load.9, i32 %j.load.12
    %null.load.29 = load i32, i32* %getelementptr.51
    %result.load.11 = load i32**, i32*** @result
    %i.load.29 = load i32, i32* @i
    %getelementptr.52 = getelementptr i32*, i32** %result.load.11, i32 %i.load.29
    %null.load.30 = load i32*, i32** %getelementptr.52
    %j.load.13 = load i32, i32* @j
    %getelementptr.53 = getelementptr i32, i32* %null.load.30, i32 %j.load.13
    %null.load.31 = load i32, i32* %getelementptr.53
    call void @printF(i32 %null.load.28, i32 %null.load.29, i32 %null.load.31)
    %resultCount.load = load i32, i32* @resultCount
    %add.22 = add i32 %resultCount.load, 1
    store i32 %add.22, i32* @resultCount
    br label %if_exit.6
if_false.6:
    br label %if_exit.6
if_exit.6:
    br label %if_exit.5
}


