package com.itheima.myMap;

import java.util.HashMap;
import java.util.Map;

public class MyMapDemo1 {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();

        map.put("001","I love you");
        map.put("002","huangsiqi");
        map.put("003","Python");
        map.put("004","Java");

        System.out.println(map);
        // 输出结果
        //  {001=I love you, 002=huangsiqi, 003=Python, 004=Java}

        System.out.println(map.size());  // 键值对 4
        System.out.println(map.isEmpty());  // false
        System.out.println(map.containsValue("huangsiqi"));  // true
        System.out.println(map.containsKey("001"));  // true

        // 添加数据
        map.put("005", "Zhangzehong");
        System.out.println(map);  //{001=I love you, 002=huangsiqi, 003=Python, 004=Java, 005=Zhangzehong}

        // 删除数据
        map.remove("001");
        System.out.println(map); // {002=huangsiqi, 003=Python, 004=Java, 005=Zhangzehong}

        // 移除所有数据
        map.clear();
        System.out.println("clear之后的结果" + map);  // ()


    }
}
