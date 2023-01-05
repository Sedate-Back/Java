package com.itheima.system;


public class systemDemo {
    public static void main(String[] args) {
        System.out.println("10");
         //        System.exit(0); // 会停止到这一行，后面的20不输出
        System.out.println("20");


        long start  = System.currentTimeMillis(); // 获取当前时间，以毫秒计算


        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = new int[10];

        // arraycopy(数据源数组，起始索引，目的地数组，起始索引，拷贝个数)
        System.arraycopy(arr1, 0, arr2, 0, arr1.length);
        for (int i = 0; i < arr2.length; i++) {
            System.out.println(arr2[i]);
        }
        // 输出结果 1 2 3 4 5 0 0 0 0 0
    }
}
