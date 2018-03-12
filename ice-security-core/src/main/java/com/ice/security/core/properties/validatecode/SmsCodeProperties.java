package com.ice.security.core.properties.validatecode;

/**
 * Description：
 * Cteated by wangpeng
 * 2018/3/12 18:08
 */
public class SmsCodeProperties {

    /** 短信验证码长度. */
    private int length = 6;

    /** 短信验证码有效期 */
    private int expireIn = 180;

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
