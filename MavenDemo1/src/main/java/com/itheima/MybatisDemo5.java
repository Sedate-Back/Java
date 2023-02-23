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

    Mybatis 代理开发 mapper 根据多条件查询数据

 */


public class MybatisDemo5 {
    @Test
    public void testSelectByCondition() throws Exception{
        // 数据传递
        int status = 1;
        String  companyName = "华为";
        String  brandName = "华为";


        // 数据处理
        companyName = "%" +companyName+ "%";
        brandName = "%" +brandName+ "%";


        // 1. 加载mybatis的核心配置文件 获取sqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2. 获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3. 执行sql语句

        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brands = brandMapper.selectByCondition(status, companyName, brandName);

        System.out.println(brands);


        // 4. 释放资源
        sqlSession.close();

    }


    @Test
    public void testSelectBySingleCondition() throws Exception{
        // 数据传递
        int status = 1;
        String  companyName = "华为";
        String  brandName = "华为";


        // 数据处理
        companyName = "%" +companyName+ "%";
        brandName = "%" +brandName+ "%";


        // 数据存储
        Brand brand = new Brand();
        // brand.setStatus(status);
        // brand.setBrandName(brandName);
        brand.setCompanyName(companyName);




        // 1. 加载mybatis的核心配置文件 获取sqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2. 获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3. 执行sql语句

        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brands = brandMapper.selectSingleByCondition(brand);

        System.out.println(brands);


        // 4. 释放资源
        sqlSession.close();

    }
}
