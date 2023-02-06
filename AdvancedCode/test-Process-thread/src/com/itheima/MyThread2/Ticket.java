package com.itheima.MyThread2;
/*
  这种情况，由于对象中没有设置线程等待或阻塞，会导致线程切换的时候，共享数据重复的情况出现；
  所以我们可以通过加锁、阻塞、等待等操作，来让线程等待执行，等票卖完了再允许其他线程进行
 */
public class Ticket implements Runnable {

    private int tickets = 100; // 线程共享数据

    @Override
    public void run() {
        while (true) {
            if (tickets <= 0) {
                break;
            } else {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                tickets--;
                System.out.println(Thread.currentThread().getName() + "在卖票，还剩余" + tickets + "张票～");
            }
        }
    }
}
