package com.ice.web.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Description：自定义拦截器
 * Cteated by wangpeng
 * 2018/3/9 9:33
 */
@Component
public class TimeInterceptor implements HandlerInterceptor {

    /**
     * 在controller方法调用之前调用
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("preHandler");
        System.out.println(((HandlerMethod) o).getBean().getClass().getName());
        System.out.println(((HandlerMethod) o).getMethod().getName());
        httpServletRequest.setAttribute("startTime", new Date().getTime());
        return true;
    }

    /**
     * 在controller方法调用之后调用，但是controller抛出异常不调用
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
        long start = (long) httpServletRequest.getAttribute("startTime");
        System.out.println("time interceptor 耗时：" + (new Date().getTime() - start));
    }

    /**
     * 在controller方法调用之后调用，不管controller是否抛出异常，类似 finally
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("afterCompletion");
        Long start = (Long) httpServletRequest.getAttribute("startTime");
        System.out.println("time interceptor 耗时：" + (new Date().getTime() - start));
        if (e != null) {
            System.out.println("exception => " + e.getMessage());
        }
    }
}
