package com.ice.security.core.properties;

import com.ice.security.core.properties.borwser.BrowserProperties;
import com.ice.security.core.properties.validatecode.ImageCodeProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * Description: Security安全框架属性配置
 * Cteated by wangpeng
 * 2018/3/10 21:59
 */
@ConfigurationProperties(prefix = "ice.security")
public class SecurityProperties {

    /** 浏览器属性配置 */
    @NestedConfigurationProperty
    private BrowserProperties browser = new BrowserProperties();

    @NestedConfigurationProperty
    private ImageCodeProperties codeImage = new ImageCodeProperties();

    public ImageCodeProperties getCodeImage() {
        return codeImage;
    }

    public void setCodeImage(ImageCodeProperties codeImage) {
        this.codeImage = codeImage;
    }

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }



}
