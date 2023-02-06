package com.itheima.MyThread4;

public class Foodie extends Thread{
    private Desk desk;

    public Foodie(Desk desk){
        this.desk = desk;
    }

    @Override
    public void run() {
        while (true){
            synchronized (desk.getLock()){
                if(desk.getCount() == 0){
                    break;
                }else {
                    if (desk.isFlag()){
                        System.out.println("吃货正在吃汉堡包");
                        desk.setFlag(false);
                        desk.getLock().notifyAll();
                        desk.setCount(desk.getCount() - 1);
                    }else {
                        try {
                            desk.getLock().wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
