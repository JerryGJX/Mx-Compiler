package IR.Value;

import IR.Type.IntegerType;

public interface IRDefine {
    IntegerType INT8 = new IntegerType(8);
    IntegerType INT16 = new IntegerType(16);
    IntegerType INT32 = new IntegerType(32);
    IntegerType INT64 = new IntegerType(64);

    Integer POINTER_SIZE = 4;//risc-v 32

    String GLOBAL_PREFIX = "@";
    String LOCAL_PREFIX = "%";
    String STRING_PREFIX = "str";
    String LABEL_PREFIX = "label";
    String FUNCTION_PREFIX = "func";
    String GLOBAL_VAR_PREFIX = "global";

    String NULLPTR_IDENTIFIER = "null";
}
