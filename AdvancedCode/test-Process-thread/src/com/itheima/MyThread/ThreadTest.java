package com.itheima.MyThread;

public class ThreadTest {
    public static void main(String[] args) {
        MyThreadDemo1 myThreadDemo1 = new MyThreadDemo1();
        MyThreadDemo1 myThreadDemo2 = new MyThreadDemo1();

        //  开启线程要用start方法
        myThreadDemo1.start();
        myThreadDemo2.start();

    }
}
