package IR.Value;

import FrontEnd.BuiltInElements;
import IR.Type.IntegerType;
import IR.Value.User.Constant.IRIntConstant;

public interface IRDefine {
    IntegerType INT8 = new IntegerType(8);
    IntegerType INT16 = new IntegerType(16);
    IntegerType INT32 = new IntegerType(32);
    IntegerType INT64 = new IntegerType(64);

    Integer POINTER_SIZE = 4;//risc-v 32

    Integer STRING_ALIGN = 1;//?

    String GLOBAL_PREFIX = "@";
    String LOCAL_PREFIX = "%";
    String STRING_PREFIX = "str";
    String LABEL_PREFIX = "label";
    String FUNCTION_PREFIX = "func";
    String GLOBAL_VAR_PREFIX = "global";


    IRIntConstant MAIN_DEFAULT_RETURN = new IRIntConstant(0);

    String LLVM_ALLOCA_INST = "alloca";
    String LLVM_LOAD_INST = "load";
    String LLVM_STORE_INST = "store";
    String LLVM_RET_INST = "ret";

    String LLVM_GEP_INST = "getelementptr";

    String LLVM_BR_INST = "br";

    String NULLPTR_IDENTIFIER = "null";

    String LLVM_INIT_FUNCTION = "_init_func";
}
