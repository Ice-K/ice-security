package com.ice.security.browser;

import com.ice.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * Description: BrowserSecurity配置加载器
 * Cteated by wangpeng
 * 2018/3/10 1:01
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private SecurityProperties securityProperties;


    @Autowired
    private AuthenticationSuccessHandler iceAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler iceAuthenticationFailureHandler;


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

        //http.httpBasic()//httpBasic登录
        http.formLogin()//表单登录
            .loginPage("/authentication/require")//自定义登录页面
            .loginProcessingUrl("/authentication/form")//配置登录请求
            .successHandler(iceAuthenticationSuccessHandler)//自定义认证成功以后的处理
            .failureHandler(iceAuthenticationFailureHandler)//自定义认证失败后的处理
            .and()
            .authorizeRequests()//对请求授权
            .antMatchers("/authentication/require",
                    securityProperties.getBrowser().getLoginPage(),
                    "/code/image").permitAll()
            .anyRequest()//任何请求
            .authenticated()//都需要身份认证
            .and()
            .csrf().disable();//关闭跨站请求功能

    }
}
