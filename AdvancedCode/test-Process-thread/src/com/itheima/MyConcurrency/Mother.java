package com.itheima.MyConcurrency;

import java.util.concurrent.CountDownLatch;

public class Mother extends Thread{
    private CountDownLatch countDownLatch;
    public Mother(CountDownLatch countDownLatch){
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try{
            countDownLatch.await();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("妈妈在收拾碗筷");
    }
}
