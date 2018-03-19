package com.ice.security.core.properties.OAuth2;

import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * Description：
 * Cteated by wangpeng
 * 2018/3/19 14:18
 */
public class OAuth2Properties {

    private String jwtSigningKey = "ice";

    @NestedConfigurationProperty
    private OAuth2ClientProperties[] clients = new OAuth2ClientProperties[]{};

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
