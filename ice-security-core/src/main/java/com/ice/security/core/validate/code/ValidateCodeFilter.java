package com.ice.security.core.validate.code;

import com.ice.security.core.properties.SecurityProperties;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Description：配置验证码校验过滤器
 * 实现InitializingBean接口的目的是为了
 * 在所有的配置属性都装配完成以后初始化urls
 * Cteated by wangpeng
 * 2018/3/12 9:47
 */
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    private AuthenticationFailureHandler authenticationFailureHandler;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    private Set<String> urls = new HashSet<>();

    private SecurityProperties securityProperties;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();//Spring 的一个工具类

    /**
     * 初始化要验证的url set集合
     */
    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        String[] configUrls = StringUtils.split(securityProperties.getCodeImage().getUrls(),",");
        if (configUrls != null) {
            Collections.addAll(urls, configUrls);
        }
        if (securityProperties.getCodeImage().isEnable()) {
            urls.add("/authentication/form");
        }
    }

    /**
     * 过滤请求，判断是否需要校验
     * @param httpServletRequest request请求
     * @param httpServletResponse response响应
     * @param filterChain         filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        //将request请求的路径与需要验证的路径进行匹配
        boolean action = false;
        for (String url : urls) {
            if (antPathMatcher.match(url,httpServletRequest.getRequestURI())) {
                action = true;
            }
        }

        if (action) {//如果request的请求路径为需要验证的路径则进行验证
            try {
                validate(new ServletWebRequest(httpServletRequest));
            } catch (ValidateCodeException vce) {
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, vce);
                return;
            }
        }

        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }


    /**
     * 执行验证码校验
     * @param servletWebRequest web请求
     */
    private void validate(ServletWebRequest servletWebRequest) throws ServletRequestBindingException {

        //session中的验证码对象
        ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(servletWebRequest,ValidateCodeController.SESSION_KEY);
        //用户输入的验证码字符串
        String codeInRequest = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(),"imageCode");

        if (StringUtils.isBlank(codeInRequest)) {//如果请求中的验证码为空
            throw new ValidateCodeException("请输入验证码");
        }

        if (codeInSession == null) {//如果Session中的验证码为空
            throw new ValidateCodeException("验证码不存在");
        }

        if (codeInSession.isExpried()) {//如果session中的验证码过期
            sessionStrategy.removeAttribute(servletWebRequest,ValidateCodeController.SESSION_KEY);
            throw new ValidateCodeException("验证码已过期");
        }

        if (!StringUtils.equalsIgnoreCase(codeInRequest,codeInSession.getCode())) {//如果请求中的验证码和session中的验证码不匹配
            throw new ValidateCodeException("验证码错误");
        }

        //验证码匹配成功
        sessionStrategy.removeAttribute(servletWebRequest, ValidateCodeController.SESSION_KEY);
    }


    public Set<String> getUrls() {
        return urls;
    }

    public void setUrls(Set<String> urls) {
        this.urls = urls;
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    public SessionStrategy getSessionStrategy() {
        return sessionStrategy;
    }

    public void setSessionStrategy(SessionStrategy sessionStrategy) {
        this.sessionStrategy = sessionStrategy;
    }
}
