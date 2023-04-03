package com.itheima.demo;

import redis.clients.jedis.Jedis;

import java.util.Set;

public class demo1 {
    public static void main(String[] args) {
        // 1.获取连接
        Jedis jedis = new Jedis("localhost", 6379);

        // 2. 执行操作
        jedis.set("username","xiaoming");

        String value = jedis.get("username");
        System.out.println(value); // xiaoming


        jedis.hset("myhash", "addr", "bj");
        String hValue = jedis.hget("myhash", "addr");
        System.out.println(hValue); // bj


        Set<String> keys = jedis.keys("*");
        for (String key : keys) {
            System.out.println(key); // myhash  username
        }

        // 关闭连接
        jedis.close();
    }
}
