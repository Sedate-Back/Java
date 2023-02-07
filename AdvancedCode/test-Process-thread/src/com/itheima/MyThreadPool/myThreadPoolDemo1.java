package com.itheima.MyThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class myThreadPoolDemo1 {
    public static void main(String[] args) {
        // 1. 创建线程池对象 executorService
        ExecutorService executorService = Executors.newCachedThreadPool();

        // 2. 往线程池对象中加入任务
        executorService.submit(()->{
            System.out.println(Thread.currentThread().getName() + "正在运行！");
            // pool-1-thread-2正在运行！
        });

        executorService.submit(()->{
            System.out.println(Thread.currentThread().getName() + "正在运行！");
            // pool-1-thread-1正在运行！
        });

        // 3. 回收线程及摧毁线程池
        executorService.shutdown();
    }



}
