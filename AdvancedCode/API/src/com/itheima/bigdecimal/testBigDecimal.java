package com.itheima.bigdecimal;

import java.math.BigDecimal;

public class testBigDecimal {
    public static void main(String[] args) {
        BigDecimal bd1 = new BigDecimal(10.1);
        BigDecimal bd2 = new BigDecimal("0.1");

        // BigDecimal类的构造方法，参数可以是 double 或 String
        // 如果用double类传递进行计算，结果会是很长的结果
        // 用String传值，就能计算出精确的值
        // BigDecimal的加减乘除

        BigDecimal bd3 = new BigDecimal("0.1");
        BigDecimal bd4 = new BigDecimal("2");
        //    加
        BigDecimal add = bd3.add(bd4);

        // 减
        BigDecimal sub = bd3.subtract(bd4);

        // 乘
        BigDecimal mult = bd3.multiply(bd4);

        // 除
        BigDecimal divide = bd3.divide(bd4);

        System.out.println("加法的结果"+ add);
        System.out.println("减法的结果"+ sub);
        System.out.println("乘法的结果"+ mult);
        System.out.println("除法的结果"+ divide);

//        加法的结果2.1
//        减法的结果-1.9
//        乘法的结果0.2
//        除法的结果0.05


        // 如果两个BigDecimal对象除不尽，例如10/3 = 3.33333 循环，
        // 那么就需要用到 divide的拓展用法
        BigDecimal bd5 = new BigDecimal("0.3");
        BigDecimal round_up  = bd4.divide(bd5, 2, BigDecimal.ROUND_UP); // 进一法
        BigDecimal round_half_up  = bd4.divide(bd5, 2, BigDecimal.ROUND_HALF_UP); // 四舍五入法
        BigDecimal round_floor  = bd4.divide(bd5, 2, BigDecimal.ROUND_FLOOR); // 去尾法
        System.out.println("round_up:" + round_up);
        System.out.println("round_half_up:" + round_half_up);
        System.out.println("round_floor：" + round_floor);


    }
}
