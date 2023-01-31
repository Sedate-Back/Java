package com.itheima.MyFileStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class InputStreamDemo2 {
   // 拷贝文件
   public static void main(String[] args) throws IOException {
       FileInputStream fileInputStream = new FileInputStream("b.txt");
       FileOutputStream fileOutputStream = new FileOutputStream("b_copy.txt");

       // 读数据后写入
       int by;
       while ((by=fileInputStream.read()) != -1){
           fileOutputStream.write(by);
       }

       fileInputStream.close();
       fileOutputStream.close();

   }
}
