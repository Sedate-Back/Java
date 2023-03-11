package com.itheima.web.linstener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class LinstenerDemo implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        // 加载资源的时候 默认运行
        System.out.println("contextLoaderListener");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
