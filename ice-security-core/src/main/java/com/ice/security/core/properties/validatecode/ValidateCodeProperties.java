package com.ice.security.core.properties.validatecode;

import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * Description：
 * Cteated by wangpeng
 * 2018/3/13 11:59
 */
public class ValidateCodeProperties {

    @NestedConfigurationProperty
    private ImageCodeProperties image = new ImageCodeProperties();
    @NestedConfigurationProperty
    private SmsCodeProperties sms = new SmsCodeProperties();

    public ImageCodeProperties getImage() {
        return image;
    }

    public void setImage(ImageCodeProperties image) {
        this.image = image;
    }

    public SmsCodeProperties getSms() {
        return sms;
    }

    public void setSms(SmsCodeProperties sms) {
        this.sms = sms;
    }
}
