package com.itheima.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCDemo3 {
    /*

    主要演示的是，查询器，executeQuery 也是SQL中最重要的查询
   查询后获得的对象，是resultSet对象， 特性是有指针，可以用next去将指针下一一行

   */

    public static void main(String[] args) throws Exception{
        // 1. 注册驱动
        // Class.forName("com.mysql.jdbc.Driver");  // 在5版本后，不需要这么写，也会自动注册驱动

        // 2. 获得连接( 需要传递url、 username、 password参数）

        String url = "jdbc:mysql://127.0.0.1:3306/javadb1";  // 固定搭配
        // 如果想要运行的时候不提示SSL，就可以把url这么写
        String urlCloseSSL = "jdbc:mysql://127.0.0.1:3306/javadb1?useSSL=False";


        String username = "root";
        String password = "1234";

        Connection connection = DriverManager.getConnection(urlCloseSSL, username, password);

        // 3. 定义SQL语句 （演示我们的查询，SQL也要写成查询的语句）
        String sql = "select *from stu";

        // 4. 获取执行SQL语句的statement对象
        Statement statement = connection.createStatement();

        // 5. 执行查询语句
        ResultSet resultSet = statement.executeQuery(sql);

        // 6. 因为是集合，而我们不知道集合有多少，所以需要while遍历， resultSet.next 如果有单位，就返回True；没有就返回false
        while (resultSet.next()){
            // 6.1 获取Set集合中的数据
            int id =resultSet.getInt("id");
            String name = resultSet.getString("name");
            int age =resultSet.getInt("age");
            String sex = resultSet.getString("sex");
            String address = resultSet.getString("address");
            int math =resultSet.getInt("math");
            int english =resultSet.getInt("english");
            String hire_date = resultSet.getString("hire_date");

            System.out.println(id);
            System.out.println(name);
            System.out.println(age);
            System.out.println(sex);
            System.out.println(address);
            System.out.println(math);
            System.out.println(english);
            System.out.println(hire_date);

            System.out.println("==========================");

        }

        // 7 释放资源
        resultSet.close();
        statement.close();
        connection.close();

    }
}
