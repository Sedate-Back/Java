package com.itheima.config;

import org.omg.CORBA.CTX_RESTRICT_SCOPE;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

import javax.servlet.Filter;

// web容器配置类 Demo1
/*public class ServletContainersInitConfig extends AbstractDispatcherServletInitializer {

    // 加载Springmvc配置类，生成Springmvc容器，可以理解为Spring的IOC
    protected WebApplicationContext createServletApplicationContext() {
        // 初始化WebApplicationContext对象
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        // 加载指定配置类
        applicationContext.register(SpringMvcConfig.class);
        return applicationContext;
    }

    // 设置由SpringMVC控制器处理的请求映射路径
    protected String[] getServletMappings() {
        return new String[]{"/"}; // 表示所有映射路径的访问都有mvc控制
    }

    // 加载Spring配置类
    protected WebApplicationContext createRootApplicationContext() {
        return null;
    }
}*/
// Demo2
public class ServletContainersInitConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

    protected Class<?>[] getRootConfigClasses() {
        // 加载Spring配置类
        return new Class[0]; // new Class[]{SpringConfig.class};
    }

    protected Class<?>[] getServletConfigClasses() {
        // 加载SpringMVC配置类
        return new Class[]{SpringMvcConfig.class};
    }

    protected String[] getServletMappings() {
        // 设置MVC控制的访问映射路径
        return new String[]{"/"};
    }

    // 定义UTF-8中文解码字符
    @Override
    protected Filter[] getServletFilters(){
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        return new Filter[]{characterEncodingFilter};
    }
}