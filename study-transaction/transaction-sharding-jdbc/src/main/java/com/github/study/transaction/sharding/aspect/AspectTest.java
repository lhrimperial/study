package com.github.study.transaction.sharding.aspect;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


/**
 *
 */
@Aspect
@Component
public class AspectTest {

    @Before("execution(* com.github..Processor1.say(..))")
    public void pointCut(JoinPoint joinPoint) {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();

        System.out.println("targetName:"+targetName);
        System.out.println("methodName:"+methodName);
        System.out.println("arguments:"+ JSON.toJSONString(arguments));
        System.out.println("aspect....");
    }
}
