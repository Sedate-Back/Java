package com.itheima.MyBuffered;

import java.io.*;

public class BufferedDemo1 {
    public static void main(String[] args) throws IOException {
        // 缓冲流构造方法
        // 1. 输出流
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("a.txt"));

        // 写数据
        bufferedOutputStream.write("hello\r".getBytes());
        bufferedOutputStream.write("world\r".getBytes());

        // 释放资源
        bufferedOutputStream.close();

        // 2. 输入流
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("a.txt"));

        // 读数据
        int by;
        while ((by = bufferedInputStream.read()) != -1){
            System.out.println((char) by);
        }

        // 释放资源
        bufferedInputStream.close();
    }
}
