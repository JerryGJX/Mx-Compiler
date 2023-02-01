
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
%class.vector = type {i32*}

; GlobalFunc
define void @_init_func() {
_init_func.entry:
    br label %_init_func.exit
_init_func.exit:
    ret void
}

define i32 @vector.init(%class.vector* %this.1) {
vector.init.entry:
    %vector.init.ret.addr = alloca i32
    %data.addr.1 = getelementptr %class.vector, %class.vector* %this.1, i32 0, i32 0
    %mul = mul i32 10, 4
    %add = add i32 %mul, 4
    %_malloc.call = call i8* @_malloc(i32 %add)
    %bitcast = bitcast i8* %_malloc.call to i32*
    store i32 10, i32* %bitcast
    %getelementptr = getelementptr i32, i32* %bitcast, i32 0, i32 1
    %bitcast.1 = bitcast i32* %getelementptr to i32*
    store i32* %bitcast.1, i32** %data.addr.1
    %vector.getDim.call = call i32 @vector.getDim(%class.vector* %this.1)
    store i32 %vector.getDim.call, i32* %vector.init.ret.addr
    br label %vector.init.exit
vector.init.exit:
    %vector.init.ret.load = load i32, i32* %vector.init.ret.addr
    ret i32 %vector.init.ret.load
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
    %getelementptr.1 = getelementptr i32, i32* %bitcast.2, i32 0, i32 -1
    %array.load = load i32, i32* %getelementptr.1
    store i32 %array.load, i32* %A.addr
    %A.load = load i32, i32* %A.addr
    store i32 %A.load, i32* %vector.getDim.ret.addr
    br label %vector.getDim.exit
vector.getDim.exit:
    %vector.getDim.ret.load = load i32, i32* %vector.getDim.ret.addr
    ret i32 %vector.getDim.ret.load
}


; MainFunc
define i32 @main() {
main.entry:
    %A.addr.1 = alloca i32
    %x.addr = alloca %class.vector*
    %main.ret.addr = alloca i32
    call void @_init_func()
    store i32 0, i32* %main.ret.addr
    %_malloc.call.1 = call i8* @_malloc(i32 4)
    %bitcast.3 = bitcast i8* %_malloc.call.1 to %class.vector*
    call void @vector.vector(%class.vector* %bitcast.3)
    store %class.vector* %bitcast.3, %class.vector** %x.addr
    %x.load = load %class.vector*, %class.vector** %x.addr
    %vector.init.call = call i32 @vector.init(%class.vector* %x.load)
    store i32 %vector.init.call, i32* %A.addr.1
    %A.load.1 = load i32, i32* %A.addr.1
    call void @printInt(i32 %A.load.1)
    %A.load.2 = load i32, i32* %A.addr.1
    store i32 %A.load.2, i32* %main.ret.addr
    br label %main.exit
main.exit:
    %main.ret.load = load i32, i32* %main.ret.addr
    ret i32 %main.ret.load
}


