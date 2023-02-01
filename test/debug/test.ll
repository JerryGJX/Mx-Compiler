
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
@strConst.2 = private unnamed_addr constant [1 x i8] c"\00"
@strConst.1 = private unnamed_addr constant [3 x i8] c" .\00"
@strConst = private unnamed_addr constant [3 x i8] c" O\00"

; GlobalVar
@col = global i32* zeroinitializer
@d = global i32** zeroinitializer
@row = global i32* zeroinitializer
@N = global i32 zeroinitializer

; StructDef

; GlobalFunc
define void @_init_func() {
_init_func.entry:
    store i32 8, i32* @N
    %mul = mul i32 8, 4
    %add = add i32 %mul, 4
    %_malloc.call = call i8* @_malloc(i32 %add)
    %bitcast = bitcast i8* %_malloc.call to i32*
    store i32 8, i32* %bitcast
    %getelementptr = getelementptr i32, i32* %bitcast, i32 0, i32 1
    %bitcast.1 = bitcast i32* %getelementptr to i32*
    store i32* %bitcast.1, i32** @row
    %mul.1 = mul i32 8, 4
    %add.1 = add i32 %mul.1, 4
    %_malloc.call.1 = call i8* @_malloc(i32 %add.1)
    %bitcast.2 = bitcast i8* %_malloc.call.1 to i32*
    store i32 8, i32* %bitcast.2
    %getelementptr.1 = getelementptr i32, i32* %bitcast.2, i32 0, i32 1
    %bitcast.3 = bitcast i32* %getelementptr.1 to i32*
    store i32* %bitcast.3, i32** @col
    %mul.2 = mul i32 2, 4
    %add.2 = add i32 %mul.2, 4
    %_malloc.call.2 = call i8* @_malloc(i32 %add.2)
    %bitcast.4 = bitcast i8* %_malloc.call.2 to i32*
    store i32 2, i32* %bitcast.4
    %getelementptr.2 = getelementptr i32, i32* %bitcast.4, i32 0, i32 1
    %bitcast.5 = bitcast i32* %getelementptr.2 to i32**
    store i32** %bitcast.5, i32*** @d
    br label %_init_func.exit
_init_func.exit:
    ret void
}

define void @printBoard() {
printBoard.entry:
    %j.addr = alloca i32
    %i.addr = alloca i32
    store i32 0, i32* %i.addr
    br label %for_cond
printBoard.exit:
    ret void
for_cond:
    %i.load = load i32, i32* %i.addr
    %N.load = load i32, i32* @N
    %icmp = icmp slt i32 %i.load, %N.load
    br i1 %icmp, label %for_body, label %for_exit
for_body:
    store i32 0, i32* %j.addr
    br label %for_cond.1
for_step:
    %i.load.1 = load i32, i32* %i.addr
    %add.3 = add i32 %i.load.1, 1
    store i32 %add.3, i32* %i.addr
    br label %for_cond
for_exit:
    %getelementptr.7 = getelementptr [1 x i8], [1 x i8]* @strConst.2, i32 0, i32 0
    call void @println(i8* %getelementptr.7)
    br label %printBoard.exit
for_cond.1:
    %j.load = load i32, i32* %j.addr
    %N.load.1 = load i32, i32* @N
    %icmp.1 = icmp slt i32 %j.load, %N.load.1
    br i1 %icmp.1, label %for_body.1, label %for_exit.1
for_body.1:
    %col.load = load i32*, i32** @col
    %i.load.2 = load i32, i32* %i.addr
    %getelementptr.3 = getelementptr i32, i32* %col.load, i32 0, i32 %i.load.2
    %null.load = load i32, i32* %getelementptr.3
    %j.load.2 = load i32, i32* %j.addr
    %icmp.2 = icmp eq i32 %null.load, %j.load.2
    br i1 %icmp.2, label %if_true, label %if_false
for_step.1:
    %j.load.1 = load i32, i32* %j.addr
    %add.4 = add i32 %j.load.1, 1
    store i32 %add.4, i32* %j.addr
    br label %for_cond.1
for_exit.1:
    %getelementptr.6 = getelementptr [1 x i8], [1 x i8]* @strConst.2, i32 0, i32 0
    call void @println(i8* %getelementptr.6)
    br label %for_step
if_true:
    %getelementptr.4 = getelementptr [3 x i8], [3 x i8]* @strConst, i32 0, i32 0
    call void @print(i8* %getelementptr.4)
    br label %if_exit
if_false:
    %getelementptr.5 = getelementptr [3 x i8], [3 x i8]* @strConst.1, i32 0, i32 0
    call void @print(i8* %getelementptr.5)
    br label %if_exit
if_exit:
    br label %for_step.1
}

define void @search(i32 %c) {
search.entry:
    %tmp.addr.1 = alloca i8
    %tmp.addr = alloca i8
    %r.addr = alloca i32
    %c.addr = alloca i32
    store i32 %c, i32* %c.addr
    %c.load = load i32, i32* %c.addr
    %N.load.2 = load i32, i32* @N
    %icmp.3 = icmp eq i32 %c.load, %N.load.2
    br i1 %icmp.3, label %if_true.1, label %if_false.1
search.exit:
    ret void
if_true.1:
    call void @printBoard()
    br label %if_exit.1
if_false.1:
    store i32 0, i32* %r.addr
    br label %for_cond.2
if_exit.1:
    br label %search.exit
for_cond.2:
    %r.load = load i32, i32* %r.addr
    %N.load.3 = load i32, i32* @N
    %icmp.4 = icmp slt i32 %r.load, %N.load.3
    br i1 %icmp.4, label %for_body.2, label %for_exit.2
for_body.2:
    %row.load = load i32*, i32** @row
    %r.load.2 = load i32, i32* %r.addr
    %getelementptr.8 = getelementptr i32, i32* %row.load, i32 0, i32 %r.load.2
    %null.load.1 = load i32, i32* %getelementptr.8
    %icmp.5 = icmp eq i32 %null.load.1, 0
    %zext = zext i1 %icmp.5 to i8
    store i8 %zext, i8* %tmp.addr
    br i1 %icmp.5, label %noShortCutBlock, label %logicExitBlock
for_step.2:
    %r.load.1 = load i32, i32* %r.addr
    %add.5 = add i32 %r.load.1, 1
    store i32 %add.5, i32* %r.addr
    br label %for_cond.2
for_exit.2:
    br label %if_exit.1
if_true.2:
    %d.load.2 = load i32**, i32*** @d
    %getelementptr.13 = getelementptr i32*, i32** %d.load.2, i32 0, i32 1
    %r.load.5 = load i32, i32* %r.addr
    %N.load.5 = load i32, i32* @N
    %add.8 = add i32 %r.load.5, %N.load.5
    %sub.2 = sub i32 %add.8, 1
    %c.load.3 = load i32, i32* %c.addr
    %sub.3 = sub i32 %sub.2, %c.load.3
    %null.load.6 = load i32*, i32** %getelementptr.13
    %getelementptr.14 = getelementptr i32, i32* %null.load.6, i32 0, i32 %sub.3
    store i32 1, i32* %getelementptr.14
    %d.load.3 = load i32**, i32*** @d
    %getelementptr.15 = getelementptr i32*, i32** %d.load.3, i32 0, i32 0
    %r.load.6 = load i32, i32* %r.addr
    %c.load.4 = load i32, i32* %c.addr
    %add.9 = add i32 %r.load.6, %c.load.4
    %null.load.7 = load i32*, i32** %getelementptr.15
    %getelementptr.16 = getelementptr i32, i32* %null.load.7, i32 0, i32 %add.9
    store i32 1, i32* %getelementptr.16
    %row.load.1 = load i32*, i32** @row
    %r.load.7 = load i32, i32* %r.addr
    %getelementptr.17 = getelementptr i32, i32* %row.load.1, i32 0, i32 %r.load.7
    store i32 1, i32* %getelementptr.17
    %col.load.1 = load i32*, i32** @col
    %c.load.5 = load i32, i32* %c.addr
    %getelementptr.18 = getelementptr i32, i32* %col.load.1, i32 0, i32 %c.load.5
    %r.load.8 = load i32, i32* %r.addr
    store i32 %r.load.8, i32* %getelementptr.18
    %c.load.6 = load i32, i32* %c.addr
    %add.10 = add i32 %c.load.6, 1
    call void @search(i32 %add.10)
    %row.load.2 = load i32*, i32** @row
    %r.load.9 = load i32, i32* %r.addr
    %getelementptr.19 = getelementptr i32, i32* %row.load.2, i32 0, i32 %r.load.9
    store i32 0, i32* %getelementptr.19
    %d.load.4 = load i32**, i32*** @d
    %getelementptr.20 = getelementptr i32*, i32** %d.load.4, i32 0, i32 0
    %r.load.10 = load i32, i32* %r.addr
    %c.load.7 = load i32, i32* %c.addr
    %add.11 = add i32 %r.load.10, %c.load.7
    %null.load.8 = load i32*, i32** %getelementptr.20
    %getelementptr.21 = getelementptr i32, i32* %null.load.8, i32 0, i32 %add.11
    store i32 0, i32* %getelementptr.21
    %d.load.5 = load i32**, i32*** @d
    %getelementptr.22 = getelementptr i32*, i32** %d.load.5, i32 0, i32 1
    %r.load.11 = load i32, i32* %r.addr
    %N.load.6 = load i32, i32* @N
    %add.12 = add i32 %r.load.11, %N.load.6
    %sub.4 = sub i32 %add.12, 1
    %c.load.8 = load i32, i32* %c.addr
    %sub.5 = sub i32 %sub.4, %c.load.8
    %null.load.9 = load i32*, i32** %getelementptr.22
    %getelementptr.23 = getelementptr i32, i32* %null.load.9, i32 0, i32 %sub.5
    store i32 0, i32* %getelementptr.23
    br label %if_exit.2
if_false.2:
    br label %if_exit.2
if_exit.2:
    br label %for_step.2
noShortCutBlock:
    %d.load = load i32**, i32*** @d
    %getelementptr.9 = getelementptr i32*, i32** %d.load, i32 0, i32 0
    %r.load.3 = load i32, i32* %r.addr
    %c.load.1 = load i32, i32* %c.addr
    %add.6 = add i32 %r.load.3, %c.load.1
    %null.load.2 = load i32*, i32** %getelementptr.9
    %getelementptr.10 = getelementptr i32, i32* %null.load.2, i32 0, i32 %add.6
    %null.load.3 = load i32, i32* %getelementptr.10
    %icmp.6 = icmp eq i32 %null.load.3, 0
    %and = and i1 %icmp.5, %icmp.6
    %zext.1 = zext i1 %and to i8
    store i8 %zext.1, i8* %tmp.addr
    br label %logicExitBlock
logicExitBlock:
    %and.load = load i8, i8* %tmp.addr
    %trunc = trunc i8 %and.load to i1
    %zext.2 = zext i1 %trunc to i8
    store i8 %zext.2, i8* %tmp.addr.1
    br i1 %trunc, label %noShortCutBlock.1, label %logicExitBlock.1
noShortCutBlock.1:
    %d.load.1 = load i32**, i32*** @d
    %getelementptr.11 = getelementptr i32*, i32** %d.load.1, i32 0, i32 1
    %r.load.4 = load i32, i32* %r.addr
    %N.load.4 = load i32, i32* @N
    %add.7 = add i32 %r.load.4, %N.load.4
    %sub = sub i32 %add.7, 1
    %c.load.2 = load i32, i32* %c.addr
    %sub.1 = sub i32 %sub, %c.load.2
    %null.load.4 = load i32*, i32** %getelementptr.11
    %getelementptr.12 = getelementptr i32, i32* %null.load.4, i32 0, i32 %sub.1
    %null.load.5 = load i32, i32* %getelementptr.12
    %icmp.7 = icmp eq i32 %null.load.5, 0
    %and.1 = and i1 %trunc, %icmp.7
    %zext.3 = zext i1 %and.1 to i8
    store i8 %zext.3, i8* %tmp.addr.1
    br label %logicExitBlock.1
logicExitBlock.1:
    %and.load.1 = load i8, i8* %tmp.addr.1
    %trunc.1 = trunc i8 %and.load.1 to i1
    br i1 %trunc.1, label %if_true.2, label %if_false.2
}


; MainFunc
define i32 @main() {
main.entry:
    %j.addr.1 = alloca i32
    %i.addr.1 = alloca i32
    %main.ret.addr = alloca i32
    call void @_init_func()
    store i32 0, i32* %main.ret.addr
    store i32 0, i32* %i.addr.1
    br label %for_cond.3
main.exit:
    %main.ret.load = load i32, i32* %main.ret.addr
    ret i32 %main.ret.load
for_cond.3:
    %i.load.3 = load i32, i32* %i.addr.1
    %icmp.8 = icmp slt i32 %i.load.3, 8
    br i1 %icmp.8, label %for_body.3, label %for_exit.3
for_body.3:
    %row.load.3 = load i32*, i32** @row
    %i.load.5 = load i32, i32* %i.addr.1
    %getelementptr.24 = getelementptr i32, i32* %row.load.3, i32 0, i32 %i.load.5
    store i32 0, i32* %getelementptr.24
    %col.load.2 = load i32*, i32** @col
    %i.load.6 = load i32, i32* %i.addr.1
    %getelementptr.25 = getelementptr i32, i32* %col.load.2, i32 0, i32 %i.load.6
    store i32 0, i32* %getelementptr.25
    br label %for_step.3
for_step.3:
    %i.load.4 = load i32, i32* %i.addr.1
    %add.13 = add i32 %i.load.4, 1
    store i32 %add.13, i32* %i.addr.1
    br label %for_cond.3
for_exit.3:
    store i32 0, i32* %i.addr.1
    br label %for_cond.4
for_cond.4:
    %i.load.7 = load i32, i32* %i.addr.1
    %icmp.9 = icmp slt i32 %i.load.7, 2
    br i1 %icmp.9, label %for_body.4, label %for_exit.4
for_body.4:
    %d.load.6 = load i32**, i32*** @d
    %i.load.9 = load i32, i32* %i.addr.1
    %getelementptr.26 = getelementptr i32*, i32** %d.load.6, i32 0, i32 %i.load.9
    %add.15 = add i32 8, 8
    %sub.6 = sub i32 %add.15, 1
    %mul.3 = mul i32 %sub.6, 4
    %add.16 = add i32 %mul.3, 4
    %_malloc.call.3 = call i8* @_malloc(i32 %add.16)
    %bitcast.6 = bitcast i8* %_malloc.call.3 to i32*
    store i32 %sub.6, i32* %bitcast.6
    %getelementptr.27 = getelementptr i32, i32* %bitcast.6, i32 0, i32 1
    %bitcast.7 = bitcast i32* %getelementptr.27 to i32*
    store i32* %bitcast.7, i32** %getelementptr.26
    store i32 0, i32* %j.addr.1
    br label %for_cond.5
for_step.4:
    %i.load.8 = load i32, i32* %i.addr.1
    %add.14 = add i32 %i.load.8, 1
    store i32 %add.14, i32* %i.addr.1
    br label %for_cond.4
for_exit.4:
    call void @search(i32 0)
    store i32 0, i32* %main.ret.addr
    br label %main.exit
for_cond.5:
    %j.load.3 = load i32, i32* %j.addr.1
    %add.17 = add i32 8, 8
    %sub.7 = sub i32 %add.17, 1
    %icmp.10 = icmp slt i32 %j.load.3, %sub.7
    br i1 %icmp.10, label %for_body.5, label %for_exit.5
for_body.5:
    %d.load.7 = load i32**, i32*** @d
    %i.load.10 = load i32, i32* %i.addr.1
    %getelementptr.28 = getelementptr i32*, i32** %d.load.7, i32 0, i32 %i.load.10
    %null.load.10 = load i32*, i32** %getelementptr.28
    %j.load.5 = load i32, i32* %j.addr.1
    %getelementptr.29 = getelementptr i32, i32* %null.load.10, i32 0, i32 %j.load.5
    store i32 0, i32* %getelementptr.29
    br label %for_step.5
for_step.5:
    %j.load.4 = load i32, i32* %j.addr.1
    %add.18 = add i32 %j.load.4, 1
    store i32 %add.18, i32* %j.addr.1
    br label %for_cond.5
for_exit.5:
    br label %for_step.4
}


