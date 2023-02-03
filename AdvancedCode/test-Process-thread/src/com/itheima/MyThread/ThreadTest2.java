package com.itheima.MyThread;

public class ThreadTest2 {
    public static void main(String[] args) {
        // 创建Runnable实现对象
        MyThreadDemo2 myThreadDemo2 = new MyThreadDemo2();

        // 创建线程对象，并将Runnable对象传递给线程对象
        Thread t1 = new Thread(myThreadDemo2,"黄思琦");
        Thread t2 = new Thread(myThreadDemo2,"张泽鸿");

        // 启动线程
        t1.start();
        t2.start();

    }
}
