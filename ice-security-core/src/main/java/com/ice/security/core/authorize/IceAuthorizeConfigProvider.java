package com.ice.security.core.authorize;

import com.ice.security.core.properties.SecurityConstants;
import com.ice.security.core.properties.SecurityProperties;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * Description:核心模块的授权配置提供器，安全模块涉及的url的授权配置在这里
 * Cteated by wangpeng
 * 2018/3/20 23:19
 */
@Component
@Order(Integer.MIN_VALUE)
public class IceAuthorizeConfigProvider implements AuthorizeConfigProvider {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {

        config.antMatchers(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_OPENID,
                SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
                securityProperties.getBrowser().getLoginPage(),
                securityProperties.getBrowser().getSignUpPage(),
                securityProperties.getBrowser().getSession().getSessionInvalidUrl()).permitAll();
        if (StringUtils.isNotBlank(securityProperties.getBrowser().getLogoutPage())) {
            config.antMatchers(securityProperties.getBrowser().getLogoutPage()).permitAll();
        }
        return false;
    }
}
