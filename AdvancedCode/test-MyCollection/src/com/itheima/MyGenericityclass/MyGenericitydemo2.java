package com.itheima.MyGenericityclass;

public class MyGenericitydemo2<T> implements Generic<T> {
    // Generic 的接口1
    @Override
    public void show(T t){
        System.out.println(t);
    }
}
