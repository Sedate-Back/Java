记录JSP小Demo的开发细节和内容，本文档从概念、开发流程、开发细节开始说明



## JSPDemo

### 案例说明

- 基于Maven、Tomcat、mybatis三个框架，串联前后端

- 通过按钮的点击，展示数据库的内容，并通过JSP的EL、JSTL技术，将数据嵌入HTML代码块中，美化展示

- 数据库操作：前端页面点击查看、添加、修改，输入对应的数据通过POST请求给到后端，后端进行相应操作

  - 细节：
    - 修改数据的时候，点击后需要先根据ID将内容回显给修改页面
    - 在页面点击的时候，href需要填写的是@WebServlet的端口，再根据设置了@WebServlet的servlet设置跳转的jsp
    - 在jsp页面展示的时候，需要在最前面加上 ` isELIgnored="false" `来展示通过重定向req传递给页面的内容

  

### 案例准备

案例准备分为3个板块，1是项目结构的创建，2是数据库内容的准备，3是项目结构文件内容的补充

#### **1.项目创建**

- 在idea上，新建一个基于maven的项目，**骨架选择web-app**，其他不需要修改
  - 注意： 在文件 - 项目机构中，统一编译版本和运行的java版本，避免后续报错，项目用的是java8
- 在main文件下，**创建java目录**，**并补充软件包**
  - com.itheima.mapper
  - com.itheima.pojo
  - com.itheima.service
  - com.itheima.util
  - com.itheima.web
- 在resources文件夹中，**补充目录com/itheima/mapper**(如果没有resources文件夹的话，就创建)
- 在webapp文件夹中，WEB-INF中补充web.xml文件（一般用骨架创建会补充）
- 在src结构下，创建test文件夹

#### **2.数据库的内容准备**

- 此案例是基于mysql的，所以如果你使用的是其他的数据库，请自行查连接信息

- 创建一个javadb1的数据库，并创建tb_brand的数据表

- 内容如下

  - ```sql
    -- 删除tb_brand表
    drop table if exists tb_brand;
    -- 创建tb_brand表
    create table tb_brand
    (
        -- id 主键
        id           int primary key auto_increment,
        -- 品牌名称
        brand_name   varchar(20),
        -- 企业名称
        company_name varchar(20),
        -- 排序字段
        ordered      int,
        -- 描述信息
        description  varchar(100),
        -- 状态：0：禁用  1：启用
        status       int
    );
    -- 添加数据
    insert into tb_brand (brand_name, company_name, ordered, description, status)
    values ('三只松鼠', '三只松鼠股份有限公司', 5, '好吃不上火', 0),
           ('华为', '华为技术有限公司', 100, '华为致力于把数字世界带入每个人、每个家庭、每个组织，构建万物互联的智能世界', 1),
           ('小米', '小米科技有限公司', 50, 'are you ok', 1);
    
    
    SELECT * FROM tb_brand;
    ```

#### **3.项目文件准备**

- **3.1 mysql类**

  - java/com/iteheima/pojo 下创建一个Javabean类，`Brand.java`

  - 该类用来与sql中的tb_brand表的表头字段对应

  - ```java
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
    
    
        public Brand() {
        }
    
        public Brand(Integer id, String brandName, String companyName, String description) {
            this.id = id;
            this.brandName = brandName;
            this.companyName = companyName;
            this.description = description;
        }
    
        public Brand(Integer id, String brandName, String companyName, Integer ordered, String description, Integer status) {
            this.id = id;
            this.brandName = brandName;
            this.companyName = companyName;
            this.ordered = ordered;
            this.description = description;
            this.status = status;
        }
    
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

  - java/com/iteheima/utils 下创建`SqlSessionFactoryUtil.java`

  - 用来创建基于mybatis操作数据库的对象

  - ```java
    package com.itheima.util;
    
    import org.apache.ibatis.io.Resources;
    import org.apache.ibatis.session.SqlSessionFactory;
    import org.apache.ibatis.session.SqlSessionFactoryBuilder;
    
    import java.io.IOException;
    import java.io.InputStream;
    
    public class SqlSessionFactoryUtils {
        private static SqlSessionFactory sqlSessionFactory;
    
        static {
            //静态代码块会随着类的加载而自动执行，且只执行一次
            try {
                String resource = "mybatis-config.xml";
                InputStream inputStream = Resources.getResourceAsStream(resource);
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    
        public static SqlSessionFactory getSqlSessionFactory(){
            return sqlSessionFactory;
        }
    }
    
    ```

- **3.2 配置文件类**

  - 项目下的`pom.xml`文件补充

  - 补充项目需要用到的包、maven项目的版本、tomcat版本信息等

  - ```xml
    <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
        <modelVersion>4.0.0</modelVersion>
        <groupId>org.example</groupId>
        <artifactId>JSPDemo</artifactId>
        <packaging>war</packaging>
        <version>1.0-SNAPSHOT</version>
        <name>JSPDemo Maven Webapp</name>
        <url>http://maven.apache.org</url>
    
        <properties>
            <maven.compiler.source>8</maven.compiler.source>
            <maven.compiler.target>8</maven.compiler.target>
        </properties>
    
        <dependencies>
            <dependency>
                <groupId>junit</groupId>
                <artifactId>junit</artifactId>
                <version>3.8.1</version>
                <scope>test</scope>
            </dependency>
    
            <!-- mysql -->
            <dependency>
                <groupId>mysql</groupId>
                <artifactId>mysql-connector-java</artifactId>
                <version>8.0.32</version>
            </dependency>
    
            <!-- mybatis -->
            <dependency>
                <groupId>org.mybatis</groupId>
                <artifactId>mybatis</artifactId>
                <version>3.5.6</version>
            </dependency>
    
            <!-- servlet -->
            <dependency>
                <groupId>javax.servlet</groupId>
                <artifactId>javax.servlet-api</artifactId>
                <version>3.1.0</version>
                <scope>provided</scope>
            </dependency>
    
            <!-- jsp  -->
            <dependency>
                <groupId>javax.servlet.jsp</groupId>
                <artifactId>jsp-api</artifactId>
                <version>2.2</version>
                <scope>provided</scope>
            </dependency>
    
            <!--jstl-->
            <dependency>
                <groupId>jstl</groupId>
                <artifactId>jstl</artifactId>
                <version>1.2</version>
            </dependency>
            <dependency>
                <groupId>taglibs</groupId>
                <artifactId>standard</artifactId>
                <version>1.1.2</version>
            </dependency>
    
    
        </dependencies>
        <build>
            <plugins>
                <plugin>
                    <groupId>org.apache.tomcat.maven</groupId>
                    <artifactId>tomcat7-maven-plugin</artifactId>
                    <version>2.2</version>
                </plugin>
            </plugins>
            <finalName>JSPDemo</finalName>
        </build>
    </project>
    
    ```

  - 在JSPDemo/src/main/resources创建`mybatis-config.xml`

  - 写入与本地数据库的连接信息和相关配置

  - ```xml
    <?xml version="1.0" encoding="UTF-8" ?>
    
    <!DOCTYPE configuration
            PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
            "http://mybatis.org/dtd/mybatis-3-config.dtd">
    <configuration>
        <!--起别名-->
        <typeAliases>
            <package name="com.itheima.pojo"/>
        </typeAliases>
    
        <environments default="development">
            <environment id="development">
                <transactionManager type="JDBC"/>
                <dataSource type="POOLED">
                    <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
                    <property name="url" value="jdbc:mysql:///javadb1?useSSL=false"/>
                    <property name="username" value="root"/>
                    <property name="password" value="1234"/>
                </dataSource>
            </environment>
        </environments>
        <mappers>
            
            <!--扫描mapper-->
            <package name="com.itheima.mapper"/>
        </mappers>
    </configuration>
    ```

  - 在resources/com/itheima/mapper下，创建`BrandMapper.xml`

  - 用来与com/itheima/mapper下的`BrandMapper.java`进行匹配

    - 注意点：因为实例类的字段名与数据库的字段名不一致，所以需要用resultMap进行自定义匹配！

  - ```xml
    <?xml version="1.0" encoding="ISO-8859-1"?>
    
    <!DOCTYPE mapper
            PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
            "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
    <mapper namespace="com.itheima.mapper.BrandMapper">
        <resultMap id="brandResultMap" type="brand">
            <result column="brand_name" property="brandName"></result>
            <result column="company_name" property="companyName"></result>
        </resultMap>
    
    </mapper>
    ```

- **3.3 Mapper代码**

  - 在java/com/itheima/mapper下，创建`BrandMapper.java（接口类）`文件

  - 主要用来与配置文件`BrandMapper.xml`进行方法的匹配和sql语句的设置

    - 注意点：如果要使用字段名匹配，需要用到`@ResultMap(id="")`绑定xml文件中的id名，不然会出现前端页面展示不了字段的情况

  - ```java
    package com.itheima.mapper;
    
    
    
    import com.itheima.pojo.Brand;
    import org.apache.ibatis.annotations.Insert;
    import org.apache.ibatis.annotations.ResultMap;
    import org.apache.ibatis.annotations.Select;
    import org.apache.ibatis.annotations.Update;
    
    import java.util.List;
    
    public interface BrandMapper {
    
    
    }
    ```

至此，项目前期准备结束。



#### 4.项目编译



**项目需求**

- 在前端点击”查看所有“，展示数据表格
- 在展示的数据表格最上方，有个按钮可以用来新增数据
- 在展示的数据表格中，可以操作数据进行修改数据，同时点击后展示此条数据的原本内容（内容回显）
  - 上述场景基于**`MVC开发模式`**进行开发



**项目开发**

- 前端页面，均存放在**webapp**目录下

  - **index.html**

    - 当项目run起来之后，点击连接会默认进入index页面，也是按钮 ”点击展示“的地方

      - 注意点： a标签的href绑定的是webservlet绑定的url

    - ```html
      <!DOCTYPE html>
      <html lang="en">
      <head>
          <meta charset="UTF-8">
          <title>JSPDemo</title>
      </head>
      <body>
      
      
      <a href="/JSPDemo/selectAllServlet">查询所有</a>
      </body>
      </html>
      ```

  - **brand.jsp**

    - 点击查询所有后，跳转的页面

      - 注意点：page配置的时候需要加上` isELIgnored="false"`注释，才能把Tomcat的rep传递过来的brand数据读取到页面中
      - 要写JSTL需要引用`<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>`
      - 调用数据的占位符是`${}`
      - 添加操作和修改操作是通过js绑定点击事件跳转到对应的WebServlet页面的

    - ```jsp
      <%--
        Created by IntelliJ IDEA.
        User: Administrator
        Date: 2023/3/8
        Time: 10:03
        To change this template use File | Settings | File Templates.
      --%>
      
      
      <!-- isELIgnored="false" %> 非常重要！！！！ -->
      <%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
      
      <!DOCTYPE html>
      <html lang="en">
      <head>
          <meta charset="UTF-8">
          <title>Title</title>
      </head>
      <body>
      <input type="button" value="新增" id="addAll"><br>
      
      <hr>
      <table border="1" cellspacing="0" width="80%">
          <tr>
              <th>序号</th>
              <th>品牌名称</th>
              <th>企业名称</th>
              <th>排序</th>
              <th>品牌介绍</th>
              <th>状态</th>
              <th>操作</th>
          </tr>
      
          <c:forEach items="${brands}" var="brand" varStatus="status">
              <tr align="center">
                      <%--<td>${brand.id}</td>--%>
                  <td>${status.count}</td>
                  <td>${brand.brandName}</td>
                  <td>${brand.companyName}</td>
                  <td>${brand.ordered}</td>
                  <td>${brand.description}</td>
                  <c:if test="${brand.status == 1}">
                      <td>启用</td>
                  </c:if>
                  <c:if test="${brand.status != 1}">
                      <td>禁用</td>
                  </c:if>
                  <td><a href="/JSPDemo/selectByIdServlet?id=${brand.id}">修改</a> <a href="#">删除</a></td>
              </tr>
          </c:forEach>
      </table>
      
      <script>
          document.getElementById("addAll").onclick = function (){
              location.href = "/JSPDemo/addBrand.jsp"
          }
      
      </script>
      </body>
      </html>
      ```

  - **addBrand.jsp**

    - 点击”新增“之后的页面

    - ```jsp
      <%--
        Created by IntelliJ IDEA.
        User: Administrator
        Date: 2023/3/8
        Time: 21:29
        To change this template use File | Settings | File Templates.
      --%>
      <%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
      <html lang="en">
      <head>
          <meta charset="UTF-8">
          <title>添加品牌</title>
      </head>
      <body>
      <h3>添加品牌</h3>
      <form action="/JSPDemo/addAllServlet" method="post">
          品牌名称：<input name="brandName"><br>
          企业名称：<input name="companyName"><br>
          排序：<input name="ordered"><br>
          描述信息：<textarea rows="5" cols="20" name="description"></textarea><br>
          状态：
          <input type="radio" name="status" value="0">禁用
          <input type="radio" name="status" value="1">启用<br>
      
          <input type="submit" value="提交">
      </form>
      </body>
      </html>
      ```

  - **update.jsp**

    - 点击更新按钮后的页面信息

    - ```jsp
      <%--
        Created by IntelliJ IDEA.
        User: Administrator
        Date: 2023/3/8
        Time: 21:29
        To change this template use File | Settings | File Templates.
      --%>
      <%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
      <html lang="en">
      <head>
        <meta charset="UTF-8">
        <title>修改品牌</title>
      </head>
      <body>
      <h3>修改品牌</h3>
      <form action="/JSPDemo/updateServlet" method="post">
        <input type="hidden" name="id" value="${brand.id}">
        品牌名称：<input name="brandName" value="${brand.brandName}"><br>
        企业名称：<input name="companyName" value="${brand.companyName}"><br>
        排序：<input name="ordered" value="${brand.ordered}"><br>
        描述信息：<textarea rows="5" cols="20" name="description">${brand.description} </textarea><br>
        状态：
        <c:if test="${brand.status == 0}">
          <input type="radio" name="status" value="0" checked>禁用
          <input type="radio" name="status" value="1">启用<br>
        </c:if>
      
        <c:if test="${brand.status == 1}">
          <input type="radio" name="status" value="0" >禁用
          <input type="radio" name="status" value="1" checked>启用<br>
        </c:if>
      
        <input type="submit" value="提交">
      </form>
      </body>
      </html>
      ```

至此，前端页面 编写完毕



------------------------------

下面开始后端代码编写

- 1.首先是库管DAO，也叫数据库响应端，在com/itheima/mapperBrandMapper.java内编译

  - **1.`selectAll`**

    - 查询tb_brand这张表的所有内容的mapper方法

  - **2.`addAll`**

    - 添加数据

  - **3.`selectById`**

    - 根据Id查询数据

  - **4.`update`**

    - 根据查询后的id进行数据更新

  - ```java
    package com.itheima.mapper;
    
    
    
    import com.itheima.pojo.Brand;
    import org.apache.ibatis.annotations.Insert;
    import org.apache.ibatis.annotations.ResultMap;
    import org.apache.ibatis.annotations.Select;
    import org.apache.ibatis.annotations.Update;
    
    import java.util.List;
    
    public interface BrandMapper {
        // 查询所有
        @Select("select * from tb_brand")
        @ResultMap("brandResultMap")
        List<Brand> selectAll();
    
    
        // 添加
        @Insert("insert into tb_brand values (null,#{brandName}, #{companyName}, #{ordered},#{description},#{status})")
        void addAll(Brand brand);
    
        // 修改时回显内容（id绑定
        @Select("select *from tb_brand where id = #{id}")
        @ResultMap("brandResultMap")
        Brand selectById(int id);
    
    
        // 修改
        @Update("update tb_brand set brand_name = #{brandName},company_name = #{companyName},ordered = #{ordered},description = #{description},status = #{status} where id = #{id} ")
        void update(Brand brand);
    
    }
    ```

  - 注意点：

    - 绑定post请求传递的id数据的占位符是`#{xxxx}`
    - 因为数据库的字段和pojo的实例类不对应，所以要用resultMap
    - mybatis的版本要≥3.5.6，才能够用注解的方式

至此，库管代码编写完毕。

-------------------------------------------------------------------------



- 2.服务端代码编写 -> `BrandService.java`

  - 服务端的代码主要实现对数据库操作对象的调用，数据的返回和提交事务。

  - 主要的方法一一对应mapper中的方法

  - ```java
    package com.itheima.service;
    
    import com.itheima.mapper.BrandMapper;
    import com.itheima.pojo.Brand;
    import com.itheima.util.SqlSessionFactoryUtils;
    import org.apache.ibatis.session.SqlSession;
    import org.apache.ibatis.session.SqlSessionFactory;
    
    import java.util.List;
    import java.util.Map;
    
    public class BrandService {
        SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();
    
        public List<Brand> selectAll(){
            SqlSession sqlSession =  factory.openSession();
            BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
    
            List<Brand> brands = brandMapper.selectAll();
    
            sqlSession.close();
    
            return brands;
    
        }
    
        public void addAll(Brand brand){
            SqlSession sqlSession =factory.openSession();
            BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
    
            brandMapper.addAll(brand);
    
            // 提交事务
            sqlSession.commit();
    
            sqlSession.close();
    
        }
    
        public Brand selectById(int id){
            SqlSession sqlSession =factory.openSession();
            BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
    
            Brand brand = brandMapper.selectById(id);
            sqlSession.close();
            return brand;
        }
    
        public void update(Brand brand){
            SqlSession sqlSession =factory.openSession();
            BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
    
            brandMapper.update(brand);
    
            sqlSession.commit();
            sqlSession.close();
        }
    }
    ```

  - 注意点：

    - sqlSession在方法体中均有使用，所以需要把创建sqlSession对象的代码提升作用域
    - 增、删、改这种变动数据库的操作，需要提交事务`sqlSeesion.commit()`



至此，服务端代码编写完毕

------------------



- 3.web端，也称为业务端 -> 基于Servlet框架编译，包括4个Servlet，与Service的方法体对应

  - **1.`selectAllServlet`**

    - 调用service方法中的方法体，对数据进行展示

    - ```java
      package com.itheima.web;
      
      import com.itheima.pojo.Brand;
      import com.itheima.service.BrandService;
      
      import javax.servlet.ServletException;
      import javax.servlet.annotation.WebServlet;
      import javax.servlet.http.HttpServlet;
      import javax.servlet.http.HttpServletRequest;
      import javax.servlet.http.HttpServletResponse;
      import java.io.IOException;
      import java.util.List;
      
      @WebServlet("/selectAllServlet")
      public class selectAllServlet extends HttpServlet {
          private BrandService service = new BrandService();
      
          @Override
          protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
              List<Brand> brands = service.selectAll();
              System.out.println(brands);
              req.setAttribute("brands", brands);
      
              req.getRequestDispatcher("/brand.jsp").forward(req, resp);
      
          }
      
          @Override
          protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
              this.doGet(req, resp);
          }
      }
      
      ```

    - 

  - **2.`addAllServlet`**

    - 调用service方法中的方法体，对传递过来的数据进行添加操作

    - ```java
      package com.itheima.web;
      
      import com.itheima.pojo.Brand;
      import com.itheima.service.BrandService;
      
      import javax.servlet.ServletException;
      import javax.servlet.annotation.WebServlet;
      import javax.servlet.http.HttpServlet;
      import javax.servlet.http.HttpServletRequest;
      import javax.servlet.http.HttpServletResponse;
      import java.io.IOException;
      
      @WebServlet("/addAllServlet")
      public class addAllServlet extends HttpServlet {
      
          private BrandService service = new BrandService();
          @Override
          protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      
              // post utf-8 处理
              req.setCharacterEncoding("utf-8");
      
              // 1.接收表单
              String brandName = req.getParameter("brandName");
              String companyName = req.getParameter("companyName");
              String ordered = req.getParameter("ordered");
              String description = req.getParameter("description");
              String status = req.getParameter("status");
      
              //封装为一个Brand对象
              Brand brand = new Brand();
              brand.setBrandName(brandName);
              brand.setCompanyName(companyName);
              brand.setOrdered(Integer.parseInt(ordered));
              brand.setDescription(description);
              brand.setStatus(Integer.parseInt(status));
      
              //2. 调用service 完成添加
              service.addAll(brand);
      
              //3. 转发到查询所有Servlet
              req.getRequestDispatcher("/selectAllServlet").forward(req,resp);
      
          }
      
      
      
          @Override
          protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
              this.doGet(req, resp);
          }
      }
      
      ```

  

  - **3.`selectByIdServlet`**

    - 调用service方法中的方法体，对传递过来的Id进行数据查询

    - ```java
      package com.itheima.web;
      
      import com.itheima.pojo.Brand;
      import com.itheima.service.BrandService;
      
      import javax.servlet.ServletException;
      import javax.servlet.annotation.WebServlet;
      import javax.servlet.http.HttpServlet;
      import javax.servlet.http.HttpServletRequest;
      import javax.servlet.http.HttpServletResponse;
      import java.io.IOException;
      
      @WebServlet("/selectByIdServlet")
      public class selectByIdServlet extends HttpServlet {
          private BrandService service = new BrandService();
          @Override
          protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
              String id = req.getParameter("id");
      
              Brand brand = service.selectById(Integer.parseInt(id));
      
              req.setAttribute("brand", brand);
      
              req.getRequestDispatcher("/update.jsp").forward(req, resp);
      
      
          }
      
          @Override
          protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
              this.doGet(req, resp);
          }
      }
      
      ```

  

  - **4.`updateServlet`**

    - 调用service方法中的方法体，对传递过来的数据进行修改操作

    - ```java
      package com.itheima.web;
      
      import com.itheima.pojo.Brand;
      import com.itheima.service.BrandService;
      
      import javax.servlet.ServletException;
      import javax.servlet.annotation.WebServlet;
      import javax.servlet.http.HttpServlet;
      import javax.servlet.http.HttpServletRequest;
      import javax.servlet.http.HttpServletResponse;
      import java.io.IOException;
      
      @WebServlet("/updateServlet")
      public class updateServlet extends HttpServlet {
      
          private BrandService service = new BrandService();
          @Override
          protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      
              // post utf-8 处理
              req.setCharacterEncoding("utf-8");
      
              // 1.接收表单
              String id = req.getParameter("id");
              String brandName = req.getParameter("brandName");
              String companyName = req.getParameter("companyName");
              String ordered = req.getParameter("ordered");
              String description = req.getParameter("description");
              String status = req.getParameter("status");
      
              //封装为一个Brand对象
              Brand brand = new Brand();
              brand.setId(Integer.parseInt(id));
              brand.setBrandName(brandName);
              brand.setCompanyName(companyName);
              brand.setOrdered(Integer.parseInt(ordered));
              brand.setDescription(description);
              brand.setStatus(Integer.parseInt(status));
      
              //2. 调用service 完成添加
              service.update(brand);
      
              //3. 转发到查询所有Servlet
              req.getRequestDispatcher("/selectAllServlet").forward(req,resp);
      
          }
      
      
      
          @Override
          protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
              this.doGet(req, resp);
          }
      }
      ```



至此，代码部分全部编写完毕。

run起来就可以通过页面的访问来操作数据库了。



----------

3/10开发新功能：

### 根据Session、Cookies，实现登录、注册小案例

- 分别的功能点：
  - Cookies：登录中新增记住我勾选框，在登录成功后，下次前往登录界面会自动填充
  - Session：注册的时候，将页面的验证码答案写在Session里，用户提交数据后，根据Session的验证码的值进行校验



#### 案例准备

- **验证码插件引用**

  - 将`CheckCodeUtil`放在Utils目录下

  - `CheckCodeUtil`： 主要是用来生成随机字符串的功能插件，生成对应的图像和答案

  - ```java
    package com.itheima.util;
    
    import javax.imageio.ImageIO;
    import java.awt.*;
    import java.awt.geom.AffineTransform;
    import java.awt.image.BufferedImage;
    import java.io.File;
    import java.io.FileOutputStream;
    import java.io.IOException;
    import java.io.OutputStream;
    import java.util.Arrays;
    import java.util.Random;
    
    /**
     * 生成验证码工具类
     */
    public class CheckCodeUtil {
    
        public static final String VERIFY_CODES = "123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        private static Random random = new Random();
    
    
    
        /**
         * 输出随机验证码图片流,并返回验证码值（一般传入输出流，响应response页面端，Web项目用的较多）
         *
         * @param w
         * @param h
         * @param os
         * @param verifySize
         * @return
         * @throws IOException
         */
        public static String outputVerifyImage(int w, int h, OutputStream os, int verifySize) throws IOException {
            String verifyCode = generateVerifyCode(verifySize);
            outputImage(w, h, os, verifyCode);
            return verifyCode;
        }
    
        /**
         * 使用系统默认字符源生成验证码
         *
         * @param verifySize 验证码长度
         * @return
         */
        public static String generateVerifyCode(int verifySize) {
            return generateVerifyCode(verifySize, VERIFY_CODES);
        }
    
        /**
         * 使用指定源生成验证码
         *
         * @param verifySize 验证码长度
         * @param sources    验证码字符源
         * @return
         */
        public static String generateVerifyCode(int verifySize, String sources) {
            // 未设定展示源的字码，赋默认值大写字母+数字
            if (sources == null || sources.length() == 0) {
                sources = VERIFY_CODES;
            }
            int codesLen = sources.length();
            Random rand = new Random(System.currentTimeMillis());
            StringBuilder verifyCode = new StringBuilder(verifySize);
            for (int i = 0; i < verifySize; i++) {
                verifyCode.append(sources.charAt(rand.nextInt(codesLen - 1)));
            }
            return verifyCode.toString();
        }
    
        /**
         * 生成随机验证码文件,并返回验证码值 (生成图片形式，用的较少)
         *
         * @param w
         * @param h
         * @param outputFile
         * @param verifySize
         * @return
         * @throws IOException
         */
        public static String outputVerifyImage(int w, int h, File outputFile, int verifySize) throws IOException {
            String verifyCode = generateVerifyCode(verifySize);
            outputImage(w, h, outputFile, verifyCode);
            return verifyCode;
        }
    
    
    
        /**
         * 生成指定验证码图像文件
         *
         * @param w
         * @param h
         * @param outputFile
         * @param code
         * @throws IOException
         */
        public static void outputImage(int w, int h, File outputFile, String code) throws IOException {
            if (outputFile == null) {
                return;
            }
            File dir = outputFile.getParentFile();
            //文件不存在
            if (!dir.exists()) {
                //创建
                dir.mkdirs();
            }
            try {
                outputFile.createNewFile();
                FileOutputStream fos = new FileOutputStream(outputFile);
                outputImage(w, h, fos, code);
                fos.close();
            } catch (IOException e) {
                throw e;
            }
        }
    
        /**
         * 输出指定验证码图片流
         *
         * @param w
         * @param h
         * @param os
         * @param code
         * @throws IOException
         */
        public static void outputImage(int w, int h, OutputStream os, String code) throws IOException {
            int verifySize = code.length();
            BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            Random rand = new Random();
            Graphics2D g2 = image.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    
            // 创建颜色集合，使用java.awt包下的类
            Color[] colors = new Color[5];
            Color[] colorSpaces = new Color[]{Color.WHITE, Color.CYAN,
                    Color.GRAY, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE,
                    Color.PINK, Color.YELLOW};
            float[] fractions = new float[colors.length];
            for (int i = 0; i < colors.length; i++) {
                colors[i] = colorSpaces[rand.nextInt(colorSpaces.length)];
                fractions[i] = rand.nextFloat();
            }
            Arrays.sort(fractions);
            // 设置边框色
            g2.setColor(Color.GRAY);
            g2.fillRect(0, 0, w, h);
    
            Color c = getRandColor(200, 250);
            // 设置背景色
            g2.setColor(c);
            g2.fillRect(0, 2, w, h - 4);
    
            // 绘制干扰线
            Random random = new Random();
            // 设置线条的颜色
            g2.setColor(getRandColor(160, 200));
            for (int i = 0; i < 20; i++) {
                int x = random.nextInt(w - 1);
                int y = random.nextInt(h - 1);
                int xl = random.nextInt(6) + 1;
                int yl = random.nextInt(12) + 1;
                g2.drawLine(x, y, x + xl + 40, y + yl + 20);
            }
    
            // 添加噪点
            // 噪声率
            float yawpRate = 0.05f;
            int area = (int) (yawpRate * w * h);
            for (int i = 0; i < area; i++) {
                int x = random.nextInt(w);
                int y = random.nextInt(h);
                // 获取随机颜色
                int rgb = getRandomIntColor();
                image.setRGB(x, y, rgb);
            }
            // 添加图片扭曲
            shear(g2, w, h, c);
    
            g2.setColor(getRandColor(100, 160));
            int fontSize = h - 4;
            Font font = new Font("Algerian", Font.ITALIC, fontSize);
            g2.setFont(font);
            char[] chars = code.toCharArray();
            for (int i = 0; i < verifySize; i++) {
                AffineTransform affine = new AffineTransform();
                affine.setToRotation(Math.PI / 4 * rand.nextDouble() * (rand.nextBoolean() ? 1 : -1), (w / verifySize) * i + fontSize / 2, h / 2);
                g2.setTransform(affine);
                g2.drawChars(chars, i, 1, ((w - 10) / verifySize) * i + 5, h / 2 + fontSize / 2 - 10);
            }
    
            g2.dispose();
            ImageIO.write(image, "jpg", os);
        }
    
        /**
         * 随机颜色
         *
         * @param fc
         * @param bc
         * @return
         */
        private static Color getRandColor(int fc, int bc) {
            if (fc > 255) {
                fc = 255;
            }
            if (bc > 255) {
                bc = 255;
            }
            int r = fc + random.nextInt(bc - fc);
            int g = fc + random.nextInt(bc - fc);
            int b = fc + random.nextInt(bc - fc);
            return new Color(r, g, b);
        }
    
        private static int getRandomIntColor() {
            int[] rgb = getRandomRgb();
            int color = 0;
            for (int c : rgb) {
                color = color << 8;
                color = color | c;
            }
            return color;
        }
    
        private static int[] getRandomRgb() {
            int[] rgb = new int[3];
            for (int i = 0; i < 3; i++) {
                rgb[i] = random.nextInt(255);
            }
            return rgb;
        }
    
        private static void shear(Graphics g, int w1, int h1, Color color) {
            shearX(g, w1, h1, color);
            shearY(g, w1, h1, color);
        }
    
        private static void shearX(Graphics g, int w1, int h1, Color color) {
    
            int period = random.nextInt(2);
    
            boolean borderGap = true;
            int frames = 1;
            int phase = random.nextInt(2);
    
            for (int i = 0; i < h1; i++) {
                double d = (double) (period >> 1)
                        * Math.sin((double) i / (double) period
                        + (6.2831853071795862D * (double) phase)
                        / (double) frames);
                g.copyArea(0, i, w1, 1, (int) d, 0);
                if (borderGap) {
                    g.setColor(color);
                    g.drawLine((int) d, i, 0, i);
                    g.drawLine((int) d + w1, i, w1, i);
                }
            }
    
        }
    
        private static void shearY(Graphics g, int w1, int h1, Color color) {
    
            int period = random.nextInt(40) + 10; // 50;
    
            boolean borderGap = true;
            int frames = 20;
            int phase = 7;
            for (int i = 0; i < w1; i++) {
                double d = (double) (period >> 1)
                        * Math.sin((double) i / (double) period
                        + (6.2831853071795862D * (double) phase)
                        / (double) frames);
                g.copyArea(i, 0, 1, h1, 0, (int) d);
                if (borderGap) {
                    g.setColor(color);
                    g.drawLine(i, (int) d, i, 0);
                    g.drawLine(i, (int) d + h1, i, h1);
                }
    
            }
    
        }
    }
    
    
    ```

- **Bean类创建：User类**

  - 主要是用来进行与数据库tb_user的调用和使用的

  - 存放在pojo目录下

  - ```java
    package com.itheima.pojo;
    
    public class User {
        private Integer id;
        private String username;
        private String password;
    
        public Integer getId() {
            return id;
        }
    
        public void setId(Integer id) {
            this.id = id;
        }
    
        public String getUsername() {
            return username;
        }
    
        public void setUsername(String username) {
            this.username = username;
        }
    
        public String getPassword() {
            return password;
        }
    
        public void setPassword(String password) {
            this.password = password;
        }
    
        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }
    
    ```

- **Mapper类**

  - **`UserMapper.java（接口类）`**

    - 用于创建操作数据库的方法和Statement语句

    - 存放在Java/com/itheima/mapper中

    - ```java
      package com.itheima.mapper;
      
      import com.itheima.pojo.User;
      import org.apache.ibatis.annotations.Insert;
      import org.apache.ibatis.annotations.Param;
      import org.apache.ibatis.annotations.Select;
      
      public interface UserMapper {
      }
      
      ```

    

  - **`UserMapper.xml`**

    - 用于匹配UserMapper生成的class对象

    - 存放在resources/com/itheima/mapper中

    - ```xml
      <?xml version="1.0" encoding="ISO-8859-1"?>
      
      <!DOCTYPE mapper
              PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
              "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
      <mapper namespace="com.itheima.mapper.UserMapper">
      
      
      </mapper>
      ```



- Web页面

  - `login.jsp`

    - 登录页面，存放在webapp中

    - 页面的功能，可以在浏览器中进行填写信息和点击**记住我**的功能 -> **基于Cookies**

    - 注意点：

      - form提交的表格的地址，要对应WebServlet中的url
      - `remeber`的标签需要设置value的值
      - cookies在页面中调用`${cookie.xxxx.value}`就能够在页面中动态获取值， xxxx表示之前我们设置进去cookie的变量名

    - ```jsp
      <%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
      <!DOCTYPE html>
      <html lang="en">
      
      <head>
          <meta charset="UTF-8">
          <title>login</title>
          <link href="css/login.css" rel="stylesheet">
      </head>
      
      <body>
      <div id="loginDiv" style="height: 350px">
          <form action="/JSPDemo/loginServlet" method="post" id="form">
              <h1 id="loginMsg">LOGIN IN</h1>
              <div id="errorMsg">${login_msg} ${register_msg}</div>
              <p>Username:<input id="username" name="username" type="text" value="${cookie.username.value}"></p>
              <p>Password:<input id="password" name="password" type="password" value="${cookie.password.value}"></p>
              <p>Remember:<input id="remember" name="remember" value="1" type="checkbox"></p>
              <div id="subDiv">
                  <input type="submit" class="button" value="login up">
                  <input type="reset" class="button" value="reset">&nbsp;&nbsp;&nbsp;
                  <a href="register.jsp">没有账号？</a>
              </div>
          </form>
      </div>
      </body>
      </html>
      ```

    

  - `register.jsp`

    - 注册页面，存放在webapp中

    - 页面功能，可以进行用户的注册，注册中需要填写验证码，校验成功后比对用户名是否存在，不存在就可以顺利保存。 -> 验证码的校验**基于 Session**

    - 注意点

      - 点击看不清之后的图片刷新：通过Javascript的点击事件绑定按钮，通过Webservlet绑定的生成随机验证码的插件，来刷新图片，url后面拼接了当前的时间`new Date().getMilliseconds();`

        - ```javascript
          <script>
            document.getElementById("changeImg").onclick = function () {
              //路径后面添加时间戳的目的是避免浏览器进行缓存静态资源
              document.getElementById("checkCodeImg").src = "/JSPDemo/checkCodeServlet?"+new Date().getMilliseconds();
            }
          </script>
          ```

    - ```jsp
      <%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
      <!DOCTYPE html>
      <html lang="en">
      <head>
        <meta charset="UTF-8">
        <title>欢迎注册</title>
        <link href="css/register.css" rel="stylesheet">
      </head>
      <body>
      <div class="form-div">
        <div class="reg-content">
          <h1>欢迎注册</h1>
          <span>已有帐号？</span> <a href="login.html">登录</a>
        </div>
        <form id="reg-form" action="/JSPDemo/registerServlet" method="post">
          <table>
            <tr>
              <td>用户名</td>
              <td class="inputs">
                <input name="username" type="text" id="username">
                <br>
                <span id="username_err" class="err_msg">${register_msg}</span>
              </td>
            </tr>
            <tr>
              <td>密码</td>
              <td class="inputs">
                <input name="password" type="password" id="password">
                <br>
                <span id="password_err" class="err_msg" style="display: none">密码格式有误</span>
              </td>
            </tr>
            <tr>
              <td>验证码</td>
              <td class="inputs">
                <input name="checkCode" type="text" id="checkCode">
                <img id="checkCodeImg" src="/JSPDemo/checkCodeServlet">
                <a href="#" id="changeImg" >看不清？</a>
              </td>
            </tr>
          </table>
          <div class="buttons">
            <input value="注 册" type="submit" id="reg_btn">
          </div>
          <br class="clear">
        </form>
      </div>
      
      <script>
        document.getElementById("changeImg").onclick = function () {
          //路径后面添加时间戳的目的是避免浏览器进行缓存静态资源
          document.getElementById("checkCodeImg").src = "/JSPDemo/checkCodeServlet?"+new Date().getMilliseconds();
        }
      </script>
      
      </body>
      </html>
      ```



#### **案例编译**

基于MVC开发模式，DAO层我们已经编写完毕，现在就差Service层和Web层需要开发，按照开发流程，我们先完善

**1.Service的开发**

- 定义一个UserService的方法

  - 调用sqlSession对象来操作Mysql
  - 获取mapper对象的相关操作方法
  - 涉及增删改的需要提交事务

- ```java
  package com.itheima.service;
  
  
  import com.itheima.mapper.UserMapper;
  import com.itheima.pojo.User;
  import com.itheima.util.SqlSessionFactoryUtils;
  import org.apache.ibatis.session.SqlSession;
  import org.apache.ibatis.session.SqlSessionFactory;
  
  import java.util.List;
  
  public class UserService {
      //1.使用工具类获取SqlSessionFactory
      SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();
      /**
       * 登录方法
       * @param username
       * @param password
       * @return
       */
      public User login(String username, String password){
          //2. 获取SqlSession
          SqlSession sqlSession = factory.openSession();
          //3. 获取UserMapper
          UserMapper mapper = sqlSession.getMapper(UserMapper.class);
          //4. 调用方法
          User user = mapper.select(username, password);
          //释放资源
          sqlSession.close();
  
          return  user;
      }
  
      public boolean register(User user){
          //2. 获取SqlSession
          SqlSession sqlSession = factory.openSession();
          //3. 获取UserMapper
          UserMapper mapper = sqlSession.getMapper(UserMapper.class);
          //4. 判断用户名是否存在
          User u = mapper.selectByUsername(user.getUsername());
  
          if(u == null){
              // 用户名不存在，注册
              mapper.add(user);
              sqlSession.commit();
          }
          sqlSession.close();
  
          return u == null;
  
      }
  }
  
  ```





**2.Web的开发**

有两个需求页面，所以我们定义两个Servlet类，来分别实现逻辑功能

- **`loginServlet.java`**

  - 功能：

    - 创建与Service的连接
    - 获取Post请求传递给服务端的参数，如果账号密码在数据库查得到，就登录成功，并跳转到brand页面
    - 登录成功后查看是否有选择”记住我“，如果有，就将账号密码存储在cookies中
    - 如果查不到就返回登录失败等错误信息

  - ```java
    package com.itheima.web;
    
    import com.itheima.pojo.User;
    import com.itheima.service.UserService;
    
    import javax.servlet.ServletException;
    import javax.servlet.annotation.WebServlet;
    import javax.servlet.http.*;
    import java.io.IOException;
    
    @WebServlet("/loginServlet")
    public class loginServlet extends HttpServlet {
        private UserService service = new UserService();
    
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            //1. 获取用户名和密码
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            //获取复选框数据
            String remember = request.getParameter("remember");
    
            //2. 调用service查询
            User user = service.login(username, password);
    
            //3. 判断
            if(user != null){
                //判断用户是否勾选记住我，字符串写前面是为了避免出现空指针异常
                if("1".equals(remember)){
                    //勾选了，发送Cookie
                    //1. 创建Cookie对象
                    Cookie c_username = new Cookie("username",username);
                    Cookie c_password = new Cookie("password",password);
                    // 设置Cookie的存活时间
                    c_username.setMaxAge( 60 * 60 * 24 * 7);
                    c_password.setMaxAge( 60 * 60 * 24 * 7);
                    //2. 发送
                    response.addCookie(c_username);
                    response.addCookie(c_password);
                }
    
                //登录成功，跳转到查询所有的BrandServlet
    
                //将登陆成功后的user对象，存储到session
                HttpSession session = request.getSession();
                session.setAttribute("user",user);
    
                String contextPath = request.getContextPath();
                response.sendRedirect(contextPath+"/selectAllServlet");
            }else {
                // 登录失败,
                // 存储错误信息到request
                request.setAttribute("login_msg","用户名或密码错误");
                // 跳转到login.jsp
                request.getRequestDispatcher("/login.jsp").forward(request,response);
    
            }
        }
    
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            this.doGet(request, response);
        }
    }
    
    ```

    

- **`registerServlet.java`**

  - 需求

    - 创建与Service的连接
    - 获取Post请求传递给服务端的参数，
    - 同时调用session中的验证码正确答案，与用户输入的进行比对
    - 如果验证码与随机生成的验证成功，就进入账号是否唯一的判断；如果不成功，返回报错信息，重新回到注册页面
    - 账号唯一，注册成功，写入数据库
    - 账号不唯一，返回报错信息

  - ```java
    package com.itheima.web;
    
    import com.itheima.pojo.User;
    import com.itheima.service.UserService;
    
    import javax.servlet.ServletException;
    import javax.servlet.annotation.WebServlet;
    import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import javax.servlet.http.HttpSession;
    import java.io.IOException;
    
    @WebServlet("/registerServlet")
    public class RegisterServlet extends HttpServlet {
        private UserService service = new UserService();
    
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            //1. 获取用户名和密码数据
            String username = request.getParameter("username");
            String password = request.getParameter("password");
    
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
    
            // 获取用户输入的验证码
            String checkCode = request.getParameter("checkCode");
    
            // 程序生成的验证码，从Session获取
            HttpSession session = request.getSession();
            String checkCodeGen = (String) session.getAttribute("checkCodeGen");
    
            // 比对
            if(!checkCodeGen.equalsIgnoreCase(checkCode)){
    
                request.setAttribute("register_msg","验证码错误");
                request.getRequestDispatcher("/register.jsp").forward(request,response);
    
                // 不允许注册
                return;
            }
    
    
            //2. 调用service 注册
            boolean flag = service.register(user);
            //3. 判断注册成功与否
            if(flag){
                //注册功能，跳转登陆页面
                request.setAttribute("register_msg","注册成功，请登录");
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            }else {
                //注册失败，跳转到注册页面
    
                request.setAttribute("register_msg","用户名已存在");
                request.getRequestDispatcher("/register.jsp").forward(request,response);
            }
    
    
        }
    
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            this.doGet(request, response);
        }
    }
    ```



又因为验证码的展示需要src绑定url，所以需要再定义一个类来展示验证码的图片

**`CheckCodeServlet`**

- 需求

  - 调用插件CheckCodeUtil的生成验证码方法`outputVerifyImage`生成图像和答案
  - 将答案写入Session中

- ```java
  package com.itheima.web;
  
  
  
  import com.itheima.util.CheckCodeUtil;
  
  import javax.servlet.ServletException;
  import javax.servlet.ServletOutputStream;
  import javax.servlet.annotation.WebServlet;
  import javax.servlet.http.HttpServlet;
  import javax.servlet.http.HttpServletRequest;
  import javax.servlet.http.HttpServletResponse;
  import javax.servlet.http.HttpSession;
  import java.io.IOException;
  
  
  @WebServlet("/checkCodeServlet")
  public class CheckCodeServlet extends HttpServlet {
      @Override
      protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          // 生成验证码
          ServletOutputStream os = resp.getOutputStream();
          String checkCode = CheckCodeUtil.outputVerifyImage(100, 50, os, 4);
  
          // 存入Session
          HttpSession session = req.getSession();
          session.setAttribute("checkCodeGen",checkCode);
  
      }
  
      @Override
      protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          this.doGet(req, resp);
      }
  }
  
  ```