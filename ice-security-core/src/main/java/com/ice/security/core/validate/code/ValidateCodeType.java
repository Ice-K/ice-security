package com.ice.security.core.validate.code;

import com.ice.security.core.properties.SecurityConstants;

/**
 * Description：
 * Cteated by wangpeng
 * 2018/3/13 9:56
 */
public enum ValidateCodeType {

    /**
     * 短信验证码
     */
    SMS{
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_SMS;
        }
    },

    /**
     * 图片验证码
     */
    IMAGE{
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_IMAGE;
        }
    };

    /**
     * 校验时从请求中获取的参数名字
     * @return 参数名字
     */
    public abstract String getParamNameOnValidate();
}
