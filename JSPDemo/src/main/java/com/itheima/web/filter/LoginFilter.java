package com.itheima.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 这是Filter的初始化方法
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 经过此Filter会执行的主要方法

        // 1. 获取session中的账号
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        // 2. 因为我们在前面的url中，配置了所有路径都拦截，所以有一些资源页面（css、js等）都会给拦截，造成页面bug
        // 所以我们要定义一个列表，用于放行
        String[] urls = {"/login.jsp", "/imgs/", "/css/", "/loginServlet", "/register.jsp", "/registerServlet", "/checkCodeServlet", "/register.html", "/selectUserServlet"};

        // 然后判断我们当前访问的是不是现在的资源路径
        String url = request.getRequestURL().toString(); // /JSPDemo/login.jsp

        // 遍历我们自己创建的列表
        for (String s : urls) {
            // url包不包含 我们定义的列表，例如login.jsp
            if (url.contains(s)){
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }


        // 3. 判断session中有没有user
        Object user = request.getSession().getAttribute("user");

        if (user != null){
            // 不是null 就说明登陆过了 可以查看其他页面
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            // null 就返回登录页面，并展示错误信息
            request.setAttribute("login_msg", "您尚未登录！");
            request.getRequestDispatcher("/login.jsp").forward(request, servletResponse);
        }


    }

    @Override
    public void destroy() {
        // 这是Filter的关闭时候会运行的方法
    }
}
