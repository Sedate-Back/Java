package com.itheima.MyBuffered;

import java.io.*;

public class BufferedDemo2 {
    public static void main(String[] args) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(""));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(""));

        byte[] bys = new byte[1024];
        int len;
        while ((len = bis.read(bys)) != -1) {
            bos.write(bys, 0, len);
        }

        bos.close();
        bis.close();
    }
}

