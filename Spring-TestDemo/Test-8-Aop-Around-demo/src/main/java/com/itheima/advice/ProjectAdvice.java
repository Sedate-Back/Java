package com.itheima.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect  // 定义通知类
public class ProjectAdvice {
    // 匹配业务层的所有方法
    @Pointcut("execution(* com.itheima.service.*Service.*(..))")
    private void servicePt(){}

    @Around("ProjectAdvice.servicePt()") // 绑定执行到那的切点
    public void runSpeed(ProceedingJoinPoint pjp) throws Throwable{
        Signature signature = pjp.getSignature();

        String className = signature.getDeclaringTypeName();

        String methodName = signature.getName();
        // 开始记录时间
        long start = System.currentTimeMillis();

        // 循环执行
        for (int i = 0; i < 10000; i++) {
            pjp.proceed();
        }
        // 结束时间
        long end = System.currentTimeMillis();

        System.out.println("万次执行："+ className + ".." + methodName + "---->" + (end-start)+ "ms");

    }
}
