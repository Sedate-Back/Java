package com.itheima.MyDate;

import java.time.LocalDateTime;

public class JDK8MyDate7 {
    // LocalDateTime 的修改方法  with + 时间单位
    public static void main(String[] args) {
        //public LocalDateTime withYear(int year)   修改年
        LocalDateTime localDateTime = LocalDateTime.of(2020, 11, 11, 13, 14, 15);
         LocalDateTime newLocalDateTime = localDateTime.withYear(2048);
         System.out.println(newLocalDateTime); // 2048-11-11T13:14:15

        LocalDateTime newLocalDateTime2 = localDateTime.withMonth(12);
        System.out.println(newLocalDateTime2); // 2020-12-11T13:14:15

    }

}
