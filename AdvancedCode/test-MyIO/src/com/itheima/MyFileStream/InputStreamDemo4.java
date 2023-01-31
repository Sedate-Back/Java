package com.itheima.MyFileStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class InputStreamDemo4 {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("a.txt");

        FileOutputStream fos = new FileOutputStream("a_copy.txt");

        byte[] bys = new byte[1024];
        int len;
        while ((len=fis.read(bys)) != -1){
            fos.write(bys, 0 ,len);
        }
        fos.close();
        fis.close();
    }
}
