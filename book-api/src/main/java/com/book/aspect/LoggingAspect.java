package com.book.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 日志记录方面
 *
 * @author k
 * @date 2023/06/23
 */
@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Pointcut("within(@org.springframework.stereotype.Controller *)")
    public void controller() {}

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void restController() {}

    @Before("controller() || restController()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("进入方法：{}", joinPoint.getSignature());
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            log.info("参数{}：{}", i+1, args[i]);
        }
    }

    @AfterReturning(pointcut = "controller() || restController()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("退出方法：{}，返回值为：{}", joinPoint.getSignature(), result);
    }

    @AfterThrowing(pointcut = "controller() || restController()", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        log.error("方法：{} 抛出异常：{}", joinPoint.getSignature(), ex.getMessage());
    }
}
