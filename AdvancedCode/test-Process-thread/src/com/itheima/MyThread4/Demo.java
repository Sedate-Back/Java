package com.itheima.MyThread4;

public class Demo {
    public static void main(String[] args) {
        Desk desk = new Desk();
        Foodie f= new Foodie(desk);
        Cooker c = new Cooker(desk);

        f.start();
        c.start();
    }
}
