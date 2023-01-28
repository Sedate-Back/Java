package com.itheima.myMap;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyMapDemo2 {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();

        map.put("001","I love you");
        map.put("002","huangsiqi");
        map.put("003","Python");
        map.put("004","Java");

        System.out.println(map);

        // 根据键获取值
        System.out.println(map.get("001"));

        // 获取所有键的集合
        Set<String> keySet = map.keySet();
        for (String s : keySet) {
            System.out.println(s);
        }
        // 001
        // 002
        // 003
        // 004

        // 获取所有值的集合
        Collection<String> values = map.values();
        for (String value : values) {
            System.out.println(value);
        }
        // I love you
        // huangsiqi
        // Python
        // Java

    }
}
