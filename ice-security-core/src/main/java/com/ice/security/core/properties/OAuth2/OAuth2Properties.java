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
     * 使用redis存储token的配置，只有在ice.security.oauth2.tokenStore配置为redis时生效
     */
    private String tokenStore;

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

    public String getTokenStore() {
        return tokenStore;
    }

    public void setTokenStore(String tokenStore) {
        this.tokenStore = tokenStore;
    }
}
