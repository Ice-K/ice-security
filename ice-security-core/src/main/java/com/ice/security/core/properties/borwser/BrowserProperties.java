package com.ice.security.core.properties.borwser;


import com.ice.security.core.properties.SecurityConstants;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * Description:浏览器环境配置项
 * Cteated by wangpeng
 * 2018/3/10 21:59
 */
public class BrowserProperties {

    /**session属性配置*/
    @NestedConfigurationProperty
    private SessionProperties session = new SessionProperties();

    /** 标准注册页 /ice-signup.html. */
    private String signUpPage = SecurityConstants.DEFAULT_SIGNUP_PAGE_URL;

    /** 标准登录页 /ice-login.html. */
    private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;

    /** 标准退出页. */
    private String logoutPage;

    /** 登录响应的方式，默认是json. */
    private LoginResponseType loginResponseType = LoginResponseType.JSON;

    /** '记住我'时间设置(单位秒),默认一星期. */
    private int rememberMeSeconds = 60*60*24*7;

    /**
     * 登录成功后跳转的地址，如果设置了此属性，则登录成功后总是会跳到这个地址上。
     *
     * 只在signInResponseType为REDIRECT时生效
     */
    private String loginSuccessUrl;

    public String getSignUpPage() {
        return signUpPage;
    }

    public void setSignUpPage(String signUpPage) {
        this.signUpPage = signUpPage;
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

    public LoginResponseType getLoginResponseType() {
        return loginResponseType;
    }

    public void setLoginResponseType(LoginResponseType loginResponseType) {
        this.loginResponseType = loginResponseType;
    }

    public SessionProperties getSession() {
        return session;
    }

    public void setSession(SessionProperties session) {
        this.session = session;
    }

    public String getLogoutPage() {
        return logoutPage;
    }

    public void setLogoutPage(String logoutPage) {
        this.logoutPage = logoutPage;
    }

    public String getLoginSuccessUrl() {
        return loginSuccessUrl;
    }

    public void setLoginSuccessUrl(String loginSuccessUrl) {
        this.loginSuccessUrl = loginSuccessUrl;
    }
}
