package com.ice.security.core.properties.social.wechat;


import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * Description：微信属性配置
 * Cteated by wangpeng
 * 2018/3/16 10:00
 */
public class WeChatProperties extends SocialProperties {

    /**
     * 第三方id，用来决定发起第三方登录的url默认是微信
     */
    private String providerId = "weixin";

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
