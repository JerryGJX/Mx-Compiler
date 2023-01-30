; ModuleID = 'builtin.c'
source_filename = "builtin.c"
target datalayout = "e-m:e-p:32:32-i64:64-n32-S128"
target triple = "riscv32"

@STR_BUF_SIZE = dso_local constant i32 256, align 4
@INT_BUF_SIZE = dso_local constant i32 20, align 4
@.str = private unnamed_addr constant [3 x i8] c"%s\00", align 1
@.str.1 = private unnamed_addr constant [4 x i8] c"%s\0A\00", align 1
@.str.2 = private unnamed_addr constant [3 x i8] c"%d\00", align 1
@.str.3 = private unnamed_addr constant [4 x i8] c"%d\0A\00", align 1

; Function Attrs: noinline nounwind optnone
define dso_local void @print(i8* noundef %0) #0 {
  %2 = alloca i8*, align 4
  store i8* %0, i8** %2, align 4
  %3 = load i8*, i8** %2, align 4
  %4 = call i32 (i8*, ...) @printf(i8* noundef getelementptr inbounds ([3 x i8], [3 x i8]* @.str, i32 0, i32 0), i8* noundef %3)
  ret void
}

declare dso_local i32 @printf(i8* noundef, ...) #1

; Function Attrs: noinline nounwind optnone
define dso_local void @println(i8* noundef %0) #0 {
  %2 = alloca i8*, align 4
  store i8* %0, i8** %2, align 4
  %3 = load i8*, i8** %2, align 4
  %4 = call i32 (i8*, ...) @printf(i8* noundef getelementptr inbounds ([4 x i8], [4 x i8]* @.str.1, i32 0, i32 0), i8* noundef %3)
  ret void
}

; Function Attrs: noinline nounwind optnone
define dso_local void @printInt(i32 noundef %0) #0 {
  %2 = alloca i32, align 4
  store i32 %0, i32* %2, align 4
  %3 = load i32, i32* %2, align 4
  %4 = call i32 (i8*, ...) @printf(i8* noundef getelementptr inbounds ([3 x i8], [3 x i8]* @.str.2, i32 0, i32 0), i32 noundef %3)
  ret void
}

; Function Attrs: noinline nounwind optnone
define dso_local void @printlnInt(i32 noundef %0) #0 {
  %2 = alloca i32, align 4
  store i32 %0, i32* %2, align 4
  %3 = load i32, i32* %2, align 4
  %4 = call i32 (i8*, ...) @printf(i8* noundef getelementptr inbounds ([4 x i8], [4 x i8]* @.str.3, i32 0, i32 0), i32 noundef %3)
  ret void
}

; Function Attrs: noinline nounwind optnone
define dso_local i8* @getString() #0 {
  %1 = alloca i8*, align 4
  %2 = call i8* @malloc(i32 noundef 256)
  store i8* %2, i8** %1, align 4
  %3 = load i8*, i8** %1, align 4
  %4 = call i32 (i8*, ...) @scanf(i8* noundef getelementptr inbounds ([3 x i8], [3 x i8]* @.str, i32 0, i32 0), i8* noundef %3)
  %5 = load i8*, i8** %1, align 4
  ret i8* %5
}

declare dso_local i8* @malloc(i32 noundef) #1

declare dso_local i32 @scanf(i8* noundef, ...) #1

; Function Attrs: noinline nounwind optnone
define dso_local i32 @getInt() #0 {
  %1 = alloca i32, align 4
  %2 = call i32 (i8*, ...) @scanf(i8* noundef getelementptr inbounds ([3 x i8], [3 x i8]* @.str.2, i32 0, i32 0), i32* noundef %1)
  %3 = load i32, i32* %1, align 4
  ret i32 %3
}

; Function Attrs: noinline nounwind optnone
define dso_local i8* @toString(i32 noundef %0) #0 {
  %2 = alloca i32, align 4
  %3 = alloca i8*, align 4
  store i32 %0, i32* %2, align 4
  %4 = call i8* @malloc(i32 noundef 256)
  store i8* %4, i8** %3, align 4
  %5 = load i8*, i8** %3, align 4
  %6 = load i32, i32* %2, align 4
  %7 = call i32 (i8*, i8*, ...) @sprintf(i8* noundef %5, i8* noundef getelementptr inbounds ([3 x i8], [3 x i8]* @.str.2, i32 0, i32 0), i32 noundef %6)
  %8 = load i8*, i8** %3, align 4
  ret i8* %8
}

declare dso_local i32 @sprintf(i8* noundef, i8* noundef, ...) #1

; Function Attrs: noinline nounwind optnone
define dso_local i8* @_str_substring(i8* noundef %0, i32 noundef %1, i32 noundef %2) #0 {
  %4 = alloca i8*, align 4
  %5 = alloca i32, align 4
  %6 = alloca i32, align 4
  %7 = alloca i8*, align 4
  %8 = alloca i32, align 4
  store i8* %0, i8** %4, align 4
  store i32 %1, i32* %5, align 4
  store i32 %2, i32* %6, align 4
  %9 = load i32, i32* %6, align 4
  %10 = load i32, i32* %5, align 4
  %11 = sub nsw i32 %9, %10
  %12 = add nsw i32 %11, 1
  %13 = call i8* @malloc(i32 noundef %12)
  store i8* %13, i8** %7, align 4
  %14 = load i32, i32* %5, align 4
  store i32 %14, i32* %8, align 4
  br label %15

15:                                               ; preds = %29, %3
  %16 = load i32, i32* %8, align 4
  %17 = load i32, i32* %6, align 4
  %18 = icmp slt i32 %16, %17
  br i1 %18, label %19, label %32

19:                                               ; preds = %15
  %20 = load i8*, i8** %4, align 4
  %21 = load i32, i32* %8, align 4
  %22 = getelementptr inbounds i8, i8* %20, i32 %21
  %23 = load i8, i8* %22, align 1
  %24 = load i8*, i8** %7, align 4
  %25 = load i32, i32* %8, align 4
  %26 = load i32, i32* %5, align 4
  %27 = sub nsw i32 %25, %26
  %28 = getelementptr inbounds i8, i8* %24, i32 %27
  store i8 %23, i8* %28, align 1
  br label %29

29:                                               ; preds = %19
  %30 = load i32, i32* %8, align 4
  %31 = add nsw i32 %30, 1
  store i32 %31, i32* %8, align 4
  br label %15, !llvm.loop !5

32:                                               ; preds = %15
  %33 = load i8*, i8** %7, align 4
  %34 = load i32, i32* %6, align 4
  %35 = load i32, i32* %5, align 4
  %36 = sub nsw i32 %34, %35
  %37 = getelementptr inbounds i8, i8* %33, i32 %36
  store i8 0, i8* %37, align 1
  %38 = load i8*, i8** %7, align 4
  ret i8* %38
}

; Function Attrs: noinline nounwind optnone
define dso_local i32 @_str_length(i8* noundef %0) #0 {
  %2 = alloca i8*, align 4
  store i8* %0, i8** %2, align 4
  %3 = load i8*, i8** %2, align 4
  %4 = call i32 @strlen(i8* noundef %3)
  ret i32 %4
}

declare dso_local i32 @strlen(i8* noundef) #1

; Function Attrs: noinline nounwind optnone
define dso_local i32 @_str_ord(i8* noundef %0, i32 noundef %1) #0 {
  %3 = alloca i8*, align 4
  %4 = alloca i32, align 4
  store i8* %0, i8** %3, align 4
  store i32 %1, i32* %4, align 4
  %5 = load i8*, i8** %3, align 4
  %6 = load i32, i32* %4, align 4
  %7 = getelementptr inbounds i8, i8* %5, i32 %6
  %8 = load i8, i8* %7, align 1
  %9 = zext i8 %8 to i32
  ret i32 %9
}

; Function Attrs: noinline nounwind optnone
define dso_local i32 @_str_parseInt(i8* noundef %0) #0 {
  %2 = alloca i8*, align 4
  %3 = alloca i32, align 4
  store i8* %0, i8** %2, align 4
  %4 = load i8*, i8** %2, align 4
  %5 = call i32 (i8*, i8*, ...) @sscanf(i8* noundef %4, i8* noundef getelementptr inbounds ([3 x i8], [3 x i8]* @.str.2, i32 0, i32 0), i32* noundef %3)
  %6 = load i32, i32* %3, align 4
  ret i32 %6
}

declare dso_local i32 @sscanf(i8* noundef, i8* noundef, ...) #1

; Function Attrs: noinline nounwind optnone
define dso_local i8* @_malloc(i32 noundef %0) #0 {
  %2 = alloca i32, align 4
  store i32 %0, i32* %2, align 4
  %3 = load i32, i32* %2, align 4
  %4 = call i8* @malloc(i32 noundef %3)
  ret i8* %4
}

; Function Attrs: noinline nounwind optnone
define dso_local i8* @_str_concat(i8* noundef %0, i8* noundef %1) #0 {
  %3 = alloca i8*, align 4
  %4 = alloca i8*, align 4
  %5 = alloca i8*, align 4
  store i8* %0, i8** %3, align 4
  store i8* %1, i8** %4, align 4
  %6 = load i8*, i8** %3, align 4
  %7 = call i32 @strlen(i8* noundef %6)
  %8 = load i8*, i8** %4, align 4
  %9 = call i32 @strlen(i8* noundef %8)
  %10 = add i32 %7, %9
  %11 = add i32 %10, 1
  %12 = call i8* @malloc(i32 noundef %11)
  store i8* %12, i8** %5, align 4
  %13 = load i8*, i8** %5, align 4
  %14 = load i8*, i8** %3, align 4
  %15 = call i8* @strcpy(i8* noundef %13, i8* noundef %14)
  %16 = load i8*, i8** %5, align 4
  %17 = load i8*, i8** %4, align 4
  %18 = call i8* @strcat(i8* noundef %16, i8* noundef %17)
  %19 = load i8*, i8** %5, align 4
  ret i8* %19
}

declare dso_local i8* @strcpy(i8* noundef, i8* noundef) #1

declare dso_local i8* @strcat(i8* noundef, i8* noundef) #1

; Function Attrs: noinline nounwind optnone
define dso_local zeroext i8 @_str_eq(i8* noundef %0, i8* noundef %1) #0 {
  %3 = alloca i8*, align 4
  %4 = alloca i8*, align 4
  store i8* %0, i8** %3, align 4
  store i8* %1, i8** %4, align 4
  %5 = load i8*, i8** %3, align 4
  %6 = load i8*, i8** %4, align 4
  %7 = call i32 @strcmp(i8* noundef %5, i8* noundef %6)
  %8 = icmp eq i32 %7, 0
  %9 = zext i1 %8 to i32
  %10 = trunc i32 %9 to i8
  ret i8 %10
}

declare dso_local i32 @strcmp(i8* noundef, i8* noundef) #1

; Function Attrs: noinline nounwind optnone
define dso_local zeroext i8 @_str_ne(i8* noundef %0, i8* noundef %1) #0 {
  %3 = alloca i8*, align 4
  %4 = alloca i8*, align 4
  store i8* %0, i8** %3, align 4
  store i8* %1, i8** %4, align 4
  %5 = load i8*, i8** %3, align 4
  %6 = load i8*, i8** %4, align 4
  %7 = call i32 @strcmp(i8* noundef %5, i8* noundef %6)
  %8 = icmp ne i32 %7, 0
  %9 = zext i1 %8 to i32
  %10 = trunc i32 %9 to i8
  ret i8 %10
}

; Function Attrs: noinline nounwind optnone
define dso_local zeroext i8 @_str_ult(i8* noundef %0, i8* noundef %1) #0 {
  %3 = alloca i8*, align 4
  %4 = alloca i8*, align 4
  store i8* %0, i8** %3, align 4
  store i8* %1, i8** %4, align 4
  %5 = load i8*, i8** %3, align 4
  %6 = load i8*, i8** %4, align 4
  %7 = call i32 @strcmp(i8* noundef %5, i8* noundef %6)
  %8 = icmp slt i32 %7, 0
  %9 = zext i1 %8 to i32
  %10 = trunc i32 %9 to i8
  ret i8 %10
}

; Function Attrs: noinline nounwind optnone
define dso_local zeroext i8 @_str_ugt(i8* noundef %0, i8* noundef %1) #0 {
  %3 = alloca i8*, align 4
  %4 = alloca i8*, align 4
  store i8* %0, i8** %3, align 4
  store i8* %1, i8** %4, align 4
  %5 = load i8*, i8** %3, align 4
  %6 = load i8*, i8** %4, align 4
  %7 = call i32 @strcmp(i8* noundef %5, i8* noundef %6)
  %8 = icmp sgt i32 %7, 0
  %9 = zext i1 %8 to i32
  %10 = trunc i32 %9 to i8
  ret i8 %10
}

; Function Attrs: noinline nounwind optnone
define dso_local zeroext i8 @_str_ule(i8* noundef %0, i8* noundef %1) #0 {
  %3 = alloca i8*, align 4
  %4 = alloca i8*, align 4
  store i8* %0, i8** %3, align 4
  store i8* %1, i8** %4, align 4
  %5 = load i8*, i8** %3, align 4
  %6 = load i8*, i8** %4, align 4
  %7 = call i32 @strcmp(i8* noundef %5, i8* noundef %6)
  %8 = icmp sle i32 %7, 0
  %9 = zext i1 %8 to i32
  %10 = trunc i32 %9 to i8
  ret i8 %10
}

; Function Attrs: noinline nounwind optnone
define dso_local zeroext i8 @_str_uge(i8* noundef %0, i8* noundef %1) #0 {
  %3 = alloca i8*, align 4
  %4 = alloca i8*, align 4
  store i8* %0, i8** %3, align 4
  store i8* %1, i8** %4, align 4
  %5 = load i8*, i8** %3, align 4
  %6 = load i8*, i8** %4, align 4
  %7 = call i32 @strcmp(i8* noundef %5, i8* noundef %6)
  %8 = icmp sge i32 %7, 0
  %9 = zext i1 %8 to i32
  %10 = trunc i32 %9 to i8
  ret i8 %10
}

attributes #0 = { noinline nounwind optnone "frame-pointer"="all" "min-legal-vector-width"="0" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-features"="+a,+c,+m" }
attributes #1 = { "frame-pointer"="all" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-features"="+a,+c,+m" }

!llvm.module.flags = !{!0, !1, !2, !3}
!llvm.ident = !{!4}

!0 = !{i32 1, !"wchar_size", i32 4}
!1 = !{i32 1, !"target-abi", !"ilp32"}
!2 = !{i32 7, !"frame-pointer", i32 2}
!3 = !{i32 1, !"SmallDataLimit", i32 8}
!4 = !{!"Ubuntu clang version 14.0.0-1ubuntu1"}
!5 = distinct !{!5, !6}
!6 = !{!"llvm.loop.mustprogress"}
