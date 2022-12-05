### class and type design

imitate the LLVM value design 

reference: https://llvm.org/doxygen/classllvm_1_1Value.html







### variable address finding strategy

for variable named `A` as example:

```c++
int A;  //this A is renamed as A in scope_1
class Test{
    int A;  //this A is renamed as A_1 in scope_2
    int try(){
        int A = 0;  //this A is renamed as A_2 in scope_3
        return A;
    }
}

// scope_3.parent = scope_2
// scope_2.parent = scope_1
```

A map in `currentScope` map the variable name to its identifier.

And a map in the `IRBuilder` map the identifier to the `IRValue` that contain the address of the variable (which is usually an `allocaInst` or `GEPInst`).

#### Finding procedure

//todo





### How to get the value of an expression node of AST ?

```java
class ExpNode{
//...
    IRValue irAddress; // for some exp, we can get its address in memory
    IRValue irValue;   // every exp can have one, but not every exp should have this
}

//in irBuilder

func getExpAddress(ExpNode node);

func getExpValue(ExpNode node){
    if (node.irValue)return node.irValue;
    else{
        add load inst
            node.irValue = load from irAddress;
        return node.irValue;
    }
}

```

1. for the exp that we can get its address, we store its address in its node.
2. for the exp that do not have an address, we store its value (usually a  `binaryInst` or `funcCallInst` or others)
3. each time we want to get the address of an exp (almost always an identifier), we call 





### boolType and memBoolType treating strategy

 each time call load inst, we change the return value type from memBoolType to boolType

