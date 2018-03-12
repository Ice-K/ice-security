package com.ice.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * Description：验证码生成器接口
 * Cteated by wangpeng
 * 2018/3/12 14:46
 */
public interface ValidateCodeGenerator {

    ValidateCode generator(ServletWebRequest request);
}
