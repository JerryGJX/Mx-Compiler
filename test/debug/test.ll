
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
%class.point = type {i32, i32, i32}

; GlobalFunc
define void @_init_func() {
_init_func.entry:
    br label %_init_func.exit
_init_func.exit:
    ret void
}

define void @point.printPoint(%class.point* %this.1) {
point.printPoint.entry:
    %x.addr.1 = getelementptr %class.point, %class.point* %this.1, i32 0, i32 0
    %x.load = load i32, i32* %x.addr.1
    %toString.call = call i8* @toString(i32 %x.load)
    call void @println(i8* %toString.call)
    %y.addr.1 = getelementptr %class.point, %class.point* %this.1, i32 0, i32 1
    %y.load = load i32, i32* %y.addr.1
    %toString.call.1 = call i8* @toString(i32 %y.load)
    call void @println(i8* %toString.call.1)
    br label %point.printPoint.exit
point.printPoint.exit:
    ret void
}

define void @point.point(%class.point* %this) {
point.point.entry:
    %x.addr = getelementptr %class.point, %class.point* %this, i32 0, i32 0
    store i32 0, i32* %x.addr
    %y.addr = getelementptr %class.point, %class.point* %this, i32 0, i32 1
    store i32 0, i32* %y.addr
    %z.addr = getelementptr %class.point, %class.point* %this, i32 0, i32 2
    store i32 0, i32* %z.addr
    br label %point.point.exit
point.point.exit:
    ret void
}


; MainFunc
define i32 @main() {
main.entry:
    %a.addr = alloca %class.point*
    %main.ret.addr = alloca i32
    call void @_init_func()
    store i32 0, i32* %main.ret.addr
    %_malloc.call = call i8* @_malloc(i32 12)
    %bitcast = bitcast i8* %_malloc.call to %class.point*
    call void @point.point(%class.point* %bitcast)
    store %class.point* %bitcast, %class.point** %a.addr
    %a.load = load %class.point*, %class.point** %a.addr
    call void @point.printPoint(%class.point* %a.load)
    store i32 0, i32* %main.ret.addr
    br label %main.exit
main.exit:
    %main.ret.load = load i32, i32* %main.ret.addr
    ret i32 %main.ret.load
}


