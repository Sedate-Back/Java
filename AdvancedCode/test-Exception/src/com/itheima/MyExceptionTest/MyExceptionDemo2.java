package com.itheima.MyExceptionTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyExceptionDemo2 {
    // throw 抛出异常
    public static void main(String[] args) {
//        int [] arr = {1,2,3,4,5};
        int [] arr = null;
        printArr(arr);//就会 接收到一个异常.
        //我们还需要自己处理一下异常.
    }

    private static void printArr(int[] arr) {
        if(arr == null){
            //调用者知道成功打印了吗?
            System.out.println("参数不能为null");
            throw new NullPointerException(); //当参数为null的时候
            //手动创建了一个异常对象,抛给了调用者,产生了一个异常
        }else{
            for (int i = 0; i < arr.length; i++) {
                System.out.println(arr[i]);
            }
        }
    }
}
