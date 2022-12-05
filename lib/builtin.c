const int STR_BUF_SIZE = 256;
const int INT_BUF_SIZE = 10;



//  voidType, "print", INT8Star
//         voidType, "println", INT8Star
//         voidType, "printInt", INT32
//         voidType, "printlnInt", INT32
//         INT8Star, "getString"
//         INT32, "getInt"
//         INT8Star, "toString", INT32

//         INT8Star, "_str_substring", INT8Star, INT32, INT32
//         INT32, "_str_length", INT8Star
//         INT32, "_str_ord", INT8Star, INT32
//         INT32, "_str_parseInt", INT8Star

//         INT8Star, "_malloc", INT32
//         INT8Star, "_str_concat", INT8Star, INT8Star
//         boolType, "_str_eq", INT8Star, INT8Star
//         boolType, "_str_ne", INT8Star, INT8Star
//         boolType, "_str_ult", INT8Star, INT8Star
//         boolType, "_str_ugt", INT8Star, INT8Star
//         boolType, "_str_ule", INT8Star, INT8Star
//         boolType, "_str_uge", INT8Star, INT8Star




void print(char* str) { printf("%s", str); }
void println(char* str) { printf("%s\n", str); }
void printInt(int i) { printf("%d", i); }
void printlnInt(int i) { printf("%d\n", i); }

char* getString() {
    char* str = (char*)malloc(STR_BUF_SIZE);
    scanf("%s", str);
    return str;
}

int getInt() {
    int i;
    scanf("%d", &i);
    return i;
}

char* toString(int i) {
    char* str = (char*)malloc(INT_BUF_SIZE);
    sprintf(str, "%d", i);
    return str;
}

char* _str_substring(char* str, int left, int right) {
    char* substr = (char*)malloc(right - left + 1);
    int i;
    for (i = left; i < right; i++) {
        substr[i - left] = str[i];
    }
    substr[right - left] = '\0';
    return substr;
}

int _str_length(char* str) {
    return strlen(str);
}

int _str_ord(char* str, int pos) {
    return str[pos];
}

int _str_parseInt(char* str) {
   int res;
   	sscanf(str, "%d", &res);
   	return res;
}

char* _malloc(int size) {
	return malloc(size);
}

char* _str_concat(char *a, char* b){
    char *res = malloc(strlen(a) + strlen(b) + 5);
	strcpy(res, a);
	strcat(res, b);
	return res;
}

char _str_eq(char *a, char* b){
    return strcmp(a, b) == 0;
}

char _str_ne(char *a, char* b){
    return strcmp(a, b) != 0;
}

char _str_ult(char *a, char* b){
    return strcmp(a, b) < 0;
}

char _str_ugt(char *a, char* b){
    return strcmp(a, b) > 0;
}

char _str_ule(char *a, char* b){
    return strcmp(a, b) <= 0;
}

char _str_uge(char *a, char* b){
    return strcmp(a, b) >= 0;
}

