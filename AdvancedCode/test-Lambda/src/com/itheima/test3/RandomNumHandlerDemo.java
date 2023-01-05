package com.itheima.test3;

import java.util.Random;

public class RandomNumHandlerDemo {
    public static void main(String[] args) {


        // 如果lambda操作的接口的方法有返回值，一定要再最后加return语句，返回内容或值
        useRandomNumHandler(()->{
            Random random = new Random();
            int num =random.nextInt(10) + 1;
            return num;
        });
    }

    public static void useRandomNumHandler(RandomNumHandler randomNumHandler){
        int result = randomNumHandler.getNumber();
        System.out.println(result);

    }
}

interface RandomNumHandler{
    int getNumber();
}
