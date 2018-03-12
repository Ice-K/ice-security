package com.ice.security.core.properties.borwser;


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

    /** 记住我token存储时间设置(单位秒). */
    private int rememberMeSeconds = 60*60*24*7;

    public int getRememberMeSeconds() {
        return rememberMeSeconds;
    }

    public void setRememberMeSeconds(int rememberMeSeconds) {
        this.rememberMeSeconds = rememberMeSeconds;
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
