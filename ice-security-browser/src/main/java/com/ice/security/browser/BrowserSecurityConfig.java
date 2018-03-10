package com.ice.security.browser;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Description:
 * Cteated by wangpeng
 * 2018/3/10 1:01
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //http.formLogin()//表单登录
        http.httpBasic()//httpBasic登录
            .and()
            .authorizeRequests()//对请求授权
            .anyRequest()//任何请求
            .authenticated();//都需要身份认证

    }
}
