package com.itheima.MyReflect.GetFields;

/*

        获取成员变量有四种方法：
            1。 getFields
            2。 getDeclaredFields
            3。 getField
            4。 getDeclaredField

 */

import java.lang.reflect.Field;

public class Demo1 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException {

        // 1.获取对象
        Class aClass = Class.forName("com.itheima.MyReflect.GetFields.Student");

        // 2。获取成员变量
        Field[] fields = aClass.getFields();
        for (Field field : fields) {
            System.out.println(field);
        }
        // public java.lang.String com.itheima.MyReflect.GetFields.Student.name
        // public int com.itheima.MyReflect.GetFields.Student.age
        // public java.lang.String com.itheima.MyReflect.GetFields.Student.gender

        System.out.println("----------------------");

        Field[] declaredFields = aClass.getDeclaredFields(); //
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField);
        }
        // public java.lang.String com.itheima.MyReflect.GetFields.Student.name
        // public int com.itheima.MyReflect.GetFields.Student.age
        // public java.lang.String com.itheima.MyReflect.GetFields.Student.gender
        // private int com.itheima.MyReflect.GetFields.Student.money

        System.out.println("----------------------");

        Field field = aClass.getField("name");
        System.out.println(field);
        // public java.lang.String com.itheima.MyReflect.GetFields.Student.name

        System.out.println("----------------------");

        Field money = aClass.getDeclaredField("money");
        System.out.println(money);
        // private int com.itheima.MyReflect.GetFields.Student.money


    }
}
