package IR.Value;

import FrontEnd.BuiltInElements;
import IR.Type.*;
import IR.Value.User.Constant.IRIntConstant;

public interface IRDefine {
    BasicType INT8 = new IntegerType(8);
    BasicType INT8Star = new PointerType(INT8);
    BasicType INT32 = new IntegerType(32);
    BasicType INT32Star = new PointerType(INT32);
    BasicType INT64 = new IntegerType(64);
    BasicType stringType = new PointerType(new IntegerType(8));
    BasicType boolType = new BoolType();
    //    BasicType allocBoolType = new IntegerType(8);
    BasicType voidType = new VoidType();
    BasicType nullType = new PointerType(null);


    Integer POINTER_SIZE = 4;//risc-v 32

    Integer STRING_ALIGN = 1;//?

    String GLOBAL_PREFIX = "@";
    String LOCAL_PREFIX = "%";
    String STRING_PREFIX = "str";
    String LABEL_PREFIX = "label";
    String FUNCTION_PREFIX = "func";
    String GLOBAL_VAR_PREFIX = "global";

//    String CONSTANT_PREFIX = "constant";

    String FOR_COND_LABEL = "for_cond";
    String FOR_BODY_LABEL = "for_body";
    String FOR_STEP_LABEL = "for_step";
    String FOR_EXIT_LABEL = "for_exit";

    String WHILE_COND_LABEL = "while_cond";
    String WHILE_BODY_LABEL = "while_body";
    String WHILE_EXIT_LABEL = "while_exit";

    String IF_TRUE_LABEL = "if_true";
    String IF_FALSE_LABEL = "if_false";
    String IF_EXIT_LABEL = "if_exit";

//    String THIS_POINTER = "this_ptr";



    IRIntConstant MAIN_DEFAULT_RETURN = new IRIntConstant(0);

    String LLVM_ALLOCA_INST = "alloca";
    String LLVM_BITCAST_INST = "bitcast";
    String LLVM_BR_INST = "br";

    String LLVM_CALL_INST = "call";
    String LLVM_GEP_INST = "getelementptr";
    String LLVM_ICMP_INST = "icmp";
    String LLVM_LOAD_INST = "load";
    String LLVM_RET_INST = "ret";
    String LLVM_STORE_INST = "store";
    String LLVM_TRUNC_INST = "trunc";
    String LLVM_ZEXT_INST = "zext";


    String LLVM_LABEL = "label";

    String NULLPTR_IDENTIFIER = "null";

    String LLVM_INIT_FUNCTION = "_init_func";
    String LLVM_STRING_IDENTIFIER = "strConst";
}
