package com.ice.security.core.authentication;

import com.ice.security.core.properties.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * Description:表单登录配置
 * Cteated by wangpeng
 * 2018/3/20 23:30
 */
@Component
public class FormAuthenticationConfig {

    @Autowired
    protected AuthenticationSuccessHandler iceAuthenticationSuccessHandler;

    @Autowired
    protected AuthenticationFailureHandler iceAuthenticationFailureHandler;

    public void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .successHandler(iceAuthenticationSuccessHandler)
                .failureHandler(iceAuthenticationFailureHandler);
    }
}
