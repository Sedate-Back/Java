package com.itheima.MyCharStream;

import java.io.*;

public class CharStreamDemo6 {
    public static void main(String[] args) throws IOException {

        //创建字符缓冲输出流
        BufferedWriter bw = new BufferedWriter(new                                                          FileWriter("myCharStream\\bw.txt"));

        //写数据
        for (int i = 0; i < 10; i++) {
            bw.write("hello" + i);
            //bw.write("\r\n");
            bw.newLine();
            bw.flush();
        }

        //释放资源
        bw.close();

        //创建字符缓冲输入流
        BufferedReader br = new BufferedReader(new                                                          FileReader("myCharStream\\bw.txt"));

        String line;
        while ((line=br.readLine())!=null) {
            System.out.println(line);
        }

        br.close();
    }
}
