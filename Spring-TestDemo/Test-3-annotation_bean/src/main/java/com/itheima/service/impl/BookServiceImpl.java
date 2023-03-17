package com.itheima.service.impl;

import com.itheima.dao.BookDao;
import com.itheima.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Component

@Service  // 服务层注解
public class BookServiceImpl implements BookService {

    @Autowired  // 自动注解 根据类的名称进行 可以不用写set方法
    @Qualifier("bookDao") // 用这个注解指定对应的类是哪个类
    private BookDao bookDao;

//    public void setBookDao(BookDao bookDao) {
//        this.bookDao = bookDao;
//    }
//    @Value("zhangsan") // 用这个方式可以注入值给基本数据类型
//    @Value("${name}") // 读取配置文件的值 -》 需要去config类中配置
    private String name;

    public void save() {
        System.out.println("Book service save");
        bookDao.save();
    }
}
