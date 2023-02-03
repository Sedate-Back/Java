package com.itheima.MyThread;

import java.util.concurrent.FutureTask;

public class ThreadTest4 {
    public static void main(String[] args) {


        MyThreadDemo4 mc = new MyThreadDemo4();

        FutureTask ft = new FutureTask(mc);

        Thread t = new Thread(ft);


        t.setName("kiss");

        // 设置线程优先级
        t.setPriority(1);
        t.start();

        MyThreadDemo4 mc2 = new MyThreadDemo4();

        FutureTask ft2 = new FutureTask(mc2);

        Thread t2 = new Thread(ft2);


        t2.setName("Miss");

        // 设置线程优先级
        t2.setPriority(10);


        t2.start();

    }


}
