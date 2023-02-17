package com.itheima.jdbc;

import java.net.URL;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;

public class JDBCDemo {
    // 本Demo用来记录如何用JDBC连接数据库
    public static void main(String[] args) throws Exception {
        // 1. 注册驱动
        Class.forName("com.mysql.jdbc.Driver");  // 在5版本后，不需要这么写，也会自动注册驱动

        // 2. 获得连接( 需要传递url、 username、 password参数）

        String url = "jdbc:mysql://127.0.0.1:3306/javadb1";  // 固定搭配
        // 如果想要运行的时候不提示SSL，就可以把url这么写
        String urlCloseSSL = "jdbc:mysql://127.0.0.1:3306/javadb1?useSSL=False";



        String username = "root";
        String password = "1234";

        Connection connection = DriverManager.getConnection(urlCloseSSL, username, password);

        // 3. 定义SQL语句（就是你想要执行的SQL语句)
        String sql = "update stu set age = 54 where id = 1";

        // 4. 获取执行SQL语句的statement对象
        Statement statement = connection.createStatement();

        // 5. 执行SQL语句
        int i = statement.executeUpdate(sql);

        // 6. 返回结果
        System.out.println(i);

        // 7. 释放资源
        statement.close();
        connection.close();

    }
}
