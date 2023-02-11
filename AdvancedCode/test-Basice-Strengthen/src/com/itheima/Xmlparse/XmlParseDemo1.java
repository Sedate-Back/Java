package com.itheima.Xmlparse;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XmlParseDemo1 {
    public static void main(String[] args) throws DocumentException {
        // 1. 获取DOM对象 DOM就是文件的整体
        SAXReader saxReader = new SAXReader();

        // 2. 用read方法读取DOM对象的内容
        Document document = saxReader.read(new File("test-Basice-Strengthen/src/com/itheima/Xmlparse/xml/student.xml"));

        // 3. 获取DOM对象的第一个根标签 -> students
        Element rootElement = document.getRootElement();

        // 4. 获取students的子标签 -> student xml中有多少个student对象，存储到List中
        List<Element> students = rootElement.elements("student");

        // 5. 创建列表存储学生对象
        ArrayList<Student> list = new ArrayList<>();

        // 6。 遍历XML中student的个数， 来获取id、name、age
        for (Element student : students) {
            // 获取id
            Attribute attribute = student.attribute("id");
            String id = attribute.getValue();

            // 获取name
            Element nameElement = student.element("name");
            String name = nameElement.getText();

            // 获取age
            Element ageElement = student.element("age");
            String age = ageElement.getText();

            // student构造方法创建对象 并添加到学生容器list中
            Student s = new Student(id, name, Integer.parseInt(age));
            list.add(s);

        }

        for (Student student : list) {
            System.out.println(student);
        }



    }
}
