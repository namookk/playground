package com.playground.user.payload.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class FieldConverterAspect {

    @Before("get(* *) && @annotation(com.playground.user.payload.annotation.DateHyphen)")
    public void interceptRead(JoinPoint thisJoinPoint) {
        System.out.println(thisJoinPoint);
    }

    @Before("set(* *) && @annotation(com.playground.user.payload.annotation.DateHyphen) && args(newValue)")
    public void interceptWrite(JoinPoint thisJoinPoint, Object newValue) {
        System.out.println(thisJoinPoint + " -> " + newValue);
    }
}
