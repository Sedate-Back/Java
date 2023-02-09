package com.itheima.MyReflect.GetConstructor;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/*
    利用构造，创建对象
    1。 public可以直接构造 ->  newInstance
    2。 private需要修改Accessible  -> setAccessible
 */
public class Demo2 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        // 1. 获取类对象
        Class aClass = Class.forName("com.itheima.MyReflect.GetConstructor.Student");

        // 2. 获取构造
        Constructor declaredConstructor = aClass.getDeclaredConstructor(String.class);

        // 3. 因为是私有的，所以要修改通过权限
        declaredConstructor.setAccessible(true);

        // 4. newInstance创建对象后强转
        Student student = (Student) declaredConstructor.newInstance("zhangsan");
        System.out.println(student);
        // name的值为:zhangsan
        // private...Student...有参构造方法
        // com.itheima.MyReflect.GetConstructor.Student@5305068a


    }
}
