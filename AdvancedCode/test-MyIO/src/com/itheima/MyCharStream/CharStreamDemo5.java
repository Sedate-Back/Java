package com.itheima.MyCharStream;

import java.io.*;

public class CharStreamDemo5 {
    public static void main(String[] args) throws IOException {
        //BufferedWriter(Writer out)
        BufferedWriter bw = new BufferedWriter(new FileWriter(""));
        bw.write("hello\r");
        bw.write("world\r");
        bw.close();

        //BufferedReader(Reader in)
        BufferedReader br = new BufferedReader(new FileReader(""));

        //一次读取一个字符数据
//        int ch;
//        while ((ch=br.read())!=-1) {
//            System.out.print((char)ch);
//        }

        //一次读取一个字符数组数据
        char[] chs = new char[1024];
        int len;
        while ((len=br.read(chs))!=-1) {
            System.out.print(new String(chs,0,len));
        }

        br.close();
    }
}
