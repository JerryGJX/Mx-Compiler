# coding:utf-8
import os
import time

command = "cp {code_file} ../debug/test.mx && sh ir-auto-test.sh < {input_file} > {output_file}"

judge_list = open("../testCases/testCase/codegen/judgelist.txt").readlines()

cnt = 0

fail_collect = list()

os.system("cd ../../src/ && javac Compiler.java && cd ../test/test_command")

for judge in judge_list:
    cnt += 1

    code_file = judge.replace("\n", "").replace("./", "../testCases/testCase/codegen/")
    input_file = "../irtestspace/input.txt"
    output_file = "../irtestspace/output.txt"
    std_file = "../irtestspace/std.txt"

    input_fp = open(input_file, "w")
    output_fp = open(output_file, "w")
    std_fp = open(std_file, "w")

    fp = open(code_file, encoding='UTF-8')

    print(code_file)

    lines = fp.readlines()

    input_start = False
    std_start = False

    for i in range(len(lines)):
        if lines[i].find("= output =") > 0:
            std_start = True
        elif lines[i].find("= input =") > 0:
            input_start = True
        elif lines[i].find("= end =") > 0:
            input_start = False
            std_start = False
        elif input_start:
            input_fp.write(lines[i])
        elif std_start:
            std_fp.write(lines[i])

    input_fp.close()
    output_fp.close()
    std_fp.close()

    print("\033[34m Loading finish. Start to run LLVM IR.")

    os.system(command.format(code_file=code_file,
                             input_file=input_file, output_file=output_file))

    wrap = os.popen(
        "diff {file1} {file2} -w -B".format(file1=output_file, file2=std_file))
    info = wrap.readlines()

    if len(info) == 0:
        print("\033[32m[Success] [test]: in {testpoint} \033[0m".format(
            testpoint=code_file + ", point " + str(cnt)))
    else:
        print("\033[31m[Failed] [test]: in {testpoint} \033[0m".format(
            testpoint=code_file + ", point " + str(cnt)))
        # print("[info]: ", info)
        fail_collect.append(code_file)

    time.sleep(1)

print(fail_collect)

# os.system("rm ./**/*.class")
