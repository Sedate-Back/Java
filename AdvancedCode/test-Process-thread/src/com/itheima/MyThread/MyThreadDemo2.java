package com.itheima.MyThread;

public class MyThreadDemo2 implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName()+ ":" + i);
        }
    }
}
