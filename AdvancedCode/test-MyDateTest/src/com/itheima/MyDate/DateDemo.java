package com.itheima.MyDate;

import java.util.Date;

public class DateDemo {
    public static void main(String[] args) {
        //public Date()：分配一个 Date对象，并初始化，以便它代表它被分配的时间，精确到毫秒
        Date d1 = new Date();
        System.out.println(d1);

        //    运行结果    Tue Jan 10 20:17:37 CST 2023


        //public Date(long date)：分配一个 Date对象，并将其初始化为表示从标准基准时间起指定的毫秒数
        long date = 1000*60*60;  // 一个小时
        Date d2 = new Date(date);
        System.out.println(d2);

        //   运行结果     Thu Jan 01 09:00:00 CST 1970
    }
}
