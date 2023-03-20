package com.itheima.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

// springmvc配置类，本质上还是Spring配置类
@Configuration
@ComponentScan("com.itheima.controller") // 扫描路径包下的所有bean类
@EnableWebMvc
public class SpringMvcConfig {
}
