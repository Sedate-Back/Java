package com.itheima.test4;

import java.lang.reflect.InvocationTargetException;

public class CalulatorDemo {
    public static void main(String[] args) {
        useCalculator((int a, int b)->{return a+b;});
    }


    public static void useCalculator(Calculator calculator){
        int res = calculator.calc(10, 20);
        System.out.println(res);
    }

}




interface Calculator{
    int calc(int a, int b);
}