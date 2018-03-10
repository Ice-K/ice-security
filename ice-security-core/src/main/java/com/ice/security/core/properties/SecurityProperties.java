package com.ice.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Description:
 * Cteated by wangpeng
 * 2018/3/10 21:59
 */
@ConfigurationProperties(prefix = "ice.security")
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties();


    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }
}
