
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

; GlobalFunc
define void @_init_func() {
_init_func.entry:
    br label %_init_func.exit
_init_func.exit:
    ret void
}

define i32 @qpow(i32 %a, i32 %p, i32 %mod) {
qpow.entry:
    %y.addr = alloca i32
    %t.addr = alloca i32
    %qpow.ret.addr = alloca i32
    %mod.addr = alloca i32
    %p.addr = alloca i32
    %a.addr = alloca i32
    store i32 %a, i32* %a.addr
    store i32 %p, i32* %p.addr
    store i32 %mod, i32* %mod.addr
    store i32 1, i32* %t.addr
    %a.load = load i32, i32* %a.addr
    store i32 %a.load, i32* %y.addr
    br label %while_cond
qpow.exit:
    %qpow.ret.load = load i32, i32* %qpow.ret.addr
    ret i32 %qpow.ret.load
while_cond:
    %p.load = load i32, i32* %p.addr
    %icmp = icmp sgt i32 %p.load, 0
    br i1 %icmp, label %while_body, label %while_exit
while_body:
    %p.load.1 = load i32, i32* %p.addr
    %and = and i32 %p.load.1, 1
    %icmp.1 = icmp eq i32 %and, 1
    br i1 %icmp.1, label %if_true, label %if_false
while_exit:
    %t.load.1 = load i32, i32* %t.addr
    store i32 %t.load.1, i32* %qpow.ret.addr
    br label %qpow.exit
if_true:
    %t.load = load i32, i32* %t.addr
    %y.load = load i32, i32* %y.addr
    %mul = mul i32 %t.load, %y.load
    %mod.load = load i32, i32* %mod.addr
    %srem = srem i32 %mul, %mod.load
    store i32 %srem, i32* %t.addr
    br label %if_exit
if_false:
    br label %if_exit
if_exit:
    %y.load.1 = load i32, i32* %y.addr
    %y.load.2 = load i32, i32* %y.addr
    %mul.1 = mul i32 %y.load.1, %y.load.2
    %mod.load.1 = load i32, i32* %mod.addr
    %srem.1 = srem i32 %mul.1, %mod.load.1
    store i32 %srem.1, i32* %y.addr
    %p.load.2 = load i32, i32* %p.addr
    %sdiv = sdiv i32 %p.load.2, 2
    store i32 %sdiv, i32* %p.addr
    br label %while_cond
}


; MainFunc
define i32 @main() {
main.entry:
    %main.ret.addr = alloca i32
    call void @_init_func()
    store i32 0, i32* %main.ret.addr
    %qpow.call = call i32 @qpow(i32 2, i32 10, i32 10000)
    %toString.call = call i8* @toString(i32 %qpow.call)
    call void @println(i8* %toString.call)
    store i32 0, i32* %main.ret.addr
    br label %main.exit
main.exit:
    %main.ret.load = load i32, i32* %main.ret.addr
    ret i32 %main.ret.load
}


