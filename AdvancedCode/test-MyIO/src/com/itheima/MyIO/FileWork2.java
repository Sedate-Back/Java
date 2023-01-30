package com.itheima.MyIO;

import java.io.File;

public class FileWork2 {
    /*+ 案例需求

            删除一个多级文件夹

+ 实现步骤

  + 定义一个方法,接收一个File对象
  + 遍历这个File对象,获取它下边的每个文件和文件夹对象
  + 判断当前遍历到的File对象是文件还是文件夹
  + 如果是文件,直接删除
  + 如果是文件夹,递归调用自己,将当前遍历到的File对象当做参数传递
  + 参数传递过来的文件夹File对象已经处理完成,最后直接删除这个空文件夹*/

    public static void main(String[] args) {
        File file = new File("aaa");
        deleteDir(file);
        System.out.println("程序结束！ ");
    }
    //1.定义一个方法,接收一个File对象
    private static void deleteDir(File src) {
        //先删掉这个文件夹里面所有的内容.
        //递归 方法在方法体中自己调用自己.
        //注意: 可以解决所有文件夹和递归相结合的题目
        //2.遍历这个File对象,获取它下边的每个文件和文件夹对象
        File[] files = src.listFiles();
        //3.判断当前遍历到的File对象是文件还是文件夹
        for (File file : files) {
            //4.如果是文件,直接删除
            if(file.isFile()){
                file.delete();
            }else{
                //5.如果是文件夹,递归调用自己,将当前遍历到的File对象当做参数传递
                deleteDir(file);//参数一定要是src文件夹里面的文件夹File对象
            }
        }
        //6.参数传递过来的文件夹File对象已经处理完成,最后直接删除这个空文件夹
        src.delete();
    }
}
