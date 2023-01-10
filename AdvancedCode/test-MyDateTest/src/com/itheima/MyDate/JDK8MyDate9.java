package com.itheima.MyDate;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class JDK8MyDate9 {
    // Duration 计算两段时间的时间间隔
    public static void main(String[] args) {


        // 首先创建两个LocalDateTime对象
        LocalDateTime localDateTime1 = LocalDateTime.of(2020, 1, 1, 13, 14, 15);
        LocalDateTime localDateTime2 = LocalDateTime.of(2020, 1, 2, 11, 12, 13);

        //public static Duration between(开始时间,结束时间)  计算两个“时间"的间隔
        // 创建Duration对象
        Duration duration = Duration.between(localDateTime1, localDateTime2);
        System.out.println(duration);  //PT21H57M58S
        //public long toSeconds()	       获得此时间间隔的秒
        System.out.println(duration.toSeconds()); //79078
        //public int toMillis()	           获得此时间间隔的毫秒
        System.out.println(duration.toMillis()); //79078000
        //public int toNanos()             获得此时间间隔的纳秒
        System.out.println(duration.toNanos()); //79078000000000
    }



}
