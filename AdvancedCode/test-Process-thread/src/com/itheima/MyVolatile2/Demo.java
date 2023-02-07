package com.itheima.MyVolatile2;

public class Demo {
    public static void main(String[] args) {
        MyThread1 t1 = new MyThread1();
        t1.setName("t1");
        t1.start();

        MyThread2 t2 = new MyThread2();
        t2.setName("t2");
        t2.start();
    }
}
