package com.itheima.MyServlet;

/*
     用继承HttpServlet的方式来定义接口
 */

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;


@WebServlet("/demo2")
public class Demo2 extends MyHttpServlet {
    @Override
    protected void doPost(ServletRequest req, ServletResponse res) {
        System.out.println("get...");
    }

    @Override
    protected void doGet(ServletRequest req, ServletResponse res) {
        System.out.println("post");
    }
}
