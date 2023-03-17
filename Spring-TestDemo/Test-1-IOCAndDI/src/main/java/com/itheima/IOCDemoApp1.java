package com.itheima;

import com.itheima.service.BookService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IOCDemoApp1 {
    public static void main(String[] args) {
        // 1. 创建IOC对象 加载spring的核心配置文件xml
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        // 2. 从IOC容器中获取bean类（bookService对象）
        BookService bookService = (BookService) ctx.getBean("bookService");

        // 3. 调用Bean类的save方法
        bookService.save();
    }
}
