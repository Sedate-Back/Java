package com.itheima.MyConcurrency;

import java.util.concurrent.CountDownLatch;

public class Chile3 extends Thread{
    private CountDownLatch countDownLatch;
    public Chile3(CountDownLatch countDownLatch){
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println(getName() + "在吃第" + i + "个饺子");
        }
        countDownLatch.countDown();
    }
}
