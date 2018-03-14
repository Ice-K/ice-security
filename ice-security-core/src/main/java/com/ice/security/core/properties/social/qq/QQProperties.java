package com.ice.security.core.properties.social.qq;

import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * Description: QQ授权登录属性配置
 * Cteated by wangpeng
 * 2018/3/15 2:14
 */
public class QQProperties extends SocialProperties {

    /**服务商id*/
    private String providerId = "qq";

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
