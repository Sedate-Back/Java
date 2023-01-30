package com.itheima.mylist;

import java.util.LinkedList;

public class MyLinkedListDemo2 {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        list.add("ddd");


        // 1. 添加到首部
        list.addFirst("qqq");
        System.out.println(list); // [qqq, aaa, bbb, ccc, ddd]

        // 2. 添加到末尾
        list.addLast("www");
        System.out.println(list); // [qqq, aaa, bbb, ccc, ddd, www]


        // 3. 获取头部和尾部的值
        String first = list.getFirst();
        String last = list.getLast();

        System.out.println(first);  // qqq
        System.out.println(last); // www

        // 4.删除头部和尾部的值
        String s = list.removeFirst();
        String s1 = list.removeLast();
        System.out.println(s); // qqq
        System.out.println(s1); // www
        System.out.println(list);  // [aaa, bbb, ccc, ddd]

    }
}
