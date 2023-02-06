package com.itheima.MyThread2;

public class Ticket3Demo {
    public static void main(String[] args) {
        Ticket3 ticket3 = new Ticket3();

        Thread t1 = new Thread(ticket3);
        Thread t2 = new Thread(ticket3);
        Thread t3 = new Thread(ticket3);

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}
