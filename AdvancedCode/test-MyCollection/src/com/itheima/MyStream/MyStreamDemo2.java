package com.itheima.MyStream;

import java.util.*;
import java.util.stream.Stream;

public class MyStreamDemo2 {
    public static void main(String[] args) {
        // 生成Stream流得方式
        // 1. Collection体系集合
        List<String> list = new ArrayList<>();
        Stream<String> listStream = list.stream();

        Set<String> set = new HashSet<>();
        Stream<String> setStream = set.stream();


        // 2. map体系是间接生成Stream流
        Map<String, Integer> map = new HashMap<>();
        Stream<String> mapKeyStream = map.keySet().stream();
        Stream<Integer> mapValueStream = map.values().stream();
        Stream<Map.Entry<String, Integer>> entryStream = map.entrySet().stream();

        // 3. 数组Arrays可以通过stream方法生成流
        String[] strArr = {"zzh", "hsq", "lxs"};
        Stream<String> intArrayStream = Arrays.stream(strArr);


        // 4. 同种数据类型的多个数据可以通过Stream接口的静态方法of(T... values)生成流
        Stream<String> strArrayStream2 = Stream.of("hello", "world", "java");
        Stream<Integer> intStream = Stream.of(10, 20, 30);
    }

}
