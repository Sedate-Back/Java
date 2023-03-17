package com.itheima.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration  // 这句话就是表示这个是配置类
@ComponentScan("com.itheima") // 这句话表示 我要扫描 com.itheima下面的所有包
@PropertySource({"classpath:jdbc.properties"}) // 导入外部的配置文件
public class SpringConfig {
}
