package com.itheima.test3;

public class Test3Innerclass {
    public static void main(String[] args) {
        Outer outer = new Outer();
        outer.method();
    }
}
class Outer{
    public void method(){

        class Inner{
            public void show(){
                System.out.println("show ---");
            }

        }
        Inner inner = new Inner();
        inner.show();
    }
}
