package com.ice.security.core.properties;


/**
 * Description:browserSecurity配置封装类
 * Cteated by wangpeng
 * 2018/3/10 21:59
 */
public class BrowserProperties {

    private String loginPage = "/login.html";
    private LoginType loginType = LoginType.JSON;


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
