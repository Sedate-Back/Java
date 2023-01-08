## Integer

###  基本类型包装类概述

将基本数据类型封装成对象的好处在于可以在对象中定义更多的功能、方法，操作该数据

常用的操作之一：用于基本数据类型与字符串之间的转换

`例如 ： “123” -> 123`

| 基本数据类型 |  包装类  |
| :----------: | :------: |
|     byte     |   Byte   |
|    short     |  Short   |
|     long     |   Long   |
|    float     |  Float   |
|    double    |  Double  |
|   boolean    | Boolean  |
|     int      | Integer  |
|     char     | Charcter |



### 如何创建Integer对象

方法： `Integer valueof()`

参数：int or string



### 关于自动装箱和自动拆箱

- 装箱：把基本数据类型转换为对应的包装类类型

- 拆箱：把包装类类型转换为对应的基本数据类型

举例：

```java
Integer i = 100; //  自动装箱
i += 200;  // i=i+200; i+200 自动拆箱;  i = i + 200 是自动装箱
```



**注意**：在使用包装类类型的时候，如果做操作，最好先判断是否为 null

我们推荐的是，**只要是对象，在使用前就必须进行不为 null 的判断**



### 如何将字符串类型的整数变成int类型的整数

- 方法：`Integer.parseInt()`
- 参数：`Integer.String`

举例

```java
String s1 = '100';
int i2 = Integer.parseInt(s1);
System.out.println(i2);

// 输出结果
i2的结果为100；同时为int类型

```



### 如何将int类型的整数变成String类型的

#### 方法1：

​	直接再int变量后+""

举例：

```java
int i1 = 100;
String s1 = i1 + "";

// 此时 s1就变成了string类型的整数
```

#### 方法2：

调用string中的valueof的方法

举例：

```java
int i1 = 100;
String s1 =  String.valueof(i1);

// 此时 s1就变成了string类型的整数
```



### 练习

将字符串数组中的数字提取出来，放到新的int数据类型的数组中

```java
public class MyIntgerDemo2 {
    public static void main(String[] args) {
        String s = "91 27 46 38 50";

        String[] stringArr = s.split(" ");

        int[] numberArr = new int[stringArr.length];

        for (int i = 0; i < stringArr.length; i++) {
            int num = Integer.parseInt(stringArr[i]);
            numberArr[i] = num;

        }
        for (int i = 0; i < numberArr.length; i++) {
            System.out.println(numberArr[i]);
        }
    }
}
```

