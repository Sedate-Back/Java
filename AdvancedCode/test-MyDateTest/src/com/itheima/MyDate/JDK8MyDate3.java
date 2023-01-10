package com.itheima.MyDate;

import java.time.*;

public class JDK8MyDate3 {
    public static void main(String[] args) {
        // 创建一个LocalDateTime对象，时间为 2020-12-12 8：10：12
        LocalDateTime localDateTime = LocalDateTime.of(2020, 12, 12, 8, 10, 12);
        //public LocalDate toLocalDate ()    将LocalDateTime转换成为一个LocalDate对象 显示日期
        LocalDate localDate = localDateTime.toLocalDate();
        System.out.println(localDate); // 2020-12-12

        //public LocalTime toLocalTime ()    将LocalDateTime转换成为一个LocalTime对象 显示时间
        LocalTime localTime = localDateTime.toLocalTime();
        System.out.println(localTime);  // 08:10:12
    }
}
