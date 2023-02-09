package com.itheima.Xmlparse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;

public class XmlParseDemo1 {
    public static void main(String[] args) throws DocumentException {
        // 1. 获取DOM对象
        SAXReader saxReader = new SAXReader();

        Document document = saxReader.read(new File("AdvancedCode/test-Basice-Strengthen/src/com/itheima/Xmlparse/xml/student.xml"));

        Element rootElement = document.getRootElement();

        rootElement.elements();
    }
}
