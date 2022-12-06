# shellcheck disable=SC2164
cd ../../src
java Compiler -cp .

cd ../test/debug
llvm-link test.ll builtin.ll -o linked.bc

clang linked.bc -o test
./test
cd ../test_command
