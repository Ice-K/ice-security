package com.ice.security.core.properties.validatecode;

/**
 * Description：图形验证码属性配置
 * Cteated by wangpeng
 * 2018/3/12 12:36
 */
public class ImageCodeProperties extends SmsCodeProperties{

    public ImageCodeProperties() {
        setLength(4);
        setExpireIn(120);
    }

    /** 验证码图片宽度 */
    private int width = 65;

    /** 验证码图片高度. */
    private int height = 25;

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

}
