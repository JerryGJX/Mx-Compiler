
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
@strConst = private unnamed_addr constant [4 x i8] c"<< \00"
@strConst.3 = private unnamed_addr constant [2 x i8] c" \00"
@strConst.4 = private unnamed_addr constant [4 x i8] c">> \00"
@strConst.2 = private unnamed_addr constant [3 x i8] c") \00"
@strConst.1 = private unnamed_addr constant [2 x i8] c"(\00"

; GlobalVar
@p = global i32 zeroinitializer
@i = global i32 zeroinitializer
@k = global i32 zeroinitializer
@n = global i32 zeroinitializer

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
    %main.ret.addr = alloca i32
    call void @_init_func()
    store i32 0, i32* %main.ret.addr
    %getInt.call = call i32 @getInt()
    store i32 %getInt.call, i32* @n
    %getInt.call.1 = call i32 @getInt()
    store i32 %getInt.call.1, i32* @p
    %getInt.call.2 = call i32 @getInt()
    store i32 %getInt.call.2, i32* @k
    %p.load = load i32, i32* @p
    %k.load = load i32, i32* @k
    %sub = sub i32 %p.load, %k.load
    %icmp = icmp sgt i32 %sub, 1
    br i1 %icmp, label %if_true, label %if_false
main.exit:
    %main.ret.load = load i32, i32* %main.ret.addr
    ret i32 %main.ret.load
if_true:
    %getelementptr = getelementptr [4 x i8], [4 x i8]* @strConst, i32 0
    call void @print(i8* %getelementptr)
    br label %if_exit
if_false:
    br label %if_exit
if_exit:
    %p.load.1 = load i32, i32* @p
    %k.load.1 = load i32, i32* @k
    %sub.1 = sub i32 %p.load.1, %k.load.1
    store i32 %sub.1, i32* @i
    br label %for_cond
for_cond:
    %i.load = load i32, i32* @i
    %p.load.2 = load i32, i32* @p
    %k.load.2 = load i32, i32* @k
    %add = add i32 %p.load.2, %k.load.2
    %icmp.1 = icmp sle i32 %i.load, %add
    br i1 %icmp.1, label %for_body, label %for_exit
for_body:
    %i.load.2 = load i32, i32* @i
    %icmp.2 = icmp sle i32 1, %i.load.2
    %zext = zext i1 %icmp.2 to i8
    store i8 %zext, i8* %tmp.addr
    br i1 %icmp.2, label %noShortCutBlock, label %logicExitBlock
for_step:
    %i.load.1 = load i32, i32* @i
    %add.1 = add i32 %i.load.1, 1
    store i32 %add.1, i32* @i
    br label %for_cond
for_exit:
    %p.load.4 = load i32, i32* @p
    %k.load.3 = load i32, i32* @k
    %add.2 = add i32 %p.load.4, %k.load.3
    %n.load.1 = load i32, i32* @n
    %icmp.5 = icmp slt i32 %add.2, %n.load.1
    br i1 %icmp.5, label %if_true.3, label %if_false.3
if_true.1:
    %i.load.4 = load i32, i32* @i
    %p.load.3 = load i32, i32* @p
    %icmp.4 = icmp eq i32 %i.load.4, %p.load.3
    br i1 %icmp.4, label %if_true.2, label %if_false.2
if_false.1:
    br label %if_exit.1
if_exit.1:
    br label %for_step
noShortCutBlock:
    %i.load.3 = load i32, i32* @i
    %n.load = load i32, i32* @n
    %icmp.3 = icmp sle i32 %i.load.3, %n.load
    %and = and i1 %icmp.2, %icmp.3
    %zext.1 = zext i1 %and to i8
    store i8 %zext.1, i8* %tmp.addr
    br label %logicExitBlock
logicExitBlock:
    %and.load = load i8, i8* %tmp.addr
    %trunc = trunc i8 %and.load to i1
    br i1 %trunc, label %if_true.1, label %if_false.1
if_true.2:
    %getelementptr.1 = getelementptr [2 x i8], [2 x i8]* @strConst.1, i32 0
    call void @print(i8* %getelementptr.1)
    %i.load.5 = load i32, i32* @i
    %toString.call = call i8* @toString(i32 %i.load.5)
    call void @print(i8* %toString.call)
    %getelementptr.2 = getelementptr [3 x i8], [3 x i8]* @strConst.2, i32 0
    call void @print(i8* %getelementptr.2)
    br label %if_exit.2
if_false.2:
    %i.load.6 = load i32, i32* @i
    call void @printInt(i32 %i.load.6)
    %getelementptr.3 = getelementptr [2 x i8], [2 x i8]* @strConst.3, i32 0
    call void @print(i8* %getelementptr.3)
    br label %if_exit.2
if_exit.2:
    br label %if_exit.1
if_true.3:
    %getelementptr.4 = getelementptr [4 x i8], [4 x i8]* @strConst.4, i32 0
    call void @print(i8* %getelementptr.4)
    br label %if_exit.3
if_false.3:
    br label %if_exit.3
if_exit.3:
    store i32 0, i32* %main.ret.addr
    br label %main.exit
}


