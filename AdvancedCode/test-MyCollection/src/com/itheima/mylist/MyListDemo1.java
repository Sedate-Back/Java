package com.itheima.mylist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyListDemo1 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");


        Iterator<String> it = list.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }

        System.out.println("----------");

        for (String s : list) {
            System.out.println(s);
        }
    }
}
