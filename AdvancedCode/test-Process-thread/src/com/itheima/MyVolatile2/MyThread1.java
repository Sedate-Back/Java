package com.itheima.MyVolatile2;

import com.itheima.MyVolatile2.Money;

public class MyThread1 extends Thread{
    @Override
    public void run() {
        while (true){
            synchronized (Money.lock){
                if(Money.money != 100000){
                    System.out.println("结婚基金已经不是十万了");
                    break;
                }
            }
        }
    }
}
