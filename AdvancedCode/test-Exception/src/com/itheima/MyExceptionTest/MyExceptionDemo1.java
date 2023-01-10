package com.itheima.MyExceptionTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyExceptionDemo1 {
    // throws 抛出异常
    public static void main(String[] args) throws ParseException {
        System.out.println("开始");
        method();
        method2();

        System.out.println("结束");
    }

    //编译时异常
    public static void method2() throws ParseException {
        String s = "2048-08-09";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = sdf.parse(s);
        System.out.println(d);
    }

    //运行时异常
    public static void method() throws ArrayIndexOutOfBoundsException {
        int[] arr = {1, 2, 3};
        System.out.println(arr[3]); // 索引超出异常
    }
}
