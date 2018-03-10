package com.ice.security.browser;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Description:
 * Cteated by wangpeng
 * 2018/3/10 1:01
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 自定义密码加密及校验
     * @return passwordEncoder密码校验器
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        //自定义MD5加密
        return new MD5PasswordEncoder();
        //security自带加密
        //return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.formLogin()//表单登录
        //http.httpBasic()//httpBasic登录
            .and()
            .authorizeRequests()//对请求授权
            .anyRequest()//任何请求
            .authenticated();//都需要身份认证

    }
}
