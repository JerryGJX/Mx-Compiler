
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
@MAX = global i32 zeroinitializer
@pick = global i32* zeroinitializer
@fst = global i32* zeroinitializer
@ans = global i32 zeroinitializer
@tot = global i32 zeroinitializer
@m = global i32 zeroinitializer
@n = global i32 zeroinitializer
@dis = global i32* zeroinitializer
@ed = global %class.Edge** zeroinitializer

; StructDef
%class.Edge = type {i32, i32, i32, i32}

; GlobalFunc
define void @_init_func() {
_init_func.entry:
    store i32 1000000, i32* @MAX
    store i32 0, i32* @tot
    store i32 0, i32* @ans
    store i32* null, i32** @pick
    store i32* null, i32** @fst
    store i32* null, i32** @dis
    store %class.Edge** null, %class.Edge*** @ed
    br label %_init_func.exit
_init_func.exit:
    ret void
}

define void @Edge.Edge(%class.Edge* %this) {
Edge.Edge.entry:
    br label %Edge.Edge.exit
Edge.Edge.exit:
    ret void
}

define void @addedge(i32 %x, i32 %y, i32 %w) {
addedge.entry:
    %w.addr.1 = alloca i32
    %y.addr.1 = alloca i32
    %x.addr.1 = alloca i32
    store i32 %x, i32* %x.addr.1
    store i32 %y, i32* %y.addr.1
    store i32 %w, i32* %w.addr.1
    %tot.load = load i32, i32* @tot
    %add = add i32 %tot.load, 1
    store i32 %add, i32* @tot
    %ed.load = load %class.Edge**, %class.Edge*** @ed
    %tot.load.1 = load i32, i32* @tot
    %getelementptr = getelementptr %class.Edge*, %class.Edge** %ed.load, i32 %tot.load.1
    %_malloc.call = call i8* @_malloc(i32 16)
    %bitcast = bitcast i8* %_malloc.call to %class.Edge*
    call void @Edge.Edge(%class.Edge* %bitcast)
    store %class.Edge* %bitcast, %class.Edge** %getelementptr
    %ed.load.1 = load %class.Edge**, %class.Edge*** @ed
    %tot.load.2 = load i32, i32* @tot
    %getelementptr.1 = getelementptr %class.Edge*, %class.Edge** %ed.load.1, i32 %tot.load.2
    %null.load = load %class.Edge*, %class.Edge** %getelementptr.1
    %x.1 = getelementptr %class.Edge, %class.Edge* %null.load, i32 0
    %x.load = load i32, i32* %x.addr.1
    store i32 %x.load, i32* %x.1
    %ed.load.2 = load %class.Edge**, %class.Edge*** @ed
    %tot.load.3 = load i32, i32* @tot
    %getelementptr.2 = getelementptr %class.Edge*, %class.Edge** %ed.load.2, i32 %tot.load.3
    %null.load.1 = load %class.Edge*, %class.Edge** %getelementptr.2
    %y.1 = getelementptr %class.Edge, %class.Edge* %null.load.1, i32 1
    %y.load = load i32, i32* %y.addr.1
    store i32 %y.load, i32* %y.1
    %ed.load.3 = load %class.Edge**, %class.Edge*** @ed
    %tot.load.4 = load i32, i32* @tot
    %getelementptr.3 = getelementptr %class.Edge*, %class.Edge** %ed.load.3, i32 %tot.load.4
    %null.load.2 = load %class.Edge*, %class.Edge** %getelementptr.3
    %w.1 = getelementptr %class.Edge, %class.Edge* %null.load.2, i32 3
    %w.load = load i32, i32* %w.addr.1
    store i32 %w.load, i32* %w.1
    %ed.load.4 = load %class.Edge**, %class.Edge*** @ed
    %tot.load.5 = load i32, i32* @tot
    %getelementptr.4 = getelementptr %class.Edge*, %class.Edge** %ed.load.4, i32 %tot.load.5
    %null.load.3 = load %class.Edge*, %class.Edge** %getelementptr.4
    %nxt = getelementptr %class.Edge, %class.Edge* %null.load.3, i32 2
    %fst.load = load i32*, i32** @fst
    %x.load.1 = load i32, i32* %x.addr.1
    %getelementptr.5 = getelementptr i32, i32* %fst.load, i32 %x.load.1
    %null.load.4 = load i32, i32* %getelementptr.5
    store i32 %null.load.4, i32* %nxt
    %fst.load.1 = load i32*, i32** @fst
    %x.load.2 = load i32, i32* %x.addr.1
    %getelementptr.6 = getelementptr i32, i32* %fst.load.1, i32 %x.load.2
    %tot.load.6 = load i32, i32* @tot
    store i32 %tot.load.6, i32* %getelementptr.6
    br label %addedge.exit
addedge.exit:
    ret void
}


; MainFunc
define i32 @main() {
main.entry:
    %tmp.addr.1 = alloca i8
    %tmp.addr = alloca i8
    %y.addr.3 = alloca i32
    %j.addr = alloca i32
    %w.addr.2 = alloca i32
    %y.addr.2 = alloca i32
    %x.addr.2 = alloca i32
    %i.addr = alloca i32
    %main.ret.addr = alloca i32
    call void @_init_func()
    store i32 0, i32* %main.ret.addr
    %getInt.call = call i32 @getInt()
    store i32 %getInt.call, i32* @n
    %getInt.call.1 = call i32 @getInt()
    store i32 %getInt.call.1, i32* @m
    %n.load = load i32, i32* @n
    %add.1 = add i32 %n.load, 1
    %mul = mul i32 %add.1, 4
    %add.2 = add i32 %mul, 4
    %_malloc.call.1 = call i8* @_malloc(i32 %add.2)
    %bitcast.1 = bitcast i8* %_malloc.call.1 to i32*
    store i32 %add.1, i32* %bitcast.1
    %getelementptr.7 = getelementptr i32, i32* %bitcast.1, i32 1
    %bitcast.2 = bitcast i32* %getelementptr.7 to i32*
    store i32* %bitcast.2, i32** @pick
    %n.load.1 = load i32, i32* @n
    %add.3 = add i32 %n.load.1, 1
    %mul.1 = mul i32 %add.3, 4
    %add.4 = add i32 %mul.1, 4
    %_malloc.call.2 = call i8* @_malloc(i32 %add.4)
    %bitcast.3 = bitcast i8* %_malloc.call.2 to i32*
    store i32 %add.3, i32* %bitcast.3
    %getelementptr.8 = getelementptr i32, i32* %bitcast.3, i32 1
    %bitcast.4 = bitcast i32* %getelementptr.8 to i32*
    store i32* %bitcast.4, i32** @fst
    %n.load.2 = load i32, i32* @n
    %add.5 = add i32 %n.load.2, 1
    %mul.2 = mul i32 %add.5, 4
    %add.6 = add i32 %mul.2, 4
    %_malloc.call.3 = call i8* @_malloc(i32 %add.6)
    %bitcast.5 = bitcast i8* %_malloc.call.3 to i32*
    store i32 %add.5, i32* %bitcast.5
    %getelementptr.9 = getelementptr i32, i32* %bitcast.5, i32 1
    %bitcast.6 = bitcast i32* %getelementptr.9 to i32*
    store i32* %bitcast.6, i32** @dis
    %m.load = load i32, i32* @m
    %mul.3 = mul i32 2, %m.load
    %add.7 = add i32 %mul.3, 1
    %mul.4 = mul i32 %add.7, 4
    %add.8 = add i32 %mul.4, 4
    %_malloc.call.4 = call i8* @_malloc(i32 %add.8)
    %bitcast.7 = bitcast i8* %_malloc.call.4 to i32*
    store i32 %add.7, i32* %bitcast.7
    %getelementptr.10 = getelementptr i32, i32* %bitcast.7, i32 1
    %bitcast.8 = bitcast i32* %getelementptr.10 to %class.Edge**
    store %class.Edge** %bitcast.8, %class.Edge*** @ed
    store i32 0, i32* %i.addr
    br label %for_cond
main.exit:
    %main.ret.load = load i32, i32* %main.ret.addr
    ret i32 %main.ret.load
for_cond:
    %i.load = load i32, i32* %i.addr
    %n.load.3 = load i32, i32* @n
    %icmp = icmp sle i32 %i.load, %n.load.3
    br i1 %icmp, label %for_body, label %for_exit
for_body:
    %pick.load = load i32*, i32** @pick
    %i.load.2 = load i32, i32* %i.addr
    %getelementptr.11 = getelementptr i32, i32* %pick.load, i32 %i.load.2
    store i32 0, i32* %getelementptr.11
    %fst.load.2 = load i32*, i32** @fst
    %i.load.3 = load i32, i32* %i.addr
    %getelementptr.12 = getelementptr i32, i32* %fst.load.2, i32 %i.load.3
    store i32 0, i32* %getelementptr.12
    %dis.load = load i32*, i32** @dis
    %i.load.4 = load i32, i32* %i.addr
    %getelementptr.13 = getelementptr i32, i32* %dis.load, i32 %i.load.4
    %MAX.load = load i32, i32* @MAX
    store i32 %MAX.load, i32* %getelementptr.13
    br label %for_step
for_step:
    %i.load.1 = load i32, i32* %i.addr
    %add.9 = add i32 %i.load.1, 1
    store i32 %add.9, i32* %i.addr
    br label %for_cond
for_exit:
    store i32 1, i32* %i.addr
    br label %for_cond.1
for_cond.1:
    %i.load.5 = load i32, i32* %i.addr
    %m.load.1 = load i32, i32* @m
    %icmp.1 = icmp sle i32 %i.load.5, %m.load.1
    br i1 %icmp.1, label %for_body.1, label %for_exit.1
for_body.1:
    %getInt.call.2 = call i32 @getInt()
    store i32 %getInt.call.2, i32* %x.addr.2
    %getInt.call.3 = call i32 @getInt()
    store i32 %getInt.call.3, i32* %y.addr.2
    %getInt.call.4 = call i32 @getInt()
    store i32 %getInt.call.4, i32* %w.addr.2
    %x.load.3 = load i32, i32* %x.addr.2
    %y.load.1 = load i32, i32* %y.addr.2
    %w.load.1 = load i32, i32* %w.addr.2
    call void @addedge(i32 %x.load.3, i32 %y.load.1, i32 %w.load.1)
    %y.load.2 = load i32, i32* %y.addr.2
    %x.load.4 = load i32, i32* %x.addr.2
    %w.load.2 = load i32, i32* %w.addr.2
    call void @addedge(i32 %y.load.2, i32 %x.load.4, i32 %w.load.2)
    br label %for_step.1
for_step.1:
    %i.load.6 = load i32, i32* %i.addr
    %add.10 = add i32 %i.load.6, 1
    store i32 %add.10, i32* %i.addr
    br label %for_cond.1
for_exit.1:
    store i32 1, i32* %x.addr.2
    store i32 1, i32* %i.addr
    br label %for_cond.2
for_cond.2:
    %i.load.7 = load i32, i32* %i.addr
    %n.load.4 = load i32, i32* @n
    %sub = sub i32 %n.load.4, 1
    %icmp.2 = icmp sle i32 %i.load.7, %sub
    br i1 %icmp.2, label %for_body.2, label %for_exit.2
for_body.2:
    %pick.load.1 = load i32*, i32** @pick
    %x.load.5 = load i32, i32* %x.addr.2
    %getelementptr.14 = getelementptr i32, i32* %pick.load.1, i32 %x.load.5
    store i32 1, i32* %getelementptr.14
    %fst.load.3 = load i32*, i32** @fst
    %x.load.6 = load i32, i32* %x.addr.2
    %getelementptr.15 = getelementptr i32, i32* %fst.load.3, i32 %x.load.6
    %null.load.5 = load i32, i32* %getelementptr.15
    store i32 %null.load.5, i32* %j.addr
    br label %for_cond.3
for_step.2:
    %i.load.8 = load i32, i32* %i.addr
    %add.11 = add i32 %i.load.8, 1
    store i32 %add.11, i32* %i.addr
    br label %for_cond.2
for_exit.2:
    %ans.load.1 = load i32, i32* @ans
    call void @printInt(i32 %ans.load.1)
    store i32 0, i32* %main.ret.addr
    br label %main.exit
for_cond.3:
    %j.load = load i32, i32* %j.addr
    %icmp.3 = icmp ne i32 %j.load, 0
    br i1 %icmp.3, label %for_body.3, label %for_exit.3
for_body.3:
    %ed.load.6 = load %class.Edge**, %class.Edge*** @ed
    %j.load.2 = load i32, i32* %j.addr
    %getelementptr.17 = getelementptr %class.Edge*, %class.Edge** %ed.load.6, i32 %j.load.2
    %null.load.8 = load %class.Edge*, %class.Edge** %getelementptr.17
    %y.2 = getelementptr %class.Edge, %class.Edge* %null.load.8, i32 1
    %null.load.9 = load i32, i32* %y.2
    store i32 %null.load.9, i32* %y.addr.3
    %pick.load.2 = load i32*, i32** @pick
    %y.load.3 = load i32, i32* %y.addr.3
    %getelementptr.18 = getelementptr i32, i32* %pick.load.2, i32 %y.load.3
    %null.load.10 = load i32, i32* %getelementptr.18
    %icmp.4 = icmp eq i32 %null.load.10, 0
    %zext = zext i1 %icmp.4 to i8
    store i8 %zext, i8* %tmp.addr
    br i1 %icmp.4, label %noShortCutBlock, label %logicExitBlock
for_step.3:
    %ed.load.5 = load %class.Edge**, %class.Edge*** @ed
    %j.load.1 = load i32, i32* %j.addr
    %getelementptr.16 = getelementptr %class.Edge*, %class.Edge** %ed.load.5, i32 %j.load.1
    %null.load.6 = load %class.Edge*, %class.Edge** %getelementptr.16
    %nxt.1 = getelementptr %class.Edge, %class.Edge* %null.load.6, i32 2
    %null.load.7 = load i32, i32* %nxt.1
    store i32 %null.load.7, i32* %j.addr
    br label %for_cond.3
for_exit.3:
    store i32 0, i32* %x.addr.2
    store i32 1, i32* %j.addr
    br label %for_cond.4
if_true:
    %dis.load.2 = load i32*, i32** @dis
    %y.load.5 = load i32, i32* %y.addr.3
    %getelementptr.21 = getelementptr i32, i32* %dis.load.2, i32 %y.load.5
    %ed.load.8 = load %class.Edge**, %class.Edge*** @ed
    %j.load.4 = load i32, i32* %j.addr
    %getelementptr.22 = getelementptr %class.Edge*, %class.Edge** %ed.load.8, i32 %j.load.4
    %null.load.14 = load %class.Edge*, %class.Edge** %getelementptr.22
    %w.3 = getelementptr %class.Edge, %class.Edge* %null.load.14, i32 3
    %null.load.15 = load i32, i32* %w.3
    store i32 %null.load.15, i32* %getelementptr.21
    br label %if_exit
if_false:
    br label %if_exit
if_exit:
    br label %for_step.3
noShortCutBlock:
    %ed.load.7 = load %class.Edge**, %class.Edge*** @ed
    %j.load.3 = load i32, i32* %j.addr
    %getelementptr.19 = getelementptr %class.Edge*, %class.Edge** %ed.load.7, i32 %j.load.3
    %null.load.11 = load %class.Edge*, %class.Edge** %getelementptr.19
    %w.2 = getelementptr %class.Edge, %class.Edge* %null.load.11, i32 3
    %null.load.12 = load i32, i32* %w.2
    %dis.load.1 = load i32*, i32** @dis
    %y.load.4 = load i32, i32* %y.addr.3
    %getelementptr.20 = getelementptr i32, i32* %dis.load.1, i32 %y.load.4
    %null.load.13 = load i32, i32* %getelementptr.20
    %icmp.5 = icmp slt i32 %null.load.12, %null.load.13
    %and = and i1 %icmp.4, %icmp.5
    %zext.1 = zext i1 %and to i8
    store i8 %zext.1, i8* %tmp.addr
    br label %logicExitBlock
logicExitBlock:
    %and.load = load i8, i8* %tmp.addr
    %trunc = trunc i8 %and.load to i1
    br i1 %trunc, label %if_true, label %if_false
for_cond.4:
    %j.load.5 = load i32, i32* %j.addr
    %n.load.5 = load i32, i32* @n
    %icmp.6 = icmp sle i32 %j.load.5, %n.load.5
    br i1 %icmp.6, label %for_body.4, label %for_exit.4
for_body.4:
    %pick.load.3 = load i32*, i32** @pick
    %j.load.7 = load i32, i32* %j.addr
    %getelementptr.23 = getelementptr i32, i32* %pick.load.3, i32 %j.load.7
    %null.load.16 = load i32, i32* %getelementptr.23
    %icmp.7 = icmp eq i32 %null.load.16, 0
    %zext.2 = zext i1 %icmp.7 to i8
    store i8 %zext.2, i8* %tmp.addr.1
    br i1 %icmp.7, label %noShortCutBlock.1, label %logicExitBlock.1
for_step.4:
    %j.load.6 = load i32, i32* %j.addr
    %add.12 = add i32 %j.load.6, 1
    store i32 %add.12, i32* %j.addr
    br label %for_cond.4
for_exit.4:
    %x.load.8 = load i32, i32* %x.addr.2
    %icmp.9 = icmp eq i32 %x.load.8, 0
    br i1 %icmp.9, label %if_true.2, label %if_false.2
if_true.1:
    %j.load.9 = load i32, i32* %j.addr
    store i32 %j.load.9, i32* %x.addr.2
    br label %if_exit.1
if_false.1:
    br label %if_exit.1
if_exit.1:
    br label %for_step.4
noShortCutBlock.1:
    %dis.load.3 = load i32*, i32** @dis
    %j.load.8 = load i32, i32* %j.addr
    %getelementptr.24 = getelementptr i32, i32* %dis.load.3, i32 %j.load.8
    %null.load.17 = load i32, i32* %getelementptr.24
    %dis.load.4 = load i32*, i32** @dis
    %x.load.7 = load i32, i32* %x.addr.2
    %getelementptr.25 = getelementptr i32, i32* %dis.load.4, i32 %x.load.7
    %null.load.18 = load i32, i32* %getelementptr.25
    %icmp.8 = icmp slt i32 %null.load.17, %null.load.18
    %and.1 = and i1 %icmp.7, %icmp.8
    %zext.3 = zext i1 %and.1 to i8
    store i8 %zext.3, i8* %tmp.addr.1
    br label %logicExitBlock.1
logicExitBlock.1:
    %and.load.1 = load i8, i8* %tmp.addr.1
    %trunc.1 = trunc i8 %and.load.1 to i1
    br i1 %trunc.1, label %if_true.1, label %if_false.1
if_true.2:
    %sub.1 = sub i32 0, 1
    call void @printInt(i32 %sub.1)
    store i32 0, i32* %main.ret.addr
    br label %main.exit
if_false.2:
    br label %if_exit.2
if_exit.2:
    %ans.load = load i32, i32* @ans
    %dis.load.5 = load i32*, i32** @dis
    %x.load.9 = load i32, i32* %x.addr.2
    %getelementptr.26 = getelementptr i32, i32* %dis.load.5, i32 %x.load.9
    %null.load.19 = load i32, i32* %getelementptr.26
    %add.13 = add i32 %ans.load, %null.load.19
    store i32 %add.13, i32* @ans
    br label %for_step.2
}


