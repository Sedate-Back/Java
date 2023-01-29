## 可变参数

### 用处

方法传递的参数不确定的时候，可以使用可变参数

类似与python传参中的args



### 底层原理

其实就是传递一个arr数组或集合给方法去调用，方法体中需要循环数组再去处理变量



### 语法

```java
 public static int getSum(int...arr){
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum = sum + arr[i];
        }
        return sum;
    }

// 方法名(int... 变量名)
```



### 举例

```java
package com.itheima.MyParameter;

public class ParameterDemo1 {
    public static void main(String[] args) {

        int sum = getSum(1, 2, 3, 4, 5, 6, 7, 8, 9);
        System.out.println(sum);

    }

    public static int getSum(int...arr){
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum = sum + arr[i];
        }
        return sum;
    }
}

```



### 注意

- 当方法体还需要传递其他变量的时候，需要把可变参数变量放在最后
