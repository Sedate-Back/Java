package com.itheima.MyConcurrency;

import java.util.concurrent.CountDownLatch;

public class Chile2 extends Thread{
    private CountDownLatch countDownLatch;
    public Chile2(CountDownLatch countDownLatch){
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        for (int i = 0; i < 15; i++) {
            System.out.println(getName() + "在吃第" + i + "个饺子");
        }
        countDownLatch.countDown();
    }
}
