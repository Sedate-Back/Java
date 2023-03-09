### Servlet

#### 概述

- 用来使前端静态页面，根据用户的不同，展示不同的信息在页面中

- 是JavaEE规范之一



#### 快速入门

- **需求分析: 编写一个Servlet类，并使用IDEA中Tomcat插件进行部署，最终通过浏览器访问所编写的Servlet程序**



具体实现步骤：

1. 创建Web项目`web-demo`，导入Servlet依赖坐标

   1. ```xml
      <dependency>
          <groupId>javax.servlet</groupId>
          <artifactId>javax.servlet-api</artifactId>
          <version>3.1.0</version>
          <!--
            此处为什么需要添加该标签?
            provided指的是在编译和测试过程中有效,最后生成的war包时不会加入
             因为Tomcat的lib目录中已经有servlet-api这个jar包，如果在生成war包的时候生效就会和Tomcat中的jar包冲突，导致报错
          -->
          <scope>provided</scope>
      </dependency>
      ```

2. 创建:定义一个类，实现Servlet接口，并重写接口中所有方法，并在service方法中输入一句话

   1. ```java
      package com.itheima.web;
      
      import javax.servlet.*;
      import java.io.IOException;
      
      public class ServletDemo1 implements Servlet {
      
          public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
              System.out.println("servlet hello world~");
          }
          public void init(ServletConfig servletConfig) throws ServletException {
      
          }
      
          public ServletConfig getServletConfig() {
              return null;
          }
      
          public String getServletInfo() {
              return null;
          }
      
          public void destroy() {
      
          }
      }
      ```

   

3. 配置:在类上使用@WebServlet注解，配置该Servlet的访问路径

   1. ```java
      @WebServlet("/demo1")
      ```

4. 访问:启动Tomcat,浏览器中输入URL地址访问该Servlet

   1. ```html
      http://localhost:8080/web-demo/demo1
      ```

5. 访问后，在控制台会打印`servlet hello world~` 说明servlet程序已经成功运行。

至此，Servlet的入门案例就已经完成，大家可以按照上面的步骤进行练习了



#### 执行流程

* 浏览器发出`http://localhost:8080/web-demo/demo1`请求，从请求中可以解析出三部分内容，分别是`localhost:8080`、`web-demo`、`demo1`
  * 根据`localhost:8080`可以找到要访问的Tomcat Web服务器
  * 根据`web-demo`可以找到部署在Tomcat服务器上的web-demo项目
  * 根据`demo1`可以找到要访问的是项目中的哪个Servlet类，根据@WebServlet后面的值进行匹配
* 找到ServletDemo1这个类后，Tomcat Web服务器就会为ServletDemo1这个类创建一个对象，然后调用对象中的service方法
  * ServletDemo1实现了Servlet接口，所以类中必然会重写service方法供Tomcat Web服务器进行调用
  * service方法中有ServletRequest和ServletResponse两个参数，ServletRequest封装的是请求数据，ServletResponse封装的是响应数据，后期我们可以通过这两个参数实现前后端的数据交互



#### 生命周期

Servlet运行在Servlet容器(web服务器)中，其生命周期由容器来管理，分为4个阶段：

1. **加载和实例化**：默认情况下，当Servlet第一次被访问时，由容器创建Servlet对象

```xml
默认情况，Servlet会在第一次访问被容器创建，但是如果创建Servlet比较耗时的话，那么第一个访问的人等待的时间就比较长，用户的体验就比较差，那么我们能不能把Servlet的创建放到服务器启动的时候来创建，具体如何来配置?

@WebServlet(urlPatterns = "/demo1",loadOnStartup = 1)
loadOnstartup的取值有两类情况
	（1）负整数:第一次访问时创建Servlet对象
	（2）0或正整数:服务器启动时创建Servlet对象，数字越小优先级越高
```

2. **初始化**：在Servlet实例化之后，容器将调用Servlet的**init()**方法初始化这个对象，完成一些如加载配置文件、创建连接等初始化的工作。该方法只**调用一次**

3. **请求处理**：**每次**请求Servlet时，Servlet容器都会调用Servlet的**service()**方法对请求进行处理
4. **服务终止**：当需要释放内存或者容器关闭时，容器就会调用Servlet实例的**destroy()**方法完成资源的释放。在destroy()方法调用之后，容器会释放这个Servlet实例，该实例随后会被Java的垃圾收集器所回收



#### 体系结构

通过上面的学习，我们知道要想编写一个Servlet就必须要实现Servlet接口，重写接口中的5个方法，虽然已经能完成要求，但是编写起来还是比较麻烦的，因为我们更关注的其实只有service方法，那有没有更简单方式来创建Servlet呢?

因为我们将来开发B/S架构的web项目，都是针对HTTP协议，所以我们自定义Servlet,会通过继承**HttpServlet**

案例

```java
@WebServlet("/demo4")
public class ServletDemo4 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO GET 请求方式处理逻辑
        System.out.println("get...");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO Post 请求方式处理逻辑
        System.out.println("post...");
    }
}
```

* 要想发送一个GET请求，请求该Servlet，只需要通过浏览器发送`http://localhost:8080/web-demo/demo4`,就能看到doGet方法被执行了

* 要想发送一个POST请求，请求该Servlet，单单通过浏览器是无法实现的，这个时候就需要编写一个form表单来发送请求，在webapp下创建一个`a.html`页面，内容如下:

* ```html
  <!DOCTYPE html>
  <html lang="en">
  <head>
      <meta charset="UTF-8">
      <title>Title</title>
  </head>
  <body>
      <form action="/web-demo/demo4" method="post">
          <input name="username"/><input type="submit"/>
      </form>
  </body>
  </html>
  ```



#### 优化

因为每次写servlet的时候都需要先去调用接口，再去重写doGet和doPost方法很麻烦，所以以后我们写接口的时候，可以直接继承HttpServlet，并重写需要的方法就可以了，例如doGet和doPost方法

```java
@WebServlet("/demo5")
public class ServletDemo5 extends HttpServlet {

    @Override
    protected void doGet(ServletRequest req, ServletResponse res) {
        System.out.println("get...");
    }

    @Override
    protected void doPost(ServletRequest req, ServletResponse res) {
        System.out.println("post...");
    }
}

```



#### url配置

- 规则
  - 精确匹配
    - 配置路径： `@WebServlet("/user/select")`
    - 访问路径：`localhost:8080/web-demo/user/select`
  - 目录匹配
    - 配置路径： `@WebServlet("/user/*")`
    - 访问路径：`localhost:8080/web-demo/user/aaa` or `localhost:8080/web-demo/user/bbb`
  - 扩展名匹配
    - 配置路径： `@WebServlet("*.do")`
    - 访问路径：`localhost:8080/web-demo/aaa.dp` or `localhost:8080/web-demo/user/bbb.do`
  - 任意匹配
    - 配置路径： `@WebServlet("/")` or `@WebServlet("*")` 
    - 访问路径：`localhost:8080/web-demo/hehe` or `localhost:8080/web-demo/haha`



### Request

#### 概念

- 获取请求数据
  - 浏览器会发送HTTP请求到后台服务器[Tomcat]
  - HTTP的请求中会包含很多请求数据[请求行+请求头+请求体]
  - 后台服务器[Tomcat]会对HTTP请求中的数据进行解析并把解析结果存入到一个对象中
  - 所存入的对象即为request对象，所以我们可以从request对象中获取请求的相关参数
  - 获取到数据后就可以继续后续的业务，比如获取用户名和密码就可以实现登录操作的相关业务

#### 获取数据

- 获取请求行数据（请求行包含三块内容，分别是`请求方式`、`请求资源路径`、`HTTP协议及版本`）

  - 获取请求方式: `GET`

  ```java
  String getMethod()
  ```

  * 获取虚拟目录(项目访问路径): `/request-demo`

  ```java
  String getContextPath()
  ```

  * 获取URL(统一资源定位符): `http://localhost:8080/request-demo/req1`

  ```java
  StringBuffer getRequestURL()
  ```

  * 获取URI(统一资源标识符): `/request-demo/req1`

  ```java
  String getRequestURI()	
  ```

  * 获取请求参数(GET方式): `username=zhangsan&password=123`

  ```java
  String getQueryString()
  ```

- 获取请求头数据

  - ```java
    String agent = req.getHeader("user-agent");
    System.out.println(agent);
    ```

- 获取请求体数据

  - 请求体的访问模式是通过post的方式进行数据传递，所以获取请求体的时候，需要我们模拟post请求的流程

  - 步骤：

    - 准备一个页面，在页面中添加form表单,用来发送post请求

    - ```html
      <!DOCTYPE html>
      <html lang="en">
      <head>
          <meta charset="UTF-8">
          <title>Title</title>
      </head>
      <body>
      <!-- 
          action:form表单提交的请求地址
          method:请求方式，指定为post
      -->
      <form action="/request-demo/req1" method="post">
          <input type="text" name="username">
          <input type="password" name="password">
          <input type="submit">
      </form>
      </body>
      </html>
      ```

    - 在Servlet的doPost方法中获取数据

    - ```java
      /**
       * request 获取请求数据
       */
      @WebServlet("/req1")
      public class RequestDemo1 extends HttpServlet {
          @Override
          protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          }
          @Override
          protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
              //在此处获取请求体中的数据
          }
      }
      ```

    - 调用getReader()或者getInputStream()方法，因为目前前端传递的是纯文本数据，所以我们采用getReader()方法来获取

    - ```java
      /**
       * request 获取请求数据
       */
      @WebServlet("/req1")
      public class RequestDemo1 extends HttpServlet {
          @Override
          protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          }
          @Override
          protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
               //获取post 请求体：请求参数
              //1. 获取字符输入流
              BufferedReader br = req.getReader();
              //2. 读取数据
              String line = br.readLine();
              System.out.println(line);
          }
      }
      ```

#### 优化doGet和doPost

因为不管是Get或post方式，都会走业务流程到代码块，所以我们可以将doGet和doPost整合起来，在doPost的代码区写上`this.doGet(request, response)`

就可以只编辑Get的方法，同时在遇到post请求的时候，也可以顺利处理数据

```java
public class RequestDemo1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       //采用request提供的获取请求参数的通用方式来获取请求参数
       //编写其他的业务代码...
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
```





#### 优化获取请求体数据

当post请求是2个或2个以上的返回的时候，我们就需要借助map来存储数据和取出数据，但是Servlet给我们提供了接口来优化数据的读取能力`req.getParameter("xxx")`





### Response

#### 概念

- 设置响应数据
  - 业务处理完后，后台就需要给前端返回业务处理的结果即响应数据
  - 把响应数据封装到response对象中
  - 后台服务器[Tomcat]会解析response对象,按照[响应行+响应头+响应体]格式拼接结果
  - 浏览器最终解析结果，把内容展示在浏览器给用户浏览



#### 