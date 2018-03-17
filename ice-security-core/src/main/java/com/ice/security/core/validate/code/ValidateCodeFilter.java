package com.ice.security.core.validate.code;

import com.ice.security.core.properties.SecurityConstants;
import com.ice.security.core.properties.SecurityProperties;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Description：配置验证码校验过滤器
 * 实现InitializingBean接口的目的是为了
 * 在所有的配置属性都装配完成以后初始化urls
 * Cteated by wangpeng
 * 2018/3/12 9:47
 */
@Component("validateCodeFilter")
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    /**
     * 验证码校验失败处理器
     */
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    /**
     * 系统配置信息
     */
    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 系统中的校验码处理器
     */
    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    /**
     * 存放所有需要校验验证码的url
     */
    private Map<String, ValidateCodeType> urlMap = new HashMap<>();

    /**
     * 验证请求url与配置的url是否匹配的工具类
     */
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    /**
     * 初始化要验证的url set集合
     */
    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();

        if (securityProperties.getCode().getImage().isEnable()) {//是否开启登录验证码
            urlMap.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM, ValidateCodeType.IMAGE);//登录请求
        }
        addUrlToMap(securityProperties.getCode().getImage().getUrls(), ValidateCodeType.IMAGE);//其他请求

        if (securityProperties.getCode().getSms().isEnable()) {
            urlMap.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE, ValidateCodeType.SMS);//登录请求
        }
        addUrlToMap(securityProperties.getCode().getSms().getUrls(), ValidateCodeType.SMS);//其他请求
    }

    /**
     * 将系统中配置的需要校验验证码的url根据校验的类型（image，sms）放入map中
     *
     * @param urlString 系统中配置的需要校验的url
     * @param type      验证类型
     */
    protected void addUrlToMap(String urlString, ValidateCodeType type) {
        if (StringUtils.isNotBlank(urlString)) {
            String[] urls = StringUtils.splitByWholeSeparatorPreserveAllTokens(urlString, ",");
            for (String url : urls) {
                urlMap.put(url, type);
            }
        }
    }

    /**
     * 过滤请求，判断是否需要校验
     *
     * @param httpServletRequest  request请求
     * @param httpServletResponse response响应
     * @param filterChain         filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        ValidateCodeType type = getValidateCodeType(httpServletRequest);
        if (type != null) {
            logger.info("校验请求 <" + httpServletRequest.getRequestURI() + "> 中的验证码，验证类型" + type);
            try {
                validateCodeProcessorHolder.findValidateCodeProcessor(type)
                        .validate(new ServletWebRequest(httpServletRequest, httpServletResponse));
                logger.info("验证码校验通过");
            } catch (ValidateCodeException vce) {
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, vce);
                return;
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }


    /**
     * 根据请求来获取校验码的类型，如果请求不需要验证，则返回null
     * @param request http请求
     * @return  验证类型
     */
    private ValidateCodeType getValidateCodeType(HttpServletRequest request) {
        ValidateCodeType result = null;
        if (!StringUtils.equalsIgnoreCase(request.getMethod(), "get")) {
            Set<String> urls = urlMap.keySet();
            for (String url : urls) {
                if (pathMatcher.match(url, request.getRequestURI())) {
                    result = urlMap.get(url);
                }
            }
        }
        return result;

    }

}
