package com.ice.web.config;

import com.ice.web.filter.TimeFilter;
import com.ice.web.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Description：注册第三方filter过滤器及拦截器
 * Cteated by wangpeng
 * 2018/3/9 9:27
 */
//@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{

    @Autowired
    private TimeInterceptor timeInterceptor;


    /**
     * 异步请求相关的四个配置
     */
//    @Override
//    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
//
//        //拦截器相关配置
//        configurer.registerCallableInterceptors();
//        configurer.registerDeferredResultInterceptors();
//        //超时时间配置
//        configurer.setDefaultTimeout(1000*60);
//        //线程池配置默认不可重用
//        configurer.setTaskExecutor();
//    }

    /**
     * 添加第三方拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInterceptor);
    }


    /**
     *  过滤器注册器
     */
    @Bean
    public FilterRegistrationBean timeFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        //将自定义过滤器加入registrationBean（注册的bean）
        TimeFilter timeFilter = new TimeFilter();
        registrationBean.setFilter(timeFilter);

        //配置要过滤的请求
        List<String> urls = new ArrayList<>();
        urls.add("/*");
        registrationBean.setUrlPatterns(urls);
        return registrationBean;
    }

}
