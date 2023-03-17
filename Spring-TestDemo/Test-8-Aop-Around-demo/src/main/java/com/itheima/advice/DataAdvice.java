package com.itheima.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.swing.*;

/*
 案例中是通过pjp获取方法体传递的参数，将参数截取后处理，之后再发送给方法体进行执行
 */

@Component
@Aspect
public class DataAdvice {

    @Pointcut("execution(boolean com.itheima.service.*Service.*(*,*))")
    private void servicePt(){}


    public Object trimStr(ProceedingJoinPoint pjp) throws Throwable{
        Object[] args = pjp.getArgs(); // 获取当前方法体的参数信息
        for (int i = 0; i < args.length; i++) {
            if (args[i].getClass().equals(String.class)){
                args[i] = args[i].toString().trim();
            }
        }
        // 将处理后的参数传递给方法
        Object ret = pjp.proceed(args);
        return ret;
    }
}
