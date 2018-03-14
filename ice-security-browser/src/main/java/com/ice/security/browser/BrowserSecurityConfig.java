package com.ice.security.browser;

import com.ice.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.ice.security.core.properties.SecurityProperties;
import com.ice.security.core.validate.code.SmsCodeFilter;
import com.ice.security.core.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;


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

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;


    /**
     * 自定义密码加密及校验
     * @return passwordEncoder密码校验器
     */
    @Bean
    public PasswordEncoder passwordEncoder() {

        return new MD5PasswordEncoder();//自定义MD5加密
        //return new BCryptPasswordEncoder();//security自带加密
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        //tokenRepository.setCreateTableOnStartup(true);//在启动的时候自动创建表(存储user和token)
        return tokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(iceAuthenticationFailureHandler);
        validateCodeFilter.setSecurityProperties(securityProperties);
        validateCodeFilter.afterPropertiesSet();

        SmsCodeFilter smsCodeFilter = new SmsCodeFilter();
        smsCodeFilter.setAuthenticationFailureHandler(iceAuthenticationFailureHandler);
        smsCodeFilter.setSecurityProperties(securityProperties);
        smsCodeFilter.afterPropertiesSet();

        http.addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
            .formLogin()//表单登录
                .loginPage("/authentication/require")//自定义认证页面
                .loginProcessingUrl("/authentication/form")//配置登录请求
                .successHandler(iceAuthenticationSuccessHandler)//自定义认证成功以后的处理
                .failureHandler(iceAuthenticationFailureHandler)//自定义认证失败后的处理
                .and()
            .rememberMe()//记住我
                .tokenRepository(persistentTokenRepository())//token配置
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())//token保存时间默认一星期
                .userDetailsService(userDetailsService)//认证成功后返回userDetails
        //http.httpBasic()//httpBasic登录
                .and()
            .authorizeRequests()//对请求授权
                //忽略的请求
                .antMatchers("/authentication/require",
                        securityProperties.getBrowser().getLoginPage(),
                        "/code/*").permitAll()
                .anyRequest()//任何请求
                .authenticated()//都需要身份认证
                .and()
            .csrf().disable()//关闭跨站请求功能
            .apply(smsCodeAuthenticationSecurityConfig);

    }
}
