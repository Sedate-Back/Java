package com.itheima.MyGenericityclass;

import javax.swing.*;

public class MyGenericitydemo1 {
    public static void main(String[] args) {
        box<String> box1 = new box<>();
        box1.setElement("NI真好看");

        box<Integer> box2 = new box<>();
        box2.setElement(12345);

        System.out.println(box1.getElement()); // NI真好看

        System.out.println(box2.getElement()); // 12345

    }
}
