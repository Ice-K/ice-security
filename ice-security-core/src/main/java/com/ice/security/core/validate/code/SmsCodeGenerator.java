package com.ice.security.core.validate.code;

import com.ice.security.core.properties.SecurityProperties;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Random;

/**
 * Description：图片验证码生成器实现类
 * Cteated by wangpeng
 * 2018/3/12 15:07
 */
public class SmsCodeGenerator implements ValidateCodeGenerator {

    @Autowired SecurityProperties securityProperties;


    @Override
    public ValidateCode generator(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(securityProperties.getSmsCode().getLength());
        return new ValidateCode(code, securityProperties.getSmsCode().getExpireIn());
    }

}
