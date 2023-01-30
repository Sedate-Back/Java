package com.itheima.MyHsahMap;

import java.util.HashMap;
import java.util.Set;

public class HashMapDemo1 {
    public static void main(String[] args) {
        HashMap<Student, String> hashMap = new HashMap<Student, String>();

        Student s1 = new Student("zzh", 20);
        Student s2 = new Student("hsq", 18);
        Student s3 = new Student("lsx", 18);
        Student s4 = new Student("lxt", 18);

        hashMap.put(s1, "汕头");
        hashMap.put(s2, "揭阳");
        hashMap.put(s3, "广州");
        hashMap.put(s4, "深圳");

        Set<Student> keySet = hashMap.keySet();
        for (Student student : keySet) {
            String s = hashMap.get(student);
            System.out.println(student.getName() + "," + student.getAge() + "," + s);
        }
    }
}
