package com.itheima.service.serviceImpl;

import com.itheima.dao.BookDao;
import com.itheima.dao.daoImp.BookDaoImpl;
import com.itheima.service.BookService;

public class BookServiceImpl implements BookService {
    private BookDao bookDao;
    public void save() {
        System.out.println("book service save...");
        bookDao.save();
    }

    // 因为上面的成员变量中，去掉了new创建对象的代码，所以需要set方法来进行与配置文件的属性绑定
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }
}
