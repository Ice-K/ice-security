package com.ice.security.core.properties.social.qq;

import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * Description: QQ授权登录配置
 * Cteated by wangpeng
 * 2018/3/15 2:14
 */
public class QQProperties extends SocialProperties {

    /**第三方id，用来决定发起第三方登录的url，默认是 qq。*/
    private String providerId = "qq";

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
