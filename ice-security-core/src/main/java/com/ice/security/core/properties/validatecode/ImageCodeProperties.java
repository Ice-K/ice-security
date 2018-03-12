package com.ice.security.core.properties.validatecode;

/**
 * Description：图形验证码属性配置
 * Cteated by wangpeng
 * 2018/3/12 12:36
 */
public class ImageCodeProperties {

    /** 验证码图片宽度 */
    private int width = 65;

    /** 验证码图片高度. */
    private int height = 25;

    /** 验证码字符个数. */
    private int length = 4;

    /** 验证码有效期. */
    private int expireIn = 60;

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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
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
