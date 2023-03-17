package com.itheima.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

public class JdbcConfig {
    @Value("com.mysql.cj.jdbc.mysql")
    private String driver;

    @Value("jdbc:mysql://localhost:3306/javadb1")
    private String url;

    @Value("root")
    private String userName;

    @Value("1234")
    private String password;



    @Bean // 说明这是一个Bean类 才有class文件可以被调用
    public DataSource dataSource(){

        // demo1 这里面的配置是写死的，要解耦的话，需要注入模式
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(driver);
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(userName);
        druidDataSource.setPassword(password);
        return druidDataSource;
    }
}
