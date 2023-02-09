package com.itheima.MyReflect.GetMethod;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/*
    使用成员方法
    invoke
 */
public class Demo2 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        // 1. 获取class对象
        Class aClass = Class.forName("com.itheima.MyReflect.GetMethod.Student");

        // 2. 获取成员方法对象
        Method function4 = aClass.getDeclaredMethod("function4", String.class);

        // 3. 创建一个学生对象
        Student student = (Student) aClass.newInstance();

        // 4. 运行方法
        Object invoke = function4.invoke(student, "zhangsan");

        System.out.println(invoke);
        // function4方法，有参有返回值,参数为zhangsan
        // aaa


    }
}
