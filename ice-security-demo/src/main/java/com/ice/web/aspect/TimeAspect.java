package com.ice.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Description：AOP切面业务
 * Cteated by wangpeng
 * 2018/3/9 10:28
 */
//@Aspect
//@Component
public class TimeAspect {

    //切点
    @Pointcut("execution(public * com.ice.web.controller.UserController.*(..))")
    private void controllerHandler(){}


    /**
     * 命中后执行的方法：
     * @ Before 目标方法执行之前执行
     * @ AfterThrowing 目标方法执行中抛出异常后执行
     */
    @Around("controllerHandler()")
    public Object handlerController(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("环绕通知：在目标方法执行前后都执行");
        System.out.println("time aspect start");
        Long start = new Date().getTime();
        Object[] objects = joinPoint.getArgs();
        for (Object o : objects) {
            System.out.println(o.getClass().getName());
        }
        Object o = joinPoint.proceed();
        System.out.println("time aspect 耗时：" + (new Date().getTime() - start));
        System.out.println("time aspect end");
        return o;
    }

    /*@Before("execution(public * com.ice.web.controller.UserController.*(..))")
    public void before() {
        System.out.println("前置通知：在目标方法执行前执行");
    }

    @After("execution(public * com.ice.web.controller.UserController.*(..))")
    public void after() {
        System.out.println("后置通知：在目标方法执行后执行");
    }

    @AfterReturning(pointcut = "execution(public * com.ice.web.controller.UserController.*(..))", argNames = "jp,object", returning = "object")
    public void afterRetrunings(JoinPoint jp, Object object) {
        //注意这里的object 应该和returning="object"保持一致
        System.out.println(object);//object是目标方法返回的参数
        System.out.println(jp.getArgs() ); //通过这种方法可以获取目标方法的传入参数
    }

    @AfterThrowing("execution(public * com.ice.web.controller.UserController.*(..))")
    public void afterThrowing() {
        System.out.println("异常通知：在目标方法发生异常时执行" );
    }
*/

}
