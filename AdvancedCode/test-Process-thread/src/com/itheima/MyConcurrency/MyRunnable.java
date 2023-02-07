package com.itheima.MyConcurrency;

import java.util.concurrent.Semaphore;

public class MyRunnable implements Runnable{
    private Semaphore semaphore = new Semaphore(2);
    @Override
    public void run() {
        try {
            semaphore.acquire();
            System.out.println("Start");
            Thread.sleep(2000);
            System.out.println("Out!");
            semaphore.release();


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
