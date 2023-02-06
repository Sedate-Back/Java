package com.itheima.MyThread2;

public class SellTicketDemo {
    public static void main(String[] args) {
        /*// 1. 创建卖票对象
        Ticket ticket = new Ticket();

        // 2. 创建3条线程，表示3个窗口
        Thread t1 = new Thread(ticket, "窗口1");
        Thread t2 = new Thread(ticket, "窗口2");
        Thread t3 = new Thread(ticket, "窗口3");

        // 3. 开启线程
        t1.start();
        t2.start();
        t3.start();*/

        // 1. 创建卖票对象
        Ticket2 ticket2 = new Ticket2();

        // 2. 创建3条线程，表示3个窗口
        Thread t1 = new Thread(ticket2, "窗口1");
        Thread t2 = new Thread(ticket2, "窗口2");
        Thread t3 = new Thread(ticket2, "窗口3");

        // 3. 开启线程
        t1.start();
        t2.start();
        t3.start();

    }
}
