package com.itheima.MyThread;

import java.util.concurrent.Callable;

public class MyThreadDemo4 implements Callable<String> {
    @Override
    public String call() throws Exception {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);

        }
        return "Thread Over！";
    }
}
