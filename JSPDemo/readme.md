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