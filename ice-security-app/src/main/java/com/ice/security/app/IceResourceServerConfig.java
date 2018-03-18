package com.ice.security.app;

import com.ice.security.app.social.openid.OpenIdAuthenticationSecurityConfig;
import com.ice.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.ice.security.core.properties.SecurityConstants;
import com.ice.security.core.properties.SecurityProperties;
import com.ice.security.core.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * Description: 资源服务器
 * Cteated by wangpeng
 * 2018/3/18 18:34
 */
@Configuration
@EnableResourceServer
public class IceResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    protected AuthenticationSuccessHandler iceAuthenticationSuccessHandler;

    @Autowired
    protected AuthenticationFailureHandler iceAuthenticationFailureHandler;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private SpringSocialConfigurer iceSocialSecurityConfig;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private OpenIdAuthenticationSecurityConfig openIdAuthenticationSecurityConfig;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.formLogin()
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .successHandler(iceAuthenticationSuccessHandler)
                .failureHandler(iceAuthenticationFailureHandler);

        http.apply(validateCodeSecurityConfig)//校验码相关配置
                .and()
            .apply(smsCodeAuthenticationSecurityConfig)//短信登录相关配置
                .and()
            .apply(iceSocialSecurityConfig)//第三方登录
                .and()
            .apply(openIdAuthenticationSecurityConfig)//openId登录验证
                .and()
            .authorizeRequests()
                .antMatchers(//不需要校验的部分
                        SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                        securityProperties.getBrowser().getLoginPage(),
                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",//code/*
                        securityProperties.getBrowser().getSignUpPage(),
                        securityProperties.getBrowser().getSession().getSessionInvalidUrl(),
                        securityProperties.getBrowser().getLogoutPage(),
                        "/user/regist"//注册
                ).permitAll()
                .anyRequest()
                .authenticated()
                .and()
            .csrf().disable();
    }
}
