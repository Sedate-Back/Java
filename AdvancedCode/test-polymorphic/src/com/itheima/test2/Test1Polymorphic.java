package com.itheima.test2;

public class Test1Polymorphic {
    public static void main(String[] args) {
        Fu fu = new Zi();
        System.out.println(fu.num);
        fu.method();
    }
}

class Fu{
    int num = 10;

    public void method(){
        System.out.println("Fu ... method");
    }
}

class Zi extends Fu{
    int num = 20;

    public void method(){
        System.out.println("Zi ... method");
    }
}