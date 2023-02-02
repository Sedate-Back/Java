package com.itheima.ObjectStream;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class ObjectStreamWork1 {
    /**
     *  read():
     *      读取到文件末尾返回值是 -1
     *  readLine():
     *      读取到文件的末尾返回值 null
     *  readObject():
     *      读取到文件的末尾 直接抛出异常
     *  如果要序列化的对象有多个,不建议直接将多个对象序列化到文件中,因为反序列化时容易出异常
     *      建议: 将要序列化的多个对象存储到集合中,然后将集合序列化到文件中
     */
    public static void main(String[] args) throws Exception {
        /*// 序列化
        //1.创建序列化流对象
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("myCode\\oos.txt"));
        ArrayList<Student> arrayList = new ArrayList<>();
        //2.创建多个学生对象
        Student s = new Student("佟丽娅",30);
        Student s01 = new Student("佟丽娅",30);
        //3.将学生对象添加到集合中
        arrayList.add(s);
        arrayList.add(s01);
        //4.将集合对象序列化到文件中
        oos.writeObject(arrayList);
        oos.close();*/

        // 反序列化
        //5.创建反序列化流对象
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("myCode\\oos.txt"));
        //6.将文件中的对象数据,读取到内存中
        Object obj = ois.readObject();
        ArrayList<Student> arrayList = (ArrayList<Student>)obj;
        ois.close();
        for (Student s : arrayList) {
            System.out.println(s.getName() + "," + s.getAge());
        }
    }
}
