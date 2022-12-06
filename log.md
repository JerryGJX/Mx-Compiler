# Log

### 10/15 

Working on `Utils.scope`

`Todo`:

- replace the `public Map<String, Integer> VariableTable;` with `public Map<String, Type> VariableTable;`（line 9）
- replace `Integer` related part.



### IRDebug

```c
int[][] a=new int[30][30];
int main()
{
	return 0;
}

```

problem: not dealing with the `tmp var` define in `init_func`



 ```c
 class vector{
 	int[] data;
 //....
 	int getDim(){
 		if (data == null) return 0;
 		return data.size();
 	}
 
 	string tostring(){
 		string temp = "( ";
 		if (getDim() > 0) {
 			temp = temp + toString(data[0]);
 		}
 		int i;
 		for (i = 1; i < getDim(); ++i) {
 			temp = temp + ", " + toString(data[i]);
 		}
 		temp = temp + " )";
 		return temp;
 	}
 
 };
 ```

problem: not dealing with the member function call within the scope of the class





```java
public String getRawString(String str) {
        return str.replace("\\n", "\n")
                .replace("\\t", "\t")
                .replace("\\\\", "\\")
                .replace("\\\"", "\"");
    }
```

problem: do not have this part of code to deal with the initial string.





```c
for (i = 2; ; ++i ) {
		if ( i > N ) break;
		if ( check[i] ) {
			P[++M] = i;
			phi[i] = i - 1;
		}
		int k = i;
		int i;
	}
```

problem:  I let the `forCondBlock`, `forStepBlock`, and the `forBodyBlock` share the same scope, I am a idiot.
