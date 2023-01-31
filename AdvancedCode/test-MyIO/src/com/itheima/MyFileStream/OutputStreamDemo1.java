package com.itheima.MyFileStream;

import java.io.FileOutputStream;
import java.io.IOException;

public class OutputStreamDemo1 {
    public static void main(String[] args) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("a.txt");
        fileOutputStream.write("I love hsq".getBytes());
        fileOutputStream.close();

        FileOutputStream fileOutputStream2 = new FileOutputStream("b.txt");
        byte[] bytes = "abcde".getBytes();
        fileOutputStream2.write(bytes);
        fileOutputStream2.close();

        FileOutputStream fileOutputStream3 = new FileOutputStream("b.txt", true);
        fileOutputStream3.write(bytes);
        fileOutputStream3.close();
    }
}
