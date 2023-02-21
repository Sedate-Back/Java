## Mybatis笔记

### 1. 创建项目

- 打开idle，在新建项目的时候，选择右边的架构Maven-Archetype，

  - 详细信息选择：
    - Archetype 选择 quickStart
    - 版本 ： 1.1
    - SDK选择1.8版本
    - 1.8版本对应的是8版本的JDK
    - 高级设置不需要修改
  - 点击创建
  - 创建好后，编译器会默认生成src文件夹和pom.xml

- 在src文件夹下的main和test文件夹，分别创建新的文件夹resources

  - 此文件夹用来存放mapper配置文件或其他配置文件

- 在两个resources文件夹下创建配置文件mybatis-config.xml

  - 填入一下内容

  - ```xml
    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE configuration
            PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
            "http://mybatis.org/dtd/mybatis-3-config.dtd">
    <!--configuration核心配置文件-->
    <configuration>
        <environments default="development">
            <environment id="development">
                <transactionManager type="JDBC"/>
                <dataSource type="POOLED">
    <!--                数据库连接信息-->
                    <property name="driver" value="com.mysql.jdbc.Driver"/>
                    <property name="url" value="jdbc:mysql:///javadb1?useSSl=false"/>
                    <property name="username" value="root"/>
                    <property name="password" value="1234"/>
                </dataSource>
            </environment>
        </environments>
    
        <mappers>
            <!-- 加载sql映射文件-->
    <!--        <mapper resource="com/itheima/mapper/StuMapper.xml"/>-->
    
    <!--        用Mapper方式加载sql映射文件 : 好处是可以批量添加sql映射文件，项目过大的时候可以避免需要重复添加 -->
            <package name="com.itheima.mapper"/>
    
        </mappers>
    </configuration>
    
    ```

  - 配置文件包含，数据库连接信息、sql映射文件路径等配置

- 回到初始化项目的时候创建好的pom配置文件

  - 这个文件主要是用来配置与整个maven项目的依赖，例如需要用到什么包、版本、信息等

  - 例如

  - ```xml
    <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
      <modelVersion>4.0.0</modelVersion>
    
      <groupId>com.itheima.Demo</groupId>
      <artifactId>MavenDemo1</artifactId>
      <version>1.0-SNAPSHOT</version>
      <packaging>jar</packaging>
    
      <name>MavenDemo1</name>
      <url>http://maven.apache.org</url>
    
      <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
      </properties>
    
      <dependencies>
        <dependency>
          <groupId>junit</groupId>
          <artifactId>junit</artifactId>
          <version>3.8.1</version>
          <scope>test</scope>
        </dependency>
    
    
        <dependency>
          <groupId>org.mybatis</groupId>
          <artifactId>mybatis</artifactId>
          <version>3.5.5</version>
        </dependency>
    
        <dependency>
          <groupId>mysql</groupId>
          <artifactId>mysql-connector-java</artifactId>
          <version>5.1.46</version>
        </dependency>
    
        <dependency>
          <groupId>mysql</groupId>
          <artifactId>mysql-connector-java</artifactId>
          <version>5.1.46</version>
        </dependency>
    
        <!-- 添加slf4j日志api -->
        <dependency>
          <groupId>org.slf4j</groupId>
          <artifactId>slf4j-api</artifactId>
          <version>1.7.20</version>
        </dependency>
        <!-- 添加logback-classic依赖 -->
        <dependency>
          <groupId>ch.qos.logback</groupId>
          <artifactId>logback-classic</artifactId>
          <version>1.2.3</version>
        </dependency>
        <!-- 添加logback-core依赖 -->
        <dependency>
          <groupId>ch.qos.logback</groupId>
          <artifactId>logback-core</artifactId>
          <version>1.2.3</version>
        </dependency>
    
      </dependencies>
    </project>
    
    ```

  - 在更新完pom文件的时候，需要点击文件右上角的刷新按钮，才能将配置刷新给maven包管理工具

- 至此，项目的创建完毕。

  - 小注意点：如果运行发生版本错误（5、7错误）
    - 1. 版本jdk是否匹配版本
      2. maven的配置文件setting.xml里面默认的jdk版本是什么，如果不是目前项目的jdk版本，就修改成1.8（全局修改）





### 2. 创建pojo

- pojo存放的是，java实体类，用于mybatis对数据进行短暂的存储和保存的，同时读取到内存后可以进行增删改查的操作

#### 操作步骤

1. 在main和test的文件目录下创建pojo文件夹，路径为com.itheima.pojo



### 3. 基于Mapper来编写案例

- 需要创建3个文件，来运行Mapper



1. 创建pojo实例用来接受数据库的返回

```java
package com.itheima.pojo;

public class Brand {

    // id 主键
    private Integer id;
    // 品牌名称
    private String brandName;
    // 企业名称
    private String companyName;
    // 排序字段
    private Integer ordered;
    // 描述信息
    private String description;
    // 状态：0：禁用  1：启用
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getOrdered() {
        return ordered;
    }

    public void setOrdered(Integer ordered) {
        this.ordered = ordered;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", brandName='" + brandName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", ordered=" + ordered +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}

```



2. 创建statement对象来操作sql语句的接口类

```java
package com.itheima.mapper;

import com.itheima.pojo.Brand;
import com.itheima.pojo.Stu;

import java.util.List;

public interface BrandMapper {

    public List<Brand> selectAll();
}

```



3. 创建BrandMapper的配置文件，位置于main/resource/com/itheima/mapper.

- 创建BrandMaapper.xml文件

- ```xml
  <?xml version="1.0" encoding="UTF-8" ?>
  <!DOCTYPE mapper
          PUBLIC "-//mybatis.org//DTD Magger 3.0//EN"
          "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
  
  <mapper namespace="com.itheima.mapper.BrandMapper">
  
  
      <select id="selectAll" resultType="com.itheima.pojo.Brand">
          select *
          from tb_brand;
      </select>
  </mapper>
  ```



4.  在配置完后，需要到mybatis配置文件中，将sql映射文件路径进行更换

- 这里可以用相对路径以此添加映射文件

```xml
<!-- 加载sql映射文件-->
<mapper resource="com/itheima/mapper/StuMapper.xml"/>
```



- 也可以基于mapper用于同路径下的包收集

```xml
<!--        用Mapper方式加载sql映射文件 : 好处是可以批量添加sql映射文件，项目过大的时候可以避免需要重复添加 -->
        <package name="com.itheima.mapper"/>
```





5.  基本配置完毕，要创建main主线程来测试 或 用test 进行测试

```java
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

```