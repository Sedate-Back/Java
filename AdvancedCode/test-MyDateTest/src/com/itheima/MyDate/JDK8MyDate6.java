package com.itheima.MyDate;

import java.time.LocalDateTime;

public class JDK8MyDate6 {
    // 添加或减去时间单位 minus + 时间单位
    public static void main(String[] args) {
        //public LocalDateTime minusYears (long years)  减去或者添加年
        LocalDateTime localDateTime = LocalDateTime.of(2020, 11, 11, 13, 14, 15);
        LocalDateTime newLocalDateTime = localDateTime.minusYears(1);
        System.out.println(newLocalDateTime);  // 2019-11-11T13:14:15

        LocalDateTime newLocalDateTime2 = localDateTime.minusYears(-1);
        System.out.println(newLocalDateTime2); //  2021-11-11T13:14:15

    }

}
