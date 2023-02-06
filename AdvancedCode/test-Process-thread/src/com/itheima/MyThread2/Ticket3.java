package com.itheima.MyThread2;

import java.util.concurrent.locks.ReentrantLock;

public class Ticket3 implements Runnable{
    private int ticket = 100;
    private Object obj = new Object();
    private ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true){
            try {
                lock.lock();
                if (ticket <=0){
                    break;
                }else {
                    Thread.sleep(50);
                    ticket--;
                    System.out.println(Thread.currentThread().getName() + "在卖票,还剩下" + ticket + "张票");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }

    }

}
