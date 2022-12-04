# shellcheck disable=SC2164
cd ../debug
llvm-link test.ll builtin.ll -o linked.bc
clang linked.bc -o test
./test
cd ../test_command