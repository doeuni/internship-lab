package com.intern.integration.common.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {
        @Pointcut("execution(* com.intern.integration.api.service..*(..))")
        public void serviceMethods() {}

    @Before("serviceMethods()") public void logBefore(JoinPoint joinPoint) {
           String methodName = joinPoint.getSignature().toShortString();
           Object[] args = joinPoint.getArgs();
           log.info("✅ API 호출 시작 → Method: {} Args: {}", methodName, args);
        }
    @AfterReturning(pointcut = "serviceMethods()", returning = "result")  public void logAfterReturning(JoinPoint joinPoint, Object result) {
            String methodName = joinPoint.getSignature().toShortString();
            log.info("✅ API 호출 성공 → Method: {} Response: {}", methodName, result); }
    @AfterThrowing(pointcut = "serviceMethods()", throwing = "ex")  public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
            String methodName = joinPoint.getSignature().toShortString();
            log.error("❌ API 호출 실패 → Method: {} Exception: {}", methodName, ex.getMessage(), ex);
        }
}