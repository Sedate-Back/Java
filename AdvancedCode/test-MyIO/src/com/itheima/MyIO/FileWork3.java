package com.itheima.MyIO;

import java.io.File;
import java.util.HashMap;

public class FileWork3 {
    /*案例需求

            统计一个文件夹中每种文件的个数并打印
      实现步骤

+ 定义一个方法,参数是HashMap集合用来统计次数和File对象要统计的文件夹
+ 遍历File对象,获取它下边的每一个文件和文件夹对象
+ 判断当前File对象是文件还是文件夹
+ 如果是文件,判断这种类型文件后缀名在HashMap集合中是否出现过
  + 没出现过,将这种类型文件的后缀名存入集合中,次数存1
  + 出现过,获取这种类型文件的后缀名出现的次数,对其+1,在存回集合中
+ 如果是文件夹,递归调用自己,HashMap集合就是参数集合,File对象是当前文件夹对象

            */
    public static void main(String[] args) {
        // 指定文件夹
        File allDirectory = new File("aaa");

        // 创建Map集合来存储， 键存储后缀 值存储次数
        HashMap<String, Integer> fileMap = new HashMap<>();

        // 调用记数方法
        getCount(fileMap, allDirectory);
        System.out.println(fileMap);
    }

    private static void getCount(HashMap<String, Integer> hm, File file) {
        // 读取文件夹内的文件内容
        File[] files = file.listFiles();
        // 遍历循环内容
        for (File f : files) {
            //3.判断当前File对象是文件还是文件夹
            if(f.isFile()){
                //如果是文件,判断这种类型文件后缀名在HashMap集合中是否出现过
                String fileName = f.getName(); // 获取名称
                String[] fileNameArr = fileName.split("\\."); // 获取名称与后缀的数组

                // 如果数组的长度为2，说明是文件
                if(fileNameArr.length == 2){
                    String fileEndName = fileNameArr[1]; // 获取后缀
                    if(hm.containsKey(fileEndName)){
                        //出现过,获取这种类型文件的后缀名出现的次数,对其+1,在存回集合中
                        Integer count = hm.get(fileEndName);
                        //这种文件又出现了一次.
                        count++;
                        //把已经出现的次数给覆盖掉.
                        hm.put(fileEndName,count); // 将后缀和重新计数元素存储到map中
                    }else{
                        // 没出现过,将这种类型文件的后缀名存入集合中,次数存1
                        hm.put(fileEndName,1);
                    }
                }
            }else{
                //如果是文件夹,递归调用自己,HashMap集合就是参数集合,File对象是当前文件夹对象代码实现
                getCount(hm,f);
            }
        }
    }
}

