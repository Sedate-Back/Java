package com.itheima.MyThread;

import java.util.concurrent.Callable;

public class MyThreadDemo3 implements Callable<String> {
    // 用Callable类，需要设置泛性，泛性的类型和返回值的类型一致，会在程序结束后调用返回值属性
    // 如果不需要返回值，就设置null
    @Override
    public String call() throws Exception {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName()+ ":"+ i);
        }
        String s = "真舒服";
        return s;
    }
}
