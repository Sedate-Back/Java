package com.itheima;

import com.itheima.mapper.BrandMapper;
import com.itheima.mapper.StuMapper;
import com.itheima.pojo.Brand;
import com.itheima.pojo.Stu;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/*

    Mybatis 代理开发 mapper 查询所有数据

 */


public class MybatisDemo3 {
    public static void main(String[] args) throws IOException {
        // 1. 加载mybatis的核心配置文件 获取sqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2. 获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3. 执行sql语句

        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brandList = brandMapper.selectAll();
        System.out.println(brandList);


        // 4. 释放资源
        sqlSession.close();

    }


}
