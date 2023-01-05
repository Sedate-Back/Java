## API

### math

部分使用

```java
package com.itheima.math;

public class mathDemo {
    public static void main(String[] args) {
        // abs 的使用： 绝对值
        int abs = Math.abs(10);
        System.out.println(abs);

        // ceil 向上取整
        double ceil = Math.ceil(10.1);
        System.out.println(ceil);
        // 输出结果： 11

        // floor 向下取整
        double floor = Math.floor(10.1);
        System.out.println(floor);
        // 输出结果： 10

        // round 四舍五入
        double round_1 = Math.round(10.1);
        double round_2 = Math.round(10.5);
        System.out.println(round_1);
        System.out.println(round_2);
        // 输出结果 10  、 11

        // max 比较两数大小 输出较大数
        double max = Math.max(10, 20);
        System.out.println(max);
        // 20

        // min 与上相反

        // random 取 0.0 ~ 1.0 之间的数 是随机的
        double random = Math.random();
        System.out.println(random);

    }
}
```



### System

```java
public class systemDemo {
    public static void main(String[] args) {
        System.out.println("10");
         //        System.exit(0); // 会停止到这一行，后面的20不输出
        System.out.println("20");


        long start  = System.currentTimeMillis(); // 获取当前时间，以毫秒计算


        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = new int[10];

        // arraycopy(数据源数组，起始索引，目的地数组，起始索引，拷贝个数)
        System.arraycopy(arr1, 0, arr2, 0, arr1.length);
        for (int i = 0; i < arr2.length; i++) {
            System.out.println(arr2[i]);
        }
        // 输出结果 1 2 3 4 5 0 0 0 0 0
    }
}

```



### Object

#### 概述

每个类都可以将Object作为父类。所有类都直接或者间接的继承自该类

构造方法： `publicObject()`

回想面向对象中，为什么说子类的构造方法默认访问的是父类的无参构造方法?因为它们的顶级父类只有无参构造方法



#### 结论

- Object类是所有类的直接或者间接父类
- 直接打印一个对象就是打印这个对象的toString方法的返回值
- Object类的toString方法得到的是对象的地址值
- 我们一般会对toString方法进行重写



#### toString衍生的面试题

```java
public class Demo{
    public static void main(String[] args) {
        Student1 s1 = new Student("zhangsan", 23);
        Student1 s2 = new Student("zhangsan", 23);
        
        System,out.println(s1 == s2); // 比较底层的地址值
        System,out.println(s1.equals(s2)); 
        // 因为Student这个类里没有equals，但是父类Object有，Object是比较地址值，所以都是false
}}

// 输出结果
false
false
```



#### Object常用方法

- toString   ： 返回对象的字符串表示形式。建议所有子类重写该方法，自动生成
- equals(另一个对象)  : 比较是否相等，默认比较地址值，重写可以比较内容，自动生成



### Objects 

![image-20230106000507034](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20230106000507034.png)

