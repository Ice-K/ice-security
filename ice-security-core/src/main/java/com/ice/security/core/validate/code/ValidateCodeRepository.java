package com.ice.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * Description: 校验码存取器
 * 具体实现在一下两个类中：
 * 1.ice-security-browser模块：com.ice.security.browser.validate.code.impl.SessionValidateCodeRepository
 * 2.ice-security-app模块：com.ice.security.app.validate.code.impl.
 * Cteated by wangpeng
 * 2018/3/18 22:58
 */
public interface ValidateCodeRepository {

    /**
     * 保存校验码
     * @param request request请求
     * @param code  验证码字符串
     * @param type  验证码类型
     */
    void save(ServletWebRequest request, ValidateCode code, ValidateCodeType type);


    /**
     * 获取验证码
     * @param request 请求
     * @return 验证码对象
     */
    ValidateCode get(ServletWebRequest request, ValidateCodeType type);


    /**
     * 移除验证码
     * @param request 请求
     * @param type  验证码类型
     */
    void remove(ServletWebRequest request, ValidateCodeType type);
}
