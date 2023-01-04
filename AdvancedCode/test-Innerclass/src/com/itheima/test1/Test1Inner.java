package com.itheima.test1;

public class Test1Inner {
    public static void main(String[] args) {

        Outer.Inner inner = new Outer().new Inner();
        inner.show();
        System.out.println(inner.num);
    }
}

class Outer{
    private int a = 20;
    class Inner{
        int num = 10;

        public void show(){
            System.out.println("inner --- show");
            System.out.println(a);
        }
    }
}
