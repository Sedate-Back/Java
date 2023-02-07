package com.itheima.MyConcurrency;

/*
   使用场景；
     当某一条线程需要等待其他线程结束后，再开始运行，就需要用CountDownLatch
 */


import java.util.concurrent.CountDownLatch;

public class MyCountDownLatch {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(3);

        Mother mother = new Mother(countDownLatch);
        mother.start();

        Chile1 chile1 = new Chile1(countDownLatch);
        chile1.setName("xiaoming");

        Chile2 chile2 = new Chile2(countDownLatch);
        chile2.setName("xiaohong");

        Chile3 chile3 = new Chile3(countDownLatch);
        chile3.setName("xiaogang");

        chile1.start();
        chile2.start();
        chile3.start();

    }

}
