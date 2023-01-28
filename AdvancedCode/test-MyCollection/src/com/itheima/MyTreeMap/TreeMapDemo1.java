package com.itheima.MyTreeMap;

import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class TreeMapDemo1 {
    public static void main(String[] args) {
        TreeMap<Student, String> treeMap = new TreeMap<>();

        Student s1 = new Student("zzh", 23);
        Student s2 = new Student("hsq", 22);
        Student s3 = new Student("lxs", 21);
        Student s4 = new Student("lxt", 18);

        treeMap.put(s1, "广东汕头");
        treeMap.put(s2, "广东揭阳");
        treeMap.put(s3, "广东深圳");
        treeMap.put(s4, "广东广州");

        treeMap.forEach(
                (Student key, String value) -> {
                    System.out.println(key + "---" + value);
                }
        );

    }
}
