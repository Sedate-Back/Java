package com.itheima.MyArraysDemo;

import java.util.Arrays;

public class MyArraysDemo {
    public static void main(String[] args) {
        // 1. 将数组变成字符串返回
        int [] arr = {3, 2, 4, 6, 7};
        System.out.println(Arrays.toString(arr));

        // 输出结果
        // [3, 2, 4, 6, 7]

        // 2. 将数组排序后返回
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));

        // 输出结果
        // [2, 3, 4, 6, 7]


        // 3. 利用二分查找返回指定元素的索引
        int [] arr2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int index = Arrays.binarySearch(arr2, 1);
        int index2 = Arrays.binarySearch(arr2, 11);
        int index3 =Arrays.binarySearch(arr2, 0);

        System.out.println(index); // 0
        System.out.println(index2); // -11
        System.out.println(index3); // -1

        // 如果找到就返回值的索引
        // 找不到就返回该元素原本应该出现的索引的相反值-1 例如 11 如果存在的话，应该是在第10个位置， 所以就是 -10 -1 = -11

    }
}
