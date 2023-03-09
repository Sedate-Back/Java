package com.itheima.MyServlet;

/*
     request/ response 相关内容
     1. request请求中的内容：
        请求行
        请求头
        请求体（ get方法或post方法）：
            1. get是展示在url中的 getQueryString，
            2.post是展示在请求体中的，得用字符、字节流取数据getReader
     2. 获取传递到后台的值
        1、 get和post都能用 getParameterMap() 返回的是字符串集合key， 需要循环拿value
     3. 解决传值中文出现的乱码问题
        1. post可以直接设置 ： request.setCharacterEncoding("UTF-8");
        2. get 比较麻烦 先转为字节数组 再用字节数组解码
                username  = new String(username.getBytes(StandardCharsets.ISO_8859_1),StandardCharsets.UTF_8);

     4. 请求转发
        当用户返回一个url时，会访问后重新转发到其他地址。

        request.getRequestDispatcher("/req6").forward(request,response);

 */

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;


@WebServlet("/demo3")
public class Demo3 extends MyHttpServlet {
    @Override
    protected void doPost(ServletRequest req, ServletResponse res) throws IOException {
        this.doGet(req, res);
    }

    @Override
    protected void doGet(ServletRequest req, ServletResponse res) throws IOException {
        //GET请求逻辑
        System.out.println("get....");
        //1. 获取所有参数的Map集合
        Map<String, String[]> map = req.getParameterMap();
        for (String key : map.keySet()) {
            // username:zhangsan lisi
            System.out.print(key+":");

            //获取值
            String[] values = map.get(key);
            for (String value : values) {
                System.out.print(value + " ");
            }

            System.out.println();
        }
    }
}
