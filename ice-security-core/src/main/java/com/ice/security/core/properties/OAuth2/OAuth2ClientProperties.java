package com.ice.security.core.properties.OAuth2;

/**
 * Description：配置OAuth2协议支持的应用
 * Cteated by wangpeng
 * 2018/3/19 14:18
 */
public class OAuth2ClientProperties {

    /**应用的id.*/
    private String clientId;

    /**应用的id.*/
    private String clientSecret;

    /**token有效时间，单位秒.*/
    private int accessTokenValiditySeconds = 7200;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public int getAccessTokenValiditySeconds() {
        return accessTokenValiditySeconds;
    }

    public void setAccessTokenValiditySeconds(int accessTokenValiditySeconds) {
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
    }
}
