
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
@strConst.3 = private unnamed_addr constant [11 x i8] c"vector x: \00"
@strConst.5 = private unnamed_addr constant [11 x i8] c"vector y: \00"
@strConst.1 = private unnamed_addr constant [3 x i8] c", \00"
@strConst.7 = private unnamed_addr constant [8 x i8] c"x * y: \00"
@strConst.4 = private unnamed_addr constant [9 x i8] c"excited!\00"
@strConst.6 = private unnamed_addr constant [8 x i8] c"x + y: \00"
@strConst = private unnamed_addr constant [3 x i8] c"( \00"
@strConst.2 = private unnamed_addr constant [3 x i8] c" )\00"
@strConst.8 = private unnamed_addr constant [15 x i8] c"(1 << 3) * y: \00"

; GlobalVar

; StructDef
%class.vector = type {i32*}

; GlobalFunc
define void @_init_func() {
_init_func.entry:
    br label %_init_func.exit
_init_func.exit:
    ret void
}

define %class.vector* @vector.scalarInPlaceMultiply(%class.vector* %this.4, i32 %c) {
vector.scalarInPlaceMultiply.entry:
    %i.addr.2 = alloca i32
    %vector.scalarInPlaceMultiply.ret.addr = alloca %class.vector*
    %c.addr = alloca i32
    %this.addr.4 = alloca %class.vector*
    store %class.vector* %this.4, %class.vector** %this.addr.4
    %this.load.4 = load %class.vector*, %class.vector** %this.addr.4
    store i32 %c, i32* %c.addr
    %data.addr.4 = getelementptr %class.vector, %class.vector* %this.load.4, i32 0, i32 0
    %data.load.4 = load i32*, i32** %data.addr.4
    %icmp.4 = icmp eq i32* %data.load.4, null
    br i1 %icmp.4, label %if_true.2, label %if_false.2
vector.scalarInPlaceMultiply.exit:
    %vector.scalarInPlaceMultiply.ret.load = load %class.vector*, %class.vector** %vector.scalarInPlaceMultiply.ret.addr
    ret %class.vector* %vector.scalarInPlaceMultiply.ret.load
if_true.2:
    store %class.vector* null, %class.vector** %vector.scalarInPlaceMultiply.ret.addr
    br label %vector.scalarInPlaceMultiply.exit
if_false.2:
    br label %if_exit.2
if_exit.2:
    store i32 0, i32* %i.addr.2
    br label %for_cond.1
for_cond.1:
    %i.load.8 = load i32, i32* %i.addr.2
    %vector.getDim.call.1 = call i32 @vector.getDim(%class.vector* %this.load.4)
    %icmp.5 = icmp slt i32 %i.load.8, %vector.getDim.call.1
    br i1 %icmp.5, label %for_body.1, label %for_exit.1
for_body.1:
    %data.addr.4 = getelementptr %class.vector, %class.vector* %this.load.4, i32 0, i32 0
    %null.load.4 = load i32*, i32** %data.addr.4
    %i.load.10 = load i32, i32* %i.addr.2
    %getelementptr.8 = getelementptr i32, i32* %null.load.4, i32 0, i32 %i.load.10
    %c.load = load i32, i32* %c.addr
    %data.addr.4 = getelementptr %class.vector, %class.vector* %this.load.4, i32 0, i32 0
    %null.load.5 = load i32*, i32** %data.addr.4
    %i.load.11 = load i32, i32* %i.addr.2
    %getelementptr.9 = getelementptr i32, i32* %null.load.5, i32 0, i32 %i.load.11
    %null.load.6 = load i32, i32* %getelementptr.9
    %mul.2 = mul i32 %c.load, %null.load.6
    store i32 %mul.2, i32* %getelementptr.8
    br label %for_step.1
for_step.1:
    %i.load.9 = load i32, i32* %i.addr.2
    %add.3 = add i32 %i.load.9, 1
    store i32 %add.3, i32* %i.addr.2
    br label %for_cond.1
for_exit.1:
    store %class.vector* %this.load.4, %class.vector** %vector.scalarInPlaceMultiply.ret.addr
    br label %vector.scalarInPlaceMultiply.exit
}

define void @vector.vector(%class.vector* %this) {
vector.vector.entry:
    %this.addr = alloca %class.vector*
    store %class.vector* %this, %class.vector** %this.addr
    %this.load = load %class.vector*, %class.vector** %this.addr
    br label %vector.vector.exit
vector.vector.exit:
    ret void
}

define i32 @vector.getDim(%class.vector* %this.2) {
vector.getDim.entry:
    %vector.getDim.ret.addr = alloca i32
    %this.addr.2 = alloca %class.vector*
    store %class.vector* %this.2, %class.vector** %this.addr.2
    %this.load.2 = load %class.vector*, %class.vector** %this.addr.2
    %data.addr.2 = getelementptr %class.vector, %class.vector* %this.load.2, i32 0, i32 0
    %data.load.1 = load i32*, i32** %data.addr.2
    %icmp.2 = icmp eq i32* %data.load.1, null
    br i1 %icmp.2, label %if_true.1, label %if_false.1
vector.getDim.exit:
    %vector.getDim.ret.load = load i32, i32* %vector.getDim.ret.addr
    ret i32 %vector.getDim.ret.load
if_true.1:
    store i32 0, i32* %vector.getDim.ret.addr
    br label %vector.getDim.exit
if_false.1:
    br label %if_exit.1
if_exit.1:
    %data.addr.2 = getelementptr %class.vector, %class.vector* %this.load.2, i32 0, i32 0
    %data.load.2 = load i32*, i32** %data.addr.2
    %bitcast.4 = bitcast i32* %data.load.2 to i32*
    %getelementptr.5 = getelementptr i32, i32* %bitcast.4, i32 0, i32 -1
    %array.load.2 = load i32, i32* %getelementptr.5
    store i32 %array.load.2, i32* %vector.getDim.ret.addr
    br label %vector.getDim.exit
}

define i32 @vector.dot(%class.vector* %this.3, %class.vector* %rhs) {
vector.dot.entry:
    %result.addr = alloca i32
    %i.addr.1 = alloca i32
    %vector.dot.ret.addr = alloca i32
    %rhs.addr = alloca %class.vector*
    %this.addr.3 = alloca %class.vector*
    store %class.vector* %this.3, %class.vector** %this.addr.3
    %this.load.3 = load %class.vector*, %class.vector** %this.addr.3
    store %class.vector* %rhs, %class.vector** %rhs.addr
    store i32 0, i32* %i.addr.1
    store i32 0, i32* %result.addr
    br label %while_cond
vector.dot.exit:
    %vector.dot.ret.load = load i32, i32* %vector.dot.ret.addr
    ret i32 %vector.dot.ret.load
while_cond:
    %i.load.4 = load i32, i32* %i.addr.1
    %vector.getDim.call = call i32 @vector.getDim(%class.vector* %this.load.3)
    %icmp.3 = icmp slt i32 %i.load.4, %vector.getDim.call
    br i1 %icmp.3, label %while_body, label %while_exit
while_body:
    %data.addr.3 = getelementptr %class.vector, %class.vector* %this.load.3, i32 0, i32 0
    %data.load.3 = load i32*, i32** %data.addr.3
    %i.load.5 = load i32, i32* %i.addr.1
    %getelementptr.6 = getelementptr i32, i32* %data.load.3, i32 0, i32 %i.load.5
    %null.load.1 = load i32, i32* %getelementptr.6
    %rhs.load = load %class.vector*, %class.vector** %rhs.addr
    %data = getelementptr %class.vector, %class.vector* %rhs.load, i32 0, i32 0
    %null.load.2 = load i32*, i32** %data
    %i.load.6 = load i32, i32* %i.addr.1
    %getelementptr.7 = getelementptr i32, i32* %null.load.2, i32 0, i32 %i.load.6
    %null.load.3 = load i32, i32* %getelementptr.7
    %mul.1 = mul i32 %null.load.1, %null.load.3
    store i32 %mul.1, i32* %result.addr
    %i.load.7 = load i32, i32* %i.addr.1
    %add.2 = add i32 %i.load.7, 1
    store i32 %add.2, i32* %i.addr.1
    br label %while_cond
while_exit:
    %result.load = load i32, i32* %result.addr
    store i32 %result.load, i32* %vector.dot.ret.addr
    br label %vector.dot.exit
}

define void @vector.init(%class.vector* %this.1, i32* %vec) {
vector.init.entry:
    %i.addr = alloca i32
    %vec.addr = alloca i32*
    %this.addr.1 = alloca %class.vector*
    store %class.vector* %this.1, %class.vector** %this.addr.1
    %this.load.1 = load %class.vector*, %class.vector** %this.addr.1
    store i32* %vec, i32** %vec.addr
    %vec.load = load i32*, i32** %vec.addr
    %icmp = icmp eq i32* %vec.load, null
    br i1 %icmp, label %if_true, label %if_false
vector.init.exit:
    ret void
if_true:
    br label %vector.init.exit
if_false:
    br label %if_exit
if_exit:
    %data.addr.1 = getelementptr %class.vector, %class.vector* %this.load.1, i32 0, i32 0
    %vec.load.1 = load i32*, i32** %vec.addr
    %bitcast = bitcast i32* %vec.load.1 to i32*
    %getelementptr = getelementptr i32, i32* %bitcast, i32 0, i32 -1
    %array.load = load i32, i32* %getelementptr
    %mul = mul i32 %array.load, 4
    %add = add i32 %mul, 4
    %_malloc.call = call i8* @_malloc(i32 %add)
    %bitcast.1 = bitcast i8* %_malloc.call to i32*
    store i32 %array.load, i32* %bitcast.1
    %getelementptr.1 = getelementptr i32, i32* %bitcast.1, i32 0, i32 1
    %bitcast.2 = bitcast i32* %getelementptr.1 to i32*
    store i32* %bitcast.2, i32** %data.addr.1
    store i32 0, i32* %i.addr
    br label %for_cond
for_cond:
    %i.load = load i32, i32* %i.addr
    %vec.load.2 = load i32*, i32** %vec.addr
    %bitcast.3 = bitcast i32* %vec.load.2 to i32*
    %getelementptr.2 = getelementptr i32, i32* %bitcast.3, i32 0, i32 -1
    %array.load.1 = load i32, i32* %getelementptr.2
    %icmp.1 = icmp slt i32 %i.load, %array.load.1
    br i1 %icmp.1, label %for_body, label %for_exit
for_body:
    %data.addr.1 = getelementptr %class.vector, %class.vector* %this.load.1, i32 0, i32 0
    %data.load = load i32*, i32** %data.addr.1
    %i.load.2 = load i32, i32* %i.addr
    %getelementptr.3 = getelementptr i32, i32* %data.load, i32 0, i32 %i.load.2
    %vec.load.3 = load i32*, i32** %vec.addr
    %i.load.3 = load i32, i32* %i.addr
    %getelementptr.4 = getelementptr i32, i32* %vec.load.3, i32 0, i32 %i.load.3
    %null.load = load i32, i32* %getelementptr.4
    store i32 %null.load, i32* %getelementptr.3
    br label %for_step
for_step:
    %i.load.1 = load i32, i32* %i.addr
    %add.1 = add i32 %i.load.1, 1
    store i32 %add.1, i32* %i.addr
    br label %for_cond
for_exit:
    br label %vector.init.exit
}

define %class.vector* @vector.add(%class.vector* %this.5, %class.vector* %rhs.1) {
vector.add.entry:
    %i.addr.3 = alloca i32
    %temp.addr = alloca %class.vector*
    %tmp.addr = alloca i8
    %vector.add.ret.addr = alloca %class.vector*
    %rhs.addr.1 = alloca %class.vector*
    %this.addr.5 = alloca %class.vector*
    store %class.vector* %this.5, %class.vector** %this.addr.5
    %this.load.5 = load %class.vector*, %class.vector** %this.addr.5
    store %class.vector* %rhs.1, %class.vector** %rhs.addr.1
    %vector.getDim.call.2 = call i32 @vector.getDim(%class.vector* %this.load.5)
    %rhs.load.1 = load %class.vector*, %class.vector** %rhs.addr.1
    %vector.getDim.call.3 = call i32 @vector.getDim(%class.vector* %rhs.load.1)
    %icmp.6 = icmp ne i32 %vector.getDim.call.2, %vector.getDim.call.3
    %zext = zext i1 %icmp.6 to i8
    store i8 %zext, i8* %tmp.addr
    br i1 %icmp.6, label %logicExitBlock, label %noShortCutBlock
vector.add.exit:
    %vector.add.ret.load = load %class.vector*, %class.vector** %vector.add.ret.addr
    ret %class.vector* %vector.add.ret.load
if_true.3:
    store %class.vector* null, %class.vector** %vector.add.ret.addr
    br label %vector.add.exit
if_false.3:
    br label %if_exit.3
if_exit.3:
    %_malloc.call.1 = call i8* @_malloc(i32 4)
    %bitcast.5 = bitcast i8* %_malloc.call.1 to %class.vector*
    call void @vector.vector(%class.vector* %bitcast.5)
    store %class.vector* %bitcast.5, %class.vector** %temp.addr
    %temp.load = load %class.vector*, %class.vector** %temp.addr
    %data.1 = getelementptr %class.vector, %class.vector* %temp.load, i32 0, i32 0
    %vector.getDim.call.5 = call i32 @vector.getDim(%class.vector* %this.load.5)
    %mul.3 = mul i32 %vector.getDim.call.5, 4
    %add.4 = add i32 %mul.3, 4
    %_malloc.call.2 = call i8* @_malloc(i32 %add.4)
    %bitcast.6 = bitcast i8* %_malloc.call.2 to i32*
    store i32 %vector.getDim.call.5, i32* %bitcast.6
    %getelementptr.10 = getelementptr i32, i32* %bitcast.6, i32 0, i32 1
    %bitcast.7 = bitcast i32* %getelementptr.10 to i32*
    store i32* %bitcast.7, i32** %data.1
    store i32 0, i32* %i.addr.3
    br label %for_cond.2
noShortCutBlock:
    %vector.getDim.call.4 = call i32 @vector.getDim(%class.vector* %this.load.5)
    %icmp.7 = icmp eq i32 %vector.getDim.call.4, 0
    %or = or i1 %icmp.6, %icmp.7
    %zext.1 = zext i1 %or to i8
    store i8 %zext.1, i8* %tmp.addr
    br label %logicExitBlock
logicExitBlock:
    %or.load = load i8, i8* %tmp.addr
    %trunc = trunc i8 %or.load to i1
    br i1 %trunc, label %if_true.3, label %if_false.3
for_cond.2:
    %i.load.12 = load i32, i32* %i.addr.3
    %vector.getDim.call.6 = call i32 @vector.getDim(%class.vector* %this.load.5)
    %icmp.8 = icmp slt i32 %i.load.12, %vector.getDim.call.6
    br i1 %icmp.8, label %for_body.2, label %for_exit.2
for_body.2:
    %temp.load.1 = load %class.vector*, %class.vector** %temp.addr
    %data.2 = getelementptr %class.vector, %class.vector* %temp.load.1, i32 0, i32 0
    %null.load.7 = load i32*, i32** %data.2
    %i.load.14 = load i32, i32* %i.addr.3
    %getelementptr.11 = getelementptr i32, i32* %null.load.7, i32 0, i32 %i.load.14
    %data.addr.5 = getelementptr %class.vector, %class.vector* %this.load.5, i32 0, i32 0
    %data.load.5 = load i32*, i32** %data.addr.5
    %i.load.15 = load i32, i32* %i.addr.3
    %getelementptr.12 = getelementptr i32, i32* %data.load.5, i32 0, i32 %i.load.15
    %null.load.8 = load i32, i32* %getelementptr.12
    %rhs.load.2 = load %class.vector*, %class.vector** %rhs.addr.1
    %data.3 = getelementptr %class.vector, %class.vector* %rhs.load.2, i32 0, i32 0
    %null.load.9 = load i32*, i32** %data.3
    %i.load.16 = load i32, i32* %i.addr.3
    %getelementptr.13 = getelementptr i32, i32* %null.load.9, i32 0, i32 %i.load.16
    %null.load.10 = load i32, i32* %getelementptr.13
    %add.6 = add i32 %null.load.8, %null.load.10
    store i32 %add.6, i32* %getelementptr.11
    br label %for_step.2
for_step.2:
    %i.load.13 = load i32, i32* %i.addr.3
    %add.5 = add i32 %i.load.13, 1
    store i32 %add.5, i32* %i.addr.3
    br label %for_cond.2
for_exit.2:
    %temp.load.2 = load %class.vector*, %class.vector** %temp.addr
    store %class.vector* %temp.load.2, %class.vector** %vector.add.ret.addr
    br label %vector.add.exit
}

define i1 @vector.copy(%class.vector* %this.8, %class.vector* %rhs.2) {
vector.copy.entry:
    %i.addr.5 = alloca i32
    %vector.copy.ret.addr = alloca i8
    %rhs.addr.2 = alloca %class.vector*
    %this.addr.8 = alloca %class.vector*
    store %class.vector* %this.8, %class.vector** %this.addr.8
    %this.load.8 = load %class.vector*, %class.vector** %this.addr.8
    store %class.vector* %rhs.2, %class.vector** %rhs.addr.2
    %rhs.load.3 = load %class.vector*, %class.vector** %rhs.addr.2
    %icmp.12 = icmp eq %class.vector* %rhs.load.3, null
    br i1 %icmp.12, label %if_true.6, label %if_false.6
vector.copy.exit:
    %vector.copy.ret.load = load i8, i8* %vector.copy.ret.addr
    %trunc.2 = trunc i8 %vector.copy.ret.load to i1
    ret i1 %trunc.2
if_true.6:
    %zext.4 = zext i1 false to i8
    store i8 %zext.4, i8* %vector.copy.ret.addr
    br label %vector.copy.exit
if_false.6:
    br label %if_exit.6
if_exit.6:
    %rhs.load.4 = load %class.vector*, %class.vector** %rhs.addr.2
    %vector.getDim.call.10 = call i32 @vector.getDim(%class.vector* %rhs.load.4)
    %icmp.13 = icmp eq i32 %vector.getDim.call.10, 0
    br i1 %icmp.13, label %if_true.7, label %if_false.7
if_true.7:
    %data.addr.8 = getelementptr %class.vector, %class.vector* %this.load.8, i32 0, i32 0
    store i32* null, i32** %data.addr.8
    br label %if_exit.7
if_false.7:
    %data.addr.8 = getelementptr %class.vector, %class.vector* %this.load.8, i32 0, i32 0
    %rhs.load.5 = load %class.vector*, %class.vector** %rhs.addr.2
    %vector.getDim.call.11 = call i32 @vector.getDim(%class.vector* %rhs.load.5)
    %mul.4 = mul i32 %vector.getDim.call.11, 4
    %add.8 = add i32 %mul.4, 4
    %_malloc.call.3 = call i8* @_malloc(i32 %add.8)
    %bitcast.8 = bitcast i8* %_malloc.call.3 to i32*
    store i32 %vector.getDim.call.11, i32* %bitcast.8
    %getelementptr.20 = getelementptr i32, i32* %bitcast.8, i32 0, i32 1
    %bitcast.9 = bitcast i32* %getelementptr.20 to i32*
    store i32* %bitcast.9, i32** %data.addr.8
    store i32 0, i32* %i.addr.5
    br label %for_cond.4
if_exit.7:
    %zext.5 = zext i1 true to i8
    store i8 %zext.5, i8* %vector.copy.ret.addr
    br label %vector.copy.exit
for_cond.4:
    %i.load.20 = load i32, i32* %i.addr.5
    %vector.getDim.call.12 = call i32 @vector.getDim(%class.vector* %this.load.8)
    %icmp.14 = icmp slt i32 %i.load.20, %vector.getDim.call.12
    br i1 %icmp.14, label %for_body.4, label %for_exit.4
for_body.4:
    %data.addr.8 = getelementptr %class.vector, %class.vector* %this.load.8, i32 0, i32 0
    %data.load.9 = load i32*, i32** %data.addr.8
    %i.load.22 = load i32, i32* %i.addr.5
    %getelementptr.21 = getelementptr i32, i32* %data.load.9, i32 0, i32 %i.load.22
    %rhs.load.6 = load %class.vector*, %class.vector** %rhs.addr.2
    %data.4 = getelementptr %class.vector, %class.vector* %rhs.load.6, i32 0, i32 0
    %null.load.13 = load i32*, i32** %data.4
    %i.load.23 = load i32, i32* %i.addr.5
    %getelementptr.22 = getelementptr i32, i32* %null.load.13, i32 0, i32 %i.load.23
    %null.load.14 = load i32, i32* %getelementptr.22
    store i32 %null.load.14, i32* %getelementptr.21
    br label %for_step.4
for_step.4:
    %i.load.21 = load i32, i32* %i.addr.5
    %add.9 = add i32 %i.load.21, 1
    store i32 %add.9, i32* %i.addr.5
    br label %for_cond.4
for_exit.4:
    br label %if_exit.7
}

define i8* @vector.tostring(%class.vector* %this.7) {
vector.tostring.entry:
    %i.addr.4 = alloca i32
    %temp.addr.1 = alloca i8*
    %vector.tostring.ret.addr = alloca i8*
    %this.addr.7 = alloca %class.vector*
    store %class.vector* %this.7, %class.vector** %this.addr.7
    %this.load.7 = load %class.vector*, %class.vector** %this.addr.7
    %getelementptr.15 = getelementptr [3 x i8], [3 x i8]* @strConst, i32 0, i32 0
    store i8* %getelementptr.15, i8** %temp.addr.1
    %vector.getDim.call.8 = call i32 @vector.getDim(%class.vector* %this.load.7)
    %icmp.10 = icmp sgt i32 %vector.getDim.call.8, 0
    br i1 %icmp.10, label %if_true.5, label %if_false.5
vector.tostring.exit:
    %vector.tostring.ret.load = load i8*, i8** %vector.tostring.ret.addr
    ret i8* %vector.tostring.ret.load
if_true.5:
    %temp.load.3 = load i8*, i8** %temp.addr.1
    %data.addr.7 = getelementptr %class.vector, %class.vector* %this.load.7, i32 0, i32 0
    %data.load.7 = load i32*, i32** %data.addr.7
    %getelementptr.16 = getelementptr i32, i32* %data.load.7, i32 0, i32 0
    %null.load.11 = load i32, i32* %getelementptr.16
    %toString.call = call i8* @toString(i32 %null.load.11)
    %_str_concat.call = call i8* @_str_concat(i8* %temp.load.3, i8* %toString.call)
    store i8* %_str_concat.call, i8** %temp.addr.1
    br label %if_exit.5
if_false.5:
    br label %if_exit.5
if_exit.5:
    store i32 1, i32* %i.addr.4
    br label %for_cond.3
for_cond.3:
    %i.load.17 = load i32, i32* %i.addr.4
    %vector.getDim.call.9 = call i32 @vector.getDim(%class.vector* %this.load.7)
    %icmp.11 = icmp slt i32 %i.load.17, %vector.getDim.call.9
    br i1 %icmp.11, label %for_body.3, label %for_exit.3
for_body.3:
    %temp.load.4 = load i8*, i8** %temp.addr.1
    %getelementptr.17 = getelementptr [3 x i8], [3 x i8]* @strConst.1, i32 0, i32 0
    %_str_concat.call.1 = call i8* @_str_concat(i8* %temp.load.4, i8* %getelementptr.17)
    %data.addr.7 = getelementptr %class.vector, %class.vector* %this.load.7, i32 0, i32 0
    %data.load.8 = load i32*, i32** %data.addr.7
    %i.load.19 = load i32, i32* %i.addr.4
    %getelementptr.18 = getelementptr i32, i32* %data.load.8, i32 0, i32 %i.load.19
    %null.load.12 = load i32, i32* %getelementptr.18
    %toString.call.1 = call i8* @toString(i32 %null.load.12)
    %_str_concat.call.2 = call i8* @_str_concat(i8* %_str_concat.call.1, i8* %toString.call.1)
    store i8* %_str_concat.call.2, i8** %temp.addr.1
    br label %for_step.3
for_step.3:
    %i.load.18 = load i32, i32* %i.addr.4
    %add.7 = add i32 %i.load.18, 1
    store i32 %add.7, i32* %i.addr.4
    br label %for_cond.3
for_exit.3:
    %temp.load.5 = load i8*, i8** %temp.addr.1
    %getelementptr.19 = getelementptr [3 x i8], [3 x i8]* @strConst.2, i32 0, i32 0
    %_str_concat.call.3 = call i8* @_str_concat(i8* %temp.load.5, i8* %getelementptr.19)
    store i8* %_str_concat.call.3, i8** %temp.addr.1
    %temp.load.6 = load i8*, i8** %temp.addr.1
    store i8* %temp.load.6, i8** %vector.tostring.ret.addr
    br label %vector.tostring.exit
}

define i1 @vector.set(%class.vector* %this.6, i32 %idx, i32 %IRValue) {
vector.set.entry:
    %vector.set.ret.addr = alloca i8
    %IRValue.addr = alloca i32
    %idx.addr = alloca i32
    %this.addr.6 = alloca %class.vector*
    store %class.vector* %this.6, %class.vector** %this.addr.6
    %this.load.6 = load %class.vector*, %class.vector** %this.addr.6
    store i32 %idx, i32* %idx.addr
    store i32 %IRValue, i32* %IRValue.addr
    %vector.getDim.call.7 = call i32 @vector.getDim(%class.vector* %this.load.6)
    %idx.load = load i32, i32* %idx.addr
    %icmp.9 = icmp slt i32 %vector.getDim.call.7, %idx.load
    br i1 %icmp.9, label %if_true.4, label %if_false.4
vector.set.exit:
    %vector.set.ret.load = load i8, i8* %vector.set.ret.addr
    %trunc.1 = trunc i8 %vector.set.ret.load to i1
    ret i1 %trunc.1
if_true.4:
    %zext.2 = zext i1 false to i8
    store i8 %zext.2, i8* %vector.set.ret.addr
    br label %vector.set.exit
if_false.4:
    br label %if_exit.4
if_exit.4:
    %data.addr.6 = getelementptr %class.vector, %class.vector* %this.load.6, i32 0, i32 0
    %data.load.6 = load i32*, i32** %data.addr.6
    %idx.load.1 = load i32, i32* %idx.addr
    %getelementptr.14 = getelementptr i32, i32* %data.load.6, i32 0, i32 %idx.load.1
    %IRValue.load = load i32, i32* %IRValue.addr
    store i32 %IRValue.load, i32* %getelementptr.14
    %zext.3 = zext i1 true to i8
    store i8 %zext.3, i8* %vector.set.ret.addr
    br label %vector.set.exit
}


; MainFunc
define i32 @main() {
main.entry:
    %y.addr = alloca %class.vector*
    %i.addr.6 = alloca i32
    %a.addr = alloca i32*
    %x.addr = alloca %class.vector*
    %main.ret.addr = alloca i32
    call void @_init_func()
    store i32 0, i32* %main.ret.addr
    %_malloc.call.4 = call i8* @_malloc(i32 4)
    %bitcast.10 = bitcast i8* %_malloc.call.4 to %class.vector*
    call void @vector.vector(%class.vector* %bitcast.10)
    store %class.vector* %bitcast.10, %class.vector** %x.addr
    %mul.5 = mul i32 10, 4
    %add.10 = add i32 %mul.5, 4
    %_malloc.call.5 = call i8* @_malloc(i32 %add.10)
    %bitcast.11 = bitcast i8* %_malloc.call.5 to i32*
    store i32 10, i32* %bitcast.11
    %getelementptr.23 = getelementptr i32, i32* %bitcast.11, i32 0, i32 1
    %bitcast.12 = bitcast i32* %getelementptr.23 to i32*
    store i32* %bitcast.12, i32** %a.addr
    store i32 0, i32* %i.addr.6
    br label %for_cond.5
main.exit:
    %main.ret.load = load i32, i32* %main.ret.addr
    ret i32 %main.ret.load
for_cond.5:
    %i.load.24 = load i32, i32* %i.addr.6
    %icmp.15 = icmp slt i32 %i.load.24, 10
    br i1 %icmp.15, label %for_body.5, label %for_exit.5
for_body.5:
    %a.load = load i32*, i32** %a.addr
    %i.load.26 = load i32, i32* %i.addr.6
    %getelementptr.24 = getelementptr i32, i32* %a.load, i32 0, i32 %i.load.26
    %i.load.27 = load i32, i32* %i.addr.6
    %sub = sub i32 9, %i.load.27
    store i32 %sub, i32* %getelementptr.24
    br label %for_step.5
for_step.5:
    %i.load.25 = load i32, i32* %i.addr.6
    %add.11 = add i32 %i.load.25, 1
    store i32 %add.11, i32* %i.addr.6
    br label %for_cond.5
for_exit.5:
    %x.load = load %class.vector*, %class.vector** %x.addr
    %a.load.1 = load i32*, i32** %a.addr
    call void @vector.init(%class.vector* %x.load, i32* %a.load.1)
    %getelementptr.25 = getelementptr [11 x i8], [11 x i8]* @strConst.3, i32 0, i32 0
    call void @print(i8* %getelementptr.25)
    %x.load.1 = load %class.vector*, %class.vector** %x.addr
    %vector.tostring.call = call i8* @vector.tostring(%class.vector* %x.load.1)
    call void @println(i8* %vector.tostring.call)
    %_malloc.call.6 = call i8* @_malloc(i32 4)
    %bitcast.13 = bitcast i8* %_malloc.call.6 to %class.vector*
    call void @vector.vector(%class.vector* %bitcast.13)
    store %class.vector* %bitcast.13, %class.vector** %y.addr
    %y.load = load %class.vector*, %class.vector** %y.addr
    %x.load.2 = load %class.vector*, %class.vector** %x.addr
    %vector.copy.call = call i1 @vector.copy(%class.vector* %y.load, %class.vector* %x.load.2)
    %y.load.1 = load %class.vector*, %class.vector** %y.addr
    %vector.set.call = call i1 @vector.set(%class.vector* %y.load.1, i32 3, i32 817)
    br i1 %vector.set.call, label %if_true.8, label %if_false.8
if_true.8:
    %getelementptr.26 = getelementptr [9 x i8], [9 x i8]* @strConst.4, i32 0, i32 0
    call void @println(i8* %getelementptr.26)
    br label %if_exit.8
if_false.8:
    br label %if_exit.8
if_exit.8:
    %getelementptr.27 = getelementptr [11 x i8], [11 x i8]* @strConst.5, i32 0, i32 0
    call void @print(i8* %getelementptr.27)
    %y.load.2 = load %class.vector*, %class.vector** %y.addr
    %vector.tostring.call.1 = call i8* @vector.tostring(%class.vector* %y.load.2)
    call void @println(i8* %vector.tostring.call.1)
    %getelementptr.28 = getelementptr [8 x i8], [8 x i8]* @strConst.6, i32 0, i32 0
    call void @print(i8* %getelementptr.28)
    %x.load.3 = load %class.vector*, %class.vector** %x.addr
    %y.load.3 = load %class.vector*, %class.vector** %y.addr
    %vector.add.call = call %class.vector* @vector.add(%class.vector* %x.load.3, %class.vector* %y.load.3)
    %vector.tostring.call.2 = call i8* @vector.tostring(%class.vector* %vector.add.call)
    call void @println(i8* %vector.tostring.call.2)
    %getelementptr.29 = getelementptr [8 x i8], [8 x i8]* @strConst.7, i32 0, i32 0
    call void @print(i8* %getelementptr.29)
    %x.load.4 = load %class.vector*, %class.vector** %x.addr
    %y.load.4 = load %class.vector*, %class.vector** %y.addr
    %vector.dot.call = call i32 @vector.dot(%class.vector* %x.load.4, %class.vector* %y.load.4)
    %toString.call.2 = call i8* @toString(i32 %vector.dot.call)
    call void @println(i8* %toString.call.2)
    %getelementptr.30 = getelementptr [15 x i8], [15 x i8]* @strConst.8, i32 0, i32 0
    call void @print(i8* %getelementptr.30)
    %y.load.5 = load %class.vector*, %class.vector** %y.addr
    %shl = shl i32 1, 3
    %vector.scalarInPlaceMultiply.call = call %class.vector* @vector.scalarInPlaceMultiply(%class.vector* %y.load.5, i32 %shl)
    %vector.tostring.call.3 = call i8* @vector.tostring(%class.vector* %vector.scalarInPlaceMultiply.call)
    call void @println(i8* %vector.tostring.call.3)
    store i32 0, i32* %main.ret.addr
    br label %main.exit
}


