package com.ice.security.core.validate.code.sms;

import com.ice.security.core.properties.SecurityProperties;
import com.ice.security.core.validate.code.ValidateCode;
import com.ice.security.core.validate.code.ValidateCodeGenerator;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * Description：短信验证码生成器实现类
 * Cteated by wangpeng
 * 2018/3/12 15:07
 */
@Component("smsValidateCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {

    @Autowired SecurityProperties securityProperties;


    @Override
    public ValidateCode generator(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
        return new ValidateCode(code, securityProperties.getCode().getSms().getExpireIn());
    }

}
