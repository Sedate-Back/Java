package com.itheima.mycollection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class demo4 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("b");

        //method1(list);

        method2(list);
    }

    private static void method2(ArrayList<String> list) {
        Iterator<String> iterator = list.iterator();

        while (iterator.hasNext()){
            String next = iterator.next();
            if ("b".equals(next)){
                iterator.remove();
            }

        }
        System.out.println(list); // [a, c, d, e]
    }

    private static void method1(ArrayList<String> list) {
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            if ("b".equals(s)){
                list.remove(s);
                i--;
            }
        }
        System.out.println(list); // [a, c, d, e]
    }
}
