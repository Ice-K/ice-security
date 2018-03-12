package com.ice.security.core.properties;

import com.ice.security.core.properties.borwser.BrowserProperties;
import com.ice.security.core.properties.validatecode.ImageCodeProperties;
import com.ice.security.core.properties.validatecode.SmsCodeProperties;
import com.ice.security.core.validate.sms.SmsCodeSender;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * Description: Security安全框架属性配置
 * Cteated by wangpeng
 * 2018/3/10 21:59
 */
@ConfigurationProperties(prefix = "ice.security")
public class SecurityProperties {

    /** 浏览器属性配置 */
    @NestedConfigurationProperty
    private BrowserProperties browser = new BrowserProperties();

    /** 图片验证码属性配置 */
    @NestedConfigurationProperty
    private ImageCodeProperties imageCode = new ImageCodeProperties();


    private SmsCodeProperties smsCode = new SmsCodeProperties();


    public ImageCodeProperties getImageCode() {
        return imageCode;
    }

    public void setImageCode(ImageCodeProperties imageCode) {
        this.imageCode = imageCode;
    }

    public SmsCodeProperties getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(SmsCodeProperties smsCode) {
        this.smsCode = smsCode;
    }

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }



}
