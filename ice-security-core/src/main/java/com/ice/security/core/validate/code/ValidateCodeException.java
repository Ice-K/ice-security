package com.ice.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * Description：自定义验证码校验异常（用于验证码校验失败）
 * Cteated by wangpeng
 * 2018/3/12 9:53
 */
class ValidateCodeException extends AuthenticationException {
    private static final long serialVersionUID = 6447842804573687746L;

    ValidateCodeException(String msg) {
        super(msg);
    }
}
