package com.itheima.MyThread5;

import java.util.concurrent.ArrayBlockingQueue;

public class Cooker extends Thread{
    private ArrayBlockingQueue<String> bd;
    public Cooker(ArrayBlockingQueue<String> bd){
        this.bd = bd;

    }

    @Override
    public void run() {
        while (true){
            try{
                bd.put("汉堡包");
                System.out.println("厨师放入一个汉堡包");

            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
