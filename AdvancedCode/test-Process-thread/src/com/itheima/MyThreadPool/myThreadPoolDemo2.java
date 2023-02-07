package com.itheima.MyThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class myThreadPoolDemo2 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        ThreadPoolExecutor pool = (ThreadPoolExecutor) executorService;
        System.out.println(pool.getPoolSize()); // 0

        executorService.submit(() -> {
            System.out.println(Thread.currentThread().getName() + "在执行了");
        }); // pool-1-thread-2在执行了

        executorService.submit(() -> {
            System.out.println(Thread.currentThread().getName() + "在执行了");
        }); // pool-1-thread-1在执行了

        System.out.println(pool.getPoolSize());  // 2

        executorService.shutdown();
    }
}
