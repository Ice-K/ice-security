package com.ice.security.core.properties.validatecode;

/**
 * Description：短信验证码属性配置
 * Cteated by wangpeng
 * 2018/3/12 18:08
 */
public class SmsCodeProperties {

    /** 验证码长度. */
    private int length = 6;

    /** 验证码有效期 */
    private int expireIn = 180;

    /** 需要验证的url 格式 /xxxx,/xxxx. */
    private String urls;

    /** 是否启用登录验证码. */
    private boolean enable = true;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(int expireIn) {
        this.expireIn = expireIn;
    }
}
