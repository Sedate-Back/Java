package com.itheima.test4;

public class TestSwimming {
    public static void main(String[] args) {
        goSwimming(new Swimming() {
            @Override
            public void swim() {
                System.out.println("Let's go swimming together!!");
            }
        });
        // 对于Lambda表达式，是对匿名内部类进行优化 = 》 便于理解的说法
        goSwimming(()-> {System.out.println("Let's go swimming together!!");});
    }
    public static void goSwimming(Swimming swimming){
        swimming.swim();
    }
}

