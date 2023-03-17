package com.itheima.advice;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

// 通知类必须配置成Spring管理的bean
@Component
@Aspect // 将当前类设为切面类
public class MyAdvice {

    @Pointcut("execution(void com.itheima.dao.BookDao.update())") // 设置切入点
    private void pt(){}

    @Before("pt()")  // 当执行到上述方法的时候，会看到这个Before，会在方法之前执行before下的方法
    public void method(){
        System.out.println(System.currentTimeMillis());
    }
}
