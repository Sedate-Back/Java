package com.itheima.MyIO;

import java.io.File;
import java.io.IOException;

public class FileDemo1 {
    public static void main(String[] args) throws IOException {
        // 创建文件
        File file1 = new File("E:\\java\\a.md");
        System.out.println(file1.createNewFile());

        // 创建文件夹 mkdirs 可以创建多个多级的文件夹
        File file2 = new File("E:\\java\\test\\aa\\bb\\cc");
        System.out.println(file2.mkdirs());
    }



}
