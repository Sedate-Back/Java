package com.itheima.MyIO;

import java.io.File;
import java.io.IOException;

public class FileWork1 {
    /* + 案例需求

    在当前模块下的aaa文件夹中创建一个a.txt文件

+ 实现步骤

  + 创建File对象,指向aaa文件夹
  + 判断aaa文件夹是否存在,如果不存在则创建
  + 创建File对象,指向aaa文件夹下的a.txt文件
  + 创建这个文件 Z
  */
    public static void main(String[] args) throws IOException {
        File file1 = new File("aaa");
        boolean exists = file1.exists();
        // 如果文件夹不存在 生成文件夹
        if (exists == false){
            boolean mkdirs = file1.mkdirs();
            if (mkdirs == true){
                System.out.println("创建成功！ ");
            }
        }
        File file2 = new File(file1, "a.txt");
        boolean newFile = file2.createNewFile();
        if (newFile == true){
            System.out.println("创建成功！ ");
        }


    }
}
