package com.itheima.MyReflect.GetMethod;

/*
    获取成员方法也有四种方法
        1. getMethods
        2. getDeclaredMethods
        3. getMethod
        4. getDeclaredMethod
 */

import java.lang.reflect.Method;

public class Demo1 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {
        // 1. 获取class对象
        Class aClass = Class.forName("com.itheima.MyReflect.GetMethod.Student");

        // 2. 获取成员方法
        Method[] methods = aClass.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }
        // public void com.itheima.MyReflect.GetMethod.Student.function1()
        // public void com.itheima.MyReflect.GetMethod.Student.function2(java.lang.String)
        // public java.lang.String com.itheima.MyReflect.GetMethod.Student.function3()
        // public java.lang.String com.itheima.MyReflect.GetMethod.Student.function4(java.lang.String)

        System.out.println("-----------");

        Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod);
        }
        // public void com.itheima.MyReflect.GetMethod.Student.function1()
        // private void com.itheima.MyReflect.GetMethod.Student.show()
        // public void com.itheima.MyReflect.GetMethod.Student.function2(java.lang.String)
        // public java.lang.String com.itheima.MyReflect.GetMethod.Student.function3()
        // public java.lang.String com.itheima.MyReflect.GetMethod.Student.function4(java.lang.String)

        System.out.println("-----------");

        Method function1 = aClass.getMethod("function1");
        System.out.println(function1);
        // public void com.itheima.MyReflect.GetMethod.Student.function1()

        System.out.println("-----------");

        Method show = aClass.getDeclaredMethod("show");
        System.out.println(show);
        // private void com.itheima.MyReflect.GetMethod.Student.show()






    }
}
