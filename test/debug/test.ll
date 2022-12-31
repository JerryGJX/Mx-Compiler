
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
@e = global %class.Edge** zeroinitializer
@ans = global i32 zeroinitializer
@rk = global i32* zeroinitializer
@fa = global i32* zeroinitializer
@m = global i32 zeroinitializer
@n = global i32 zeroinitializer

; StructDef
%class.Edge = type {i32, i32, i32}

; GlobalFunc
define void @_init_func() {
_init_func.entry:
    store i32 0, i32* @ans
    store i32* null, i32** @fa
    store i32* null, i32** @rk
    store %class.Edge** null, %class.Edge*** @e
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

define i32 @find(i32 %x) {
find.entry:
    %find.ret.addr = alloca i32
    %x.addr.2 = alloca i32
    store i32 %x, i32* %x.addr.2
    %x.load.3 = load i32, i32* %x.addr.2
    %fa.load.1 = load i32*, i32** @fa
    %x.load.4 = load i32, i32* %x.addr.2
    %getelementptr.12 = getelementptr i32, i32* %fa.load.1, i32 %x.load.4
    %null.load.9 = load i32, i32* %getelementptr.12
    %icmp.9 = icmp eq i32 %x.load.3, %null.load.9
    br i1 %icmp.9, label %if_true.3, label %if_false.3
find.exit:
    %find.ret.load = load i32, i32* %find.ret.addr
    ret i32 %find.ret.load
if_true.3:
    %x.load.5 = load i32, i32* %x.addr.2
    store i32 %x.load.5, i32* %find.ret.addr
    br label %find.exit
if_false.3:
    br label %if_exit.3
if_exit.3:
    %fa.load.2 = load i32*, i32** @fa
    %x.load.6 = load i32, i32* %x.addr.2
    %getelementptr.13 = getelementptr i32, i32* %fa.load.2, i32 %x.load.6
    %null.load.10 = load i32, i32* %getelementptr.13
    %find.call = call i32 @find(i32 %null.load.10)
    store i32 %find.call, i32* %x.addr.2
    %fa.load.3 = load i32*, i32** @fa
    %x.load.7 = load i32, i32* %x.addr.2
    %getelementptr.14 = getelementptr i32, i32* %fa.load.3, i32 %x.load.7
    %null.load.11 = load i32, i32* %getelementptr.14
    store i32 %null.load.11, i32* %find.ret.addr
    br label %find.exit
}

define void @qsort(%class.Edge** %e.1, i32 %l, i32 %r) {
qsort.entry:
    %tmp.addr.1 = alloca i8
    %tmp.addr = alloca i8
    %x.addr.1 = alloca %class.Edge*
    %j.addr = alloca i32
    %i.addr = alloca i32
    %r.addr = alloca i32
    %l.addr = alloca i32
    %e.addr = alloca %class.Edge**
    store %class.Edge** %e.1, %class.Edge*** %e.addr
    store i32 %l, i32* %l.addr
    store i32 %r, i32* %r.addr
    %l.load = load i32, i32* %l.addr
    %r.load = load i32, i32* %r.addr
    %icmp = icmp slt i32 %l.load, %r.load
    br i1 %icmp, label %if_true, label %if_false
qsort.exit:
    ret void
if_true:
    %l.load.1 = load i32, i32* %l.addr
    store i32 %l.load.1, i32* %i.addr
    %r.load.1 = load i32, i32* %r.addr
    store i32 %r.load.1, i32* %j.addr
    %e.load = load %class.Edge**, %class.Edge*** %e.addr
    %l.load.2 = load i32, i32* %l.addr
    %getelementptr = getelementptr %class.Edge*, %class.Edge** %e.load, i32 %l.load.2
    %null.load = load %class.Edge*, %class.Edge** %getelementptr
    store %class.Edge* %null.load, %class.Edge** %x.addr.1
    br label %while_cond
if_false:
    br label %if_exit
if_exit:
    br label %qsort.exit
while_cond:
    %i.load = load i32, i32* %i.addr
    %j.load = load i32, i32* %j.addr
    %icmp.1 = icmp slt i32 %i.load, %j.load
    br i1 %icmp.1, label %while_body, label %while_exit
while_body:
    br label %while_cond.1
while_exit:
    %e.load.7 = load %class.Edge**, %class.Edge*** %e.addr
    %i.load.10 = load i32, i32* %i.addr
    %getelementptr.7 = getelementptr %class.Edge*, %class.Edge** %e.load.7, i32 %i.load.10
    %x.load.2 = load %class.Edge*, %class.Edge** %x.addr.1
    store %class.Edge* %x.load.2, %class.Edge** %getelementptr.7
    %e.load.8 = load %class.Edge**, %class.Edge*** %e.addr
    %l.load.3 = load i32, i32* %l.addr
    %i.load.11 = load i32, i32* %i.addr
    %sub.2 = sub i32 %i.load.11, 1
    call void @qsort(%class.Edge** %e.load.8, i32 %l.load.3, i32 %sub.2)
    %e.load.9 = load %class.Edge**, %class.Edge*** %e.addr
    %i.load.12 = load i32, i32* %i.addr
    %add.2 = add i32 %i.load.12, 1
    %r.load.2 = load i32, i32* %r.addr
    call void @qsort(%class.Edge** %e.load.9, i32 %add.2, i32 %r.load.2)
    br label %if_exit
while_cond.1:
    %i.load.1 = load i32, i32* %i.addr
    %j.load.1 = load i32, i32* %j.addr
    %icmp.2 = icmp slt i32 %i.load.1, %j.load.1
    %zext = zext i1 %icmp.2 to i8
    store i8 %zext, i8* %tmp.addr
    br i1 %icmp.2, label %noShortCutBlock, label %logicExitBlock
while_body.1:
    %j.load.3 = load i32, i32* %j.addr
    %sub = sub i32 %j.load.3, 1
    store i32 %sub, i32* %j.addr
    br label %while_cond.1
while_exit.1:
    %i.load.2 = load i32, i32* %i.addr
    %j.load.4 = load i32, i32* %j.addr
    %icmp.4 = icmp slt i32 %i.load.2, %j.load.4
    br i1 %icmp.4, label %if_true.1, label %if_false.1
noShortCutBlock:
    %e.load.1 = load %class.Edge**, %class.Edge*** %e.addr
    %j.load.2 = load i32, i32* %j.addr
    %getelementptr.1 = getelementptr %class.Edge*, %class.Edge** %e.load.1, i32 %j.load.2
    %null.load.1 = load %class.Edge*, %class.Edge** %getelementptr.1
    %w = getelementptr %class.Edge, %class.Edge* %null.load.1, i32 0, i32 2
    %null.load.2 = load i32, i32* %w
    %x.load = load %class.Edge*, %class.Edge** %x.addr.1
    %w.1 = getelementptr %class.Edge, %class.Edge* %x.load, i32 0, i32 2
    %null.load.3 = load i32, i32* %w.1
    %icmp.3 = icmp sge i32 %null.load.2, %null.load.3
    %and = and i1 %icmp.2, %icmp.3
    %zext.1 = zext i1 %and to i8
    store i8 %zext.1, i8* %tmp.addr
    br label %logicExitBlock
logicExitBlock:
    %and.load = load i8, i8* %tmp.addr
    %trunc = trunc i8 %and.load to i1
    br i1 %trunc, label %while_body.1, label %while_exit.1
if_true.1:
    %e.load.2 = load %class.Edge**, %class.Edge*** %e.addr
    %i.load.3 = load i32, i32* %i.addr
    %getelementptr.2 = getelementptr %class.Edge*, %class.Edge** %e.load.2, i32 %i.load.3
    %e.load.3 = load %class.Edge**, %class.Edge*** %e.addr
    %j.load.5 = load i32, i32* %j.addr
    %getelementptr.3 = getelementptr %class.Edge*, %class.Edge** %e.load.3, i32 %j.load.5
    %null.load.4 = load %class.Edge*, %class.Edge** %getelementptr.3
    store %class.Edge* %null.load.4, %class.Edge** %getelementptr.2
    %i.load.4 = load i32, i32* %i.addr
    %add = add i32 %i.load.4, 1
    store i32 %add, i32* %i.addr
    br label %if_exit.1
if_false.1:
    br label %if_exit.1
if_exit.1:
    br label %while_cond.2
while_cond.2:
    %i.load.5 = load i32, i32* %i.addr
    %j.load.6 = load i32, i32* %j.addr
    %icmp.5 = icmp slt i32 %i.load.5, %j.load.6
    %zext.2 = zext i1 %icmp.5 to i8
    store i8 %zext.2, i8* %tmp.addr.1
    br i1 %icmp.5, label %noShortCutBlock.1, label %logicExitBlock.1
while_body.2:
    %i.load.7 = load i32, i32* %i.addr
    %add.1 = add i32 %i.load.7, 1
    store i32 %add.1, i32* %i.addr
    br label %while_cond.2
while_exit.2:
    %i.load.8 = load i32, i32* %i.addr
    %j.load.7 = load i32, i32* %j.addr
    %icmp.7 = icmp slt i32 %i.load.8, %j.load.7
    br i1 %icmp.7, label %if_true.2, label %if_false.2
noShortCutBlock.1:
    %e.load.4 = load %class.Edge**, %class.Edge*** %e.addr
    %i.load.6 = load i32, i32* %i.addr
    %getelementptr.4 = getelementptr %class.Edge*, %class.Edge** %e.load.4, i32 %i.load.6
    %null.load.5 = load %class.Edge*, %class.Edge** %getelementptr.4
    %w.2 = getelementptr %class.Edge, %class.Edge* %null.load.5, i32 0, i32 2
    %null.load.6 = load i32, i32* %w.2
    %x.load.1 = load %class.Edge*, %class.Edge** %x.addr.1
    %w.3 = getelementptr %class.Edge, %class.Edge* %x.load.1, i32 0, i32 2
    %null.load.7 = load i32, i32* %w.3
    %icmp.6 = icmp slt i32 %null.load.6, %null.load.7
    %and.1 = and i1 %icmp.5, %icmp.6
    %zext.3 = zext i1 %and.1 to i8
    store i8 %zext.3, i8* %tmp.addr.1
    br label %logicExitBlock.1
logicExitBlock.1:
    %and.load.1 = load i8, i8* %tmp.addr.1
    %trunc.1 = trunc i8 %and.load.1 to i1
    br i1 %trunc.1, label %while_body.2, label %while_exit.2
if_true.2:
    %e.load.5 = load %class.Edge**, %class.Edge*** %e.addr
    %j.load.8 = load i32, i32* %j.addr
    %getelementptr.5 = getelementptr %class.Edge*, %class.Edge** %e.load.5, i32 %j.load.8
    %e.load.6 = load %class.Edge**, %class.Edge*** %e.addr
    %i.load.9 = load i32, i32* %i.addr
    %getelementptr.6 = getelementptr %class.Edge*, %class.Edge** %e.load.6, i32 %i.load.9
    %null.load.8 = load %class.Edge*, %class.Edge** %getelementptr.6
    store %class.Edge* %null.load.8, %class.Edge** %getelementptr.5
    %j.load.9 = load i32, i32* %j.addr
    %sub.1 = sub i32 %j.load.9, 1
    store i32 %sub.1, i32* %j.addr
    br label %if_exit.2
if_false.2:
    br label %if_exit.2
if_exit.2:
    br label %while_cond
}

define void @init() {
init.entry:
    %i.addr.1 = alloca i32
    %n.load = load i32, i32* @n
    %add.3 = add i32 %n.load, 1
    %mul = mul i32 %add.3, 4
    %add.4 = add i32 %mul, 4
    %_malloc.call = call i8* @_malloc(i32 %add.4)
    %bitcast = bitcast i8* %_malloc.call to i32*
    store i32 %add.3, i32* %bitcast
    %getelementptr.8 = getelementptr i32, i32* %bitcast, i32 1
    %bitcast.1 = bitcast i32* %getelementptr.8 to i32*
    store i32* %bitcast.1, i32** @fa
    %n.load.1 = load i32, i32* @n
    %add.5 = add i32 %n.load.1, 1
    %mul.1 = mul i32 %add.5, 4
    %add.6 = add i32 %mul.1, 4
    %_malloc.call.1 = call i8* @_malloc(i32 %add.6)
    %bitcast.2 = bitcast i8* %_malloc.call.1 to i32*
    store i32 %add.5, i32* %bitcast.2
    %getelementptr.9 = getelementptr i32, i32* %bitcast.2, i32 1
    %bitcast.3 = bitcast i32* %getelementptr.9 to i32*
    store i32* %bitcast.3, i32** @rk
    store i32 1, i32* %i.addr.1
    br label %for_cond
init.exit:
    ret void
for_cond:
    %i.load.13 = load i32, i32* %i.addr.1
    %n.load.2 = load i32, i32* @n
    %icmp.8 = icmp sle i32 %i.load.13, %n.load.2
    br i1 %icmp.8, label %for_body, label %for_exit
for_body:
    %fa.load = load i32*, i32** @fa
    %i.load.15 = load i32, i32* %i.addr.1
    %getelementptr.10 = getelementptr i32, i32* %fa.load, i32 %i.load.15
    %i.load.16 = load i32, i32* %i.addr.1
    store i32 %i.load.16, i32* %getelementptr.10
    %rk.load = load i32*, i32** @rk
    %i.load.17 = load i32, i32* %i.addr.1
    %getelementptr.11 = getelementptr i32, i32* %rk.load, i32 %i.load.17
    store i32 1, i32* %getelementptr.11
    br label %for_step
for_step:
    %i.load.14 = load i32, i32* %i.addr.1
    %add.7 = add i32 %i.load.14, 1
    store i32 %add.7, i32* %i.addr.1
    br label %for_cond
for_exit:
    br label %init.exit
}

define i1 @union(i32 %x.1, i32 %y) {
union.entry:
    %union.ret.addr = alloca i8
    %y.addr.1 = alloca i32
    %x.addr.3 = alloca i32
    store i32 %x.1, i32* %x.addr.3
    store i32 %y, i32* %y.addr.1
    %x.load.8 = load i32, i32* %x.addr.3
    %find.call.1 = call i32 @find(i32 %x.load.8)
    store i32 %find.call.1, i32* %x.addr.3
    %y.load = load i32, i32* %y.addr.1
    %find.call.2 = call i32 @find(i32 %y.load)
    store i32 %find.call.2, i32* %y.addr.1
    %x.load.9 = load i32, i32* %x.addr.3
    %y.load.1 = load i32, i32* %y.addr.1
    %icmp.10 = icmp eq i32 %x.load.9, %y.load.1
    br i1 %icmp.10, label %if_true.4, label %if_false.4
union.exit:
    %union.ret.load = load i8, i8* %union.ret.addr
    %trunc.2 = trunc i8 %union.ret.load to i1
    ret i1 %trunc.2
if_true.4:
    %zext.4 = zext i1 false to i8
    store i8 %zext.4, i8* %union.ret.addr
    br label %union.exit
if_false.4:
    br label %if_exit.4
if_exit.4:
    %rk.load.1 = load i32*, i32** @rk
    %x.load.10 = load i32, i32* %x.addr.3
    %getelementptr.15 = getelementptr i32, i32* %rk.load.1, i32 %x.load.10
    %null.load.12 = load i32, i32* %getelementptr.15
    %rk.load.2 = load i32*, i32** @rk
    %y.load.2 = load i32, i32* %y.addr.1
    %getelementptr.16 = getelementptr i32, i32* %rk.load.2, i32 %y.load.2
    %null.load.13 = load i32, i32* %getelementptr.16
    %icmp.11 = icmp sgt i32 %null.load.12, %null.load.13
    br i1 %icmp.11, label %if_true.5, label %if_false.5
if_true.5:
    %fa.load.4 = load i32*, i32** @fa
    %y.load.3 = load i32, i32* %y.addr.1
    %getelementptr.17 = getelementptr i32, i32* %fa.load.4, i32 %y.load.3
    %x.load.11 = load i32, i32* %x.addr.3
    store i32 %x.load.11, i32* %getelementptr.17
    %rk.load.3 = load i32*, i32** @rk
    %x.load.12 = load i32, i32* %x.addr.3
    %getelementptr.18 = getelementptr i32, i32* %rk.load.3, i32 %x.load.12
    %rk.load.4 = load i32*, i32** @rk
    %x.load.13 = load i32, i32* %x.addr.3
    %getelementptr.19 = getelementptr i32, i32* %rk.load.4, i32 %x.load.13
    %null.load.14 = load i32, i32* %getelementptr.19
    %rk.load.5 = load i32*, i32** @rk
    %y.load.4 = load i32, i32* %y.addr.1
    %getelementptr.20 = getelementptr i32, i32* %rk.load.5, i32 %y.load.4
    %null.load.15 = load i32, i32* %getelementptr.20
    %add.8 = add i32 %null.load.14, %null.load.15
    store i32 %add.8, i32* %getelementptr.18
    br label %if_exit.5
if_false.5:
    %fa.load.5 = load i32*, i32** @fa
    %x.load.14 = load i32, i32* %x.addr.3
    %getelementptr.21 = getelementptr i32, i32* %fa.load.5, i32 %x.load.14
    %y.load.5 = load i32, i32* %y.addr.1
    store i32 %y.load.5, i32* %getelementptr.21
    %rk.load.6 = load i32*, i32** @rk
    %y.load.6 = load i32, i32* %y.addr.1
    %getelementptr.22 = getelementptr i32, i32* %rk.load.6, i32 %y.load.6
    %rk.load.7 = load i32*, i32** @rk
    %y.load.7 = load i32, i32* %y.addr.1
    %getelementptr.23 = getelementptr i32, i32* %rk.load.7, i32 %y.load.7
    %null.load.16 = load i32, i32* %getelementptr.23
    %rk.load.8 = load i32*, i32** @rk
    %x.load.15 = load i32, i32* %x.addr.3
    %getelementptr.24 = getelementptr i32, i32* %rk.load.8, i32 %x.load.15
    %null.load.17 = load i32, i32* %getelementptr.24
    %add.9 = add i32 %null.load.16, %null.load.17
    store i32 %add.9, i32* %getelementptr.22
    br label %if_exit.5
if_exit.5:
    %zext.5 = zext i1 true to i8
    store i8 %zext.5, i8* %union.ret.addr
    br label %union.exit
}


; MainFunc
define i32 @main() {
main.entry:
    %ed.addr.1 = alloca %class.Edge*
    %j.addr.1 = alloca i32
    %ed.addr = alloca %class.Edge*
    %i.addr.2 = alloca i32
    %main.ret.addr = alloca i32
    call void @_init_func()
    store i32 0, i32* %main.ret.addr
    %getInt.call = call i32 @getInt()
    store i32 %getInt.call, i32* @n
    %getInt.call.1 = call i32 @getInt()
    store i32 %getInt.call.1, i32* @m
    %m.load = load i32, i32* @m
    %mul.2 = mul i32 %m.load, 4
    %add.10 = add i32 %mul.2, 4
    %_malloc.call.2 = call i8* @_malloc(i32 %add.10)
    %bitcast.4 = bitcast i8* %_malloc.call.2 to i32*
    store i32 %m.load, i32* %bitcast.4
    %getelementptr.25 = getelementptr i32, i32* %bitcast.4, i32 1
    %bitcast.5 = bitcast i32* %getelementptr.25 to %class.Edge**
    store %class.Edge** %bitcast.5, %class.Edge*** @e
    store i32 0, i32* %i.addr.2
    br label %for_cond.1
main.exit:
    %main.ret.load = load i32, i32* %main.ret.addr
    ret i32 %main.ret.load
for_cond.1:
    %i.load.18 = load i32, i32* %i.addr.2
    %m.load.1 = load i32, i32* @m
    %icmp.12 = icmp slt i32 %i.load.18, %m.load.1
    br i1 %icmp.12, label %for_body.1, label %for_exit.1
for_body.1:
    %_malloc.call.3 = call i8* @_malloc(i32 12)
    %bitcast.6 = bitcast i8* %_malloc.call.3 to %class.Edge*
    call void @Edge.Edge(%class.Edge* %bitcast.6)
    store %class.Edge* %bitcast.6, %class.Edge** %ed.addr
    %ed.load = load %class.Edge*, %class.Edge** %ed.addr
    %x.2 = getelementptr %class.Edge, %class.Edge* %ed.load, i32 0, i32 0
    %getInt.call.2 = call i32 @getInt()
    store i32 %getInt.call.2, i32* %x.2
    %ed.load.1 = load %class.Edge*, %class.Edge** %ed.addr
    %y.1 = getelementptr %class.Edge, %class.Edge* %ed.load.1, i32 0, i32 1
    %getInt.call.3 = call i32 @getInt()
    store i32 %getInt.call.3, i32* %y.1
    %ed.load.2 = load %class.Edge*, %class.Edge** %ed.addr
    %w.4 = getelementptr %class.Edge, %class.Edge* %ed.load.2, i32 0, i32 2
    %getInt.call.4 = call i32 @getInt()
    store i32 %getInt.call.4, i32* %w.4
    %e.load.10 = load %class.Edge**, %class.Edge*** @e
    %i.load.20 = load i32, i32* %i.addr.2
    %getelementptr.26 = getelementptr %class.Edge*, %class.Edge** %e.load.10, i32 %i.load.20
    %ed.load.3 = load %class.Edge*, %class.Edge** %ed.addr
    store %class.Edge* %ed.load.3, %class.Edge** %getelementptr.26
    br label %for_step.1
for_step.1:
    %i.load.19 = load i32, i32* %i.addr.2
    %add.11 = add i32 %i.load.19, 1
    store i32 %add.11, i32* %i.addr.2
    br label %for_cond.1
for_exit.1:
    %e.load.11 = load %class.Edge**, %class.Edge*** @e
    %m.load.2 = load i32, i32* @m
    %sub.3 = sub i32 %m.load.2, 1
    call void @qsort(%class.Edge** %e.load.11, i32 0, i32 %sub.3)
    call void @init()
    store i32 0, i32* %i.addr.2
    store i32 0, i32* %j.addr.1
    br label %while_cond.3
while_cond.3:
    %i.load.21 = load i32, i32* %i.addr.2
    %n.load.3 = load i32, i32* @n
    %sub.4 = sub i32 %n.load.3, 1
    %icmp.13 = icmp slt i32 %i.load.21, %sub.4
    br i1 %icmp.13, label %while_body.3, label %while_exit.3
while_body.3:
    %j.load.10 = load i32, i32* %j.addr.1
    %m.load.3 = load i32, i32* @m
    %icmp.14 = icmp sge i32 %j.load.10, %m.load.3
    br i1 %icmp.14, label %if_true.6, label %if_false.6
while_exit.3:
    %find.call.3 = call i32 @find(i32 1)
    %rk.load.9 = load i32*, i32** @rk
    %getelementptr.28 = getelementptr i32, i32* %rk.load.9, i32 %find.call.3
    %null.load.22 = load i32, i32* %getelementptr.28
    %n.load.4 = load i32, i32* @n
    %icmp.15 = icmp eq i32 %null.load.22, %n.load.4
    br i1 %icmp.15, label %if_true.8, label %if_false.8
if_true.6:
    %sub.5 = sub i32 0, 1
    call void @printInt(i32 %sub.5)
    store i32 0, i32* %main.ret.addr
    br label %main.exit
if_false.6:
    br label %if_exit.6
if_exit.6:
    %e.load.12 = load %class.Edge**, %class.Edge*** @e
    %j.load.11 = load i32, i32* %j.addr.1
    %getelementptr.27 = getelementptr %class.Edge*, %class.Edge** %e.load.12, i32 %j.load.11
    %null.load.18 = load %class.Edge*, %class.Edge** %getelementptr.27
    store %class.Edge* %null.load.18, %class.Edge** %ed.addr.1
    %j.load.12 = load i32, i32* %j.addr.1
    %add.12 = add i32 %j.load.12, 1
    store i32 %add.12, i32* %j.addr.1
    %ed.load.4 = load %class.Edge*, %class.Edge** %ed.addr.1
    %x.3 = getelementptr %class.Edge, %class.Edge* %ed.load.4, i32 0, i32 0
    %null.load.19 = load i32, i32* %x.3
    %ed.load.5 = load %class.Edge*, %class.Edge** %ed.addr.1
    %y.2 = getelementptr %class.Edge, %class.Edge* %ed.load.5, i32 0, i32 1
    %null.load.20 = load i32, i32* %y.2
    %union.call = call i1 @union(i32 %null.load.19, i32 %null.load.20)
    br i1 %union.call, label %if_true.7, label %if_false.7
if_true.7:
    %i.load.22 = load i32, i32* %i.addr.2
    %add.13 = add i32 %i.load.22, 1
    store i32 %add.13, i32* %i.addr.2
    %ans.load = load i32, i32* @ans
    %ed.load.6 = load %class.Edge*, %class.Edge** %ed.addr.1
    %w.5 = getelementptr %class.Edge, %class.Edge* %ed.load.6, i32 0, i32 2
    %null.load.21 = load i32, i32* %w.5
    %add.14 = add i32 %ans.load, %null.load.21
    store i32 %add.14, i32* @ans
    br label %if_exit.7
if_false.7:
    br label %if_exit.7
if_exit.7:
    br label %while_cond.3
if_true.8:
    %ans.load.1 = load i32, i32* @ans
    call void @printInt(i32 %ans.load.1)
    br label %if_exit.8
if_false.8:
    %sub.6 = sub i32 0, 1
    call void @printInt(i32 %sub.6)
    br label %if_exit.8
if_exit.8:
    store i32 0, i32* %main.ret.addr
    br label %main.exit
}


