package com.itheima.ObjectStream;

import java.io.*;

public class ObjectStreamDemo1 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("a.txt"));

        Student s1 = new Student("佟丽娅", 30);
        objectOutputStream.writeObject(s1);

        objectOutputStream.close();

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("a.txt"));
        Object readObject = objectInputStream.readObject();
        Student s = (Student) readObject;
        System.out.println(s.getName() + "----" + s.getAge());
    }
}
