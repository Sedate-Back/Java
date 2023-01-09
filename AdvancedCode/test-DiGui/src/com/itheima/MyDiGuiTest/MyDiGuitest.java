package com.itheima.MyDiGuiTest;

public class MyDiGuitest {
    public static void main(String[] args) {
        int sum = getSum(100);
        System.out.println(sum);
    }

    private static int getSum(int i) {
        //1- 100之间的和
        //100 + (1-99之间的和)
        // 99 + (1- 98之间的和)
        //....
        //1
        //方法的作用: 求 1- i 之间和
        if(i == 1){
            // i == 1 这个是递归的出口 当i == 1的时候，返回i的当前值给getsum，回推到i==初始值的时候
            return 1;
        }else{
            // 初始值 -1 直到 i==1的时候，停止调用getSum
            return i + getSum(i -1);
        }
    }
}