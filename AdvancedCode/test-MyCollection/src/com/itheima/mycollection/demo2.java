package com.itheima.mycollection;

import java.util.ArrayList;
import java.util.Collection;

public class demo2 {
    public static void main(String[] args) {
        Collection<String> collection = new ArrayList<>();
        // 添加元素
        collection.add("aaa");
        collection.add("bbb");
        collection.add("ccc");
        collection.add("dddd");
        System.out.println(collection); // [aaa, bbb, ccc]

        // 移除元素
        //method1(collection);

        // 根据条件删除
        // method2(collection);
        
        // 清空集合
        // method3(collection);

        // 判断集合中有没有要的元素
        // method4(collection);

        // 判断集合是不是空的
        boolean result1 = collection.isEmpty();
        System.out.println(result1); // false

        // 返回集合的个数、长度
        int size = collection.size();
        System.out.println(size); // 4


    }

    private static void method4(Collection<String> collection) {
        boolean result1  = collection.contains("a");
        System.out.println(result1); // false

        boolean result2  = collection.contains("aaa");
        System.out.println(result2); // true
    }

    private static void method3(Collection<String> collection) {
        collection.clear();
        System.out.println(collection); // []
    }

    private static void method2(Collection<String> collection) {
        // removeif 底层回去遍历集合，得到集合的每一个元素
        //  s 依次表示每一个集合元素
        // 就会把着每一个元素都到lambda表达式中判断一下
        // 返回是True 就删除 ； False就不删除
        collection.removeIf(
                // 如果返回的s长度是3， 就删除
                (String s) ->{
                    return s.length() == 3;  // lambda 表达式 =>  是从源码分析的来， String s 是因为，元素存的都是String类型的
                    // 后半句 需要返回的是boolean值，才能进行删除
                 }
        );

        System.out.println(collection);  // [dddd]
    }

    private static void method1(Collection<String> collection) {
        // 移除集合中的元素
        // 如果删除成功， 返回True ； 删除失败，返回False
        boolean result = collection.remove("aaa");
        boolean result2 = collection.remove("ddd");
        System.out.println(result);
        System.out.println(result2);
        System.out.println(collection);  // [bbb, ccc]
    }
}
