package com.itheima.MyCharStream;


import java.io.FileReader;
import java.io.IOException;

public class CharStreamDemo3 {
    public static void main(String[] args) throws IOException {

        FileReader fr = new FileReader("myCharStream\\b.txt");

        //int read()：一次读一个字符数据
//        int ch;
//        while ((ch=fr.read())!=-1) {
//            System.out.print((char)ch);
//        }

        //int read(char[] cbuf)：一次读一个字符数组数据
        char[] chs = new char[1024];
        int len;
        while ((len = fr.read(chs)) != -1) {
            System.out.print(new String(chs, 0, len));
        }

        //释放资源
        fr.close();
    }
}
