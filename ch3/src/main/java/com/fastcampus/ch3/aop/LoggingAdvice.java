package com.fastcampus.ch3.aop;

import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class LoggingAdvice {
    @Around("execution(* com.fastcampus.ch3.aop.MyMath.*(..))") // pointcut : 부가기능이 적용될 메소드의 패턴
    public Object callLog(ProceedingJoinPoint pjp) throws Throwable {
        // before
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        System.out.println(
                "<<[start] "
                + pjp.getSignature().getName()          // 메소드.선언부.메소드명
                + Arrays.deepToString(pjp.getArgs())    // 매개변수 목록 배열
        );
        // before

        Object result = pjp.proceed();  // target의 method를 호출

        // after
        System.out.println("result = " + result);
        stopWatch.stop();
        System.out.println("[end]>> " + stopWatch.getTime() + " 밀리초\n");
        return result;
        // after
    }
}