package com.itheima.MyReflect.GetConstructor;

import java.lang.reflect.Constructor;

public class Demo1 {
    // 获取构造方法有4种：
    //         1。 getConstructors
    //         2。 getDeclaredConstructors
    //         3。 getConstructor
    //         4。 getDeclaredConstructor
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {
        // 1. 获取Class对象
        Class aClass = Class.forName("com.itheima.MyReflect.GetConstructor.Student");

        // 2.调用方法

        Constructor[] constructors = aClass.getConstructors(); // 获取公共方法public修饰的
        for (Constructor constructor : constructors) {
            System.out.println(constructor);
        }
        // public com.itheima.MyReflect.GetConstructor.Student()
        // public com.itheima.MyReflect.GetConstructor.Student(java.lang.String,int)


        Constructor[] declaredConstructors = aClass.getDeclaredConstructors(); // 所有构造
        for (Constructor declaredConstructor : declaredConstructors) {
            System.out.println(declaredConstructor);
        }
        // private com.itheima.MyReflect.GetConstructor.Student(java.lang.String)
        // public com.itheima.MyReflect.GetConstructor.Student()
        // public com.itheima.MyReflect.GetConstructor.Student(java.lang.String,int)

        Constructor classConstructor = aClass.getConstructor(String.class, int.class); // 获取指定的pubilc方法
        System.out.println(classConstructor);
        // public com.itheima.MyReflect.GetConstructor.Student(java.lang.String,int)

        Constructor declaredConstructor = aClass.getDeclaredConstructor(String.class);
        System.out.println(declaredConstructor);
        // private com.itheima.MyReflect.GetConstructor.Student(java.lang.String)
    }
}
