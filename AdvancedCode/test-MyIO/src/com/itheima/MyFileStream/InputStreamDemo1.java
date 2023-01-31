package com.itheima.MyFileStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class InputStreamDemo1 {
    // 输入流

    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("a.txt");
        int read;
        while ((read= fis.read()) != -1){
            System.out.println((char)read);
        }
        fis.close();
    }
}
