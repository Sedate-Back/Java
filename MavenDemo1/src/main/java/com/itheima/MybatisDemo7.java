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

/*

    Mybatis 代理开发 mapper 修改数据 和删除数据， 批量删除数据

 */


public class MybatisDemo7 {
    @Test
    public void testUpdateAll() throws IOException {

        // 数据传递
        int status = 1;
        String  companyName = "Apple";
        String  brandName = "Apple phone";
        int ordered = 50;
        String description = "Best best and good!";
        int id = 6;

        Brand brand = new Brand();
        brand.setBrandName(brandName);
        brand.setCompanyName(companyName);
        brand.setOrdered(ordered);
        brand.setDescription(description);
        brand.setStatus(status);
        brand.setId(id);

        // 1. 加载mybatis的核心配置文件 获取sqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2. 获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true);


        // 3. 执行sql语句

        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        brandMapper.update(brand);

        // 4. 释放资源
        sqlSession.close();

    }

    @Test
    public void testDeleteAll() throws Exception{
        int id = 5;

        // 1. 加载mybatis的核心配置文件 获取sqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2. 获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true);


        // 3. 执行sql语句

        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        brandMapper.deleteById(id);

        // 4. 释放资源
        sqlSession.close();


    }

    @Test
    public void testDeleteMore() throws Exception{
        int[] ids = {4, 5, 6};

        // 1. 加载mybatis的核心配置文件 获取sqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2. 获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true);


        // 3. 执行sql语句

        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        brandMapper.deleteByIds(ids);

        // 4. 释放资源
        sqlSession.close();



    }

}
