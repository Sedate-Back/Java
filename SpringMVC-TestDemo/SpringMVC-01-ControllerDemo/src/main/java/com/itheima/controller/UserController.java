package com.itheima.controller;

import com.itheima.domain.Address;
import com.itheima.domain.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller // 定义表现层控制器Bean
@RequestMapping("/user") // 这个类所有方法访问前都需要先加上/user
public class UserController {

    @RequestMapping("/save") // 设置访问映射路径
    @ResponseBody  // 设置 将当前方法的返回值传递给Respond
    public String save(){
        System.out.println("user save....");
        return "{'info':'springmvc'}";   // 模拟返回Json格式，但传递的是String字符串
    }

    @RequestMapping("/commonParam")
    @ResponseBody
    public String commonParam(String name,Integer age){
        // get和post请求传递回来的参数都可以用 形参的方式 接收 和 使用
        System.out.println("普通参数传递 name ==> "+name);
        System.out.println("普通参数传递 age ==> "+age);
        return "{'module':'common param'}";

        // get请求传递中文处理方式在 pom的Tomcat引用中文解码字符集就可以
        // post请求传递要去ServletContainersInitConfig配置类中重 getServletFilters方法
    }
    @RequestMapping("/commonParamDifferentName") // 利用 参数绑定 进行数据传递
    @ResponseBody
    public String commonParamDifferentName(@RequestParam("name") String username, Integer age){
        System.out.println("普通参数传递 name ==> "+username);
        System.out.println("普通参数传递 age ==> "+age);
        return "{'module':'common Param Different Name '}";
    }

    @RequestMapping("/pojoParam") //  pojo实例类 数据传递
    @ResponseBody
    public String pojoParam(Address address){
        System.out.println("实例类参数传递 ==> "+address);
        return "{'module':'pojo Param '}";
    }

    @RequestMapping("/pojoContainPojoParam") // pojo实例类中包含其他的pojo实例类嵌套 数据传递 请求address.province
    @ResponseBody
    public String pojoContainPojoParam(User user){
        System.out.println("实例类参数传递 ==> "+user);
        return "{'module':'pojo Contain Pojo Param '}";

    }

    @RequestMapping("/arrayParam") // 多选列表传递  likes ... likes ..
    @ResponseBody
    public String arrayParam(String[] likes){
        System.out.println("数组参数传递 likes ==> "+ Arrays.toString(likes));
        return "{'module':'array Param Likes'}";

    }

    @RequestMapping("/listParamForJson") // 多选列表传递  likes ... likes ..
    @ResponseBody
    public String listParamForJson(@RequestBody List<String> likes){
        // @RequestBody 作用：将请求中请求体所包含的数据传递给请求参数，此注解一个处理器方法只能使用一次

        // 接受Json数据
        // 1. 前往pom加入jackson模块
        // 2. 在SpringMvcConfig开启数据类型自动转换配置
        // 3. 编写该方法接受数据
        // 4. postman模拟访问
        System.out.println("list common(json)参数传递 list ==> "+likes);
        return "{'module':'list Param For Json'}";

    }

    @RequestMapping("/pojoParamForJson")
    @ResponseBody
    public String pojoParamForJson(@RequestBody User user){
        System.out.println("pojo(json)参数传递 user ==> "+user);
        return "{'module':'pojo for json param'}";
        // 前端参数传递格式
        // {
        //    "name":"zzh",
        //    "age":24,
        //    "address":
        //    {
        //        "province": "Guangdong",
        //        "city":"Shantou"
        //    }
        //}
    }

    @RequestMapping("/listPojoParamForJson") // POJO集合参数：json数组数据与集合泛型属性名相同，定义List类型形参即可接收参数
    @ResponseBody
    public String listPojoParamForJson(@RequestBody List<User> list){
        System.out.println("list pojo(json)参数传递 list ==> "+list);
        return "{'module':'list pojo for json param'}";
    }

    @RequestMapping("/dataParam")  // 日期类型参数传递
    @ResponseBody
    public String dataParam(Date date,
                            @DateTimeFormat(pattern="yyyy-MM-dd") Date date1,
                            @DateTimeFormat(pattern="yyyy/MM/dd HH:mm:ss") Date date2){
        //日期参数 http://localhost:80/dataParam?date=2088/08/08&date1=2088-08-18&date2=2088/08/28 8:08:08
        //使用@DateTimeFormat注解设置日期类型数据格式，默认格式yyyy/MM/dd
        System.out.println("参数传递 date ==> "+date);
        System.out.println("参数传递 date1(yyyy-MM-dd) ==> "+date1);
        System.out.println("参数传递 date2(yyyy/MM/dd HH:mm:ss) ==> "+date2);
        return "{'module':'data param'}";
    }


    //响应POJO对象
    //返回值为实体类对象，设置返回值为实体类类型，即可实现返回对应对象的json数据，需要依赖@ResponseBody注解和@EnableWebMvc注解
    @RequestMapping("/toJsonPOJO")
    @ResponseBody
    public User toJsonPOJO(){
        System.out.println("返回json对象数据");
        User user = new User();
        user.setName("itcast");
        user.setAge(15);
        Address address = new Address();
        address.setCity("Shantou");
        address.setProvince("Guangdong");
        user.setAddress(address);
        return user;
    }

    @RequestMapping("/toJsonList")
    @ResponseBody
    public List<User> toJsonList(){
        System.out.println("返回json集合数据");
        User user1 = new User();
        user1.setName("传智播客");
        user1.setAge(15);
        Address address = new Address();
        address.setCity("Shantou");
        address.setProvince("Guangdong");
        user1.setAddress(address);

        User user2 = new User();
        user2.setName("黑马程序员");
        user2.setAge(12);
        Address address2 = new Address();
        address.setCity("Shenzhen");
        address.setProvince("Guangdong");
        user2.setAddress(address);

        List<User> userList = new ArrayList<User>();
        userList.add(user1);
        userList.add(user2);

        return userList;
    }
}
