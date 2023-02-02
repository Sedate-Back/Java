package com.itheima.ConversionStream;

import java.io.*;

public class ConversionStreamDemo1 {
    public static void main(String[] args) throws IOException {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream("a.txt"), "GBK");
        outputStreamWriter.write("黑马程序员");
        outputStreamWriter.close();


        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream("a.txt"), "GBK");
        int ch;
        while ((ch=inputStreamReader.read())!= -1){
            System.out.println((char) ch);
        }
        inputStreamReader.close();

    }
}
