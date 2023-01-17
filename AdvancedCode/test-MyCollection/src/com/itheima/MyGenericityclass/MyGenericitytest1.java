package com.itheima.MyGenericityclass;

public class MyGenericitytest1 {
    public static void main(String[] args) {
        // Generic 的测试类
        Generic<String> g1 = new MyGenericitydemo2<>();
        g1.show("杨幂");

        Generic<Integer> g2 = new MyGenericitydemo2<>();
        g2.show(30);

        Generic g3 = new MyGenericitydemo3();
        g3.show(10);
    }
}
