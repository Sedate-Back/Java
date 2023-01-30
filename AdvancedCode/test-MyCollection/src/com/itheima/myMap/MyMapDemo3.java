package com.itheima.myMap;

import java.lang.reflect.Member;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyMapDemo3 {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();

        map.put("001","I love you");
        map.put("002","huangsiqi");
        map.put("003","Python");
        map.put("004","Java");

        System.out.println(map); // {001=I love you, 002=huangsiqi, 003=Python, 004=Java}

        Set<Map.Entry<String, String>> entrySet = map.entrySet();
        System.out.println(entrySet);  // [001=I love you, 002=huangsiqi, 003=Python, 004=Java]

        for (Map.Entry<String, String> stringStringEntry : entrySet) {
            String key = stringStringEntry.getKey();
            String value = stringStringEntry.getValue();
            System.out.println(key + "," + value);

        }
        // 001,I love you
        // 002,huangsiqi
        // 003,Python
        // 004,Java
    }
}
