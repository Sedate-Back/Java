package com.itheima.integerDemo;

public class MyIntgerDemo2 {
    public static void main(String[] args) {
        String s = "91 27 46 38 50";

        String[] stringArr = s.split(" ");

        int[] numberArr = new int[stringArr.length];

        for (int i = 0; i < stringArr.length; i++) {
            int num = Integer.parseInt(stringArr[i]);
            numberArr[i] = num;

        }
        for (int i = 0; i < numberArr.length; i++) {
            System.out.println(numberArr[i]);
        }
    }
}
