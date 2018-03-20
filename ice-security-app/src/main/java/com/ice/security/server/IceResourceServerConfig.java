package com.ice.security.server;

import com.ice.security.app.authentication.openid.OpenIdAuthenticationSecurityConfig;
import com.ice.security.core.authentication.FormAuthenticationConfig;
import com.ice.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.ice.security.core.authorize.AuthorizeConfigManager;
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
 * Description: 资源服务器配置
 * Cteated by wangpeng
 * 2018/3/18 18:34
 */
@Configuration
@EnableResourceServer
public class IceResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    protected AuthenticationSuccessHandler iceAuthenticationSuccessHandler;

    @Autowired
    protected AuthenticationFailureHandler iceAuthenticationFailureHandler;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private OpenIdAuthenticationSecurityConfig openIdAuthenticationSecurityConfig;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private SpringSocialConfigurer iceSocialSecurityConfig;

    @Autowired
    private AuthorizeConfigManager authorizeConfigManager;

    @Autowired
    private FormAuthenticationConfig formAuthenticationConfig;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        formAuthenticationConfig.configure(http);

        http.apply(validateCodeSecurityConfig)//校验码相关配置
                .and()
            .apply(smsCodeAuthenticationSecurityConfig)//短信登录相关配置
                .and()
            .apply(iceSocialSecurityConfig)//第三方登录
                .and()
            .apply(openIdAuthenticationSecurityConfig)//openId登录验证
                .and()
            .csrf().disable();

        authorizeConfigManager.config(http.authorizeRequests());
    }
}
