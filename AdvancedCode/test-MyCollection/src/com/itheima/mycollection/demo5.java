package com.itheima.mycollection;

import java.util.ArrayList;
import java.util.Iterator;

public class demo5 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("f");


        // 1.数据类型一定是集合或者数组中的元素的类型
        // 2.str仅仅是一个变量名，代表数组中的每一个元素， 是第三方变量
        // 3 list是要遍历的集合
        // 可以用 集合名+.for 生成  => 例如 list.for + 回车

        for(String str: list){
            System.out.println(str);
        }
    }
}
