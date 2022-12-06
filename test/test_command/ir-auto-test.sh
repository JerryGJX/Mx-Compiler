# shellcheck disable=SC2164
cd ../../src
java Compiler -cp .

cd ../test/debug
clang test.ll builtin.ll -o test -m32
./test

cd ../test_command
