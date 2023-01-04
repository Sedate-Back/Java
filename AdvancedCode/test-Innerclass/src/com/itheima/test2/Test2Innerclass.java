package com.itheima.test2;

import java.util.concurrent.CopyOnWriteArrayList;

public class Test2Innerclass {
    public static void main(String[] args) {
        Outer outer = new Outer();
        outer.method();
        Outer2.Inner oi = new Outer2.Inner();
        oi.show();

        Outer2.Inner.method();
    }
}

class Outer{
    private class Inner{
        public void show(){
            System.out.println(" inner --- show");
        }
    }
    public void method(){
        Inner inner = new Inner();
        inner.show();
    }
}
class Outer2{
    static class Inner{
        public void show(){
            System.out.println(" inner --- show");
        }

        public static void method(){
            System.out.println("inner --- method");
        }
    }
}