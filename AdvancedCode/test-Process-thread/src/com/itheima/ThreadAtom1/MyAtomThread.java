package com.itheima.ThreadAtom1;

import java.util.concurrent.atomic.AtomicInteger;

public class MyAtomThread implements Runnable{
    AtomicInteger ac = new AtomicInteger(0);
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            int count = ac.incrementAndGet();
            System.out.println("已经送了"+ count + "个冰淇淋");
        }
    }
}
