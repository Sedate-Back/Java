package com.itheima.mycollection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class demo6 {
    public static void main(String[] args) {
        Collection<Student> collection = new ArrayList<>();
        Student student1 = new Student("小皮同学", 23);
        Student student2 = new Student("l同学", 31);
        Student student3 = new Student("xiaojia同学", 33);

        collection.add(student1);
        collection.add(student2);
        collection.add(student3);

        Iterator<Student> iterator = collection.iterator();

        while (iterator.hasNext()){
            Student next = iterator.next();
            System.out.println(next);
        }
        System.out.println("----------------------");

        for (Student student : collection) {
            System.out.println(student);
        }

    }
}
