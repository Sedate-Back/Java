package com.itheima.mylist;

import java.util.ArrayList;
import java.util.List;

public class MyListDemo2 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        list.add("ddd");

        // 方法一：在list的指定索引处添加元素
        method1(list);
        
        // 方法二：在指定索引处，删除索引对应的元素
        method2(list);
        
        // 方法三：在指定索引处，将对应元素修改成输入的元素
        method3(list);
        
        // 方法四：获取指定索引的元素值
        method4(list);

    }

    private static void method4(List<String> list) {
        String s = list.get(0);
        System.out.println(s);
    }

    private static void method3(List<String> list) {
        String s = list.set(0, "qqq");
        System.out.println(s);
        System.out.println(list);
    }

    private static void method2(List<String> list) {
        String s = list.remove(0);
        System.out.println(s);
        System.out.println(list);
    }

    private static void method1(List<String> list) {
        list.add(0,"qqq");
        System.out.println(list); // [qqq, aaa, bbb, ccc, ddd]
    }
}
