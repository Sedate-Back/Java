package com.itheima.MyCharStream;

import java.io.FileWriter;
import java.io.IOException;

public class CharStreamDemo2 {
    public static void main(String[] args) throws IOException {
        FileWriter fw = new FileWriter("");

        //void write(int c)：写一个字符
//        fw.write(97);
//        fw.write(98);
//        fw.write(99);

        //void writ(char[] cbuf)：写入一个字符数组
        char[] chs = {'a', 'b', 'c', 'd', 'e'};
//        fw.write(chs);

        //void write(char[] cbuf, int off, int len)：写入字符数组的一部分
//        fw.write(chs, 0, chs.length);
//        fw.write(chs, 1, 3);

        //void write(String str)：写一个字符串
//        fw.write("abcde");

        //void write(String str, int off, int len)：写一个字符串的一部分
//        fw.write("abcde", 0, "abcde".length());
        fw.write("abcde", 1, 3);

        //释放资源
        fw.close();
    }
}
