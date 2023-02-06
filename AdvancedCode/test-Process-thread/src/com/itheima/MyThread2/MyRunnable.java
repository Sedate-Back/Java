package com.itheima.MyThread2;

public class MyRunnable implements Runnable {
    private static int ticketCount = 100;

    @Override
    public void run() {
        while (true) {
            if ("窗口1".equals(Thread.currentThread().getName())) {

                boolean result = synchronizedMethod();
                if (result) {
                    break;
                }
            }
            if ("窗口2".equals(Thread.currentThread().getName())) {
                synchronized (MyRunnable.class) {
                    if (ticketCount <= 0) {
                        break;
                    } else {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        ticketCount--;
                        System.out.println(Thread.currentThread().getName() + "在卖票,还剩下" + ticketCount + "张票");
                    }
                }
            }
        }
    }

    private static synchronized boolean synchronizedMethod() {
        if (ticketCount <= 0) {
            return true;
        } else {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ticketCount--;
            System.out.println(Thread.currentThread().getName() + "在卖票,还剩下" + ticketCount + "张票");
            return false;
        }
    }
}
