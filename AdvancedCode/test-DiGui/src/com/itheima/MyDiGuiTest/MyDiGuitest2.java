package com.itheima.MyDiGuiTest;

public class MyDiGuitest2 {
    public static void main(String[] args) {
        //调用方法
        int result = getJc(6);
        //输出结果
        System.out.println("5的阶乘是：" + result);
    }

    private static int getJc(int n) {
        if (n==1){
            // 阶乘的出口是i==1 当i递减到1的时候，程序结束，返回计算结果
            return 1;
        }else {
            return n*getJc(n-1);
        }
    }
}