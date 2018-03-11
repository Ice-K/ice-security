package com.ice.security.core.properties;


/**
 * Description:browserSecurity属性配置文件
 * Cteated by wangpeng
 * 2018/3/10 21:59
 */
public class BrowserProperties {

    /** 标准登录页. */
    private String loginPage = "/login.html";

    /** 认证结果返回JSON或者跳转REDIRECT. */
    private LoginType loginType = LoginType.JSON;

    /** 验证码图片宽度 */
    private int validCodeWidth = 65;

    /** 验证码图片高度. */
    private int validCodeHeight = 25;

    /** 验证码字符个数. */
    private int validCodeLength = 4;

    /** 验证码有效期. */
    private int validTime = 60;


    public int getValidCodeWidth() {
        return validCodeWidth;
    }

    public void setValidCodeWidth(int validCodeWidth) {
        this.validCodeWidth = validCodeWidth;
    }

    public int getValidCodeHeight() {
        return validCodeHeight;
    }

    public void setValidCodeHeight(int validCodeHeight) {
        this.validCodeHeight = validCodeHeight;
    }

    public int getValidTime() {
        return validTime;
    }

    public void setValidTime(int overdueTime) {
        this.validTime = overdueTime;
    }

    public int getValidCodeLength() {
        return validCodeLength;
    }

    public void setValidCodeLength(int validateCodeLength) {
        this.validCodeLength = validateCodeLength;
    }

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }
}
