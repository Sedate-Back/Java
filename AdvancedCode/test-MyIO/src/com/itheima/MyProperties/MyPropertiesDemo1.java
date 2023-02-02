package com.itheima.MyProperties;

import java.util.Properties;
import java.util.Set;

public class MyPropertiesDemo1 {
    public static void main(String[] args) {
        Properties properties = new Properties();

        //
        properties.put("001", "佟丽娅");
        properties.put("002", "杨幂");
        properties.put("003", "赵丽颖");

        Set<Object> keySet = properties.keySet();
        for (Object key : keySet) {
            Object value = properties.get(key);
            System.out.println(key + "---" + value);
        }

    }
}
