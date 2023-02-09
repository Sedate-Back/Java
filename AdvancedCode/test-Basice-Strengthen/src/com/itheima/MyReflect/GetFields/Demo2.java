package com.itheima.MyReflect.GetFields;


import java.lang.reflect.Field;

/*
    获取成员变量后，可以进行赋值或获取值
    set
    get

 */
public class Demo2 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        // 1。 获取class对象
        Class aClass = Class.forName("com.itheima.MyReflect.GetFields.Student");

        // 2。 获取变量
        Field money = aClass.getDeclaredField("money");

        // 因为money是private修饰的，需要修改accessible
        money.setAccessible(true);

        // 3. 获取值
        // 3.1 创建obj参数，传递给get方法
        Student student = (Student) aClass.newInstance();
        Object o = money.get(student);
        System.out.println(o); // 300

        // 4. 修改
        money.set(student, 400);
        Object o1 = money.get(student);
        System.out.println(o1); // 400


    }
}
