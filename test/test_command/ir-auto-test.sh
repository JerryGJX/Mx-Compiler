# shellcheck disable=SC2164
cd ../../src
java Compiler

cd ../test/debug
llvm-link test.ll builtin.ll -o linked.bc
clang linked.bc -o test
./test