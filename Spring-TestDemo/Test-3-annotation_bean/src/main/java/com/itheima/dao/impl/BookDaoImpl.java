package com.itheima.dao.impl;

import com.itheima.dao.BookDao;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//@Component("bookDao") -> 最高层注解
@Repository("bookDao") // 数据层注解
@Scope("singleton") // 作用范围 单例模式

public class BookDaoImpl implements BookDao {
    public void save() {
        System.out.println("book dao save");
    }

    @PostConstruct // 字面上理解  在构造之后 构造bean类后运行init方法
    public void init() {
        System.out.println("book dao init");
    }

    @PreDestroy //  字面上理解 在摧毁Bean类之前 运行destroy方法
    public void destroy() {
        System.out.println("book dao destroy");
    }

}
