package com.itheima.MyConcurrency;

/*
    使用场景：
        指定一个管理员，每次允许多少个任务通过，任务通过后返回资源给管理员，接着继续跑任务，指导结束
 */

public class MySemaphore {
    public static void main(String[] args) {
        MyRunnable mr = new MyRunnable();

        for (int i = 0; i < 100; i++) {
            new Thread(mr).start();
        }
    }
}
