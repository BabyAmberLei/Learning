package com.amber.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * 基于注解
 */
@Aspect
public class AspectOption {

    /**
     * 前置增强，在方法之前执行
     */
    @Before("execution(* com.amber.dao.ProductDao.create(..))")
    public void before() {
        System.out.println("AspectOption before");
    }

    /**
     * 后置增强，在方法执行之后执行
     */
    @After("execution(* com.amber.dao.ProductDao.delete(..))")
    public void after() {
        System.out.println("AspectOption after");
    }

    /**
     * 后置返回通知增强，可以获取到方法的返回值
     * @param result
     */
    @AfterReturning(value = "execution(* com.amber.dao.ProductDao.update(..))", returning = "result")
    public void afterReturning(String result) {
        System.out.println("AspectOption afterReturning " + result);
    }

    /**
     * 环绕通知，在方法执行之前和执行之后运行
     * @param joinPoint
     */
    @Around(value = "execution(* com.amber.dao.ProductDao.addBatch(..))")
    public void around(ProceedingJoinPoint joinPoint) {
        System.out.println("AspectOption around before");
        try {
            Object obj = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("AspectOption around after");
    }

    /**
     * 异常抛出通知,方法抛出异常的时候执行
     * @param e
     */
    @AfterThrowing(value = "execution(* com.amber.dao.ProductDao.delBatch(..))", throwing = "e")
    public void afterThrowing(Throwable e) {
        System.out.println("AspectOption afterReturing");
        System.out.println(e);
    }
}
