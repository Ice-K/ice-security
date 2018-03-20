package com.ice.security.core.properties.OAuth2;

import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * Description：
 * Cteated by wangpeng
 * 2018/3/19 14:18
 */
public class OAuth2Properties {

    /**
     * 使用jwt时为token签名的秘钥
     */
    private String jwtSigningKey = "ice";

    /**
     * 客户端配置
     */
    @NestedConfigurationProperty
    private OAuth2ClientProperties[] clients = {};

    public OAuth2ClientProperties[] getClients() {
        return clients;
    }

    public void setClients(OAuth2ClientProperties[] clients) {
        this.clients = clients;
    }

    public String getJwtSigningKey() {
        return jwtSigningKey;
    }

    public void setJwtSigningKey(String jwtSigningKey) {
        this.jwtSigningKey = jwtSigningKey;
    }
}
