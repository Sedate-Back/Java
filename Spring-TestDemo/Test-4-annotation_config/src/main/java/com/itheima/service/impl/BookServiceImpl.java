package com.itheima.service.impl;

import com.itheima.dao.BookDao;
import com.itheima.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

//@Component

@Service  // 服务层注解
public class BookServiceImpl implements BookService {

    @Autowired
    @Qualifier("bookDao")
    private BookDao bookDao;
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

//    @Value("zhangsan") // 用这个方式可以注入值给基本数据类型
    @Value("${jdbc.username}") // 读取配置文件的值 -》 需要去config类中配置
    private String name;

    public void save() {
        System.out.println("Book service save");
        bookDao.save();
        System.out.println(name);
    }
}
