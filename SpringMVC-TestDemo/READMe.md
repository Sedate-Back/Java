# Spring mvc

## 1.概述

- SpringMVC是一种基于Java实现MVC模型的轻量级Web框架

- 优点

  - 使用简单，开发便捷，相比于Servlet
  - 更加轻量

  

## 2.入门案例

### 2.1**步骤**

1. 创建web工程(Maven结构)

2. 设置Tomcat服务器，加载web工程（Tomcat插件）

3. 导入坐标（SpringMVC + Servlet)
4. 定义处理请求的功能类（UserController）
5. 服务Spring mvc配置类，加载处理请求的Bean
6. 加载SpringMVC配置，并设置 MVC请求拦截的路径。 

### 2.2 相关注解

#### 2.2.1@Controller注解

- 名称：@Controller
- 类型：类注解
- 位置：SpringMVC控制器类定义上方
- 作用：设定SpringMVC的核心控制器bean
- 范例

```java
@Controller
public class UserController {
}
```

#### 2.2.2 @RequestMapping注解

- 名称：@RequestMapping
- 类型：方法注解
- 位置：SpringMVC控制器方法定义上方
- 作用：设置当前控制器方法请求访问路径
- 范例

```java
@RequestMapping("/save")
public void save(){
    System.out.println("user save ...");
}
```

> 注意：其实@RequestMapping注解还可以写到类上面，笔记后面会介绍到。

#### 2.2.3 @ResponseBody注解

- 名称：@ResponseBody
- 类型：方法注解
- 位置：SpringMVC控制器方法定义上方
- 作用：设置当前控制器方法响应内容为当前返回值，无需解析
- 范例

```java
@RequestMapping("/save")
@ResponseBody
public String save(){
    System.out.println("user save ...");
    return "{'info':'springmvc'}";
}
```

#### 2.2.4 AbstractDispatcherServletInitializer类

- AbstractDispatcherServletInitializer类是SpringMVC提供的快速初始化Web3.0容器的抽象类

- AbstractDispatcherServletInitializer提供三个接口方法供用户实现

  - createServletApplicationContext()方法，创建Servlet容器时，加载SpringMVC对应的bean并放入WebApplicationContext对象范围中，而WebApplicationContext的作用范围为ServletContext范围，即整个web容器范围。

  ```java
  //加载springmvc配置类，产生springmvc容器（本质还是spring容器）
  protected WebApplicationContext createServletApplicationContext() {
      AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
      ctx.register(SpringMvcConfig.class);
      return ctx;
  }
  ```

  - getServletMappings()方法，设定SpringMVC对应的请求映射路径，设置为/表示拦截所有请求，任意请求都将转入到SpringMVC进行处理。

  ```java
  //设置由springmvc控制器处理的请求映射路径
  protected String[] getServletMappings() {
      return new String[]{"/"};
  }
  ```

  - createRootApplicationContext()方法，如果创建Servlet容器时需要加载非SpringMVC对应的bean，使用当前方法进行，使用方式同createServletApplicationContext()

  ```java
  //加载spring配置类
  protected WebApplicationContext createRootApplicationContext() {
      return null;
  }
  ```

### 3.为什么在Springmvc的Bean类不会加载到Spring里呢？

- SpringMVC相关bean（表现层bean）

- Spring控制的bean

  1. 业务bean（Service）

  2. 功能bean（DataSource等）

- SpringMVC相关bean加载控制
  1. SpringMVC加载的bean对应的包均在com.itheima.controller包内
- Spring相关bean加载控制
  1. 方式一：Spring加载的bean设定扫描范围为com.itheima，排除掉controller包内的bean
  2. 方式二：Spring加载的bean设定扫描范围为精准范围，例如service包、dao包等
  3. 方式三：不区分Spring与SpringMVC的环境，加载到同一个环境中

## 3.请求与响应

### 1 请求映射路径【重点】

#### 问题导入

@RequestMapping注解注解可以写在哪？有什么作用？

#### 1.1 @RequestMapping注解

- 名称：@RequestMapping
- 类型：==方法注解  类注解==
- 位置：SpringMVC控制器方法定义上方
- 作用：设置当前控制器方法请求访问路径，如果设置在类上统一设置当前控制器方法请求访问路径前缀
- 范例

```java
@Controller
//类上方配置的请求映射与方法上面配置的请求映射连接在一起，形成完整的请求映射路径
@RequestMapping("/user")
public class UserController {
    //请求路径映射
    @RequestMapping("/save") //此时save方法的访问路径是：/user/save
    @ResponseBody
    public String save(){
        System.out.println("user save ...");
        return "{'module':'user save'}";
    }
}
```



### 2 请求参数

#### 2.1 发送普通类型参数【重点】

##### 问题导入

如何解决POST请求中文乱码问题？

##### 2.1.1 请求方式

- GET请求
- POST请求

##### 2.1.2 GET请求传递普通参数

- 普通参数：url地址传参，地址参数名与形参变量名相同，定义形参即可接收参数

![image-20210805102858905](D:/java/3、Java 微服务框架/2-springmvc/day01/讲义/assets/image-20210805102858905.png)

```java
//普通参数：请求参数与形参名称对应即可完成参数传递
@RequestMapping("/commonParam")
@ResponseBody
public String commonParam(String name ,int age){
    System.out.println("普通参数传递 name ==> "+name);
    System.out.println("普通参数传递 age ==> "+age);
    return "{'module':'common param'}";
}
```

- 问题：如果同学们传递的参数是中文试试，你们会发现接收到的参数出现了中文乱码问题。
- 原因：tomcat 8.5版本之后GET请求就不再出现中文乱码问题，但是我们使用的是tomcat7插件，所以会出现GET请求中文乱码问题。
- 解决：在pom.xml添加tomcat7插件处配置UTF-8字符集，解决GET请求中文乱码问题。

```xml
<build>
    <plugins>
      <plugin>
        <groupId>org.apache.tomcat.maven</groupId>
        <artifactId>tomcat7-maven-plugin</artifactId>
        <version>2.1</version>
        <configuration>
          <port>80</port><!--tomcat端口号-->
          <path>/</path> <!--虚拟目录-->
          <uriEncoding>UTF-8</uriEncoding><!--访问路径编解码字符集-->
        </configuration>
      </plugin>
    </plugins>
  </build>
```



##### 2.1.3 POST请求传递普通参数

- 普通参数：form表单post请求传参，表单参数名与形参变量名相同，定义形参即可接收参数

![image-20210805103041922](D:/java/3、Java 微服务框架/2-springmvc/day01/讲义/assets/image-20210805103041922.png)

```java
//普通参数：请求参数与形参名称对应即可完成参数传递
@RequestMapping("/commonParam")
@ResponseBody
public String commonParam(String name ,int age){
    System.out.println("普通参数传递 name ==> "+name);
    System.out.println("普通参数传递 age ==> "+age);
    return "{'module':'common param'}";
}
```

问题：我们发现，POST请求传递的参数如果包含中文那么就会出现中文乱码问题，说明我们之前配置的tomcat插件uri路径编解码字符集无法解决POST请求中文乱码问题。那么如何解决呢？

##### 2.1.4 POST请求中文乱码处理

> 在加载SpringMVC配置的配置类中指定字符过滤器。

```java
public class ServletContainersInitConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringMvcConfig.class};
    }

    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    //乱码处理
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        return new Filter[]{filter};
    }
}
```



#### 2.2 五种类型参数传递

##### 问题导入

当请求参数名与形参变量名不同，该如何接收请求参数？

##### 2.2.1 五种类型参数介绍

- 普通参数
- POJO类型参数
- 嵌套POJO类型参数
- 数组类型参数
- 集合类型参数

##### 2.2.2 普通参数【重点】

- 普通参数：当请求参数名与形参变量名不同，使用@RequestParam绑定参数关系

![image-20210805104824258](D:/java/3、Java 微服务框架/2-springmvc/day01/讲义/assets/image-20210805104824258.png)

```java
//普通参数：请求参数名与形参名不同时，使用@RequestParam注解关联请求参数名称与形参名称之间的关系
@RequestMapping("/commonParamDifferentName")
@ResponseBody
public String commonParamDifferentName(@RequestParam("name") String userName , int age){
    System.out.println("普通参数传递 userName ==> "+userName);
    System.out.println("普通参数传递 age ==> "+age);
    return "{'module':'common param different name'}";
}
```

- 名称：@RequestParam
- 类型：形参注解
- 位置：SpringMVC控制器方法形参定义前面
- 作用：绑定请求参数与处理器方法形参间的关系
- 参数：
  - required：是否为必传参数
  - defaultValue：参数默认值



##### 2.2.3 POJO类型参数【重点】

- POJO参数：请求参数名与形参对象属性名相同，定义POJO类型形参即可接收参数

![image-20210805105056731](D:/java/3、Java 微服务框架/2-springmvc/day01/讲义/assets/image-20210805105056731.png)

```java
public class User {
    private String name;
    private int age;
    //同学们自己添加getter/setter/toString()方法
}
```

```java
//POJO参数：请求参数与形参对象中的属性对应即可完成参数传递
@RequestMapping("/pojoParam")
@ResponseBody
public String pojoParam(User user){
    System.out.println("pojo参数传递 user ==> "+user);
    return "{'module':'pojo param'}";
}
```

**==注意事项：请求参数key的名称要和POJO中属性的名称一致，否则无法封装。==**

##### 2.2.4 嵌套POJO类型参数

- POJO对象中包含POJO对象

```java
public class User {
    private String name;
    private int age;
    private Address address;
    //同学们自己添加getter/setter/toString()方法
}
public class Address {
    private String province;
    private String city;
    private Address address;
}
```

- 嵌套POJO参数：请求参数名与形参对象属性名相同，按照对象层次结构关系即可接收嵌套POJO属性参数

![image-20210805105505625](D:/java/3、Java 微服务框架/2-springmvc/day01/讲义/assets/image-20210805105505625.png)

```java
//嵌套POJO参数：嵌套属性按照层次结构设定名称即可完成参数传递
@RequestMapping("/pojoContainPojoParam")
@ResponseBody
public String pojoContainPojoParam(User user){
    System.out.println("pojo嵌套pojo参数传递 user ==> "+user);
    return "{'module':'pojo contain pojo param'}";
}
```

**==注意事项：请求参数key的名称要和POJO中属性的名称一致，否则无法封装。==**



##### 2.2.5 数组类型参数

- 数组参数：请求参数名与形参对象属性名相同且请求参数为多个，定义数组类型即可接收参数

![image-20210805105825688](D:/java/3、Java 微服务框架/2-springmvc/day01/讲义/assets/image-20210805105825688.png)

```java
//数组参数：同名请求参数可以直接映射到对应名称的形参数组对象中
@RequestMapping("/arrayParam")
@ResponseBody
public String arrayParam(String[] likes){
    System.out.println("数组参数传递 likes ==> "+ Arrays.toString(likes));
    return "{'module':'array param'}";
}
```



##### 2.2.6 集合类型参数

- 集合保存普通参数：请求参数名与形参集合对象名相同且请求参数为多个，@RequestParam绑定参数关系

![image-20210805105957957](D:/java/3、Java 微服务框架/2-springmvc/day01/讲义/assets/image-20210805105957957.png)

```java
//集合参数：同名请求参数可以使用@RequestParam注解映射到对应名称的集合对象中作为数据
@RequestMapping("/listParam")
@ResponseBody
public String listParam(@RequestParam List<String> likes){
    System.out.println("集合参数传递 likes ==> "+ likes);
    return "{'module':'list param'}";
}
```



#### 2.3 json数据参数传递

##### 问题导入

问题：@EnableWebMvc注解和@ResponseBody注解有什么用？

##### 2.3.1 json数据参数介绍

- json普通数组（["","","",...]）
- json对象（{key:value,key:value,...}）
- json对象数组（[{key:value,...},{key:value,...}]）

##### 2.3.2 传递json普通数组

###### 2.3.2.1 代码演示

1. 添加json数据转换相关坐标

```xml
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.9.0</version>
</dependency>
```

2. 设置发送json数据（请求body中添加json数据）

![image-20210805110937684](D:/java/3、Java 微服务框架/2-springmvc/day01/讲义/assets/image-20210805110937684.png)

3. 开启自动转换json数据的支持

```java
@Configuration
@ComponentScan("com.itheima.controller")
//开启json数据类型自动转换
@EnableWebMvc
public class SpringMvcConfig {
}
```

**注意事项：**

@EnableWebMvc注解功能强大，该注解整合了多个功能，此处仅使用其中一部分功能，即json数据进行自动类型转换

4. 在Controller中编写方法接收json参数

```java
//集合参数：json格式
//1.开启json数据格式的自动转换，在配置类中开启@EnableWebMvc
//2.使用@RequestBody注解将外部传递的json数组数据映射到形参的集合对象中作为数据
@RequestMapping("/listParamForJson")
@ResponseBody
public String listParamForJson(@RequestBody List<String> likes){
    System.out.println("list common(json)参数传递 list ==> "+likes);
    return "{'module':'list common for json param'}";
}
```

###### 2.3.2.2 @EnableWebMvc注解介绍

- 名称：@EnableWebMvc
- 类型：==配置类注解==
- 位置：SpringMVC配置类定义上方
- 作用：开启SpringMVC多项辅助功能
- 范例：

```java
@Configuration
@ComponentScan("com.itheima.controller")
@EnableWebMvc
public class SpringMvcConfig {
}
```

###### 2.3.2.3 @RequestBody注解介绍

- 名称：@RequestBody
- 类型：==形参注解==
- 位置：SpringMVC控制器方法形参定义前面
- 作用：将请求中请求体所包含的数据传递给请求参数，此注解一个处理器方法只能使用一次
- 范例：

```java
@RequestMapping("/listParamForJson")
@ResponseBody
public String listParamForJson(@RequestBody List<String> likes){
    System.out.println("list common(json)参数传递 list ==> "+likes);
    return "{'module':'list common for json param'}";
} 
```

##### 2.3.3 传递json对象

- POJO参数：json数据与形参对象属性名相同，定义POJO类型形参即可接收参数

![image-20210805111544701](D:/java/3、Java 微服务框架/2-springmvc/day01/讲义/assets/image-20210805111544701.png)

```java
//POJO参数：json格式
//1.开启json数据格式的自动转换，在配置类中开启@EnableWebMvc
//2.使用@RequestBody注解将外部传递的json数据映射到形参的实体类对象中，要求属性名称一一对应
@RequestMapping("/pojoParamForJson")
@ResponseBody
public String pojoParamForJson(@RequestBody User user){
    System.out.println("pojo(json)参数传递 user ==> "+user);
    return "{'module':'pojo for json param'}";
}
```

##### 2.3.4 传递json对象数组

- POJO集合参数：json数组数据与集合泛型属性名相同，定义List类型形参即可接收参数

![image-20210805111626095](D:/java/3、Java 微服务框架/2-springmvc/day01/讲义/assets/image-20210805111626095.png)

```java
//集合参数：json格式
//1.开启json数据格式的自动转换，在配置类中开启@EnableWebMvc
//2.使用@RequestBody注解将外部传递的json数组数据映射到形参的保存实体类对象的集合对象中，要求属性名称一一对应
@RequestMapping("/listPojoParamForJson")
@ResponseBody
public String listPojoParamForJson(@RequestBody List<User> list){
    System.out.println("list pojo(json)参数传递 list ==> "+list);
    return "{'module':'list pojo for json param'}";
}
```

##### 2.3.5 @RequestBody与@RequestParam区别

- 区别
  @RequestParam用于接收url地址传参，表单传参【application/x-www-form-urlencoded】
  @RequestBody用于接收json数据【application/json】
- 应用
  后期开发中，发送json格式数据为主，@RequestBody应用较广
  如果发送非json格式数据，选用@RequestParam接收请求参数



### 3 日期类型参数传递【重点】

#### 问题导入

@DateTimeFormat注解的作用是什么？

#### 3.1 代码演示

- 日期类型数据基于系统不同格式也不尽相同
  2088-08-18
  2088/08/18
  08/18/2088
- 接收形参时，根据不同的日期格式设置不同的接收方式

![image-20210805113333189](D:/java/3、Java 微服务框架/2-springmvc/day01/讲义/assets/image-20210805113333189.png)

```java
//日期参数 http://localhost:80/dataParam?date=2088/08/08&date1=2088-08-18&date2=2088/08/28 8:08:08
//使用@DateTimeFormat注解设置日期类型数据格式，默认格式yyyy/MM/dd
@RequestMapping("/dataParam")
@ResponseBody
public String dataParam(Date date,
                  @DateTimeFormat(pattern="yyyy-MM-dd") Date date1,
                  @DateTimeFormat(pattern="yyyy/MM/dd HH:mm:ss") Date date2){
    System.out.println("参数传递 date ==> "+date);
    System.out.println("参数传递 date1(yyyy-MM-dd) ==> "+date1);
    System.out.println("参数传递 date2(yyyy/MM/dd HH:mm:ss) ==> "+date2);
    return "{'module':'data param'}";
}
```

#### 3.2 @DateTimeFormat注解介绍

- 名称：@DateTimeFormat
- 类型：==形参注解==
- 位置：SpringMVC控制器方法形参前面
- 作用：设定日期时间型数据格式

- 属性：pattern：指定日期时间格式字符串

#### 3.3 工作原理

- 其内部依赖Converter接口

```java
public interface Converter<S, T> {
    @Nullable
    T convert(S var1);
}
```

- 请求参数年龄数据（String→Integer）
- json数据转对象（json → POJO）
- 日期格式转换（String → Date）

#### 3.4 注意事项

传递日期类型参数必须在配置类上使用@EnableWebMvc注解。其功能之一：根据类型匹配对应的类型转换器。

### 4 响应

#### 问题导入

如何响应json数据？

#### 4.1响应页面【了解】

```java
@Controller
public class UserController {

    //响应页面/跳转页面
    //返回值为String类型，设置返回值为页面名称，即可实现页面跳转
    @RequestMapping("/toJumpPage")
    public String toJumpPage(){
        System.out.println("跳转页面");
        return "page.jsp";
    }
}
```

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <h2>Hello Spring MVC!</h2>
    </body>
</html>
```

#### 4.2 文本数据【了解】

```java
//响应文本数据
//返回值为String类型，设置返回值为任意字符串信息，即可实现返回指定字符串信息，需要依赖@ResponseBody注解
@RequestMapping("/toText")
@ResponseBody
public String toText(){
    System.out.println("返回纯文本数据");
    return "response text";
}
```

#### 4.3 json数据【重点】

```java
//响应POJO对象
//返回值为实体类对象，设置返回值为实体类类型，即可实现返回对应对象的json数据，需要依赖@ResponseBody注解和@EnableWebMvc注解
@RequestMapping("/toJsonPOJO")
@ResponseBody
public User toJsonPOJO(){
    System.out.println("返回json对象数据");
    User user = new User();
    user.setName("itcast");
    user.setAge(15);
    return user;
}
```

```java
//响应POJO集合对象
//返回值为集合对象，设置返回值为集合类型，即可实现返回对应集合的json数组数据，需要依赖@ResponseBody注解和@EnableWebMvc注解
@RequestMapping("/toJsonList")
@ResponseBody
public List<User> toJsonList(){
    System.out.println("返回json集合数据");
    User user1 = new User();
    user1.setName("传智播客");
    user1.setAge(15);

    User user2 = new User();
    user2.setName("黑马程序员");
    user2.setAge(12);

    List<User> userList = new ArrayList<User>();
    userList.add(user1);
    userList.add(user2);

    return userList;
}
```

**==注意：需要添加jackson-databind依赖以及在SpringMvcConfig配置类上添加@EnableWebMvc注解==**



## 4.RESTful -> REST 风格



简单来说，就是通过修改映射路径，达到简化配置的目的

- 请求方式不同：

  - POST请求用于提交新增数据 -> `@POSTMapping`

  - PUT请求用于修改数据-> `@PUTMapping`

  - DELETE请求用于删除数据-> `@DELETEMapping`

  - GET请求用于获取数据-> `@GETMapping`

- 如果包含参数，如ID等

  - 可以用`@PathVariable`注解，将参数的名称写在形参上
  - ` @GETtMapping(value = "/{id}")`



## 5.SSM整合

### 1 SSM整合配置

#### 问题导入

请描述“SSM整合流程”中各个配置类的作用？

#### 1.1 SSM整合流程

1. 创建工程
2. SSM整合
   - Spring
     - SpringConfig
   - MyBatis
     - MybatisConfig
     - JdbcConfig
     - jdbc.properties
   - SpringMVC
     - ServletConfig
     - SpringMvcConfig
3. 功能模块
   - 表与实体类
   - dao（接口+自动代理）
   - service（接口+实现类）
     - 业务层接口测试（整合JUnit）
   - controller
     - 表现层接口测试（PostMan）

代码不写了，SpringBoot优化很多





## 6.表现层数据封装

解决方案：

1. 定义一个result类，包含code、data、msg信息，返回JSON格式给前端
2. 定义一个code类，将code的数据写成 final static类型，可直接调用
3. 表现层数据封装返回Result对象





## 7.异常处理器

### 1.主要用来解决什么

​	当我们在访问页面的时候，如果程序报错，或者输入了错误的值给到我们的后端，会导致页面出现404或者500的错误，用户的体验是不好的。

​	所以我们需要在后端截取，报错信息返回数据封装给前端，给用户一些提示和安慰。



### 2.异常的分类

- 业务类型异常BusinessException（如用户输入了错误的ID，或者重复进行了一些暴力的测试）
- 系统类型异常SystemException（如服务器宕机或者死机）
- 其它异常Exception（如我们自身程序的bug）



### 3.代码实现

#### 3.1 根据异常分类自定义异常类

##### 3.1.1 自定义项目系统级异常

```java
//自定义异常处理器，用于封装异常信息，对异常进行分类
public class SystemException extends RuntimeException{
    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public SystemException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public SystemException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}
```

##### 3.1.2 自定义项目业务级异常

```java 
//自定义异常处理器，用于封装异常信息，对异常进行分类
public class BusinessException extends RuntimeException{
    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(Integer code,String message,Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}
```

#### 3.2 自定义异常编码（持续补充）

```java
public class Code {

	//之前其他状态码省略没写，以下是新补充的状态码，可以根据需要自己补充
    
    public static final Integer SYSTEM_ERR = 50001;
    public static final Integer SYSTEM_TIMEOUT_ERR = 50002;
    public static final Integer SYSTEM_UNKNOW_ERR = 59999;
    public static final Integer BUSINESS_ERR = 60002;
    
}
```

#### 3.3 触发自定义异常

```java
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;

	//在getById演示触发异常，其他方法省略没有写进来
    public Book getById(Integer id) {
        //模拟业务异常，包装成自定义异常
        if(id <0){
            throw new BusinessException(Code.BUSINESS_ERR,"请不要使用你的技术挑战我的耐性!");
        }
    }
}
```

#### 3.4 在异常通知类中拦截并处理异常

```java
@RestControllerAdvice //用于标识当前类为REST风格对应的异常处理器
public class ProjectExceptionAdvice {
    //@ExceptionHandler用于设置当前处理器类对应的异常类型
    @ExceptionHandler(SystemException.class)
    public Result doSystemException(SystemException ex){
        //记录日志
        //发送消息给运维
        //发送邮件给开发人员,ex对象发送给开发人员
        return new Result(ex.getCode(),null,ex.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public Result doBusinessException(BusinessException ex){
        return new Result(ex.getCode(),null,ex.getMessage());
    }

    //除了自定义的异常处理器，保留对Exception类型的异常处理，用于处理非预期的异常
    @ExceptionHandler(Exception.class)
    public Result doOtherException(Exception ex){
        //记录日志
        //发送消息给运维
        //发送邮件给开发人员,ex对象发送给开发人员
        return new Result(Code.SYSTEM_UNKNOW_ERR,null,"系统繁忙，请稍后再试！");
    }
}
```



## 8.拦截器

### 1.概念

- 拦截器（Interceptor）是一种动态拦截方法调用的机制，在SpringMVC中动态拦截控制器方法的执行
- 作用：
  1. 在指定的方法调用前后执行预先设定的代码
  2. 阻止原始方法的执行
  3. 总结：增强
- 核心原理：AOP思想



### 2.拦截器和过滤器的区别

- 归属不同：Filter属于Servlet技术，Interceptor属于SpringMVC技术
- 拦截内容不同：Filter对所有访问进行增强，Interceptor仅针对SpringMVC的访问进行增强



### 3.拦截器代码实现

#### 【第一步】定义拦截器

> 做法：定义一个类，实现HandlerInterceptor接口即可

```java
@Component //注意当前类必须受Spring容器控制
//定义拦截器类，实现HandlerInterceptor接口
public class ProjectInterceptor implements HandlerInterceptor {
    @Override
    //原始方法调用前执行的内容
    //返回值类型可以拦截控制的执行，true放行，false终止
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle..."+contentType);
        return true;
    }

    @Override
    //原始方法调用后执行的内容
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle...");
    }

    @Override
    //原始方法调用完成后执行的内容
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion...");
    }
}
```

#### 【第二步】配置加载拦截器

```java
@Configuration
public class SpringMvcSupport extends WebMvcConfigurationSupport {
    @Autowired
    private ProjectInterceptor projectInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        //配置拦截器
        registry.addInterceptor(projectInterceptor)
            .addPathPatterns("/books","/books/*");
    }
}
```

使用标准接口WebMvcConfigurer简化开发（注意：侵入式较强）

```java
@Configuration
@ComponentScan({"com.itheima.controller"})
@EnableWebMvc
//实现WebMvcConfigurer接口可以简化开发，但具有一定的侵入性
public class SpringMvcConfig implements WebMvcConfigurer {
    @Autowired
    private ProjectInterceptor projectInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //配置多拦截器
        registry.addInterceptor(projectInterceptor)
            .addPathPatterns("/books","/books/*");
    }
}
```

#### 

### 4.拦截器参数

#### 4.1 前置处理

```java
//原始方法调用前执行的内容
//返回值类型可以拦截控制的执行，true放行，false终止
public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    System.out.println("preHandle..."+contentType);
    return true;
}
```

- 参数
  1. request:请求对象
  2. response:响应对象
  3. handler:被调用的处理器对象，本质上是一个方法对象，对反射技术中的Method对象进行了再包装

- 返回值
  返回值为false，被拦截的处理器将不执行。

#### 4.2 后置处理

```java
//原始方法调用后执行的内容
public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    System.out.println("postHandle...");
}
```

- 参数
  modelAndView：如果处理器执行完成具有返回结果，可以读取到对应数据与页面信息，并进行跳转

> 注意：如果处理器方法出现异常了，该方法不会执行

#### 4.3 完成后处理

```java 
//原始方法调用完成后执行的内容
public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    System.out.println("afterCompletion...");
}
```

- 参数
  ex:如果处理器执行过程中出现异常对象，可以针对异常情况进行单独处理

> 注意：无论处理器方法内部是否出现异常，该方法都会执行。



### 5.多个拦截器配置

- 定义第二个拦截器

```java
@Component
public class ProjectInterceptor2 implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle...222");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle...222");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion...222");
    }
}
```

- 配置第二个拦截器

```java
@Configuration
@ComponentScan({"com.itheima.controller"})
@EnableWebMvc
//实现WebMvcConfigurer接口可以简化开发，但具有一定的侵入性
public class SpringMvcConfig implements WebMvcConfigurer {
    @Autowired
    private ProjectInterceptor projectInterceptor;
    @Autowired
    private ProjectInterceptor2 projectInterceptor2;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //配置多拦截器
        registry.addInterceptor(projectInterceptor)
            .addPathPatterns("/books","/books/*");
        registry.addInterceptor(projectInterceptor2)
            .addPathPatterns("/books","/books/*");
    }
}
```
