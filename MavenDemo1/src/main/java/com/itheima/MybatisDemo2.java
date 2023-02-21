package com.itheima;

import com.itheima.mapper.StuMapper;
import com.itheima.pojo.Stu;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/*

    Mybatis 代理开发 mapper

 */


public class MybatisDemo2 {
    public static void main(String[] args) throws IOException {
        // 1. 加载mybatis的核心配置文件 获取sqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2. 获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3. 执行sql语句
        // List<Stu> stus = sqlSession.selectList("test.selectAll");
        // System.out.println(stus);

        // 3. Mapper代理执行sql语句

        // 3.1 定义与SQL映射文件同名的Mapper接口，并且将Mapper接口和SQL映射文件放置在同一目录下
        // 3.2 设置SQL映射文件的namespace属性为Mapper接口全限定名
        // 3.3 在 Mapper 接口中定义方法，方法名就是SQL映射文件中sql语句的id，并保持参数类型和返回值类型一致
        //  编码
        //  通过 SqlSession 的 getMapper方法获取 Mapper接口的代理对象
        //  调用对应方法完成sql的执行
        StuMapper stuMapper = sqlSession.getMapper(StuMapper.class);
        List<Stu> stus = stuMapper.selectAll();
        System.out.println(stus);


        // 4. 释放资源
        sqlSession.close();

    }
}
