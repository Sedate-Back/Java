package com.itheima.MyHashSet;


import java.util.HashSet;

public class HashSetDemo1 {
    public static void main(String[] args) {
        HashSet<Student> HashSet = new HashSet<>();

        Student s1 = new Student("zzh", 23);
        Student s2 = new Student("zzh", 23);
        Student s3 = new Student("hsq", 23);

        HashSet.add(s1);
        HashSet.add(s2);
        HashSet.add(s3);

        for (Student student : HashSet) {
            System.out.println(student);
        }
    }
}
