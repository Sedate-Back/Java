package com.itheima.MyGenericityclass;

import javax.swing.*;

public class MyGenericitydemo1 {
    public static void main(String[] args) {
        // 调用泛型类box 传递类型string，创建对象box1
        box<String> box1 = new box<>();

        // 调用set方法传递变量给泛型类box
        box1.setElement("NI真好看");

        box<Integer> box2 = new box<>();
        box2.setElement(12345);

        System.out.println(box1.getElement()); // NI真好看

        System.out.println(box2.getElement()); // 12345

    }
}
