package com.ice.security.browser.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ice.security.core.properties.borwser.LoginResponseType;
import com.ice.security.core.properties.SecurityProperties;
import com.ice.security.core.support.SimpleResponse;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description: 认证成功以后的处理
 *
 * authentication接口：作用：封装认证信息，
 * 包括：
 * 1.认证请求里面的信息，认证请求的ip，session
 * 2.UserDetails 认证授权信息
 *
 * Cteated by wangpeng
 * 2018/3/10 22:54
 */
@Component("iceAuthenticationSuccessHandler")
public class IceAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;

    private RequestCache requestCache = new HttpSessionRequestCache();

    /**
     * (non-Javadoc)
     *
     * @see org.springframework.security.web.authentication.AuthenticationSuccessHandler#onAuthenticationSuccess(
     * javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
     * org.springframework.security.core.Authentication)
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        logger.info("登录成功");

        if (LoginResponseType.JSON.equals(securityProperties.getBrowser().getLoginResponseType())) {//如果配置返回的是JSON
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            String type = authentication.getClass().getSimpleName();
            httpServletResponse.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(type)));
        } else {//如果配置为认证成功后跳转
            //如果设置了ice.security.browser.loginSuccessUrl,总是调到设置的地址上
            //如果没有设置，则尝试跳转到登录之前访问的地址上，如果登录前访问的地址为空，则跳到网站跟路径上
            if (StringUtils.isNotBlank(securityProperties.getBrowser().getLoginSuccessUrl())) {
                requestCache.removeRequest(httpServletRequest, httpServletResponse);
                setAlwaysUseDefaultTargetUrl(true);
                setDefaultTargetUrl(securityProperties.getBrowser().getLoginSuccessUrl());
            }
            super.onAuthenticationSuccess(httpServletRequest,httpServletResponse,authentication);
        }

    }
}
