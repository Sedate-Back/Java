package com.itheima.MyConcurrency;

/*
    并发工具：ConcurrentHashMap
    因为Map是java中比较常见的数据存储单元，但是多线程在操作map的时候不安全，
    在jdk1.7以前，用Hashtable 但是效率低（原因是每次加入或删除数据的时候，都会将整张表锁起来，影响其他线程的使用效率）
    之后，出现了ConcurrentHashMap，jdk7和8的原理不同

 */

import java.util.concurrent.ConcurrentHashMap;

public class MyConcurrentHashMap {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap<String, String> hm = new ConcurrentHashMap<>(100);

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 25; i++) {
                hm.put(i+"", i+"");
            }
        });

        Thread t2= new Thread(() -> {
            for (int i = 25; i < 51; i++) {
                hm.put(i+"", i+"");

            }
        });

        t1.start();
        t2.start();

        System.out.println("--------------------");

        Thread.sleep(1000); // 让线程操作完毕

        for (int i = 0; i < 51; i++) {
            System.out.println(hm.get(i+ ""));

        }

    }
}
