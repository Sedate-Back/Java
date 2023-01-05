package com.itheima.test2;

public class StringHandlerDemo {
    public static void main(String[] args) {

        // 匿名内部类的实现
        useStringHandler(new StringHandler() {
            @Override
            public void printMessage(String msg) {
                System.out.println("我是匿名内部类"+ msg);
            }
        });
        // lambda的实现
        useStringHandler( (String msg) -> {System.out.println("我是Lambda类"+ msg);});
    }
    public static void useStringHandler(StringHandler stringHandler){
        stringHandler.printMessage("itheima");
    }
}

interface StringHandler{
    void printMessage(String msg);
}