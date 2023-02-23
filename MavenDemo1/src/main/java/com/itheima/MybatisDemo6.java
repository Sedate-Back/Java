package com.itheima;

import com.itheima.mapper.BrandMapper;
import com.itheima.pojo.Brand;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/*

    Mybatis 代理开发 mapper 添加数据 和 添加后返回id值

 */


public class MybatisDemo6 {
    @Test
    public void testAddAll() throws IOException {

        // 数据传递
        int status = 1;
        String  companyName = "Apple company";
        String  brandName = "Apple phone";
        int ordered = 100;
        String description = "Best best!";

        Brand brand = new Brand();
        brand.setBrandName(brandName);
        brand.setCompanyName(companyName);
        brand.setOrdered(ordered);
        brand.setDescription(description);
        brand.setStatus(status);

        // 1. 加载mybatis的核心配置文件 获取sqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2. 获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true);


        // 3. 执行sql语句

        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        brandMapper.addAll(brand);

        // 4. 释放资源
        sqlSession.close();

    }

    @Test
    public void testAddReturnId() throws Exception{
        // 数据传递
        int status = 1;
        String  companyName = "Apple company";
        String  brandName = "Apple phone";
        int ordered = 100;
        String description = "Best best!";

        Brand brand = new Brand();
        brand.setBrandName(brandName);
        brand.setCompanyName(companyName);
        brand.setOrdered(ordered);
        brand.setDescription(description);
        brand.setStatus(status);

        // 1. 加载mybatis的核心配置文件 获取sqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2. 获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true);


        // 3. 执行sql语句

        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        brandMapper.addReturnId(brand);
        System.out.println(brand.getId());


        // 4. 释放资源
        sqlSession.close();

    }

}
