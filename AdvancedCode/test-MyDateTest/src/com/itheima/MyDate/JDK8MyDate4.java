package com.itheima.MyDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class JDK8MyDate4 {
    public static void main(String[] args) {
        method1();
        method2();
    }

    private static void method2() {
        //public static LocalDateTime parse (准备解析的字符串, 解析格式) 把一个日期字符串解析成为一个LocalDateTime对象
        String s = "2020年11月12日 13:14:15";
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        LocalDateTime parse = LocalDateTime.parse(s, pattern);
        System.out.println(parse); // 2020-11-12T13:14:15
    }

    private static void method1() {
        // 创建一个时间为 2020年11月12日 13：15：15 的 LocalDateTime对象
        LocalDateTime localDateTime = LocalDateTime.of(2020, 11, 12, 13, 14, 15);
        System.out.println(localDateTime); //  2020-11-12T13:14:15

        //public String format (指定格式)   把一个LocalDateTime格式化成为一个字符串
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        System.out.println(pattern);
        // Value(YearOfEra,4,19,EXCEEDS_PAD)'年'Value(MonthOfYear,2)'月'Value(DayOfMonth,2)'日'' 'Value(HourOfDay,2)':'Value(MinuteOfHour,2)':'Value(SecondOfMinute,2)

        // 所以 pattern之后，还需要用localDateTime.format方法进行日期时间格式化
        String s = localDateTime.format(pattern);
        System.out.println(s); //  2020年11月12日 13:14:15
    }
}
