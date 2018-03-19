package com.ice.security.core.properties;

import com.ice.security.core.properties.OAuth2.OAuth2Properties;
import com.ice.security.core.properties.borwser.BrowserProperties;
import com.ice.security.core.properties.social.SocialProperties;
import com.ice.security.core.properties.validatecode.ValidateCodeProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * Description: Security安全框架属性配置
 * Cteated by wangpeng
 * 2018/3/10 21:59
 */
@ConfigurationProperties(prefix = "ice.security")
public class SecurityProperties {

    /**浏览器属性配置*/
    @NestedConfigurationProperty
    private BrowserProperties browser = new BrowserProperties();

    /**验证码属性配置*/
    @NestedConfigurationProperty
    private ValidateCodeProperties code = new ValidateCodeProperties();

    /**第三方授权登录属性配置*/
    @NestedConfigurationProperty
    private SocialProperties social = new SocialProperties();

    /**App OAuth2相关配置*/
    @NestedConfigurationProperty
    private OAuth2Properties oauth2 = new OAuth2Properties();

    public SocialProperties getSocial() {
        return social;
    }

    public void setSocial(SocialProperties social) {
        this.social = social;
    }

    public ValidateCodeProperties getCode() {
        return code;
    }

    public void setCode(ValidateCodeProperties code) {
        this.code = code;
    }

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }

    public OAuth2Properties getOauth2() {
        return oauth2;
    }

    public void setOauth2(OAuth2Properties oauth2) {
        this.oauth2 = oauth2;
    }
}
