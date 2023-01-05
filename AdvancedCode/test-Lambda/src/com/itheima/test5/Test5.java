package com.itheima.test5;

public class Test5 {
    public static void main(String[] args) {
        // 用最初始的Lambda方法
        useInter((double a,double b)->{return a+b;});

        // 简化Lambda方法
        useInter((a, b) -> a+b );
        // 简化条件 前面的括号是传参，参数的类型可以省略；
        // 当参数为1个的时候，括号可以省
        // 当没有参数的时候，括号需要留下
        // 执行语句只有一句的时候，可以省略 大括号{} ，分号; 和 return;
    }
    public static void useInter(Inter inter){
        double res = inter.method(12.3, 22.3);
        System.out.println(res);
    }
}

interface Inter{
    double method(double a, double b);
}