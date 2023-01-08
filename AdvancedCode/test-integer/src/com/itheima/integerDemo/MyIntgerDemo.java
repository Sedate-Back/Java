package com.itheima.integerDemo;

public class MyIntgerDemo {
    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE); // int的最大值
        System.out.println(Integer.MIN_VALUE); // int的最小值


        //    Integer

        // Integer构造方法
        Integer i1 = new Integer(100);
        Integer i2 = new Integer("100");
        // valueof 方法
        Integer integer3 =Integer.valueOf("200");
        Integer integer4 = Integer.valueOf(200);
        // 更简单的方法
        Integer i5 = 100;

        // jdk的特性 --- 自动装箱
        // 装箱 把一个基本数据类型 变量对应的包装类
        // 自动 java 底层会帮我们自动的掉哟个valueof的方法
        // jdk的特性 --- 自动拆箱
        // 拆箱 把一个包装类型 变成对应的基本数据类型

        int i6 = i5;
        i6 += 200;
        System.out.println(i6);
        // 上面这段代码的执行流程， 1. 将i5自动拆箱给i6；2. i6 +200 得到新的i6；3. 输出i6


    }
}
