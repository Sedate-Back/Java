package com.itheima.MyDate;

import java.time.LocalDateTime;

public class JDK8MyDate {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now); // 2023-01-10T20:22:51.176467400

        LocalDateTime localDateTime = LocalDateTime.of(2020, 11, 11, 11, 11, 11);
        System.out.println(localDateTime); // 2020-11-11T11:11:11
    }
}
