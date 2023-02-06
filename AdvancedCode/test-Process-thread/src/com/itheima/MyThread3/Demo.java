package com.itheima.MyThread3;

public class Demo {
    public static void main(String[] args) {
        Foodie f = new Foodie();
        Cooker c = new Cooker();

        f.start();
        c.start();
    }
}
