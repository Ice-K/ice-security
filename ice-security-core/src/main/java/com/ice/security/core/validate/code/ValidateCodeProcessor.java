package com.ice.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * Description：校验码处理器，封装不同校验码(图片验证码，短信验证码)的处理逻辑
 * Cteated by wangpeng
 * 2018/3/13 9:27
 */
public interface ValidateCodeProcessor {

    /**
     * 创建校验码
     * @param servletWebRequest 请求
     */
    void create(ServletWebRequest servletWebRequest) throws Exception;

    /**
     * 校验验证码
     * @param servletWebRequest 请求
     */
    void validate(ServletWebRequest servletWebRequest);

}
