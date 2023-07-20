package com.hailey.search.api.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Aspect
@Component
public class LoggingAspect {
    @Before("execution(* com.hailey.search.api.controller..*Controller.*(..))")
    public void beforeMethodExecution(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String methodName = joinPoint.getSignature().getName();
        log.info("START URI: {} {}{}, queryString: {}", methodName, request.getMethod(), request.getRequestURI(), request.getQueryString());
    }
    @After("execution(* com.hailey.search.api.controller..*Controller.*(..))")
    public void afterMethodExecution(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String methodName = joinPoint.getSignature().getName();
        log.info("END URI: {} {}{}", methodName, request.getMethod(), request.getRequestURI());
    }
}