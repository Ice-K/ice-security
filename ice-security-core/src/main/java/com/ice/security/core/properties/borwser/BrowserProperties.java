package com.ice.security.core.properties.borwser;


import com.ice.security.core.properties.SecurityConstants;

/**
 * Description:browserSecurity属性配置文件
 * Cteated by wangpeng
 * 2018/3/10 21:59
 */
public class BrowserProperties {

    /** 标准登录页. */
    private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;

    /** 认证结果返回JSON或者跳转REDIRECT. */
    private LoginResponseType loginType = LoginResponseType.JSON;

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

    public LoginResponseType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginResponseType loginType) {
        this.loginType = loginType;
    }
}
