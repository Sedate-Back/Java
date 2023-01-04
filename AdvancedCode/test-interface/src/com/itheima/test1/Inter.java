package com.itheima.test1;

public interface Inter {
    int a = 1314;
    public abstract void Study();

    public default void Learn(){
        System.out.println("因为接口需要，我添加了一些body内容");
    }
}
