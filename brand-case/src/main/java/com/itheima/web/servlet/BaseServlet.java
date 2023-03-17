package com.itheima.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//


public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 获取请求路径名： 因为方法名跟路径名我们设置为一致的，所以获取后可以通过访问路径来调用方法
        String requestURI = req.getRequestURI(); // 实例： /brand-case/brand/selectAll

        // 2. 获取最后一个字段区间
        int index = requestURI.lastIndexOf("/");
        String methodName = requestURI.substring(index + 1); // 获取最后一个/的索引，+1 就去掉索引 获得方法名
        // System.out.println(methodName);
        // 3. 执行方法 => class的反射应用
        // 3.1 获取字节码对象class
        Class<? extends BaseServlet> aClass = this.getClass();
        // 3.2 获取方法对象 => getMethod需要传递， 1.具体的方法名称，2.对应方法的参数的字节码class文件类型
        try {
            Method method = aClass.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            // 3.3 执行
            method.invoke(this, req, resp); // 这里的this指的是调用的类，例如以后是BrandServlet调用，this指的就是BrandServlet
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
