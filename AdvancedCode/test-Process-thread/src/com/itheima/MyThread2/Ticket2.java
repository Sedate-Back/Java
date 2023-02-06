package com.itheima.MyThread2;

/*
synchronized(任意对象) {
	多条语句操作共享数据的代码
}
 */
public class Ticket2 implements Runnable{
    private int tickets = 100;
    private Object obj = new Object();

    @Override
    public void run() {
        while (true){
            synchronized (obj){
                if (tickets>0){
                    try{
                        Thread.sleep(100);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    //
                    System.out.println(Thread.currentThread().getName() + "在卖票，还剩余" + tickets + "张票～");
                    tickets--;
                }else {
                    break;
                }
            }
        }
    }
}
