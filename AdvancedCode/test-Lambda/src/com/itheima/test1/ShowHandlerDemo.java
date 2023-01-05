package com.itheima.test1;

public class ShowHandlerDemo {
    public static void main(String[] args) {
        useShowHandler(new ShowHandler() {
            @Override
            public void show() {
                System.out.println("匿名内部类的Show方法---...");
            }
        });

        useShowHandler(()->{
            System.out.println("Lambda的Show方法---...");
        });
    }

    public static void useShowHandler(ShowHandler showHandler){
        showHandler.show();
    }
}
