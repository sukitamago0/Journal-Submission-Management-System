package com.journal.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//切面Aop
@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.journal..controller.*.*(..)) || execution(* com.journal..dao.*.*(..)) || execution(* com.journal..service.*.*(..))")
    public void logMethodName(JoinPoint joinPoint) {
//        String className = joinPoint.getTarget().getClass().getSimpleName();
//        String methodName = joinPoint.getSignature().getName();
//        System.out.println("切面Aop功能：Current method: " + className + "." + methodName);
    }
}
