package com.itheima.MyThreadPool;

import com.itheima.MyThread2.MyRunnable;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class myThreadPoolDemo3 {
    //    参数一：核心线程数量：  最小存在几个线程，用来跑参数5任务队列中的任务
    //    参数二：最大线程数 = 核心线程数 + 临时线程数， 如果任务多到核心线程跑不过来，就会临时创建一些线程来服务任务
    //    参数三：空闲线程最大存活时间 ->   60 100 120 int
    //    参数四：时间单位 -> TimeUnit.xxxx  （固定搭配，时间单位)
    //    参数五：任务队列 -> new ArrayBlockingQueue<>(10),
    //    参数六：创建线程工厂 -> Executors.defaultThreadFactory(),
    //    参数七：任务的拒绝策略 -> 1. new ThreadPoolExecutor.AbortPolicy()  丢弃任务并抛出异常
    //                          2. new ThreadPoolExecutor.DiscardPolicy()  丢弃任务，不抛出异常
    //                          3. new ThreadPoolExecutor.DiscardOldestPolicy()
    //                          4. new ThreadPoolExecutor.CallerRunsPolicy()
    public static void main(String[] args) {
        // Executor 执行人
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                2,
                5,
                2,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        pool.submit(new MyRunnable());
        pool.submit(new MyRunnable());

        pool.shutdown();
    }
}
