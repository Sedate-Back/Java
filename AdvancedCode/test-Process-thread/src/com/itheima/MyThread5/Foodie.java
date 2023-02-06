package com.itheima.MyThread5;

import java.util.concurrent.ArrayBlockingQueue;

public class Foodie extends Thread{
    private ArrayBlockingQueue<String> bd;

    public Foodie(ArrayBlockingQueue<String> bd){
        this.bd = bd;
    }

    @Override
    public void run() {
        while(true){
            try{
                String take = bd.take();
                System.out.println("吃货将"+ take + "拿出来吃了");
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
