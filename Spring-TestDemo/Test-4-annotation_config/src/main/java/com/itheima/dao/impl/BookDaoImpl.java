package com.itheima.dao.impl;

import com.itheima.dao.BookDao;
import org.springframework.stereotype.Repository;

//@Component("bookDao") -> 最高层注解
@Repository("bookDao") // 数据层注解

public class BookDaoImpl implements BookDao {
    public void save() {
        System.out.println("book dao save");
    }
}
