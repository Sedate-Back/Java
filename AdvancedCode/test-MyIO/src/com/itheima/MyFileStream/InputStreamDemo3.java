package com.itheima.MyFileStream;

import java.io.FileInputStream;
import java.io.IOException;

public class InputStreamDemo3 {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("a.txt");

        byte[] bytes = new byte[1024];
        int len;
        while ((len=fileInputStream.read(bytes)) != -1){
            System.out.println(new String(bytes, 0 , len));
        }
        fileInputStream.close();
    }
}
