package com.itheima.MyVolatile2;

import com.itheima.MyVolatile2.Money;

public class MyThread2 extends Thread{
    @Override
    public void run() {
        synchronized (Money.lock){
            try{
                Thread.sleep(10);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            Money.money = 90000;
        }
    }
}
