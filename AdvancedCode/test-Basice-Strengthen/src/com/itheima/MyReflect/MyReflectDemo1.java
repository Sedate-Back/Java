package com.itheima.MyReflect;

public class MyReflectDemo1 {
    public static void main(String[] args) throws ClassNotFoundException {
        Class clazz = Class.forName("com.itheima.MyReflect.Student");
        System.out.println(clazz);
        // class com.itheima.MyReflect.Student

        Class studentClass = Student.class;
        System.out.println(studentClass);
        // class com.itheima.MyReflect.Student

        Student student = new Student();
        Class studentClass1 = student.getClass();
        System.out.println(studentClass1);
        // class com.itheima.MyReflect.Student
    }
}
