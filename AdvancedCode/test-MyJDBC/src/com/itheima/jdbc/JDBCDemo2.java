package com.itheima.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JDBCDemo2 {
    /*

    在最后SQL的教学中，我们学习了事务的概念和重要性
    JDBC也提供了可以执行SQL的时候，事务的接口

    conn.setAutoCommit(false) 开启事务

    conn.commit();            提交事务

    con.rollback();           回滚事务


    结合Java的try catch 异常处理，可以保障操作的事务性

     */

    public static void main(String[] args) throws Exception {
        // 1. 注册驱动
        // Class.forName("com.mysql.jdbc.Driver");  // 在5版本后，不需要这么写，也会自动注册驱动

        // 2. 获得连接( 需要传递url、 username、 password参数）

        String url = "jdbc:mysql://127.0.0.1:3306/javadb1";  // 固定搭配
        // 如果想要运行的时候不提示SSL，就可以把url这么写
        String urlCloseSSL = "jdbc:mysql://127.0.0.1:3306/javadb1?useSSL=False";



        String username = "root";
        String password = "1234";

        Connection connection = DriverManager.getConnection(urlCloseSSL, username, password);

        // 3. 定义SQL语句（就是你想要执行的SQL语句)
        String sql1 = "update stu set age = 59 where id = 1";
        String sql2 = "update stu set age = 38 where id = 2";

        // 4. 获取执行SQL语句的statement对象
        Statement statement = connection.createStatement();


        // 5. 事务性执行
        try {
            // 5.1 开启事务
            connection.setAutoCommit(false);

            // 5.2 执行SQL语句
            int i1 = statement.executeUpdate(sql1);

            // 5.3 处理结果
            System.out.println(i1);

            // 假设这个地方出现了错误
            // int i = 3/0;

            int i2 = statement.executeUpdate(sql2);
            System.out.println(i2);


            // 6 提交事务
            connection.commit();

        }catch (Exception e) {
            // 1 如果try发生错误，就执行catch里的回滚
            connection.rollback();
            e.printStackTrace();
        }


        // 7. 释放资源
        statement.close();
        connection.close();
    }
}
