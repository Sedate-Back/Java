package com.itheima.mytreeset;

import java.util.TreeSet;

public class TestTreeset {
    public static void main(String[] args) {
        TreeSet<Student> students = new TreeSet<>();

        // 调用排序方式



        Student s1 = new Student("dahei", 80, 80, 80);
        Student s2 = new Student("erhei", 90, 90, 90);
        Student s3 = new Student("xiaohei", 100, 100, 100);

        students.add(s1);
        students.add(s2);
        students.add(s3);

        for (Student student : students) {
            System.out.println(student);
        }
    }
}
