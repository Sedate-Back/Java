package com.itheima.MyParameter;

import java.util.List;
import java.util.Map;
import java.util.Set;

// 通过of方法创建集合的不可变对象
public class ParameterDemo2 {
    public static void main(String[] args) {
        // 1. list,of
        List<Object> list = List.of(1, 2, 3, 4, 5);
        System.out.println(list); // [1, 2, 3, 4, 5]

        // 2. set.of
        Set<Integer> set = Set.of(1, 2, 3, 4, 5, 6);
        System.out.println(set); // [6, 1, 2, 3, 4, 5]

        // 3. map.of
        Map<String, String> map = Map.of("heima001", "zzh", "heima002", "huangsiqi");
        System.out.println(map); //  {heima002=huangsiqi, heima001=zzh}
    }
}
