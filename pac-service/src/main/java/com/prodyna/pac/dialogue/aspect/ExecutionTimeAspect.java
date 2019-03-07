package com.prodyna.pac.dialogue.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExecutionTimeAspect {

    @Pointcut(value = "@annotation(com.prodyna.pac.dialogue.annotation.LogExecutionTime)")
    protected void annotationLogExecutionTime() {

    }

    @Around("annotationLogExecutionTime()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

        Logger log = LoggerFactory.getLogger(joinPoint.getTarget().getClass());

        long start = System.currentTimeMillis();

        log.info(">>> {}", joinPoint.getSignature());

        Object proceed = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - start;

        log.info("<<< {}, Duration {} ms", joinPoint.getSignature(), executionTime);
        return proceed;
    }

}
