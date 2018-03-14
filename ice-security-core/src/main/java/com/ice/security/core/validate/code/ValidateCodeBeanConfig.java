package com.ice.security.core.validate.code;

import com.ice.security.core.properties.SecurityProperties;
import com.ice.security.core.validate.code.image.ImageCodeGenerator;
import com.ice.security.core.validate.code.sms.DefaultSmsCodeSender;
import com.ice.security.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description：注册额外的图片验证码生成器
 * @ ConditionalOnMissingBean 注解说明：
 * 当spring容器在容器中没有找到名字为"imageCodeGenerator"的类时，
 * 执行下面的方法
 * Cteated by wangpeng
 * 2018/3/12 15:52
 */
@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
    public ValidateCodeGenerator imageValidateCodeGenerator() {
        ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
        codeGenerator.setSecurityProperties(securityProperties);
        return codeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender() {
        return new DefaultSmsCodeSender();
    }
}
