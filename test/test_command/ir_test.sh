# shellcheck disable=SC2164
cd ../debug
clang test.ll builtin.ll -o test -m32
./test
cd ../test_command