package com.ice.security.core.properties.borwser;


import com.ice.security.core.properties.SecurityConstants;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * Description:browserSecurity属性配置文件
 * Cteated by wangpeng
 * 2018/3/10 21:59
 */
public class BrowserProperties {

    /**session属性配置*/
    @NestedConfigurationProperty
    private SessionProperties session = new SessionProperties();

    /** 标准注册页 /ice-signup.html. */
    private String signUpUrl = SecurityConstants.DEFAULT_SIGNUP_PAGE_URL;

    /** 标准登录页 /ice-login.html. */
    private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;

    /** 认证结果返回JSON或者跳转REDIRECT. */
    private LoginResponseType loginType = LoginResponseType.JSON;

    /** 记住我token存储时间设置(单位秒). */
    private int rememberMeSeconds = 60*60*24*7;

    public String getSignUpUrl() {
        return signUpUrl;
    }

    public void setSignUpUrl(String signUpUrl) {
        this.signUpUrl = signUpUrl;
    }

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

    public SessionProperties getSession() {
        return session;
    }

    public void setSession(SessionProperties session) {
        this.session = session;
    }
}
