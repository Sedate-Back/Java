package com.itheima.MyParameter;

public class ParameterDemo1 {
    public static void main(String[] args) {

        int sum = getSum(1, 2, 3, 4, 5, 6, 7, 8, 9);
        System.out.println(sum);

    }

    public static int getSum(int...arr){
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum = sum + arr[i];
        }
        return sum;
    }
}
