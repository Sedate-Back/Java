package com.itheima.MyThread;

public class MyThreadDemo1 extends Thread{
    @Override
    public void run() {
        for (int i1 = 0; i1 < 100; i1++) {
            System.out.println("我爱黄思琦" + i1);
        }
    }
}
