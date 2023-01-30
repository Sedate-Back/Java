package com.itheima.mycollection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class demo3 {
    public static void main(String[] args) {
        Collection<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");

        //
        Iterator<String> iterator = list.iterator();

        //

//        boolean result1 = iterator.hasNext();
//        System.out.println(result1); // true
//
//        // 取出当前位置的元素，并往后有个长度
//        System.out.println(iterator.next()); // a
//        System.out.println(iterator.next()); // b
//        System.out.println(iterator.next()); // c
//        System.out.println(iterator.next()); // d
//        System.out.println(iterator.next()); // e

        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }
}
