package com.itheima.MyThread3;
/*
  为中介：用来判断消费者或者生产者线程如何分配资源
 */
public class Desk {
    public static boolean flag = false;

    public static int count = 10;

    // 锁对象
    public static final Object lock = new Object();


}
