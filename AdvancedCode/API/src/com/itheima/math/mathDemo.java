package com.itheima.math;

public class mathDemo {
    public static void main(String[] args) {
        // abs 的使用： 绝对值
        int abs = Math.abs(10);
        System.out.println(abs);

        // ceil 向上取整
        double ceil = Math.ceil(10.1);
        System.out.println(ceil);
        // 输出结果： 11

        // floor 向下取整
        double floor = Math.floor(10.1);
        System.out.println(floor);
        // 输出结果： 10

        // round 四舍五入
        double round_1 = Math.round(10.1);
        double round_2 = Math.round(10.5);
        System.out.println(round_1);
        System.out.println(round_2);
        // 输出结果 10  、 11

        // max 比较两数大小 输出较大数
        double max = Math.max(10, 20);
        System.out.println(max);
        // 20

        // min 与上相反

        // random 取 0.0 ~ 1.0 之间的数 是随机的
        double random = Math.random();
        System.out.println(random);

    }
}
