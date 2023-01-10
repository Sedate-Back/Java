package com.itheima.MyExceptionTest;

public class MyExceptionDemo3 {
    // try-catch 方式处理异常
    public static void main(String[] args) {
        System.out.println("开始");
        method();
        System.out.println("结束");
    }

    public static void method() {
        try {
            int[] arr = {1, 2, 3};
            System.out.println(arr[3]); // 因为arr【3】 是不存在的，所以JVM会报错，直接去执行catch的内容
            System.out.println("这里能够访问到吗");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("你访问的数组索引不存在，请回去修改为正确的索引");
        }
    }
}
