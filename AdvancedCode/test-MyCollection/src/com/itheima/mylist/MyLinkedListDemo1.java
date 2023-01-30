package com.itheima.mylist;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MyLinkedListDemo1 {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();

        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        list.add("ddd");

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        for (String s : list) {
            System.out.println(s);
        }


    }
}
