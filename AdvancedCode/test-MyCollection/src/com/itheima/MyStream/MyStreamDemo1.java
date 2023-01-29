package com.itheima.MyStream;

import java.util.ArrayList;
import java.util.List;
//import java.util.List;

public class MyStreamDemo1 {
    public static void main(String[] args) {
        ArrayList<String> list1 = new ArrayList<>(List.of("zzh", "zzr", "zcj", "hsq", "lxt", "lxs"));

        // stream().filter() 主要是用来筛选条件的， 一个filter筛选一个条件
        // 变量s 就是每一个元素， filter后面括号是Lambda表达式
        // s -> s.startwith("z")  =》 变量的开头是以z开头的，进行下一步筛选
        // (s -> s.length() == 3   变量长度是3的 进行下一步筛选
        // forEach 后也是加 Lambda表达式 表示： 将符合上述目标的元素输出
        list1.stream().filter(s -> s.startsWith("z"))
                .filter(s -> s.length() == 3)
                .forEach(s -> System.out.println(s));
    }
}
