package com.itheima.MyCharStream;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class CharStreamDemo1 {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String s = "中国";
        byte[] bytes = s.getBytes();
        System.out.println(Arrays.toString(bytes));
        // 默认使用 UTF-8  一个中文占3个字节
        // [-28, -72, -83, -27, -101, -67]

        byte[] gbks = s.getBytes("GBK");
        System.out.println(Arrays.toString(gbks));
        // [-42, -48, -71, -6]
        // GBK一个中文占2个字节

    }
}
