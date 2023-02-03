package com.itheima.MyThread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadTest3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyThreadDemo3 myThreadDemo3 = new MyThreadDemo3();

        // 调用Future中的FutureTask方法
        FutureTask<String> ft = new FutureTask<>(myThreadDemo3);

        // 创建线程对象
        Thread t1 = new Thread(ft);
        t1.setName("在一起第");
        // 开启线程
        t1.start();

        // 捕获返回值（需要等线程运行结束后才能捕获，不然会一致卡住）
        String result = ft.get();
        System.out.println(result);
    }
}
