
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
@strConst.4 = private unnamed_addr constant [23 x i8] c"x.tostring() in main: \00"
@strConst.5 = private unnamed_addr constant [22 x i8] c"x.getDim() in main3: \00"
@strConst.3 = private unnamed_addr constant [22 x i8] c"x.getDim() in main2: \00"
@strConst.2 = private unnamed_addr constant [22 x i8] c"x.getDim() in main1: \00"
@strConst = private unnamed_addr constant [19 x i8] c"getDim in init2 = \00"
@strConst.1 = private unnamed_addr constant [25 x i8] c"data.size in tostring = \00"

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

define void @vector.init(%class.vector* %this.1) {
vector.init.entry:
    %data.addr.1 = getelementptr %class.vector, %class.vector* %this.1, i32 0, i32 0
    %mul = mul i32 10, 4
    %add = add i32 %mul, 4
    %_malloc.call = call i8* @_malloc(i32 %add)
    %bitcast = bitcast i8* %_malloc.call to i32*
    store i32 10, i32* %bitcast
    %getelementptr = getelementptr i32, i32* %bitcast, i32 0, i32 1
    %bitcast.1 = bitcast i32* %getelementptr to i32*
    store i32* %bitcast.1, i32** %data.addr.1
    %getelementptr.1 = getelementptr [19 x i8], [19 x i8]* @strConst, i32 0, i32 0
    %vector.getDim.call = call i32 @vector.getDim(%class.vector* %this.1)
    %toString.call = call i8* @toString(i32 %vector.getDim.call)
    %_str_concat.call = call i8* @_str_concat(i8* %getelementptr.1, i8* %toString.call)
    call void @println(i8* %_str_concat.call)
    br label %vector.init.exit
vector.init.exit:
    ret void
}

define void @vector.vector(%class.vector* %this) {
vector.vector.entry:
    br label %vector.vector.exit
vector.vector.exit:
    ret void
}

define i32 @vector.getDim(%class.vector* %this.2) {
vector.getDim.entry:
    %A.addr = alloca i32
    %vector.getDim.ret.addr = alloca i32
    %data.addr.2 = getelementptr %class.vector, %class.vector* %this.2, i32 0, i32 0
    %data.load = load i32*, i32** %data.addr.2
    %bitcast.2 = bitcast i32* %data.load to i32*
    %getelementptr.2 = getelementptr i32, i32* %bitcast.2, i32 0, i32 -1
    %array.load = load i32, i32* %getelementptr.2
    store i32 %array.load, i32* %A.addr
    %A.load = load i32, i32* %A.addr
    store i32 %A.load, i32* %vector.getDim.ret.addr
    br label %vector.getDim.exit
vector.getDim.exit:
    %vector.getDim.ret.load = load i32, i32* %vector.getDim.ret.addr
    ret i32 %vector.getDim.ret.load
}

define i8* @vector.tostring(%class.vector* %this.3) {
vector.tostring.entry:
    %vector.tostring.ret.addr = alloca i8*
    %getelementptr.3 = getelementptr [25 x i8], [25 x i8]* @strConst.1, i32 0, i32 0
    %data.addr.3 = getelementptr %class.vector, %class.vector* %this.3, i32 0, i32 0
    %data.load.1 = load i32*, i32** %data.addr.3
    %bitcast.3 = bitcast i32* %data.load.1 to i32*
    %getelementptr.4 = getelementptr i32, i32* %bitcast.3, i32 0, i32 -1
    %array.load.1 = load i32, i32* %getelementptr.4
    %toString.call.1 = call i8* @toString(i32 %array.load.1)
    %_str_concat.call.1 = call i8* @_str_concat(i8* %getelementptr.3, i8* %toString.call.1)
    call void @println(i8* %_str_concat.call.1)
    %vector.getDim.call.1 = call i32 @vector.getDim(%class.vector* %this.3)
    %toString.call.2 = call i8* @toString(i32 %vector.getDim.call.1)
    store i8* %toString.call.2, i8** %vector.tostring.ret.addr
    br label %vector.tostring.exit
vector.tostring.exit:
    %vector.tostring.ret.load = load i8*, i8** %vector.tostring.ret.addr
    ret i8* %vector.tostring.ret.load
}


; MainFunc
define i32 @main() {
main.entry:
    %x.addr = alloca %class.vector*
    %main.ret.addr = alloca i32
    call void @_init_func()
    store i32 0, i32* %main.ret.addr
    %_malloc.call.1 = call i8* @_malloc(i32 4)
    %bitcast.4 = bitcast i8* %_malloc.call.1 to %class.vector*
    call void @vector.vector(%class.vector* %bitcast.4)
    store %class.vector* %bitcast.4, %class.vector** %x.addr
    %getelementptr.5 = getelementptr [22 x i8], [22 x i8]* @strConst.2, i32 0, i32 0
    %x.load = load %class.vector*, %class.vector** %x.addr
    %vector.getDim.call.2 = call i32 @vector.getDim(%class.vector* %x.load)
    %toString.call.3 = call i8* @toString(i32 %vector.getDim.call.2)
    %_str_concat.call.2 = call i8* @_str_concat(i8* %getelementptr.5, i8* %toString.call.3)
    call void @println(i8* %_str_concat.call.2)
    %x.load.1 = load %class.vector*, %class.vector** %x.addr
    call void @vector.init(%class.vector* %x.load.1)
    %getelementptr.6 = getelementptr [22 x i8], [22 x i8]* @strConst.3, i32 0, i32 0
    %x.load.2 = load %class.vector*, %class.vector** %x.addr
    %vector.getDim.call.3 = call i32 @vector.getDim(%class.vector* %x.load.2)
    %toString.call.4 = call i8* @toString(i32 %vector.getDim.call.3)
    %_str_concat.call.3 = call i8* @_str_concat(i8* %getelementptr.6, i8* %toString.call.4)
    call void @println(i8* %_str_concat.call.3)
    %getelementptr.7 = getelementptr [23 x i8], [23 x i8]* @strConst.4, i32 0, i32 0
    %x.load.3 = load %class.vector*, %class.vector** %x.addr
    %vector.tostring.call = call i8* @vector.tostring(%class.vector* %x.load.3)
    %_str_concat.call.4 = call i8* @_str_concat(i8* %getelementptr.7, i8* %vector.tostring.call)
    call void @println(i8* %_str_concat.call.4)
    %getelementptr.8 = getelementptr [22 x i8], [22 x i8]* @strConst.5, i32 0, i32 0
    %x.load.4 = load %class.vector*, %class.vector** %x.addr
    %vector.getDim.call.4 = call i32 @vector.getDim(%class.vector* %x.load.4)
    %toString.call.5 = call i8* @toString(i32 %vector.getDim.call.4)
    %_str_concat.call.5 = call i8* @_str_concat(i8* %getelementptr.8, i8* %toString.call.5)
    call void @println(i8* %_str_concat.call.5)
    br label %main.exit
main.exit:
    %main.ret.load = load i32, i32* %main.ret.addr
    ret i32 %main.ret.load
}


